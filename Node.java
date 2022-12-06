//Trie Node class
//Authors:
//Maxwell Caiazza, Ava Crocker, Taylor Carlson


import java.util.HashMap;

public class Node implements Comparable<Node>{
    Node parent;
    HashMap<Character, Node> children = new HashMap<Character, Node>();
    char initialC;
    boolean isFullWord;
    int freq;

    // Node Constructor
    public Node() {}
    
    // Assigning the Char Value
    public Node(char c){
        this.initialC = c;
    }
    
    public int compareTo(Node e){
        return Integer.compare(this.freq, e.freq);
    }
}
