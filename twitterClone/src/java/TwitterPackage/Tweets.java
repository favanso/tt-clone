package TwitterPackage;

import java.io.Serializable;

public class Tweets implements Serializable {

    private int id;
    private int userId;
    private String content;
    private String dateTime;
    private int likes;

    public Tweets() {
        this(0, 0, "", "", 0);
    }

    public Tweets(int id, int userId, String content, String dateTime, int likes) {
        this.id = id;
        this.userId = userId;
        this.content = content;
        this.dateTime = dateTime;
        this.likes = likes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

}
