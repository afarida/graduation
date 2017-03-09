package uz.service;

import org.springframework.util.Assert;
import uz.model.User;
import uz.model.Vote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.repository.UserRepository;
import uz.repository.VoteRepository;
import uz.util.exception.ExceptionUtil;

import java.util.Date;
import java.util.List;

/**
 * Created by Admin on 15.02.2017.
 */
@Service
public class VoteServiceImpl implements VoteService {
    @Autowired
    private VoteRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void delete(int id, int userId) {
        ExceptionUtil.checkNotFoundWithId(repository.delete(id, userId) != 0, id);
    }

    @Override
    public List<Vote> getAll(int userId) {
        return repository.getAll(userId);
    }

    @Override
    public List<Vote> getAll() {
        return repository.getAll();
    }

    @Override
    public List<Vote> getAllByDate(Date date) {
        return repository.getAllByDate(date);
    }

    @Override
    public Vote create(Vote vote, int userId) {
        Assert.notNull(vote, "vote must not be null");
        if (vote.getUser() != null && vote.getUser().getId() != userId)
            return null;
        vote.setUser(userRepository.findOne(userId));
        return repository.save(vote);
    }

    @Override
    public Vote update(Vote vote, int userId) {
        Assert.notNull(vote);
        return ExceptionUtil.checkNotFoundWithId(create(vote, userId), vote.getId());
    }

    @Override
    public Vote get(Integer id, int userId) {
        return ExceptionUtil.checkNotFoundWithId(repository.findOne(id), id);
    }
}
