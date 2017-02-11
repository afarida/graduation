package model;

/**
 * Created by Admin on 10.02.2017.
 */
public class Vote extends BaseEntity {

    private boolean vote;

    private User user;

    public boolean isVote() {
        return vote;
    }

    public void setVote(boolean vote) {
        this.vote = vote;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
