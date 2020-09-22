package com.example.papaassistant.Schema;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "Recipe")
public class RecipeSchema implements Serializable {

    @PrimaryKey
    private int id;
    private String name;
    private String imageLink;
    private String ingredients;

    private double healthScore;
    private int numberOfPeople;
    private int readyTime;

    public RecipeSchema() {
    }

    public RecipeSchema(int id, String name, String imageLink, String ingredients, double healthScore, int numberOfPeople, int readyTime) {
        this.id = id;
        this.name = name;
        this.imageLink = imageLink;
        this.ingredients = ingredients;
        this.healthScore = healthScore;
        this.numberOfPeople = numberOfPeople;
        this.readyTime = readyTime;
    }

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

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public double getHealthScore() {
        return healthScore;
    }

    public void setHealthScore(double healthScore) {
        this.healthScore = healthScore;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public int getReadyTime() {
        return readyTime;
    }

    public void setReadyTime(int readyTime) {
        this.readyTime = readyTime;
    }
}
