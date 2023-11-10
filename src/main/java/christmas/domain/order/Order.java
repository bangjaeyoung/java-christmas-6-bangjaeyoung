package christmas.domain.order;

import christmas.domain.menu.MenuService;

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
}
