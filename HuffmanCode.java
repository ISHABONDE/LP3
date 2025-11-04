import java.util.*;

// Node used for creating the Huffman Tree
class Node {
    char ch;       // character
    int freq;      // frequency of character
    Node left;     // left child
    Node right;    // right child

    Node(char c, int f) {
        ch = c;
        freq = f;
    }
}

public class HuffmanCode {

    // To store final Huffman codes for each character
    static HashMap<Character, String> codes = new HashMap<>();

    // This function will create binary code by going left(0) or right(1)
    static void makeCode(Node root, String code) {
        if (root == null) return;

        // If leaf node (means actual character stored)
        if (root.left == null && root.right == null) {
            codes.put(root.ch, code);  // store final code of character
            return;
        }

        // Go left => add '0'
        makeCode(root.left, code + "0");

        // Go right => add '1'
        makeCode(root.right, code + "1");
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String text = sc.nextLine();
        sc.close();

        // Step 1: Count frequency of each character
        HashMap<Character, Integer> freq = new HashMap<>();
        for (char c : text.toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }

        // Step 2: Create Min Heap (priority queue) sorted by frequency
        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            public int compare(Node a, Node b) {
                return a.freq - b.freq; // smallest frequency first
            }
        });

        // Step 3: Create one node for every character and push in heap
        for (char c : freq.keySet()) {
            pq.add(new Node(c, freq.get(c)));
        }

        // Step 4: Build Huffman Tree
        // Take TWO smallest nodes, join, push back until one root remains
        while (pq.size() > 1) {
            Node x = pq.poll(); // smallest
            Node y = pq.poll(); // second smallest

            // Create a new parent node with sum of frequencies
            Node newNode = new Node('-', x.freq + y.freq);
            newNode.left = x;
            newNode.right = y;

            pq.add(newNode); // push back to heap
        }

        // Step 5: Root of Huffman Tree
        Node root = pq.poll();

        // Step 6: Generate Huffman Codes
        makeCode(root, "");

        // Step 7: Print Huffman Codes
        System.out.println("\nHuffman Codes:");
        for (char c : codes.keySet()) {
            System.out.println(c + " : " + codes.get(c));
        }

        // Step 8: Encode input string using generated codes
        String encoded = "";
        for (char c : text.toCharArray()) {
            encoded += codes.get(c);
        }

        System.out.println("\nOriginal Text: " + text);
        System.out.println("Encoded Text : " + encoded);
    }
}

