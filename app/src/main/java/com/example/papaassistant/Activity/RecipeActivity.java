package com.example.papaassistant.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.EditText;

import com.example.papaassistant.Adapter.RecipeViewPagerAdapter;
import com.example.papaassistant.R;
import com.example.papaassistant.Recipe;

public class RecipeActivity extends AppCompatActivity {

    static final String LOG_TAG = RecipeActivity.class.getSimpleName();

    EditText editTextRecipe;
    ViewPager2 viewPager2;
    RecipeViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        initComponents();
    }

    private void initComponents() {
        editTextRecipe = findViewById(R.id.editTextRecipe);
        viewPager2 = findViewById(R.id.recipeViewPager);

        Intent intent = getIntent();
        Recipe recipe = (Recipe) intent.getSerializableExtra("recipe");
        Bitmap bitmap = intent.getParcelableExtra("image");

        if (recipe == null)
            return;

        Log.d(LOG_TAG, "recipe received");

        editTextRecipe.setText(recipe.recipe.getName());
        adapter = new RecipeViewPagerAdapter(this, recipe, bitmap);
        viewPager2.setAdapter(adapter);
    }
}