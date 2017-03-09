package uz.service;

import org.springframework.security.access.annotation.Secured;
import uz.model.User;
import uz.model.Vote;

import java.util.Date;
import java.util.List;

/**
 * Created by Admin on 15.02.2017.
 */
public interface VoteService {
    void delete(int id, int userId);

    List<Vote> getAll(int userId);

    @Secured("ROLE_ADMIN")
    List<Vote> getAll();

    @Secured("ROLE_ADMIN")
    List<Vote> getAllByDate(Date date);

    Vote create(Vote vote, int userId);

    Vote update(Vote vote, int userId);

    Vote get(Integer id, int userId);
}
