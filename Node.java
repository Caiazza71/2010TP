//Trie Node class
//Authors:
//Maxwell Caiazza, Ava Crocker, Taylor Carlson


import java.util.HashMap;

public class Node {
    // Creates needed variables
    Node parent;
    HashMap<Character, Node> children = new HashMap<Character, Node>();
    char initialC;
    boolean isFullWord;
    int freq;

    // Creates node
    public Node() {}
    
    // Node is assigned a char
    public Node(char c){
        this.initialC = c;
    }
}
