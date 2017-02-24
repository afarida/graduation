package uz.web.vote;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import uz.AuthorizedUser;
import uz.model.Vote;

import java.net.URI;
import java.util.List;

/**
 * Created by Admin on 23.02.2017.
 */
@RestController
@RequestMapping(VoteController.REST_URL)
public class VoteController extends AbstractVoteController{
    static final String REST_URL = "/votes";

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable int id) {
        super.delete(id, AuthorizedUser.id());
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Vote> getAll() {
        return super.getAll(AuthorizedUser.id());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vote> create(@RequestBody Vote vote) {
        Vote created = super.create(vote, AuthorizedUser.id());

        URI newUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "{id}")
                .buildAndExpand(created.getId())
                .toUri();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(newUri);

        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Vote update(@RequestBody Vote vote) {
        return super.update(vote, AuthorizedUser.id());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Vote get(@PathVariable int id) {
        return super.get(id, AuthorizedUser.id());
    }
}
