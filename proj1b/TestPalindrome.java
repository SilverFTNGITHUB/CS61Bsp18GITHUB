import org.junit.Test;

import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();
    static OffByOne offByOne = new OffByOne();


    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    } //Uncomment this class once you've created your Palindrome class.

    @Test
    public void testIsPalindrome() {
        String strPali = "catac";
        assertTrue("string = " + strPali, palindrome.isPalindrome(strPali));
        strPali = "a";
        assertTrue("string = " + strPali, palindrome.isPalindrome(strPali));
        strPali = "";
        assertTrue("string = " + strPali, palindrome.isPalindrome(strPali));
    }

    @Test
    public void testIsNotPalindrome() {
        String strNotPali = "catef";
        assertFalse(strNotPali, palindrome.isPalindrome(strNotPali));
    }

    @Test
    public void testIsPalindromeOffByOne() {
        String strPali = "flake";
        assertTrue("string = " + strPali, palindrome.isPalindrome(strPali, offByOne));
        strPali = "a";
        assertTrue("string = " + strPali, palindrome.isPalindrome(strPali, offByOne));
        strPali = "";
        assertTrue("string = " + strPali, palindrome.isPalindrome(strPali, offByOne));
    }

    @Test
    public void testIsNotPalindromeOffByOne() {
        String strNotPali = "catef";
        assertFalse(strNotPali, palindrome.isPalindrome(strNotPali, offByOne));
    }

    @Test
    public void testIsPalindromeOffByN() {
        OffByN offByN = new OffByN(5);
        String strPali = "fl%qa";
        assertTrue("string = " + strPali, palindrome.isPalindrome(strPali, offByN));
        strPali = "a";
        assertTrue("string = " + strPali, palindrome.isPalindrome(strPali, offByN));
        strPali = "";
        assertTrue("string = " + strPali, palindrome.isPalindrome(strPali, offByN));
    }

    @Test
    public void testIsNotPalindromeOffByN() {
        OffByN offByN = new OffByN(5);
        String strNotPali = "catef";
        assertFalse(strNotPali, palindrome.isPalindrome(strNotPali, offByN));
    }

    @Test
    public void testQuestions() {
        assertFalse("test".equals(null));
        int a = (int) 'a';
    }


}
