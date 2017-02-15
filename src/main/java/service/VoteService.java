package service;

import model.Vote;

import java.util.Date;
import java.util.List;

/**
 * Created by Admin on 15.02.2017.
 */
public interface VoteService {
    boolean delete(Date date, int user_id);

    List<Vote> findAll();

    Vote save(Vote vote);

    Vote findOne(Integer id);
}
