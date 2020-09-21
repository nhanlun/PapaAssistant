package com.example.papaassistant;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.papaassistant.Schema.InstructionSchema;
import com.example.papaassistant.Schema.RecipeSchema;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Recipe implements Serializable {
    @Embedded public RecipeSchema recipe;

    @Relation(
            parentColumn = "id",
            entityColumn = "recipeId",
            projection = {"step", "instruction"},
            entity = InstructionSchema.class
    )
    public List<Instruction> instructions;

    public Recipe() {
        recipe = new RecipeSchema();
        instructions = new ArrayList<>();
    }
}
