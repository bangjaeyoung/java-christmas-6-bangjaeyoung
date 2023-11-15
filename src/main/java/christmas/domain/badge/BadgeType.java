package christmas.domain.badge;

public enum BadgeType {
    STAR("별", 5_000),
    TREE("트리", 10_000),
    SANTA("산타", 20_000),
    NONE("없음", 0);
    
    private final String name;
    private final int standardPrice;
    
    BadgeType(String name, int standardPrice) {
        this.name = name;
        this.standardPrice = standardPrice;
    }
    
    public String getName() {
        return name;
    }
    
    public int getStandardPrice() {
        return standardPrice;
    }
}
