package service;

import model.Menu;
import util.exception.NotFoundException;

import java.util.List;

/**
 * Created by Admin on 13.02.2017.
 */
public interface MenuService {
    Menu get(int id) throws NotFoundException;

    Menu save(Menu menu);

    Menu update(Menu menu) throws NotFoundException;

    void delete(Menu menu) throws NotFoundException;

    List<Menu> getAll();
}
