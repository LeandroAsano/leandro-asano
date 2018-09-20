package classes;

import java.time.LocalDate;

public class Entry {

    private String title;
    private String text;
    private LocalDate date;
    private String tag;

    public Entry(String title, String text, LocalDate date, String tag) {
        this.title = title;
        this.text = text;
        this.date = date;
        this.tag = tag;
    }

    public String getEntry(){
        return "title: "+title+"\n text: \n"+text+" \n date: "+date.toString()+" \n tags: "+tag;
    }
}
