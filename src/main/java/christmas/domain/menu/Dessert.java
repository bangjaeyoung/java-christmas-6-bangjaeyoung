package christmas.domain.menu;

public enum Dessert {
    chocolateCake("초코케이크", 15_000),
    iceCream("아이스크림", 5_000);
    
    private final String name;
    private final int price;
    
    Dessert(String name, int price) {
        this.name = name;
        this.price = price;
    }
}
