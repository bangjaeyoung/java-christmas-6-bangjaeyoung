package christmas.domain.event;

import christmas.domain.VisitDate;
import christmas.domain.order.Orders;

public class EventService {
    private static final int STANDARD_OF_APPLYING_DISCOUNT = 10_000;
    
    public int applyEvent(VisitDate visitDate, Orders orders, int totalPrice) {
        if (totalPrice < STANDARD_OF_APPLYING_DISCOUNT) {
            return totalPrice;
        }
        return 0;
    }
}
