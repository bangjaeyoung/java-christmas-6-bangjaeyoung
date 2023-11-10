package christmas.domain.menu;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class MainCourseTest {
    
    @DisplayName("메인에 속하는 메뉴일 경우, true를 반환한다.")
    @ParameterizedTest
    @ValueSource(strings = {"티본스테이크", "바비큐립", "해산물파스타", "크리스마스파스타"})
    void containsMainCourse(String menuName) {
        // when // then
        assertThat(MainCourse.contains(menuName)).isTrue();
    }
    
    @DisplayName("메인에 속하지 않는 메뉴일 경우, false를 반환한다.")
    @ParameterizedTest
    @ValueSource(strings = {"제로콜라", "레드와인", "샴페인"})
    void notContainsMainCourse(String menuName) {
        // when // then
        assertThat(MainCourse.contains(menuName)).isFalse();
    }
}
