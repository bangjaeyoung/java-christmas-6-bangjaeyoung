package christmas.view;

import christmas.domain.event.EventService;

public enum MessageType {
    // Input Message
    START(String.format("안녕하세요! 우테코 식당 %d월 이벤트 플래너입니다.", EventService.EVENT_MONTH)),
    ASK_VISIT_DATE(String.format("%d월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)", EventService.EVENT_MONTH)),
    ASK_MENU_NAME_AND_COUNT("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)"),
    
    // Output Message
    BENEFIT_INTRO(EventService.EVENT_MONTH + "월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!"),
    ORDER_MENU("<주문 메뉴>"),
    TOTAL_PRICE("<할인 전 총주문 금액>"),
    GIVEAWAY_MENU("<증정 메뉴>"),
    BENEFIT_DETAILS("<혜택 내역>"),
    TOATL_DISCOUNT_PRICE("<총혜택 금액>"),
    FINAL_TOTAL_PRICE("<할인 후 예상 결제 금액>"),
    ASSIGNED_BADGE(String.format("<%d월 이벤트 배지>", EventService.EVENT_MONTH)),
    NOTHING("없음"),
    
    // Error Message
    NOT_VALID_DATE_ERROR("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    NOT_VALID_MENU_ERROR("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    
    private final String message;
    
    MessageType(String message) {
        this.message = message;
    }
    
    public String getMessage() {
        return message;
    }
}
