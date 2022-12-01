/*

  Authors (group members): Maxwell Caiazza, Taylor Carlson, Ava Crocker
  Email addresses of group members: mcaiazza2021@fit.edu, tcarlson2021@fit.edu, acrocker2021@fit.edu
  Group name: Node Wizards

  Course: Data Structures and Algorithms, CSE 2010
  Section: Section 3

  Description of the overall algorithm:
*/

/*
 * Extra Notes
 * 
 * When guessing, we can base the guess off the most likely from the UDTree and then
 *  return to the dictionary if it gives nothing of importance
 * 
 */
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

public class SmartWord 
{
    
    String currentWord = "";
    int wordCnt = 0;
    
    Trie dictionary;
    Trie oldMess;
    
    String[] guesses = new String[3];  // 3 guesses from SmartWord

    // initialize SmartWord with a file of English words
    public SmartWord(String wordFile) throws FileNotFoundException
    {
        //This will be our DataIn.java class
        dictionary = new Trie();
        Scanner in = new Scanner(new File(wordFile));

        while(in.hasNext()){
            dictionary.insert(in.next());
        }
    }

    // process old messages from oldMessageFile
    public void processOldMessages(String oldMessageFile) throws FileNotFoundException
    {
        oldMess = new Trie();
        Scanner scans = new Scanner(new File(oldMessageFile)); 

        while(scans.hasNext()) {
            String word = scans.next();
            oldMess.insert(word);
        }
    }

    // based on a letter typed in by the user, return 3 word guesses in an array
    // letter: letter typed in by the user
    // letterPosition:  position of the letter in the word, starts from 0
    // wordPosition: position of the word in a message, starts from 0
    public String[] guess(char letter,  int letterPosition, int wordPosition)
    {
        //If theres a new word it clears where it was
        if(wordCnt == wordPosition){ // same word found
            currentWord += letter;
        }else{ // new word found
            wordCnt++;
            currentWord = letter + "";
        }
        
        if(oldMess.searchTrie(currentWord) != null) {
        // if it finds the word in the older messages
            Node phrase = oldMess.searchTrie(currentWord);
            oldMess.find(phrase, 0);          
            
            if(oldMess.dictionary.size() > 3){ // All guesses coming from old Messages
                ArrayList<String> words = new ArrayList<String>();
                ArrayList<Integer> occurances = new ArrayList<Integer>();

                int location;

                for(String word : oldMess.dictionary){
                    if(words.contains(word)){
                        location = words.indexOf(word);
                        occurances.add(location, occurances.get(location) + 1);
                    }else{
                        words.add(word);
                        occurances.add(1);
                    }
                }

                for(int k = 0; k < 3; k ++){
                    int largest = 0;
                    for(int i = 0; i < occurances.size(); i++){
                        if(occurances.get(i) >= largest){
                            largest = i;
                        }
                    }
                    guesses[k] = words.get(largest);
                }
            }else{ // 1 or two guesses coming from old messages
                int i = 0;
                for(String word : oldMess.dictionary){
                    guesses[i] = word;
                    i++;
                }
                phrase = dictionary.searchTrie(currentWord);
                dictionary.find(phrase, 0);   
                Random rand = new Random();
                int index;
                for(; i < 3 ; i++ ){
                    index = rand.nextInt(dictionary.dictionary.size());
                    guesses[i] = dictionary.dictionary.get(index);
                }
            }

        }else{ // no words found in oldMessages, so it goes to dict
            Node phrase = dictionary.searchTrie(currentWord);
                dictionary.find(phrase, 0);   
                Random rand = new Random();
                int index;
                for(int i = 0; i < 3 ; i++ ){
                    index = rand.nextInt(dictionary.dictionary.size());
                    guesses[i] = dictionary.dictionary.get(index);
                }
        }
        
        oldMess.dictionary.clear();
        dictionary.dictionary.clear();
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
        if (isCorrectGuess == false) {
            if (correctWord != null) {
                isCorrectGuess = true;
            }
        }
        return;
    }

}
