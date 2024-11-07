package com.example.wardrobe;

public class ClothingItem {
    private String name;
    private String category;
    private String color;

    public ClothingItem(String name, String category, String color) {
        this.name = name;
        this.category = category;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getColor() {
        return color;
    }
}

