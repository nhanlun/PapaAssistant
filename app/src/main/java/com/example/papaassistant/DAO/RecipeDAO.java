package com.example.papaassistant.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.papaassistant.Recipe;
import com.example.papaassistant.Schema.RecipeSchema;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface RecipeDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(RecipeSchema recipe);

    @Transaction
    @Query("SELECT * FROM Recipe ORDER BY lastAccess DESC")
    LiveData<List<Recipe>> getRecipe();

    @Transaction
    @Query("SELECT COUNT(*) FROM Recipe")
    LiveData<Integer> countRecipe();

    @Delete
    void delete(RecipeSchema recipeSchema);
}
