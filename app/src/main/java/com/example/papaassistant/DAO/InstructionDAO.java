package com.example.papaassistant.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import com.example.papaassistant.Schema.InstructionSchema;

@Dao
public interface InstructionDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(InstructionSchema instruction);
}
