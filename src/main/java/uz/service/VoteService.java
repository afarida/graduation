package uz.service;

import uz.model.User;
import uz.model.Vote;

import java.util.Date;
import java.util.List;

/**
 * Created by Admin on 15.02.2017.
 */
public interface VoteService {
    boolean delete(Date date, User user);

    List<Vote> findAll();

    Vote save(Vote vote);

    Vote findOne(Integer id);
}
