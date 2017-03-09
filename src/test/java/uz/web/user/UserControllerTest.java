package uz.web.user;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import uz.AuthorizedUser;
import uz.model.User;
import uz.service.UserService;
import uz.web.AbstractControllerTest;
import uz.web.json.JsonUtil;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static uz.TestUtil.userHttpBasic;
import static uz.UserTestData.*;

/**
 * Created by Admin on 01.03.2017.
 */
public class UserControllerTest extends AbstractControllerTest {
    private static final String REST_URL = UserController.REST_URL;

    @Autowired
    private UserService service;

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL).with(userHttpBasic(USER)))
                .andDo(print())
                .andExpect(status().isOk());

        MATCHER.assertCollectionEquals(Collections.singletonList(ADMIN), service.getAll());
    }

    @Test
    public void testDeleteUnauthorized() throws Exception {
        mockMvc.perform(delete(REST_URL))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void testUpdate() throws Exception {
        User user = new User(USER);
        user.setName("newName");

        mockMvc.perform(put(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(user))
                .with(userHttpBasic(USER)))
                .andDo(print())
                .andExpect(status().isOk());

        MATCHER.assertEquals(user, service.get(USER_ID));
    }

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL).with(userHttpBasic(USER)))
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MATCHER.contentMatcher(USER));
    }

    @Test
    public void testGetUnauthorized() throws Exception {
        mockMvc.perform(get(REST_URL))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }
}