package christmas.domain.menu;

import java.util.Arrays;

public enum Appetizer {
    MUSHROOM_SOUP("양송이수프", 6_000),
    TAPAS("타파스", 5_500),
    CAESAR_SALAD("시저샐러드", 8_000);
    
    private final String name;
    private final int price;
    
    Appetizer(String name, int price) {
        this.name = name;
        this.price = price;
    }
    
    public static boolean contains(String name) {
        return Arrays.stream(Appetizer.values())
                .anyMatch(appetizer -> appetizer.name.equals(name));
    }
    
    public static int getPriceOfMenu(String name) {
        return Arrays.stream(Appetizer.values())
                .filter(appetizer -> appetizer.name.equals(name))
                .findFirst()
                .map(Appetizer::getPrice)
                .orElse(0);
    }
    
    public String getName() {
        return name;
    }
    
    public int getPrice() {
        return price;
    }
}
