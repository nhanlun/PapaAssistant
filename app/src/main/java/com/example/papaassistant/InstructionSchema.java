package com.example.papaassistant;

import androidx.room.Entity;

@Entity(tableName = "Instruction", primaryKeys = {"recipeId", "step"})
public class InstructionSchema {

    private int recipeId;
    private int step;
    private String instruction;

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }
}