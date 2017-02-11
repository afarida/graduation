package model;

import java.util.Date;

/**
 * Created by Admin on 10.02.2017.
 */
public class Menu extends NamedEntity {

    private int price;

    private Date date = new Date();

    private Restaurant restaurant;

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
        return name + " {" +
                "restaurant=" + restaurant +
                ", date=" + date +
                ", price=" + price +
                '}';
    }
}
