package com.example.papaassistant;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.papaassistant.DAO.InstructionDAO;
import com.example.papaassistant.DAO.RecipeDAO;
import com.example.papaassistant.Schema.InstructionSchema;

import java.util.List;

public class RecipeRepository {

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

    public void insertRecipeToHistory(final Recipe recipe) {
        int recipeId = recipe.recipe.getId();
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
        HistoryDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                recipeDAOHistory.insert(recipe.recipe);
            }
        });
    }

    public LiveData<List<Recipe>> getRecipeInHistory() {
        return recipeDAOHistory.getRecipe();
    }

    public LiveData<Integer> countRecipeInHistory() {
        return recipeDAOHistory.countRecipe();
    }
}
