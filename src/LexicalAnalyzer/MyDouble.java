/**
 * 
 */
package LexicalAnalyzer;

/**
 * @author marius
 *
 */
public class MyDouble extends Value {
	private double doubleVal;
	
	public MyDouble(double doubleVal) {
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
