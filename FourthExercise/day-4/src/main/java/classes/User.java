package classes;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private List<String> subscribers;

    public User(String name) {
        this.name = name;
        this.subscribers = subscribers = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getSubscribers() {
        return subscribers;
    }


    public void setSubscribers(String subscriber) {
        this.subscribers.add(subscriber);
    }
}
