package La;

public class State40 extends State {
	private static State instance;
	
	private State40() {
		tokenType = T_Type.MUL;
		
	}
	
	public static State getInstance() {
		if ( instance == null ) {
			instance = new State40();
		}
		
		return instance;
	}
	
	@Override
	public Token accept(Visitor visitor) {
		return visitor.visit(this);
	}
	
	@Override
	public String toString() {
		return "40";
	}
	
}
