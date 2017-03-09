package uz.model;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by Admin on 10.02.2017.
 */
public enum Role implements GrantedAuthority {
    ROLE_ADMIN,
    ROLE_USER;

    @Override
    public String getAuthority() {
        return name();
    }
}
