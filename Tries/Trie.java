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
            return;
        }
        TrieNode child = start.children[word.charAt(0) - 'a'];
        if (child == null) {
            child = new TrieNode(word.charAt(0));
            start.children[word.charAt(0) - 'a'] = child;
            child.childCount++;
        }
        addHelper(child, word.substring(1));
    }

    public boolean search(String word) {
        return searchHelper(start, word);
    }

    private boolean searchHelper(TrieNode start, String word) {
        if (word.length() == 0) {
            return start.isLast;
        }

        TrieNode child = start.children[word.charAt(0) - 'a'];
        if (child == null) {
            return false;
        }
        return searchHelper(child, word.substring(1));
    }

    public void delete(String word) {
        deleteHelper(start, word);
        return;
    }

    private void deleteHelper(TrieNode start, String word) {
        if (word.length() == 0) {
            start.isLast = false;
            return;
        }

        TrieNode child = start.children[word.charAt(0) - 'a'];
        if (child == null) {
            return;
        }
        deleteHelper(child, word.substring(1));
        if (!child.isLast && child.childCount == 0) {
            child.children[word.charAt(0) - 'a'] = null;
            child.childCount--;
        }
    }
}
