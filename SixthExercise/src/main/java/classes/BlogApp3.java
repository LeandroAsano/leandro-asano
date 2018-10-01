package classes;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class BlogApp3 {

    public static class SortbyDate implements Comparator<Entry> {
        public int compare(Entry a, Entry b)
        {
            return Period.between(LocalDate.now(),a.getDate()).getDays() - Period.between(LocalDate.now(),b.getDate()).getDays();
        }
    }

    public static class SortbyTitle implements Comparator<Entry> {
        public int compare(Entry a, Entry b)
        {
            return a.getTitle().compareTo(b.getTitle());
        }
    }

    public static class SortbyLikes implements Comparator<Entry> {
        public int compare(Entry a, Entry b)
        {
            return b.getLikes()-(a.getLikes());
        }
    }

    //this method is user for show all tags at the moment, and is used for newEntry, defineTags and searchEntry
    public static String showTags(HashMap<Integer,String> tagNames){
        String out="";
        if (tagNames.isEmpty()){
            return "No tags at the moment";
        }else {
            for (Map.Entry<Integer, String> entry : tagNames.entrySet()) {
                Integer key = entry.getKey();
                String value = entry.getValue();
                out = out.concat(key + "-#" + value + "\n");
            }
            return  out;
        }
    }



    //Method for the differents searchs
    public static void searchEntry(List<Entry> entries, HashMap<Integer,String> tagNames, int choice){
        Scanner input = new Scanner(System.in);
        String search;
        switch(choice){
            case 1: { //case of the search by tagName
                showTags(tagNames);
                search = tagNames.get(input.nextInt());
                entries.stream().filter(p -> p.getTag().contains(search)).forEach(e -> System.out.println(e.getEntry()));

            }break;
            case 2: {   //case of search by Text
                String text;
                System.out.println("enter text");
                input.nextLine();
                text = input.nextLine();
                entries.stream().filter(p -> p.getText().contains(text)).forEach(e -> System.out.println(e.getEntry()));
            } break;
            case 3: {  //case of search by User
                System.out.println("enter owner");
                input.nextLine();
                String owner = input.nextLine();
                entries.stream().filter(p -> p.getUser().contains(owner)).forEach(e -> System.out.println(e.getEntry()));
            }break;
            case 4: {   //case of search between dates
                String str;
                String str2;

                System.out.println("enter date base yyyy-mm-dd");
                str = input.next();
                System.out.println("enter date limit yyyy-mm-dd");
                str2 = input.next();

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");    //this line define the formatter of the date

                LocalDate dateTimeBase = LocalDate.parse(str, formatter);
                LocalDate dateTimeEnd = LocalDate.parse(str2, formatter);

                //this stream verify all the dates between dates entered
                entries.stream().filter(e -> e.getDate().isAfter(dateTimeBase)
                        && e.getDate().isBefore(dateTimeEnd)).
                        forEach(e -> System.out.println(e.getEntry()));
            }
        }
    }

    //this method made the input can be only numeric
    public static int controlErrorType(){
        Scanner input = new Scanner(System.in);
        int option;

        while (!input.hasNextInt()) {
            System.out.println("Only numeric values");
            input.next();
        }
        option = input.nextInt();

        return option;
    }

    //This is a method that delete an entry selected
    public static void deleteEntry(List<Entry> entries){
        Scanner input = new Scanner(System.in);
        //initialize
        int cont=1;
        int choice;

        //check entries list are not empty
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
            } while (choice < 1 || choice >= cont);
            //this try catch if for control of the existence of choice
            try {
                entries.remove(choice - 1);
                System.out.println("Entry Removed Successfully!");
            } catch (InputMismatchException e ) {
                System.out.println("Error of input match");
            }
        }
    }

    //this method show the entries ordered and reverse it that is the choice of user
    public static void showPostsEstructure(List<Entry> entries){
        int choice = controlErrorType();
        if (choice==1){
            entries.forEach(entry -> System.out.println(entry.getEntry()));
        }else{
            if (choice==2){
                Collections.reverse(entries);
                entries.forEach(entry -> System.out.println(entry.getEntry()));
            }else{
                System.out.println("Invalid Number");
            }
        }
    }

    //This method is for show the post ordered by date, title or configurable number
    public static void showPosts(List<Entry> entries) {
        int option;
        System.out.println("Show posts by: \n 1-Date \n 2-Title \n 3-Specific number \n 4-All Posts \n 5-3 Most Likes");
        option = controlErrorType();
        switch (option) {
            case 1:{
                System.out.println("1-By Newest \n2-By Oldest");
                entries.sort(new SortbyDate()); //sort list "entries" by date
                showPostsEstructure(entries);
            }break;
            case 2:{
                System.out.println("1-By Ascending \n2-By Descending");
                entries.sort(new SortbyTitle()); //sort list "entries" by title
                showPostsEstructure(entries);
            }break;
            case 3: {    //shows an configurable quantity of entries
                System.out.println("Introduce number");
                int number= controlErrorType();

                for (int i = 0; i < number ; i++) {
                    //this try catch allow show "Empty" if not exists "number" entries now.
                    try {
                        System.out.println((i+1)+"-"+entries.get(i).getEntry());
                    } catch (IndexOutOfBoundsException e){
                        System.out.println((i+1)+"- Empty");
                    }
                }
            }break;
            case 4:{            //only shows all the entries
                entries.forEach(entry -> System.out.println(entry.getEntry()));
            }break;
            case 5:{        //shows 3 most likes posts
                entries.sort(new SortbyLikes());
                for (int i = 0; i < 3; i++) {
                    System.out.println(entries.get(i).getEntry());
                }
            }
            default: {
                System.out.println("invalid number");
            }break;
        }
    }

    //This is a part of code that is used 3 times in the main
    public static String reutilizableAddUser(List<User> userlist){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter name");
        String name = input.nextLine();
        User newuser = new User(name); //create user
        newuser.addUser(name ,userlist, newuser); //call of adduser method
        return name;
    }

    //METODO MAIN
    public static void main(String [] args) throws IOException {
        //initialize variables
        Entry newent;
        Scanner input = new Scanner(System.in);
        int option;
        int cont=0;
        String user;

        //instance of the HashMap and List Collections
        List<User> userlist = new ArrayList<>();
        user=reutilizableAddUser(userlist);
        HashMap<Integer,String> tagNames = new HashMap<>();
        List<Entry> entries = new ArrayList<>();
        List<Group> groups = new ArrayList<>();
        //This "do" executes it selfs while option are distinct of "Exit"
        do {
            System.out.println("Current User : "+user);
            System.out.println("WELCOME TO DEVSBLOG \n 1-New Entry \n 2-Delete Entry \n 3-Show 10 Recent Entries \n 4-Define tags");
            System.out.println(" 5-Search an specified Entry \n 6-Groups Options \n 7-User Options \n 8-Subscribe User \n 9-ShowPosts \n 10-Like a post");
            System.out.println("0-Exit");
            //control of valid number
            do {
                option = controlErrorType();
            }while (option < 0 || option > 10 );

            switch (option) {
                case 1: {
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
                    System.out.println(showTags(tagNames));
                    System.out.println("0-Finish");
                    tagIn=controlErrorType();

                    //this cicle works while the user keeps selecting tags
                    while (tagIn!=0){
                        tags = tags.concat(" #"+tagNames.get(tagIn)); //this concat the tags selected by the user
                        tagIn=input.nextInt();
                    }
                    newent = new Entry(user,title,text,tags);
                    newent.addEntry(entries, userlist, newent);
                }
                break;


                case 2: {
                    deleteEntry(entries);
                }
                break;


                //    CASE 3 - SHOW THE TEN MOST RECENT ENTRIES
                case 3: {
                    //show the entries
                    for (int i = 0; i < 10 ; i++) {
                        //this try catch allow show "Empty" if not exists 10 entries now.
                        try {
                            System.out.println((i+1)+"-"+entries.get(i).getEntry());
                        } catch (IndexOutOfBoundsException e){
                            System.out.println((i+1)+"- Empty");
                        }
                    }

                }break;


                case 4: {   //DEFINE A NEW TAG
                    String tagName;
                    //input of variables
                    System.out.println(showTags(tagNames));
                    System.out.println();
                    System.out.println(" Enter tagName: ");
                    tagName=input.next();
                    tagNames.put((cont+1),tagName); //put of the tag in the HashMap
                    System.out.println("TagName Registered!");
                    cont++;
                }break;


                case 5:{  //SEARCH OF AN SPECIFIED ENTRY
                    int choice;
                    System.out.println("Which search you want?");
                    System.out.println(" 1-By tagname \n 2-By text \n 3-By posting user \n 4-Between dates");
                    choice=input.nextInt();
                    searchEntry(entries,tagNames,choice);
                }break;


                case 6: {   //GROUP OPTIONS
                    int groupoption;
                    //enter of the option
                    System.out.println("Group Options \n 1-Add Group \n 2-Subscribe to Group");
                    groupoption = input.nextInt();
                    switch (groupoption) {
                        case 1: {    //case of add new group
                            System.out.println("Enter name group");
                            input.nextLine();
                            String name = input.nextLine();
                            Group newgroup = new Group(name);  //creat new group
                            newgroup.addGroup(groups, user, newgroup);   //add the group at the list of groups
                        }
                        break;
                        case 2: {      //case of subscribe to a group
                            groups.forEach(e -> System.out.println("GroupName:" + e.getName() + "\nSubs:" + e.getSubscribers()));
                            System.out.println("Which group?");
                            String groupchoice;
                            input.nextLine();
                            groupchoice = input.nextLine();
                            for (Group p : groups) {
                                if (p.getName().contains(groupchoice)) {
                                    p.setSubscriber(user);
                                }
                            }
                        }break;
                    }
                }break;


                case 7:{        //USER OPTIONS
                    int choice;
                    System.out.println("1-Log in \n2-Add user");
                    //this do controls the validating types entered
                    do {
                        choice = controlErrorType();
                    }while (choice < 1 || choice > 2 );

                    //select choice
                    switch (choice){
                        case 1:{
                            //if there is no exist current users ask to create one
                            if (userlist.isEmpty()){
                                System.out.println("No users existents, add new");
                                user=reutilizableAddUser(userlist);
                            }else {
                                //Log as existent user
                                userlist.forEach(p -> System.out.println(p.getName()));         //this for-each show the list of users existents
                                System.out.println("Enter name");
                                String name = input.nextLine();
                                //this stream search the name of user to switch as him
                                for (User p:userlist) {
                                    if (p.getName().contains(name)){
                                        user=p.getName();
                                    }

                                }
                                for (int i = 0; i < userlist.size(); i++) {
                                    if (userlist.get(i).getName().contains(name)) {
                                        user=name;
                                    }
                                }
                            }
                        }break;
                        case 2:{    //when just want add a new user
                            user=reutilizableAddUser(userlist);
                        }
                    }
                }break;


                case 8:{        //SUBSCRIBE TO AN REGISTERED USER

                    AtomicBoolean founded= new AtomicBoolean(false);
                    userlist.forEach(userRegistered -> System.out.println(userRegistered.getName()));  //show all users registered at the moment

                    System.out.println("Enter name");
                    String choice = input.nextLine();       //select the user to subscribe

                    //thiscompares if the user registered is equals to the selected
                    for (User userRegistered: userlist) {
                        if (userRegistered.getName().equals(choice)){
                            userRegistered.setSubscribers(user);
                        }
                    }

                    if (founded.get()){
                        System.out.println("Subscription successfully!");
                    }else{
                        System.out.println("Error user no exists");
                    }
                }break;


                case 9:{
                    showPosts(entries);
                }break;

                case 10:{
                    String titlename;
                    showPosts(entries);
                    System.out.println("Enter name of the post");
                    titlename = input.nextLine();
                    for (Entry p: entries) {
                        if (p.getTitle().contains(titlename)){
                            System.out.println(p.likeaPost());
                        }
                    }
                }

            }
        }while(option!=0);
    }
}