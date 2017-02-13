package service;

import model.Restaurant;
import util.exception.NotFoundException;

import java.util.List;

/**
 * Created by Admin on 13.02.2017.
 */
public interface RestaurantService {
    Restaurant get(int id) throws NotFoundException;

    Restaurant save(Restaurant restaurant);

    Restaurant update(Restaurant restaurant) throws NotFoundException;

    void delete(Restaurant restaurant) throws NotFoundException;

    List<Restaurant> getAll();
}
