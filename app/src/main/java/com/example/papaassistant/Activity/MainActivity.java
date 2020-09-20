package com.example.papaassistant.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.papaassistant.DishTypeList;
import com.example.papaassistant.R;
import com.example.papaassistant.Adapter.ViewPagerAdapter;
import com.example.papaassistant.Recipe;

import org.w3c.dom.Text;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    EditText editTextSearch;
    ViewPager2 viewPager2;
    ViewPagerAdapter viewPagerAdapter;
    int currentPage =0;
    int NUM_PAGES = 4;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Recipe recipe = new Recipe();
        recipe.recipe.setImageLink("https://spoonacular.com/recipeImages/654959-312x231.jpg");
        Intent intent = new Intent(MainActivity.this, RecipeActivity.class);
        intent.putExtra("recipe", recipe);
        startActivity(intent);

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
        }, 500, 3000);

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
                for (int i = 0; i < s.length(); ++i) if (s.charAt(i) == '\n') {
                    s.delete(i, i);
                    found = true;
                }
                if (found) {
                    // TODO: call activity search
                }
            }
        });
    }
}