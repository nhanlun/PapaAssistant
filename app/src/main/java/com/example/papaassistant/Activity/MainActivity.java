package com.example.papaassistant.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.papaassistant.DishTypeList;
import com.example.papaassistant.R;
import com.example.papaassistant.Adapter.ViewPagerAdapter;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    EditText editTextSearch;
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
        viewPager2.setAdapter(viewPagerAdapter);

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