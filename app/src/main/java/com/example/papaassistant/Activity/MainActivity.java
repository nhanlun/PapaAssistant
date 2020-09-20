package com.example.papaassistant.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;

import com.example.papaassistant.DishTypeList;
import com.example.papaassistant.R;
import com.example.papaassistant.Adapter.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity {

    ViewPager2 viewPager2;
    ViewPagerAdapter viewPagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager2 = findViewById(R.id.mainViewPager);
        DishTypeList dishTypeList = new DishTypeList();
        dishTypeList.createList();
        viewPagerAdapter = new ViewPagerAdapter(this, dishTypeList.getDishTypeArrayList());
        if(dishTypeList==null){
            Log.d("null", "null");
        }
        viewPager2.setAdapter(viewPagerAdapter);
    }
}