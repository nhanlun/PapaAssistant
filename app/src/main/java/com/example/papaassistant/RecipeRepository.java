package com.example.papaassistant;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.papaassistant.DAO.InstructionDAO;
import com.example.papaassistant.DAO.RecipeDAO;
import com.example.papaassistant.Schema.InstructionSchema;

import java.util.ArrayList;
import java.util.List;

public class RecipeRepository {

    private InstructionDAO instructionDAOHistory;
    private RecipeDAO recipeDAO;

    public RecipeRepository(Application application) {
        HistoryDatabase database = HistoryDatabase.getDatabase(application);
        instructionDAOHistory = database.instructionDAO();
        recipeDAO = database.recipeDAO();
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
                recipeDAO.insert(recipe.recipe);
            }
        });
    }

    public LiveData<List<Recipe>> getRecipeInHistory() {
        return recipeDAO.getRecipeInHistory();
    }

    public LiveData<Integer> countRecipeInHistory() {
        return recipeDAO.countRecipeInHistory();
    }
}
