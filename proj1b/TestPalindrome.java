import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();
    static CharacterComparator obo = new OffByOne();
    static CharacterComparator obn = new OffByN(2);
    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("synergy");
        String actual = "";
        for (int i = 0; i < "synergy".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("synergy", actual);
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
        assertTrue(palindrome.isPalindrome("abcb", obo));
        assertFalse(palindrome.isPalindrome("abba", obo));
        assertTrue(palindrome.isPalindrome("flake", obo));
        assertTrue(palindrome.isPalindrome("A",obo));
        assertTrue(palindrome.isPalindrome("Ab",obo));
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
