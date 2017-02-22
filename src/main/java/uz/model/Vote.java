package uz.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Admin on 10.02.2017.
 */
@Entity
@Table(name = "votes")
public class Vote extends BaseEntity {

    @Column(name = "vote", nullable = false)
    @NotEmpty
    private boolean vote;

    @Column(name = "date", nullable = false)
    @NotEmpty
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    public boolean isVote() {
        return vote;
    }

    public void setVote(boolean vote) {
        this.vote = vote;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Vote() {
    }

    public Vote(Vote vote) {
        this(vote.getId(), vote.isVote(), vote.getDate(),vote.getUser(), vote.getRestaurant());
    }

    public Vote(Integer id, boolean vote, Date date, User user, Restaurant restaurant) {
        super(id);
        this.vote = vote;
        this.date = date;
        this.user = user;
        this.restaurant = restaurant;
    }
}
