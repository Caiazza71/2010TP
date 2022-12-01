/*
* Citation:
* Data Structures and Algorithms in Java, Sixth Edition 
* Chapter 13.3
* Written By:
* Maxwell Caiazza, Ava Crocker, Taylor Carlson
*/





import java.util.LinkedList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Iterator;

public class Trie {
    public Node root, start;
    public String currentWord;
    public ArrayList<String> dictionary; 

    //Default constructer
    public Trie() {
        this.root = new Node();
        this.dictionary  = new ArrayList<String>();
    }

    //Puts a given element into the TRIE using the Node class

    public void insert(String wordIn) {
        Node currentNode = root;
        HashMap<Character, Node> children = root.children;

        for (int i = 0; i < wordIn.length(); i++){
            Node temp;
            char initial = wordIn.charAt(i);

            if(!children.containsKey(initial)){
                temp = new Node(initial);
                temp.parent = currentNode;
                children.put(initial, temp);
            } else {
                
                temp = children.get(initial);
            }

            children = temp.children;
            currentNode = temp;
            if (i == wordIn.length() - 1) {
                temp.isFullWord = true;
            }
        }
    }

    //Find takes ina valid node and then spits out all the children with similar prefixes
    public void find(Node temp, int num) {

        if(temp.isFullWord == true) {
            Node n;
            n = temp;
            String s = currentWord;
            LinkedList<String> finder = new LinkedList<String>(); 

            while(n != start) {
                finder.addFirst(Character.toString(n.initialC) );
                n = n.parent;
            }

            while(finder.size() != 0) {
                s = s + finder.removeFirst();
            }
            dictionary.add(s);
        }

        ArrayList<Character> arr = new ArrayList<Character>();
        Set<Character> list = temp.children.keySet();
        Iterator<Character> runner = list.iterator();

        while(runner.hasNext()) {
            Character letter = (Character)runner.next();  
            arr.add(letter);
        } 

        for( int i = 0;i < arr.size(); i++) {
            find(temp.children.get(arr.get(i)), num + 2);
        } 
    }

    //Searching the tree when given a word to find where the children are
    public Node searchTrie(String word) {
        Node temp = null;
        Map<Character, Node> below = root.children; 

        for(Character initial : word.toCharArray()){
          if(!below.containsKey(initial)){
            return null;
          } else {
            temp = below.get(initial);
            below = temp.children;
          }
        }

        currentWord = word;
        start = temp;
        dictionary.clear();
        return temp;
    }
}