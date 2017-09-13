package lexicalA;

import java.util.HashMap;

public class State45 extends State {
	private static State instance;
	
	private State45() {
		tokenType = T_Type.NON_FINAL_STATE;
		
		transitions = new HashMap<>();
		
		transitions.put('|', 46);
	}
	
	public static State getInstance() {
		if ( instance == null ) {
			instance = new State45();
		}
		
		return instance;
	}
	
	@Override
	public Token accept(Visitor visitor) {
		return visitor.visit(this);
	}
	
	@Override
	public String toString() {
		return "45";
	}
	
}
