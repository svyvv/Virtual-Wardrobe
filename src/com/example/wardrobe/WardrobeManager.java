package com.example.wardrobe;

import java.util.ArrayList;
import java.util.List;

public class WardrobeManager { private static WardrobeManager instance;
    private List<ClothingItem> clothingItems;
    private List<Outfit> outfits;

    private WardrobeManager() {
        clothingItems = new ArrayList<>();
        outfits = new ArrayList<>();
    }

    public static synchronized WardrobeManager getInstance() {
        if (instance == null) {
            instance = new WardrobeManager();
        }
        return instance;
    }

    public void addClothingItem(ClothingItem item) {
        clothingItems.add(item);
    }

    public List<ClothingItem> getClothingItems() {
        return clothingItems;
    }

    public void addOutfit(Outfit outfit) {
        outfits.add(outfit);
    }

    public List<Outfit> getOutfits() {
        return outfits;
    }
}
