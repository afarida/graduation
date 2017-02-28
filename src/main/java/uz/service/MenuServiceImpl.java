package uz.service;

import org.springframework.util.Assert;
import uz.model.Menu;
import uz.model.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.repository.MenuRepository;
import uz.util.exception.ExceptionUtil;

import java.util.Date;
import java.util.List;

/**
 * Created by Admin on 15.02.2017.
 */
@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuRepository repository;

    @Override
    public void delete(int id) {
        ExceptionUtil.checkNotFoundWithId(repository.delete(id) != 0, id);
    }

    @Override
    public List<Menu> getAll() {
        return repository.getAll();
    }

    @Override
    public Menu create(Menu menu) {
        Assert.notNull(menu, "menu must not be null");
        return repository.save(menu);
    }

    @Override
    public Menu update(Menu menu) {
        Assert.notNull(menu);
        ExceptionUtil.checkNotFoundWithId(repository.findOne(menu.getId()), menu.getId());
        return repository.save(menu);
    }

    @Override
    public Menu get(Integer id) {
        return ExceptionUtil.checkNotFoundWithId(repository.findOne(id), id);
    }

    @Override
    public List<Menu> getByDate(Date date) {
        return repository.getByDate(date);
    }

    @Override
    public List<Menu> getByRestaurant(int restaurantId) {
        return repository.getByRestaurantId(restaurantId);
    }

    @Override
    public List<Menu> getByDateAndRestaurant(Date date, int restaurantId) {
        return repository.getByDateAndRestaurantId(date, restaurantId);
    }
}
