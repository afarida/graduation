package uz.web;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import uz.model.Restaurant;
import uz.service.RestaurantService;
import uz.web.json.JsonUtil;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static uz.RestaurantTestData.*;
import static uz.TestUtil.userHttpBasic;
import static uz.UserTestData.ADMIN;
import static uz.UserTestData.USER;

/**
 * Created by Admin on 02.03.2017.
 */
public class RestaurantControllerTest extends AbstractControllerTest {
    private static final String REST_URL = RestaurantController.REST_URL + "/";

    @Autowired
    private RestaurantService service;

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + ELKI_ID).with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print());

        MATCHER.assertCollectionEquals(Arrays.asList(VERSAL, YAPONA), service.getAll());
    }

    @Test
    public void testDeleteNotAcceptable() throws Exception {
        mockMvc.perform(delete(REST_URL + ELKI_ID).with(userHttpBasic(USER)))
                .andExpect(status().isNotAcceptable())
                .andDo(print());
    }

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get(REST_URL).with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andExpect(MATCHER.contentListMatcher(Arrays.asList(VERSAL, YAPONA, ELKI)))
                .andDo(print());
    }

    @Test
    public void testCreate() throws Exception {
        Restaurant newRestaurant = new Restaurant("NewRestaurant");
        mockMvc.perform(post(REST_URL).with(userHttpBasic(ADMIN))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newRestaurant)))
                .andExpect(status().isCreated())
                .andDo(print());
        MATCHER.assertCollectionEquals(Arrays.asList(newRestaurant, VERSAL, YAPONA, ELKI), service.getAll());
    }

    @Test
    public void testCreateNotAcceptable() throws Exception {
        Restaurant newRestaurant = new Restaurant("NewRestaurant");
        mockMvc.perform(post(REST_URL).with(userHttpBasic(USER))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newRestaurant)))
                .andExpect(status().isNotAcceptable())
                .andDo(print());
    }

    @Test
    public void testUpdate() throws Exception {
        Restaurant uRestaurant = new Restaurant(VERSAL);
        uRestaurant.setName("UpdateName");
        mockMvc.perform(put(REST_URL).with(userHttpBasic(ADMIN))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(uRestaurant)))
                .andExpect(status().isOk())
                .andDo(print());
        MATCHER.assertEquals(uRestaurant, service.get(VERSAL_ID));
    }

    @Test
    public void testUpdateNotAcceptable() throws Exception {
        Restaurant uRestaurant = new Restaurant(VERSAL);
        uRestaurant.setName("UpdateName");
        mockMvc.perform(put(REST_URL).with(userHttpBasic(USER))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(uRestaurant)))
                .andExpect(status().isNotAcceptable())
                .andDo(print());
    }

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + YAPONA_ID).with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(MATCHER.contentMatcher(YAPONA));
    }

}