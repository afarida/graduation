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

    List<Vote> findAll(int userId);

    Vote save(Vote vote, int userId);

    Vote update(Vote vote, int userId);

    Vote findOne(Integer id, int userId);
}
