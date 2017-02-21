package uz.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import uz.model.Vote;
import uz.util.exception.NotFoundException;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;

import static uz.RestaurantTestData.VERSAL;
import static uz.RestaurantTestData.YAPONA;
import static uz.UserTestData.*;
import static uz.VoteTestData.*;
import static uz.VoteTestData.MATCHER;

/**
 * Created by Admin on 21.02.2017.
 */
public class VoteServiceImplTest extends AbstractServiceTest {
    @Autowired
    private VoteService service;

    @Test
    public void testDelete() throws Exception {
        service.delete(VOTE1_ID, USER_ID);
        MATCHER.assertCollectionEquals(Collections.singletonList(VOTE3), service.findAll(USER_ID));
    }

    @Test(expected = NotFoundException.class)
    public void testNotFoundDelete() throws Exception {
        service.delete(VOTE2_ID, USER.getId());
    }

    @Test
    public void testFindAll() throws Exception {
        MATCHER.assertCollectionEquals(Arrays.asList(VOTE1, VOTE3), service.findAll(USER_ID));
    }

    @Test
    public void testNullFindAll() throws Exception {
        MATCHER.assertCollectionEquals(Collections.EMPTY_LIST, service.findAll(1));
    }

    @Test
    public void testSave() throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2017, 1, 18);
        Vote vote = new Vote(null, true, calendar.getTime(), ADMIN, VERSAL);
        Vote createsVote = service.save(vote, ADMIN_ID);
        vote.setId(createsVote.getId());
        MATCHER.assertCollectionEquals(Arrays.asList(VOTE2, VOTE4, vote), service.findAll(ADMIN_ID));
    }

    @Test
    public void testUpdate() throws Exception {
        Vote uVote = new Vote(VOTE2);
        uVote.setRestaurant(YAPONA);
        service.update(uVote, ADMIN_ID);
        MATCHER.assertEquals(uVote, service.findOne(VOTE2_ID, ADMIN_ID));
    }

    @Test(expected = NotFoundException.class)
    public void testNotFoundUpdate() throws Exception {
        service.update(VOTE2, USER_ID);
    }

    @Test
    public void testFindOne() throws Exception {
        MATCHER.assertEquals(VOTE2, service.findOne(VOTE2_ID, ADMIN_ID));
    }

}