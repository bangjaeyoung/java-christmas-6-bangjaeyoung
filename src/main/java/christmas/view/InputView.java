package christmas.view;

import camp.nextstep.edu.missionutils.Console;

import java.util.Arrays;
import java.util.List;

import static christmas.view.MessageType.ASK_MENU_NAME_AND_COUNT;
import static christmas.view.MessageType.ASK_VISIT_DATE;
import static christmas.view.MessageType.NOT_VALID_DATE_ERROR;
import static christmas.view.MessageType.NOT_VALID_MENU_ERROR;
import static christmas.view.MessageType.START;

public class InputView {
    public static final String DELIMETER_OF_MENU_NAME_AND_COUNT = "-";
    
    public static int inputVisitDate() {
        printStartMessage();
        System.out.println(ASK_VISIT_DATE.getMessage());
        
        return convertToInt(getUserInput());
    }
    
    public static List<String> inputOrders() {
        System.out.println(ASK_MENU_NAME_AND_COUNT.getMessage());
        String userInput = getUserInput();
        checkContainsDelimeter(userInput);
        
        return convertToStringList(userInput);
    }
    
    private static void printStartMessage() {
        System.out.println(START.getMessage());
    }
    
    private static int convertToInt(String userInput) {
        try {
            return Integer.parseInt(userInput);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NOT_VALID_DATE_ERROR.getMessage());
        }
    }
    
    private static String getUserInput() {
        return Console.readLine();
    }
    
    private static void checkContainsDelimeter(String userInput) {
        if (!userInput.contains(DELIMETER_OF_MENU_NAME_AND_COUNT)) {
            throw new IllegalArgumentException(NOT_VALID_MENU_ERROR.getMessage());
        }
    }
    
    private static List<String> convertToStringList(String userInput) {
        return Arrays.asList(userInput.split(","));
    }
}
