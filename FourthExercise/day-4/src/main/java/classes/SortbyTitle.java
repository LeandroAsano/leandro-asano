package classes;

import java.util.Comparator;

public class SortbyTitle implements Comparator<Entry> {

    public int compare(Entry a, Entry b)
    {
        return a.getTitle().compareTo(b.getTitle());
    }
}
