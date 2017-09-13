package lexicalA;

public class State13 extends State {
	private static State instance;
	
	private State13(){
		tokenType = T_Type.LACC;
	}
	
	public static State getInstance(){
		if (instance == null){
			instance = new State13();
		}
		
		return instance;
	}
	
	@Override
	public Token accept(Visitor visitor) {
		return visitor.visit(this);
	}
	
	@Override
	public String toString(){
		return "13";
	}
	
}
