package La;

public class State37 extends State {
	private static State instance;
	
	private State37() {
		tokenType = T_Type.CT_STRING;
		
	}
	
	public static State getInstance() {
		if ( instance == null ) {
			instance = new State37();
		}
		
		return instance;
	}
	
	@Override
	public Token accept(Visitor visitor) {
		return visitor.visit(this);
	}
	
	@Override
	public String toString() {
		return "37";
	}
}
