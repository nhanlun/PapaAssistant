package com.example.papaassistant.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.papaassistant.DAO.InstructionDAO;
import com.example.papaassistant.DAO.RecipeDAO;
import com.example.papaassistant.Schema.InstructionSchema;
import com.example.papaassistant.Schema.RecipeSchema;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {RecipeSchema.class, InstructionSchema.class}, version = 1, exportSchema = false)
public abstract class LibraryDatabase extends RoomDatabase {

    public abstract RecipeDAO recipeDAO();
    public abstract InstructionDAO instructionDAO();

    private static final int NUMBER_OF_THREAD = 10;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREAD);

    private static volatile LibraryDatabase INSTANCE;

    static LibraryDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            synchronized (LibraryDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            LibraryDatabase.class, "LibraryDatabase")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
