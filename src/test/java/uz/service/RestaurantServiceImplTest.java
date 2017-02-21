package uz.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import uz.model.Restaurant;
import uz.util.exception.NotFoundException;

import java.util.Arrays;

import static uz.RestaurantTestData.*;

/**
 * Created by Admin on 20.02.2017.
 */
public class RestaurantServiceImplTest extends AbstractServiceTest {

    @Autowired
    private RestaurantService service;

    @Test
    public void testDelete() throws Exception {
        service.delete(VERSAL_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(YAPONA, ELKI), service.findAll());
    }

    @Test(expected = NotFoundException.class)
    public void testNotFoundDelete() throws Exception {
        service.delete(1);
    }

    @Test
    public void testFindAll() throws Exception {
        MATCHER.assertCollectionEquals(Arrays.asList(VERSAL, YAPONA, ELKI), service.findAll());
    }

    @Test
    public void testSave() throws Exception {
        Restaurant newRestaurant = new Restaurant("New");
        Restaurant createdRestaurant = service.save(newRestaurant);
        newRestaurant.setId(createdRestaurant.getId());
        MATCHER.assertCollectionEquals(Arrays.asList(newRestaurant, VERSAL, YAPONA, ELKI), service.findAll());
    }

    @Test
    public void testUpdate() throws Exception {
        Restaurant uRestaurant = new Restaurant(VERSAL);
        uRestaurant.setName("UpdateName");
        service.update(uRestaurant);
        MATCHER.assertEquals(uRestaurant, service.findOne(VERSAL_ID));
    }

    @Test(expected = NotFoundException.class)
    public void testNotFoundUpdate() throws Exception {
        Restaurant restaurant = new Restaurant(VERSAL);
        restaurant.setId(1);
        restaurant.setName("Name");
        service.update(restaurant);
    }

    @Test
    public void testFindOne() throws Exception {
        MATCHER.assertEquals(YAPONA, service.findOne(YAPONA_ID));
    }
}