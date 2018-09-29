package classes;

import java.io.*;
import java.time.LocalDate;
import java.util.List;

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
    public void addEntry(List<Entry> entries, List<User> userList, Entry ent){
        entries.add(ent);                           //Charge of entry to the List
        emailPost(ent,userList);         //Call of email method
        System.out.println("Entry Successfully!");
    }

    //This method sends the email posts for the subscribers
    public void emailPost(Entry ent, List<User> userList) {

        //this for-each is for each users
        userList.forEach(user -> {

            //this for-each is for each subscriber
            user.getSubscribers().forEach(subscriber -> {
                File newFile = new File(subscriber+".txt");

                //verify if the file already exists
                if (newFile.exists()){
                    System.out.println("Email for "+subscriber+" already send!");
                }else{
                    try {
                        newFile.createNewFile();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    System.out.println("Email to "+subscriber+" was send!");
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

    public void setTag(String tag) {
        this.tag = tag;
    }
}
