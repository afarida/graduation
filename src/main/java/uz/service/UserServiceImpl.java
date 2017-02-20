package uz.service;

import uz.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import uz.repository.UserRepository;
import uz.util.exception.ExceptionUtil;

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
        boolean found = repository.delete(id) != 0;
        ;
        ExceptionUtil.checkNotFoundWithId(found, id);
        return found;
    }

    @Override
    public List<User> findAll() {
        return repository.findAll(SORT_NAME_EMAIL);
    }

    @Override
    public User save(User user) {
        Assert.notNull(user, "user must not be null");
        return repository.save(user);
    }

    @Override
    public User update(User user) {
        Assert.notNull(user, "user must not be null");
        return repository.save(ExceptionUtil.checkNotFoundWithId(user, user.getId()));
    }

    @Override
    public User findOne(Integer id) {
        return ExceptionUtil.checkNotFoundWithId(repository.findOne(id), id);
    }

    @Override
    public User findByEmail(String email) {
        return ExceptionUtil.checkNotFound(repository.findByEmail(email), "email=" + email);
    }
}
