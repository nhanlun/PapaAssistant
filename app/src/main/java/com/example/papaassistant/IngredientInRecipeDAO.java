package com.example.papaassistant;

import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

public interface IngredientInRecipeDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(IngredientInRecipeSchema ingredient);
}
