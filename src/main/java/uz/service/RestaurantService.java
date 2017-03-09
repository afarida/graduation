package uz.service;

import org.springframework.security.access.annotation.Secured;
import uz.model.Restaurant;

import java.util.List;

/**
 * Created by Admin on 15.02.2017.
 */
public interface RestaurantService {
    @Secured("ROLE_ADMIN")
    void delete(int id);

    List<Restaurant> getAll();

    @Secured("ROLE_ADMIN")
    Restaurant create(Restaurant restaurant);

    @Secured("ROLE_ADMIN")
    Restaurant update(Restaurant restaurant);

    Restaurant get(Integer id);
}
