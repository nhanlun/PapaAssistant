package com.example.papaassistant.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_recipe, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        saveRecipeIntoLibrary();
        return super.onOptionsItemSelected(item);
    }

    private void saveRecipeIntoLibrary() {
        // TODO: finish this huhu
        Toast toast = Toast.makeText(this, "Saved", Toast.LENGTH_SHORT);
        toast.show();
        recipeRepository.insertRecipeToLibrary(recipe);
    }

    private void putRecipeIntoHistory() {
        if (recipe == null)
            return;
        Date date = Calendar.getInstance().getTime();
        recipe.recipe.setLastAccess(date);
        recipeRepository.insertRecipeToHistory(recipe);
    }

    private void initComponents() {
        recipeRepository = new RecipeRepository(this.getApplication());
        EditText editTextRecipe = findViewById(R.id.editTextRecipe);
        ViewPager2 viewPager2 = findViewById(R.id.recipeViewPager);
        TextView textViewNumberOfPeople = findViewById(R.id.textViewRecipeNumberOfPeople);
        TextView textViewHealthScore = findViewById(R.id.textViewRecipeHealthScore);
        TextView textViewReadyTime = findViewById(R.id.textViewRecipeReadyTime);

        Intent intent = getIntent();
        recipe = (Recipe) intent.getSerializableExtra("recipe");

        if (recipe == null)
            return;

        editTextRecipe.setText(recipe.recipe.getName());
        textViewNumberOfPeople.setText(String.valueOf(recipe.recipe.getNumberOfPeople()));
        textViewHealthScore.setText(String.valueOf(recipe.recipe.getHealthScore()));
        textViewReadyTime.setText(String.valueOf(recipe.recipe.getReadyTime()));
        RecipeViewPagerAdapter adapter = new RecipeViewPagerAdapter(this, recipe);
        viewPager2.setAdapter(adapter);
    }
}