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
import uz.model.Menu;
import uz.service.MenuService;

import java.net.URI;
import java.util.Date;
import java.util.List;

/**
 * Created by Admin on 23.02.2017.
 */
@RestController
public class MenuController {
    private static final String REST_ADMIN_URL = "/admin/menus";
    private static final String REST_URL = "/menus";

    private static final Logger LOG = LoggerFactory.getLogger(MenuController.class);

    @Autowired
    private MenuService service;

    @DeleteMapping(value = REST_ADMIN_URL + "/{id}")
    void delete(@PathVariable int id) {
        LOG.info("delete " + id);
        service.delete(id);
    }

    @GetMapping(value = {REST_ADMIN_URL, REST_URL}, produces = MediaType.APPLICATION_JSON_VALUE)
    List<Menu> getAll() {
        LOG.info("getAll");
        return service.getAll();
    }

    @PostMapping(value = REST_ADMIN_URL, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Menu> create(@RequestBody Menu menu) {
        LOG.info("create " + menu);
        Menu created = service.create(menu);

        URI newUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_ADMIN_URL + "/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(newUri);

        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping(value = REST_ADMIN_URL, consumes = MediaType.APPLICATION_JSON_VALUE)
    Menu update(@PathVariable Menu menu) {
        LOG.info("update " + menu);
        return service.update(menu);
    }

    @GetMapping(value = {REST_ADMIN_URL + "/{id}", REST_URL + "/{id}"}, produces = MediaType.APPLICATION_JSON_VALUE)
    Menu get(@PathVariable int id) {
        LOG.info("get " + id);
        return service.get(id);
    }

    @GetMapping(value = {REST_ADMIN_URL + "/by", REST_URL + "/by"}, produces = MediaType.APPLICATION_JSON_VALUE)
    List<Menu> getByDateAndRestaurant(@RequestParam("date") Date date, @RequestParam("restaurantId") Integer restaurantId) {
        LOG.info("getByDateAndRestaurant by Date {} and Restaurant {}", date, restaurantId);
        if (date == null) {
            return service.getByRestaurant(restaurantId);
        } else if (restaurantId == null)
            return service.getByDate(date);
        return service.getByDateAndRestaurant(date, restaurantId);
    }
}
