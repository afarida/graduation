package uz.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import uz.model.Menu;
import uz.util.exception.NotFoundException;

import java.util.Arrays;
import java.util.Calendar;

import static uz.MenuTestData.*;
import static uz.MenuTestData.MATCHER;
import static uz.RestaurantTestData.*;

/**
 * Created by Admin on 22.02.2017.
 */
public class MenuServiceImplTest extends AbstractServiceTest {
    private static final Calendar CALENDAR = Calendar.getInstance();

    @Autowired
    private MenuService service;

    @Test
    public void testDelete() throws Exception {
        service.delete(MENU13_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(MENU12, MENU11, MENU14, MENU15), service.getByRestaurant(ELKI_ID));
    }

    @Test(expected = NotFoundException.class)
    public void testNotFoundDelete() throws Exception {
        service.delete(1);
    }

    @Test
    public void testGetAll() throws Exception {
        MATCHER.assertCollectionEquals(MENUS, service.getAll());
    }

    @Test
    public void testCreate() throws Exception {
        CALENDAR.set(2017, 1, 18, 0, 0, 0);
        Menu menu = new Menu(null, "New dish name", 100, CALENDAR.getTime(), ELKI);
        Menu createdMenu = service.create(menu);
        menu.setId(createdMenu.getId());
        MATCHER.assertCollectionEquals(Arrays.asList(MENU12, MENU11, MENU14, MENU13, MENU15, menu),
                service.getByRestaurant(ELKI_ID));
    }

    @Test
    public void testUpdate() throws Exception {
        Menu menu = new Menu(MENU1);
        menu.setDish("Update dish name");
        service.update(menu);
        MATCHER.assertEquals(menu, service.get(MENU1_ID));
    }

    @Test(expected = NotFoundException.class)
    public void testNotFoundUpdate() throws Exception {
        Menu menu = new Menu(MENU13);
        menu.setId(1);
        menu.setDish("Update dish name");
        service.update(menu);
    }

    @Test
    public void testGet() throws Exception {
        MATCHER.assertEquals(MENU2, service.get(MENU2_ID));
    }

    @Test
    public void testGetByDate() throws Exception {
        CALENDAR.set(2017, 0, 16, 0, 0, 0);
        MATCHER.assertCollectionEquals(Arrays.asList(MENU5, MENU4, MENU10, MENU9, MENU12, MENU11),
                service.getByDate(CALENDAR.getTime()));
    }

    @Test
    public void testGetByRestaurant() throws Exception {
        MATCHER.assertCollectionEquals(Arrays.asList(MENU7, MENU8, MENU6, MENU10, MENU9),
                service.getByRestaurant(YAPONA_ID));
    }

    @Test
    public void testGetByDateAndRestaurant() throws Exception {
        CALENDAR.set(2017, 0, 15, 0, 0, 0);
        MATCHER.assertCollectionEquals(Arrays.asList(MENU2, MENU3, MENU1),
                service.getByDateAndRestaurant(CALENDAR.getTime(),VERSAL_ID));
    }

}