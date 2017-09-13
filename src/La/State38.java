package La;

public class State38 extends State {
	private static State instance;
	
	private State38() {
		tokenType = T_Type.ADD;
		
	}
	
	public static State getInstance() {
		if ( instance == null ) {
			instance = new State38();
		}
		
		return instance;
	}
	
	@Override
	public Token accept(Visitor visitor) {
		return visitor.visit(this);
	}
	
	@Override
	public String toString() {
		return "38";
	}
	
}
