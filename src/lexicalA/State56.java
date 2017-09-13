package lexicalA;

import java.util.HashMap;

public class State56 extends State {
	private static State instance;
	
	private State56() {
		tokenType = T_Type.NON_FINAL_STATE;
		
		transitions = new HashMap<>();
		char c;
		//every char different from \n, \r , \0 is a valid char in an line comment
		for (int i = 1; i <=127; i++){
			c = (char)i;
			
			if ((c != '\r') && (c != '\n')){
				transitions.put(c, 56);
			}
		}
		
	}
	
	public static State getInstance() {
		if ( instance == null ) {
			instance = new State56();
		}
		
		return instance;
	}
	
	@Override
	public Token accept(Visitor visitor) {
		return visitor.visit(this);
	}
	
	@Override
	public String toString() {
		return "56";
	}
	
}
