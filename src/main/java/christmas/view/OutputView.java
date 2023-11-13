package christmas.view;

import christmas.domain.order.Orders;

import java.util.stream.Collectors;

public class OutputView {
    private static final String BENEFIT_INTRO_MESSAGE = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    private static final String ORDER_MENU_MESSAGE = "<주문 메뉴>";
    private static final String LINE_SEPARATOR = System.lineSeparator();
    
    public static void printErrorMessage(Exception e) {
        System.out.println(e.getMessage());
    }
    
    public static void printOrderResult(int visitDate, Orders orders) {
        String result = makeBenefitIntroMessage(visitDate) +
                LINE_SEPARATOR +
                makeOrderList(orders) +
                LINE_SEPARATOR;
        System.out.println(result);
    }
    
    private static String makeBenefitIntroMessage(int visitDate) {
        return String.format(BENEFIT_INTRO_MESSAGE, visitDate) + LINE_SEPARATOR;
    }
    
    private static String makeOrderList(Orders orders) {
        String orderItems = orders.getOrders().stream()
                .map(order -> String.format("%s %d개", order.getMenuName(), order.getMenuCount()))
                .collect(Collectors.joining(LINE_SEPARATOR));
        
        return String.join(LINE_SEPARATOR, ORDER_MENU_MESSAGE, orderItems);
    }
}
