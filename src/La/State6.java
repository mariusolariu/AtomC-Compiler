package La;

public class State6 extends State {
	private static State instance;
	
	private State6(){
		tokenType = T_Type.END;
	}
	

	@Override
	public Token accept(Visitor visitor) {
		return visitor.visit(this);
	}
	
	
	public static State getInstance(){
		if (instance == null){
			instance = new State6();
		}
		
		return instance;
	}
	
	@Override
	public String toString(){
		return "6";
	}
	
}
