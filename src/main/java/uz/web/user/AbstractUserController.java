package uz.web.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import uz.model.User;
import uz.service.UserService;

import java.util.List;

/**
 * Created by Admin on 23.02.2017.
 */
public abstract class AbstractUserController {
    protected static final Logger LOG = LoggerFactory.getLogger(AbstractUserController.class);

    @Autowired
    private UserService service;

    public void delete(int id) {
        LOG.info("delete " + id);
        service.delete(id);
    }

    public List<User> getAll() {
        LOG.info("getAll");
        return service.getAll();
    }

    public User create(User user) {
        LOG.info("create " + user);
        return service.create(user);
    }

    public User update(User user) {
        LOG.info("update " + user);
        return service.update(user);
    }

    public User get(int id) {
        LOG.info("get " + id);
        return service.get(id);
    }

    public User findByEmail(String email) {
        LOG.info("findByEmail " + email);
        return service.findByEmail(email);
    }
}
