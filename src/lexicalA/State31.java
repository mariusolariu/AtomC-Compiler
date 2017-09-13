package lexicalA;

import java.util.HashMap;

public class State31 extends State {
	private static State instance;
	
	private State31() {
		tokenType = T_Type.NON_FINAL_STATE;
		
		transitions = new HashMap<>();
		
		transitions.put('"', 37);
		transitions.put('\\', 29);
		transitions.put('\'', 34);
		
		char c;
		for ( int i = 0; i < 127; i++ ) {
			c = (char) i;
			
			if ( (c != '"') && (c != '\\') && (c != '\'') ) {
				transitions.put(c, 36);
			}
		}
		
	}
	
	public static State getInstance() {
		if ( instance == null ) {
			instance = new State31();
		}
		
		return instance;
	}
	
	@Override
	public Token accept(Visitor visitor) {
		return visitor.visit(this);
	}
	
	@Override
	public String toString() {
		return "31";
	}
	
}
