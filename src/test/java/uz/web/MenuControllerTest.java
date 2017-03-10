package uz.web;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import uz.model.Menu;
import uz.service.MenuService;
import uz.web.json.JsonUtil;

import java.util.Arrays;
import java.util.Calendar;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static uz.MenuTestData.MATCHER;
import static uz.MenuTestData.*;
import static uz.RestaurantTestData.*;
import static uz.TestUtil.userHttpBasic;
import static uz.UserTestData.ADMIN;
import static uz.UserTestData.USER;

/**
 * Created by Admin on 10.03.2017.
 */
public class MenuControllerTest extends AbstractControllerTest {
    private static final String REST_URL = MenuController.REST_URL + "/";

    @Autowired
    private MenuService service;

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + MENU1_ID).with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print());
        MATCHER.assertCollectionEquals(Arrays.asList(MENU2, MENU3, MENU5, MENU4),
                service.getByRestaurant(VERSAL_ID));
    }

    @Test
    public void testDeleteNotAcceptable() throws Exception {
        mockMvc.perform(delete(REST_URL + MENU1_ID).with(userHttpBasic(USER)))
                .andExpect(status().isNotAcceptable())
                .andDo(print());
    }

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get(REST_URL).with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andExpect(MATCHER.contentListMatcher(MENUS))
                .andDo(print());
    }

    @Test
    public void testCreate() throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2017, 0, 15, 0, 0, 0);

        Menu newMenu = new Menu(null, "NewName", 100, calendar.getTime(), YAPONA);
        ResultActions actions = mockMvc.perform(post(REST_URL).with(userHttpBasic(ADMIN))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newMenu)))
                .andExpect(status().isCreated())
                .andDo(print());

        Menu created = MATCHER.fromJsonAction(actions);
        newMenu.setId(created.getId());

        MATCHER.assertCollectionEquals(Arrays.asList(MENU7, newMenu, MENU8, MENU6, MENU10, MENU9), service.getByRestaurant(YAPONA_ID));
    }

    @Test
    public void testCreateNotAcceptable() throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2017, 0, 15, 0, 0, 0);

        Menu newMenu = new Menu(null, "NewName", 100, calendar.getTime(), YAPONA);
        ResultActions actions = mockMvc.perform(post(REST_URL).with(userHttpBasic(USER))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newMenu)))
                .andExpect(status().isNotAcceptable())
                .andDo(print());
    }

    @Test
    public void testUpdate() throws Exception {
        Menu uMenu = new Menu(MENU1);
        uMenu.setDish("UpdateName");

        mockMvc.perform(put(REST_URL).with(userHttpBasic(ADMIN))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(uMenu)))
                .andExpect(status().isOk())
                .andDo(print());
        MATCHER.assertEquals(uMenu, service.get(MENU1_ID));
    }

    @Test
    public void testUpdateNotAcceptable() throws Exception {
        mockMvc.perform(put(REST_URL).with(userHttpBasic(USER))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(MENU1)))
                .andExpect(status().isNotAcceptable())
                .andDo(print());
    }


    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + MENU2_ID).with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andExpect(MATCHER.contentMatcher(MENU2))
                .andDo(print());
    }

    @Test
    public void testGetByDate() throws Exception {
        mockMvc.perform(get(REST_URL + "by?date=2017-01-15")
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andExpect(MATCHER.contentListMatcher(Arrays.asList(MENU2, MENU3, MENU1, MENU7,
                        MENU8, MENU6)))
                .andDo(print());
    }

    @Test
    public void testGetByRestaurant() throws Exception {
        mockMvc.perform(get(REST_URL + "by?restaurant=" + VERSAL_ID)
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andExpect(MATCHER.contentListMatcher(Arrays.asList(MENU2, MENU3, MENU1, MENU5, MENU4)))
                .andDo(print());
    }

    @Test
    public void testGetByDateAndRestaurant() throws Exception {
        mockMvc.perform(get(REST_URL + "by?date=2017-01-15&restaurant=" + VERSAL_ID)
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andExpect(MATCHER.contentListMatcher(Arrays.asList(MENU2, MENU3, MENU1)))
                .andDo(print());
    }

}