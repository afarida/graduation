package service;

import model.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import repository.RestaurantRepository;

import java.util.List;

/**
 * Created by Admin on 15.02.2017.
 */
@Service
public class RestaurantServiceImpl implements RestaurantService {
    private static final Sort SORT_NAME = new Sort("name");

    @Autowired
    private RestaurantRepository repository;

    @Override
    public boolean delete(int id) {
        return repository.delete(id) != 0;
    }

    @Override
    public List<Restaurant> findAll() {
        return repository.findAll(SORT_NAME);
    }

    @Override
    public Restaurant save(Restaurant restaurant) {
        return repository.save(restaurant);
    }

    @Override
    public Restaurant findOne(Integer id) {
        return repository.findOne(id);
    }
}
