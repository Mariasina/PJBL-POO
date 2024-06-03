package Models.Entity;

public class Score {
    private long id;
    private int score_value;
    private User id_user;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public int getValue() {
        return score_value;
    }
    public void setValue(int score_value) {
        this.score_value = score_value;
    }  
    public User getIdUser() {
        return id_user;
    }
    public void setIdUser(User id_user) {
        this.id_user = id_user;
    }    
}
