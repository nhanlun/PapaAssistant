package com.example.papaassistant.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.papaassistant.Adapter.SearchListAdapter;
import com.example.papaassistant.Adapter.ViewPagerAdapter;
import com.example.papaassistant.R;
import com.example.papaassistant.Recipe;
import com.example.papaassistant.RecipeRepository;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    private static final String LOG_TAG = HistoryActivity.class.getSimpleName();

    private RecipeRepository recipeRepository;
    private SearchListAdapter adapter;
    private ArrayList<Recipe> recipes = new ArrayList<>();
    private RecyclerView recyclerView;

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
        LiveData<List<Recipe>> recipesLiveData = recipeRepository.getRecipeInHistory();

        recipesLiveData.observe(this, new Observer<List<Recipe>>() {
            @Override
            public void onChanged(List<Recipe> recipesTmp) {
                recipes = (ArrayList<Recipe>) recipesTmp;
                adapter = new SearchListAdapter(getBaseContext(), recipes);
                adapter.setOnItemClickListener(new SearchListAdapter.ClickListener() {
                    @Override
                    public void onItemClick(int position, View v) {
                        Recipe recipe = recipes.get(position);
                        ImageView view = v.findViewById(R.id.dishImageView);
                        Bitmap bitmap = ((BitmapDrawable) view.getDrawable()).getBitmap();
                        Intent intent = new Intent(HistoryActivity.this, RecipeActivity.class);
                        intent.putExtra("recipe", recipe);
                        intent.putExtra("image", bitmap);
                        startActivity(intent);
                    }
                });
                recyclerView.setAdapter(adapter);
            }
        });
    }
}