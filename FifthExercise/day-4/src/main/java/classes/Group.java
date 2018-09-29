package classes;

import java.util.ArrayList;
import java.util.List;

public class Group {
    private String name;
    private List<String> subscribers;

    public Group(String name) {
        this.name = name;
        this.subscribers = new ArrayList<>();
    }

    public void addGroup(List<Group> groups, String user, Group newgroup){
        newgroup.setSubscriber(user);          //set the user as part of the subs of the group
        groups.add(newgroup);                       //add the group at the list of groups
        System.out.println("Group Added");
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

    public void setSubscriber(String subscriber) {
        this.subscribers.add(subscriber);
    }
}
