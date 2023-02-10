package Tries;

public class TrieNode {
    char character;
    boolean isLast;
    TrieNode children[];
    int childCount;

    TrieNode(char character) {
        this.character = character;
        isLast = false;
        children = new TrieNode[26];
        childCount = 0;
    }
}
