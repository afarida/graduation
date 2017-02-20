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

    public Vote() {
    }

    public Vote(boolean vote, Date date) {
        this.vote = vote;
        this.date = date;
    }
}
