package com.example.wardrobe;

import java.util.List;
import java.util.stream.Collectors;

public class WeatherBasedSuggestion implements SuggestionStrategy {

    @Override
    public List<ClothingItem> suggest(WardrobeManager wardrobe) {
        return wardrobe.getClothingItems().stream()
                .filter(item -> item.getCategory().equalsIgnoreCase("jacket") ||
                        item.getCategory().equalsIgnoreCase("sweater") ||
                        item.getCategory().equalsIgnoreCase("coat") ||
                        item.getCategory().equalsIgnoreCase("hoodie"))
                .collect(Collectors.toList());
    }
}

