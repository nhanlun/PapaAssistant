package com.example.papaassistant.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.papaassistant.Adapter.SearchListAdapter;
import com.example.papaassistant.R;
import com.example.papaassistant.Recipe;
import com.example.papaassistant.RecipeAPIGETer;
import com.example.papaassistant.uiThreadCallback;

import java.util.ArrayList;
import java.util.HashMap;

public class SearchActivity extends AppCompatActivity implements uiThreadCallback {
    private ArrayList<Recipe> recipes;
//    private TextView textView;
    private RecyclerView recyclerView;
    private String query;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        startAsyncTask();
        initComponent();
    }

    private void initComponent() {
//        textView = findViewById(R.id.searchResultTextView);
        recyclerView = findViewById(R.id.listRecipe);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(lm);
        Toast toast = Toast.makeText(this, "Searching", Toast.LENGTH_SHORT);
        toast.show();
    }

    private void startAsyncTask() {
        Intent intent = getIntent();
        HashMap<String, String> arguments = (HashMap<String, String>) intent.getSerializableExtra("arguments");

        RecipeAPIGETer geter = new RecipeAPIGETer(this, arguments);
        geter.setUiThreadCallbackWeakReference(this);
        geter.execute();
    }

    @Override
    public void publishToUiThread(ArrayList<Recipe> recipeArrayList) {
        recipes = recipeArrayList;
        Toast toast = Toast.makeText(this, "Displaying " + recipes.size() + " recipes", Toast.LENGTH_SHORT);
        toast.show();
//        textView.setText(getString(R.string.search_found, query, recipes.size()));

        SearchListAdapter adapter = new SearchListAdapter(this, recipes);
        adapter.setOnItemClickListener(new SearchListAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
//                Log.d("ItemClickListener", "huhu click roi ma");
                Recipe recipe = recipes.get(position);
                Intent intent = new Intent(SearchActivity.this, RecipeActivity.class);
                intent.putExtra("recipe", recipe);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);


    }
}
