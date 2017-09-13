package La;

import java.util.HashMap;

public class State20 extends State {
    private static State instance;
	
	private State20() {
		tokenType = T_Type.NON_FINAL_STATE;
		transitions = new HashMap<>();
		
		for (char i = 'A'; i <= 'F'; i++){
			transitions.put(i, 21);
			transitions.put((char)(i+32), 21);
		}
		
		for (char i = '0'; i <= '9'; i++){
			transitions.put(i, 21);
			
		}
	}
	
	public static State getInstance() {
		if ( instance == null ) {
			instance = new State20();
		}
		
		return instance;
	}
	
	@Override
	public Token accept(Visitor visitor) {
		return visitor.visit(this);
	}
	
	@Override
	public String toString(){
		return "20";
	}
}
