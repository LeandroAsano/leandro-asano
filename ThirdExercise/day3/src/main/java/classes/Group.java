package classes;

import java.util.List;

public class Group {
    private String name;
    private List<String> subscribers;

    public Group(String name, List<String> subscribers) {
        this.name = name;
        this.subscribers = subscribers;
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

    public void setSubscribers(String subscribers) {
        this.subscribers.add(subscribers);
    }
}
