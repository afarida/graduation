package uz.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import uz.AuthorizedUser;
import uz.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import uz.repository.UserRepository;
import uz.util.exception.ExceptionUtil;
import uz.util.exception.NotFoundException;

import java.util.List;

/**
 * Created by Admin on 15.02.2017.
 */
@Service("userService")
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public void delete(int id) throws NotFoundException {
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
    public User update(User user) throws NotFoundException {
        Assert.notNull(user, "user must not be null");
        ExceptionUtil.checkNotFoundWithId(repository.findOne(user.getId()), user.getId());
        return repository.save(user);
    }

    @Override
    public User get(Integer id) throws NotFoundException {
        return ExceptionUtil.checkNotFoundWithId(repository.findOne(id), id);
    }

    @Override
    public User findByEmail(String email) throws NotFoundException {
        return ExceptionUtil.checkNotFound(repository.findByEmail(email), "email=" + email);
    }

    @Override
    public AuthorizedUser loadUserByUsername(String email) throws UsernameNotFoundException {
        User u = repository.findByEmail(email.toLowerCase());
        if (u == null)
            throw new UsernameNotFoundException("User " + email + " is not found");
        return new AuthorizedUser(u);
    }
}
