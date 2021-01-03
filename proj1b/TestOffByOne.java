import org.junit.Test;

import static org.junit.Assert.*;

public class TestOffByOne {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    @Test
    public void testEqualChars() {
        assertTrue(offByOne.equalChars('x', 'y'));
        assertTrue(offByOne.equalChars('x', 'Y'));
        assertFalse(offByOne.equalChars('a', 'e'));
        assertFalse(offByOne.equalChars('a', 'a'));
        assertTrue(offByOne.equalChars('&', '%'));
    }
    //Uncomment this class once you've created your CharacterComparator interface
    // and OffByOne class. *

}
