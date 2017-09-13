package lexicalA;

import java.util.HashMap;

public class State58 extends State {
	private static State instance;
	
	private State58() {
		tokenType = T_Type.NON_FINAL_STATE;
		
		transitions = new HashMap<>();
		
		transitions.put('*', 58);
		
		transitions.put('/', 59);
		
		char c;
		
		for (int i = 1; i <=127; i++){
			c = (char)i;
			
			if ((c != '*') && (c != '/')){
				transitions.put(c, 55);
			}
		}
	}
	
	public static State getInstance() {
		if ( instance == null ) {
			instance = new State58();
		}
		
		return instance;
	}
	
	@Override
	public Token accept(Visitor visitor) {
		return visitor.visit(this);
	}
	
	@Override
	public String toString() {
		return "58";
	}
	
}
