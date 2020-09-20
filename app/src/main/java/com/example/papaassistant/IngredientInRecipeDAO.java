package com.example.papaassistant;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

@Dao
public interface IngredientInRecipeDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(IngredientInRecipeSchema ingredient);
}
