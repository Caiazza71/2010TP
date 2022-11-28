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


public class SmartWord
{
    String[] guesses = new String[3];  // 3 guesses from SmartWord

    // initialize SmartWord with a file of English words
    public SmartWord(String wordFile)
    {
        //This will be our DataIn.java class
    }

    // process old messages from oldMessageFile
    public void processOldMessages(String oldMessageFile)
    {
        //This will be our LikelyHood.java file
        
        //Store in a tree??
        //Make it super easy to go down the list of words
        //              A
        //      AA  AB  AC  AD  AE
        //And so on! only create as far as we need to. 
        //aka, there should always be a step at the bottom with no children,
        //   but wont always need to be a full word
        
    }

    // based on a letter typed in by the user, return 3 word guesses in an array
    // letter: letter typed in by the user
    // letterPosition:  position of the letter in the word, starts from 0
    // wordPosition: position of the word in a message, starts from 0
    public String[] guess(char letter,  int letterPosition, int wordPosition)
    {
	
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
// heyyyyyyyyyyy
    }

}
