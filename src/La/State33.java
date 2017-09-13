package La;

import java.util.HashMap;

public class State33 extends State {
private static State instance;
	
	private State33() {
		tokenType = T_Type.NON_FINAL_STATE;
		
		transitions = new HashMap<>();
		
		transitions.put('\'', 34);
		
	}
	
	public static State getInstance() {
		if ( instance == null ) {
			instance = new State33();
		}
		
		return instance;
	}
	
	@Override
	public Token accept(Visitor visitor) {
		return visitor.visit(this);
	}
	
	@Override
	public String toString() {
		return "33";
	}
	
}
