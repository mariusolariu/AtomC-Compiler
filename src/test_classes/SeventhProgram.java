package test_classes;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import syntacticA_domainA.SyntacticAnalyzer;

public class SeventhProgram {
	@Test
	public void test() {
		SyntacticAnalyzer sa = new SyntacticAnalyzer(
		        "/mnt/92AEC018AEBFF339/proiecte/Proiecte java/Compilation Techniques/tests/7.c");
		boolean ok = sa.run();
		
		assertEquals(true, ok);
	}
}
