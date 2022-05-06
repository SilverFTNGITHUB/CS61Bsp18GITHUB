import java.nio.charset.StandardCharsets;

public class Palindrome {
    /**
     * Given a String, wordToDeque should return a Deque
     * where the characters appear in the same order as in the String.
     *
     * @param word
     * @return
     */
    public Deque<Character> wordToDeque(String word) {
        //return null;
        Deque<Character> wordDeque = new ArrayDeque<>();
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            wordDeque.addLast(ch);
        }
        return wordDeque;
    }

    /**
     * The isPalindrome method should return true if the given word is a palindrome,
     * and false otherwise
     *
     * @param word
     * @return
     */
    public boolean isPalindrome(String word) {
        Deque<Character> wordDeque = wordToDeque(word);
        while (wordDeque.size() >= 2) {
            // null can't call .equals(), be careful with corner case
            if (!wordDeque.removeFirst().equals(wordDeque.removeLast())) {
                return false;
            }
        }
        return true;
    }

    /**
     * The method will return true if the word is a palindrome according to the character
     * comparison test provided by the CharacterComparator passed in as argument cc.
     *
     * @param word
     * @param cc
     * @return
     */
    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> wordDeque = wordToDeque(word);
        while (wordDeque.size() >= 2) {
            // null can't call .equals(), be careful with corner case
            if (!cc.equalChars(wordDeque.removeFirst(), wordDeque.removeLast())) {
                return false;
            }
        }
        return true;
    }
}

