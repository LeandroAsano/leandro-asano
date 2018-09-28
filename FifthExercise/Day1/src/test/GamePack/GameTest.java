package GamePack;

import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class GameTest {

    static Game gam;
    static Scanner input = new Scanner(System.in);

    @BeforeClass
    public static void instaceClass(){
        gam = new Game();
    }

    @Test
    public void testAmountPlayers() {
        //arrange
        int cantplay = 5;
        Array[] expected;
        //act
        expected = new Array[cantplay];
        Player[] result = gam.arrayGenerate(cantplay);
        //assert
        assertArrayEquals(expected,result);
    }

    @Test
    public void testInstruccions(){

        String expected = "Two players take turns either taking one or two sticks. \nThe player who takes the last stick loses. \nSimple right? \n";
        String result = Game.showInstructions();
        assertEquals(expected,result);

    }


}