/**
 * 
 */
package La;

/**
 * @author marius
 *
 */
public class State4 extends State {
	private static State instance;
	
	private State4(){
		tokenType = T_Type.EQUAL;
	}


	@Override
	public Token accept(Visitor visitor) {
		
		return visitor.visit(this);
	}
	
	public static State getInstance(){
		if (instance == null){
			instance = new State4();
		}
		
		return instance;
	}
	
	@Override
	public String toString(){
		return "4";
	}
}
