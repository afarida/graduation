package service;

import model.Restaurant;
import org.springframework.stereotype.Service;
import util.exception.NotFoundException;

import java.util.List;

/**
 * Created by Admin on 13.02.2017.
 */
@Service
public class RestaurantServiceImpl implements RestaurantService {
    @Override
    public Restaurant get(int id) throws NotFoundException {
        return null;
    }

    @Override
    public Restaurant save(Restaurant restaurant) {
        return null;
    }

    @Override
    public Restaurant update(Restaurant restaurant) throws NotFoundException {
        return null;
    }

    @Override
    public void delete(Restaurant restaurant) throws NotFoundException {

    }

    @Override
    public List<Restaurant> getAll() {
        return null;
    }
}
