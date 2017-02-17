package service;

import model.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import repository.RestaurantRepository;
import util.exception.ExceptionUtil;

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
        boolean found = repository.delete(id) != 0;
        ExceptionUtil.checkNotFoundWithId(found, id);
        return found;
    }

    @Override
    public List<Restaurant> findAll() {
        return repository.findAll(SORT_NAME);
    }

    @Override
    public Restaurant save(Restaurant restaurant) {
        if (restaurant.isNew()) {
            return repository.save(restaurant);
        }
        return repository.save(ExceptionUtil.checkNotFoundWithId(restaurant, restaurant.getId()));
    }

    @Override
    public Restaurant findOne(Integer id) {
        return ExceptionUtil.checkNotFoundWithId(repository.findOne(id), id);
    }
}
