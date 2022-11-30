
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

        for (int i = 0; i < word.length(); i++){
            Node temp;
            char initial = word.charAt(i);

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
            if (i == word.length() - 1) {
                temp.isFullWord = true;
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

  public void find(Node temp, int num) {

        if(temp.isFullWord == true) {
            Node n;
            n = temp;
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
          System.out.println(dictionary.get(i).replaceAll("[^a-zA-Z]",  " ").split(" ")[0]);
        } 
    }

    public static void main(String[] args) throws FileNotFoundException {
        Trie tree;
        tree = new Trie();  
        //Trie oldMess;
        //oldMess = new Trie();
        Scanner scan = new Scanner(new File(args[0]));
        //Scanner scans = new Scanner(new File(args[1])); 
        Scanner scanner = new Scanner(System.in);

        

        while(scan.hasNext()) {
            String word = scan.next();
            tree.insert(word);
        }

       
        String input = scanner.next();
        /* 
        if(oldMess.begin(input)==true) {
        Node phrase = oldMess.searchTrie(input);
        oldMess.find(phrase, 0);
        oldMess.print(); 
        }
        */

        // Return list of words to tree to find the one with the greatest value
        scanner.close();
    }
}
