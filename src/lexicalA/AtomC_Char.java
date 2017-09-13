package lexicalA;

import java.util.LinkedList;

public class AtomC_Char extends Value {
	private char c;
	//just one escaped character can be in an AtomiC char but in order to be conistent
	//with CT_STRING (where we can have more esc chars) we use here as well a LinkedList
	
	private LinkedList<Character> escapedChar;
	
	public AtomC_Char(char c){
		this.c = c;
	}
	
	public AtomC_Char(char c, LinkedList<Character> escapedChar){
		this.c = c;
		this.escapedChar = escapedChar;
	}
	@Override
	public String toString(){
		return String.valueOf(c);
	}
	
	
}
