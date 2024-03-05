public class Palindrome {

    public Deque<Character> wordToDeque(String word) {
        Deque<Character> word_deque = new LinkedListDeque<Character>();
        for (int i = 0; i < word.length(); i++) {
            word_deque.addLast(word.charAt(i));
        }
        return word_deque;
    }
}