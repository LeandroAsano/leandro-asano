package classes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BlogApp3Test {

    @Test
    public void showTagsTest() {

        BlogApp3 blog = mock(BlogApp3.class);
        HashMap<Integer, String> tagNames = new HashMap<>();
        tagNames.put(1,"Leo");
        when(blog.showTags(tagNames)).thenReturn("no tags at the moment");
        Assertions.assertNotNull(tagNames);
    }


}