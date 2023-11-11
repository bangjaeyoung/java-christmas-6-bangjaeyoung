package christmas.view;

import christmas.domain.order.Orders;

import java.util.stream.Collectors;

public class OutputView {
    private static final String BENEFIT_INTRO_MESSAGE = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    private static final String ORDER_MENU_MESSAGE = "<주문 메뉴>";
    
    public void printErrorMessage(Exception e) {
        System.out.println(e.getMessage());
    }
    
    public void printBenefitIntroMessage(int visitDate) {
        String introMessage = String.format(BENEFIT_INTRO_MESSAGE, visitDate);
        System.out.println(introMessage);
        printNewLine();
    }
    
    public void printNewLine() {
        System.out.println();
    }
    
    public void printOrders(Orders orders) {
        System.out.println(ORDER_MENU_MESSAGE);
        String ordersString = orders.getOrders().stream()
                .map(order -> String.format("%s %d개", order.getMenuName(), order.getMenuCount()))
                .collect(Collectors.joining(System.lineSeparator()));
        System.out.println(ordersString);
    }
}
