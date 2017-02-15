package repository;

import model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import util.exception.NotFoundException;

import java.util.Date;

/**
 * Created by Admin on 13.02.2017.
 */
public interface VoteRepository extends JpaRepository<Vote, Integer> {
    @Transactional
    @Modifying
    @Query("DELETE from votes v where v.date=?1 and v.user_id=?2")
    int delete(Date date, int user_id);
}
