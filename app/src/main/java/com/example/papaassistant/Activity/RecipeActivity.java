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

    private static final String LOG_TAG = RecipeActivity.class.getSimpleName();

    private RecipeRepository recipeRepository;

    private Recipe recipe;

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
        EditText editTextRecipe = findViewById(R.id.editTextRecipe);
        ViewPager2 viewPager2 = findViewById(R.id.recipeViewPager);
        TextView textViewNumberOfPeople = findViewById(R.id.textViewRecipeNumberOfPeople);
        TextView textViewHealthScore = findViewById(R.id.textViewRecipeHealthScore);
        TextView textViewReadyTime = findViewById(R.id.textViewRecipeReadyTime);

        Intent intent = getIntent();
        recipe = (Recipe) intent.getSerializableExtra("recipe");
        Bitmap bitmap = intent.getParcelableExtra("image");

        if (recipe == null)
            return;

        editTextRecipe.setText(recipe.recipe.getName());
        textViewNumberOfPeople.setText(String.valueOf(recipe.recipe.getNumberOfPeople()));
        textViewHealthScore.setText(String.valueOf(recipe.recipe.getHealthScore()));
        textViewReadyTime.setText(String.valueOf(recipe.recipe.getReadyTime()));
        RecipeViewPagerAdapter adapter = new RecipeViewPagerAdapter(this, recipe, bitmap);
        viewPager2.setAdapter(adapter);
    }
}