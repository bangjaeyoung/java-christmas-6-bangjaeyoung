package christmas.domain.order;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static christmas.view.MessageType.NOT_VALID_MENU_ERROR;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class OrderTest {
    
    @DisplayName("메뉴 이름에 관한 테스트")
    @Nested
    class MenuNameTest {
        
        private final int menuCount = 2;
        
        @DisplayName("메뉴에 포함되지 않는 메뉴 이름으로 주문이 생성될 경우, 예외가 발생한다.")
        @Test
        void shouldContainsMenu() {
            // given
            String nonContainsMenuName = "김밥";
            
            // when // then
            assertThatThrownBy(() -> new Order(nonContainsMenuName, menuCount))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(NOT_VALID_MENU_ERROR.getMessage());
        }
        
        @DisplayName("메뉴 이름이 같은 메뉴 객체끼리는 같은 객체로 취급한다.")
        @Test
        void equalsOrderWithSameMenuName() {
            // given
            Order firstOrder = new Order("양송이수프", menuCount);
            Order secondOrder = new Order("양송이수프", menuCount);
            
            // then
            assertThat(firstOrder).isEqualTo(secondOrder);
        }
        
        @DisplayName("메뉴 이름이 다른 메뉴 객체끼리는 다른 객체로 취급한다.")
        @Test
        void notEqualsOrderWithDifferentMenuName() {
            // given
            Order firstOrder = new Order("양송이수프", menuCount);
            Order secondOrder = new Order("티본스테이크", menuCount);
            
            // then
            assertThat(firstOrder).isNotEqualTo(secondOrder);
        }
    }
    
    @DisplayName("메뉴 개수에 관한 테스트")
    @Nested
    class MenuCountTest {
        
        private final String menuName = "양송이수프";
        
        @DisplayName("주문되는 메뉴의 개수가 1개 이상이면 Order 객체가 정상적으로 생성된다.")
        @ParameterizedTest
        @ValueSource(ints = {1, 5, 10})
        void withValidMenuCount(int menuCount) {
            // when // then
            assertThat(new Order(menuName, menuCount)).isInstanceOf(Order.class);
        }
        
        @DisplayName("주문되는 메뉴의 개수가 1개 미만이면 예외가 발생한다.")
        @ParameterizedTest
        @ValueSource(ints = {-10, -1, 0})
        void withInvalidMenuCount(int menuCount) {
            // when // then
            assertThatThrownBy(() -> new Order(menuName, menuCount))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(NOT_VALID_MENU_ERROR.getMessage());
        }
    }
}
