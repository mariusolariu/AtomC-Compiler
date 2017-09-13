/**
 * 
 */
package La;

/**
 * @author marius
 *
 */
public class State5 extends State{
	private static State instance;
	
	private State5(){
		tokenType = T_Type.ASSIGN;
	}



	@Override
	public Token accept(Visitor visitor) {
		return visitor.visit(this);
	}
	
	
	public static State getInstance(){
		if (instance == null){
			instance = new State5();
		}
		
		return instance;
	}
	
	@Override
	public String toString(){
		return "5";
	}
}
