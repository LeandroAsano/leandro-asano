package classes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GroupTest {

    @Test
    public void addGroupTest(){
        List<Group> grouplist = new ArrayList<>();
        Group newgroup = new Group("Leo");
        grouplist.add(newgroup);
        Assertions.assertNotNull(grouplist);
    }

    @Test
    public void setsubscribersTest(){
        String subscriber = "Ale";
        Group newgroup = new Group("Group1");
        List<String> subscribersExpected = new ArrayList<>();
        subscribersExpected.add(subscriber);
        newgroup.setSubscriber(subscriber);
        List<String> subscriberResult = newgroup.getSubscribers();
        Assertions.assertEquals(subscribersExpected,subscriberResult);
    }

}