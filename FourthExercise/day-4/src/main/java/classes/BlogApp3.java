package classes;

import java.io.*;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class BlogApp3 {

    //this method is user for show all tags at the moment, and is used for newEntry, defineTags and searchEntry
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
    private static void newEntry(List<Entry> entries, HashMap<Integer,String> tagNames, String actualuser, List<User> userList) throws IOException {
        Scanner input = new Scanner(System.in);
        String user;
        String title;
        String text;
        String tags = "";
        LocalDate date;
        int tagIn;

        //input of variables
        user=actualuser;
        System.out.println("Enter the title ");
        title=input.nextLine();
        System.out.println("Enter text");
        text=input.nextLine();
        System.out.println("Select tags ");
        showTags(tagNames);
        System.out.println("0-Finish");
        tagIn=controlErrorType();

        //this cicle works while the user keeps selecting tags
        while (tagIn!=0){
            tags = tags.concat(" #"+tagNames.get(tagIn)); //this concat the tags selected by the user
            tagIn=input.nextInt();
        }

        //Entry creation
        Entry ent = new Entry(user,title,text,LocalDate.now(),tags);
        entries.add(ent);                           //Charge of entry to the List
        emailPost(ent,userList);            //Call of email method
        System.out.println("Entry Successfully!");
    }

    //This method sends the email posts for the subscribers
    private static void emailPost(Entry ent, List<User> userList) {

        //this for-each is for each users
        userList.forEach(user -> {

                //this for-each is for each subscriber
            user.getSubscribers().forEach(subscriber -> {
                File newFile = new File(subscriber+".txt");

                //verify if the file already exists
                if (newFile.exists()){
                    System.out.println("Email for "+subscriber+" already send!");
                }else{
                    try {
                        newFile.createNewFile();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    System.out.println("Email to "+subscriber+" was send!");
                }

                //definition of file variables
                PrintWriter printWriter = null;
                try {
                    printWriter = new PrintWriter(new BufferedWriter(new FileWriter(newFile, true)));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                //this print write the entry in the files
                printWriter.println(ent.getEntry()+"\n");
                printWriter.close();
            });
        });
    }

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

    //Method for create tags
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

    //Method for the differents searchs
    public static void searchEntry(List<Entry> entries, HashMap<Integer,String> tagNames){
        Scanner input = new Scanner(System.in);
        int choice;
        String search;
        System.out.println("Which search you want?");
        System.out.println(" 1-By tagname \n 2-By text \n 3-By posting user \n 4-Between dates");
        choice=input.nextInt();
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

    public static void groupOption(List<Group> groups, String user){
        Scanner input = new Scanner(System.in);
        int option;

        //enter of the option
        System.out.println("Group Options \n 1-Add Group \n 2-Subscribe to Group");
        option=input.nextInt();
        switch (option){
            case 1:{    //case of add new group
                System.out.println("Enter name group");
                input.nextLine();
                String name=input.nextLine();
                List<String> subs = new ArrayList<>(); //create the subs of new group
                Group newgroup = new Group(name,subs);  //creat new group
                newgroup.setSubscribers(user);          //set the user as part of the subs of the group
                groups.add(newgroup);                   //add the group at the list of groups

            }break;
            case 2:{      //case of subscribe to a group
                groups.forEach(e -> System.out.println("GroupName:"+e.getName()+"\nSubs:"+e.getSubscribers()));
                System.out.println("Which group?");
                String groupchoice;
                input.nextLine();
                groupchoice=input.nextLine();
                //search the group and add the user at they subs
                groups.stream().filter(p -> p.getName().contains(groupchoice)).forEach(p -> p.setSubscribers(user));
            }break;
        }
    }

    //this method is called for create a new User and returns the actual user
    public static String newUser(Scanner input, List<User> userList){
        String actualuser;
        System.out.println("Enter name");
        String name = input.nextLine();
        User user = new User(name); //creat user
        userList.add(user);
        actualuser=name;    //assign the user created as actual user
        System.out.println("New User Registered! \n");
        return actualuser;
    }

    public static String userOptions (List<User> userList){
        Scanner input = new Scanner(System.in);
        int choice;
        AtomicReference<String> actualuser= new AtomicReference<>(""); //initialize actualuser variable
        System.out.println("1-Log in \n2-Add user");

        //this do controls the validating types entered
        do {
            choice = controlErrorType();
        }while (choice < 1 || choice > 2 );

        //select choice
        switch (choice){
            case 1:{
                //if there is no exist current users ask to create one
                if (userList.isEmpty()){
                    System.out.println("No users existents, add new");
                    actualuser.set(newUser(input, userList));
                }else {
                    //Log as existent user
                    userList.forEach(p -> System.out.println(p.getName()));         //this for-each show the list of users existents
                    System.out.println("Enter name");
                    input.nextLine();
                    String name = input.nextLine();

                    //this stream search the name of user to switch as him
                    userList.stream().filter(p -> p.getName().contains(name)).forEach(e -> actualuser.set(e.getName()));
                    for (int i = 0; i < userList.size(); i++) {
                        if (userList.get(i).getName().contains(name)) {
                            actualuser.set(name);
                        }
                    }
                }
            }break;
            case 2:{    //when just want add a new user
                actualuser.set(newUser(input, userList));
            }

        }
        return actualuser.get();
    }

    public static void subscribeToUser(List<User> userList, String user){
        //Initialize variables
        Scanner input = new Scanner(System.in);
        AtomicBoolean founded= new AtomicBoolean(false);

        userList.forEach(userRegistered -> System.out.println(userRegistered.getName()));  //show all users registered at the moment

        System.out.println("Enter name");
        String choice = input.nextLine();       //select the user to subscribe

        //this stream compares if the user registered is equals to the selected
        userList.stream().filter(userRegistered -> userRegistered.getName().equals(choice)).forEach(e -> {
            e.setSubscribers(user);
            founded.set(true);  //ifi this boolean is true it means its founded
        });

        if (founded.get()){
            System.out.println("Subscription successfully!");
        }else{
            System.out.println("Error user no exists");
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
        System.out.println("Show posts by: \n 1-Date \n 2-Title \n 3-Specific number \n 4-All Posts");
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
            }
            case 3: {    //shows an introducing quantity of entries
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
            default: {
                System.out.println("invalid number");
            }
        }
    }



    public static void main(String [] args) throws IOException {
        //initialize variables
        int option;
        int cont=0;
        String user;

        //instance of the HashMap and List Collections
        List<User> userlist = new ArrayList<>();
        user=userOptions(userlist);
        HashMap<Integer,String> tagNames = new HashMap<>();
        List<Entry> entries = new ArrayList<>();
        List<Group> groups = new ArrayList<>();
        //This "do" executes it selfs while option are distinct of "Exit"
        do {
            System.out.println("Current User : "+user);
            System.out.println("WELCOME TO DEVSBLOG \n 1-New Entry \n 2-Delete Entry \n 3-Show 10 Recent Entries \n 4-Define tags");
            System.out.println(" 5-Search an specified Entry \n 6-Groups Options \n 7-User Options \n 8-Subscribe User \n 9-ShowPosts \n 0-Exit");

            //control of valid number
            do {
                option = controlErrorType();
            }while (option < 0 || option > 9 );


            switch (option) {
                case 1: {
                    newEntry(entries,tagNames,user, userlist);
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
                }break;
                case 5:{
                    searchEntry(entries,tagNames);
                }break;
                case 6:{
                    groupOption(groups, user);
                }break;
                case 7:{
                    user= userOptions(userlist);
                }break;
                case 8:{
                    subscribeToUser(userlist,user);
                }break;
                case 9:{
                    showPosts(entries);
                }break;
            }

        }while(option!=0);
    }

}