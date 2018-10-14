package classes;

import java.time.LocalDate;

public class Entry {

    private User user;
    private String title;
    private String text;
    private LocalDate date;
    private String tag;


    public Entry(String user, String title, String text, LocalDate date, String tag) {
        this.user = new User(user);
        this.title = title;
        this.text = text;
        this.date = date;
        this.tag = tag;
    }

    public String getUser() {
        return user.getName();
    }

    public void setUser(String user) {
        this.user.setName(user);
    }

    public String getEntry(){
        return " owner: "+this.user.getName()+" \n title: "+title+"\n text: \n"+text+" \n date: "+date.toString()+" \n tags: "+tag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
