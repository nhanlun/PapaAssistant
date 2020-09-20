package com.example.papaassistant;

import androidx.annotation.NonNull;
import androidx.room.Entity;

@Entity(tableName = "IngredientInRecipe", primaryKeys = {"id", "name"})
public class IngredientInRecipeSchema {
    private int id;
    @NonNull
    String name;
    private double amount;
    private String unit;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
