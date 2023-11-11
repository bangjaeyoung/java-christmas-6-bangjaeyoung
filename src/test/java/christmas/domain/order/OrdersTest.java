package christmas.domain.order;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static christmas.domain.order.Order.NOT_VALID_MENU_ERROR_MESSAGE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class OrdersTest {
    
    @DisplayName("중복된 메뉴에 관한 테스트")
    @Nested
    class DuplicatedMenuTest {
        
        private final int menuCount = 2;
        
        @DisplayName("중복되지 않은 메뉴를 입력한 경우, 정상적으로 주문 리스트가 등록된다.")
        @Test
        void hasNotDuplicatedMenu() {
            // given
            Order firstOrder = new Order("양송이수프", menuCount);
            Order secondOrder = new Order("티본스테이크", menuCount);
            
            // when
            Orders orders = new Orders(List.of(firstOrder, secondOrder));
            
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
            Order firstOrder = new Order(menuName, menuCount);
            Order secondOrder = new Order(menuName, menuCount);
            
            // when // then
            assertThatThrownBy(() -> new Orders(List.of(firstOrder, secondOrder)))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(NOT_VALID_MENU_ERROR_MESSAGE);
        }
    }
    
    @DisplayName("주문된 메뉴의 최대 개수에 관한 테스트")
    @Nested
    class MaxMenuCountTest {
        
        private final String menuName = "양송이수프";
        
        @DisplayName("주문된 메뉴의 개수의 총합이 20개 이하인 경우, 정상적으로 주문이 등록된다.")
        @ParameterizedTest
        @ValueSource(ints = {1, 10, 20})
        void hasValidMenuCount(int menuCount) {
            // given
            Order order = new Order(menuName, menuCount);
            
            // when
            Orders orders = new Orders(List.of(order));
            
            // then
            assertThat(orders).isInstanceOf(Orders.class);
        }
        
        @DisplayName("주문된 메뉴의 개수의 총합이 20개를 초과할 경우, 예외가 발생한다.")
        @ParameterizedTest
        @ValueSource(ints = {30, 40, 50})
        void hasInvalidMenuCount(int menuCount) {
            // given
            Order order = new Order(menuName, menuCount);
            
            // when // then
            assertThatThrownBy(() -> new Orders(List.of(order)))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(NOT_VALID_MENU_ERROR_MESSAGE);
        }
    }
}
