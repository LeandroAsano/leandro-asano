package classes;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private List<String> subscribers;

    public User(String name) {
        this.name = name;
        this.subscribers = new ArrayList<>();
    }

    //this method is called for create a new User and returns the actual user
    public void addUser(String name, List<User> userList, User user){
        userList.add(user);//assign the user created as actual user
        System.out.println("New User Registered! \n");
    }

    public void setSubscribers(String subscriber) {
        this.subscribers.add(subscriber);
    }

    public String getName() {
        return name;
    }

    public List<String> getSubscribers() {
        return subscribers;
    }



}
