package uz.web.user;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import uz.model.Role;
import uz.model.User;
import uz.service.UserService;
import uz.web.AbstractControllerTest;
import uz.web.json.JsonUtil;

import java.util.Arrays;
import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static uz.TestUtil.userHttpBasic;
import static uz.UserTestData.*;

/**
 * Created by Admin on 28.02.2017.
 */
public class AdminControllerTest extends AbstractControllerTest {
    static final String REST_URL = AdminController.REST_URL + '/';

    @Autowired
    private UserService service;

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + USER_ID).with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print());
        MATCHER.assertCollectionEquals(Collections.singletonList(ADMIN), service.getAll());
    }

    @Test
    public void testDeleteSecured() throws Exception {
        mockMvc.perform(delete(REST_URL + USER_ID).with(userHttpBasic(USER)))
                .andExpect(status().isForbidden())
                .andDo(print());
    }

    @Test
    public void testDeleteNotFound() throws Exception {
        mockMvc.perform(delete(REST_URL + 1).with(userHttpBasic(ADMIN)))
                .andExpect(status().isUnprocessableEntity())
                .andDo(print());
    }

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get(REST_URL).with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentListMatcher(Arrays.asList(ADMIN, USER)));
    }

    @Test
    public void testCreate() throws Exception {
        User user = new User(null, "New", "new@gmail.com", "newPass", Role.ROLE_USER);
        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(user))
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isCreated());

        User returned = MATCHER.fromJsonAction(action);
        user.setId(returned.getId());

        MATCHER.assertEquals(user, returned);
        MATCHER.assertCollectionEquals(Arrays.asList(ADMIN, user, USER), service.getAll());
    }

    @Test
    public void testUpdate() throws Exception {
        User user = new User(USER);
        user.setName("Update User");
        ResultActions action = mockMvc.perform(put(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(user))
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk());

        User returned = MATCHER.fromJsonAction(action);
        MATCHER.assertEquals(returned, service.get(USER_ID));
    }

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + ADMIN_ID).with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentMatcher(ADMIN));
    }

    @Test
    public void testGetNotFound() throws Exception {
        mockMvc.perform(get(REST_URL + 1).with(userHttpBasic(ADMIN)))
                .andExpect(status().isUnprocessableEntity())
                .andDo(print());
    }

    @Test
    public void testGetUnauthorized() throws Exception {
        mockMvc.perform(get(REST_URL + USER_ID))
                .andExpect(status().isUnauthorized())
                .andDo(print());
    }

    @Test
    public void testFindByEmail() throws Exception {
        mockMvc.perform(get(REST_URL + "by?email=" + ADMIN.getEmail()).with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentMatcher(ADMIN));
    }

}