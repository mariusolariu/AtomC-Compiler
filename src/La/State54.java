package La;

import java.util.HashMap;

public class State54 extends State {
	private static State instance;
	
	private State54() {
		tokenType = T_Type.NON_FINAL_STATE;
		
		transitions = new HashMap<>();
		
		transitions.put('/', 56);
		
		transitions.put('*', 55);
		
	
		
	}
	
	public static State getInstance() {
		if ( instance == null ) {
			instance = new State54();
		}
		
		return instance;
	}
	
	@Override
	public Token accept(Visitor visitor) {
		return visitor.visit(this);
	}
	
	@Override
	public String toString() {
		return "54";
	}
}
