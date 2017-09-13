package lexicalA;

import java.util.HashMap;

public class State43 extends State {
	private static State instance;
	
	private State43() {
		tokenType = T_Type.NON_FINAL_STATE;
		
		transitions = new HashMap<>();
	
		transitions.put('&', 44);
	}
	
	public static State getInstance() {
		if ( instance == null ) {
			instance = new State43();
		}
		
		return instance;
	}
	
	@Override
	public Token accept(Visitor visitor) {
		return visitor.visit(this);
	}
	
	@Override
	public String toString() {
		return "43";
	}	
}
