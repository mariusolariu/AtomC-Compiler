package La;

public class State7 extends State {
	private static State instance;
	
	private State7(){
		tokenType = T_Type.COMMA;
	}
	
	public static State getInstance(){
		if (instance == null){
			instance = new State7();
		}
		
		return instance;
	}


	@Override
	public Token accept(Visitor visitor) {
		return visitor.visit(this);
	}
	
	@Override
	public String toString(){
		return "7";
	}
	
	
}
