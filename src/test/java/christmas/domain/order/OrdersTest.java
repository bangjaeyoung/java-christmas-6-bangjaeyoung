package christmas.domain.order;

import christmas.domain.menu.Beverage;
import christmas.domain.menu.Dessert;
import christmas.domain.menu.MainCourse;
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
    
    @DisplayName("음료만 주문될 경우, 예외가 발생한다.")
    @Test
    void testOnlyBeverageOrder() {
        // given
        int menuCount = 2;
        Order firstOrder = new Order(Beverage.ZERO_COKE.getName(), menuCount);
        Order secondOrder = new Order(Beverage.RED_WINE.getName(), menuCount);
        Order thirdOrder = new Order(Beverage.CHAMPAGNE.getName(), menuCount);
        
        // when // then
        assertThatThrownBy(() -> new Orders(List.of(firstOrder, secondOrder, thirdOrder)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(NOT_VALID_MENU_ERROR_MESSAGE);
    }
    
    @DisplayName("주문된 메뉴들 중 디저트 메뉴의 총 개수를 가져온다.")
    @Test
    void getDessertMenuCount() {
        // given
        int menuCount = 2;
        Order firstOrder = new Order(Dessert.CHOCOLATE_CAKE.getName(), menuCount);
        Order secondOrder = new Order(Dessert.ICE_CREAM.getName(), menuCount);
        Orders orders = new Orders(List.of(firstOrder, secondOrder));
        
        int expectedDessertMenuCount = 4;
        
        // when
        int actualDessertMenuCount = orders.getDessertMenuCount();
        
        // then
        assertThat(actualDessertMenuCount).isEqualTo(expectedDessertMenuCount);
    }
    
    @DisplayName("주문된 메뉴들 중 메인 메뉴의 총 개수를 가져온다.")
    @Test
    void getMainCourseMenuCount() {
        // given
        int menuCount = 2;
        Order firstOrder = new Order(MainCourse.T_BONE_STEAK.getName(), menuCount);
        Order secondOrder = new Order(MainCourse.SEAFOOD_PASTA.getName(), menuCount);
        Orders orders = new Orders(List.of(firstOrder, secondOrder));
        
        int expectedMainCourseMenuCount = 4;
        
        // when
        int actualMainCourseMenuCount = orders.getMainCourseMenuCount();
        
        // then
        assertThat(actualMainCourseMenuCount).isEqualTo(expectedMainCourseMenuCount);
    }
}
