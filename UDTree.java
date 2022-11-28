//Authors Max Caiazza, Ava Crocker, Taylor Carlson

public class UDTree { // User Data Tree for storing past user data
    
    public class Node implements Comparable{
        //giving each node access to the parent and all its children
        Node parent = null;
        Node[] children = new Node[26];
        
        //Giving the node the things it needs to track such as the value, and the 
        String value = null;
        int freq = 0;
        
        public Node(Node p, String val){
            parent = p;
            value = val;
        }
        
        public int compareTo(Node e){
            return value.compareTo(e.value);
        }
    }
    
    public Node head = new Node(null, null);
    
    //Adding in a node on the tree
    public void add(String val){
        //if we find the node already
            //add one to freqency
            //will give us some concrete way to sort
        //else 
            //we end on the parent and use it in node declaration.
    }
    
    //Getting all of the children of the given tree
    public void returnChildren(){}
    
    public void find(){}
}
