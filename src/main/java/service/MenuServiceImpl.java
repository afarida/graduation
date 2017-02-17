package service;

import model.Menu;
import model.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.MenuRepository;
import util.exception.ExceptionUtil;

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
    public boolean delete(int id) {
        boolean found = repository.delete(id) != 0;
        ExceptionUtil.checkNotFoundWithId(found, id);
        return found;
    }

    @Override
    public List<Menu> findAll() {
        return repository.findAll();
    }

    @Override
    public Menu save(Menu menu) {
        if (menu.isNew()) {
            return repository.save(menu);
        }
        return repository.save(ExceptionUtil.checkNotFoundWithId(menu, menu.getId()));
    }

    @Override
    public Menu findOne(Integer id) {
        return ExceptionUtil.checkNotFoundWithId(repository.findOne(id), id);
    }

    @Override
    public List<Menu> getByDate(Date date) {
        return repository.getByDate(date);
    }

    @Override
    public List<Menu> getByDateAndRestaurant(Date date, Restaurant restaurant) {
        return repository.getByDateAndRestaurant_Id(date, restaurant.getId());
    }
}
