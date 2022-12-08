/*
* Citation:
* Data Structures and Algorithms in Java, Sixth Edition 
* Chapter 13.3
* Written By:
* Maxwell Caiazza, Ava Crocker, Taylor Carlson
*/

import java.util.ArrayList; // for arrays
import java.util.Collections; // for sorting arrays

import java.util.HashMap;
import java.util.Map;

public class Trie {
    public Node root, start;
    public String currentWord;
    public ArrayList<Node> dictionary; 
    public ArrayList<String> possibleGuesses;

    //Default constructer
    public Trie() {
        this.root = new Node();
        this.dictionary  = new ArrayList<Node>();
    }

    //Puts a given element into the TRIE using the Node class

    HashMap<Character, Node> children;
    public void insert(String wordIn) {
        
        
        Node currentNode = root;
        children = root.children;
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
            
            if(searchTrie(wordIn) != null){
                temp.freq++;
                //System.out.println(temp.freq + " " + wordIn);
            }

        }
    }

    //Searching the tree when given a word to find where the children are
    private Map<Character, Node> below;
    
    public Node searchTrie(String word) {
        Node temp = null;
        below = root.children; 

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
    
    private ArrayList<Node> nodeQ = new ArrayList<Node>();
    private Node current;
    //Find takes in a valid node and then spits out all the children with similar prefixes
    public void find(Node temp, int num) {
        nodeQ.clear();
        nodeQ = new ArrayList<Node>();
        nodeQ.add(temp);
        
        while(!nodeQ.isEmpty()) {
            //Removing the first node
            current = nodeQ.remove(0); 
            if(current != null){            
                if(current.isFullWord == true) {dictionary.add(current);} // Add node to dictionary if it is a full word
                
                //Adding all children 
                for(Node i : current.children.values()){
                    nodeQ.add(nodeQ.size(),i);
                }
            }
        }
    }
    
    private Node newNode;
    private String word;
    private Node temp;
    
    public ArrayList<String> getPossibleGuesses(String currentWord, int location){
        //Filling the dictionary of the trie with the 
        newNode = this.searchTrie(currentWord);
        //System.out.println(newNode.initialC);
        this.find(newNode, location);
        
        possibleGuesses = new ArrayList<String>(dictionary.size());
        
        //Sorting the dictionary based on frequency
        Collections.sort(dictionary, Collections.reverseOrder());
        
        word = "";
        for(Node node : dictionary){
            temp = node;
            while(temp != root){
                word = Character.toString(temp.initialC) + word;
                temp = temp.parent;
            }
            possibleGuesses.add(word);
            word = ""; // resetting the word to nothing
        }
        return possibleGuesses;
    }
}