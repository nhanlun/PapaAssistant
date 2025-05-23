package com.example.papaassistant.Schema;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.papaassistant.DateConverter;
import com.example.papaassistant.Recipe;

import java.io.Serializable;
import java.util.Date;

@Entity(tableName = "Recipe")
@TypeConverters({DateConverter.class})
public class RecipeSchema implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    public RecipeSchema(int id, String name, String imageLink, String ingredients, double healthScore, int numberOfPeople, int readyTime) {
        //TODO: What to do with the last access date
        this.id = id;
        this.name = name;
        this.imageLink = imageLink;
        this.ingredients = ingredients;
        this.healthScore = healthScore;
        this.numberOfPeople = numberOfPeople;
        this.readyTime = readyTime;
    }

    public RecipeSchema() {
    }

    private String name;
    private String imageLink;
    private String ingredients;

    private double healthScore;
    private int numberOfPeople;
    private int readyTime;

    private Date lastAccess;

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

    public Date getLastAccess() {
        return lastAccess;
    }

    public void setLastAccess(Date lastAccess) {
        this.lastAccess = lastAccess;
    }
}
