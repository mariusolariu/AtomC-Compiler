package La;

public class State28 extends State {
	
	private static State instance;
	
	private State28() {
		tokenType = T_Type.CT_REAL;
	}
	
	public static State getInstance() {
		if ( instance == null ) {
			instance = new State28();
		}
		
		return instance;
	}
	
	@Override
	public Token accept(Visitor visitor) {
		return visitor.visit(this);
	}
	
	@Override
	public String toString() {
		return "28";
	}
	
}
