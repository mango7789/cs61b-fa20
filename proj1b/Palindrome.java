public class Palindrome {

    public Deque<Character> wordToDeque(String word) {
        Deque<Character> word_deque = new LinkedListDeque<Character>();
        for (int i = 0; i < word.length(); i++) {
            word_deque.addLast(word.charAt(i));
        }
        return word_deque;
    }

    public boolean isPalindrome(String word) {
        if (word == null) {
            return false;
        }
        int word_len = word.length();
        if (word_len == 1 || word_len == 0) {
            return true;
        }
        else {
            Deque<Character> word_deque = wordToDeque(word);
            while (word_deque.size() > 1) {
                char front = word_deque.removeFirst(), end = word_deque.removeLast();
                if (front != end) {
                    return false;
                }
            }
            return true;
        }
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (word == null) {
            return false;
        }
        int word_len = word.length();
        if (word_len == 1 || word_len == 0) {
            return true;
        }
        else {
            Deque<Character> word_deque = wordToDeque(word);
            
            while (word_deque.size() > 1) {
                char front = word_deque.removeFirst(), end = word_deque.removeLast();
                if (!cc.equalChars(front, end)) {
                    return false;
                }
            }
            return true;
        }
    }
}