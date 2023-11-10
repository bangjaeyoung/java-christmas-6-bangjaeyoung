package christmas.domain.order;

import christmas.domain.menu.MenuService;

import java.util.Objects;

public class Order {
    private static final String NOT_VALID_MENU_ERROR_MESSAGE = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";
    
    private final String menuName;
    
    public Order(String menuName) {
        validateMenu(menuName);
        this.menuName = menuName;
    }
    
    private void validateMenu(String menuName) {
        if (!MenuService.containsMenu(menuName)) {
            throw new IllegalArgumentException(NOT_VALID_MENU_ERROR_MESSAGE);
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
        Order order = (Order) o;
        return Objects.equals(menuName, order.menuName);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(menuName);
    }
}
