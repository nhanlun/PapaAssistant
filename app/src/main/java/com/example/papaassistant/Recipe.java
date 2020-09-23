package com.example.papaassistant;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.papaassistant.Schema.InstructionSchema;
import com.example.papaassistant.Schema.RecipeSchema;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Recipe implements Serializable {
    @Embedded
    public RecipeSchema recipe;

    @Relation(
            parentColumn = "id",
            entityColumn = "recipeId",
            projection = {"step", "instruction"},
            entity = InstructionSchema.class
    )
    public List<Instruction> instructions;

    public Recipe(int id, String name, String imageLink, String ingredients
            , double healthScore, int numberOfPeople, int readyTime,
                  ArrayList<Instruction> instructions) {
        recipe = new RecipeSchema(id, name, imageLink, ingredients, healthScore, numberOfPeople, readyTime);
        this.instructions = instructions;
    }

    public Recipe() {
        recipe = new RecipeSchema();
        instructions = new ArrayList<>();
    }
}
