/*

  Authors (group members): Maxwell Caiazza, Taylor Carlson, Ava Crocker
  Email addresses of group members: mcaiazza2021@fit.edu, tcarlson2021@fit.edu, acrocker2021@fit.edu
  Group name: Node Wizards

  Course: Data Structures and Algorithms, CSE 2010
  Section: Section 3

  Description of the overall algorithm: Recommends 3 words in a message based on past messages and a dictionary
*/

/*
 * Extra Notes
 * 
 * When guessing, we can base the guess off the most likely from the UDTree and then
 *  return to the dictionary if it gives nothing of importance
 * 
 */

 // Needed imports
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class SmartWord {
    
    // Creates variables later needed in the class
    ArrayList<String> prevGuesses = new ArrayList<String>();
    String currentWord = "";
    int wordCnt = 0;
    Trie trie;
    
    String[] guesses = new String[3];  // 3 guesses from SmartWord

    // Initialize SmartWord with a file of English words
    public SmartWord(String wordFile) throws FileNotFoundException {
        // Creates a trie to store words
        trie = new Trie();
        Scanner in = new Scanner(new File(wordFile));

        // Inserts the words into the trie and sorts them
        while(in.hasNext()){
            trie.insert(in.next().replaceAll("[^a-zA-Z]",  ""));
        }
    }

    // process old messages from oldMessageFile
    public void processOldMessages(String oldMessageFile) throws FileNotFoundException {
        // Creates a trie to store old messages
        Scanner scan = new Scanner(new File(oldMessageFile)); 

        // Inserts the words into the trie and sorts them
        while(scan.hasNext()) { 
            //automatically removes all extra bits from
            trie.insert(scan.next().replaceAll("[^a-zA-Z]",  ""));
        }
    }

    // based on a letter typed in by the user, return 3 word guesses in an array
    // letter: letter typed in by the user
    // letterPosition:  position of the letter in the word, starts from 0
    // wordPosition: position of the word in a message, starts from 0
    public String[] guess(char letter,  int letterPosition, int wordPosition) {
        //If theres a new word it clears where it was
        if(wordCnt == wordPosition){ // same word found
            currentWord += letter;
        }else{ // new word found
            wordCnt++;
            currentWord = letter + "";
        }
        guesses = new String[3];
        
        //Filling the dictionary of the trie with the 
        Node newNode = trie.searchTrie(currentWord);
        trie.find(newNode, 0);
        ArrayList<String> possibleGuesses = trie.getPossibleGuesses();
        
        while(guesses.length < 3){
            if(!prevGuesses.contains(possibleGuesses.get(0))){ // if its not in prev guesses
                guesses[guesses.length] = possibleGuesses.get(0);
                possibleGuesses.remove(0);
            }else{ // If it finds the guess already in prevguesses
                possibleGuesses.remove(0);
            }
        }
        
        //Debugging Printing That helps track what we are guessing
        for(String word : guesses){
            System.out.print(word +" ");
        }
        System.out.println();
        
        return guesses;
        
    }

    // feedback on the 3 guesses from the user
    // isCorrectGuess: true if one of the guesses is correct
    // correctWord: 3 cases:
    // a.  correct word if one of the guesses is correct
    // b.  null if none of the guesses is correct, before the user has typed in 
    //            the last letter
    // c.  correct word if none of the guesses is correct, and the user has 
    //            typed in the last letter
    // That is:
    // Case       isCorrectGuess      correctWord   
    // a.         true                correct word
    // b.         false               null
    // c.         false               correct word
    public void feedback(boolean isCorrectGuess, String correctWord)        
    {
        
        //Debugging prints
        System.out.println("--------");
        System.out.println(isCorrectGuess);
        System.out.println(correctWord);
        System.out.println("--------");
        
        
        
        if (isCorrectGuess && correctWord != null) { // Case A
            prevGuesses.clear();
            return;
        }else if(!isCorrectGuess && correctWord == null){ // Case B
            for(String word : guesses){
                prevGuesses.add(word);
            }
            return;
        }else{// Case C
            prevGuesses.clear();
            return;
        }
    
    }

}
