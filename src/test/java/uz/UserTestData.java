package uz;

import uz.matcher.ModelMatcher;
import uz.model.Role;
import uz.model.User;

import java.util.Objects;

import static uz.model.BaseEntity.START_SEQ;

/**
 * Created by Admin on 20.02.2017.
 */
public class UserTestData {
    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 1;

    public static final User USER = new User(USER_ID, "User", "user@yandex.ru", "password", Role.USER);
    public static final User ADMIN = new User(ADMIN_ID, "Admin", "admin@gmail.com", "admin", Role.ADMIN, Role.USER);

    public static final ModelMatcher<User> MATCHER = ModelMatcher.of(User.class,
            (expected, actual) -> expected == actual ||
                    Objects.equals(expected.getId(), actual.getId())
                            && Objects.equals(expected.getName(), actual.getName())
                            && Objects.equals(expected.getEmail(), actual.getEmail())
                            && Objects.equals(expected.isEnabled(), actual.isEnabled())
                            && Objects.equals(expected.getRoles(), actual.getRoles())
                    );
}
