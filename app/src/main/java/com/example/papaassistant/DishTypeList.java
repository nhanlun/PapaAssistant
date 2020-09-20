package com.example.papaassistant;

import java.util.ArrayList;

import javax.xml.namespace.QName;

public class DishTypeList {
    private ArrayList<DishType> dishTypeArrayList;

    public DishTypeList() {
        dishTypeArrayList = new ArrayList<>();
    }

    public ArrayList<DishType> getDishTypeArrayList() {
        return dishTypeArrayList;
    }

    public void createList(){
        dishTypeArrayList.add(new DishType(R.drawable.appetizer, "Appetizer"));
    }
}
