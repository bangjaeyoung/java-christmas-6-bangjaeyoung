package christmas.controller;

import christmas.view.MessageType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static christmas.view.MessageType.NOT_VALID_MENU_ERROR;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ChristmasControllerTest {
    
    @DisplayName("입력된 문자 형태가 메뉴의 형태(메뉴이름:메뉴개수)를 띄지 않는 경우, 예외가 발생한다.")
    @Test
    void shouldInputMenuWithRightMenuType() {
        // given
        ChristmasController christmasController = new ChristmasController();
        String[] nonValidInputMenu = new String[]{"티본스테이크", "레드와인", "3"};
        
        // when // then
        assertThatThrownBy(() -> christmasController.validateMenuType(nonValidInputMenu))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(NOT_VALID_MENU_ERROR.getMessage());
    }
}
