package syntacticAdomainA;

public enum SymbolDepth {
	GLOBAL, LOCAL, BLOCK;
	
	//java enums are immutable object references
	//because every symbol in an enum is a public static final field
	//see : Joshua Bloch's type-safe enumeration pattern. 
	
	private static SymbolDepth[] vals = values();
	
	
	public SymbolDepth  next(){ 
		 return vals[(this.ordinal() + 1) % vals.length];
	}
	
	public  SymbolDepth  previous(){
		
		if (this.ordinal() == 0){
			return vals[vals.length - 1];
		}else{
			return  vals[this.ordinal() - 1];
		}
	}
	
	public void m(){
	}
	
}
