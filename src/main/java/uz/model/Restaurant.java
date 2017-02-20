package uz.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Admin on 10.02.2017.
 */
@Entity
@Table(name = "restaurants")
public class Restaurant extends NamedEntity {

    @OneToMany(mappedBy = "restaurant")
    private List<Menu> menus;

    public List<Menu> getMenus() {
        return menus;
    }

    public Restaurant() {
    }

    public Restaurant(String name) {
        super(name);
    }

    public Restaurant(Integer id, String name) {
        super(id, name);
    }

    public Restaurant(Restaurant r) {
        this(r.getId(), r.getName());
    }
}
