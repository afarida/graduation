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
import uz.model.Menu;
import uz.service.MenuService;

import java.net.URI;
import java.util.Date;
import java.util.List;

/**
 * Created by Admin on 23.02.2017.
 */
@RestController
@RequestMapping(MenuController.REST_URL)
public class MenuController {
    public static final String REST_URL = "/menus";

    private static final Logger LOG = LoggerFactory.getLogger(MenuController.class);

    @Autowired
    private MenuService service;

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        LOG.info("delete " + id);
        service.delete(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Menu> getAll() {
        LOG.info("getAll");
        return service.getAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Menu> create(@RequestBody Menu menu) {
        LOG.info("create " + menu);
        Menu created = service.create(menu);

        URI newUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(newUri);

        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Menu update(@RequestBody Menu menu) {
        LOG.info("update " + menu);
        return service.update(menu);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Menu get(@PathVariable int id) {
        LOG.info("get " + id);
        return service.get(id);
    }

    @GetMapping(value = "/by", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Menu> getByDateAndRestaurant(@RequestParam(value = "date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date,
                                      @RequestParam(value = "restaurant", required = false) Integer restaurantId) {
        LOG.info("getByDateAndRestaurant by Date {} and Restaurant {}", date, restaurantId);
        if (date == null) {
            return service.getByRestaurant(restaurantId);
        } else if (restaurantId == null)
            return service.getByDate(date);
        return service.getByDateAndRestaurant(date, restaurantId);
    }
}
