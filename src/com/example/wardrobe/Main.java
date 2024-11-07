package com.example.wardrobe;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Map<String, Outfit> outfitCalendar = new HashMap<>();

    public static void main(String[] args) {
        WardrobeManager wardrobe = WardrobeManager.getInstance();

        System.out.println("Welcome to the Virtual Wardrobe & Styling Assistant!");

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add Clothing Items");
            System.out.println("2. Create Outfit");
            System.out.println("3. Suggest Outfits");
            System.out.println("4. Plan Outfit for a Date");
            System.out.println("5. View Planned Outfits");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> addClothingItems(wardrobe);
                case 2 -> createOutfit(wardrobe);
                case 3 -> suggestOutfits(wardrobe);
                case 4 -> planOutfitForDate(wardrobe);
                case 5 -> viewPlannedOutfits();
                case 6 -> {
                    System.out.println("Exiting. Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void addClothingItems(WardrobeManager wardrobe) {
        System.out.println("Add clothing items to your wardrobe. Type 'done' to finish.");
        while (true) {
            System.out.print("Enter item name (or 'done' to finish): ");
            String name = scanner.nextLine();
            if (name.equalsIgnoreCase("done")) break;

            System.out.print("Enter item category (e.g., top, bottom, jacket): ");
            String category = scanner.nextLine();

            System.out.print("Enter item color: ");
            String color = scanner.nextLine();

            ClothingItem item = new ClothingItem(name, category, color);
            wardrobe.addClothingItem(item);
            System.out.println("Added: " + item.getName() + " (" + item.getCategory() + ", " + item.getColor() + ")");
        }
    }


    private static void createOutfit(WardrobeManager wardrobe) {
        if (wardrobe.getClothingItems().isEmpty()) {
            System.out.println("No clothing items available to create an outfit. Please add items first.");
            return;
        }
        System.out.print("Enter a name for the new outfit: ");
        String outfitName = scanner.nextLine();
        Outfit outfit = new Outfit(outfitName);

        System.out.println("Select items for this outfit:");
        List<ClothingItem> items = wardrobe.getClothingItems();
        for (int i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + ". " + items.get(i).getName() + " (" + items.get(i).getCategory() + ")");
        }

        System.out.println("Enter item numbers separated by commas (e.g., 1,3,5), or 'done' to finish:");
        String input = scanner.nextLine();
        String[] indices = input.split(",");

        for (String index : indices) {
            int itemIndex = Integer.parseInt(index.trim()) - 1;
            outfit.addItem(items.get(itemIndex));
        }

        wardrobe.addOutfit(outfit);
        System.out.println("Outfit created: " + outfit.getDescription());
    }

    private static void suggestOutfits(WardrobeManager wardrobe) {
        if (wardrobe.getOutfits().isEmpty()) {
            System.out.println("No outfits available to suggest. Please create an outfit first.");
            return;
        }
        SuggestionContext suggestionContext = new SuggestionContext();

        System.out.println("Choose suggestion criteria: ");
        System.out.println("1. Weather-based");
        System.out.println("2. Event-based");
        System.out.print("Enter choice: ");
        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1:
                suggestionContext.setStrategy(new WeatherBasedSuggestion());
                break;
            case 2:
                System.out.print("Enter event type (e.g., formal, casual): ");
                String eventType = scanner.nextLine();
                suggestionContext.setStrategy(new EventBasedSuggestion(eventType));
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }

        List<ClothingItem> suggestions = suggestionContext.getSuggestions(wardrobe);
        System.out.println("Suggested items:");
        for (ClothingItem item : suggestions) {
            System.out.println("- " + item.getName() + " (" + item.getCategory() + ")");
        }
    }

    private static void planOutfitForDate(WardrobeManager wardrobe) {
        if (wardrobe.getOutfits().isEmpty()) {
            System.out.println("No outfits available to plan for a date. Please create an outfit first.");
            return;
        }
        System.out.print("Enter the date for planning (e.g., 2024-12-25): ");
        String date = scanner.nextLine();

        System.out.println("Available outfits:");
        List<Outfit> outfits = wardrobe.getOutfits();
        for (int i = 0; i < outfits.size(); i++) {
            System.out.println((i + 1) + ". " + outfits.get(i).getDescription());
        }

        System.out.print("Select an outfit number for " + date + ": ");
        int outfitIndex = Integer.parseInt(scanner.nextLine()) - 1;

        Outfit selectedOutfit = outfits.get(outfitIndex);
        outfitCalendar.put(date, selectedOutfit);
        System.out.println("Planned outfit for " + date + ": " + selectedOutfit.getDescription());
    }
    private static void viewPlannedOutfits() {
        if (outfitCalendar.isEmpty()) {
            System.out.println("No outfits have been planned yet.");
            return;
        }

        System.out.println("Planned Outfits:");
        for (Map.Entry<String, Outfit> entry : outfitCalendar.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue().getDescription());
        }
    }

}
