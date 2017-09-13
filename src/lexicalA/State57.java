package lexicalA;

public class State57 extends State {
	private static State instance;
	
	private State57() {
		tokenType = T_Type.LINECOMMENT;
	
		
	}
	
	public static State getInstance() {
		if ( instance == null ) {
			instance = new State57();
		}
		
		return instance;
	}
	
	@Override
	public Token accept(Visitor visitor) {
		return visitor.visit(this);
	}
	
	@Override
	public String toString() {
		return "57";
	}
	
}
