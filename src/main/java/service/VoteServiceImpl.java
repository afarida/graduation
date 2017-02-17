package service;

import model.User;
import model.Vote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.VoteRepository;
import util.exception.ExceptionUtil;

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
    public boolean delete(Date date, User user) {
        boolean found = repository.delete(date, user) != 0;
        ExceptionUtil.checkNotFound(found, "date=" + date);
        return found;
    }

    @Override
    public List<Vote> findAll() {
        return repository.findAll();
    }

    @Override
    public Vote save(Vote vote) {
        if (vote.isNew()) {
            return repository.save(vote);
        }
        return repository.save(ExceptionUtil.checkNotFoundWithId(vote, vote.getId()));
    }

    @Override
    public Vote findOne(Integer id) {
        return ExceptionUtil.checkNotFoundWithId(repository.findOne(id), id);
    }
}
