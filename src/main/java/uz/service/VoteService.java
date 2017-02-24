package uz.service;

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

    List<Vote> getAll();

    List<Vote> getAllByDate(Date date);

    Vote create(Vote vote, int userId);

    Vote update(Vote vote, int userId);

    Vote get(Integer id, int userId);
}
