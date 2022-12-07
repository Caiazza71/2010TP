/*
* Citation:
* Data Structures and Algorithms in Java, Sixth Edition 
* Chapter 13.3
* Written By:
* Maxwell Caiazza, Ava Crocker, Taylor Carlson
*/


import java.util.ArrayList; // for arrays
import java.util.Collections; // for sorting arrays

//Imports to get rid of \/\/\/\/\/\/
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Iterator;

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

    public void insert(String wordIn) {
        Node currentNode = root;
        HashMap<Character, Node> children = root.children;
        //String word = wordIn.replaceAll("[^a-zA-Z]",  "");
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
            /* 
            if (temp.isFullWord && temp.initialC == wordIn.charAt(wordIn.length() - 1)){
                System.out.println(wordIn + " " + temp.initialC + " " + wordIn.charAt(wordIn.length() - 1));
                
                temp.freq++;
                System.out.println(temp.freq);
            }
            */
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
    
    private ArrayList<Node> nodeQ;
    //Find takes in a valid node and then spits out all the children with similar prefixes
    public void find(Node temp, int num) {
        
        nodeQ = new ArrayList<Node>();
        nodeQ.add(temp);
        Node current;
        
        while( !nodeQ.isEmpty()) {
            //Removing the first node 
            current = nodeQ.remove(0);
            if(current.isFullWord == true) {dictionary.add(temp);} // Add node to dictionary if it is a full word
            
            //Adding all children 
            for(Node i : current.children.values()){
                nodeQ.add(nodeQ.size(),i);
            }
        }
    }
    
    public ArrayList<String> getPossibleGuesses(String currentWord, int location){
        
        //Filling the dictionary of the trie with the 
        Node newNode = this.searchTrie(currentWord);
        //System.out.println(newNode.initialC);
        this.find(newNode, location);
        
        possibleGuesses = new ArrayList<String>();
        
        //Sorting the dictionary based on frequency
        Collections.sort(dictionary);
        Node temp;
        String word = "";
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