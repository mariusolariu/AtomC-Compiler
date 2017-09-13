/**
 * 
 */
package lexicalA;

import java.util.HashMap;

/**
 * @author marius
 *
 */
public class State0 extends State{
	private static State instance;
	
	
	private State0(){
		tokenType = T_Type.NON_FINAL_STATE;
		
		transitions = new HashMap<>();
		
		// space state
		transitions.put('\n', 53);
		transitions.put('\r', 53);
		transitions.put('\t', 53);
		
		transitions.put(' ', 0);
		
		
		
		// chars that can form an possible identifier
		transitions.put('_', 1);
		for (int i=65; i<= 90; i++){
			//lower case letters
			transitions.put((char)i, 1);
			//upper case letters
			transitions.put((char)(i + 32), 1);
		}
		
		transitions.put('=',3);
		
		//end char
		transitions.put((char)0, 6);
		
		//delimiters
		transitions.put(',', 7);
		transitions.put(';', 8);
		transitions.put('(', 9);
		transitions.put(')', 10);
		transitions.put('[', 11);
		transitions.put(']', 12);
		transitions.put('{', 13);
		transitions.put('}', 14);
		
		//ct_int
		for (char i='1'; i<='9'; i++){
			transitions.put(i, 15);
		}
		
		//ct_int oct/hex
		transitions.put('0', 17);
		
		//char
		transitions.put('\'', 32);
		
		//string
		transitions.put('"', 35);
		
		transitions.put('+', 38);
		
		transitions.put('-', 39);
		
		transitions.put('*', 40);
		
		//transitions.put('/', 41);
		
		transitions.put('.', 42);
		
		transitions.put('&', 43);
		
		transitions.put('|', 45);
		
		transitions.put('!', 47);
		
		transitions.put('<', 49);
		
		transitions.put('>', 51);
		
		transitions.put('/', 54);
	}


	public static State getInstance(){
		
		if (instance == null){
			return new State0();
		}
		
		return instance;
	}
	
	@Override
	public Token accept(Visitor visitor) {
		return visitor.visit(this);	
	}
	
	@Override
	public String toString(){
		return "0";
	}
	

}
