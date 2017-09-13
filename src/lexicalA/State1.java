/**
 * 
 */
package lexicalA;

import java.util.HashMap;

/**
 * @author marius
 *
 */
public class State1 extends State {
	private static State instance;
	
	private State1(){
		tokenType = T_Type.NON_FINAL_STATE;
		
		transitions = new HashMap<>();
		
		for (int i = '0'; i <= '9' ; i++){
			transitions.put((char)i, 1);
		}
		
		
		transitions.put('_', 1);
		for (int i=65; i<= 90; i++){
			//lower case letters
			transitions.put((char)i, 1);
			//upper case letters
			transitions.put((char)(i + 32), 1);
		}
		
	}


	@Override
	public Token accept(Visitor visitor) {
		return visitor.visit(this);
	}

	
	public static State getInstance(){
		
		if (instance == null){
			return new State1();
		}
		
		return instance;
	}

	@Override
	public String toString(){
		return "1";
	}

}
