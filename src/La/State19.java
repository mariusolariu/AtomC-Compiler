package La;

public class State19 extends State {
	
private static State instance;
	
	private State19() {
		tokenType = T_Type.CT_INT;
		
	}
	
	public static State getInstance() {
		if ( instance == null ) {
			instance = new State19();
		}
		
		return instance;
	}
	
	@Override
	public Token accept(Visitor visitor) {
		return visitor.visit(this);
	}
	
	@Override
	public String toString(){
		return "19";
	}
	
}
