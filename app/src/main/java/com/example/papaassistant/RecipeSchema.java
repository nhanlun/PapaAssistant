package com.example.papaassistant;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "Recipe")
public class RecipeSchema {
    @PrimaryKey private int id;
    private String name;
}
