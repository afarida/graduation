package uz.repository;

import uz.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

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

    List<Menu> getByDate(Date date);

    List<Menu> getByDateAndRestaurant_Id(Date date, int restaurant_id);
}
