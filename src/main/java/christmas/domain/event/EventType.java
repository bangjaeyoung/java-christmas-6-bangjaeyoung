package christmas.domain.event;

import christmas.domain.menu.Beverage;

public enum EventType {
    CHRISTMAS_D_DAY_DISCOUNT("크리스마스 디데이 할인", 100),
    WEEKEND_DISCOUNT("주말 할인", 2_023),
    WEEKDAY_DISCOUNT("평일 할인", 2_023),
    SPECIAL_DISCOUNT("특별 할인", 1_000),
    GIVEAWAY_EVENT("증정 이벤트", Beverage.CHAMPAGNE.getPrice());
    
    private final String eventName;
    private final int discountPrice;
    
    EventType(String eventName, int discountPrice) {
        this.eventName = eventName;
        this.discountPrice = discountPrice;
    }
    
    public String getEventName() {
        return eventName;
    }
    
    public int getDiscountPrice() {
        return discountPrice;
    }
}
