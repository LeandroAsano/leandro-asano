package classes;

import java.time.LocalDate;
import java.time.Period;
import java.util.Comparator;

public class SortbyDate implements Comparator<Entry> {

    public int compare(Entry a, Entry b)
    {
        return Period.between(LocalDate.now(),a.getDate()).getDays() - Period.between(LocalDate.now(),b.getDate()).getDays();
    }
}


