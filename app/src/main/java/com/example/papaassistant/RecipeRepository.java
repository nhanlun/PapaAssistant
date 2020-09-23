package com.example.papaassistant;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.papaassistant.DAO.InstructionDAO;
import com.example.papaassistant.DAO.RecipeDAO;
import com.example.papaassistant.Database.HistoryDatabase;
import com.example.papaassistant.Database.LibraryDatabase;
import com.example.papaassistant.Schema.InstructionSchema;
import com.example.papaassistant.Schema.RecipeSchema;

import java.util.ArrayList;
import java.util.List;

public class RecipeRepository {

    private static final String LOG_TAG = RecipeRepository.class.getSimpleName();

    private InstructionDAO instructionDAOHistory;
    private RecipeDAO recipeDAOHistory;
    private InstructionDAO instructionDAOLibrary;
    private RecipeDAO recipeDAOLibrary;

    public RecipeRepository(Application application) {
        HistoryDatabase historyDatabase = HistoryDatabase.getDatabase(application);
        instructionDAOHistory = historyDatabase.instructionDAO();
        recipeDAOHistory = historyDatabase.recipeDAO();

        LibraryDatabase libraryDatabase = LibraryDatabase.getDatabase(application);
        instructionDAOLibrary = libraryDatabase.instructionDAO();
        recipeDAOLibrary = libraryDatabase.recipeDAO();
    }

    public void insertRecipeToLibrary(final Recipe recipe) {
        final int recipeId = recipe.recipe.getId();
        LibraryDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                recipeDAOLibrary.insert(recipe.recipe);
            }
        });
        for (int i = 0; i < recipe.instructions.size(); ++i) {
            Instruction instruction = recipe.instructions.get(i);
            Log.d(LOG_TAG, instruction.getInstruction());
            final InstructionSchema instructionSchema = new InstructionSchema(recipeId, instruction);
            LibraryDatabase.databaseWriteExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    instructionDAOLibrary.insert(instructionSchema);
                }
            });
        }
    }

    public void insertRecipeToHistory(final Recipe recipe) {
        int recipeId = recipe.recipe.getId();
        HistoryDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                recipeDAOHistory.insert(recipe.recipe);
            }
        });
        for (int i = 0; i < recipe.instructions.size(); ++i) {
            Instruction instruction = recipe.instructions.get(i);
            final InstructionSchema instructionSchema = new InstructionSchema(recipeId, instruction);
            HistoryDatabase.databaseWriteExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    instructionDAOHistory.insert(instructionSchema);
                }
            });
        }
    }

    public LiveData<List<Recipe>> getRecipeInHistory() {
        return recipeDAOHistory.getRecipe();
    }

    public LiveData<Integer> countRecipeInHistory() {
        return recipeDAOHistory.countRecipe();
    }

    public void deleteHistory(ArrayList<Recipe> recipes) {
        for (int i = 0; i < recipes.size(); ++i) {
            final Recipe recipe = recipes.get(i);
            int id = recipe.recipe.getId();
            HistoryDatabase.databaseWriteExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    RecipeSchema recipeSchema = recipe.recipe;
                    recipeDAOHistory.delete(recipeSchema);
                }
            });
            for (int j = 0; j < recipe.instructions.size(); ++j) {
                final InstructionSchema instructionSchema = new InstructionSchema(id, recipe.instructions.get(j));
                HistoryDatabase.databaseWriteExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        instructionDAOHistory.delete(instructionSchema);
                    }
                });
            }
        }
    }

    public LiveData<List<Recipe>> getRecipeInLibrary() {
        return recipeDAOLibrary.getRecipe();
    }

    public void deleteLibrary(final Recipe recipe) {
        int id = recipe.recipe.getId();
        LibraryDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                RecipeSchema recipeSchema = recipe.recipe;
                recipeDAOLibrary.delete(recipeSchema);
            }
        });
        for (int i = 0; i < recipe.instructions.size(); ++i) {
            final InstructionSchema instructionSchema = new InstructionSchema(id, recipe.instructions.get(i));
            LibraryDatabase.databaseWriteExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    instructionDAOLibrary.delete(instructionSchema);
                }
            });
        }
    }
}
