import java.util.HashMap;

public class Node {
    
    Node above;
    HashMap<Character, Node> below = new HashMap<Character, Node>();
    char initialC;
    boolean bottom;

    public Node() {}
    
    public Node(char c){
        this.initialC = c;
    }
}
