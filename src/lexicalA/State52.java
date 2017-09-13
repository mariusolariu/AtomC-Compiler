package lexicalA;

public class State52 extends State {
	private static State instance;
	
	private State52() {
		tokenType = T_Type.GREATEREQ;

		
	}
	
	public static State getInstance() {
		if ( instance == null ) {
			instance = new State52();
		}
		
		return instance;
	}
	
	@Override
	public Token accept(Visitor visitor) {
		return visitor.visit(this);
	}
	
	@Override
	public String toString() {
		return "52";
	}
}
