/**
 * 
 */
package LexicalAnalyzer;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author marius
 *
 */
public class Parser {
	private LinkedList<Token> tokens = new LinkedList<>();
	private int line;
	private LinkedList<String> inputFileLines;
	
		private void addTk(int code) {
		
		}
	
		private void tkErr(int line, String message) {
			System.err.println("Error in line " + line + ": " + message);
			System.exit(1);
		}
		
		private Token createDecimalToIntToken(String currentInputLine, int startPos, int endPos){
				String sValue = currentInputLine.substring(startPos, endPos);
				int iValue = Integer.valueOf(sValue);
				MyInt myInt = new MyInt(iValue);
			
			return new Token(T_Type.CT_INT, line, myInt);
		}
		
		private Token createHexToIntToken(String currentInputLine, int startPos, int endPos){
			String sValue = currentInputLine.substring(startPos + 2, endPos);
			int iValue = Integer.valueOf(sValue,16);
			MyInt myInt = new MyInt(iValue);
		
		return new Token(T_Type.CT_INT, line, myInt);
	    }
		
		private Token createOctToIntToken(String currentInputLine, int startPos, int endPos){
			String sValue = currentInputLine.substring(startPos, endPos);
			int iValue = Integer.valueOf(sValue,8);
			MyInt myInt = new MyInt(iValue);
		
		    return new Token(T_Type.CT_INT, line, myInt);
	    }
		
		private T_Type getNextToken(){
			int state = 0;
			String currentInputLine = inputFileLines.get(line);
			int index = 0; // the current index of char to be processed from currentInputLine
			int startPos = 0; //the position in a string from which an identifier/value starts
			char currentChar = 0;
			
			while (true) {
				//if (index <= (currentInputLine.length()-1))
					 currentChar = currentInputLine.charAt(index ); //the char to be processed atm
				
				
				switch (state) {
					
					
					case 0 :
						
							//the beginning of a comment
							if (currentChar == '/'){
								state = 1;
								index++;
						
							// -||- ct_int decimal
							}else if ((currentChar >= '1') && (currentChar <= '9')){
								state = 8;
								startPos = index;
								index++;
							}
							// -||- ct_int oct or ct_int hex
							else if (currentChar == '0'){
								state = 10;
								startPos = index;
								index++;
							}
							// consume space, carriage return and tab
							else if ((currentChar == ' ') || (currentChar == '\r') || (currentChar == '\t')){
								index++;
							// new line
							}else if ((currentChar == '\n')){
								line++;
								currentInputLine = inputFileLines.get(line);
								index = 0;
							//end of line;
							}else if (currentChar == 0){
								tokens.add(new Token(T_Type.END, line));
								return T_Type.END;
							}
						
						break;
						
					//comment - *	
					case 1:	
						
							if (currentChar == '*'){
								state = 2;
								index++;
							}else{ //it's an error 
								tkErr(line," '*' expected as described in comment-RD");
							}
							
						break;
					
					//comment - *, ^*
					case 2:
						
						if (currentChar == '*'){
							state = 3;
							
						}else{
							//just consume the input char because it's a part of comment
							state = 5;
						}
						
						index++;
						
					break;
					
					//comment - /
					case 3:
						
							if (currentChar == '/'){
								state = 4;
							}else if (currentChar =='*'){
								//remains in the same state
							}else{
								state = 6;	
							}
						
						index++;
					
					break;
					
					// comment of type "/**/"
					case 4:
						
							tokens.add(new Token(T_Type.COMMENT, line));
							//TODO handle the full line
							//line++;
						
						return T_Type.COMMENT;
						
					case 5:	
						
							if (currentChar == '*'){
								state = 3;
							}else{
								//stays in the same state as long as currentChar == ^*
							}
						
							index++;
					
					break;
					
					case 6:
							
							if (currentChar == '/'){
								state = 7;
								index ++;
							}else{
								tkErr(line," according to comment-RD in state 6 it must receive a '/'");
								
							}
							
					break;
					
					case 7: 
						
							if (currentChar == '*'){
								state = 3;
								index++;
							}else{
								tkErr(line," according to comment-RD in state 7 it must receive a '*' ");
							
							}
					break;
					
					//final state CT_INT - int in decimal format
					case 8:
						
							if ((currentChar >= '0') && (currentChar <='9')){
								index++;
								//the state remains the same
							}else{
								//converting the string into CT_INT decimal
								int endPos = index;
								
								tokens.add(createDecimalToIntToken(currentInputLine, startPos, endPos));
								
								return T_Type.CT_INT;
							}
					break;
					
					case 10:
						
							//hex format
							if (currentChar=='x'){
								state = 12;	
								
							//oct format
							}else if ((currentChar >= '0') && (currentChar <='7')){
								state=11;
							}else{
								tkErr(line," [0-7] or 'x' expected according to transition diagram");
							}
						
						index++;

					break;
					
					case 11:
						
						if ((currentChar >= '0') && (currentChar <='7')){
							//remains in the same state
							index++;
						}else{
							//TODO handle invalid input
							
							int endPos = index;
							tokens.add(createOctToIntToken(currentInputLine, startPos, endPos));
							
							return T_Type.CT_INT;
						}
						
					break;
					
					case 12:
						
						if (((currentChar >= '0') && (currentChar <='9')) || (isValidHexChar(currentChar))){
							//remains in the same state
							index++;
						}else if (!isValidHexChar(currentChar) && (currentChar != '\n')){
							tkErr(line,"invalid hex number");
							
						}else{
							int endPos = index;
							tokens.add(createHexToIntToken(currentInputLine, startPos, endPos));
							
							return T_Type.CT_INT;
						}
						
					break;
					
				}
			}
		}
			
		
		private boolean isValidHexChar(char c){
			return (((c >= 'a') && (c <='f')) || ((c >= 'A') && (c <= 'F')) );
		}
	
		private void readSmallTextFile(String aFileName) throws IOException {
			Path path = Paths.get(aFileName);
			final Charset ENCODING = StandardCharsets.UTF_8;
			Scanner scanner = new Scanner(path, ENCODING.name());
			
			inputFileLines = new LinkedList<>();
			
				while (scanner.hasNextLine()){
					inputFileLines.add(scanner.nextLine() + '\n');
				}
			
			// append End at the input file	
			String endLine =  String.valueOf((char)0);
			inputFileLines.add(endLine);
			
		}
		

		/**
		 * 
		 */
		private void showTokens() {
			// TODO Auto-generated method stub
			
			  for (Token t : tokens){
		    	   System.out.println(t.getLine() + " : " + t.getTokenType());
		       }
			
		}
	
		public static void main(String[] args) throws IOException {
		       Parser p = new Parser();
		       
		       p.readSmallTextFile(
		        "/mnt/92AEC018AEBFF339/proiecte/Proiecte java/Compilation Techniques/firstProg.atomc");
		       
		       while (T_Type.END != p.getNextToken()){
		    	   p.line++;
		       }
		       p.showTokens();
		     
		}

}
