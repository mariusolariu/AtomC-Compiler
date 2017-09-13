package lexicalA;

public class State41 extends State {
	private static State instance;
	
	private State41() {
		tokenType = T_Type.DIV;
		
	}
	
	public static State getInstance() {
		if ( instance == null ) {
			instance = new State41();
		}
		
		return instance;
	}
	
	@Override
	public Token accept(Visitor visitor) {
		return visitor.visit(this);
	}
	
	@Override
	public String toString() {
		return "41";
	}
	
}
