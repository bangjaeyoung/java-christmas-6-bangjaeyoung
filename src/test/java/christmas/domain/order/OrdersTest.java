package christmas.domain.order;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class OrdersTest {
    private static final String NOT_VALID_MENU_ERROR_MESSAGE = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";
    
    @DisplayName("중복되지 않은 메뉴를 입력한 경우, 정상적으로 주문 리스트가 등록된다.")
    @Test
    void hasNotDuplicatedMenu() {
        // given
        Order firstOrder = new Order("양송이수프");
        Order secondOrder = new Order("티본스테이크");
        List<Order> orderList = List.of(firstOrder, secondOrder);
        
        // when
        Orders orders = new Orders(orderList);
        
        // then
        assertThat(orders.getOrders()).hasSize(2)
                .extracting("menuName")
                .containsExactlyInAnyOrder("양송이수프", "티본스테이크");
    }
    
    @DisplayName("중복된 메뉴를 입력한 경우, 예외가 발생한다.")
    @Test
    void hasDuplicatedMenu() {
        // given
        String menuName = "양송이수프";
        List<Order> orderList = List.of(new Order(menuName), new Order(menuName));
        
        // when // then
        assertThatThrownBy(() -> new Orders(orderList))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(NOT_VALID_MENU_ERROR_MESSAGE);
    }
}
