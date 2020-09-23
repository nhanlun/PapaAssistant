package com.example.papaassistant.Schema;

import androidx.room.Entity;

import com.example.papaassistant.Instruction;

@Entity(tableName = "Instruction", primaryKeys = {"recipeId", "step"})
public class InstructionSchema extends Instruction {

    private int recipeId;

    InstructionSchema() {}

    public InstructionSchema(int recipeId, Instruction instruction) {
        this.recipeId = recipeId;
        setStep(instruction.getStep());
        setInstruction(instruction.getInstruction());
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }
}