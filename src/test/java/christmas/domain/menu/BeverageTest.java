package christmas.domain.menu;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class BeverageTest {
    
    @DisplayName("음료에 속하는 메뉴일 경우, true를 반환한다.")
    @ParameterizedTest
    @ValueSource(strings = {"제로콜라", "레드와인", "샴페인"})
    void containsBeverage(String menuName) {
        // when // then
        assertThat(Beverage.contains(menuName)).isTrue();
    }
    
    @DisplayName("음료에 속하지 않는 메뉴일 경우, false를 반환한다.")
    @ParameterizedTest
    @ValueSource(strings = {"양송이수프", "타파스", "시저샐러드"})
    void notContainsBeverage(String menuName) {
        // when // then
        assertThat(Beverage.contains(menuName)).isFalse();
    }
}
