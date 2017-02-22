package uz.service;

import uz.model.Menu;
import uz.model.Restaurant;

import java.util.Date;
import java.util.List;

/**
 * Created by Admin on 15.02.2017.
 */
public interface MenuService {
    void delete(int id);

    List<Menu> findAll();

    Menu save(Menu menu);

    Menu update(Menu menu);

    Menu findOne(Integer id);

    List<Menu> getByDate(Date date);

    List<Menu> getByRestaurant(Restaurant restaurant);

    List<Menu> getByDateAndRestaurant(Date date, Restaurant restaurant);
}
