package lexicalA;

import java.util.HashMap;

public class State49 extends State {
	private static State instance;
	
	private State49() {
		tokenType = T_Type.LESS;
		
		transitions = new HashMap<>();
		
		transitions.put('=', 50);
		
	}
	
	public static State getInstance() {
		if ( instance == null ) {
			instance = new State49();
		}
		
		return instance;
	}
	
	@Override
	public Token accept(Visitor visitor) {
		return visitor.visit(this);
	}
	
	@Override
	public String toString() {
		return "49";
	}
	
}
