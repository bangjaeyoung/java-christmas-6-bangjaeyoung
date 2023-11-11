package christmas.domain.order;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static christmas.domain.order.Order.NOT_VALID_MENU_ERROR_MESSAGE;

public class Orders {
    private final static int MAX_MENU_COUNT = 20;
    
    private final List<Order> orders;
    
    public Orders(List<Order> orders) {
        checkDuplicatedMenu(orders);
        checkValidMenuCount(orders);
        this.orders = orders;
    }
    
    private void checkDuplicatedMenu(List<Order> orders) {
        Set<Order> nonDuplicatedOrders = new HashSet<>(orders);
        if (nonDuplicatedOrders.size() != orders.size()) {
            throw new IllegalArgumentException(NOT_VALID_MENU_ERROR_MESSAGE);
        }
    }
    
    private void checkValidMenuCount(List<Order> orders) {
        int totalMenuCount = sumMenuCount(orders);
        if (totalMenuCount > MAX_MENU_COUNT) {
            throw new IllegalArgumentException(NOT_VALID_MENU_ERROR_MESSAGE);
        }
    }
    
    private static int sumMenuCount(List<Order> orders) {
        return orders.stream()
                .mapToInt(Order::getMenuCount)
                .sum();
    }
    
    //TODO 추후 orders 필드에 대한 Getter 메서드가 필요한지 고민
    public List<Order> getOrders() {
        return orders;
    }
}
