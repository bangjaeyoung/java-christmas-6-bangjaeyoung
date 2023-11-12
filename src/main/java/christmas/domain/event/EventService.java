package christmas.domain.event;

import christmas.domain.VisitDate;
import christmas.domain.order.Orders;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.EnumMap;
import java.util.Map;

public class EventService {
    private static final int STANDARD_OF_APPLYING_DISCOUNT = 10_000;
    private static final int START_DISCOUNT_PRICE = 1_000;
    private static final int EVENT_YEAR = 2023;
    private static final int EVENT_MONTH = 12;
    private static final int START_DAY_OF_CHRISTMAS_D_DAY_DISCOUNT = 1;
    private static final int END_DAY_OF_CHRISTMAS_D_DAY_DISCOUNT = 25;
    
    private final Map<EventType, Integer> discountPriceOfApplyingEvent = new EnumMap<>(EventType.class);
    
    public int applyEvent(VisitDate visitDate, Orders orders, int totalPrice) {
        if (totalPrice < STANDARD_OF_APPLYING_DISCOUNT) {
            return totalPrice;
        }
        
        int date = visitDate.getDate();
        totalPrice = applyChristmasDDayDiscount(date, totalPrice);
        totalPrice = applyWeekDiscount(date, orders, totalPrice);
        totalPrice = applyWeekDiscount(date, orders, totalPrice);
        totalPrice = applySpecialDiscount(date, totalPrice);
        
        return 0;
    }
    
    public int applyChristmasDDayDiscount(int visitDate, int totalPrice) {
        if (visitDate >= START_DAY_OF_CHRISTMAS_D_DAY_DISCOUNT &&
                visitDate <= END_DAY_OF_CHRISTMAS_D_DAY_DISCOUNT) {
            int discountPrice = calculateDiscountPrice(visitDate);
            discountPriceOfApplyingEvent.put(EventType.CHRISTMAS_D_DAY_DISCOUNT, discountPrice);
            totalPrice -= discountPrice;
        }
        return totalPrice;
    }
    
    public int applyWeekDiscount(int date, Orders orders, int totalPrice) {
        String visitDayOfWeek = getDayOfWeekAboutDate(date);
        if (visitDayOfWeek.equals(DayOfWeek.FRIDAY.name()) ||
                visitDayOfWeek.equals(DayOfWeek.SATURDAY.name())) {
            return applyWeekdayDiscount(orders, totalPrice);
        }
        return applyWeekendDiscount(orders, totalPrice);
    }
    
    public int applySpecialDiscount(int date, int totalPrice) {
        String visitDayOfWeek = getDayOfWeekAboutDate(date);
        if (date == 25 || visitDayOfWeek.equals(DayOfWeek.SUNDAY.name())) {
            int discountPrice = EventType.SPECIAL_DISCOUNT.getDiscountPrice();
            discountPriceOfApplyingEvent.put(EventType.SPECIAL_DISCOUNT, discountPrice);
            totalPrice -= discountPrice;
        }
        return totalPrice;
    }
    
    private int calculateDiscountPrice(int visitDate) {
        return START_DISCOUNT_PRICE + (visitDate - 1) * EventType.CHRISTMAS_D_DAY_DISCOUNT.getDiscountPrice();
    }
    
    private String getDayOfWeekAboutDate(int date) {
        return LocalDate.of(EVENT_YEAR, EVENT_MONTH, date).getDayOfWeek().name();
    }
    
    private int applyWeekdayDiscount(Orders orders, int totalPrice) {
        int discountPrice = EventType.WEEKDAY_DISCOUNT.getDiscountPrice() * orders.getMainCourseMenuCount();
        discountPriceOfApplyingEvent.put(EventType.WEEKDAY_DISCOUNT, discountPrice);
        return totalPrice - discountPrice;
    }
    
    private int applyWeekendDiscount(Orders orders, int totalPrice) {
        int discountPrice = EventType.WEEKEND_DISCOUNT.getDiscountPrice() * orders.getDessertMenuCount();
        discountPriceOfApplyingEvent.put(EventType.WEEKEND_DISCOUNT, discountPrice);
        return totalPrice - discountPrice;
    }
}
