package uz.service;

import uz.model.User;
import uz.util.exception.NotFoundException;

import java.util.List;

/**
 * Created by Admin on 15.02.2017.
 */
public interface UserService {
    void delete(int id) throws NotFoundException;

    List<User> getAll();

    User create(User user);

    User update(User user);

    User get(Integer id) throws NotFoundException;

    User findByEmail(String email) throws NotFoundException;
}
