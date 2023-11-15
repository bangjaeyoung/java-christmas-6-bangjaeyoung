package christmas.controller;

import christmas.domain.VisitDate;
import christmas.domain.badge.BadgeService;
import christmas.domain.badge.BadgeType;
import christmas.domain.event.EventService;
import christmas.domain.order.Order;
import christmas.domain.order.OrderService;
import christmas.domain.order.Orders;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.List;

import static christmas.view.InputView.DELIMETER_OF_MENU_NAME_AND_COUNT;
import static christmas.view.MessageType.NOT_VALID_MENU_ERROR;

public class ChristmasController {
    
    private final OrderService orderService;
    private final EventService eventService;
    private final BadgeService badgeService;
    
    public ChristmasController() {
        orderService = new OrderService();
        eventService = new EventService();
        badgeService = new BadgeService();
    }
    
    public void start() {
        VisitDate visitDate = inputVisitDate();
        Orders orders = inputOrders();
        int totalPrice = orderService.calculateTotalPrice(orders);
        int finalTotalPrice = eventService.applyEvent(visitDate, orders, totalPrice);
        BadgeType assignedBadge = badgeService.assignBadgeByBenefitPrice(eventService.calculateTotalDiscountPrice());
        OutputView.printOrderResult(visitDate, orders, totalPrice, eventService, finalTotalPrice, assignedBadge);
    }
    
    private VisitDate inputVisitDate() {
        try {
            return new VisitDate(InputView.inputVisitDate());
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            return inputVisitDate();
        }
    }
    
    private Orders inputOrders() {
        try {
            return new Orders(convertToOrders());
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            return inputOrders();
        }
    }
    
    private List<Order> convertToOrders() {
        List<String> inputOrders = InputView.inputOrders();
        return inputOrders.stream()
                .map(inputOrder -> inputOrder.split(DELIMETER_OF_MENU_NAME_AND_COUNT))
                .peek(this::validateMenuType)
                .map(menu -> new Order(menu[0], convertToInt(menu[1])))
                .toList();
    }
    
    private void validateMenuType(String[] inputMenu) {
        if (inputMenu.length != 2) {
            throw new IllegalArgumentException(NOT_VALID_MENU_ERROR.getMessage());
        }
    }
    
    private int convertToInt(String userInput) {
        try {
            return Integer.parseInt(userInput);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NOT_VALID_MENU_ERROR.getMessage());
        }
    }
}
