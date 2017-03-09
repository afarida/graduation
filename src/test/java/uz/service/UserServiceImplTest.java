package uz.service;

import org.springframework.dao.DataAccessException;
import uz.model.Role;
import uz.model.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import uz.util.exception.NotFoundException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static uz.UserTestData.*;

/**
 * Created by Admin on 17.02.2017.
 */
public class UserServiceImplTest extends AbstractServiceTest {

    @Autowired
    private UserService service;

    @Test
    public void testCreate() throws Exception {
        User newUser = new User(null, "NewNameService", "new@gmail.com", "newPass", Role.ROLE_USER);
        User createdUser = service.create(newUser);
        newUser.setId(createdUser.getId());
        MATCHER.assertCollectionEquals(Arrays.asList(ADMIN, newUser, USER), service.getAll());
    }

    @Test(expected = NotFoundException.class)
    public void testNotFoundUpdate() throws Exception {
        User user = new User(USER);
        user.setId(1);
        user.setEmail("user1@gmail.com");
        service.update(user);
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(USER_ID);
        MATCHER.assertCollectionEquals(Collections.singletonList(ADMIN), service.getAll());
    }

    @Test(expected = NotFoundException.class)
    public void testNotFoundDelete() throws Exception {
        service.delete(1);
    }

    @Test(expected = DataAccessException.class)
    public void testDuplicateMailCreate() throws Exception {
        service.create(new User(null, "Duplicate", "user@yandex.ru", "newPass", Role.ROLE_USER));
    }

    @Test
    public void testGetAll() throws Exception {
        List<User> users = service.getAll();
        MATCHER.assertCollectionEquals(Arrays.asList(ADMIN, USER), users);
    }

    @Test
    public void testGet() throws Exception {
        User user = service.get(ADMIN_ID);
        MATCHER.assertEquals(ADMIN, user);
    }

    @Test
    public void testFindByEmail() throws Exception {
        User admin = service.findByEmail("admin@gmail.com");
        MATCHER.assertEquals(ADMIN, admin);
    }

    @Test
    public void testUpdate() throws Exception {
        User uUser = new User(USER);
        uUser.setName("updatedNameService");
        service.update(uUser);
        MATCHER.assertEquals(uUser, service.get(USER_ID));
    }
}