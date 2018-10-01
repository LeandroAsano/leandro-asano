package classes;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BlogApp3Test {



    @Test
    public void showTagsTest(){
        BlogApp3 blog = Mockito.mock(BlogApp3.class);
        HashMap<Integer,String> tags = new HashMap<>();
        tags.put(1,"Java");
        tags.put(2,"Hola");
        String expected = "1-#Java\n2-#Hola\n";
        String result =blog.showTags(tags);
        assertEquals(expected,result);
    }

    @Test
    public void errorTypeTest(){
        BlogApp3 blog = Mockito.mock(BlogApp3.class);
        List<User> userlist = new ArrayList<>();

        blog.reutilizableAddUser(userlist);
    }


}