package syntacticA_domainA;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import lexicalA.LexicalAnalyzer;
import lexicalA.T_Type;
import lexicalA.Token;

public class SyntacticAnalyzer {
	private Token currentToken;
	private Token consumedToken;
	
	private int index; // current index representing the pos in the list of
	                   // tokens
	private LinkedList<Token> tokens;
	private String pathToSourceCode;
	
	//fields needed in order to implement domain analysis
	private ArrayList<Symbol> symbolsTable;
	private Symbol crtStruct;
	private Symbol crtFunc;
	private SymbolDepth crtDepth;
	//TODO find a better name for this HM
	private HashMap<T_Type, Integer> tbCasesHM;
	
	public SyntacticAnalyzer(String pathToSourceCode) {
		this.pathToSourceCode = pathToSourceCode;
	
		symbolsTable = new ArrayList<>();
		crtDepth = SymbolDepth.GLOBAL;
		
		tbCasesHM = new HashMap<>();
		tbCasesHM.put(T_Type.INT, new Integer(0));
		tbCasesHM.put(T_Type.DOUBLE, new Integer(1));
		tbCasesHM.put(T_Type.CHAR, new Integer(2));
		tbCasesHM.put(T_Type.STRUCT, new Integer(3));
	
		//TODO add all the I/O functions of AtomC
		//to the symbols table
		
		Symbol extFunc;
	
		extFunc = addExtFunc("put_s", createType(TbEnum.TB_VOID, -1));
		addFuncArg(extFunc, "s", createType(TbEnum.TB_CHAR, 0));
		
		extFunc = addExtFunc("put_i", createType(TbEnum.TB_VOID, -1));
		addFuncArg(extFunc, "i", createType(TbEnum.TB_INT, 0));
		
		extFunc = addExtFunc("put_d", createType(TbEnum.TB_VOID, -1));
		addFuncArg(extFunc, "d", createType(TbEnum.TB_DOUBLE, 0));
		
		extFunc = addExtFunc("put_c", createType(TbEnum.TB_VOID, -1));
		addFuncArg(extFunc, "c", createType(TbEnum.TB_CHAR, 0));
		
		extFunc = addExtFunc("get_s", createType(TbEnum.TB_VOID, -1));
		addFuncArg(extFunc, "s", createType(TbEnum.TB_CHAR, 0));
		
		addExtFunc("get_i", createType(TbEnum.TB_INT, -1));
		addExtFunc("get_d", createType(TbEnum.TB_CHAR, -1));
		addExtFunc("get_c", createType(TbEnum.TB_DOUBLE, -1));
		addExtFunc("seconds", createType(TbEnum.TB_DOUBLE, -1));
		
	}
	
	public boolean eat(T_Type tName) {
		
		if ( currentToken.getTokenType() == tName ) {
			consumedToken = currentToken;
			currentToken = tokens.get(++index);
			
			return true;
		}
		
		return false;
	}
	
	private Symbol addSymbol(ArrayList<Symbol> targetList, String name, ClsEnum cls){
		Symbol newSymbol = new Symbol(name, cls);
		
			targetList.add(newSymbol);
		
		return newSymbol;
	}
	
	private Type createType(TbEnum baseType, int nElements){
		Type t = new Type();
			
			t.setTypeBase(baseType);
			t.setnElements(nElements);
		
		return t;
	}
	
	private void cast(Type dst, Type src){
		
		if (src.getnElements() > -1){
			
			if (dst.getnElements() > -1){
				
				if (src.getTypeBase() != dst.getTypeBase())
					tkErr("an array cannot be converted to an array of another type", currentToken);
			
			}else{
				tkErr("an array cannot be converted to a non-array", consumedToken);
			}
			
		}else{
			if (dst.getnElements() > -1){
				tkErr("a non-array cannot be converted to an array", consumedToken);
			}
		}
		
		
				//this part will be completed during VM code generation
		
				switch (src.getTypeBase()){
					
					case TB_CHAR:
					case TB_INT:
					case TB_DOUBLE:
					
						switch (dst.getTypeBase()){
							
							case TB_CHAR:
							case TB_INT:
							case TB_DOUBLE:
								
								return;
							
							case TB_STRUCT:
								
								if (dst.getTypeBase() == TbEnum.TB_STRUCT){
									
									if (!src.getS().equals(dst.getS())){
										tkErr("a structure cannot be converted to another one", currentToken);
										return;	
									}
								}
						}
				}
	
		tkErr("incompatible types", currentToken);		
	}
	
	private Symbol addExtFunc(String name, Type type){
		Symbol s = addSymbol(symbolsTable, name, ClsEnum.CLS_EXTFUNC);
			
			s.setType(type);
			
		return s;
	}
	
	private Symbol addFuncArg(Symbol func, String name, Type type){
		Symbol args = addSymbol(func.getMembers(), name, ClsEnum.CLS_VAR);
			
			args.setType(type);
			
		return args;
	}
	
	private void deleteSymbolsAfter(ArrayList<Symbol> list, Symbol target) {
		Iterator<Symbol> iter = list.iterator();
		
				while ((iter.hasNext())&& (!iter.next().equals(target)) ){}
				
					//it may happen that inside a func. no local symbol was added
					// thus we are at the end of the list and we can't 
					// get the iter.next() 
					if (iter.hasNext()) iter.next();
				
				//remove everything that is after the target symbol
				while (iter.hasNext()){
					iter.next();
					iter.remove();
				}
	}
	
	
	/**
	 * The search is performed from left to right
	 * because there are situations
	 * in which a symbol may be redefined 
	 * thus the last definition must be used
	 * 
	 * @param currentList - it can be the symbols table, the list of member for a struct
	 * or the list of arguments of a function
	 * 
	 * @param name - represents the var/func member/struct identification name as introduced by the programmer
	 * in the source code
	 * @return null - if the symbol isn't defined (good case);
	 * else a redefinition occured
	 */
	private Symbol findSymbol(ArrayList<Symbol> currentList,String name){
		Symbol currentSymb;
		int size = currentList.size() - 1;
		
				for (int i = size  ; i >= 0 ; i--){
					currentSymb = currentList.get(i);;
					
					if (currentSymb.getName().equals(name)){
						return currentSymb;
					}
				}
		
		return null;
	}
	
	public void tkErr(String message, Token tk) {
		System.err.println( " In line " + (tk.getLine() + 1) + ": " + message );
		System.exit(1);
	}
	
	private void initialize() {
		currentToken = null;
		consumedToken = null;
		index = 0;
		
	}
	
	public boolean run() {
		initialize(); // in case we want the syntactic anlysis to be performed
		              // multiple times
		              // using the same object
		
		LexicalAnalyzer la = new LexicalAnalyzer(pathToSourceCode);
		tokens = la.getTokens();
		
		// TODO remove useless tokens : SPACE, LINECOMMENT, COMMENT
		
		discardUselessTok();
		
		// la.showTokens(tokens);
		
		
		return unit();
	}
	
	private void discardUselessTok() {
		HashMap<String, Boolean> uTokens = new HashMap<>();
		uTokens.put("LINECOMMENT", true);
		uTokens.put("COMMENT", true);
		uTokens.put("SPACE", true);
		
		Iterator<Token> iter = tokens.iterator();
		
		while (iter.hasNext()) {
			Token currentItem = iter.next();
			String tName = currentItem.toString();
			
			if ( uTokens.get(tName) != null ) {
				iter.remove();
			}
			
		}
		
	}
	
	private boolean unit() {
		
		currentToken = tokens.getFirst();

			do{
				
				if (eat(T_Type.LACC)) tkErr("A block can't be declared in the global context", consumedToken);
		
			}while (declStruct() || declVar()   || declFunc());
			
		
		return true;
	}
	
	//semantic action function
	
	private void addVar(Token tk, Type type){
		Symbol s;
		
		if (crtStruct != null){
			ArrayList<Symbol> structMemberVars = crtStruct.getMembers();
			String tokenIdName = tk.getTokenName();
			
				if (findSymbol(structMemberVars, tokenIdName)
							!= null){
					tkErr("Symbol redefinition: " + tokenIdName , tk);
				}
				
				s = addSymbol(symbolsTable, tokenIdName, ClsEnum.CLS_VAR);
		
		}else if (crtFunc != null){
			s = findSymbol(symbolsTable, tk.getTokenName());
			
				if ((s != null) && (s.getDepth() == crtDepth)){
					tkErr("Symbol redefintion: " + tk.getTokenName(), tk);
				}
			
			s = addSymbol(symbolsTable, tk.getTokenName(), ClsEnum.CLS_VAR);
			s.setMem(MemEnum.MEM_LOCAL);
			//symbols can be redefined but in different contexts
			s.setDepth(crtDepth);
		}else{
			
			if (findSymbol(symbolsTable, tk.getTokenName()) != null){
				tkErr("Symbol redefintion: " + tk.getTokenName(), tk);
			}
			
			s = addSymbol(symbolsTable, tk.getTokenName(), ClsEnum.CLS_VAR);
			s.setMem(MemEnum.MEM_GLOBAL);
		}
		
		s.setType(type);
	}
	
	public void resetCrtTk(int startIndex){
		index = startIndex;			
		currentToken = tokens.get(index);
	}
	
	// TODO try different var declarations to see if it works
	private boolean declVar() {
		int startIndex = index;
		Type symbType = null;
		Token varToken;
		Token typebaseToken;
		
			//dealing with the common case between declFunc, declStruct and declVar
			if (eat(T_Type.STRUCT) && eat(T_Type.ID)  
				&& (eat(T_Type.ID) || eat(T_Type.LACC))
				&& (crtDepth != SymbolDepth.GLOBAL)	){
				
					Token tokenId = tokens.get(index - 2);
					tkErr("A struct or a function can be defined only in global context", tokenId);
			}else{
				resetCrtTk(startIndex);
			}
		
			if ((crtDepth != SymbolDepth.GLOBAL) && (typeBase() != null) ){
				Token tokenId = consumedToken;
				
				if (eat(T_Type.ID) && (eat(T_Type.LACC) || eat(T_Type.LPAR)) ){
					tkErr("A struct or a function can be defined only in global context", tokenId);
				}else{
					// it isn't a declVar rule, thus the current
					resetCrtTk(startIndex);
				}
			}
		
			if ( ( (symbType = typeBase()) != null) || eat(T_Type.VOID)) {
					
			    if ( eat(T_Type.ID) ) {
			
			    		varToken = consumedToken; // arrayDecl may or may not consume tokens
			    	
			    		//if the user tries to declare an void variable, 
			    		//symbtType will be null, thus I will create an dummy object
			    		//so that a NullPointerException won't be thrown below in the arrayDecl()
			    		typebaseToken = tokens.get(index - 2);
			    		if (typebaseToken.getTokenType() == T_Type.VOID){
			    			symbType = new Type();
			    		}
			    		
			    		//semantic action
						if (!arrayDecl(symbType)){
							// the var is not an array
							symbType.setnElements(-1);
						}
						
						//I should left factor the declFunc() and declVar()
						if (currentToken.getTokenType() != T_Type.LPAR) addVar(varToken, symbType);
				
								while (true) {
									if ( !eat(T_Type.COMMA) )
										break;
								
									if ( !eat(T_Type.ID) )
										tkErr("an ID must follow after a comma in a declVar", currentToken);
									
									varToken = consumedToken;
									
										if (!arrayDecl(symbType)){
											// the var is not an array
											symbType.setnElements(-1);
										}
										
										addVar(varToken, symbType);
								}
			
					     if ( eat(T_Type.SEMICOLON) ) { 
					    	 
								 //semantic action
								 // a var can't be of type void, only a function can
						    	 if (typebaseToken.getTokenType() == T_Type.VOID){
						    		 tkErr("A variable can't be of void type", varToken);
						    	 }
					    	 
					    	 return true;
					    }
					     
					else{
						// it isn't a declVar rule, thus the current
						resetCrtTk(startIndex);
					}
			    }
			} 
			
		
			
		
		return false;
	}
	
	private boolean arrayDecl(Type ret) {
		
			if ( !eat(T_Type.LBRACKET) )
				return false;
			
			// optional
			expr();
			
			//semantic action
			// >0 array of given size, 0=array without size, <0 non array
			ret.setnElements(0);  // for now it doesn't compute the number of el
			
			if ( !eat(T_Type.RBRACKET) )
				tkErr(" missing RBRACKET", currentToken);
			
		return true;
	}
	
	private boolean expr() {
		return exprAssign();
	}
	
	private boolean exprAssign() {
		
			//first variant
//			if ( exprUnary() ) {
//				
//				if ( !eat(T_Type.ASSIGN) ) tkErr("missing ASSIGN terminal");
//		
//				return exprAssign();
//			}
//			
//			if (exprOr()){
//				return true;
//			}
//	
		
		 //the second variant is a derivation of the original productions
		 //because at some point in the derivation process some common prefixes appear
		 //thus we need to do a factorization
		
			if (eat(T_Type.SUB) || eat(T_Type.NOT) ){
				
				if (!exprUnary()) tkErr("missing exprUnary() after SUB || NOT", currentToken);
				
				if ( !eat(T_Type.ASSIGN) ) tkErr("missing ASSIGN inside exprAssign", currentToken);
				
				return exprAssign();
			}
			
			if (exprPostfix()){
				
					if (eat(T_Type.ASSIGN)){
						
						return exprAssign();
					}
				
					//a series of optional sequences
					checkOptionalSequences();
					
				return true;
			}
			
			//I'm not sure if this last option is implemented correctly
			if (eat(T_Type.LPAR)){
				
					if (!typeName() ) tkErr("missing TypeName", currentToken);
				
					if (!eat(T_Type.RPAR)) tkErr(" missing RPAR", currentToken);
				
					if (!exprCast()) return false;
				
					checkOptionalSequences();
				
				return true;
			}
		
		return false;
	}
	
	private void checkOptionalSequences(){
		
		exprMul1();
		
		exprAdd1();
		
		exprRel1();
		
		exprEq1();
		
		exprAnd1();
		
		exprOr1();
	}
	
	private boolean exprOr() {
		
			if ( !exprAND() ) return false;
		
			// optional - can produce null string
			exprOr1();
		
		return true;
	}
	
	private boolean exprOr1() {
		
			if ( eat(T_Type.OR)){
			
				if (exprAND())  {
				
					return exprOr1();
				}else{
					tkErr("Missing exprAND after OR", currentToken);
				}
			}
				
		//It doesnt' really matter what I return below
		return true;
	}
	
	private boolean exprAND() {

			if ( !exprEq() ) return false;
		
			// optional - can produce null string
			exprAnd1();
		
		return true;
	}
	
	private boolean exprAnd1() {
		
			if ( eat(T_Type.AND)){
				//int startPos = index;
		
				if (exprEq())  {
			
					return exprAnd1();
				}else{
					tkErr("Missing exprEq after And", currentToken);
				}
			}
			
	
		return true;
	}

	private boolean exprEq() {
		
			if (!exprRel()) return false;
		
			exprEq1();
		
		return true;
	}
	
	private boolean exprEq1() {
		
			if ( eat(T_Type.EQUAL) || eat(T_Type.NOTEQ)){
				//int startPos = index;
			
				if (exprRel())  {
				
					return exprEq1();
				}else{
					tkErr("Missing exprRel after EQUAL/NOTEQ", currentToken);
				}
			}
				
		
		return true;
	}
		
	private boolean exprRel() {
		
			if ( !exprAdd() ) return false;
		
			exprRel1();
		
		return true;
	}
	
	private boolean exprRel1() {
		
			if ( eat(T_Type.LESS) || eat(T_Type.LESSEQ)
					|| eat(T_Type.GREATER) || eat(T_Type.GREATEREQ)){
		
				if (exprAdd())  {
			
					return exprRel1();
				}else{
					tkErr("Missing exprAdd", currentToken);
				}
			}
	
		return true;
	}


	private boolean exprAdd() {
		
			if ( !exprMul() ) return false;
	
			exprAdd1();
	
		return true;
	}
	
	private boolean exprAdd1() {
			
			if ( eat(T_Type.ADD) || eat(T_Type.SUB)){
	
				if (exprMul())  {
		
					return exprAdd1();
				}else{
					tkErr("Missing exprMul", currentToken);
				}
			}

	    return true;
	}

	private boolean exprMul() {
			if ( !exprCast() ) return false;

			exprMul1();

		return true;
	}

	

	private boolean exprMul1() {
		
			if ( eat(T_Type.MUL) || eat(T_Type.DIV)){

				if (exprCast())  {
	
					return exprMul1();
				}else{
					tkErr("Missing exprCast", currentToken);
				}
			}

		return true;
	}

	private boolean exprCast() {
			
			if (eat(T_Type.LPAR)){
				
				if (!typeName()) tkErr("invalid type name", currentToken);
				
				if (!eat(T_Type.RPAR)) tkErr("missing RPAR", currentToken);
				
				return exprCast();
				
			}else if (exprUnary()){
				return true;
			}
		
		
		// I throw an error only if it starts to match a part of the expression
		return false;
	}

	private boolean typeName() {
		Type ret;
		
			if ((ret = typeBase()) == null) return false;
			
			//optional + semantic action
			if (!arrayDecl(ret)){
				ret.setnElements(-1);
			}
			
		return true;
	}

	private boolean exprUnary() {
		
			if ( eat(T_Type.SUB) || eat(T_Type.NOT) ) {
				return exprUnary();
			} else if (exprPostfix()){
				return true;
			}
		
		//none of the alternatives were matched
		return false;
	}
	
	private boolean exprPostfix() {
		
			if ( !exprPrimary() ) return false;
		
			// optional
			exprPostfix1();
		
		return true;
	}
	
	private boolean exprPostfix1() {
		
		switch (currentToken.getTokenType()) {
			
			case LBRACKET :
					eat(T_Type.LBRACKET);
					if ( !expr() )
						tkErr("Incorrect <<expr>> inside postfix expression", currentToken);
					if ( !eat(T_Type.RBRACKET) )
						tkErr("Missing ']'", currentToken);
					exprPostfix1();
				
				break;
			
			case DOT :
					eat(T_Type.DOT);
					if ( !eat(T_Type.ID) )
						tkErr("Missing ID after DOT", currentToken);
					exprPostfix1();
				break;
			
			// tkErr(currentToken, "An postfix expr must start with a DOT or
		// LBRACKET or exprPrimary");
		}
		
		return true;
	}
	
	private boolean exprPrimary() {
		
			switch (currentToken.getTokenType()) {
				
				case ID :
									eat(T_Type.ID);
                                    
										//semantic action
										//check that a function call, a variable is first defined
										//and only after used
										Symbol s; 
										String idName = consumedToken.getTokenName();
										
										s = findSymbol(symbolsTable, idName);
										
										if (s == null){
											tkErr("Undefined symbol <<" + idName + ">>",consumedToken);
										}
									if (eat(T_Type.LPAR)) {
									
										expr();
									
										while (eat(T_Type.COMMA)) {
											expr();
										}
									
										if (!eat(T_Type.RPAR)) tkErr("missing RPAR inside exprPrimary", currentToken);
									}
							
					// if the currentToken isn't CT_Int nothing happens
				case CT_INT :
									eat(T_Type.CT_INT);
				
				case CT_CHAR :
									eat(T_Type.CT_CHAR);
				
				case CT_REAL :
									eat(T_Type.CT_REAL);
				
				case CT_STRING :
									eat(T_Type.CT_STRING);
				
					return true;
			
				case LPAR :
									eat(T_Type.LPAR);
									if ( !expr() )
										tkErr("invalid expr inside exprPrimary!", currentToken);
									if ( !eat(T_Type.RPAR) )
										tkErr(" missing RPAR inside exprPrimay!", currentToken);
					return true;
			}
		
		return false;
	}
	
	private Type typeBase() {
		Type ret = null;
		Integer opt = tbCasesHM.get(currentToken.getTokenType());
		
			//no valid option found
			if (opt == null) return ret;
			
			ret = new Type();
		
				switch (opt.intValue()){
					
					//int token
					case 0:
						    eat(T_Type.INT);
							ret.setTypeBase(TbEnum.TB_INT);
						break;
					
					case 1:
							eat(T_Type.DOUBLE);
							ret.setTypeBase(TbEnum.TB_DOUBLE);
						break;
						
					case 2:
							eat(T_Type.CHAR);
							ret.setTypeBase(TbEnum.TB_CHAR);
						break;

					case 3:
							eat(T_Type.STRUCT);
							if (!eat(T_Type.ID)) tkErr("After struct an ID must follow", currentToken);
							
							//TODO refactor declVar
							//if (currentToken.getTokenType() == T_Type.LACC) return null;
								
								String tokenId = consumedToken.getTokenName();	
								//semantic action
								Symbol s = findSymbol(symbolsTable, tokenId);
								
									if (s == null) tkErr("undefined symbol: " + tokenId, currentToken);
									
									if (s.getCls() != ClsEnum.CLS_STRUCT) tkErr("<<" +tokenId + ">>" + " is not a struct member", currentToken);
									
									ret.setTypeBase(TbEnum.TB_STRUCT);
									ret.setS(s);
					break;
				}
		
			
		return ret;
	}
	
	private boolean declStruct() {
		String idName = "";
		int startIndex = index;
		
			if (!eat(T_Type.STRUCT)) return false;
		
			if (!eat(T_Type.ID)) tkErr(" missing struct <<ID>> (namely the name of the struct)", currentToken);
			else{
				//semantic action
				 idName = consumedToken.getTokenName();
			}
			 
			if (!eat(T_Type.LACC)){
				//it might be just a var declaration of type struct X
					resetCrtTk(startIndex);
					
				return false;
			}
			else{
				//semantic action
				Token idToken = tokens.get(index - 2);
				
				if (findSymbol(symbolsTable,idName) != null) tkErr("symbol redefinition: " + idName, idToken);
				
				//a struct can be declared only in a global context
				if (crtDepth != SymbolDepth.GLOBAL) 
					tkErr("the struct <<" + idToken.getTokenName() + ">> isn't declared in a global context", idToken );
				
				crtStruct = addSymbol(symbolsTable, idName, ClsEnum.CLS_STRUCT);
			}
			
			
		
				while (declVar()){}
			
			if (!eat(T_Type.RACC)) tkErr(" missing RACC in struct declaration", currentToken);
			
			if (!eat(T_Type.SEMICOLON)) tkErr(" missing SEMICOLON after struct declaration", currentToken);
		
			//semantic action
			crtStruct = null;
			
		return true;
	}
	
	 private boolean declFunc() {
		 Type currentSymType;
		 Token idToken;
	  
		        //first variant of creating a func
		 		if ((currentSymType = typeBase()) != null){
		 			
			 			//optional
			 			if (eat(T_Type.MUL)){
			 				currentSymType.setnElements(0);
			 			}else{
			 				currentSymType.setnElements(-1);
			 			}
		 			
			 			if (!eat(T_Type.ID)) return false;
			 			
			 			idToken = consumedToken;
			 			
			 			if (!eat(T_Type.LPAR)) return false;
		 			
		 			return checkFuncSignature(idToken, currentSymType);
		 		}
		 		
		 		//second
		 		if (eat(T_Type.VOID)){
			 			currentSymType = new Type();
			 			currentSymType.setTypeBase(TbEnum.TB_VOID);
			 			
			 			if (!eat(T_Type.ID)) tkErr("missing ID in funcDeclr", currentToken);
			 			
			 			idToken = consumedToken;
			 			
			 			if (!eat(T_Type.LPAR)) tkErr("missing LPAR in funcDeclr", currentToken);
			 			
		 			return checkFuncSignature(idToken, currentSymType);
		 		}
	
		 
		 //if none of the possibilities were matched then isn't a func declaration	
		 return false;
	 }
	

	
	private boolean checkFuncSignature(Token idToken, Type currentSymbType) {
		
			//semantic action
			if (findSymbol(symbolsTable, idToken.getTokenName())
							!= null){
				tkErr("Symbol redefinition:" + idToken.getTokenName(), idToken);
			}
			
			crtFunc = addSymbol(symbolsTable, idToken.getTokenName(), ClsEnum.CLS_FUNC);
			crtFunc.setType(currentSymbType);
			//java enums are immutable object references
			crtDepth = crtDepth.next();
			
				
				    //optional
					if (funcArg()){
						
							while (eat(T_Type.COMMA)){
								funcArg();
							}
					}
					
					if (!eat(T_Type.RPAR)) tkErr("missing RPAR in declFunc", currentToken);
				
						//semantic action
						crtDepth = crtDepth.previous();
					
					if (!stmCompound()) tkErr("missing stmCompound after function declaration", currentToken);
				
						//must be tested
						deleteSymbolsAfter(symbolsTable, crtFunc);
						crtFunc = null;
		
		return true;
	}


	

	private boolean funcArg() {
			Type ret;
			String idText;
			
				if ((ret = typeBase()) == null) return false;
				
				if (!eat(T_Type.ID)) tkErr("missing var Id inside funcArg", currentToken);
			
				idText = consumedToken.getTokenName();
				
					if (!arrayDecl(ret)){
						ret.setnElements(-1);
					}
					
					//semantic action
					//add func args to symbols table
					Symbol s = addSymbol(symbolsTable, idText, ClsEnum.CLS_VAR);
					s.setMem(MemEnum.MEM_ARG);
					s.setDepth(crtDepth);
					s.setType(ret);
					
					//add func args to the member field of FUNC symbol
					//so that when the function is called later
					//we can check if the calling arguments are correct
					s = addSymbol(crtFunc.getMembers(), idText, ClsEnum.CLS_VAR);
		            s.setMem(MemEnum.MEM_ARG);
		            s.setDepth(crtDepth);
					s.setType(ret);
		            
		return true;
	}
	
	private boolean stmCompound() {
		    //semantic action
		
			//get the last element in the ArrayList
			int last = symbolsTable.size() - 1;
			Symbol start = symbolsTable.get(last);
		
					if (!eat(T_Type.LACC)) return false;
					
					crtDepth = crtDepth.next();
					
						while (declVar() || stm()){}
						
					    if (!eat(T_Type.RACC)) tkErr("missing RACC after func. definition", currentToken);
				
				    crtDepth = crtDepth.previous();
					
			deleteSymbolsAfter(symbolsTable, start);
				    
		return true;
	}


	private boolean stm() {
		
			if (stmCompound()) return true;
			
			if (eat(T_Type.IF)){
				
					if (!eat(T_Type.LPAR)) tkErr("missing LPAR inside IF stm", currentToken);
				
					if (!expr()) tkErr("missing or invalid exp after ( in IF stm", currentToken);
				
					if (!eat(T_Type.RPAR)) tkErr("missing RPAR inside IF stm", currentToken);
				
					if (!stm()) tkErr("missing or invalid stm inside <<if (true) branch>>", currentToken);
				
					//optional
					if (eat(T_Type.ELSE)){

						if (!stm()) tkErr("missing or invalid stm inside <<if (false) branch>>", currentToken);
					}
				
				return true;
			
			}
  			
			if (eat(T_Type.WHILE)){
				
					if (!eat(T_Type.LPAR)) tkErr("missing LPAR in while declaration", currentToken);
				
					if (!expr()) tkErr("missing or invalid exp after ( in While stm", currentToken);
				
					if (!eat(T_Type.RPAR)) tkErr("missing RPAR inside WHILE stm", currentToken);
				
					if (!stm()) tkErr("missing or invalid stm inside <<while (true)>>", currentToken);
				
				return true;
			}
			
			if (eat(T_Type.FOR)){
				
					if (!eat(T_Type.LPAR)) tkErr("missing LPAR in for declaration", currentToken);
				
					expr();
				
					if (!eat(T_Type.SEMICOLON)) tkErr("missing semicolon inside for dec", currentToken);
				
					expr();
				
					if (!eat(T_Type.SEMICOLON)) tkErr("missing semicolon inside for dec", currentToken);
				
					expr();
				
					if (!eat(T_Type.RPAR)) tkErr("missing RPAR in for declaration", currentToken);
				
					if (!stm()) tkErr("missing or invalid stm inside <<for>>", currentToken);
				
				return true;
			}
			
			if (eat(T_Type.BREAK)){
				if (!eat(T_Type.SEMICOLON)) tkErr("missing semicolon after BREAK inside for definition", currentToken);
				
				return true;
			}
			
			if (eat(T_Type.RETURN)){
				expr();
				
				if (!eat(T_Type.SEMICOLON)) tkErr("missing semicolon after RETURN token", currentToken);
				
				return true;
			}
			
			 expr();
			 
			 if (eat(T_Type.SEMICOLON)){
				 return true;
			 }
		
		return false;
	}

	public static void main(String[] args) {
		
		SyntacticAnalyzer sa = new SyntacticAnalyzer(
		        "/mnt/92AEC018AEBFF339/proiecte/Proiecte java/Compilation Techniques/tests/5.c");
		System.out.println(sa.run());
		
	}
	
}
