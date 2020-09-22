package com.example.papaassistant.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import android.os.Bundle;

import com.example.papaassistant.HistoryDatabase;
import com.example.papaassistant.R;
import com.example.papaassistant.Recipe;
import com.example.papaassistant.RecipeRepository;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class HistoryActivity extends AppCompatActivity {

    private static final String LOG_TAG = HistoryActivity.class.getSimpleName();

    private RecipeRepository recipeRepository;
    private LiveData<List<Recipe>> recipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        initComponents();
    }

    private void initComponents() {
        recipeRepository = new RecipeRepository(getApplication());
        recipes = recipeRepository.getRecipeInHistory();


    }
}