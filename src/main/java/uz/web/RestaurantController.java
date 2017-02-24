package uz.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import uz.model.Restaurant;
import uz.service.RestaurantService;

import java.net.URI;
import java.util.List;

/**
 * Created by Admin on 23.02.2017.
 */
@RestController
public class RestaurantController {
    private static final String REST_ADMIN_URL = "/admin/restaurants";
    private static final String REST_URL = "/restaurants";

    private static final Logger LOG = LoggerFactory.getLogger(RestaurantController.class);

    @Autowired
    private RestaurantService service;

    @DeleteMapping(REST_ADMIN_URL + "/{id}")
    void delete(@PathVariable("id") int id) {
        LOG.info("delete " + id);
        service.delete(id);
    }

    @GetMapping(value = {REST_ADMIN_URL, REST_URL}, produces = MediaType.APPLICATION_JSON_VALUE)
    List<Restaurant> getAll() {
        LOG.info("getAll");
        return service.getAll();
    }

    @PostMapping(value = REST_ADMIN_URL, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Restaurant> create(@RequestBody Restaurant restaurant) {
        LOG.info("create " + restaurant);

        Restaurant created = service.create(restaurant);

        URI newUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_ADMIN_URL + "/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(newUri);

        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping(value = REST_ADMIN_URL, consumes = MediaType.APPLICATION_JSON_VALUE)
    Restaurant update(@RequestBody Restaurant restaurant) {
        LOG.info("update " + restaurant);
        return service.create(restaurant);
    }

    @GetMapping(value = {REST_ADMIN_URL + "/{id}", REST_URL + "/{id}"}, produces = MediaType.APPLICATION_JSON_VALUE)
    Restaurant get(@PathVariable Integer id) {
        LOG.info("get " + id);
        return service.get(id);
    }
}
