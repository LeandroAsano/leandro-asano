package classes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void addUserlistTest() {
        List<User> userlist = new ArrayList<>();
        User newuser = new User("Leo");
        userlist.add(newuser);
        Assertions.assertNotNull(userlist);
    }

    @Test
    public void subscribersTest(){
        String subscriber = "Ale";
        User user1 = new User("Ale");

        List<String> subscribersExpected = new ArrayList<>();
        subscribersExpected.add(subscriber);

        user1.setSubscribers(subscriber);
        List<String> subscriberResult = user1.getSubscribers();

        Assertions.assertEquals(subscribersExpected,subscriberResult);
    }

}