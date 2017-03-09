package uz.web;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import uz.model.Vote;
import uz.service.VoteService;
import uz.web.json.JsonUtil;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static uz.RestaurantTestData.VERSAL;
import static uz.RestaurantTestData.YAPONA;
import static uz.TestUtil.userHttpBasic;
import static uz.UserTestData.*;
import static uz.VoteTestData.MATCHER;
import static uz.VoteTestData.*;

/**
 * Created by Admin on 09.03.2017.
 */
public class VoteControllerTest extends AbstractControllerTest {
    private static final String REST_URL = VoteController.REST_URL + "/";

    @Autowired
    private VoteService service;

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + VOTE2_ID).with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print());
        MATCHER.assertCollectionEquals(Collections.singletonList(VOTE4), service.getAll(ADMIN_ID));
    }

    @Test
    public void testGetAllAdmin() throws Exception {
        mockMvc.perform(get(REST_URL).with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andExpect(MATCHER.contentListMatcher(Arrays.asList(VOTE2, VOTE1, VOTE4, VOTE3)))
                .andDo(print());
    }

    @Test
    public void testGetAllUser() throws Exception {
        mockMvc.perform(get(REST_URL).with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andExpect(MATCHER.contentListMatcher(Arrays.asList(VOTE1, VOTE3)))
                .andDo(print());
    }

    @Test
    public void testCreate() throws Exception {
        Vote newVote = new Vote(null, true, new Date(), USER, VERSAL);
        ResultActions actions = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newVote))
                .with(userHttpBasic(USER)))
                .andExpect(status().isCreated())
                .andDo(print());

        Vote created = MATCHER.fromJsonAction(actions);
        newVote.setId(created.getId());

        MATCHER.assertCollectionEquals(Arrays.asList(VOTE1, VOTE3, newVote), service.getAll(USER_ID));
    }

    @Test
    public void testCreateUnprocessableEntity() throws Exception {
        Vote newVote = new Vote(null, true, new Date(), ADMIN, VERSAL);
        mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newVote))
                .with(userHttpBasic(USER)))
                .andExpect(status().isUnprocessableEntity())
                .andDo(print());
    }

    @Test
    public void testUpdate() throws Exception {
        Vote uVote = new Vote(VOTE1);
        uVote.setRestaurant(YAPONA);

        mockMvc.perform(put(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(uVote))
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andDo(print());
        MATCHER.assertEquals(uVote, service.get(VOTE1_ID, USER_ID));
    }

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + VOTE1_ID).with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andExpect(MATCHER.contentMatcher(VOTE1))
                .andDo(print());
    }

    @Test
    public void testGetAllByDate() throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2017, 0, 15, 0, 0, 0);

        mockMvc.perform(get(REST_URL + "by?date=" + SIMPLE_DATE_FORMAT.format(calendar.getTime()))
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andExpect(MATCHER.contentListMatcher(Arrays.asList(VOTE2, VOTE1)))
                .andDo(print());
    }

    @Test
    public void testGetAllByDateNotAcceptable() throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2017, 0, 15, 0, 0, 0);

        mockMvc.perform(get(REST_URL + "by?date=" + SIMPLE_DATE_FORMAT.format(calendar.getTime()))
                .with(userHttpBasic(USER)))
                .andExpect(status().isNotAcceptable())
                .andDo(print());
    }
}