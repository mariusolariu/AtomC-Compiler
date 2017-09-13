package La;

public class State22 extends State {
private static State instance;
	
	private State22() {
		tokenType = T_Type.CT_INT;
		
	}
	
	public static State getInstance() {
		if ( instance == null ) {
			instance = new State22();
		}
		
		return instance;
	}
	
	@Override
	public Token accept(Visitor visitor) {
		return visitor.visit(this);
	}
	
	@Override
	public String toString(){
		return "22";
	}
}
