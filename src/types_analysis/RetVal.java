package types_analysis;

import lexicalA.Value;
import syntacticA_domainA.Type;

public class RetVal {
	private Type type;
	private boolean isL_Val;
	private boolean isR_Val;
	private Value ctVal;
	
	
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public boolean isL_Val() {
		return isL_Val;
	}
	public void setL_Val(boolean isL_Val) {
		this.isL_Val = isL_Val;
	}
	public boolean isR_Val() {
		return isR_Val;
	}
	public void setR_Val(boolean isR_Val) {
		this.isR_Val = isR_Val;
	}
	public Value getCtVal() {
		return ctVal;
	}
	public void setCtVal(Value ctVal) {
		this.ctVal = ctVal;
	}
	
}
