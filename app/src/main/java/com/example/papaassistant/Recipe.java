package com.example.papaassistant;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.ArrayList;

public class Recipe {
    @Embedded public RecipeSchema recipe;

    @Relation(
            parentColumn = "id",
            entityColumn = "recipeId",
            projection = {"name", "amount", "unit"},
            entity = IngredientInRecipeSchema.class
    )
    public ArrayList<IngredientInRecipe> ingredients;

    @Relation(
            parentColumn = "id",
            entityColumn = "recipeId",
            projection = {"step", "instruction"},
            entity = InstructionSchema.class
    )
    public ArrayList<Instruction> instructions;
}
