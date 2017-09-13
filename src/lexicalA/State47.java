package lexicalA;

import java.util.HashMap;

public class State47 extends State {
	
	private static State instance;
	
	private State47() {
		tokenType = T_Type.NOT;
		
		transitions = new HashMap<>();
		
		transitions.put('=', 48);
	}
	
	public static State getInstance() {
		if ( instance == null ) {
			instance = new State47();
		}
		
		return instance;
	}
	
	@Override
	public Token accept(Visitor visitor) {
		return visitor.visit(this);
	}
	
	@Override
	public String toString() {
		return "47";
	}
}
