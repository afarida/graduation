package model;

import java.util.Date;

/**
 * Created by Admin on 10.02.2017.
 */
public class Vote extends BaseEntity {

    private boolean vote;

    private Date date;

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
}
