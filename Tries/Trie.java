package Tries;

public class Trie {
    private TrieNode start;

    public Trie() {
        start = new TrieNode('\0');
    }

    public void add(String word) {// DOG
        addHelper(start, word);
    }

    private void addHelper(TrieNode start, String word) {
        if (word.length() == 0) {
            start.isLast = true;
        }
        TrieNode child = start.children[word.charAt(0) - 'a'];
        if (child == null) {
            child = new TrieNode(word.charAt(0));
            start.children[word.charAt(0) - 'a'] = child;
        }
        addHelper(child, word.substring(1));
    }

}
