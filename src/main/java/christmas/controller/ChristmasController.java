package christmas.controller;

import christmas.domain.VisitDate;
import christmas.view.InputView;
import christmas.view.OutputView;

public class ChristmasController {
    
    private final InputView inputView;
    private final OutputView outputView;
    
    public ChristmasController() {
        inputView = new InputView();
        outputView = new OutputView();
    }
    
    public void start() {
        VisitDate visitDate = inputVisitDate();
    }
    
    private VisitDate inputVisitDate() {
        try {
            return new VisitDate(inputView.inputVisitDate());
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e);
            return inputVisitDate();
        }
    }
}
