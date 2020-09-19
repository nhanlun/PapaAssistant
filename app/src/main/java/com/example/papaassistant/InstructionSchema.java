package com.example.papaassistant;

import androidx.room.Entity;

@Entity(tableName = "Instruction", primaryKeys = {"recipeId", "step"})
public class InstructionSchema {
    private int recipeId;
    private int step;
    private String instruction;
}