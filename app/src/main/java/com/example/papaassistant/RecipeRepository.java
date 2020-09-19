package com.example.papaassistant;

import android.app.Application;

class RecipeRepository {
    RecipeRepository(Application application) {
        HistoryDatabase historyDatabase = HistoryDatabase.getDatabase(application);
        InstructionDAO instructionDAO = historyDatabase.instructionDAO();
    }
}
