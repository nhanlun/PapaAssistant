package com.example.papaassistant.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.papaassistant.R;
import com.example.papaassistant.RecipeAPIGETer;

import java.util.HashMap;

public class AdvanceSearchActivity extends AppCompatActivity {
    private TextView queryTextView;
    private TextView requiredIngredientTextView;
    private TextView maxReadyTimeTextView;
    private AutoCompleteTextView intoleranceTextView;
    private AutoCompleteTextView cuisineTextView;
    private AutoCompleteTextView dietTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advance_search);

        setupComponent();
    }

    private void setupComponent() {
        queryTextView = findViewById(R.id.queryTextField);
        ;
        requiredIngredientTextView = findViewById(R.id.requireIngredientTextView);
        maxReadyTimeTextView = findViewById(R.id.maxReadyTimeTextField);
        intoleranceTextView = findViewById(R.id.intoleranceTextField);
        cuisineTextView = findViewById(R.id.cuisineTextView);
        dietTextView = findViewById(R.id.dietTextView);

        setupAdapter();

        Button searchButton = findViewById(R.id.search_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, String> arguments = parseArguments();
                Intent intent = new Intent(AdvanceSearchActivity.this, SearchActivity.class);
                intent.putExtra("arguments", arguments);
                startActivity(intent);
            }
        });
    }

    private void setupAdapter() {
        String[] intoleranceOptions = getResources().getStringArray(R.array.intolerance_options);
        ArrayAdapter<String> intoleranceAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, intoleranceOptions);
        intoleranceTextView.setAdapter(intoleranceAdapter);


        String[] cuisineOptions = getResources().getStringArray(R.array.cuisine_option);
        ArrayAdapter<String> cuisineAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, cuisineOptions);
        cuisineTextView.setAdapter(cuisineAdapter);

        String[] dietOptions = getResources().getStringArray(R.array.diet_options);
        ArrayAdapter<String> dietAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, dietOptions);
        dietTextView.setAdapter(dietAdapter);
    }

    private HashMap<String, String> parseArguments() {
        HashMap<String, String> arguments = new HashMap<>();

        String query = queryTextView.getText().toString();
        if (!query.equals("")) {
            arguments.put(RecipeAPIGETer.QUERY, query);
        }
        String required = requiredIngredientTextView.getText().toString();
        if (!required.equals("")) {
            arguments.put(RecipeAPIGETer.INGREDIENT, required);
        }

        String intolerance = intoleranceTextView.getText().toString();
        if (!intolerance.equals("")) {
            arguments.put(RecipeAPIGETer.INTOLERANCE, intolerance);
        }

        String time = maxReadyTimeTextView.getText().toString();
        if (!time.equals("")) {
            arguments.put(RecipeAPIGETer.READYTIME, time);
        }

        String cuisine = cuisineTextView.getText().toString();
        if (!cuisine.equals("")) {
            arguments.put(RecipeAPIGETer.CUISINE, cuisine);
        }

        String diet = dietTextView.getText().toString();
        if (!diet.equals("")) {
            arguments.put(RecipeAPIGETer.DIET, diet);
        }
        return arguments;
    }
}