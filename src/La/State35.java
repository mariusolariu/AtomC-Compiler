package La;

import java.util.HashMap;

public class State35 extends State {
	private static State instance;
	
	private State35() {
		tokenType = T_Type.NON_FINAL_STATE;
		
		transitions = new HashMap<>();
		
		transitions.put('\\', 29);
		
		// check if the current char is different from " and if yes, add
		// the char
		char c;
		for ( int i = 0; i <= 127; i++ ) {
			c = (char) i;
			if ( (c != '"' ) && (c != '\\')) {
				transitions.put(c, 35);
			}
		}
		
		transitions.put('"', 37);
		
	}
	
	public static State getInstance() {
		if ( instance == null ) {
			instance = new State35();
		}
		
		return instance;
	}
	
	@Override
	public Token accept(Visitor visitor) {
		return visitor.visit(this);
	}
	
	@Override
	public String toString() {
		return "35";
	}
}
