/**
 * 
 */
package La;

import java.util.HashMap;

/**
 * @author marius
 *
 */
public class State3 extends State {
	private static State instance;
	
	private State3(){
		tokenType = T_Type.NON_FINAL_STATE;
		transitions = new HashMap<>();
		
		transitions.put('=', 4);
	}

	@Override
	public Token accept(Visitor visitor) {
		return visitor.visit(this);
	}
	
	public static State getInstance(){
		if (instance == null){
			instance = new State3();
		}
		
		return instance;
	}
	
	@Override
	public String toString(){
		return "3";
	}
	
}
