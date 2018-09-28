package GamePack;

public class Player {

    private String name;
    private int num;

    public Player(String name, int num) {
        this.name = name;
        this.num = num;
    }

    public String getName(){
        return "the loser is the player "+num+": "+name;
    }

}
