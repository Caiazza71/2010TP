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
            else {
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
        ArrayList<Character> arr = new ArrayList<Character>();
        Set<Character> list = hello.below.keySet();
        Iterator runner = list.iterator();

        while (runner.hasNext()) {
            Character letter = (Character)runner.next();  
            arr.add(letter);
        } 

        for( int i = 0;i < arr.size(); i++) {
            find(hello.below.get(arr.get(i)), num + 2);
        } 
    }

    void print() {
        System.out.println("_______________");
        for(int i = 0;i < list.size(); i++) {
          System.out.println(list.get(i));
        } 
        System.out.println("________________");
    }

    public static void main(String[] args) throws FileNotFoundException {
        Trie tree;
        tree = new Trie();  
        Scanner scan = new Scanner(new File(args[0]));
        //Scanner scans = new Scanner(new File(args[1]));
        Scanner scanner = new Scanner(System.in);

        while(scan.hasNext()) {
            String word = scan.next();
            tree.insert(word);
        }
        String input = scanner.next();

        if( tree.begin(input)==true) {
            Node tn = tree.searchTrie(input);
            tree.find(tn,0);
            tree.print(); 
        }
    }
}
