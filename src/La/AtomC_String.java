package La;

import java.util.LinkedList;

public class AtomC_String extends Value {
	private String s;
	private LinkedList<Character> escapedChars;
	
	public AtomC_String(String s){
		this.s = s;
	}
	
	public AtomC_String(String s, LinkedList<Character> escapedChars){
		this.s = s;
		this.escapedChars = escapedChars;
	}

	@Override
	public String toString() {
		return s;
	}
	
}
