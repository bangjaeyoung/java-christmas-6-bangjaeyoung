package christmas.domain.event;

import christmas.domain.VisitDate;
import christmas.domain.order.Orders;

public class EventService {
    private static final int STANDARD_OF_APPLYING_DISCOUNT = 10_000;
    private static final int START_DISCOUNT_AMOUNT = 1_000;
    private static final int ADDED_DISCOUNT_AMOUNT_PER_DAY = 100;
    
    public int applyEvent(VisitDate visitDate, Orders orders, int totalPrice) {
        if (totalPrice < STANDARD_OF_APPLYING_DISCOUNT) {
            return totalPrice;
        }
        
        int date = visitDate.getDate();
        totalPrice = applyChristmasDDayDiscount(date, totalPrice);
        
        return 0;
    }
    
    public int applyChristmasDDayDiscount(int visitDate, int totalPrice) {
        if (visitDate >= 1 && visitDate <= 25) {
            int discountAmnount = START_DISCOUNT_AMOUNT + (visitDate - 1) * ADDED_DISCOUNT_AMOUNT_PER_DAY;
            totalPrice -= discountAmnount;
        }
        return totalPrice;
    }
}
