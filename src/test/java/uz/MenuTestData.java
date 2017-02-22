package uz;

import uz.matcher.ModelMatcher;
import uz.model.Menu;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static uz.RestaurantTestData.ELKI;
import static uz.RestaurantTestData.VERSAL;
import static uz.RestaurantTestData.YAPONA;
import static uz.model.BaseEntity.START_SEQ;

/**
 * Created by Admin on 22.02.2017.
 */
public class MenuTestData {
    public static final int MENU1_ID = START_SEQ + 9;
    public static final int MENU2_ID = START_SEQ + 10;
    public static final int MENU3_ID = START_SEQ + 11;
    public static final int MENU4_ID = START_SEQ + 12;
    public static final int MENU5_ID = START_SEQ + 13;
    public static final int MENU6_ID = START_SEQ + 14;
    public static final int MENU7_ID = START_SEQ + 15;
    public static final int MENU8_ID = START_SEQ + 16;
    public static final int MENU9_ID = START_SEQ + 17;
    public static final int MENU10_ID = START_SEQ + 18;
    public static final int MENU11_ID = START_SEQ + 19;
    public static final int MENU12_ID = START_SEQ + 20;
    public static final int MENU13_ID = START_SEQ + 21;
    public static final int MENU14_ID = START_SEQ + 22;
    public static final int MENU15_ID = START_SEQ + 23;

    private static Calendar calendar = Calendar.getInstance();
    static {
        calendar.set(2017, 0, 15, 0, 0, 0);
    }
    private static Date date = calendar.getTime();

    public static final Menu MENU1 = new Menu(MENU1_ID, "Vegetable salad", 100, date, VERSAL);
    public static final Menu MENU2 = new Menu(MENU2_ID, "Chicken soup", 110, date, VERSAL);
    public static final Menu MENU3 = new Menu(MENU3_ID, "Pumpkin cream soup", 150, date, VERSAL);
    public static final Menu MENU6 = new Menu(MENU6_ID, "Water", 85, date, YAPONA);
    public static final Menu MENU7 = new Menu(MENU7_ID, "Chicken ball", 105, date, YAPONA);
    public static final Menu MENU8 = new Menu(MENU8_ID, "Vegetarian satay", 110, date, YAPONA);

    static {
        calendar.set(2017, 0, 16, 0, 0, 0);
        date = calendar.getTime();
    }

    public static final Menu MENU4 = new Menu(MENU4_ID, "Tea", 90, date, VERSAL);
    public static final Menu MENU5 = new Menu(MENU5_ID, "Juice", 80, date, VERSAL);
    public static final Menu MENU9 = new Menu(MENU9_ID, "Vegetarian soup", 125, date, YAPONA);
    public static final Menu MENU10 = new Menu(MENU10_ID, "Chocolate cake", 120, date, YAPONA);
    public static final Menu MENU11 = new Menu(MENU11_ID, "Tiramisu cake", 100, date, ELKI);
    public static final Menu MENU12 = new Menu(MENU12_ID, "Coffee", 95, date, ELKI);

    static {
        calendar.set(2017, 0, 17, 0, 0, 0);
        date = calendar.getTime();
    }

    public static final Menu MENU13 = new Menu(MENU13_ID, "Ice cream", 50, date, ELKI);
    public static final Menu MENU14 = new Menu(MENU14_ID, "Fruit bread", 70, date, ELKI);
    public static final Menu MENU15 = new Menu(MENU15_ID, "Winter Salad", 80, date, ELKI);

    public static final List<Menu> MENUS = Arrays.asList(MENU2, MENU3, MENU1, MENU7, MENU8, MENU6, MENU5, MENU4,
            MENU10, MENU9, MENU12, MENU11, MENU14, MENU13, MENU15);

    public static final ModelMatcher<Menu> MATCHER = ModelMatcher.of(Menu.class);
}
