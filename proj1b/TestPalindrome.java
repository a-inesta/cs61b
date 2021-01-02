import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();
    static CharacterComparator cc = new OffByOne();
    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("synergy");
        String actual = "synergy";
        for (int i = 0; i < "synergy".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("", actual);
    } //Uncomment this class once you've created your Palindrome class.

    @Test
    public void testIsPalindrome() {
        String input1 = "noon";
        String input2 = "aaaaaab";
        String input3 = "";
        String input4 = "a";
        assertTrue(palindrome.isPalindrome(input1));
        assertFalse(palindrome.isPalindrome(input2));
        assertTrue(palindrome.isPalindrome(input3));
        assertTrue(palindrome.isPalindrome(input4));
        assertTrue(palindrome.isPalindrome("abcb", cc));
        assertFalse(palindrome.isPalindrome("abba", cc));
        assertTrue(palindrome.isPalindrome("flake", cc));
    }

    /*@Test
    public void testIsPalindromeNR() {
        String input1 = "noon";
        String input2 = "aaaaaab";
        String input3 = "";
        String input4 = "a";
        assertTrue(palindrome.isPalindromeNR(input1));
        assertFalse(palindrome.isPalindromeNR(input2));
        assertTrue(palindrome.isPalindromeNR(input3));
        assertTrue(palindrome.isPalindromeNR(input4));
    }*/
}
