package uz.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import uz.AuthorizedUser;
import uz.model.Role;
import uz.model.Vote;
import uz.service.VoteService;

import java.net.URI;
import java.util.Date;
import java.util.List;

/**
 * Created by Admin on 23.02.2017.
 */
@RestController
@RequestMapping(VoteController.REST_URL)
public class VoteController {
    private static final Logger LOG = LoggerFactory.getLogger(VoteController.class);

    static final String REST_URL = "/votes";

    @Autowired
    private VoteService service;

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable int id) {
        LOG.info("delete {} for User {}", id, AuthorizedUser.id());
        service.delete(id, AuthorizedUser.id());
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Vote> getAll() {
        if (AuthorizedUser.get().getUserTo().getRoles().contains(Role.ROLE_ADMIN)){
            LOG.info("getAll");
            return service.getAll();
        }

        LOG.info("getAll for User {}", AuthorizedUser.id());
        return service.getAll(AuthorizedUser.id());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vote> create(@RequestBody Vote vote) {
        LOG.info("create {} for User {}", vote, AuthorizedUser.id());
        Vote created = service.create(vote, AuthorizedUser.id());

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
        LOG.info("update {} for User {}", vote, AuthorizedUser.id());
        return service.update(vote, AuthorizedUser.id());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Vote get(@PathVariable int id) {
        LOG.info("get {} for User {}", id, AuthorizedUser.id());
        return service.get(id, AuthorizedUser.id());
    }

    @GetMapping(value = "/by", produces = MediaType.APPLICATION_JSON_VALUE)
    List<Vote> getAllByDate(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date) {
        LOG.info("getAllByDate");
        return service.getAllByDate(date);
    }
}
