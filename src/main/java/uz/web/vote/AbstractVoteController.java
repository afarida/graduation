package uz.web.vote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import uz.model.Vote;
import uz.service.VoteService;

import java.util.Date;
import java.util.List;

/**
 * Created by Admin on 24.02.2017.
 */
public abstract class AbstractVoteController {
    private static final Logger LOG = LoggerFactory.getLogger(AbstractVoteController.class);

    @Autowired
    private VoteService service;

    void delete(int id, int userId){
        LOG.info("delete {} for User {}", id, userId);
        service.delete(id, userId);
    }

    List<Vote> getAll(int userId){
        LOG.info("getAll for User {}", userId);
        return service.getAll(userId);
    }

    List<Vote> getAll(){
        LOG.info("getAll");
        return service.getAll();
    }

    List<Vote> getAllByDate(Date date){
        LOG.info("getAllByDate");
        return service.getAllByDate(date);
    }

    Vote create(Vote vote, int userId){
        LOG.info("create {} for User {}", vote, userId);
        return service.create(vote, userId);
    }

    Vote update(Vote vote, int userId){
        LOG.info("update {} for User {}", vote, userId);
        return service.update(vote, userId);
    }

    Vote get(Integer id, int userId){
        LOG.info("get {} for User {}", id, userId);
        return service.get(id, userId);
    }
}
