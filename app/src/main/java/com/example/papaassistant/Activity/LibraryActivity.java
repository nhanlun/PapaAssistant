package com.example.papaassistant.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.papaassistant.Adapter.SearchListAdapter;
import com.example.papaassistant.R;
import com.example.papaassistant.Recipe;
import com.example.papaassistant.RecipeRepository;

import java.util.ArrayList;
import java.util.List;

public class LibraryActivity extends AppCompatActivity {

    private static final String LOG_TAG = LibraryActivity.class.getSimpleName();

    private RecipeRepository recipeRepository;
    private ArrayList<Recipe> recipes;
    private RecyclerView recyclerView;
    private SearchListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        initComponents();
    }

    private void initComponents() {
        recyclerView = findViewById(R.id.listRecipe);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(lm);
        recipeRepository = new RecipeRepository(getApplication());
        LiveData<List<Recipe>> recipesLiveData = recipeRepository.getRecipeInLibrary();

        recipesLiveData.observe(this, new Observer<List<Recipe>>() {
            @Override
            public void onChanged(List<Recipe> recipesTmp) {
                recipes = (ArrayList<Recipe>) recipesTmp;
                adapter = new SearchListAdapter(getApplicationContext(), recipes);
                adapter.setOnItemClickListener(new SearchListAdapter.ClickListener() {
                    @Override
                    public void onItemClick(int position, View v) {
                        Recipe recipe = recipes.get(position);
                        Log.d(LOG_TAG, "Step of recipe: " + recipe.instructions.size());
                        Intent intent = new Intent(LibraryActivity.this, ModifyRecipeActivity.class);
                        intent.putExtra("recipe", recipe);
                        startActivity(intent);
                    }
                });
                recyclerView.setAdapter(adapter);
            }
        });
    }
}