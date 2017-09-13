package lexicalA;

import java.util.HashMap;

public class State34 extends State {
private static State instance;
	
	private State34() {
		tokenType = T_Type.CT_CHAR;
		
		transitions = new HashMap<>();
		
		transitions.put('\\', 29);
		
		transitions.put('"', 37);
		
		for (int i=0; i < 127; i++){
			transitions.put((char)i, 36);
		}
		
		
	}
	
	public static State getInstance() {
		if ( instance == null ) {
			instance = new State34();
		}
		
		return instance;
	}
	
	@Override
	public Token accept(Visitor visitor) {
		return visitor.visit(this);
	}
	
	@Override
	public String toString() {
		return "34";
	}
	
}
