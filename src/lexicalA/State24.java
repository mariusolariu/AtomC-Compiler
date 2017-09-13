package lexicalA;

import java.util.HashMap;

public class State24 extends State {
	private static State instance;
	
	private State24() {
		tokenType = T_Type.NON_FINAL_STATE;
		
		transitions = new HashMap<>();
	}
	
	public static State getInstance() {
		if ( instance == null ) {
			instance = new State24();
		}
		
		return instance;
	}
	
	@Override
	public Token accept(Visitor visitor) {
		return visitor.visit(this);
	}

	@Override
	public String toString(){
		return "24";
	}

}
