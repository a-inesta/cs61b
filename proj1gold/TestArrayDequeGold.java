import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestArrayDequeGold {

    @Test
    public void testWhat() {
        StudentArrayDeque<Integer> sad = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads = new ArrayDequeSolution<>();
        int random = StdRandom.uniform(10);
        sad.addFirst(random);
        ads.addFirst(random);
        assertEquals("addFirst(" + random + ")\n", sad.get(0), ads.get(0));

    }

    @Test
    public void testArratDeque() {
        ArrayDequeSolution<Integer> ads = new ArrayDequeSolution<>();
        StudentArrayDeque<Integer> sad = new StudentArrayDeque<>();
        // addLast
        for (int i = 0; i < 10; i++) {
            int random = StdRandom.uniform(100);
            ads.addLast(random);
            sad.addLast(random);
        }
        for (int i = 0; i < 10; i++) {
            int actual = ads.get(i);
            int expected = sad.get(i);
            assertEquals("Oh noooo!\nThis is bad in addLast():\n   Random number " + actual
                            + " not equal to " + expected + "!",
                    expected, actual);
        }

        // addFirst
        for (int i = 0; i < 10; i++) {
            int random = StdRandom.uniform(100);
            ads.addFirst(random);
            sad.addFirst(random);
        }
        for (int i = 0; i < 10; i++) {
            int actual = ads.get(i);
            int expected = sad.get(i);
            assertEquals("Oh noooo!\nThis is bad in addFirst():\n   Random number " + actual
                            + " not equal to " + expected + "!",
                    expected, actual);
        }

        // removeFirst
        List<Integer> actualList = new ArrayList<>();
        List<Integer> expectedList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            actualList.add(ads.removeFirst());
            expectedList.add(sad.removeFirst());
        }
        for (int i = 0; i < 10; i++) {
            int actual = ads.get(i);
            int expected = sad.get(i);
            assertEquals("Oh noooo!\nThis is bad in removeFirst():\n   Random number " + actual
                            + " not equal to " + expected + "!",
                    expected, actual);
        }
        for (int i = 0; i < 10; i++) {
            int actual = actualList.get(i);
            int expected = expectedList.get(i);
            assertEquals("Oh noooo!\nThis is bad in removeFirst():\n   Random number " + actual
                            + " not equal to " + expected + "!",
                    expected, actual);
        }


        // removeLast
        actualList.clear();
        expectedList.clear();
        for (int i = 0; i < 10; i++) {
            actualList.add(ads.removeLast());
            expectedList.add(sad.removeLast());
        }
        int actual = ads.size();
        int expected = sad.size();
        assertEquals("Oh noooo!\nThis is bad in removeLast():\n   actual size " + actual
                        + " not equal to " + expected + "!",
                expected, actual);
        for (int i = 0; i < 10; i++) {
            assertEquals("Oh noooo!\nThis is bad in removeLast():\n   Random number " + actualList.get(i)
                            + " not equal to " + expectedList.get(i) + "!",
                    expectedList.get(i), actualList.get(i));
        }

    }

}
