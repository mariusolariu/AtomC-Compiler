package lexicalA;

public class State42 extends State {
	private static State instance;
	
	private State42() {
		tokenType = T_Type.DOT;
		
	}
	
	public static State getInstance() {
		if ( instance == null ) {
			instance = new State42();
		}
		
		return instance;
	}
	
	@Override
	public Token accept(Visitor visitor) {
		return visitor.visit(this);
	}
	
	@Override
	public String toString() {
		return "42";
	}
}
