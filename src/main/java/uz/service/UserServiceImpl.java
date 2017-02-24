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

    @Autowired
    private UserRepository repository;

    @Override
    public void delete(int id) {
        ExceptionUtil.checkNotFoundWithId(repository.delete(id) != 0, id);
    }

    @Override
    public List<User> getAll() {
        return repository.getAll();
    }

    @Transactional
    @Override
    public User create(User user) {
        Assert.notNull(user, "user must not be null");
        return repository.save(user);
    }

    @Override
    public User update(User user) {
        Assert.notNull(user, "user must not be null");
        ExceptionUtil.checkNotFoundWithId(repository.getOne(user.getId()), user.getId());
        return repository.save(user);
    }

    @Override
    public User get(Integer id) {
        return ExceptionUtil.checkNotFoundWithId(repository.getOne(id), id);
    }

    @Override
    public User findByEmail(String email) {
        return ExceptionUtil.checkNotFound(repository.findByEmail(email), "email=" + email);
    }
}
