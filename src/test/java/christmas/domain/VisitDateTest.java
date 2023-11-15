package christmas.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static christmas.view.MessageType.NOT_VALID_DATE_ERROR;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class VisitDateTest {
    
    @DisplayName("날짜가 같은 방문일자 객체끼리는 같은 객체로 취급한다.")
    @Test
    void equalsVisitDateWithSameDate() {
        // given
        int sameDate = 10;
        VisitDate firstVisitDate = new VisitDate(sameDate);
        VisitDate secondVisitDate = new VisitDate(sameDate);
        
        // then
        assertThat(firstVisitDate).isEqualTo(secondVisitDate);
    }
    
    @DisplayName("날짜가 같은 방문일자 객체끼리는 다른 객체로 취급한다.")
    @Test
    void notEqualsVisitDateWithDifferentDate() {
        // given
        VisitDate firstVisitDate = new VisitDate(10);
        VisitDate secondVisitDate = new VisitDate(15);
        
        // then
        assertThat(firstVisitDate).isNotEqualTo(secondVisitDate);
    }
    
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
                .hasMessage(NOT_VALID_DATE_ERROR.getMessage());
    }
}
