package christmas.controller;

import christmas.domain.VisitDate;
import christmas.domain.order.Order;
import christmas.domain.order.Orders;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.List;

import static christmas.domain.order.Order.NOT_VALID_MENU_ERROR_MESSAGE;
import static christmas.view.InputView.DELIMETER_OF_MENU_NAME_AND_COUNT;

public class ChristmasController {
    
    private final InputView inputView;
    private final OutputView outputView;
    
    public ChristmasController() {
        inputView = new InputView();
        outputView = new OutputView();
    }
    
    public void start() {
        VisitDate visitDate = inputVisitDate();
        Orders orders = inputOrders();
    }
    
    private VisitDate inputVisitDate() {
        try {
            return new VisitDate(inputView.inputVisitDate());
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e);
            return inputVisitDate();
        }
    }
    
    private Orders inputOrders() {
        try {
            return new Orders(convertToOrders());
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e);
            return inputOrders();
        }
    }
    
    private List<Order> convertToOrders() {
        List<String> inputOrders = inputView.inputOrders();
        return inputOrders.stream()
                .map(inputOrder -> inputOrder.split(DELIMETER_OF_MENU_NAME_AND_COUNT))
                .peek(this::validateMenuType)
                .map(menu -> new Order(menu[0], convertToInt(menu[1])))
                .toList();
    }
    
    private void validateMenuType(String[] inputMenu) {
        if (inputMenu.length != 2) {
            throw new IllegalArgumentException(NOT_VALID_MENU_ERROR_MESSAGE);
        }
    }
    
    private int convertToInt(String userInput) {
        try {
            return Integer.parseInt(userInput);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NOT_VALID_MENU_ERROR_MESSAGE);
        }
    }
}
