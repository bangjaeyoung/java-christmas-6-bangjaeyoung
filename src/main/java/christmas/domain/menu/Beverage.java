package christmas.domain.menu;

import java.util.Arrays;

public enum Beverage {
    ZERO_COKE("제로콜라", 3_000),
    RED_WINE("레드와인", 60_000),
    CHAMPAGNE("샴페인", 25_000);
    
    private final String name;
    private final int price;
    
    Beverage(String name, int price) {
        this.name = name;
        this.price = price;
    }
    
    public static boolean contains(String name) {
        return Arrays.stream(Beverage.values())
                .anyMatch(beverage -> beverage.name.equals(name));
    }
    
    public static int getPriceOfMenu(String name) {
        return Arrays.stream(Beverage.values())
                .filter(beverage -> beverage.name.equals(name))
                .findFirst()
                .map(Beverage::getPrice)
                .orElse(0);
    }
    
    public String getName() {
        return name;
    }
    
    public int getPrice() {
        return price;
    }
}
