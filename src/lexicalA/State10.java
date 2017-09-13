package lexicalA;

public class State10 extends State {
	private static State instance;
	
	private State10(){
		tokenType = T_Type.RPAR;
	}
	
	public static State getInstance(){
		if (instance == null){
			instance = new State10();
		}
		
		return instance;
	}
	
	@Override
	public Token accept(Visitor visitor) {
		return visitor.visit(this);
	}
	
	@Override
	public String toString(){
		return "10";
	}
	
}
