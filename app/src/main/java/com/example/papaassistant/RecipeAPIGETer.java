package com.example.papaassistant;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;


public class RecipeAPIGETer extends AsyncTask<Void, Void, ArrayList<Recipe>> {
    private static final String BASEURL = "https://api.spoonacular.com/recipes/complexSearch";
    private static final String APIKEY = "apiKey";
    final String apiKey;
    private static final String QUERY = "query";
    private static final String RECIPEINFO = "addRecipeInformation";
    private static final String RECIPENUTRI = "addRecipeNutrition";
    private static final String INSTRUCTION = "instructionsRequired";
    private static final String NUMBER = "number";
    private static final String DISHTYPE = "type";

    final private String baseImageUrl = "https://spoonacular.com/recipeImages/";
    final private String imageSize = "-480x360.";
    private HashMap<String, String> arguments;

    WeakReference<uiThreadCallback> uiThreadCallbackWeakReference;

    public RecipeAPIGETer(Context context, HashMap<String, String> arguments) {
        apiKey = context.getString(R.string.APIKey);
        this.arguments = arguments;
    }

    private String makeRequest(URL url) {
        //TODO: Check network condition
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        StringBuilder builder = new StringBuilder();

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null)
                urlConnection.disconnect();
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if (builder.length() == 0)
            return null;
        else
            return builder.toString();
    }

    private ArrayList<Recipe> parseRecipes(String JSONString) {
        JSONObject jsonObject = null;
        Integer resultNumber = 0;
        ArrayList<Recipe> recipes = new ArrayList<>();
        try {
            jsonObject = new JSONObject(JSONString);
            resultNumber = jsonObject.getInt("number");
            JSONArray resultArr = jsonObject.getJSONArray("results");
            for (int i = 0; i < resultNumber; ++i) {
                JSONObject recipeJSON = resultArr.getJSONObject(i);
                Recipe recipe = parseARecipe(recipeJSON);
                recipes.add(recipe);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return recipes;
    }

    private Recipe parseARecipe(JSONObject recipeJSON) {

        try {
            String name = recipeJSON.getString("title").toString();
            Integer id = recipeJSON.getInt("id");
            String imageExtension = recipeJSON.getString("imageType");
            String imageLink = baseImageUrl + id + imageSize + imageExtension;
            String ingredient = parseIngredient(recipeJSON);

            ArrayList<Instruction> instructions = parseInstructions(recipeJSON);
            Double healthyPoint = recipeJSON.getDouble("healthScore");
            Integer serveTime = recipeJSON.getInt("readyInMinutes");
            Integer serving = recipeJSON.getInt("servings");
            return new Recipe(id, name, imageLink, ingredient, healthyPoint, serving, serveTime, instructions);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;

    }

    private ArrayList<Instruction> parseInstructions(JSONObject recipeJSON) {
        ArrayList<Instruction> instructions = new ArrayList<>();
        try {
            JSONArray instructionsJSON = recipeJSON
                    .getJSONArray("analyzedInstructions").getJSONObject(0)
                    .getJSONArray("steps");
            int n = instructionsJSON.length();
            for (int i = 1; i <= n; ++i) {
                JSONObject instructionJSON = instructionsJSON.getJSONObject(i - 1);
                String description = instructionJSON.getString("step").toString();
                instructions.add(new Instruction(i, description));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return instructions;
    }

    private String parseIngredient(JSONObject recipeJSON) {
        StringBuilder builder = new StringBuilder();
        try {
            JSONArray ingredientsJSON = recipeJSON.getJSONObject("nutrition")
                    .getJSONArray("ingredients");
            int n = ingredientsJSON.length();
            for (int i = 0; i < n; ++i) {
                JSONObject ingredientJSON = ingredientsJSON.getJSONObject(i);
                String name = ingredientJSON.getString("name").toString();
                String amount = ingredientJSON.getString("amount").toString();
                String unit = ingredientJSON.getString("unit").toString();
                String line = name + ": " + amount + " " + unit;
                builder.append(line);
                builder.append('\n');
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (builder.length() == 0)
            return "";
        else
            return builder.toString();
    }

    private URL ParseUrl(HashMap<String, String> arguments) {

        Uri.Builder buildUri = Uri.parse(BASEURL).buildUpon()
                .appendQueryParameter(APIKEY, apiKey);

        if (arguments.containsKey(DISHTYPE)) {
            buildUri.appendQueryParameter(DISHTYPE, arguments.get("type"));
        } else {
            buildUri.appendQueryParameter(QUERY, arguments.get("query"));
        }
        buildUri.appendQueryParameter(RECIPEINFO, "true")
                .appendQueryParameter(RECIPENUTRI, "true")
                .appendQueryParameter(INSTRUCTION, "true")
                .appendQueryParameter(NUMBER, "20");

        Uri uri = buildUri.build();
        Log.d("URL", uri.toString());

        try {
            return new URL(buildUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setUiThreadCallbackWeakReference(uiThreadCallback uiThreadCallback) {
        this.uiThreadCallbackWeakReference = new WeakReference<>(uiThreadCallback);
    }

    @Override
    protected ArrayList<Recipe> doInBackground(Void... voids) {
        Log.d("APIKey", APIKEY);
        ArrayList<Recipe> response;
        URL url = ParseUrl(arguments);

        String responseString = makeRequest(url);
        response = parseRecipes(responseString);

        Log.d("API", "Get complete");
        return response;
    }

    @Override
    protected void onPostExecute(ArrayList<Recipe> recipes) {
        super.onPostExecute(recipes);
        if (uiThreadCallbackWeakReference != null && uiThreadCallbackWeakReference.get() != null) {
            uiThreadCallbackWeakReference.get().publishToUiThread(recipes);
        }
    }
}
