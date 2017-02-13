package service;

import model.Vote;
import util.exception.NotFoundException;

import java.util.List;

/**
 * Created by Admin on 13.02.2017.
 */
public interface VoteService {
    Vote get(int id) throws NotFoundException;

    Vote save(Vote vote);
}
