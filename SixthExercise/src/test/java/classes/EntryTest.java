package classes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class EntryTest {

    private Entry entry;


    @Test
    public void likePostTest(){
        String user = "Leo";
        String title = "Title1";
        String text = "Text1";
        String tag = "Java";

        entry = new Entry(user,title,text,tag);
        String outExpected = "Like success! Likes of this post: "+1;
        String outResult = entry.likeaPost();
        Assertions.assertEquals(outExpected,outResult);
    }

    @Test
    public void addEntryTest() {
        Entry entry = mock(Entry.class);
        List<Entry> entries = new ArrayList<>();
        List<User> userlist = new ArrayList<>();
        when(entry.addEntry(entries,userlist,entry)).thenReturn("Entry Successfully!");
        entry.addEntry(entries,userlist,entry);
        verify(entry).addEntry(entries,userlist,entry);
    }

    @Test
    public void getEntryTest(){
        String user = "Leo";
        String title = "Title1";
        String text = "Text1";
        String tag = "Java";
        entry = new Entry(user,title,text,tag);
        String expected= " owner: "+entry.getUser()+" \n title: "+entry.getTitle()+"\n text: \n"+entry.getText()+" \n date: "+entry.getDate().toString()+" \n tags: "+entry.getTag();
        String result= entry.getEntry();
        Assertions.assertEquals(expected,result);
    }

    @Test
    void emailPostTest() {
        Entry entry = mock(Entry.class);
        List<User> userlist = new ArrayList<>();
        when(entry.emailPost(entry,userlist)).thenReturn("Email send! to");
        entry.emailPost(entry,userlist);
        verify(entry).emailPost(entry,userlist);
    }

}