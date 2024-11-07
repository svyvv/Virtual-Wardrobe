package com.example.wardrobe;

import java.util.List;
import java.util.stream.Collectors;

public class EventBasedSuggestion implements SuggestionStrategy {
    private String eventType;

    public EventBasedSuggestion(String eventType) {
        this.eventType = eventType;
    }

    @Override
    public List<ClothingItem> suggest(WardrobeManager wardrobe) {
        return wardrobe.getClothingItems().stream()
                .filter(item -> item.getCategory().equalsIgnoreCase(eventType))
                .collect(Collectors.toList());
    }
}

