package com.example.papaassistant.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.example.papaassistant.Adapter.ModifyRecipeViewPagerAdapter;
import com.example.papaassistant.R;
import com.example.papaassistant.Recipe;

public class ModifyRecipeActivity extends AppCompatActivity {

    private EditText editTextRecipeName;
    private EditText editNumberOfPeople;
    private EditText editHealthScore;
    private EditText editReadyTime;
    private ViewPager2 viewpager;

    private Recipe recipe;
    private ModifyRecipeViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        
        initComponents();
        initTextWatchers();
    }

    private void initTextWatchers() {
        editTextRecipeName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                recipe.recipe.setName(s.toString());
            }
        });
        editNumberOfPeople.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                recipe.recipe.setNumberOfPeople(Integer.parseInt(s.toString()));
            }
        });
        editHealthScore.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                recipe.recipe.setHealthScore(Double.parseDouble(s.toString()));
            }
        });
        editReadyTime.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                recipe.recipe.setReadyTime(Integer.parseInt(s.toString()));
            }
        });
    }

    private void initComponents() {
        editTextRecipeName = findViewById(R.id.editTextRecipe);
        editNumberOfPeople = findViewById(R.id.editTextRecipeNumberOfPeople);
        editHealthScore = findViewById(R.id.editTextRecipeHealthScore);
        editReadyTime = findViewById(R.id.editTextRecipeReadyTime);
        viewpager = findViewById(R.id.recipeViewPager);

        Intent intent = getIntent();
        recipe = (Recipe) intent.getSerializableExtra("recipe");
        if (recipe == null) return;

        editTextRecipeName.setText(recipe.recipe.getName());
        editNumberOfPeople.setText(String.valueOf(recipe.recipe.getNumberOfPeople()));
        editHealthScore.setText(String.valueOf(recipe.recipe.getHealthScore()));
        editReadyTime.setText(String.valueOf(recipe.recipe.getReadyTime()));

        adapter = new ModifyRecipeViewPagerAdapter(this, recipe);
        viewpager.setAdapter(adapter);
    }
}