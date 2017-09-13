package lexicalA;

public class State59 extends State {
	private static State instance;
	
	private State59() {
		tokenType = T_Type.COMMENT;
		
		
	}
	
	public static State getInstance() {
		if ( instance == null ) {
			instance = new State59();
		}
		
		return instance;
	}
	
	@Override
	public Token accept(Visitor visitor) {
		return visitor.visit(this);
	}
	
	@Override
	public String toString() {
		return "59";
	}
	
}
