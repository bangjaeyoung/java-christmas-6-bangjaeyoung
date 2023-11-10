package christmas.domain.menu;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class DessertTest {
    
    @DisplayName("디저트에 속하는 메뉴일 경우, true를 반환한다.")
    @ParameterizedTest
    @ValueSource(strings = {"초코케이크", "아이스크림"})
    void containsDessert(String menuName) {
        // when // then
        assertThat(Dessert.contains(menuName)).isTrue();
    }
    
    @DisplayName("디저트에 속하지 않는 메뉴일 경우, false를 반환한다.")
    @ParameterizedTest
    @ValueSource(strings = {"제로콜라", "레드와인", "샴페인"})
    void notContainsDessert(String menuName) {
        // when // then
        assertThat(Dessert.contains(menuName)).isFalse();
    }
}
