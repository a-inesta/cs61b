public class Palindrome {
    public Deque<Character> wordToDeque(String word){
        Deque<Character> wordDeque = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            wordDeque.addLast(word.charAt(i));
        }
        return wordDeque;
    }

    public boolean isPalindrome(String word){
        Deque<Character> wordDeque = wordToDeque(word);
        return isPalindromeHelper(wordDeque);
    }

    public boolean isPalindromeHelper(Deque<Character> wordDeque) {
        if(wordDeque.size() == 1 || wordDeque.size() == 0){
            return true;
        }
        if(wordDeque.removeFirst() == wordDeque.removeLast()){
            return isPalindromeHelper(wordDeque);
        }else{
            return false;
        }
    }

    public boolean isPalindromeNR(String word){
        String reverse = "";
        for(int i = word.length() - 1; i >= 0; i--){
            reverse += word.charAt(i);
        }
        return reverse.equals(word);
    }

    public boolean isPalindrome(String word, CharacterComparator cc){
        Deque<Character> wordDeque = wordToDeque(word);
        return isPalindromeHelper(wordDeque, cc);
    }

    public boolean isPalindromeHelper(Deque<Character> wordDeque, CharacterComparator cc) {
        if(wordDeque.size() == 1 || wordDeque.size() == 0){
            return true;
        }

        if(cc.equalChars(wordDeque.removeFirst(), wordDeque.removeLast())){
            return isPalindromeHelper(wordDeque, cc);
        }else{
            return false;
        }
    }
}
