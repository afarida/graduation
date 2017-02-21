package uz;

import uz.matcher.ModelMatcher;
import uz.model.Restaurant;

import java.util.Objects;

import static uz.model.BaseEntity.START_SEQ;

/**
 * Created by Admin on 20.02.2017.
 */
public class RestaurantTestData {
    public static final int VERSAL_ID = START_SEQ + 2;
    public static final int YAPONA_ID = START_SEQ + 3;
    public static final int ELKI_ID = START_SEQ + 4;

    public static final Restaurant VERSAL = new Restaurant(VERSAL_ID, "Versal");
    public static final Restaurant YAPONA = new Restaurant(YAPONA_ID, "Yaponamama");
    public static final Restaurant ELKI = new Restaurant(ELKI_ID, "Елки-палки");

    public static final ModelMatcher<Restaurant> MATCHER = ModelMatcher.of(Restaurant.class);
}
