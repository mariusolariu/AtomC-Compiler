package La;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class TransitionManager implements Visitor {
	private char currentChar;
	private LinkedList<String> inputFileLines;
	private String currentLine;
	private int index;
	private int startPos;
	private int line;
	private LinkedList<Character> escapedChars;
	
	private Map<String, T_Type> keywords;
	
	public TransitionManager(LinkedList<String> inputFileLines) {
		this.inputFileLines = inputFileLines;
		
		keywords = new HashMap<>();
		
		keywords.put("break", T_Type.BREAK);
		keywords.put("char", T_Type.CHAR);
		keywords.put("double", T_Type.DOUBLE);
		keywords.put("else", T_Type.ELSE);
		keywords.put("for", T_Type.FOR);
		keywords.put("if", T_Type.IF);
		keywords.put("int", T_Type.INT);
		keywords.put("return", T_Type.RETURN);
		keywords.put("struct", T_Type.STRUCT);
		keywords.put("void", T_Type.VOID);
		keywords.put("while", T_Type.WHILE);
		
	}
	
	// TODO choose proper name for this method
	private Token consumingState(State currentState) {
		int nextStateID;
		currentChar = currentLine.charAt(index);
		
		if ( currentState.canConsume(currentChar) ) {
			index++;
			
			nextStateID = currentState.consume(currentChar);
			State nextState = Factory.getNextStateObj(nextStateID);
			
			return nextState.accept(this);
		} else {
			return null;
		}
		
	}
	
	// TODO choose proper name for this method
	private Token consumingStateAndError(State currentState) {
		Token returnToken = consumingState(currentState);
		
		if ( returnToken != null ) {
			
			return returnToken;
			
		} else {
			System.out.println("Can't consume '" + currentChar + "' in state " + currentState + ". Source code line: "
			        + (line + 1));
			System.exit(1);
			
			return null;
		}
	}
	
	@Override
	public Token visit(State0 state0) {
		int nextStateID;
		currentLine = inputFileLines.get(line);
		currentChar = currentLine.charAt(index);
		
		if ( state0.canConsume(currentChar) ) {
			
			if ( currentChar == '\n' ) {
				line++;
				index = 0;
				
			} else {
				startPos = index;
				index++;
			}
			
			nextStateID = state0.consume(currentChar);
			State nextState = Factory.getNextStateObj(nextStateID);
			
			return nextState.accept(this);
			
		} else {
			tkErr(line + 1, "Can't consume '" + currentChar + "' in state 0" + ". Source code line: " + (line + 1));
			return null;
		}
		
	}
	
	@Override
	public Token visit(State1 state1) {
		Token returnToken = consumingState(state1);
		
		if ( returnToken != null ) {
			
			return returnToken;
			
		} else {
			// epsilon transition
			State state = Factory.getNextStateObj(2);
			
			return state.accept(this);
		}
		
	}
	
	@Override
	// final state - id
	public Token visit(State2 state2) {
		int endPos = index;
		String word = currentLine.substring(startPos, endPos);
		Token newToken;
		
		// check if it's a keyword;
		if ( keywords.get(word) != null ) {
			T_Type tokenType = keywords.get(word);
			newToken = new Token(tokenType, line);
			
		} else { // it's an id
			newToken = new Token(state2.getTokenType(), line, word);
		}
		
		return newToken;
	}
	
	@Override
	public Token visit(State3 state3) {
		Token returnToken = consumingState(state3);
		
		if ( returnToken != null ) {
			
			return returnToken;
		} else {
			// epsilon transition
			State state = Factory.getNextStateObj(5);
			
			return state.accept(this);
		}
	}
	
	@Override
	public Token visit(State4 state4) {
		// equal token
		
		Token newToken = new Token(state4.getTokenType(), line);
		
		return newToken;
	}
	
	@Override
	// assign
	public Token visit(State5 state5) {
		Token newToken = new Token(state5.getTokenType(), line);
		
		return newToken;
	}
	
	private void tkErr(int line, String message) {
		System.err.println("Error in line " + line + ": " + message);
		System.exit(1);
	}
	
	@Override
	// end
	public Token visit(State6 state6) {
		
		return new Token(state6.getTokenType(), line);
	}
	
	@Override
	// comma
	public Token visit(State7 state7) {
		
		return new Token(state7.getTokenType(), line);
	}
	
	@Override
	// semicolon
	public Token visit(State8 state8) {
		
		return new Token(state8.getTokenType(), line);
	}
	
	@Override
	// lpar
	public Token visit(State9 state9) {
		
		return new Token(state9.getTokenType(), line);
	}
	
	@Override
	// rpar
	public Token visit(State10 state10) {
		return new Token(state10.getTokenType(), line);
	}
	
	@Override
	// lbracket
	public Token visit(State11 state11) {
		return new Token(state11.getTokenType(), line);
	}
	
	@Override
	// rbracket
	public Token visit(State12 state12) {
		return new Token(state12.getTokenType(), line);
	}
	
	@Override
	// lacc
	public Token visit(State13 state13) {
		return new Token(state13.getTokenType(), line);
	}
	
	@Override
	// racc
	public Token visit(State14 state14) {
		return new Token(state14.getTokenType(), line);
	}
	
	@Override
	public Token visit(State15 state15) {
		Token returnToken = consumingState(state15);
		
		if ( returnToken != null ) {
			
			return returnToken;
			
		} else {
			// epsilon transition
			State state = Factory.getNextStateObj(16);
			
			return state.accept(this);
		}
	}
	
	@Override
	public Token visit(State16 state16) {
		int endPos = index;
		
		String sValue = currentLine.substring(startPos, endPos);
		int iValue = Integer.valueOf(sValue);
		AtomC_Int myInt = new AtomC_Int(iValue);
		
		return new Token(T_Type.CT_INT, line, myInt);
	}
	
	@Override
	public Token visit(State17 state17) {
		Token returnToken = consumingState(state17);
		
		if ( returnToken != null ) {
			
			return returnToken;
			
		} else {
			// epsilon transition - one digit number,between [0-7]
			State state = Factory.getNextStateObj(19);
			
			return state.accept(this);
		}
		
	}
	
	@Override
	public Token visit(State18 state18) {
		Token returnToken = consumingState(state18);
		
		if ( returnToken != null ) {
			
			return returnToken;
			
		} else {
			// epsilon transition
			State state = Factory.getNextStateObj(19);
			
			return state.accept(this);
		}
	}
	
	@Override
	public Token visit(State19 state19) {
		int endPos = index;
		
		String sValue = currentLine.substring(startPos, endPos);
		int iValue = Integer.valueOf(sValue, 8);
		AtomC_Int myInt = new AtomC_Int(iValue);
		
		return new Token(state19.getTokenType(), line, myInt);
	}
	
	@Override
	public Token visit(State20 state20) {
		return consumingStateAndError(state20);
	}
	
	@Override
	public Token visit(State21 state21) {
		Token returnToken = consumingState(state21);
		
		if ( returnToken != null ) {
			
			return returnToken;
			
		} else {
			// epsilon transition
			State state = Factory.getNextStateObj(22);
			
			return state.accept(this);
		}
	}
	
	@Override
	public Token visit(State22 state22) {
		int endPos = index;
		
		String sValue = currentLine.substring(startPos + 2, endPos);
		int iValue = Integer.valueOf(sValue, 16);
		AtomC_Int myInt = new AtomC_Int(iValue);
		
		return new Token(state22.getTokenType(), line, myInt);
	}
	
	@Override
	public Token visit(State23 state23) {
		return consumingStateAndError(state23);
	}
	
	@Override
	public Token visit(State24 state24) {
		Token returnToken = consumingState(state24);
		
		if ( returnToken != null ) {
			
			return returnToken;
			
		} else {
			// epsilon transition
			State state = Factory.getNextStateObj(23);
			
			return state.accept(this);
		}
	}
	
	@Override
	public Token visit(State25 state25) {
		Token returnToken = consumingState(state25);
		
		if ( returnToken != null ) {
			
			return returnToken;
			
		} else {
			// epsilon transition
			State state = Factory.getNextStateObj(28);
			
			return state.accept(this);
		}
	}
	
	@Override
	public Token visit(State26 state26) {
		return consumingStateAndError(state26);
	}
	
	@Override
	public Token visit(State27 state27) {
		Token returnToken = consumingState(state27);
		
		if ( returnToken != null ) {
			
			return returnToken;
			
		} else {
			// epsilon transition
			State state = Factory.getNextStateObj(28);
			
			return state.accept(this);
		}
	}
	
	@Override
	public Token visit(State28 state28) {
		int endPos = index;
		String sValue;
		
		// convert real number starting with 0 from AtomC to double in java
		if ( currentLine.charAt(startPos) == '0' ) {
			sValue = currentLine.substring(startPos + 1, endPos);
		} else {
			sValue = currentLine.substring(startPos, endPos);
		}
		
		double dValue = Double.valueOf(sValue);
		Value myDouble = new AtomC_Double(dValue);
		
		return new Token(state28.getTokenType(), line, myDouble);
	}
	
	@Override
	public Token visit(State29 state29) {
		int nextStateID;
		currentChar = currentLine.charAt(index);
		
		if ( state29.canConsume(currentChar) ) {
			index++;
			
			nextStateID = state29.consume(currentChar);
			State nextState = Factory.getNextStateObj(nextStateID);
			
			//add the escaped char to this list which eventually will be part 
			//of a CT_STRING or a CT_CHAR
			if (escapedChars == null){
				escapedChars = new LinkedList();
			}
			
			escapedChars.add(currentChar);
			
			return nextState.accept(this);
		} else {
			System.out.println("Can't consume '" + currentChar + "' in state 32 or an escape sequence was found!"
			        + ". Source code line: " + (line + 1));
			System.exit(1);
			
			return null;
		}
		
	}
	

	
	@Override
	public Token visit(State31 state31) {
		return consumingStateAndError(state31);
	}
	
	@Override
	public Token visit(State32 state32) {
		int nextStateID;
		currentChar = currentLine.charAt(index);
		char nextChar = currentLine.charAt(index + 1);
		
		if ( state32.canConsume(currentChar) ) {
			index++;
			
			nextStateID = state32.consume(currentChar);
			State nextState = Factory.getNextStateObj(nextStateID);
			
			return nextState.accept(this);
		} else {
			System.out.println("Can't consume '" + currentChar + "' in state 32 or an escape sequence was found!"
			        + ". Source code line: " + (line + 1));
			System.exit(1);
			
			return null;
		}
	}
	
	@Override
	public Token visit(State33 state33) {
		return consumingStateAndError(state33);
	}
	
	@Override
	public Token visit(State34 state34) {
		char charAtStartPos = currentLine.charAt(startPos);
		
			if (charAtStartPos != '"') //not part of a string, thus a char
			{
				int endPos = index;
				char c;
				AtomC_Char myChar;
				
				// handle the case when the char expression contains escaped chars
				if (escapedChars != null){
					myChar = new AtomC_Char((char)127, escapedChars); //char 127 is a flag
					escapedChars = null;
				}else{ 
					//normal char
					 c = currentLine.charAt(startPos + 1);
					 myChar = new AtomC_Char(c);
				}
				
		
				return new Token(T_Type.CT_CHAR, line, myChar);
			
			}else{ //is a part of a string
				return consumingStateAndError(state34);
			}
			
	}
	
	@Override
	public Token visit(State35 state35) {
		int nextStateID;
		currentChar = currentLine.charAt(index);
		
		
		if ( state35.canConsume(currentChar)) {
			index++;
			
			nextStateID = state35.consume(currentChar);
			State nextState = Factory.getNextStateObj(nextStateID);
			
			return nextState.accept(this);
		} else {
			System.out.println("Can't consume '" + currentChar + "' in state 35 "
			        + ". Source code line: " + (line + 1));
			System.exit(1);
			
			return null;
		}
	}
	
	@Override
	public Token visit(State36 state36) {
		int nextStateID;
		currentChar = currentLine.charAt(index);
		
		
		if ( state36.canConsume(currentChar) ) {
			index++;
			
			nextStateID = state36.consume(currentChar);
			State nextState = Factory.getNextStateObj(nextStateID);
			
			return nextState.accept(this);
		} else {
			System.out.println("Can't consume '" + currentChar + "' in state 35 or an escape sequence was found!");
			System.exit(1);
			
			return null;
		}
	}
	
	@Override
	public Token visit(State37 state37) {
		// remove the quotation mark from the beginning and the end of the
		// string
		AtomC_String myString; 
		int endPos = index - 1;
		startPos = startPos + 1;
		
		String originalString = currentLine.substring(startPos, endPos);
		if (escapedChars != null){
		
			String flag = String.valueOf((char)127);
			
			for (Character c : escapedChars){
				//TODO make this replace all escaped chars from string
				originalString.replaceAll("\\" + c, flag);
				
			}
			
			myString = new AtomC_String(originalString, escapedChars);
			escapedChars = null;
		}else{
			myString = new AtomC_String(originalString);
		}
		
		return new Token(state37.getTokenType(), line, myString);
	}
	
	@Override
	// add
	public Token visit(State38 state38) {
		return new Token(state38.getTokenType(), line);
	}
	
	@Override
	public Token visit(State39 state39) {
		return new Token(state39.getTokenType(), line);
	}
	
	@Override
	public Token visit(State40 state40) {
		return new Token(state40.getTokenType(), line);
	}
	
	@Override
	public Token visit(State41 state41) {
		return new Token(state41.getTokenType(), line);
	}
	
	@Override
	public Token visit(State42 state42) {
		return new Token(state42.getTokenType(), line);
	}
	
	@Override
	public Token visit(State43 state43) {
		return consumingStateAndError(state43);
	}
	
	@Override
	public Token visit(State44 state44) {
		return new Token(state44.getTokenType(), line);
	}
	
	@Override
	public Token visit(State45 state45) {
		return consumingStateAndError(state45);
	}
	
	@Override
	public Token visit(State46 state46) {
		return new Token(state46.getTokenType(), line);
	}
	
	@Override
	public Token visit(State47 state47) {
		Token returnToken = consumingState(state47);
		
		if ( returnToken != null ) {
			
			return returnToken;
			
		} else {
			// simple - NOT token
			return new Token(state47.getTokenType(), line);
		}
		
	}
	
	@Override
	public Token visit(State48 state48) {
		return new Token(state48.getTokenType(), line);
	}
	
	@Override
	public Token visit(State49 state49) {
		Token returnToken = consumingState(state49);
		
		if ( returnToken != null ) {
			
			return returnToken;
			
		} else {
			// simple - LESS token
			return new Token(state49.getTokenType(), line);
		}
	}
	
	@Override
	public Token visit(State50 state50) {
		return new Token(state50.getTokenType(), line);
	}
	
	@Override
	public Token visit(State51 state51) {
		Token returnToken = consumingState(state51);
		
		if ( returnToken != null ) {
			
			return returnToken;
			
		} else {
			// simple - Greater token
			return new Token(state51.getTokenType(), line);
		}
	}
	
	@Override
	public Token visit(State52 state52) {
		return new Token(state52.getTokenType(), line);
	}
	
	@Override
	public Token visit(State53 state53) {
		
		if ( currentChar != '\n' ) {
			// if we have a tab or a carriage return then the line isn't
			// incremented
			return new Token(state53.getTokenType(), line);
		} else {
			
			// line is automatically incremented when the lexycal analyzer
				// encounters '\n'
			return new Token(state53.getTokenType(), line - 1);
		}
		
	}
	
	@Override
	public Token visit(State54 state54) {
		int nextStateID;
		currentChar = currentLine.charAt(index);
		
		if ( state54.canConsume(currentChar) ) {
			index++;
			
			nextStateID = state54.consume(currentChar);
			State nextState = Factory.getNextStateObj(nextStateID);
			
			return nextState.accept(this);
		} else { // epsilon transition - div token
			State nextToken = Factory.getNextStateObj(41);
			
			return nextToken.accept(this);
		}
	}
	
	@Override
	public Token visit(State55 state55) {
		int nextStateID;
		currentChar = currentLine.charAt(index);
		
		if ( state55.canConsume(currentChar) ) {
			
			if ( currentChar == '\n' ) {
				line++;
				index = 0;
				currentLine = inputFileLines.get(line);
			} else {
				index++;
			}
			
			nextStateID = state55.consume(currentChar);
			State nextState = Factory.getNextStateObj(nextStateID);
			
			return nextState.accept(this);
		} else {
			System.out.println("Can't consume '" + currentChar + "' in state 55 or an escape sequence was found!"
			        + ". Source code line: " + (line + 1));
			System.exit(1);
			
			return null;
		}
	}
	
	@Override
	public Token visit(State56 state56) {
		Token returnToken = consumingState(state56);
		
		if ( returnToken != null ) {
			
			return returnToken;
			
		} else {
			// epsilon transition
			State state = Factory.getNextStateObj(57);
			
			return state.accept(this);
		}
	}
	
	@Override
	public Token visit(State57 state57) {
		return new Token(state57.getTokenType(), line);
	}
	
	@Override
	public Token visit(State58 state58) {
		return consumingStateAndError(state58);
	}
	
	@Override
	public Token visit(State59 state59) {
		return new Token(state59.getTokenType(), line);
	}
	
}
