package service;

import model.User;
import org.springframework.stereotype.Service;
import util.exception.NotFoundException;

import java.util.List;

/**
 * Created by Admin on 13.02.2017.
 */
@Service
public class UserServiceImpl implements UserService {
    @Override
    public User get(int id) throws NotFoundException {
        return null;
    }

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public User update(User user) throws NotFoundException {
        return null;
    }

    @Override
    public void delete(User user) throws NotFoundException {

    }

    @Override
    public List<User> getAll() {
        return null;
    }
}
