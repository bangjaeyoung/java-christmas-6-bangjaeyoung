package christmas.domain.order;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Orders {
    private static final String NOT_VALID_MENU_ERROR_MESSAGE = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";
    
    private final List<Order> orders;
    
    public Orders(List<Order> orders) {
        checkDuplicatedMenu(orders);
        this.orders = orders;
    }
    
    private void checkDuplicatedMenu(List<Order> orders) {
        Set<Order> nonDuplicatedOrders = new HashSet<>(orders);
        if (nonDuplicatedOrders.size() != orders.size()) {
            throw new IllegalArgumentException(NOT_VALID_MENU_ERROR_MESSAGE);
        }
    }
    
    //TODO 추후 orders 필드에 대한 Getter 메서드가 필요한지 고민
    public List<Order> getOrders() {
        return orders;
    }
}
