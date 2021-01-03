import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {
    StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<>();
    ArrayDequeSolution<Integer> ads1 = new ArrayDequeSolution<>();
    @Test
    public void testWhat() {

        sad1.addFirst(3);
        ads1.addFirst(3);
        assertEquals(sad1.size(), ads1.size());
        assertEquals(sad1.removeFirst(),ads1.removeFirst());
        assertEquals(sad1.removeFirst(),ads1.removeFirst());
        assertEquals(sad1.size(), ads1.size());

        sad1.addLast(3);
        ads1.addLast(3);
        assertEquals(sad1.size(), ads1.size());
        Integer x = 3;
        assertEquals(sad1.removeFirst(), x);
        assertEquals(ads1.removeFirst(), x);
        assertEquals(sad1.size(), ads1.size());

        sad1.addFirst(5);
        sad1.addFirst(3);
        Integer expected = 3;
        Integer actual = sad1.removeFirst();
        assertEquals("Oh noooo!\nThis is bad:\n   Random number " + actual
                + " not equal to " + expected + "!", expected, actual);

    }

}
