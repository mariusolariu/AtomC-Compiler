package lexicalA;

import java.util.HashMap;

public class State15 extends State {
	private static State instance;
	
	private State15(){
		tokenType = T_Type.NON_FINAL_STATE;
		
		transitions = new HashMap<>();
		
		for (char i = '0'; i <= '9'; i++){
				transitions.put(i, 15);
		}
		
		//transition to a CT_REAL
		transitions.put('e', 23);
		transitions.put('E', 23);
		transitions.put('.', 26);
				
	}
	
	public static State getInstance(){
		if (instance == null){
			instance = new State15();
		}
		
		return instance;
	}
	
	@Override
	public Token accept(Visitor visitor) {
		return visitor.visit(this);
	}
	
	@Override
	public String toString(){
		return "15";
	}
}
