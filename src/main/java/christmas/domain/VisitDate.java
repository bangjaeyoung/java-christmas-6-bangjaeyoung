package christmas.domain;

import java.util.Objects;

public class VisitDate {
    public static final String NOT_VALID_DATE_ERROR_MESSAGE = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    private static final int MIN_DATE = 1, MAX_DATE = 31;
    
    private final int date;
    
    public VisitDate(int date) {
        validateDate(date);
        this.date = date;
    }
    
    private void validateDate(int date) {
        if (date < MIN_DATE || date > MAX_DATE) {
            throw new IllegalArgumentException(NOT_VALID_DATE_ERROR_MESSAGE);
        }
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        VisitDate visitDate = (VisitDate) o;
        return date == visitDate.date;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(date);
    }
}
