package uz.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Admin on 10.02.2017.
 */
@Entity
@Table(name = "menus")
public class Menu extends BaseEntity {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");

    @Column(name = "dish", nullable = false)
    @NotEmpty
    private String dish;

    @Column(name = "price", nullable = false)
    @NotEmpty
    private int price;

    @Column(name = "date", nullable = false)
    @Temporal(TemporalType.DATE)
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

    public Menu() {
    }

    public Menu(Integer id, String dish, int price, Date date, Restaurant restaurant) {
        super(id);
        this.dish = dish;
        this.price = price;
        this.date = date;
        this.restaurant = restaurant;
    }

    public Menu(Menu menu){
        this(menu.getId(), menu.getDish(), menu.getPrice(), menu.getDate(), menu.getRestaurant());
    }

    @Override
    public String toString() {
        return dish + " {" +
                "restaurant=" + restaurant +
                ", date=" + sdf.format(date) +
                ", price=" + price +
                '}';
    }
}
