package christmas.domain.event;

import christmas.domain.VisitDate;
import christmas.domain.order.Orders;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class EventService {
    private static final int STANDARD_OF_APPLYING_DISCOUNT = 10_000;
    private static final int START_DISCOUNT_PRICE = 1_000;
    private static final int ADDED_DISCOUNT_PRICE_PER_DAY = 100;
    private static final int EVENT_YEAR = 2023;
    private static final int EVENT_MONTH = 12;
    private static final int WEEK_DISCOUNT_PRICE = 2_023;
    
    public int applyEvent(VisitDate visitDate, Orders orders, int totalPrice) {
        if (totalPrice < STANDARD_OF_APPLYING_DISCOUNT) {
            return totalPrice;
        }
        
        int date = visitDate.getDate();
        totalPrice = applyChristmasDDayDiscount(date, totalPrice);
        totalPrice = applyWeekDiscount(date, orders, totalPrice);
        
        return 0;
    }
    
    public int applyChristmasDDayDiscount(int visitDate, int totalPrice) {
        if (visitDate >= 1 && visitDate <= 25) {
            int discountAmnount = START_DISCOUNT_PRICE + (visitDate - 1) * ADDED_DISCOUNT_PRICE_PER_DAY;
            totalPrice -= discountAmnount;
        }
        return totalPrice;
    }
    
    public int applyWeekDiscount(int date, Orders orders, int totalPrice) {
        String visitDayOfWeek = getDayOfWeekAboutDate(date);
        if (visitDayOfWeek.equals(DayOfWeek.FRIDAY.name()) || visitDayOfWeek.equals(DayOfWeek.SATURDAY.name())) {
            return applyWeekdayDiscount(orders, totalPrice);
        }
        return applyWeekendDiscount(orders, totalPrice);
    }
    
    private static String getDayOfWeekAboutDate(int date) {
        return LocalDate.of(EVENT_YEAR, EVENT_MONTH, date).getDayOfWeek().name();
    }
    
    private int applyWeekdayDiscount(Orders orders, int totalPrice) {
        return totalPrice - WEEK_DISCOUNT_PRICE * orders.getMainCourseMenuCount();
    }
    
    private int applyWeekendDiscount(Orders orders, int totalPrice) {
        return totalPrice - WEEK_DISCOUNT_PRICE * orders.getDessertMenuCount();
    }
}
