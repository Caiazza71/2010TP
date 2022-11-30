
import java.util.*;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;


public class Trie {
    public Node root, start;
    public String currentWord;
    public ArrayList<String> dictionary; 

    public Trie() {
        this.root = new Node();
        this.dictionary  = new ArrayList<String>();
    }

    public void insert(String word) {
        Node currentNode = root;
        HashMap<Character, Node> children = root.children;

        for (Character initial : word.toCharArray()){
            Node temp;

            if(children.containsKey(initial)){
                temp = children.get(initial);
            } 
            else {
                temp = new Node(initial);
                temp.parent = currentNode;
                children.put(initial, temp);
            }

            children = temp.children;
            currentNode = temp;
            for (int i = 0; i < word.length(); i++) {
                if (i == word.length() - 1) {
                    temp.isFullWord = true;
                }
            }
        }
    }

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

  public void find(Node hello, int num) {

        if(hello.isFullWord == true) {
            Node n;
            n = hello;
            String s = currentWord;
            Stack<String> finder = new Stack<String>(); 

            while(n != start) {
                finder.push(Character.toString(n.initialC) );
                n = n.parent;
            }

            while(finder.empty() != true) {
                s = s + finder.pop();
            }
            dictionary.add(s);
        }
        ArrayList<Character> arr = new ArrayList<Character>();
        Set<Character> list = hello.children.keySet();
        Iterator<Character> runner = list.iterator();

        while(runner.hasNext()) {
            Character letter = (Character)runner.next();  
            arr.add(letter);
        } 

        for( int i = 0;i < arr.size(); i++) {
            find(hello.children.get(arr.get(i)), num + 2);
        } 
    }

    public boolean begin (String letter) {
        if(searchTrie(letter) != null) {
            return true;
        }
        else {
            return false;
        }
    }

    public void print() {
        for(int i = 0;i < dictionary.size(); i++) {
          System.out.println(dictionary.get(i));
        } 
    }

    public static void main(String[] args) throws FileNotFoundException {
        Trie tree;
        tree = new Trie();  
        Scanner scan = new Scanner(new File(args[0]));
        Scanner scanner = new Scanner(System.in);

        while(scan.hasNext()) {
            String word = scan.next();
            tree.insert(word);
        }
        String input = scanner.next();

        if( tree.begin(input)==true) {
        Node phrase = tree.searchTrie(input);
        tree.find(phrase, 0);
        tree.print(); 
        }
        scanner.close();
    }
}
