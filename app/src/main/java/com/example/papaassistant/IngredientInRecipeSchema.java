package com.example.papaassistant;

import androidx.room.Entity;

@Entity(tableName = "IngredientInRecipe", primaryKeys = {"id", "name"})
public class IngredientInRecipeSchema {
    private int id;
    private String name;
    private double amount;
    private String unit;
}
