package uz.web.vote;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.model.Vote;

import java.util.Date;
import java.util.List;

/**
 * Created by Admin on 24.02.2017.
 */
@RestController
@RequestMapping(AdminVoteController.REST_URL)
public class AdminVoteController extends AbstractVoteController {
    static final String REST_URL = "/admin/votes";

    @GetMapping(value = "/{date}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<Vote> getAllByDate(@PathVariable Date date) {
        return super.getAllByDate(date);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    List<Vote> getAll() {
        return super.getAll();
    }
}
