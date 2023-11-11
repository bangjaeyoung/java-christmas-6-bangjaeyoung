package christmas.domain.event;

import christmas.domain.VisitDate;
import christmas.domain.menu.Appetizer;
import christmas.domain.order.Order;
import christmas.domain.order.Orders;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class EventServiceTest {
    
    private EventService eventService;
    
    @BeforeEach
    void setUp() {
        eventService = new EventService();
    }
    
    @DisplayName("총 주문 금액이 10,000원 이하일 경우, 어떤 이벤트(할인)도 적용되지 않는다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 5_000, 9_999})
    void notApplyEventUnderTenThousand(int totalPrice) {
        // given
        VisitDate visitDate = createVisitDate();
        Orders orders = createOrders();
        
        // when
        int finalTotalPrice = eventService.applyEvent(visitDate, orders, totalPrice);
        
        // then
        assertThat(finalTotalPrice).isEqualTo(totalPrice);
    }
    
    private static VisitDate createVisitDate() {
        return new VisitDate(25);
    }
    
    private static Orders createOrders() {
        return new Orders(
                List.of(
                        new Order(Appetizer.TAPAS.getName(), 3),
                        new Order(Appetizer.MUSHROOM_SOUP.getName(), 3)
                )
        );
    }
}
