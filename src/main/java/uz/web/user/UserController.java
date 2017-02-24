package uz.web.user;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import uz.AuthorizedUser;
import uz.model.User;

/**
 * Created by Admin on 23.02.2017.
 */
@RestController
@RequestMapping(UserController.REST_URL)
public class UserController extends AbstractUserController {
    static final String REST_URL = "/profile";

    @DeleteMapping
    public void delete() {
        super.delete(AuthorizedUser.id());
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public User update(@RequestBody User user) {
        user.setId(AuthorizedUser.id());
        return super.update(user);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public User get() {
        return super.get(AuthorizedUser.id());
    }
}
