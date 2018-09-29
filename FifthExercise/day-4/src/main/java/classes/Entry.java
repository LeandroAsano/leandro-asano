package classes;

import java.io.*;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class Entry {

    private User user;
    private String title;
    private String text;
    private LocalDate date;
    private String tag;

    public Entry(String user, String title, String text, String tag) {
        this.user = new User(user);
        this.title = title;
        this.text = text;
        this.date = LocalDate.now();
        this.tag = tag;
    }

    //This method post new entries for the blog
    public String addEntry(List<Entry> entries, List<User> userList, Entry ent){
        entries.add(ent);                           //Charge of entry to the List
        System.out.println(emailPost(ent,userList));    //Call of email method
        return "Entry Successfully!";
    }

    //This method sends the email posts for the subscribers
    public Object emailPost(Entry ent, List<User> userList) {
        AtomicReference<String> message = null;
        //this for-each is for each users
        userList.forEach(user -> {

            //this for-each is for each subscriber
            user.getSubscribers().forEach(subscriber -> {
                File newFile = new File(subscriber+".txt");

                //verify if the file already exists
                if (newFile.exists()){
                    message.set("Email for " + subscriber + " already send!");
                }else{
                    try {
                        newFile.createNewFile();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    message.set("Email to "+subscriber+" was send!");
                }

                //definition of file variables
                PrintWriter printWriter = null;
                try {
                    printWriter = new PrintWriter(new BufferedWriter(new FileWriter(newFile, true)));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                //this print write the entry in the files
                printWriter.println(ent.getEntry()+"\n");
                printWriter.close();
            });
        });
        return message;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getUser() {
        return user.getName();
    }


    public String getEntry(){
        return " owner: "+this.user.getName()+" \n title: "+title+"\n text: \n"+text+" \n date: "+date.toString()+" \n tags: "+tag;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }


    public LocalDate getDate() {
        return date;
    }

    public String getTag() {
        return tag;
    }

}
