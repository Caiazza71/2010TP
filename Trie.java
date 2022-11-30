import java.util.*;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;


public class Trie {

    public Node root, start;
    public String currentWord;
    public ArrayList<String> words; 


    public Trie() {

      this.root = new Node();
      this.words  = new ArrayList<String>();

    }

    public void insert(String word) {
    
        Node currentNode = root;
        HashMap<Character, Node> children = root.children;

        for(Character start : word.toCharArray()){
          Node temp;

          if(children.containsKey(start)){
            temp = children.get(start);
          } else {
            temp = new Node(start);
            temp.parent = currentNode;
            children.put(start, temp);
          }

          children = temp.children;
          currentNode = temp;

          for(int i = 0; i < word.length(); i++) {
            if (i == word.length() - 1) {
              temp.isFullWord = true;
            }
          }

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

    public Node searchNode(String str)
    {
        Map<Character, Node> children = root.children; 
        Node t = null;
        for(int i=0; i<str.length(); i++)
        {
            char c = str.charAt(i);
            if(children.containsKey(c))
            {
                t = children.get(c);
                children = t.children;
            }
        }

        start = t;
        currentWord = str;
        words.clear();
        return t;
    }

  void find(Node hello, int num) {
        if (hello.bottom == true) {
            String s = current;
            Stack<String> finder = new Stack<String>(); 
            Node n;
            n = hello;

    ///////////////////////////

  void wordsFinderTraversal(Node node, int offset) 
  {
        //  print(node, offset);

        if(node.isFullWord==true)
        {
          //println("leaf node found");

          Node altair;
          altair = node;

          Stack<String> hstack = new Stack<String>(); 

          while(altair != start)
          {
            //println(altair.c);
            hstack.push(Character.toString(altair.initialC) );
            altair = altair.parent;
          }

          String wrd = currentWord;

          while(hstack.empty()==false)
          {
            wrd = wrd + hstack.pop();
          }

          //println(wrd);
          words.add(wrd);

        }

         Set<Character> kset = node.children.keySet();
         //println(node.c); println(node.isFullWord);println(kset);
         Iterator<Character> itr = kset.iterator();
         ArrayList<Character> aloc = new ArrayList<Character>();

        while(itr.hasNext())
        {
            Character ch = (Character)itr.next();  
            aloc.add(ch);
            //println(ch);
        } 

     // here you can play with the order of the below

        for( int i=0;i<aloc.size();i++)
        {
            wordsFinderTraversal(node.below.get(aloc.get(i)), offset + 2);
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
        Scanner scans = new Scanner(new File(args[1]));
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

        scanner.close();
    }
}
 }
