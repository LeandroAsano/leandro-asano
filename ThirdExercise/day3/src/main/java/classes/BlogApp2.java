package classes;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class BlogApp2 {

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
        date = LocalDate.now();
        Entry ent = new Entry(user,title,text,date,tags);
        entries.add(ent);                           //Charge of entry to the List
        emailPost(ent,userList);
        System.out.println("Entry Successfully!");
    }

    private static void emailPost(Entry ent, List<User> userList) throws IOException {


        userList.forEach(p -> {

            p.getSubscribers().forEach(e -> {
                File newFile = new File(e+".txt");

                if (newFile.exists()){
                    System.out.println("Email for "+e+" already send!");
                }else{
                    try {
                        newFile.createNewFile();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    System.out.println("Email to "+e+" was send!");
                }

                FileWriter fileWriter = null;
                try {
                    fileWriter = new FileWriter(newFile, true);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                BufferedWriter buffer = new BufferedWriter(fileWriter);
                PrintWriter printWriter = new PrintWriter(buffer);



                printWriter.println(ent.getEntry()+"\n");
                printWriter.close();
            });
        });
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

    public static void searchEntry(List<Entry> entries,HashMap<Integer,String> tagNames){
        Scanner input = new Scanner(System.in);
        int choice;
        String search;
        System.out.println("Which search you want?");
        System.out.println(" 1-By tagname \n 2-By text \n 3-By posting user \n 4-Between dates");
        choice=input.nextInt();
        switch(choice){
            case 1: {
                showTags(tagNames);
                search = tagNames.get(input.nextInt());
                entries.stream().filter(p -> p.getTag().contains(search)).forEach(e -> System.out.println(e.getEntry()));
            }break;
            case 2: {
                String text;
                System.out.println("enter text");
                input.nextLine();
                text = input.nextLine();
                entries.stream().filter(p -> p.getText().contains(text)).forEach(e -> System.out.println(e.getEntry()));
            } break;
            case 3: {
                System.out.println("enter owner");
                input.nextLine();
                String owner = input.nextLine();
                entries.stream().filter(p -> p.getUser().contains(owner)).forEach(e -> System.out.println(e.getEntry()));
            }break;
            case 4: {
                String str;
                String str2;

                System.out.println("enter date base yyyy-mm-dd");
                str = input.next();
                System.out.println("enter date limit yyyy-mm-dd");
                str2 = input.next();

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                LocalDate dateTimeBase = LocalDate.parse(str, formatter);
                LocalDate dateTimeEnd = LocalDate.parse(str2, formatter);

                entries.stream().filter(e -> e.getDate().isAfter(dateTimeBase)
                        && e.getDate().isBefore(dateTimeEnd)).
                        forEach(e -> System.out.println(e.getEntry()));
            }
        }
    }

    public static void groupOption(List<Group> groups, String user){
        Scanner input = new Scanner(System.in);
        int option;
        System.out.println("Group Options \n 1-Add Group \n 2-Suscribe Group");
        option=input.nextInt();
        switch (option){
            case 1:{
                System.out.println("Enter name group");
                input.nextLine();
                String name=input.nextLine();
                List<String> subs = new ArrayList<>();
                Group newgroup = new Group(name,subs);
                newgroup.setSubscribers(user);
                groups.add(newgroup);

            }break;
            case 2:{
                groups.forEach(e -> System.out.println("GroupName:"+e.getName()+"\nSubs:"+e.getSubscribers()));
                System.out.println("Which group?");
                String groupchoice;
                input.nextLine();
                groupchoice=input.nextLine();
                groups.stream().filter(p -> p.getName().contains(groupchoice)).forEach(p -> p.setSubscribers(user));
            }break;
        }
    }

    public static String newUser(Scanner input, List<User> userList){
        String actualuser;
        System.out.println("Enter name");
        input.nextLine();
        String name = input.nextLine();
        User user = new User(name);
        userList.add(user);
        actualuser=name;
        System.out.println("New User Registered! \n");
        return actualuser;
    }

    public static String userOptions (List<User> userList){
        Scanner input = new Scanner(System.in);
        int choice;
        AtomicReference<String> actualuser= new AtomicReference<>("");
        System.out.println("1-Log in \n2-Add user");

        do {
            while (!input.hasNextInt()) {
                System.out.println("Only numeric values");
                input.next();
            }
            choice = input.nextInt();
        }while (choice < 1 || choice > 2 );

        switch (choice){
            case 1:{
                if (userList.isEmpty()){
                    System.out.println("No users existents, add new");
                    actualuser.set(newUser(input, userList));
                }else {
                    userList.forEach(p -> System.out.println(p.getName()));
                    System.out.println("Enter name");
                    input.nextLine();
                    String name = input.nextLine();

                    userList.stream().filter(p -> p.getName().contains(name)).forEach(e -> actualuser.set(e.getName()));
                    for (int i = 0; i < userList.size(); i++) {
                        if (userList.get(i).getName().contains(name)) {
                            actualuser.set(name);
                        }
                    }
                }
            }break;
            case 2:{
                actualuser.set(newUser(input, userList));
            }

        }
        return actualuser.get();
    }

    public static void subscribeUser(List<User> userList, String user){
        Scanner input = new Scanner(System.in);
        AtomicBoolean founded= new AtomicBoolean(false);
        userList.forEach(p -> System.out.println(p.getName()));
        System.out.println("Enter name");
        String choice = input.nextLine();
        userList.stream().filter(p -> p.getName().equals(choice)).forEach(e -> {
            e.setSubscribers(user);
            founded.set(true);
        });

        if (founded.get()){
            System.out.println("Subscription successfully!");
        }else{
            System.out.println("Error user no exists");
        }
    }

    public static void main(String [] args) throws IOException {
        Scanner input = new Scanner(System.in);
        int option;
        int cont=0;
        String user;

        //instance of the HashMap and List Collections
        List<User> userlist = new ArrayList<>();
        user=userOptions(userlist);
        System.out.println("User : "+user);
        HashMap<Integer,String> tagNames = new HashMap<>();
        List<Entry> entries = new ArrayList<>();
        List<Group> groups = new ArrayList<>();
        //This cicle executes while option are distinct of "Exit"
        do {
            System.out.println("Current User : "+user);
            System.out.println("WELCOME TO DEVSBLOG \n 1-New Entry \n 2-Delete Entry \n 3-Show 10 Recent Entries \n 4-Define tags");
            System.out.println(" 5-Search an specified Entry \n 6-Groups Options \n 7-User Options \n 8-Subscribe User \n 0-Exit");
            //control of valid number
            do {
                while (!input.hasNextInt()) {
                    System.out.println("Only numeric values");
                    input.next();
                }
                option = input.nextInt();
            }while (option < 0 || option > 8 );

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
                    subscribeUser(userlist,user);
                }break;
            }

        }while(option!=0);
    }

}