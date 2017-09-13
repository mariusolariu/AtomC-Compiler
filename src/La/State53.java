package La;

public class State53 extends State {
	private static State instance;
	
	private State53() {
		tokenType = T_Type.SPACE;
	
		
	}
	
	public static State getInstance() {
		if ( instance == null ) {
			instance = new State53();
		}
		
		return instance;
	}
	
	@Override
	public Token accept(Visitor visitor) {
		return visitor.visit(this);
	}
	
	@Override
	public String toString() {
		return "53";
	}
}
