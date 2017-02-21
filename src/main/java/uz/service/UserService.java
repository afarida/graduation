package uz.service;

import uz.model.User;

import java.util.List;

/**
 * Created by Admin on 15.02.2017.
 */
public interface UserService {
    void delete(int id);

    List<User> findAll();

    User save(User user);

    User update(User user);

    User findOne(Integer id);

    User findByEmail(String email);
}
