package uz.service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import uz.model.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uz.repository.RestaurantRepository;
import uz.util.exception.ExceptionUtil;

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

    @Transactional
    @Override
    public Restaurant save(Restaurant restaurant) {
        Assert.notNull(restaurant);
        return repository.save(restaurant);
    }

    @Override
    public Restaurant update(Restaurant restaurant) {
        Assert.notNull(restaurant);
        return repository.save(ExceptionUtil.checkNotFoundWithId(restaurant, restaurant.getId()));
    }

    @Override
    public Restaurant findOne(Integer id) {
        return ExceptionUtil.checkNotFoundWithId(repository.findOne(id), id);
    }
}
