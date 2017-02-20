package uz.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * Created by Admin on 11.02.2017.
 */
@MappedSuperclass
public class NamedEntity extends BaseEntity {
    @NotEmpty
    @Column(name = "name", nullable = false)
    protected String name;

    public NamedEntity() {
    }

    public NamedEntity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
