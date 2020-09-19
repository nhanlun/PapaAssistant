package com.example.papaassistant;

import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

public interface InstructionDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(InstructionSchema instruction);
}
