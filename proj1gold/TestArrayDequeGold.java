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

        random = StdRandom.uniform(10);
        sad.addFirst(random);
        sad.addFirst(3);
        Integer x = 6;
        assertEquals("addFirst(" + random + ")\naddFirst(" + 3 + ")\n" +
                "removeFirst()\n", x, sad.removeFirst());
    }



}
