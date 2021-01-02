
import org.junit.*;
import org.junit.Test;


public class TestFilk {
    @Test
    public void testIsSameNumber() {
        int a = 129;
        int b = 129;
        int c = 0;
        Assert.assertTrue(Flik.isSameNumber(a, b));
        Assert.assertFalse(Flik.isSameNumber(a, c));
    }
}
