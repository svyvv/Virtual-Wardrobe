package com.example.wardrobe;

import java.util.ArrayList;
import java.util.List;

public class Outfit {
    private String name;
    private List<ClothingItem> items;

    public Outfit(String name) {
        this.name = name;
        this.items = new ArrayList<>();
    }

    public void addItem(ClothingItem item) {
        items.add(item);
    }

    public String getDescription() {
        StringBuilder description = new StringBuilder(name + " consists of: ");
        for (ClothingItem item : items) {
            description.append(item.getName()).append(", ");
        }
        if (!items.isEmpty()) {
            // Remove the trailing comma and space
            description.setLength(description.length() - 2);
        }
        return description.toString();
    }
}

