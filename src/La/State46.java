package La;

public class State46 extends State {
	private static State instance;
	
	private State46() {
		tokenType = T_Type.OR;
		
	}
	
	public static State getInstance() {
		if ( instance == null ) {
			instance = new State46();
		}
		
		return instance;
	}
	
	@Override
	public Token accept(Visitor visitor) {
		return visitor.visit(this);
	}
	
	@Override
	public String toString() {
		return "46";
	}
}
