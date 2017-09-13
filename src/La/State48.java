package La;

public class State48 extends State {
	private static State instance;
	
	private State48() {
		tokenType = T_Type.NOTEQ;
		
	}
	
	public static State getInstance() {
		if ( instance == null ) {
			instance = new State48();
		}
		
		return instance;
	}
	
	@Override
	public Token accept(Visitor visitor) {
		return visitor.visit(this);
	}
	
	@Override
	public String toString() {
		return "48";
	}
}
