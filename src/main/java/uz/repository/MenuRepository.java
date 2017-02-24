package uz.repository;

import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.query.Param;
import uz.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

/**
 * Created by Admin on 13.02.2017.
 */
public interface MenuRepository extends JpaRepository<Menu, Integer>{
    @Transactional
    @Modifying
    @Query("DELETE from Menu m where m.id=?1")
    int delete(int id);

    @Query("SELECT m from Menu m order by m.date, m.restaurant.name, m.dish")
    List<Menu> getAll();

    @Query("SELECT m from Menu m where m.date=?1 order by m.restaurant.name, m.dish")
    List<Menu> getByDate(Date date);

    @Query("SELECT m from Menu m where m.restaurant.id=?1 order by m.date, m.dish")
    List<Menu> getByRestaurantId(int restaurantId);

    @Query("SELECT m from Menu m where m.date=?1 and m.restaurant.id=?2 order by m.dish")
    List<Menu> getByDateAndRestaurantId(Date date, int restaurantId);
}
