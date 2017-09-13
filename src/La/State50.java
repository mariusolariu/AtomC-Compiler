package La;

public class State50 extends State {
	private static State instance;
	
	private State50() {
		tokenType = T_Type.LESSEQ;

		
	}
	
	public static State getInstance() {
		if ( instance == null ) {
			instance = new State50();
		}
		
		return instance;
	}
	
	@Override
	public Token accept(Visitor visitor) {
		return visitor.visit(this);
	}
	
	@Override
	public String toString() {
		return "50";
	}
	
}
