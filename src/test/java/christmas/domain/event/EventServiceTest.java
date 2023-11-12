package christmas.domain.event;

import christmas.domain.VisitDate;
import christmas.domain.menu.Appetizer;
import christmas.domain.menu.Beverage;
import christmas.domain.menu.Dessert;
import christmas.domain.menu.MainCourse;
import christmas.domain.order.Order;
import christmas.domain.order.OrderService;
import christmas.domain.order.Orders;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class EventServiceTest {
    private static final int START_DISCOUNT_PRICE = 1_000;
    
    private EventService eventService;
    private OrderService orderService;
    
    @BeforeEach
    void setUp() {
        eventService = new EventService();
        orderService = new OrderService();
    }
    
    @DisplayName("총 주문 금액이 10,000원 이하일 경우, 어떤 이벤트(할인)도 적용되지 않는다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 5_000, 9_999})
    void notApplyEventUnderTenThousand(int totalPrice) {
        // given
        VisitDate visitDate = new VisitDate(25);
        Orders orders = new Orders(
                List.of(
                        new Order(Appetizer.TAPAS.getName(), 3),
                        new Order(Beverage.RED_WINE.getName(), 3),
                        new Order(Dessert.CHOCOLATE_CAKE.getName(), 3),
                        new Order(MainCourse.BBQ_RIBS.getName(), 3)
                )
        );
        
        // when
        int finalTotalPrice = eventService.applyEvent(visitDate, orders, totalPrice);
        
        // then
        assertThat(finalTotalPrice).isEqualTo(totalPrice);
    }
    
    @DisplayName("크리스마스 디데이 할인을 적용한다.")
    @ParameterizedTest
    @ValueSource(ints = {10_000, 20_000, 30_000})
    void applyChristmasDDayDiscount(int totalPrice) {
        // given
        VisitDate visitDate = new VisitDate(25);
        int date = visitDate.getDate();
        
        int discountPrice = START_DISCOUNT_PRICE + (date - 1) * EventType.CHRISTMAS_D_DAY_DISCOUNT.getDiscountPrice();
        int expectedFinalTotalPrice = totalPrice - discountPrice;
        
        // when
        int actualFinalTotalPrice = eventService.applyChristmasDDayDiscount(date, totalPrice);
        
        // then
        assertThat(actualFinalTotalPrice).isEqualTo(expectedFinalTotalPrice);
    }
    
    @DisplayName("주중(평일, 주말) 할인에 관한 테스트")
    @Nested
    class WeekDiscountTest {
        
        int menuCount = 3;
        
        @DisplayName("평일 할인을 적용한다.")
        @ParameterizedTest
        @ValueSource(ints = {3, 4, 5, 6, 7, 10, 11, 12, 13, 14, 17, 18, 19, 20, 21, 24, 25, 26, 27, 28, 31})
        void applyWeekdayDiscount(int date) {
            // given
            Orders orders = new Orders(
                    List.of(
                            new Order(Dessert.CHOCOLATE_CAKE.getName(), menuCount),
                            new Order(Dessert.ICE_CREAM.getName(), menuCount)
                    )
            );
            
            int totalPrice = orderService.calculateTotalPrice(orders);
            int discountPrice = (menuCount * 2) * EventType.WEEKDAY_DISCOUNT.getDiscountPrice();
            int expectedFinalTotalPrice = totalPrice - discountPrice;
            
            // when
            int actualFinalTotalPrice = eventService.applyWeekDiscount(date, orders, totalPrice);
            
            // then
            assertThat(actualFinalTotalPrice).isEqualTo(expectedFinalTotalPrice);
        }
        
        @DisplayName("주말 할인을 적용한다.")
        @ParameterizedTest
        @ValueSource(ints = {1, 2, 8, 9, 15, 16, 22, 23, 29, 30})
        void applyWeekendDiscount(int date) {
            // given
            Orders orders = new Orders(
                    List.of(
                            new Order(MainCourse.T_BONE_STEAK.getName(), menuCount),
                            new Order(MainCourse.SEAFOOD_PASTA.getName(), menuCount)
                    )
            );
            
            int totalPrice = orderService.calculateTotalPrice(orders);
            int discountPrice = (menuCount * 2) * EventType.WEEKEND_DISCOUNT.getDiscountPrice();
            int expectedFinalTotalPrice = totalPrice - discountPrice;
            
            // when
            int actualFinalTotalPrice = eventService.applyWeekDiscount(date, orders, totalPrice);
            
            // then
            assertThat(actualFinalTotalPrice).isEqualTo(expectedFinalTotalPrice);
        }
    }
}
