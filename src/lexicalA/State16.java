package lexicalA;

public class State16 extends State {	
	private static State instance;
	
	private State16() {
		tokenType = T_Type.CT_INT;
		
	}
	
	public static State getInstance() {
		if ( instance == null ) {
			instance = new State16();
		}
		
		return instance;
	}
	
	@Override
	public Token accept(Visitor visitor) {
		return visitor.visit(this);
	}
	
	@Override
	public String toString(){
		return "16";
	}
	
}
