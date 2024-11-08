//package DAA;

import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

// Node class to store characters and their frequency
class Node {
    char ch;
    int freq;
    Node left = null, right = null;

    Node(char ch, int freq) {
        this.ch = ch;
        this.freq = freq;
    }

    Node(int freq, Node left, Node right) {
        this.freq = freq;
        this.left = left;
        this.right = right;
    }
}

// Comparator to compare nodes based on their frequency
class NodeComparator implements Comparator<Node> {
    public int compare(Node node1, Node node2) {
        return node1.freq - node2.freq;
    }
}

public class DAA2 {

    // Function to print the encoded characters
    public static void printHuffmanCodes(Node root, String s, Map<Character, String> huffmanCode) {
        if (root == null) {
            return;
        }

        // Leaf node, add the character and its Huffman code to the map
        if (root.left == null && root.right == null) {
            huffmanCode.put(root.ch, s);
        }

        // Traverse left and right subtrees
        printHuffmanCodes(root.left, s + "0", huffmanCode);
        printHuffmanCodes(root.right, s + "1", huffmanCode);
    }

    // Main function to build the Huffman Tree and encode characters
    public static void buildHuffmanTree(String text) {
        // Count frequency of appearance of each character in the string
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char ch : text.toCharArray()) {
            freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
        }

        // Priority queue to store the nodes based on their frequency
        PriorityQueue<Node> pq = new PriorityQueue<>(new NodeComparator());

        // Create a leaf node for each character and add it to the priority queue
        for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
            pq.add(new Node(entry.getKey(), entry.getValue()));
        }

        // Iterate until the size of the queue becomes 1 (Huffman Tree is formed)
        while (pq.size() > 1) {
            // Remove two nodes with the lowest frequency
            Node left = pq.poll();
            Node right = pq.poll();

            // Create a new internal node with a frequency equal to the sum of both nodes' frequencies
            // and add it back to the queue
            Node parent = new Node(left.freq + right.freq, left, right);
            pq.add(parent);
        }

        // Root node of the Huffman Tree
        Node root = pq.peek();

        // Traverse the Huffman Tree and store the Huffman codes in a map
        Map<Character, String> huffmanCode = new HashMap<>();
        printHuffmanCodes(root, "", huffmanCode);

        // Print the Huffman codes
        System.out.println("Huffman Codes are: " + huffmanCode);
        System.out.println("Original string was: " + text);

        // Encode the input string
        StringBuilder encodedString = new StringBuilder();
        for (char ch : text.toCharArray()) {
            encodedString.append(huffmanCode.get(ch));
        }

        // Print the encoded string
        System.out.println("Encoded string is: " + encodedString);
    }

    // Main method
    public static void main(String[] args) {
        String text = "Greedy Search Algorithm using Huffman Encoding";
        buildHuffmanTree(text);
    }
}
