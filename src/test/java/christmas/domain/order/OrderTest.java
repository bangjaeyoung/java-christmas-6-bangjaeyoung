package christmas.domain.order;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OrderTest {
    
    @DisplayName("메뉴 이름이 같은 메뉴 객체끼리는 같은 객체로 취급한다.")
    @Test
    void equalsOrderWithSameMenuName() {
        // given
        Order firstOrder = new Order("양송이수프");
        Order secondOrder = new Order("양송이수프");
        
        // then
        assertThat(firstOrder).isEqualTo(secondOrder);
    }
    
    @DisplayName("메뉴 이름이 다른 메뉴 객체끼리는 같은 객체로 취급한다.")
    @Test
    void notEqualsOrderWithSameMenuName() {
        // given
        Order firstOrder = new Order("양송이수프");
        Order secondOrder = new Order("티본스테이크");
        
        // then
        assertThat(firstOrder).isNotEqualTo(secondOrder);
    }
}
