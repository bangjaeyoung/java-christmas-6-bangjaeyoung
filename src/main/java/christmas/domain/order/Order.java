package christmas.domain.order;

import christmas.domain.menu.MenuService;

import java.util.Objects;

public class Order {
    public static final String NOT_VALID_MENU_ERROR_MESSAGE = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";
    
    private final String menuName;
    private final int menuCount;
    
    public Order(String menuName, int menuCount) {
        validateMenuName(menuName);
        validateMenuCount(menuCount);
        this.menuName = menuName;
        this.menuCount = menuCount;
    }
    
    private void validateMenuName(String menuName) {
        if (!MenuService.containsMenu(menuName)) {
            throw new IllegalArgumentException(NOT_VALID_MENU_ERROR_MESSAGE);
        }
    }
    
    private void validateMenuCount(int menuCount) {
        if (menuCount < 1) {
            throw new IllegalArgumentException(NOT_VALID_MENU_ERROR_MESSAGE);
        }
    }
    
    public String getMenuName() {
        return menuName;
    }
    
    public int getMenuCount() {
        return menuCount;
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
