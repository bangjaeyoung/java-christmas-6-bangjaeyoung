package christmas.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private static final String START_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String ASK_VISIT_DATE_MESSAGE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private static final String NOT_VALID_DATE_ERROR_MESSAGE = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    
    public int inputVisitDate() {
        printStartMessage();
        System.out.println(ASK_VISIT_DATE_MESSAGE);
        return convertToInt(getUserInput());
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
}
