package La;

import java.util.HashMap;

public class State25 extends State {
	private static State instance;
	
	private State25() {
		tokenType = T_Type.NON_FINAL_STATE;
		transitions = new HashMap<>();
		
		for (char i = '0'; i <= '9'; i++){
			transitions.put(i, 25);
		}
		
	}
	
	public static State getInstance() {
		if ( instance == null ) {
			instance = new State25();
		}
		
		return instance;
	}
	
	@Override
	public Token accept(Visitor visitor) {
		return visitor.visit(this);
	}

	@Override
	public String toString(){
		return "25";
	}

	
}
