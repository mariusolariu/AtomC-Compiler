/**
 * 
 */
package La;

/**
 * @author marius
 *
 */
public class AtomC_Int extends Value {
	private int intVal;
	
	public AtomC_Int(int intVal) {
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
