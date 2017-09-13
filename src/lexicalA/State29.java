package lexicalA;

import java.util.HashMap;

public class State29 extends State {
	private static State instance;
	
	private State29() {
		tokenType = T_Type.NON_FINAL_STATE;
		
		transitions = new HashMap<>();
		
		transitions.put('a', 31);
		transitions.put('b', 31);
		transitions.put('f', 31);
		transitions.put('n', 31);
		transitions.put('r', 31);
		transitions.put('v', 31);
		transitions.put('t', 31);
		transitions.put('\'', 31);
		transitions.put('?', 31);
		transitions.put('\\', 31);
		transitions.put('"', 31);
		transitions.put('\0', 31);  
	}
	
	public static State getInstance() {
		if ( instance == null ) {
			instance = new State29();
		}
		
		return instance;
	}
	
	@Override
	public Token accept(Visitor visitor) {
		return visitor.visit(this);
	}
	
	@Override
	public String toString() {
		return "29";
	}
}
