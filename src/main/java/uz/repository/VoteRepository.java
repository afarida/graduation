package uz.repository;

import uz.model.User;
import uz.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by Admin on 13.02.2017.
 */
public interface VoteRepository extends JpaRepository<Vote, Integer> {
    @Transactional
    @Modifying
    @Query("DELETE from Vote v where v.id=?1 and v.user.id=?2")
    int delete(int id, int userId);

    @Query("SELECT v from Vote v where v.user.id=?1 order by v.date")
    List<Vote> getAll(int userId);

    @Query("SELECT v from Vote v order by v.date, v.user.name")
    List<Vote> getAll();

    @Query("SELECT v from Vote v where v.date=?1 order by v.user.name")
    List<Vote> getAllByDate(Date date);

    @Query("SELECT v from Vote v where v.id=?1 and v.user.id=?2")
    Vote findOne(Integer id, int userId);
}
