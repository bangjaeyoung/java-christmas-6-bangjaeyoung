package christmas.domain.order;

import christmas.domain.menu.MenuService;

public class OrderService {
    
    public int calculateTotalPrice(Orders orders) {
        return orders.getOrders().stream()
                .mapToInt(order -> MenuService.getPriceOfMenu(order.getMenuName()) * order.getMenuCount())
                .sum();
    }
}
