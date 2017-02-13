package service;

import model.Menu;
import org.springframework.stereotype.Service;
import util.exception.NotFoundException;

import java.util.List;

/**
 * Created by Admin on 13.02.2017.
 */
@Service
public class MenuServiceImpl implements MenuService {
    @Override
    public Menu get(int id) throws NotFoundException {
        return null;
    }

    @Override
    public Menu save(Menu menu) {
        return null;
    }

    @Override
    public Menu update(Menu menu) throws NotFoundException {
        return null;
    }

    @Override
    public void delete(Menu menu) throws NotFoundException {

    }

    @Override
    public List<Menu> getAll() {
        return null;
    }
}
