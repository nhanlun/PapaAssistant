package com.example.papaassistant;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {RecipeSchema.class, IngredientInRecipeSchema.class, InstructionSchema.class}, version = 1, exportSchema = false)
public abstract class HistoryDatabase extends RoomDatabase {

    public abstract IngredientInRecipeDAO ingredientInRecipeDAO();
    public abstract InstructionDAO instructionDAO();
    public abstract RecipeDAO recipeDAO();

    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    private static volatile HistoryDatabase INSTANCE;

    static HistoryDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (HistoryDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            HistoryDatabase.class, "HistoryDatabase")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
