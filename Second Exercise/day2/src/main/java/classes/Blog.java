package classes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Blog {

    static void newEntry(List<Entry> entries){
        Scanner input = new Scanner(System.in);
        String title;
        String text;
        String tags;

        System.out.println("Enter the title ");
        title=input.nextLine();
        System.out.println("Enter text");
        text=input.nextLine();
        System.out.println("Enter tags ");
        tags=input.nextLine();

        LocalDate date = LocalDate.now();
        Entry ent = new Entry(title,text,date,tags);
        entries.add(ent);

        System.out.println("Entry Successfully!");
    }

    static void deleteEntry(List<Entry> entries){
        Scanner input = new Scanner(System.in);
        int cont=1;
        int choice=0;
        System.out.println("Which entry you want delete?");
        for (Entry p: entries) {

            System.out.println(cont+"-"+p.getEntry());
            cont++;
        }
        do {
                choice = input.nextInt();
        }while(choice<1 || choice >4);
        try {
            entries.remove(choice-1);
            System.out.println("Entry Removed Successfully!");
        }catch (Exception e){
            System.out.println("Error, that entry doesn't exist");
        }
    }

    static void showTenRecentEntries(List<Entry> entries){
        for (int i = 0; i < 10 ; i++) {
           try {
               System.out.println((i+1)+"-"+entries.get(i).getEntry());
           } catch (Exception e){
               System.out.println((i+1)+"-  Vacio");
           }
        }

    }

    public static void main(String [] args){
        Scanner input = new Scanner(System.in);
        int option;

        List<Entry> entries = new ArrayList<>();
        do {
            System.out.println("WELCOME TO DEVSBLOG \n 1-New Entry \n 2-Delete Entry \n 3-Show 10 Recent Entries \n 4-Exit");
            do {
                    option = input.nextInt();
            }while (option < 1 || option > 4);

            switch (option) {
                case 1: {
                    newEntry(entries);
                }
                break;
                case 2: {
                    deleteEntry(entries);
                }
                break;
                case 3: {
                    showTenRecentEntries(entries);
                }break;
                default:
                    System.out.println("Ingrese opcion valida");

            }

        }while(option!=4);
    }

}
