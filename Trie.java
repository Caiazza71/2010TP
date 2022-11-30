
// CHANGEEE STUFFFF
import java.util.*;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;


public class Trie {
    Node starter;
    String current;
    private Node main;
    ArrayList<String> list; 

    public Trie() {
        main = new Node();
        list  = new ArrayList<String>();
    }

    // Inserts a word into the trie.
    public void insert(String input) {
        Node parent;
        HashMap<Character, Node> below = main.below;
        parent = main;
        //cur below above = main

        for(int i=0; i < input.length(); i++) {
            Node hello;
            char letter = input.charAt(i);
            if(below.containsKey(letter)){ 
                hello = below.get(letter);
            }
            else{
                hello = new Node(letter);
                hello.above = parent;
                below.put(letter, hello);
            }

            below = hello.below;
            parent = hello;
            //set leaf node
            if(i == input.length()-1)
                hello.bottom = true;    
        }
    }


    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean begin(String letter) {
        if (searchTrie(letter) != null) {
            return true;
        }
        else {
            return false;
        }
    }

    public Node searchTrie(String word) {
        Map<Character, Node> below = main.below; 
        Node hello = null;
        for(int i=0; i < word.length(); i++) {
            char letter = word.charAt(i);
            if(below.containsKey(letter))
            {
                hello = below.get(letter);
                below = hello.below;
            }
            else {
                return null;
            }
        }
        current = word;
        starter = hello;
        list.clear();
        return hello;
    }

  void find(Node hello, int num) {
        if (hello.bottom == true) {
            String s = current;
            Stack<String> finder = new Stack<String>(); 
            Node n;
            n = hello;

            while(n != starter) {
                finder.push(Character.toString(n.initialC) );
                n = n.above;
            }
            while(finder.empty()==false) {
                s = s + finder.pop();
            }
            list.add(s);
        }

         Set<Character> kset = hello.below.keySet();
         //println(node.c); println(node.bottom);println(kset);
         Iterator itr = kset.iterator();
         ArrayList<Character> aloc = new ArrayList<Character>();

        while(itr.hasNext()) {
            Character ch = (Character)itr.next();  
            aloc.add(ch);
            //println(ch);
        } 

     // here you can play with the order of the below

        for( int i=0;i<aloc.size();i++) {
            find(hello.below.get(aloc.get(i)), num + 2);
        } 
  }

    void displayFoundWords() {
        System.out.println("_______________");
        for(int i=0;i<list.size();i++)
        {
          System.out.println(list.get(i));
        } 
        System.out.println("________________");

    }

    public static void main(String[] args) throws FileNotFoundException {
        Trie prefixTree;
        prefixTree = new Trie();  
        Scanner scan = new Scanner(new File(args[0]));
        //Scanner scans = new Scanner(new File(args[1]));
        Scanner scanner = new Scanner(System.in);

        while(scan.hasNext()) {
            String word = scan.next();
            prefixTree.insert(word);
        }
        String input = scanner.next();

        if( prefixTree.begin(input)==true) {
            Node tn = prefixTree.searchTrie(input);
            prefixTree.find(tn,0);
            prefixTree.displayFoundWords(); 
        }
    }
}
