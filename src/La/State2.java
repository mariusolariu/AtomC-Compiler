/**
 * 
 */
package La;

/**
 * @author marius
 *
 */
public class State2 extends State {
	private static State instance;
	
	private State2(){
		tokenType = T_Type.ID;
		
	}

	@Override
	public Token accept(Visitor visitor) {
		return visitor.visit(this);
	}
	
	public static State getInstance(){
		
		if (instance == null){
			return new State2();
		}
		
		return instance;
	}

	@Override
	public String toString(){
		return "2";
	}
}
