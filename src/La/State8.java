package La;

public class State8 extends State {
	private static State instance;
	
	private State8(){
		tokenType = T_Type.SEMICOLON;
	}
	
	public static State getInstance(){
		if (instance == null){
			instance = new State8();
		}
		
		return instance;
	}

	@Override
	public Token accept(Visitor visitor) {
		return visitor.visit(this);
	}
	
	@Override
	public String toString(){
		return "8";
	}
	
}
