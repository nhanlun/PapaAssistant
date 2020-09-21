package com.example.papaassistant.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.papaassistant.Recipe;
import com.example.papaassistant.Schema.RecipeSchema;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface RecipeDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(RecipeSchema recipe);

    @Query("SELECT * FROM Recipe ORDER BY lastAccess DESC")
    LiveData<List<Recipe>> getRecipeInHistory();

    @Query("SELECT COUNT(*) FROM Recipe")
    LiveData<Integer> countRecipeInHistory();
}
