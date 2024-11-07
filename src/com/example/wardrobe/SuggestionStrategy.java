package com.example.wardrobe;

import java.util.List;

public interface SuggestionStrategy {
    List<ClothingItem> suggest(WardrobeManager wardrobe);
}
