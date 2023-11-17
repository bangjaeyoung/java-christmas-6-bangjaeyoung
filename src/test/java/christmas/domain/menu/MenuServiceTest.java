package christmas.domain.menu;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class MenuServiceTest {
    
    @DisplayName("메뉴판에 존재하는 메뉴일 경우, true를 반환한다.")
    @ParameterizedTest
    @ValueSource(strings = {"양송이수프", "제로콜라", "초코케이크", "티본스테이크"})
    void containsMenu(String menuName) {
        // when // then
        assertThat(MenuService.containsMenu(menuName)).isTrue();
    }
    
    @DisplayName("메뉴판에 존재하지 않는 메뉴일 경우, false를 반환한다.")
    @ParameterizedTest
    @ValueSource(strings = {"치킨", "피자", "탕수육"})
    void notContainsMenu(String menuName) {
        // when // then
        assertThat(MenuService.containsMenu(menuName)).isFalse();
    }
}
