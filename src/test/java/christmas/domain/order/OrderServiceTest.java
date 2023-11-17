package christmas.domain.order;

import christmas.domain.menu.Appetizer;
import christmas.domain.menu.Beverage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class OrderServiceTest {
    
    private OrderService orderService;
    
    @BeforeEach
    void setUp() {
        orderService = new OrderService();
    }
    
    @DisplayName("총 주문 금액을 구한다.")
    @Test
    void calculateTotalPriceOfOrders() {
        // given
        int menuCount = 2;
        
        Appetizer tapas = Appetizer.TAPAS;
        Beverage redWine = Beverage.RED_WINE;
        int expectedTotalPrice = (tapas.getPrice() * menuCount) + (redWine.getPrice() * menuCount);
        
        Orders orders = new Orders(
                List.of(
                        new Order(tapas.getName(), menuCount),
                        new Order(redWine.getName(), menuCount)
                )
        );
        
        // when
        int actualtotalPrice = orderService.calculateTotalPrice(orders);
        
        // then
        assertThat(actualtotalPrice).isEqualTo(expectedTotalPrice);
    }
}
