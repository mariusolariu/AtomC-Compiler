/**
 * 
 */
package lexicalA;


/**
 * @author marius
 *
 */
public interface Visitable {
	
	public void accept(Visitor visitor);
	
	public boolean isFinalState();

}
