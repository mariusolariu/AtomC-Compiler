package La;

public class State9 extends State {
	private static State instance;
	
	private State9(){
		tokenType = T_Type.LPAR;
	}
	
	public static State getInstance(){
		if (instance == null){
			instance = new State9();
		}
		
		return instance;
	}
	
	@Override
	public Token accept(Visitor visitor) {
		return visitor.visit(this);
	}
	
	@Override
	public String toString(){
		return "9";
	}
}
