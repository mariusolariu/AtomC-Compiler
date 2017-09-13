package La;

public class State14 extends State {
	private static State instance;
	
	private State14(){
		tokenType = T_Type.RACC;
	}
	
	public static State getInstance(){
		if (instance == null){
			instance = new State14();
		}
		
		return instance;
	}
	
	@Override
	public Token accept(Visitor visitor) {
		return visitor.visit(this);
	}
	
	@Override
	public String toString(){
		return "14";
	}
	
}
