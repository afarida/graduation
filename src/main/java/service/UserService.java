package service;

import model.User;
import util.exception.NotFoundException;

import java.util.List;

/**
 * Created by Admin on 13.02.2017.
 */
public interface UserService {
    User get(int id) throws NotFoundException;

    User save(User user);

    User update(User user) throws NotFoundException;

    void delete(User user) throws NotFoundException;

    List<User> getAll();
}
