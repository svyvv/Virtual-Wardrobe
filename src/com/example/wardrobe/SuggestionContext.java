package com.example.wardrobe;

import java.util.List;

public class SuggestionContext {
    private SuggestionStrategy strategy;

    public void setStrategy(SuggestionStrategy strategy) {
        this.strategy = strategy;
    }

    public List<ClothingItem> getSuggestions(WardrobeManager wardrobe) {
        return strategy.suggest(wardrobe);
    }
}
