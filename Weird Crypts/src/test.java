import java.util.*;
public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("hello");
		Scanner input  = new Scanner(System.in);
		String word = input.nextLine();
		String[] letters = word.split(""); //splits the word into letters
		String[] arranged = new String[letters.length];
		int ctr = 1;
		if(letters.length<4){ //words stays the same
			word = "";
			for(int i = 1; i<letters.length;i++){
				word = word + letters[i];
			}
			System.out.println(word);
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
	
		System.out.println(word);
		
		//Nrllz Suhgz Unhjposs Kmzbbhav nmrwm ki
		
		
	}

}
