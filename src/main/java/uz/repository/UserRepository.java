package uz.repository;

import uz.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Admin on 13.02.2017.
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    @Transactional
    @Modifying
    @Query("DELETE from User u where u.id=?1")
    int delete(int id);

    User findByEmail(String email);

    @Query("SELECT u from User u Order By u.name, u.email")
    List<User> getAll();
}
