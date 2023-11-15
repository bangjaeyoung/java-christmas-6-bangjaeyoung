package christmas.domain;

import christmas.view.MessageType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class VisitDateTest {
    
    @DisplayName("방문 날짜가 1 이상 31 이하의 숫자이면, 정상적으로 해당 숫자로 방문 날짜가 등록된다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 15, 31})
    void checkSaveVisitDateWell(int date) {
        // when // then
        assertThat(new VisitDate(date)).isInstanceOf(VisitDate.class);
    }
    
    @DisplayName("방문 날짜가 1 이상 31 이하의 숫자가 아니라면, 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 32})
    void validateInvalidVisitDate(int date) {
        // when // then
        assertThatThrownBy(() -> new VisitDate(date))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(MessageType.NOT_VALID_DATE_ERROR.getMessage());
    }
}
