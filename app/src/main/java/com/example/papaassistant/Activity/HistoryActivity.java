package com.example.papaassistant.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_history, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.delete_history)
                .setCancelable(true)
                .setTitle("Warning");
        builder.setPositiveButton("Sure", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                recipeRepository.deleteHistory(recipes);
            }
        });
        builder.setNegativeButton("No no no no no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) { }
        });
        builder.show();
        return super.onOptionsItemSelected(item);
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
                        Intent intent = new Intent(HistoryActivity.this, RecipeActivity.class);
                        intent.putExtra("recipe", recipe);
                        startActivity(intent);
                    }
                });
                recyclerView.setAdapter(adapter);
            }
        });
    }
}