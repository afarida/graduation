package uz.service;

import uz.model.Restaurant;

import java.util.List;

/**
 * Created by Admin on 15.02.2017.
 */
public interface RestaurantService {
    void delete(int id);

    List<Restaurant> findAll();

    Restaurant save(Restaurant restaurant);

    Restaurant update(Restaurant restaurant);

    Restaurant findOne(Integer id);
}
