package service;

import model.Menu;
import model.Restaurant;

import java.util.Date;
import java.util.List;

/**
 * Created by Admin on 15.02.2017.
 */
public interface MenuService {
    boolean delete(int id);

    List<Menu> findAll();

    Menu save(Menu menu);

    Menu findOne(Integer id);

    List<Menu> getByDate(Date date);

    List<Menu> getByDateAndRestaurant(Date date, Restaurant restaurant);
}
