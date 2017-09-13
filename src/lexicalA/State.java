/**
 * 
 */
package lexicalA;

import java.util.Map;

/**
 * @author marius
 * Interface has only static and final variables - thus I choose an abstract class
 *  because I need to instantiate the fields different for each child
 */
public abstract class State{
	protected Map<Character, Integer> transitions; // <consumed char , next state>
	protected T_Type tokenType;
	
	
	public boolean canConsume(char c) {
		return (transitions.get(c) != null) ? true : false;
	}
	
	


	
	public Integer consume(char c){
		return transitions.get(c);
	}

	public T_Type getTokenType(){
		return tokenType;
	}
	
	public abstract Token accept(Visitor visitor);
	

}
