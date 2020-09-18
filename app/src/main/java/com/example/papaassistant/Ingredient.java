package com.example.papaassistant;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "IngredientRecipe")
public class Ingredient {
    @PrimaryKey
    private String name;
    private double amount;
    private String unit;

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
