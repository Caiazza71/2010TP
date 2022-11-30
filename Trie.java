
import java.util.*;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/////////
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
    public boolean startsWith(String prefix) 
    {
        if(searchNode(prefix) == null) {return false;}
        else{return true;}
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
            else{return null;}
        }

        start = t;
        currentWord = str;
        dictionary.clear();
        return t;
    }


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
          dictionary.add(wrd);

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

     // here you can play with the order of the children

        for( int i=0;i<aloc.size();i++)
        {
            wordsFinderTraversal(node.children.get(aloc.get(i)), offset + 2);
        } 
  }

    void displayFoundWords()
    {
        System.out.println("_______________");
        for(int i=0;i<dictionary.size();i++)
        {
          System.out.println(dictionary.get(i));
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

        scanner.close();
    }
}
