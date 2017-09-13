package La;

import java.util.HashMap;

public class State51 extends State {
	private static State instance;
	
	private State51() {
		tokenType = T_Type.GREATER;
		
		transitions = new HashMap<>();
		
		transitions.put('=', 52);
		
	}
	
	public static State getInstance() {
		if ( instance == null ) {
			instance = new State51();
		}
		
		return instance;
	}
	
	@Override
	public Token accept(Visitor visitor) {
		return visitor.visit(this);
	}
	
	@Override
	public String toString() {
		return "51";
	}
}
