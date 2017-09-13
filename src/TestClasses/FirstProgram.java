package TestClasses;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import syntacticAdomainA.SyntacticAnalyzer;

public class FirstProgram {
	
	@Test
	public void test() {
		SyntacticAnalyzer sa = new SyntacticAnalyzer(
		        "/mnt/92AEC018AEBFF339/proiecte/Proiecte java/Compilation Techniques/tests/1.c");
		boolean ok = sa.run();
		
		assertEquals(true, ok);
	}
	
}
