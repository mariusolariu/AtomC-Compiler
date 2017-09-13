package lexicalA;

import java.util.HashMap;

public class State17 extends State {
	
	private static State instance;
	
	private State17() {
		tokenType = T_Type.NON_FINAL_STATE;
		
		transitions = new HashMap<>();
		
		for (char i='0'; i<='7'; i++){
			transitions.put(i, 18);
	    }
		
		transitions.put('x', 20);
		
		//transition to a CT_REAL
		transitions.put('e', 23);
		transitions.put('E', 23);
		transitions.put('8', 15);
		transitions.put('9', 15);
		transitions.put('.', 26);
		
	}
	
	public static State getInstance() {
		if ( instance == null ) {
			instance = new State17();
		}
		
		return instance;
	}
	
	@Override
	public Token accept(Visitor visitor) {
		return visitor.visit(this);
	}
	
	@Override
	public String toString(){
		return "17";
	}
}
