package lexicalA;

public class State11 extends State {
	private static State instance;
	
	private State11(){
		tokenType = T_Type.LBRACKET;
	}
	
	public static State getInstance(){
		if (instance == null){
			instance = new State11();
		}
		
		return instance;
	}
	
	@Override
	public Token accept(Visitor visitor) {
		return visitor.visit(this);
	}
	
	@Override
	public String toString(){
		return "11";
	}
}
