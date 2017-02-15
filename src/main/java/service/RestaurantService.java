package service;

import model.Restaurant;

import java.util.List;

/**
 * Created by Admin on 15.02.2017.
 */
public interface RestaurantService {
    boolean delete(int id);

    List<Restaurant> findAll();

    Restaurant save(Restaurant restaurant);

    Restaurant findOne(Integer id);
}
