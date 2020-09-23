package com.example.papaassistant;

import java.util.ArrayList;

public interface uiThreadCallback {
    void publishToUiThread(ArrayList<Recipe> recipeArrayList);
}
