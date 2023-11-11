package christmas.domain.menu;

import java.util.Arrays;

public enum MainCourse {
    T_BONE_STEAK("티본스테이크", 55_000),
    BBQ_RIBS("바비큐립", 54_000),
    SEAFOOD_PASTA("해산물파스타", 35_000),
    CHRISTMAS_PASTA("크리스마스파스타", 25_000);
    
    private final String name;
    private final int price;
    
    MainCourse(String name, int price) {
        this.name = name;
        this.price = price;
    }
    
    public static boolean contains(String name) {
        return Arrays.stream(MainCourse.values())
                .anyMatch(mainCourse -> mainCourse.name.equals(name));
    }
    
    public static int getPriceOfMenu(String name) {
        return Arrays.stream(MainCourse.values())
                .filter(mainCourse -> mainCourse.name.equals(name))
                .findFirst()
                .map(MainCourse::getPrice)
                .orElse(0);
    }
    
    public String getName() {
        return name;
    }
    
    public int getPrice() {
        return price;
    }
}
