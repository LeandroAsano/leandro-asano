package classes;

import java.time.LocalDate;
import java.util.*;

public class Blog {

    //this method is like a function to show the Tags existents
    public static void showTags(HashMap<Integer,String> tagNames){
        if (tagNames.isEmpty()){
            System.out.println("No tags at the moment");
        }else {
            tagNames.forEach((key,value) -> {
                System.out.println(key+"-#"+value);
            } );
        }
    }

    //This method post new entries for the blog
    private static void newEntry(List<Entry> entries, HashMap<Integer,String> tagNames){
        Scanner input = new Scanner(System.in);
        String title;
        String text;
        String tags = "";
        int tagIn;

        //input of variables
        System.out.println("Enter the title ");
        title=input.nextLine();
        System.out.println("Enter text");
        text=input.nextLine();
        System.out.println("Select tags ");
        showTags(tagNames);
        System.out.println("0-Finish");
        while (!input.hasNextInt()){
            System.out.println("Only numeric values");
            input.next();
        }
        tagIn=input.nextInt();
        //this cicle works while the user keeps selecting tags
        while (tagIn!=0){
            tags = tags.concat(" #"+tagNames.get(tagIn)); //this concat the tags selected by the user
            tagIn=input.nextInt();
        }
        //Date and List creation
        LocalDate date = LocalDate.now();
        Entry ent = new Entry(title,text,date,tags);
        entries.add(ent);                           //Charge of entry to the List

        System.out.println("Entry Successfully!");
    }

    public static void deleteEntry(List<Entry> entries){
        Scanner input = new Scanner(System.in);
        int cont=1;
        int choice;
        if (entries.isEmpty()){
            System.out.println("No entries existents");
        } else {
            System.out.println("Which entry you want delete?");
            //this for allow show the entries
            for (Entry p : entries) {
                System.out.println(cont + "-" + p.getEntry());
                cont++;
            }
            //input and control of the choice selected for delete
            do {
                choice = input.nextInt();
            } while (choice < 1 || choice > 4);
            //this try catch if for control of the existence of choice
            try {
                entries.remove(choice - 1);
                System.out.println("Entry Removed Successfully!");
            } catch (InputMismatchException e) {
                System.out.println("Error, that entry doesn't exist");
            }
        }
    }

    public static void showTenRecentEntries(List<Entry> entries){
        //show the entries
        for (int i = 0; i < 10 ; i++) {
            //this try catch allow show "Empty" if not exists 10 entries now.
           try {
               System.out.println((i+1)+"-"+entries.get(i).getEntry());
           } catch (IndexOutOfBoundsException e){
               System.out.println((i+1)+"- Empty");
           }
        }

    }

    public static void defineTags(HashMap<Integer, String> tagNames, int cont){
        Scanner input = new Scanner(System.in);
        String tagName;
        //input of variables
        showTags(tagNames);
        System.out.println();
        System.out.println(" Enter tagName: ");
        tagName=input.next();
        tagNames.put((cont+1),tagName); //put of the tag in the HashMap
        System.out.println("TagName Registered!");
    }

    public static void main(String [] args){
        Scanner input = new Scanner(System.in);
        int option=0;
        int cont=0;

        //instance of the HashMap and List Collections
        HashMap<Integer,String> tagNames = new HashMap<>();
        List<Entry> entries = new ArrayList<>();

        //This cicle executes while option are distinct of "Exit"
        do {
            System.out.println("WELCOME TO DEVSBLOG \n 1-New Entry \n 2-Delete Entry \n 3-Show 10 Recent Entries \n 4-Define tags");
            System.out.println(" 5-Exit");
            //control of valid number
            do {
                while (!input.hasNextInt()) {
                    System.out.println("Only numeric values");
                    input.next();
                }
                option = input.nextInt();
            }while (option < 1 || option > 5 );

            switch (option) {
                case 1: {
                    newEntry(entries,tagNames);
                }
                break;
                case 2: {
                    deleteEntry(entries);
                }
                break;
                case 3: {
                    showTenRecentEntries(entries);
                }break;
                case 4: {
                    defineTags(tagNames,cont);
                    cont++;
                }
            }

        }while(option!=5);
    }

}
