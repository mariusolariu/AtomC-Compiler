package syntacticA_domainA;

public class Type {
	private TbEnum typeBase;
	private Symbol s;
    private int nElements;
    
    public Type(TbEnum typeBase, Symbol s, int nElements) {
		super();
		this.typeBase = typeBase;
		this.s = s;
		this.nElements = nElements;
	}
    
	public Type() {
		
	}

	public final TbEnum getTypeBase() {
		return typeBase;
	}

	public final void setTypeBase(TbEnum typeBase) {
		this.typeBase = typeBase;
	}

	public final Symbol getS() {
		return s;
	}

	public final void setS(Symbol s) {
		this.s = s;
	}

	public final int getnElements() {
		return nElements;
	}

	/**
	 * @param nElements - >0 array of given size, 
	 * 					  0=array without size,
	 * 					  <0 non array
	 */
	public final void setnElements(int nElements) {
		this.nElements = nElements;
	}

	
}
