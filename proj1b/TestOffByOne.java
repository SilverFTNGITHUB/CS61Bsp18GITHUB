import org.junit.Test;

import static org.junit.Assert.*;

public class TestOffByOne {

    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    @Test
    public void testEqualCharsOffByOne() {
        assertTrue(offByOne.equalChars('a', 'b'));
        assertTrue(offByOne.equalChars('b', 'a'));
    }

    @Test
    public void testNotEqualCharsOffByOne() {
        assertFalse(offByOne.equalChars('a', 'a'));
        assertFalse(offByOne.equalChars('c', 'e'));
        assertFalse(offByOne.equalChars('\0', '\0'));// '\0' non-letters
        assertFalse(offByOne.equalChars('a', 'A'));
    }
}
