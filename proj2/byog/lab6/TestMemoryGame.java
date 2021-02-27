package byog.lab6;

import edu.princeton.cs.introcs.StdDraw;
import org.junit.Test;

import java.util.NoSuchElementException;

public class TestMemoryGame {
    static int HEIGHT = 40;
    static int WIDTH = 40;
    static MemoryGame mg = new MemoryGame(WIDTH,HEIGHT);

    public static void main(String[] args) {

        try{
            String temp = mg.solicitNCharsInput(4);
            for(int i = 0;i < temp.length();i++){
                mg.flashSequence(temp.charAt(i)+"");
            }
        }catch(NoSuchElementException e) {
            mg.drawFrame("NoSuchElementException");
        }
    }
    @Test
    public void testOfGenerateRandomString() {
        int n = 100;
        System.out.println(mg.generateRandomString(n));
    }

    @Test
    public void testOfDrawFrame() {
        mg.drawFrame(mg.generateRandomString(10));
    }
    @Test
    public void testOfFlashSequence() {
        mg.flashSequence(mg.generateRandomString(10));
    }

    @Test
    public void testOfSolicitNCharsInput() {

        String temp = mg.solicitNCharsInput(4);
        for(int i = 0;i < temp.length();i++){
            mg.flashSequence(temp.charAt(i)+"");
        }


    }

}
