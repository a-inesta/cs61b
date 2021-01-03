import org.junit.Test;

import static org.junit.Assert.*;

public class TestOffByN {
    static CharacterComparator offByN = new OffByN(5);

    // Your tests go here.
    @Test
    public void testEqualChars() {
        assertTrue(offByN.equalChars('a', 'f'));
        assertTrue(offByN.equalChars('f', 'a'));
        assertFalse(offByN.equalChars('a', 'e'));
        assertFalse(offByN.equalChars('a', 'a'));
        assertFalse(offByN.equalChars('(', ')'));
    }
    //Uncomment this class once you've created your CharacterComparator interf
    // ace and OffByOne class. *

}
