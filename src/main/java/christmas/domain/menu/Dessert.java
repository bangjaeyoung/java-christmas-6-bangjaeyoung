package christmas.domain.menu;

import java.util.Arrays;

public enum Dessert {
    CHOCOLATE_CAKE("초코케이크", 15_000),
    ICE_CREAM("아이스크림", 5_000);
    
    private final String name;
    private final int price;
    
    Dessert(String name, int price) {
        this.name = name;
        this.price = price;
    }
    
    public static boolean contains(String name) {
        return Arrays.stream(Dessert.values())
                .anyMatch(dessert -> dessert.name.equals(name));
    }
    
    public static int getPriceOfMenu(String name) {
        return Arrays.stream(Dessert.values())
                .filter(dessert -> dessert.name.equals(name))
                .findFirst()
                .map(Dessert::getPrice)
                .orElse(0);
    }
    
    public String getName() {
        return name;
    }
    
    public int getPrice() {
        return price;
    }
}
