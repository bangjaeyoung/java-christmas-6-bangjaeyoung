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
import static christmas.view.MessageType.NOTHING;
import static christmas.view.MessageType.ORDER_MENU;
import static christmas.view.MessageType.TOTAL_DISCOUNT_PRICE;
import static christmas.view.MessageType.TOTAL_PRICE;

public class OutputView {
    private static final int GIVEAWAY_MENU_COUNT = 1;
    private static final String GIVEAWAY_MENU_NAME = Beverage.CHAMPAGNE.getName();
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String COUNT_UNIT = "개", PRICE_UNIT = "원";
    
    public static void printErrorMessage(Exception e) {
        System.out.println(e.getMessage());
    }
    
    public static void printOrderResult(VisitDate visitDate, Orders orders, int totalPrice, EventService eventService,
                                        int finalTotalPrice, BadgeType assignedBadge) {
        int date = visitDate.getDate();
        String result = String.join(LINE_SEPARATOR,
                makeBenefitIntroMessage(date),
                makeOrderListMessage(orders),
                makeTotalPriceMessage(totalPrice),
                makeGiveawayMenuMessage(eventService),
                makeApplyingEventsMessage(eventService),
                makeTotalDiscountPriceMessage(eventService),
                makeFinalTotalPriceMessage(finalTotalPrice),
                makeAssignedBadge(assignedBadge)
        );
        System.out.println(result);
    }
    
    private static String makeBenefitIntroMessage(int visitDate) {
        return String.format(BENEFIT_INTRO.getMessage(), visitDate) + LINE_SEPARATOR;
    }
    
    private static String makeOrderListMessage(Orders orders) {
        String orderItems = orders.getOrders().stream()
                .map(order -> String.format("%s %d%s", order.getMenuName(), order.getMenuCount(), COUNT_UNIT))
                .collect(Collectors.joining(LINE_SEPARATOR));
        
        return makeFormattedResultMessage(ORDER_MENU.getMessage(), orderItems);
    }
    
    private static String makeTotalPriceMessage(int totalPrice) {
        String totalPriceMessage = String.format("%,d%s", totalPrice, PRICE_UNIT);
        return makeFormattedResultMessage(TOTAL_PRICE.getMessage(), totalPriceMessage);
    }
    
    private static String makeGiveawayMenuMessage(EventService eventService) {
        Map<EventType, Integer> discountPriceOfApplyingEvent = eventService.getDiscountPriceOfApplyingEvent();
        int discountPrice = discountPriceOfApplyingEvent.get(EventType.GIVEAWAY_EVENT);
        
        if (discountPrice == 0) {
            return makeFormattedResultMessage(GIVEAWAY_MENU.getMessage(), NOTHING.getMessage());
        }
        
        String giveawayMenuMessage = String.format("%s %d%s", GIVEAWAY_MENU_NAME, GIVEAWAY_MENU_COUNT, COUNT_UNIT);
        return makeFormattedResultMessage(GIVEAWAY_MENU.getMessage(), giveawayMenuMessage);
    }
    
    private static String makeApplyingEventsMessage(EventService eventService) {
        Map<EventType, Integer> applyingEvents = eventService.findApplyingEvents();
        String eventDetails = applyingEvents.entrySet().stream()
                .map(entry -> String.format("%s: -%,d%s", entry.getKey().getEventName(), entry.getValue(), PRICE_UNIT))
                .collect(Collectors.joining(LINE_SEPARATOR));
        
        if (applyingEvents.isEmpty()) {
            return makeFormattedResultMessage(BENEFIT_DETAILS.getMessage(), NOTHING.getMessage());
        }
        
        return makeFormattedResultMessage(BENEFIT_DETAILS.getMessage(), eventDetails);
    }
    
    private static String makeTotalDiscountPriceMessage(EventService eventService) {
        int totalDiscountPrice = eventService.calculateTotalDiscountPrice();
        
        if (totalDiscountPrice == 0) {
            String nothingMessage = String.format("%,d%s", 0, PRICE_UNIT);
            return makeFormattedResultMessage(TOTAL_DISCOUNT_PRICE.getMessage(), nothingMessage);
        }
        
        String totalDiscountPriceMessage = String.format("-%,d%s", totalDiscountPrice, PRICE_UNIT);
        return makeFormattedResultMessage(TOTAL_DISCOUNT_PRICE.getMessage(), totalDiscountPriceMessage);
    }
    
    private static String makeFinalTotalPriceMessage(int finalTotalPrice) {
        String finalTotalPriceMessage = String.format("%,d%s", finalTotalPrice, PRICE_UNIT);
        return makeFormattedResultMessage(FINAL_TOTAL_PRICE.getMessage(), finalTotalPriceMessage);
    }
    
    private static String makeAssignedBadge(BadgeType assignedBadge) {
        return String.join(LINE_SEPARATOR, ASSIGNED_BADGE.getMessage(), assignedBadge.getName());
    }
    
    private static String makeFormattedResultMessage(String introMessage, String resultMessage) {
        return String.join(LINE_SEPARATOR, introMessage, resultMessage) + LINE_SEPARATOR;
    }
}
