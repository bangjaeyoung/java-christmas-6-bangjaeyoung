package christmas.domain;

import java.util.Objects;

import static christmas.view.MessageType.NOT_VALID_DATE_ERROR;

public class VisitDate {
    private static final int MIN_DATE = 1, MAX_DATE = 31;
    
    private final int date;
    
    public VisitDate(int date) {
        validateDate(date);
        this.date = date;
    }
    
    private void validateDate(int date) {
        if (date < MIN_DATE || date > MAX_DATE) {
            throw new IllegalArgumentException(NOT_VALID_DATE_ERROR.getMessage());
        }
    }
    
    public int getDate() {
        return date;
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
