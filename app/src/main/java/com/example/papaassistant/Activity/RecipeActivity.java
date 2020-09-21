package com.example.papaassistant.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.papaassistant.Adapter.RecipeViewPagerAdapter;
import com.example.papaassistant.R;
import com.example.papaassistant.Recipe;
import com.example.papaassistant.RecipeRepository;

import java.util.Calendar;
import java.util.Date;

public class RecipeActivity extends AppCompatActivity {

    static final String LOG_TAG = RecipeActivity.class.getSimpleName();

    EditText editTextRecipe;
    ViewPager2 viewPager2;
    RecipeViewPagerAdapter adapter;
    TextView textViewNumberOfPeople;
    TextView textViewHealthScore;
    TextView textViewReadyTime;

    RecipeRepository recipeRepository;

    Recipe recipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        initComponents();
        putRecipeIntoHistory();
    }

    private void putRecipeIntoHistory() {
        if (recipe == null)
            return;
        Date date = Calendar.getInstance().getTime();
        recipe.recipe.setLastAccess(date);
        recipeRepository = new RecipeRepository(this.getApplication());
        recipeRepository.insertRecipeToHistory(recipe);
    }

    private void initComponents() {
        editTextRecipe = findViewById(R.id.editTextRecipe);
        viewPager2 = findViewById(R.id.recipeViewPager);
        textViewNumberOfPeople = findViewById(R.id.textViewRecipeNumberOfPeople);
        textViewHealthScore = findViewById(R.id.textViewRecipeHealthScore);
        textViewReadyTime = findViewById(R.id.textViewRecipeReadyTime);

        Intent intent = getIntent();
        Recipe recipe = (Recipe) intent.getSerializableExtra("recipe");
        Bitmap bitmap = intent.getParcelableExtra("image");

        if (recipe == null)
            return;

        editTextRecipe.setText(recipe.recipe.getName());
        textViewNumberOfPeople.setText(String.valueOf(recipe.recipe.getNumberOfPeople()));
        textViewHealthScore.setText(String.valueOf(recipe.recipe.getHealthScore()));
        textViewReadyTime.setText(String.valueOf(recipe.recipe.getReadyTime()));
        adapter = new RecipeViewPagerAdapter(this, recipe, bitmap);
        viewPager2.setAdapter(adapter);
    }
}