/**
 * 
 */
package La;

/**
 * @author marius
 *
 */
public class Token {
	private T_Type tokenType; // code (name)
	private String tokenName;
	private Value value; // it could be a CT_INT, CT_CHAR, CT_REAL;
	private int line;
	
	public Token(T_Type tokenType, int line) {
		this.tokenType = tokenType;
		this.line = line;
	}
	
	public Token(T_Type tokenType, int line, String identifier) {
		this.tokenType = tokenType;
		this.line = line;
		this.tokenName = identifier;
	}
	
	public Token(T_Type tokenType, int line, Value value) {
		this.tokenType = tokenType;
		this.line = line;
		this.value = value;
	}
	
	public Token(T_Type tokenType, int line, String identifier, Value value) {
		this.tokenType = tokenType;
		this.line = line;
		this.tokenName = identifier;
		this.value = value;
	}
	
	
	
	public final T_Type getTokenType() {
		return tokenType;
	}



	public final void setTokenType(T_Type tokenType) {
		this.tokenType = tokenType;
	}



	public final String getTokenName() {
		return tokenName;
	}
	
	public final Value getValue() {
		return value;
	}
	
	public final int getLine() {
		return line;
	}
	
	
	@Override
	public String toString(){
		return tokenType + "";
	}
	
	@Override
	public boolean equals(Object o){
		return this.tokenType == ((Token)o).getTokenType();
		
	}
}
