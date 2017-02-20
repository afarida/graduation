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
}
