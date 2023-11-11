package christmas.view;

import camp.nextstep.edu.missionutils.Console;

import java.util.Arrays;
import java.util.List;

import static christmas.domain.order.Order.NOT_VALID_MENU_ERROR_MESSAGE;

public class InputView {
    private static final String START_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String ASK_VISIT_DATE_MESSAGE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private static final String ASK_MENU_NAME_AND_COUNT_MESSAGE = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";
    private static final String NOT_VALID_DATE_ERROR_MESSAGE = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    public static final String DELIMETER_OF_MENU_NAME_AND_COUNT = "-";
    
    public int inputVisitDate() {
        printStartMessage();
        System.out.println(ASK_VISIT_DATE_MESSAGE);
        return convertToInt(getUserInput());
    }
    
    public List<String> inputOrders() {
        System.out.println(ASK_MENU_NAME_AND_COUNT_MESSAGE);
        String userInput = getUserInput();
        checkContainsDelimeter(userInput);
        return convertToStringList(userInput);
    }
    
    private void printStartMessage() {
        System.out.println(START_MESSAGE);
    }
    
    private int convertToInt(String userInput) {
        try {
            return Integer.parseInt(userInput);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NOT_VALID_DATE_ERROR_MESSAGE);
        }
    }
    
    private String getUserInput() {
        return Console.readLine();
    }
    
    private void checkContainsDelimeter(String userInput) {
        if (!userInput.contains(DELIMETER_OF_MENU_NAME_AND_COUNT)) {
            throw new IllegalArgumentException(NOT_VALID_MENU_ERROR_MESSAGE);
        }
    }
    
    private List<String> convertToStringList(String userInput) {
        return Arrays.asList(userInput.split(","));
    }
}
