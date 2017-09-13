/**
 * 
 */
package La;

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
public class LexicalAnalyzer {
	private LinkedList<Token> tokens;
	private String pathToSourceCode;

	public LexicalAnalyzer(String pathToSoruceCode){
		this.pathToSourceCode = pathToSoruceCode;
		tokens = run();
	}
	
	private LinkedList<String> readSmallTextFile(String aFileName) throws IOException {
		Path path = Paths.get(aFileName);
		final Charset ENCODING = StandardCharsets.UTF_8;
		Scanner scanner = new Scanner(path, ENCODING.name());
		
		LinkedList<String> inputFileLines = new LinkedList<>();
		
			while (scanner.hasNextLine()){
				inputFileLines.add(scanner.nextLine() + '\n');
			}
		
			// append End at the input file	
			String endLine =  String.valueOf((char)0);
			inputFileLines.add(endLine);
		
		return inputFileLines;
		
	}
	
	private LinkedList<Token> run(){
		    tokens = new LinkedList<>();
			LinkedList<String> inputFile = null;

				try {
					inputFile = readSmallTextFile(pathToSourceCode);
				} catch (IOException e) {
				
					e.printStackTrace();
					System.err.println("Couldn't read file:" + pathToSourceCode);
				}
			
				TransitionManager tr = new TransitionManager(inputFile);
				Token currentToken;
			
				while ((currentToken = getNextToken(tr)) != null){
					tokens.add(currentToken);
				
					if (currentToken.getTokenType() == T_Type.END){
						break;
					}
				}
			
			
		return tokens;
	}
	
	private La.Token getNextToken(Visitor visitor) {
		State state0 = State0.getInstance();
		
		return state0.accept(visitor);
		
	}
	
	public void showTokens(LinkedList<Token> tokens) {
		// TODO Auto-generated method stub
		
		  int line = tokens.get(0).getLine();
		  System.out.print("1: ");
		  
		  for (Token t : tokens){
			   if (line != t.getLine()){
				   line = t.getLine();
				   System.out.print("\n" + (line + 1) + ": ");
			   }
			  
	    	   System.out.print(" " + t.getTokenType() );
	    	   
	    	   if (T_Type.CT_STRING == t.getTokenType()){
	    		   System.out.printf(":%s",  t.getValue());
	    	   }
	    	   
         }
		
	}
	
	public LinkedList<Token> getTokens(){
		return tokens;
	}

	public static void main(String[] args){
		
		LexicalAnalyzer la = new LexicalAnalyzer("/mnt/92AEC018AEBFF339/proiecte/Proiecte java/Compilation Techniques/tests/1.c");
		la.showTokens(la.run());
	}
	
}
