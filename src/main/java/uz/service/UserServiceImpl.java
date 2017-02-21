package uz.service;

import org.springframework.transaction.annotation.Transactional;
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
    public void delete(int id) {
        ExceptionUtil.checkNotFoundWithId(repository.delete(id) != 0, id);
    }

    @Override
    public List<User> findAll() {
        return repository.findAll(SORT_NAME_EMAIL);
    }

    @Transactional
    @Override
    public User save(User user) {
        Assert.notNull(user, "user must not be null");
        return repository.save(user);
    }

    @Override
    public User update(User user) {
        Assert.notNull(user, "user must not be null");
        ExceptionUtil.checkNotFoundWithId(repository.findOne(user.getId()), user.getId());
        return repository.save(user);
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
