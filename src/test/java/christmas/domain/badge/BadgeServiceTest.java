package christmas.domain.badge;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BadgeServiceTest {
    
    private BadgeService badgeService;
    
    @BeforeEach
    void setUp() {
        badgeService = new BadgeService();
    }
    
    @DisplayName("배지 부여 이벤트에 관한 테스트")
    @Nested
    class BadgeEventTest {
        
        @DisplayName("별 배지의 혜택 금액 조건을 충족할 경우, 별 배지를 부여한다.")
        @Test
        void assignStarBadge() {
            // given
            int benefitPrice = BadgeType.STAR.getStandardPrice();
            
            // when // then
            assertThat(badgeService.assignBadgeByBenefitPrice(benefitPrice)).isEqualTo(BadgeType.STAR);
        }
        
        @DisplayName("트리 배지의 혜택 금액 조건을 충족할 경우, 트리 배지를 부여한다.")
        @Test
        void assignTreeBadge() {
            // given
            int benefitPrice = BadgeType.TREE.getStandardPrice();
            
            // when // then
            assertThat(badgeService.assignBadgeByBenefitPrice(benefitPrice)).isEqualTo(BadgeType.TREE);
        }
        
        @DisplayName("산타 배지의 혜택 금액 조건을 충족할 경우, 산타 배지를 부여한다.")
        @Test
        void assignSantaBadge() {
            // given
            int benefitPrice = BadgeType.SANTA.getStandardPrice();
            
            // when // then
            assertThat(badgeService.assignBadgeByBenefitPrice(benefitPrice)).isEqualTo(BadgeType.SANTA);
        }
    }
}
