package lexicalA;

import java.util.HashMap;

public class State26 extends State {
	private static State instance;
	
	private State26() {
		tokenType = T_Type.NON_FINAL_STATE;
		
		transitions = new HashMap<>();
		
		for ( char i = '0'; i <= '9'; i++ ) {
			transitions.put(i, 27);
		}
	}
	
	public static State getInstance() {
		if ( instance == null ) {
			instance = new State26();
		}
		
		return instance;
	}
	
	@Override
	public Token accept(Visitor visitor) {
		return visitor.visit(this);
	}
	
	@Override
	public String toString() {
		return "26";
	}
	
}
