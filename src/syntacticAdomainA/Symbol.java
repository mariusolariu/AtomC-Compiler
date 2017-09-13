package syntacticAdomainA;

import java.util.ArrayList;

public class Symbol {
	private String name;
	private ClsEnum cls;
	private MemEnum mem;
	private Type type;
	private SymbolDepth  depth;
	private ArrayList<Symbol> members; // it can hold args for a function or members for a struct
	
	
	public Symbol(String name, ClsEnum cls) {
		this.setName(name);
		this.setCls(cls);
		members = new ArrayList<>();
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public ArrayList<Symbol> getMembers() {
		return members;
	}


	public void setMembers(ArrayList<Symbol> members) {
		this.members = members;
	}


	public SymbolDepth getDepth() {
		return depth;
	}


	public void setDepth(SymbolDepth depth) {
		this.depth = depth;
	}


	public MemEnum getMem() {
		return mem;
	}


	public void setMem(MemEnum mem) {
		this.mem = mem;
	}


	public Type getType() {
		return type;
	}


	public void setType(Type type) {
		this.type = type;
	}


	public ClsEnum getCls() {
		return cls;
	}


	public void setCls(ClsEnum cls) {
		this.cls = cls;
	}
	
	@Override
	public boolean equals(Object o){
		return this.name.equals(((Symbol) o).getName()) ;
	}
}
