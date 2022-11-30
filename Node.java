import java.util.HashMap;

public class Node {
    char initialC;
    Node above;
    HashMap<Character, Node> below = new HashMap<Character, Node>();
    boolean bottom;

    public Node() {}
    
    public Node(char c){
        this.initialC = c;
    }
}
