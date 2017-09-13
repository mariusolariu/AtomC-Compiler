package La;

import java.util.HashMap;

public class State27 extends State {
	private static State instance;
	
	private State27() {
		tokenType = T_Type.NON_FINAL_STATE;
		
		transitions = new HashMap<>();
		
		for ( char i = '0'; i <= '9'; i++ ) {
			transitions.put(i, 27);
		}
		
		transitions.put('e', 23);
		transitions.put('E', 23);
	}
	
	public static State getInstance() {
		if ( instance == null ) {
			instance = new State27();
		}
		
		return instance;
	}
	
	@Override
	public Token accept(Visitor visitor) {
		return visitor.visit(this);
	}
	
	@Override
	public String toString() {
		return "27";
	}
	
}
