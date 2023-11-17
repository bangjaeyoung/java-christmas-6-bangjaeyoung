package christmas.domain.menu;

public class MenuService {
    
    public static boolean containsMenu(String menuName) {
        return Appetizer.contains(menuName) ||
                Beverage.contains(menuName) ||
                Dessert.contains(menuName) ||
                MainCourse.contains(menuName);
    }
    
    public static int getPriceOfMenu(String menuName) {
        if (Appetizer.contains(menuName)) {
            return Appetizer.getPriceOfMenu(menuName);
        }
        if (Beverage.contains(menuName)) {
            return Beverage.getPriceOfMenu(menuName);
        }
        if (Dessert.contains(menuName)) {
            return Dessert.getPriceOfMenu(menuName);
        }
        if (MainCourse.contains(menuName)) {
            return MainCourse.getPriceOfMenu(menuName);
        }
        return 0;
    }
}
