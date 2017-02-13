package service;

import model.Vote;
import org.springframework.stereotype.Service;
import util.exception.NotFoundException;

/**
 * Created by Admin on 13.02.2017.
 */
@Service
public class VoteServiceImpl implements VoteService {
    @Override
    public Vote get(int id) throws NotFoundException {
        return null;
    }

    @Override
    public Vote save(Vote vote) {
        return null;
    }
}
