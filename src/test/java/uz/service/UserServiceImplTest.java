package uz.service;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import uz.model.Role;
import uz.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import uz.service.UserService;
import uz.util.exception.NotFoundException;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Matcher;

import static uz.UserTestData.*;

/**
 * Created by Admin on 17.02.2017.
 */
public class UserServiceImplTest extends AbstractServiceTest {

    @Autowired
    private UserService service;

    @Test
    public void testSave() throws Exception {
        User newUser = new User(null, "New", "new@gmail.com", "newPass", Role.USER);
        User createdUser = service.save(newUser);
        newUser.setId(createdUser.getId());
        MATCHER.assertCollectionEquals(Arrays.asList(ADMIN, newUser, USER), service.findAll());
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(USER_ID);
        MATCHER.assertCollectionEquals(Collections.singletonList(ADMIN), service.findAll());
    }

    @Test(expected = NotFoundException.class)
    public void testNotFoundDelete() throws Exception {
        service.delete(1);
    }

    @Test(expected = DataAccessException.class)
    public void testDuplicateMailSave() throws Exception {
        service.save(new User(null, "Duplicate", "user@yandex.ru", "newPass", Role.USER));
    }

    @Test
    public void testFindAll() throws Exception {
        List<User> users = service.findAll();
        MATCHER.assertCollectionEquals(Arrays.asList(ADMIN, USER), users);
    }

    @Test
    public void testFindOne() throws Exception {
        User user = service.findOne(ADMIN_ID);
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
        uUser.setName("UpdatedName");
        service.update(uUser);
        MATCHER.assertEquals(uUser, service.findOne(USER_ID));
    }
}