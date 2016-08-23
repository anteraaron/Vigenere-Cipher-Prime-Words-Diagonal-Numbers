/*Programmed by Anter Aaron M. Custodio
 * 2011-42733
 * Bs-Comsci
 * 
 * This program encrypts and decrypts using Vigenere Cipher , Prime Words and Diagonal Numbers
 * MiniMp2
*/


import java.util.*;

public class WeirdCrypts {
	
	final static String[] arrayTableUpper= {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
	final static String[] arrayTableLower= {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
	//declaring the Vigenere Cipher table

	
	public static String lastDecrypt(String message, String key){
		
		String messageNoSpace = message.replaceAll("\\s", "");
		String keyNoSpace = key.replaceAll("\\s", "");
		
		if(messageNoSpace.length()>keyNoSpace.length()){ // makes the key and the word have equal length. repeats the key if its less than the message
			
			while(messageNoSpace.length()!=keyNoSpace.length()){ //key will be repeated until it became equal to the message
				
				int diff = messageNoSpace.length() - keyNoSpace.length(); // calculates the difference of the length of strings
				
				if(diff>=keyNoSpace.length()){
					keyNoSpace = keyNoSpace + keyNoSpace; //copy the whole string again if the diff is greater than the length of key
				}
				else{
					keyNoSpace = keyNoSpace + keyNoSpace.substring(0, diff);
				}
				
			}//end of while	
		}
		else{ // if the key is longer than the message, removes the excess key
			
			int diff = keyNoSpace.length() - messageNoSpace.length();
			keyNoSpace = keyNoSpace.substring(0, (keyNoSpace.length()-diff));
		}
		int ctr = 1; //used as counter for the keyArray
		String[] keyArray = keyNoSpace.split("");
		String[] words = message.split(" +"); // ignores space, gets word by word per array index
		message = "";
		for(int i = 0; i<words.length;i++){
			String[] letters = words[i].split("");
			
			for(int j = 1; j<letters.length;j++){
				message = message + reverseVigenere(letters[j],keyArray[ctr]);
				ctr++;
			}
			
			if(i!=words.length-1){
				message = message + " "; //adds a space after the word except at the last word
			}
		}
		
		return message;
	}
	
	
	public static String returnEnds(String word){
		
		String[] letters = word.split(""); //splits the word into letters
		String[] arranged = new String[letters.length];
		int ctr = 1;
		if(letters.length<4){ //words stays the same
			word = "";
			for(int i = 1; i<letters.length;i++){
				word = word + letters[i];
			}
			return word;
		}
		else if(letters.length==4){ //manipulate
			arranged[1] = letters[1];
			arranged[2] = letters[3];
			arranged[3] = letters[2];
		}
		else if(letters.length%2==0){//since index starts in zero. this function indicates if it is an odd
			for(int i = 1;i<=letters.length/2;i++){//swap
				
				if(ctr==letters.length-1){
					arranged[i] = letters[ctr];
					break;
				}
				
				arranged[i] = letters[ctr];
				ctr++;
				arranged[arranged.length-i] = letters[ctr];
				ctr++;
			}
			
		}
		else{
			for(int i = 1;i<=letters.length/2;i++){//swap
				arranged[i] = letters[ctr];
				ctr++;
				arranged[arranged.length-i] = letters[ctr];
				ctr++;
			}
		
		}
		
		word = "";
		for(int i = 1; i<arranged.length;i++){
			word = word + arranged[i]; //convert to string
		}
	
		return word;
	}
	
	
	
	public static String cornerDecrypt(String message, String key){
		
		String[] words = message.split(" +");
		
		for(int i = 1;i<words.length;i++){
			if((((i*2)-1) * (i*2)-1)>=words.length){
				break;
			}
			else{
				words[(((i*2)-1) * (i*2))-1] = returnEnds(words[(((i*2)-1) * (i*2))-1]); //gets the alternate ends of the diagonal word
			}
			
		}
		
		message = ""; //reset to nothing
		for(int i = 0;i<words.length;i++){
			message = message + words[i]; // save the newly encrypted words
			
			if(i!=(words.length-1)){
				message = message +  " "; //adds white spaces w/o trailing white spaces
			}
		}
		return message;
	}
	
	public static String reverseVigenere(String primeL, String keyL){//getting the equivalent plain text
		String equivalent = "";
		int key = 0;
		int vig = 0;
		
		
		for(int i = 0;i<arrayTableLower.length;i++){//key index
			if(keyL.equalsIgnoreCase(arrayTableLower[i])){
				key = i;
			}
		}
		
		for(int i = 0; i<arrayTableLower.length;i++){
			
			if(primeL.equals(arrayTableLower[(key + i)%26])){
				vig = i;
				equivalent = equivalent + arrayTableLower[vig];
			}
			else if(primeL.equals(arrayTableUpper[(key+i)%26])){
				vig = i;
				equivalent = equivalent + arrayTableUpper[vig];
			}
			
		}
		
		return equivalent;
	}
	
	
	public static String primeDecrypt(String message, String key){
		String[] words = message.split(" +");
		String primeWords = "";
		StringBuffer a = new StringBuffer(key);
		key = a.reverse().toString();
		ArrayList<Integer> tildeWordsIndex = new ArrayList<Integer>();//used in storing index of words with ~
		
		
		for(int i=0;i<words.length;i++){
			String[] letters = words[i].split("");
		
			if(letters[1].equals("~")){
				primeWords = primeWords + words[i].replace("~","") + " ";
				tildeWordsIndex.add(i);
			}
		}
		
		String primeNoSpace = primeWords.replaceAll("\\s", "");
		String keyNoSpace = key.replaceAll("\\s", "");
		if(primeNoSpace.length()>keyNoSpace.length()){ // makes the key and the word have equal length. repeats the key if its less than the message
			
			while(primeNoSpace.length()!=keyNoSpace.length()){ //key will be repeated until it became equal to the message
				
				int diff = primeNoSpace.length() - keyNoSpace.length(); // calculates the difference of the length of strings
				
				if(diff>=keyNoSpace.length()){
					keyNoSpace = keyNoSpace + keyNoSpace; //copy the whole string again if the diff is greater than the length of key
				}
				else{
					keyNoSpace = keyNoSpace + keyNoSpace.substring(0, diff);
				}
				
			}//end of while	
		}
		else{ // if the key is longer than the message, removes the excess key
			
			int diff = keyNoSpace.length() - primeNoSpace.length();
			keyNoSpace = keyNoSpace.substring(0, (keyNoSpace.length()-diff));
		}
		//converting the letters
		String[] keyArray = keyNoSpace.split("");
		String[] primeArray = primeWords.split(" +");
		primeWords = "";
		int ctr = 1;
		for(int i = 0;i<primeArray.length;i++){
			String[] primeLetters = primeArray[i].split("");
			
			for(int j = 1; j<primeLetters.length; j ++){
				
				primeWords = primeWords + reverseVigenere(primeLetters[j],keyArray[ctr]);
				ctr++;
			}
			
			primeWords = primeWords + " ";
		}
		
		String[] replacement = primeWords.split(" +");
		
		for(int i = 0;i<replacement.length;i++){
			words[tildeWordsIndex.get(i)] = replacement[i];
		}
		message = "";
		for(int i = 0;i<words.length;i++){
			message = message + words[i] + " "; //plug in the newly decrypted word.
		}
		
		return message;
				}//end of primeDecrypt
	
	
	public static String weirdDecrypt(String message, String key){//start of the decryption
		ArrayList<Integer> periodsIndex = new ArrayList<Integer>();//used in storing index of period
		ArrayList<Integer> spacesIndex = new ArrayList<Integer>();//used in storing index of white spaces
		ArrayList<Integer> tildeIndex = new ArrayList<Integer>();//used in storing index of tilde
		
		for(int i=0;i<message.length();i++){
			char messageChar = message.charAt(i);
			if(Character.toString(messageChar).equals("~")){
				tildeIndex.add(i); //saves the index of tilde
			}
		}
		
		message = message.replaceAll("~","");
		
		for(int i=0;i<message.length();i++){
			char messageChar = message.charAt(i);
			if(Character.toString(messageChar).equals(".")){
				periodsIndex.add(i); //saves the index of period
			}
		}
		message = message.replaceAll("\\.+","");//removes all period
		
		for(int i=0;i<message.length();i++){
			char messageChar = message.charAt(i);
			if(Character.toString(messageChar).equals(" ")){
				spacesIndex.add(i); //saves the index of spaces
			}
		}
		
		StringBuilder builder = new StringBuilder(message);
		for(int i = 0; i<tildeIndex.size();i++){
			builder.insert(tildeIndex.get(i), "~"); // reinsert tilde
		}
		message = builder.toString();
		
		
		message = primeDecrypt(message,key);
		message = cornerDecrypt(message,key);
		message = lastDecrypt(message,key);

		message = message.replaceAll(" ",""); //removes spaces
		
		StringBuilder builder2 = new StringBuilder(message);
		
		for(int i = 0; i<spacesIndex.size();i++){
			builder2.insert(spacesIndex.get(i), " "); // reinsert spaces
		}
		message = builder2.toString();
		
		for(int i = 0; i<periodsIndex.size();i++){ // reinsert periods
			builder2.insert(periodsIndex.get(i), ".");
		}
		message = builder2.toString();
		
		return message;
		
	}
		
	public static boolean isPrime(int n) {//checks whether an int is prime or not.
		    //check if n is a multiple of 2
		if (n%2==0){
			return false;
		}
	 //if not, then just check the odds
		 for(int i=3;i*i<=n;i+=2) {
			 if(n%i==0){
			 return false;
			 }
		 }
		    return true;
	}

	
	public static String primeEncrypt(String message, String key){ //prime encryption
		
		int total = 0;
		String[] words = message.split(" +");
		ArrayList<Integer> primeWordsIndex = new ArrayList<Integer>();//used in storing index of primewords
		String primeWords = "";
		
		for(int i = 0;i<words.length;i++){
			total = 0;
			String[] letters = words[i].split("");
		
			for(int j = 1; j<letters.length; j++){
				
				for(int k = 0;k<arrayTableUpper.length;k++ ){
					
					if(letters[j].equalsIgnoreCase(arrayTableUpper[k])==true){ //computes the total sum of the word
						total = total + k+1;
						break;
					}
				}
			}
		
			if (isPrime(total)==true){
			primeWords = primeWords + words[i] + " ";
			primeWordsIndex.add(i); //stores the index of the primeword
			}	
		}
		StringBuffer a = new StringBuffer(key);
		String reversedKey = a.reverse().toString(); //reversing the key
		message = initialEncryption(primeWords,reversedKey);//calls weird encrypt again but this time with reversed key
		String[] replacement = message.split(" +"); // gets the encrypted primewords and put in into array
		
		
		for(int i = 0;i<primeWordsIndex.size();i++){
			
			words[primeWordsIndex.get(i)] ="~" + replacement[i]; //replaces the prime words with the new encrypted prime words
		}
		message = "";//initialize to nothing again
		for(int i = 0;i<words.length;i++){
			message = message + words[i];//input the newly changed words
			
			if(i!=words.length-1){
				message = message + " "; //adds spaces without trailing
			}
		}
		
		return message;
	}//end of prime encrypt
	
	
	
	public static String alternateEnds(String word){//gets alternate ends
		
		String[] letters = word.split(""); //splits the word into letters
		String[] replaced = new String[letters.length];//store the newly sorted letters
		int ctr = 1;
		if(letters.length==1){ //there's no need to take the end
			return word;
		}
		else{ //getting alternate ends
		
			for(int i = 1;i<letters.length;i++){
				
				if(i%2==1){
					replaced[i]=letters[ctr];
				}
				else{
					replaced[i]=letters[letters.length-ctr];
					ctr++;
				}
			}
		}
		word = ""; //set word to nothing
		for(int i = 1;i<replaced.length;i++){ //saves the  new word
			word = word+replaced[i];
		}
		return word;
	}//end of alternateEnds
	
	public static String cornerEncrypt(String message){
		
		String[] words = message.split(" +");
		
		for(int i = 1;i<words.length;i++){
			if((((i*2)-1) * (i*2)-1)>=words.length){
				break;
			}
			else{
				words[(((i*2)-1) * (i*2))-1] = alternateEnds(words[(((i*2)-1) * (i*2))-1]); //gets the alternate ends of the diagonal word
			}
			
		}
		
		message = ""; //reset to nothing
		for(int i = 0;i<words.length;i++){
			message = message + words[i]; // save the newly encrypted words
			
			if(i!=(words.length-1)){
				message = message +  " "; //adds white spaces w/o trailing white spaces
			}
		}
		
		return message;
	}//end of cornerEncrypt
	
	public static String firstEncrypt(String letter, String key){ //base on Vigenere cipher table

		int row = 0;
		int column = 0;
		String message = "";
	
		for(int i = 0 ; i<arrayTableUpper.length;i++){ //loop in identifying the corresponding letter according to vigenere cipher table
			
			if(key.equalsIgnoreCase(arrayTableUpper[i])){
				column = i; //detects key letter
			}
			
		}
		for(int i = 0;i<arrayTableUpper.length;i++){
			if(letter.equals(arrayTableUpper[i])){ // if the letter is uppercase
				row = i; //detects message letter
				message = message + arrayTableUpper[(row+column)%26]; //modulo operator is needed so that even if the sum of rows and columns exceeded, error will not occur
			}
			else if(letter.equals(arrayTableLower[i])){ //if the letter is lowercase
				row = i;
				message = message + arrayTableLower[(row+column)%26];
			}
		}
		
			return message;
	}//end of first encryption
	
	
	
	public static String initialEncryption(String message, String key){
		
		String messageNoSpace = message.replaceAll("\\s", "");
		String keyNoSpace = key.replaceAll("\\s", "");
		
		if(messageNoSpace.length()>keyNoSpace.length()){ // makes the key and the word have equal length. repeats the key if its less than the message
			
			while(messageNoSpace.length()!=keyNoSpace.length()){ //key will be repeated until it became equal to the message
				
				int diff = messageNoSpace.length() - keyNoSpace.length(); // calculates the difference of the length of strings
				
				if(diff>=keyNoSpace.length()){
					keyNoSpace = keyNoSpace + keyNoSpace; //copy the whole string again if the diff is greater than the length of key
				}
				else{
					keyNoSpace = keyNoSpace + keyNoSpace.substring(0, diff);
				}
				
			}//end of while	
		}
		else{ // if the key is longer than the message, removes the excess key
			
			int diff = keyNoSpace.length() - messageNoSpace.length();
			keyNoSpace = keyNoSpace.substring(0, (keyNoSpace.length()-diff));
		}
		int ctr = 1; //used as counter for the keyArray
		String[] keyArray = keyNoSpace.split("");
		String[] words = message.split(" +"); // ignores space, gets word by word per array index
		message = "";
		for(int i = 0; i<words.length;i++){
			String[] letters = words[i].split("");
			
			for(int j = 1; j<letters.length;j++){
				message = message + firstEncrypt(letters[j],keyArray[ctr]);
				ctr++;
			}
			
			if(i!=words.length-1){
				message = message + " "; //adds a space after the word except at the last word
			}
		}
		
		return message;
		
	}//end of weirdEncrypt
	
	public static String weirdEncrypt(String message, String key){
		ArrayList<Integer> periodsIndex = new ArrayList<Integer>();//used in storing index of period
		ArrayList<Integer> spacesIndex = new ArrayList<Integer>();//used in storing index of white spaces
		ArrayList<Integer> tildeIndex = new ArrayList<Integer>();//used in storing index of tilde
		
		
		for(int i=0;i<message.length();i++){
			char messageChar = message.charAt(i);
			if(Character.toString(messageChar).equals(".")){
				periodsIndex.add(i); //saves the index of period
			}
		}
		message = message.replaceAll("\\.+","");//removes all period
		
		for(int i=0;i<message.length();i++){
			char messageChar = message.charAt(i);
			if(Character.toString(messageChar).equals(" ")){
				spacesIndex.add(i); //saves the index of spaces
			}
		}
		
		message = initialEncryption(message, key);
		//System.out.println("Initial Encryption: ");//prints initial encryption
		//System.out.println(message);
		message = cornerEncrypt(message); //calls corner encrypt
		//System.out.println("\nCorner Encryption: ");//prints corner encrypt
		//System.out.println(message);
		message = primeEncrypt(message,key);
		//System.out.println("\nPrime Encryption: ");//prints corner encrypt
		//System.out.println(message);
		//System.out.println();
		
		for(int i=0;i<message.length();i++){
			char messageChar = message.charAt(i);
			if(Character.toString(messageChar).equals("~")){
				tildeIndex.add(i); //saves the index of spaces
			}
		}
		
		message = message.replaceAll("~", ""); //removes tilde
		message = message.replaceAll(" ",""); //removes spaces
		
		StringBuilder builder = new StringBuilder(message);
		
		for(int i = 0; i<spacesIndex.size();i++){
			builder.insert(spacesIndex.get(i), " "); // reinsert spaces
		}
		message = builder.toString();
		
		for(int i = 0; i<periodsIndex.size();i++){ // reinsert periods
			builder.insert(periodsIndex.get(i), ".");
		}
		message = builder.toString();
		
		for(int i = 0; i<tildeIndex.size();i++){ // reinsert periods
			builder.insert(tildeIndex.get(i), "~");
		}
		message = builder.toString();
		
		return message;
	
	}
	
	public static void menu(){ //opens the menu interface
		try{ //checks if the method throws an input mismatch exception
			int choice = 0;
			Scanner input = new Scanner(System.in);
			while(choice!=3){
				choice = 0;		
				System.out.println("What do you want to do? \n[1]. Encrypt Message\n[2]. Decrypt Message\n[3]. Exit");
				choice=Integer.parseInt(input.nextLine());
				switch(choice){
					case 1:
							System.out.print("Input the message you want to encrpyt: ");
							String message = input.nextLine();
							System.out.print("\nInput the key: "); //key to from the user
							String key = input.nextLine();
							message = weirdEncrypt(message, key);
							System.out.println("\nTHE FINAL ENCRYPTED MESSAGE: ");
							System.out.println(message + "\n");
							break;
							
					case 2: 
							System.out.print("Input the message you want to Decrpyt: ");
							message = input.nextLine();
							System.out.print("\nInput the key: "); //key to from the user
							key = input.nextLine();
							message = weirdDecrypt(message, key);
							System.out.println("\nThe Decrypted word is:");
							System.out.println(message);
							System.out.println("");
							break;
					case 3: 
							System.out.println("Program Terminated...");
							break;
					default: System.out.println("There's no such choice in the menu!\n");	
				}//end of switch
				input.reset();
			}//end of while
			input.close();
		}catch(NumberFormatException e){
			System.out.println("Invalid Input!\n");
			menu(); //allows to input again after an invalid input format	
		}
		
	}//end of getInput
	
	public static void main(String[] args) {
		menu();//menu interface function.
		System.exit(0);
	}//end of main

}
