package com.example.papaassistant.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.papaassistant.Adapter.ModifyRecipeViewPagerAdapter;
import com.example.papaassistant.R;
import com.example.papaassistant.Recipe;
import com.example.papaassistant.RecipeRepository;

public class ModifyRecipeActivity extends AppCompatActivity {

    private EditText editTextRecipeName;
    private EditText editNumberOfPeople;
    private EditText editHealthScore;
    private EditText editReadyTime;
    private ViewPager2 viewpager;

    private Recipe recipe;
    private Recipe cacheRecipe;
    private ModifyRecipeViewPagerAdapter adapter;
    private RecipeRepository recipeRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        
        initComponents();
        initTextWatchers();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_library, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.optionSave) {
            saveToLibrary();
            return true;
        }
        else if (id == R.id.optionDelete) {
            deleteFromLibrary();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void deleteFromLibrary() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Warning")
                .setMessage(R.string.delete_from_library);
        builder.setPositiveButton("Sure", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        recipeRepository.deleteLibrary(cacheRecipe);
                        finish();
                    }
                });
        builder.setNegativeButton("Nah", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) { }
        });
        builder.show();
    }

    private void saveToLibrary() {
        recipeRepository.insertRecipeToLibrary(recipe);
        Toast toast = Toast.makeText(this, "Saved", Toast.LENGTH_SHORT);
        toast.show();
        cacheRecipe = recipe;
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
        recipeRepository = new RecipeRepository(getApplication());
        editTextRecipeName = findViewById(R.id.editTextRecipe);
        editNumberOfPeople = findViewById(R.id.editTextRecipeNumberOfPeople);
        editHealthScore = findViewById(R.id.editTextRecipeHealthScore);
        editReadyTime = findViewById(R.id.editTextRecipeReadyTime);
        viewpager = findViewById(R.id.recipeViewPager);

        Intent intent = getIntent();
        recipe = (Recipe) intent.getSerializableExtra("recipe");
        if (recipe == null) finish();
        cacheRecipe = recipe;

        editTextRecipeName.setText(recipe.recipe.getName());
        editNumberOfPeople.setText(String.valueOf(recipe.recipe.getNumberOfPeople()));
        editHealthScore.setText(String.valueOf(recipe.recipe.getHealthScore()));
        editReadyTime.setText(String.valueOf(recipe.recipe.getReadyTime()));

        adapter = new ModifyRecipeViewPagerAdapter(this, recipe);
        viewpager.setAdapter(adapter);
    }
}