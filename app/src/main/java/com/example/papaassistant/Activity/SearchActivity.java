package com.example.papaassistant.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.papaassistant.Adapter.SearchListAdapter;
import com.example.papaassistant.R;
import com.example.papaassistant.Recipe;
import com.example.papaassistant.RecipeAPIGETer;
import com.example.papaassistant.uiThreadCallback;

import java.util.ArrayList;
import java.util.Formatter;
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
    }

    private void startAsyncTask() {
        Intent intent = getIntent();
        query = intent.getStringExtra("query");

        HashMap<String, String> arguments = new HashMap<>();
        arguments.put("query", query);

        RecipeAPIGETer geter = new RecipeAPIGETer(this, arguments);
        geter.setUiThreadCallbackWeakReference(this);
        geter.execute();
    }

    @Override
    public void publishToUiThread(ArrayList<Recipe> recipeArrayList) {
        recipes = recipeArrayList;
//        textView.setText(getString(R.string.search_found, query, recipes.size()));

        SearchListAdapter adapter = new SearchListAdapter(this, recipes);
        recyclerView.setAdapter(adapter);
        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

    }
}
