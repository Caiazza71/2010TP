import java.util.HashMap;

public class Node {
    
    Node parent;
    HashMap<Character, Node> children = new HashMap<Character, Node>();
    char initialC;
    boolean isFullWord;

    public Node() {}
    
    public Node(char c){
        this.initialC = c;
    }
}
