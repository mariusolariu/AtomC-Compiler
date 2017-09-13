package La;

public class State44 extends State {
	private static State instance;
	
	private State44() {
		tokenType = T_Type.AND;
		
	}
	
	public static State getInstance() {
		if ( instance == null ) {
			instance = new State44();
		}
		
		return instance;
	}
	
	@Override
	public Token accept(Visitor visitor) {
		return visitor.visit(this);
	}
	
	@Override
	public String toString() {
		return "44";
	}
}
