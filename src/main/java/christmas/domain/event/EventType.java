package christmas.domain.event;

import christmas.domain.menu.Beverage;

public enum EventType {
    CHRISTMAS_D_DAY_DISCOUNT(100),
    WEEKEND_DISCOUNT(2_023),
    WEEKDAY_DISCOUNT(2_023),
    SPECIAL_DISCOUNT(1_000),
    GIVEAWAY_EVENT(Beverage.CHAMPAGNE.getPrice());
    
    private final int discountPrice;
    
    EventType(int discountPrice) {
        this.discountPrice = discountPrice;
    }
    
    public int getDiscountPrice() {
        return discountPrice;
    }
}
