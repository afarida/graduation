package model;

/**
 * Created by Admin on 11.02.2017.
 */
public class BaseEntity {
    protected Integer id;

    public BaseEntity() {
    }

    public BaseEntity(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isNew(){
        return (this.id == null);
    }
}
