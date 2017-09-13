package La;

import java.util.HashMap;

public class State55 extends State {
	private static State instance;
	
	private State55() {
		tokenType = T_Type.NON_FINAL_STATE;
		
		transitions = new HashMap<>();
		
		transitions.put('*', 58);
		
		char c = '*';
		//every char different from '\n' is a valid char in an line comment
		for (int i = 1; i <=127; i++){
			char currChar = (char)i;
			
			if (c != currChar){
				transitions.put(currChar, 55);
			}
		}
		
	
		
	}
	
	public static State getInstance() {
		if ( instance == null ) {
			instance = new State55();
		}
		
		return instance;
	}
	
	@Override
	public Token accept(Visitor visitor) {
		return visitor.visit(this);
	}
	
	@Override
	public String toString() {
		return "55";
	}
}
