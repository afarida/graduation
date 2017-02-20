package uz.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Admin on 10.02.2017.
 */
@Entity
@Table(name = "menus")
public class Menu extends BaseEntity {

    @Column(name = "dish", nullable = false)
    @NotEmpty
    private String dish;

    @Column(name = "price", nullable = false)
    @NotEmpty
    private int price;

    @Column(name = "date", nullable = false)
    @NotEmpty
    private Date date = new Date();

    @ManyToOne(fetch = FetchType.EAGER)
    private Restaurant restaurant;

    public String getDish() {
        return dish;
    }

    public void setDish(String dish) {
        this.dish = dish;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return dish + " {" +
                "restaurant=" + restaurant +
                ", date=" + date +
                ", price=" + price +
                '}';
    }
}
