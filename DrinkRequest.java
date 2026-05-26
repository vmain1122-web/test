package com.example.demo; // ★これを1行目に追加！
import java.util.List;

public class DrinkRequest {
    private String castId;
    private List<String> ingredients;

    // Getters and Setters
    public String getCastId() { return castId; }
    public void setCastId(String castId) { this.castId = castId; }
    public List<String> getIngredients() { return ingredients; }
    public void setIngredients(List<String> ingredients) { this.ingredients = ingredients; }
}