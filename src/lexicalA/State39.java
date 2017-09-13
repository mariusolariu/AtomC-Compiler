package lexicalA;

public class State39 extends State {
	private static State instance;
	
	private State39() {
		tokenType = T_Type.SUB;
		
	}
	
	public static State getInstance() {
		if ( instance == null ) {
			instance = new State39();
		}
		
		return instance;
	}
	
	@Override
	public Token accept(Visitor visitor) {
		return visitor.visit(this);
	}
	
	@Override
	public String toString() {
		return "39";
	}
	
}
