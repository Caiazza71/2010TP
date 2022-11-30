
// CHANGEEE STUFFFF
import java.util.*;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;


public class Trie
{
    private Node root;
    ArrayList<String> words; 
    Node prefixRoot;
    String curPrefix;

    public Trie()
    {
        root = new Node();
        words  = new ArrayList<String>();
    }

    // Inserts a word into the trie.
    public void insert(String word) 
    {
        HashMap<Character, Node> below = root.below;

        Node crntabove;

        crntabove = root;

        //cur below above = root

        for(int i=0; i<word.length(); i++)
        {
            char c = word.charAt(i);

            Node t;
            if(below.containsKey(c)){ t = below.get(c);}
            else
            {
            t = new Node(c);
            t.above = crntabove;
            below.put(c, t);
            }

            below = t.below;
            crntabove = t;

            //set leaf node
            if(i==word.length()-1)
                t.bottom = true;    
        }
    }
    public boolean search(String word)
    {
        Node t = searchNode(word);
        if(t != null && t.bottom){return true;}
        else{return false;}
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) 
    {
        if(searchNode(prefix) == null) {return false;}
        else{return true;}
    }

    public Node searchNode(String str)
    {
        Map<Character, Node> below = root.below; 
        Node t = null;
        for(int i=0; i<str.length(); i++)
        {
            char c = str.charAt(i);
            if(below.containsKey(c))
            {
                t = below.get(c);
                below = t.below;
            }
            else{return null;}
        }

        prefixRoot = t;
        curPrefix = str;
        words.clear();
        return t;
    }


    ///////////////////////////


  void wordsFinderTraversal(Node node, int offset) 
  {
        //  print(node, offset);

        if(node.bottom==true)
        {
          //println("leaf node found");

          Node altair;
          altair = node;

          Stack<String> hstack = new Stack<String>(); 

          while(altair != prefixRoot)
          {
            //println(altair.c);
            hstack.push(Character.toString(altair.initialC) );
            altair = altair.above;
          }

          String wrd = curPrefix;

          while(hstack.empty()==false)
          {
            wrd = wrd + hstack.pop();
          }

          //println(wrd);
          words.add(wrd);

        }

         Set<Character> kset = node.below.keySet();
         //println(node.c); println(node.bottom);println(kset);
         Iterator itr = kset.iterator();
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


    void displayFoundWords()
    {
        System.out.println("_______________");
        for(int i=0;i<words.size();i++)
        {
          System.out.println(words.get(i));
        } 
        System.out.println("________________");

    }

    public static void main(String[] args) throws FileNotFoundException {
        Trie prefixTree;
        prefixTree = new Trie();  
        Scanner scan = new Scanner(new File(args[0]));
        Scanner scanner = new Scanner(System.in);

        while(scan.hasNext()) {
            String word = scan.next();
            prefixTree.insert(word);
        }
        String input = scanner.next();

        if( prefixTree.startsWith(input)==true) {
        Node tn = prefixTree.searchNode(input);
        prefixTree.wordsFinderTraversal(tn,0);
        prefixTree.displayFoundWords(); 
        }

    }
}
