/*
*	Description: Class checks spelling of words in a .txt file against a dictionary file 
*	Author: Elshadai Biru - etb2119
*	Date: 11/21/2016
*	
*/


import java.io.*;
import java.lang.*;
import java.util.*;

 class SpellChecker {
	static String readLine;
	static char c;
	static int linect;
	static String word;
	static HashSet<String> myDictionary;
	static StringBuilder tmp;
	static File dicFile;
	static File testFile;
	static char[] alph = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u','v', 'w', 'x', 'y', 'z'}; 
	static char[] nums = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
	static LinkedList<Character> list = new LinkedList<Character>();
	
	
	public static void main(String[] args) throws FileNotFoundException, IOException{
	myDictionary = new  HashSet<String>();
	for(int i=0; i < alph.length; i++){
		list.add(alph[i]); //creates list of alphabet characters
	}
	for(int i=0; i < nums.length; i++){
		list.add(nums[i]); //adds to list number characters
	}
	if (1 < args.length) {
		  dicFile  = new File(args[0]);
		  testFile = new File(args[1]); //reads files from command line arguments
		}
	
	try{
	BufferedReader br = new BufferedReader(new FileReader(dicFile));
	while ((readLine = br.readLine()) != null){
		myDictionary.add(readLine.toLowerCase()); //adds dictionary words to hashSet
	}
	}
	catch (IOException e) {
	    e.printStackTrace();
	}
	try {
	BufferedReader br = new BufferedReader(new FileReader(testFile));
	tmp = new StringBuilder();
	while ((readLine = br.readLine()) != null){ 
		linect++; //keeps track of line numbers
    for (int i = 0; i < readLine.length(); i++){
    	c = readLine.charAt(i);
    	c = Character.toLowerCase(c);
    	if(c == ' '){		//extracts word from stored characters when space character is encountered
    		if(i != 0){
    		word = tmp.toString();
    		checkWord(word); //method that checks word against dictionary, offers suggestions
    		tmp.setLength(0); //clears StringBuilder object
    		}
    	}
    	else if (!(list.contains(c))){ //if character is punctuation 
    		if(i - 1 < 0);
    		else if(readLine.charAt(i-1)== ' ');
    		else if(readLine.charAt(i+1)== ' '); //checks for header or tailer
    		else tmp.append(c); 
    	}
    	else {
    		tmp.append(c); //adds alphabets and numbers to word
    	}
    }
    if(tmp.length()!=0){
    word = tmp.toString();
	checkWord(word);
	tmp.setLength(0);
    }
}
	}
	
	catch (IOException e) {
	    e.printStackTrace();
	}
	
	
}
	
    /*
     * Summary: Adds character to misspelled word to offer suggestions
     * Parameters: String word, word to be processed
     * Return: void
     */
	public static void addChar(String word){
		
		for(int i = 0; i <= word.length(); i++){ //goes through each position in word
			String newWord = "";
			for(char c: alph){ //iterates through each character in alphabet 
			newWord = "";
			if(i==0){
				newWord = c + word; //merge operation
			}
			else if (i == word.length()){
				newWord = word + c; //merge operation
			}
			else {
				newWord = word.substring(0, i);
				newWord += c;
				newWord += word.substring(i);   //merge operation
			}
			if(myDictionary.contains(newWord)){
				System.out.println("Suggestion: " + newWord); //prints out suggestions
			}
			}
		}
	}
	
	/*
     * Summary: Removes character from misspelled word to offer suggestions
     * Parameters: String word, word to be processed
     * Return: void
     */
	public static void removeChar(String word){
		for(int i = 0; i < word.length(); i++){ //Goes through each position in word
			String newWord = "";
			if(i==0){
				newWord = word.substring(1);
			}
			else if (i == word.length()-1){
				newWord = word.substring(0, i);
			}
			else {
			newWord = word.substring(0,i) + word.substring(i + 1, word.length());
			}
			if(myDictionary.contains(newWord)){
				System.out.println("Suggestion: " + newWord);
			}
			
		}
	}
	
	/*
     * Summary: Switches adjacent characters of misspelled word to offer suggestions
     * Parameters: String word, word to be processed
     * Return: void
     */
	public static void switchChar (String word){
		String newWord;
		char char1;
		char char2;
		StringBuilder tmp;
		for(int i = 0; i < word.length()-1; i++){
			tmp = new StringBuilder(word);
			char1 = word.charAt(i);
			char2 = word.charAt(i+1);
			tmp.setCharAt(i, char2);
			tmp.setCharAt(i+1, char1);
			newWord = tmp.toString();
			if(myDictionary.contains(newWord)){
				System.out.println("Suggestion: " + newWord);
			}
		}	
	}
	/*
     * Summary: Checks if words are misspelled, calls methods to offer suggestions
     * Parameters: String word, word to be processed
     * Return: void
     */
	public static void checkWord (String word){
		if(!(myDictionary.contains(word))){
			System.out.println("Misspelled word " + word + " at line " + linect);
			addChar(word);
			removeChar(word);
			switchChar(word);
		}
	}
}