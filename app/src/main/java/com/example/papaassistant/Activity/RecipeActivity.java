package com.example.papaassistant.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.papaassistant.R;
import com.example.papaassistant.Recipe;

public class RecipeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        Intent intent = getIntent();
        Recipe recipe = (Recipe) intent.getSerializableExtra("recipe");
    }
}