package christmas.view;

import christmas.domain.VisitDate;
import christmas.domain.badge.BadgeType;
import christmas.domain.event.EventService;
import christmas.domain.event.EventType;
import christmas.domain.menu.Beverage;
import christmas.domain.order.Orders;

import java.util.Map;
import java.util.stream.Collectors;

import static christmas.view.MessageType.ASSIGNED_BADGE;
import static christmas.view.MessageType.BENEFIT_DETAILS;
import static christmas.view.MessageType.BENEFIT_INTRO;
import static christmas.view.MessageType.FINAL_TOTAL_PRICE;
import static christmas.view.MessageType.GIVEAWAY_MENU;
import static christmas.view.MessageType.ORDER_MENU;
import static christmas.view.MessageType.TOATL_DISCOUNT_PRICE;
import static christmas.view.MessageType.TOTAL_PRICE;

public class OutputView {
    private static final String GIVEAWAY_MENU_NAME = Beverage.CHAMPAGNE.getName();
    private static final int GIVEAWAY_MENU_COUNT = 1;
    private static final String NOTHING_MESSAGE = "없음";
    private static final String LINE_SEPARATOR = System.lineSeparator();
    
    public static void printErrorMessage(Exception e) {
        System.out.println(e.getMessage());
    }
    
    public static void printOrderResult(VisitDate visitDate, Orders orders, int totalPrice, EventService eventService,
                                        int finalTotalPrice, BadgeType assignedBadge) {
        int date = visitDate.getDate();
        String result = makeBenefitIntroMessage(date) +
                LINE_SEPARATOR +
                makeOrderListMessage(orders) +
                LINE_SEPARATOR +
                makeTotalPriceMessage(totalPrice) +
                LINE_SEPARATOR +
                makeGiveawayMenuMessage(eventService) +
                LINE_SEPARATOR +
                makeApplyingEventsMessage(eventService) +
                LINE_SEPARATOR +
                makeTotalDiscountPriceMessage(eventService) +
                LINE_SEPARATOR +
                makeFinalTotalPriceMessage(finalTotalPrice) +
                LINE_SEPARATOR +
                makeAssignedBadge(assignedBadge);
        System.out.println(result);
    }
    
    private static String makeBenefitIntroMessage(int visitDate) {
        return String.format(BENEFIT_INTRO.getMessage(), visitDate) + LINE_SEPARATOR;
    }
    
    private static String makeOrderListMessage(Orders orders) {
        String orderItems = orders.getOrders().stream()
                .map(order -> String.format("%s %d개", order.getMenuName(), order.getMenuCount()))
                .collect(Collectors.joining(LINE_SEPARATOR));
        return ORDER_MENU.getMessage() +
                LINE_SEPARATOR +
                orderItems +
                LINE_SEPARATOR;
    }
    
    private static String makeTotalPriceMessage(int totalPrice) {
        return TOTAL_PRICE.getMessage() +
                LINE_SEPARATOR +
                String.format("%,d원", totalPrice) +
                LINE_SEPARATOR;
    }
    
    private static String makeGiveawayMenuMessage(EventService eventService) {
        Map<EventType, Integer> discountPriceOfApplyingEvent = eventService.getDiscountPriceOfApplyingEvent();
        int discountPrice = discountPriceOfApplyingEvent.get(EventType.GIVEAWAY_EVENT);
        
        if (discountPrice == 0) {
            return makeNothingMessage(GIVEAWAY_MENU.getMessage());
        }
        
        return GIVEAWAY_MENU.getMessage() +
                LINE_SEPARATOR +
                String.format("%s %d개", GIVEAWAY_MENU_NAME, GIVEAWAY_MENU_COUNT) +
                LINE_SEPARATOR;
    }
    
    private static String makeApplyingEventsMessage(EventService eventService) {
        Map<EventType, Integer> applyingEvents = eventService.findApplyingEvents();
        String eventDetails = applyingEvents.entrySet().stream()
                .map(entry -> String.format("%s: -%,d원", entry.getKey().getEventName(), entry.getValue()))
                .collect(Collectors.joining(LINE_SEPARATOR));
        
        if (applyingEvents.isEmpty()) {
            return makeNothingMessage(BENEFIT_DETAILS.getMessage());
        }
        
        return BENEFIT_DETAILS.getMessage() +
                LINE_SEPARATOR +
                eventDetails +
                LINE_SEPARATOR;
    }
    
    private static String makeTotalDiscountPriceMessage(EventService eventService) {
        if (eventService.calculateTotalDiscountPrice() == 0) {
            return makeNothingPriceMessage(TOATL_DISCOUNT_PRICE.getMessage());
        }
        
        return TOATL_DISCOUNT_PRICE.getMessage() +
                LINE_SEPARATOR +
                String.format("-%,d원", eventService.calculateTotalDiscountPrice()) +
                LINE_SEPARATOR;
    }
    
    private static String makeFinalTotalPriceMessage(int finalTotalPrice) {
        return FINAL_TOTAL_PRICE.getMessage() +
                LINE_SEPARATOR +
                String.format("%,d원", finalTotalPrice) +
                LINE_SEPARATOR;
    }
    
    private static String makeAssignedBadge(BadgeType assignedBadge) {
        return ASSIGNED_BADGE.getMessage() +
                LINE_SEPARATOR +
                assignedBadge.getName();
    }
    
    private static String makeNothingMessage(String introMessage) {
        return introMessage +
                LINE_SEPARATOR +
                NOTHING_MESSAGE +
                LINE_SEPARATOR;
    }
    
    private static String makeNothingPriceMessage(String introMessage) {
        return introMessage +
                LINE_SEPARATOR +
                String.format("%,d원", 0) +
                LINE_SEPARATOR;
    }
}
