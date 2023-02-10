package Tries;

import java.util.HashMap;
import java.util.Map;

import Priority_Queue.HeapEmptyException;
import Priority_Queue.PriorityQueueMinHeap;

public class HuffmanEncoder {
    HashMap<Character, String> encoder;
    HashMap<String, Character> decoder;

    public HuffmanEncoder(String feed) throws HeapEmptyException {
        // 1.Create a Freq HashMap of this feed String
        HashMap<Character, Integer> fmap = new HashMap<>();
        for (int i = 0; i < feed.length(); i++) {
            if (fmap.containsKey(feed.charAt(i))) {
                fmap.put(feed.charAt(i), fmap.get(feed.charAt(i)) + 1);
            } else {
                fmap.put(feed.charAt(i), 1);
            }
        }
        // 2.Create a minHeap of Nodes
        PriorityQueueMinHeap<Node> minHeap = new PriorityQueueMinHeap<>();
        for (Map.Entry<Character, Integer> entry : fmap.entrySet()) {
            Node node = new Node(entry.getKey(), entry.getValue());
            minHeap.insert(node, node.frq);
        }

        // 3. Combine 2 smallest frq nodes and create one and add inHeap

        while (minHeap.size() != 1) {
            Node first = minHeap.removeMin();
            Node second = minHeap.removeMin();

            Node combined = new Node(first, second);
            combined.character = '\0';
            combined.frq = first.frq + second.frq;
            minHeap.insert(combined, combined.frq);
        }

        Node ft = minHeap.removeMin();
        this.encoder = new HashMap<>();
        this.decoder = new HashMap<>();
        this.initEncoderDecoder(ft, "");
    }

    private void initEncoderDecoder(Node node, String osf) {
        if (node == null) {
            return;
        }
        if (node.left == null && node.right == null) {
            encoder.put(node.character, osf);
            decoder.put(osf, node.character);
        }
        initEncoderDecoder(node.left, osf + "0");
        initEncoderDecoder(node.right, osf + "1");

    }

    public String encode(String source) {
        String ans = "";
        for (int i = 0; i < source.length(); i++) {
            ans += encoder.get(source.charAt(i));
        }
        return ans;
    }

    public String decode(String source) {
        String ans = "";
        String temp = "";
        for (int i = 0; i < source.length(); i++) {
            temp += source.charAt(i);
            if (decoder.containsKey(temp)) {
                ans += decoder.get(temp);
                temp = "";
            }
        }
        return ans;
    }

    private class Node {
        char character;
        int frq;
        Node left;
        Node right;

        Node(char character, int frq) {
            this.character = character;
            this.frq = frq;
            this.left = null;
            this.right = null;
        }

        Node(Node left, Node right) {
            this.left = left;
            this.right = right;
        }

    }

}
