package com.example.papaassistant.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager2.widget.ViewPager2;

import com.example.papaassistant.Adapter.ViewPagerAdapter;
import com.example.papaassistant.DishTypeList;
import com.example.papaassistant.R;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    EditText editTextSearch;
    ViewPager2 viewPager2;
    ViewPagerAdapter viewPagerAdapter;
    int currentPage = 0;
    int NUM_PAGES = 4;


    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDrawerLayout();


        // TODO: remove this

//        Recipe recipe = new Recipe();
//        recipe.recipe.setImageLink("https://spoonacular.com/recipeImages/654959-312x231.jpg");
//        recipe.recipe.setName("Indomie with omelettttttttttttttttttttttttt");
//        recipe.recipe.setIngredients("Indomie\nEgg");
//        Instruction tmp = new Instruction();
//        tmp.setStep(1);
//        tmp.setInstruction("just cook it :v");
//        recipe.instructions.add(tmp);
//        recipe.instructions.add(tmp);
//        Intent intent = new Intent(MainActivity.this, RecipeActivity.class);
//        intent.putExtra("recipe", recipe);
//        startActivity(intent);
//
//        intent = new Intent(MainActivity.this, HistoryActivity.class);
//        startActivity(intent);

        viewPager2 = findViewById(R.id.mainViewPager);
        DishTypeList dishTypeList = new DishTypeList();
        dishTypeList.createList();
        viewPagerAdapter = new ViewPagerAdapter(this, dishTypeList.getDishTypeArrayList());
        viewPager2.setAdapter(viewPagerAdapter);
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                //currentPage = viewPager2.getCurrentItem();
                viewPager2.setCurrentItem(currentPage++, true);
            }
        };

        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {

            @Override
            public void run() {
                handler.post(Update);
            }
        }, 2000, 5000);

        editTextSearch = findViewById(R.id.editTextSearch);
        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                boolean found = false;
                for (int i = 0; i < s.length(); ++i)
                    if (s.charAt(i) == '\n') {
                        s.delete(i, i);
                        found = true;
                    }
                if (found) {
                    // TODO: call activity search
                    String query = s.toString();
                    HashMap<String, String> arguments = new HashMap<>();
                    arguments.put("query", query);

                    Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                    intent.putExtra("arguments", arguments);
                    startActivity(intent);
                    s.clear();
                }
            }
        });
    }

    private void initDrawerLayout() {
        drawerLayout = findViewById(R.id.mainDrawerLayout);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(drawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}