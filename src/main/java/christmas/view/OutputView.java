package christmas.view;

import christmas.domain.event.EventService;
import christmas.domain.event.EventType;
import christmas.domain.menu.Beverage;
import christmas.domain.order.Orders;

import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    private static final String BENEFIT_INTRO_MESSAGE = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    private static final String ORDER_MENU_MESSAGE = "<주문 메뉴>";
    private static final String TOTAL_PRICE_MESSAGE = "<할인 전 총주문 금액>";
    private static final String GIVEAWAY_MENU_MESSAGE = "<증정 메뉴>";
    private static final String GIVEAWAY_MENU_NAME = Beverage.CHAMPAGNE.getName();
    private static final int GIVEAWAY_MENU_COUNT = 1;
    private static final String NOTHING_MESSAGE = "없음";
    private static final String LINE_SEPARATOR = System.lineSeparator();
    
    public static void printErrorMessage(Exception e) {
        System.out.println(e.getMessage());
    }
    
    public static void printOrderResult(int visitDate, Orders orders, int totalPrice, EventService eventService) {
        String result = makeBenefitIntroMessage(visitDate) +
                LINE_SEPARATOR +
                makeOrderListMessage(orders) +
                LINE_SEPARATOR +
                makeTotalPriceMessage(totalPrice) +
                LINE_SEPARATOR +
                makeGiveawayMenuMessage(eventService) +
                LINE_SEPARATOR;
        System.out.println(result);
    }
    
    private static String makeBenefitIntroMessage(int visitDate) {
        return String.format(BENEFIT_INTRO_MESSAGE, visitDate) + LINE_SEPARATOR;
    }
    
    private static String makeOrderListMessage(Orders orders) {
        String orderItems = orders.getOrders().stream()
                .map(order -> String.format("%s %d개", order.getMenuName(), order.getMenuCount()))
                .collect(Collectors.joining(LINE_SEPARATOR));
        return ORDER_MENU_MESSAGE +
                LINE_SEPARATOR +
                orderItems +
                LINE_SEPARATOR;
    }
    
    private static String makeTotalPriceMessage(int totalPrice) {
        String formattedPrice = convertToCurrencyFormat(totalPrice);
        return TOTAL_PRICE_MESSAGE +
                LINE_SEPARATOR +
                formattedPrice +
                LINE_SEPARATOR;
    }
    
    private static String convertToCurrencyFormat(int price) {
        return String.format("%,d원", price);
    }
    
    private static String makeGiveawayMenuMessage(EventService eventService) {
        Map<EventType, Integer> discountPriceOfApplyingEvent = eventService.getDiscountPriceOfApplyingEvent();
        int discountPrice = discountPriceOfApplyingEvent.get(EventType.GIVEAWAY_EVENT);
        if (discountPrice == 0) {
            return GIVEAWAY_MENU_MESSAGE +
                    LINE_SEPARATOR +
                    NOTHING_MESSAGE +
                    LINE_SEPARATOR;
        }
        
        return GIVEAWAY_MENU_MESSAGE +
                LINE_SEPARATOR +
                String.format("%s %d개", GIVEAWAY_MENU_NAME, GIVEAWAY_MENU_COUNT) +
                LINE_SEPARATOR;
    }
}
