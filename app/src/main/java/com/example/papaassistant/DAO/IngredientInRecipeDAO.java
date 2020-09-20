package com.example.papaassistant.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import com.example.papaassistant.Schema.IngredientInRecipeSchema;

@Dao
public interface IngredientInRecipeDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(IngredientInRecipeSchema ingredient);
}
