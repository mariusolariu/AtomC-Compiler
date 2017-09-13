/**
 * 
 */
package lexicalA;

/**
 * @author marius
 *
 */
public class AtomC_Double extends Value {
	private double doubleVal;
	
	public AtomC_Double(double doubleVal) {
		this.doubleVal = doubleVal;
	}
	
	public double getDoubleVal() {
		return doubleVal;
	}
	
	@Override
	public String toString(){
		return String.valueOf(doubleVal);
	}
	
}
