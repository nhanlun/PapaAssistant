package com.example.papaassistant.Schema;

import androidx.room.Entity;

import com.example.papaassistant.Instruction;

@Entity(tableName = "Instruction", primaryKeys = {"recipeId", "step"})
public class InstructionSchema {

    private int recipeId;
    private int step;
    private String instruction;

    public InstructionSchema() {}

    public InstructionSchema(int recipeId, Instruction instruction) {
        this.recipeId = recipeId;
        this.step = instruction.getStep();
        this.instruction = instruction.getInstruction();
    }

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