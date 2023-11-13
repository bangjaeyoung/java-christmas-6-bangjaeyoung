package christmas.domain.badge;

public class BadgeService {
    
    public BadgeType assignBadgeByBenefitPrice(int benefitPrice) {
        if (benefitPrice >= BadgeType.SANTA.getStandardPrice()) {
            return BadgeType.SANTA;
        }
        
        if (benefitPrice >= BadgeType.TREE.getStandardPrice()) {
            return BadgeType.TREE;
        }
        
        if (benefitPrice >= BadgeType.STAR.getStandardPrice()) {
            return BadgeType.STAR;
        }
        
        return BadgeType.NONE;
    }
}
