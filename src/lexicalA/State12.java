package lexicalA;

public class State12 extends State {
	private static State instance;
	
	private State12(){
		tokenType = T_Type.RBRACKET;
	}
	
	public static State getInstance(){
		if (instance == null){
			instance = new State12();
		}
		
		return instance;
	}
	
	@Override
	public Token accept(Visitor visitor) {
		return visitor.visit(this);
	}
	
	@Override
	public String toString(){
		return "12";
	}
}
