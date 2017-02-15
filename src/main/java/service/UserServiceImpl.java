package service;

import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import repository.UserRepository;

import java.util.List;

/**
 * Created by Admin on 15.02.2017.
 */
@Service
public class UserServiceImpl implements UserService {
    private static final Sort SORT_NAME_EMAIL = new Sort("name", "email");

    @Autowired
    private UserRepository repository;

    @Override
    public boolean delete(int id) {
        return repository.delete(id) != 0;
    }

    @Override
    public List<User> findAll() {
        return repository.findAll(SORT_NAME_EMAIL);
    }

    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public User findOne(Integer id) {
        return repository.findOne(id);
    }

    @Override
    public User findByEmail(String email) {
        return repository.findByEmail(email);
    }
}
