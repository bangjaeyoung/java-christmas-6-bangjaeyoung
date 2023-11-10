package christmas.domain.menu;

public class MenuService {
    
    public static boolean containsMenu(String menuName) {
        return Appetizer.contains(menuName) ||
                Beverage.contains(menuName) ||
                Dessert.contains(menuName) ||
                MainCourse.contains(menuName);
    }
}
