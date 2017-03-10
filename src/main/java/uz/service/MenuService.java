package uz.service;

import org.springframework.security.access.annotation.Secured;
import uz.model.Menu;
import uz.model.Restaurant;

import java.util.Date;
import java.util.List;

/**
 * Created by Admin on 15.02.2017.
 */
public interface MenuService {
    @Secured("ROLE_ADMIN")
    void delete(int id);

    List<Menu> getAll();

    @Secured("ROLE_ADMIN")
    Menu create(Menu menu);

    @Secured("ROLE_ADMIN")
    Menu update(Menu menu);

    Menu get(Integer id);

    List<Menu> getByDate(Date date);

    List<Menu> getByRestaurant(int restaurantId);

    List<Menu> getByDateAndRestaurant(Date date, int restaurantId);
}
