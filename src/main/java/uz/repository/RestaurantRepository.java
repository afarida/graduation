package uz.repository;

import uz.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Admin on 13.02.2017.
 */
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
    @Transactional
    @Modifying
    @Query("DELETE from Restaurant r where r.id=?1")
    int delete(int id);

    @Query("SELECT r from Restaurant r Order By r.name")
    List<Restaurant> getAll();
}
