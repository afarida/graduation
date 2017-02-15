package service;

import model.Vote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.VoteRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by Admin on 15.02.2017.
 */
@Service
public class VoteServiceImpl implements VoteService {
    @Autowired
    private VoteRepository repository;

    @Override
    public boolean delete(Date date, int user_id) {
        return repository.delete(date, user_id) != 0;
    }

    @Override
    public List<Vote> findAll() {
        return repository.findAll();
    }

    @Override
    public Vote save(Vote vote) {
        return repository.save(vote);
    }

    @Override
    public Vote findOne(Integer id) {
        return repository.findOne(id);
    }
}
