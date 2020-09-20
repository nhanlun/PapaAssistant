package com.example.papaassistant;

import android.app.Application;

import com.example.papaassistant.DAO.InstructionDAO;

class RecipeRepository {
    RecipeRepository(Application application) {
        HistoryDatabase historyDatabase = HistoryDatabase.getDatabase(application);
        InstructionDAO instructionDAO = historyDatabase.instructionDAO();
    }
}
