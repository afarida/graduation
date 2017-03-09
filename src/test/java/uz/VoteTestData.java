package uz;

import uz.matcher.ModelMatcher;
import uz.model.Vote;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import static uz.RestaurantTestData.ELKI;
import static uz.RestaurantTestData.VERSAL;
import static uz.RestaurantTestData.YAPONA;
import static uz.UserTestData.ADMIN;
import static uz.model.BaseEntity.START_SEQ;
import static uz.UserTestData.USER;

/**
 * Created by Admin on 21.02.2017.
 */
public class VoteTestData {
    public static final int VOTE1_ID = START_SEQ + 5;
    public static final int VOTE2_ID = START_SEQ + 6;
    public static final int VOTE3_ID = START_SEQ + 7;
    public static final int VOTE4_ID = START_SEQ + 8;

    public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("YYYY-MM-DD");

    private static Calendar calendar = Calendar.getInstance();

    static {
        calendar.set(2017, 0, 15, 0, 0, 0);
    }

    public static final Vote VOTE1 = new Vote(VOTE1_ID, true, calendar.getTime(), USER, ELKI);
    public static final Vote VOTE2 = new Vote(VOTE2_ID, true, calendar.getTime(), ADMIN, VERSAL);

    static {
        calendar.set(2017, 0, 16, 0, 0, 0);
    }

    public static final Vote VOTE3 = new Vote(VOTE3_ID, true, calendar.getTime(), USER, YAPONA);
    public static final Vote VOTE4 = new Vote(VOTE4_ID, true, calendar.getTime(), ADMIN, VERSAL);

    public static final ModelMatcher<Vote> MATCHER = ModelMatcher.of(Vote.class);
}
