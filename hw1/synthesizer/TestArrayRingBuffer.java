package synthesizer;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    Random random = new Random(2123);
    @Test
    public void someTest() {
        ArrayRingBuffer<Double> arb = new ArrayRingBuffer<>(10);
        arb.enqueue(3.3);
        arb.enqueue(4.3);
        arb.enqueue(5.3);
        double expected = arb.dequeue();
        assertEquals(expected,3.3,10e-9);
        expected = arb.dequeue();
        assertEquals(expected,4.3,10e-9);
        expected = arb.dequeue();
        assertEquals(expected,5.3,10e-9);
    }

    @Test
    public void otherTest() {
        ArrayRingBuffer<Double> arb = new ArrayRingBuffer<>(10);
        double[] expected = new double[10];
        for (int i = 0; i < arb.capacity(); i++) {
            arb.enqueue(random.nextDouble() + i);
        }
        for (int i = 0; i < arb.capacity(); i++) {
            arb.dequeue();
        }
        for (int i = 0; i < arb.capacity(); i++) {
            expected[i] = random.nextDouble() + i;
            arb.enqueue(expected[i]);
        }
        for (double x:arb
             ) {
            System.out.println(x);
        }
        for (int i = 0; i < arb.capacity(); i++) {

            assertEquals(expected[i],arb.dequeue(),10e-9);
        }
    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
