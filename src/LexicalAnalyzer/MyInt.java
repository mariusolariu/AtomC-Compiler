/**
 * 
 */
package LexicalAnalyzer;

/**
 * @author marius
 *
 */
public class MyInt extends Value {
	private int intVal;
	
	public MyInt(int intVal) {
		this.intVal = intVal;
	}
	
	/**
	 * @return the intVal
	 */
	public final int getIntVal() {
		return intVal;
	}
	
	@Override
	public String toString(){
		return String.valueOf(intVal);
	}
	
	
}
