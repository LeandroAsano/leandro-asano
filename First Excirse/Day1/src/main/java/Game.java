import java.util.Scanner;

public class Game {

    public static void showInstructions(){
        System.out.println("Two players take turns either taking one or two sticks. \n" +
                "The player who takes the last stick loses. \n" +        //Shows the instructions of the game
                "Simple right? \n");
    }

    public static void newGame(){
        Scanner entrada = new Scanner(System.in);
        int cantplay;
        int num;
        int choice;
        int sticks=21;
        String nombre;

        //Enter of amount players in variable "c"
        System.out.println("Enter amount of players");
        do {
            cantplay = entrada.nextByte();
        }while(cantplay<2);
        Player array[] = new Player[cantplay];

        //this part make the array of players
        for (int i = 0; i < array.length; i++) {
            System.out.println("Enter player " + (i + 1) + " info: ");
            System.out.print("Name: ");
            nombre = entrada.next();
            num = (i + 1);
            //here we instance the class
            array[i] = new Player(nombre, num);
        }
        int playerturn = 0;
        /* this part runs the array how many times was necessary for finish the game */
        while (sticks > 0) {
            //this part controls the structure of the game
            System.out.println("Player " + (playerturn + 1) + " turn");
            System.out.println("Take 1 or 2 sticks");
            System.out.println("Sticks remaining: "+sticks);
            choice = entrada.nextInt();
            while (choice != 1 && choice != 2) {
                System.out.println("error, just 1 or 2");
                choice = entrada.nextByte();
            }
            sticks -= choice;
            //this part controls the i assignment
            if (sticks > 0) {
                if (playerturn == (array.length - 1)) {
                    playerturn = 0;
                } else
                    playerturn++;
            }
        }
        System.out.println(array[playerturn].getName()+"\n");   //This line show the player loser through "getPlayer" method

    }

    public static void main(String [] args){
        Scanner entrada = new Scanner(System.in);
        int c;

        do {
            System.out.println("'21STICKS' \n 1-START \n 2-INSTRUCCIONS \n 3-EXIT");
            c = entrada.nextByte();

            switch (c) {
                //case 1 controls the "newGame()" funcion
                case 1: {
                    Game.newGame();
                }
                break;
                case 2:{
                    Game.showInstructions();
                }
                break;
                case 3:{
                    System.out.println("THANKS FOR PLAY");          //End the game
                }
            }

        }while(c!=3);

    }


}
