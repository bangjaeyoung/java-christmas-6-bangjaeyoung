package christmas.domain.order;

import christmas.domain.menu.Beverage;
import christmas.domain.menu.Dessert;
import christmas.domain.menu.MainCourse;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static christmas.view.MessageType.NOT_VALID_MENU_ERROR;

public class Orders {
    private final static int MAX_MENU_COUNT = 20;
    
    private final List<Order> orders;
    
    public Orders(List<Order> orders) {
        checkDuplicatedMenu(orders);
        checkValidMenuCount(orders);
        checkOnlyBeverage(orders);
        this.orders = orders;
    }
    
    public int getDessertMenuCount() {
        return orders.stream()
                .filter(order -> Dessert.contains(order.getMenuName()))
                .mapToInt(Order::getMenuCount)
                .sum();
    }
    
    public int getMainCourseMenuCount() {
        return orders.stream()
                .filter(order -> MainCourse.contains(order.getMenuName()))
                .mapToInt(Order::getMenuCount)
                .sum();
    }
    
    private void checkDuplicatedMenu(List<Order> orders) {
        Set<Order> nonDuplicatedOrders = new HashSet<>(orders);
        if (nonDuplicatedOrders.size() != orders.size()) {
            throw new IllegalArgumentException(NOT_VALID_MENU_ERROR.getMessage());
        }
    }
    
    private void checkValidMenuCount(List<Order> orders) {
        int totalMenuCount = sumMenuCount(orders);
        if (totalMenuCount > MAX_MENU_COUNT) {
            throw new IllegalArgumentException(NOT_VALID_MENU_ERROR.getMessage());
        }
    }
    
    private static int sumMenuCount(List<Order> orders) {
        return orders.stream()
                .mapToInt(Order::getMenuCount)
                .sum();
    }
    
    private void checkOnlyBeverage(List<Order> orders) {
        List<Order> beverageOrders = getBeverageOrders(orders);
        if (orders.size() == beverageOrders.size()) {
            throw new IllegalArgumentException(NOT_VALID_MENU_ERROR.getMessage());
        }
    }
    
    private static List<Order> getBeverageOrders(List<Order> orders) {
        return orders.stream()
                .filter(order -> Beverage.contains(order.getMenuName()))
                .toList();
    }
    
    public List<Order> getOrders() {
        return orders;
    }
}
