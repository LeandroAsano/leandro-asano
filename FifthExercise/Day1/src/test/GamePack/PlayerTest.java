package GamePack;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    @DisplayName("getLoser Test")
    public void testPlayer(){
        Player player1 = new Player("Leo",1);
        String actual = player1.getName();
        String expected = "the loser is the player 1: Leo";
        assertEquals(expected,actual);
    }

}