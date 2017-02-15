package service;

import model.User;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * Created by Admin on 15.02.2017.
 */
public interface UserService {
    boolean delete(int id);

    List<User> findAll();

    User save(User user);

    User findOne(Integer id);

    User findByEmail(String email);
}
