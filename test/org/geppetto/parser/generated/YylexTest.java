package org.geppetto.parser.generated;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * The class <code>YylexTest</code> contains tests for the class <code>{@link Yylex}</code>.
 *
 * @generatedBy CodePro at 5/12/13 6:06 AM
 * @author user
 * @version $Revision: 1.0 $
 */
public class YylexTest {
	/**
	 * Run the Yylex(InputStream) constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:06 AM
	 */
	@Test
	public void testYylex_1()
		throws Exception {
		InputStream in = new ByteArrayInputStream("".getBytes());

		Yylex result = new Yylex(in);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.UnsupportedClassVersionError: org/geppetto/parser/generated/Yylex : Unsupported major.minor version 51.0
		//       at java.lang.ClassLoader.defineClass1(Native Method)
		//       at java.lang.ClassLoader.defineClass(ClassLoader.java:634)
		//       at java.security.SecureClassLoader.defineClass(SecureClassLoader.java:142)
		//       at java.net.URLClassLoader.defineClass(URLClassLoader.java:277)
		//       at java.net.URLClassLoader.access$000(URLClassLoader.java:73)
		//       at java.net.URLClassLoader$1.run(URLClassLoader.java:212)
		//       at java.security.AccessController.doPrivileged(Native Method)
		//       at java.net.URLClassLoader.findClass(URLClassLoader.java:205)
		//       at java.lang.ClassLoader.loadClass(ClassLoader.java:321)
		//       at com.instantiations.assist.eclipse.junit.execution.core.UserDefinedClassLoader.loadClass(UserDefinedClassLoader.java:62)
		//       at java.lang.ClassLoader.loadClass(ClassLoader.java:266)
		//       at com.instantiations.assist.eclipse.junit.execution.core.ExecutionContextImpl.getClass(ExecutionContextImpl.java:99)
		//       at com.instantiations.eclipse.analysis.expression.model.SimpleTypeExpression.execute(SimpleTypeExpression.java:205)
		//       at com.instantiations.eclipse.analysis.expression.model.InstanceCreationExpression.execute(InstanceCreationExpression.java:425)
		//       at com.instantiations.assist.eclipse.junit.execution.core.ExecutionRequest.execute(ExecutionRequest.java:286)
		//       at com.instantiations.assist.eclipse.junit.execution.communication.LocalExecutionClient$1.run(LocalExecutionClient.java:158)
		//       at java.lang.Thread.run(Thread.java:679)
		assertNotNull(result);
	}

	/**
	 * Run the Yylex(Reader) constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:06 AM
	 */
	@Test
	public void testYylex_2()
		throws Exception {
		Reader in = new StringReader("");

		Yylex result = new Yylex(in);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.UnsupportedClassVersionError: org/geppetto/parser/generated/Yylex : Unsupported major.minor version 51.0
		//       at java.lang.ClassLoader.defineClass1(Native Method)
		//       at java.lang.ClassLoader.defineClass(ClassLoader.java:634)
		//       at java.security.SecureClassLoader.defineClass(SecureClassLoader.java:142)
		//       at java.net.URLClassLoader.defineClass(URLClassLoader.java:277)
		//       at java.net.URLClassLoader.access$000(URLClassLoader.java:73)
		//       at java.net.URLClassLoader$1.run(URLClassLoader.java:212)
		//       at java.security.AccessController.doPrivileged(Native Method)
		//       at java.net.URLClassLoader.findClass(URLClassLoader.java:205)
		//       at java.lang.ClassLoader.loadClass(ClassLoader.java:321)
		//       at com.instantiations.assist.eclipse.junit.execution.core.UserDefinedClassLoader.loadClass(UserDefinedClassLoader.java:62)
		//       at java.lang.ClassLoader.loadClass(ClassLoader.java:266)
		//       at com.instantiations.assist.eclipse.junit.execution.core.ExecutionContextImpl.getClass(ExecutionContextImpl.java:99)
		//       at com.instantiations.eclipse.analysis.expression.model.SimpleTypeExpression.execute(SimpleTypeExpression.java:205)
		//       at com.instantiations.eclipse.analysis.expression.model.InstanceCreationExpression.execute(InstanceCreationExpression.java:425)
		//       at com.instantiations.assist.eclipse.junit.execution.core.ExecutionRequest.execute(ExecutionRequest.java:286)
		//       at com.instantiations.assist.eclipse.junit.execution.communication.LocalExecutionClient$1.run(LocalExecutionClient.java:158)
		//       at java.lang.Thread.run(Thread.java:679)
		assertNotNull(result);
	}

	/**
	 * Run the Yylex(Reader,Parser,List<String>) constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:06 AM
	 */
	@Test
	public void testYylex_3()
		throws Exception {
		Reader r = new StringReader("");
		Parser yyparser = new Parser();
		List<String> symbolTable = new ArrayList<String>();

		Yylex result = new Yylex(r, yyparser, symbolTable);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.UnsupportedClassVersionError: org/geppetto/parser/generated/Yylex : Unsupported major.minor version 51.0
		//       at java.lang.ClassLoader.defineClass1(Native Method)
		//       at java.lang.ClassLoader.defineClass(ClassLoader.java:634)
		//       at java.security.SecureClassLoader.defineClass(SecureClassLoader.java:142)
		//       at java.net.URLClassLoader.defineClass(URLClassLoader.java:277)
		//       at java.net.URLClassLoader.access$000(URLClassLoader.java:73)
		//       at java.net.URLClassLoader$1.run(URLClassLoader.java:212)
		//       at java.security.AccessController.doPrivileged(Native Method)
		//       at java.net.URLClassLoader.findClass(URLClassLoader.java:205)
		//       at java.lang.ClassLoader.loadClass(ClassLoader.java:321)
		//       at com.instantiations.assist.eclipse.junit.execution.core.UserDefinedClassLoader.loadClass(UserDefinedClassLoader.java:62)
		//       at java.lang.ClassLoader.loadClass(ClassLoader.java:266)
		//       at com.instantiations.assist.eclipse.junit.execution.core.ExecutionContextImpl.getClass(ExecutionContextImpl.java:99)
		//       at com.instantiations.eclipse.analysis.expression.model.SimpleTypeExpression.execute(SimpleTypeExpression.java:205)
		//       at com.instantiations.eclipse.analysis.expression.model.InstanceCreationExpression.execute(InstanceCreationExpression.java:425)
		//       at com.instantiations.assist.eclipse.junit.execution.core.ExecutionRequest.execute(ExecutionRequest.java:286)
		//       at com.instantiations.assist.eclipse.junit.execution.communication.LocalExecutionClient$1.run(LocalExecutionClient.java:158)
		//       at java.lang.Thread.run(Thread.java:679)
		assertNotNull(result);
	}

	/**
	 * Run the void yybegin(int) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:06 AM
	 */
	@Test
	public void testYybegin_1()
		throws Exception {
		Yylex fixture = new Yylex(new StringReader(""), new Parser(), new ArrayList<String>());
		fixture.yyreset(new StringReader(""));
		fixture.yybegin(1);
		int newState = 1;

		fixture.yybegin(newState);

		// add additional test code here
	}

	/**
	 * Run the char yycharat(int) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:06 AM
	 */
	@Test
	public void testYycharat_1()
		throws Exception {
		Yylex fixture = new Yylex(new StringReader(""), new Parser(), new ArrayList<String>());
		fixture.yyreset(new StringReader(""));
		fixture.yybegin(1);
		int pos = 1;

		char result = fixture.yycharat(pos);

		// add additional test code here
		assertEquals(' ', result);
	}

	/**
	 * Run the void yyclose() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:06 AM
	 */
	@Test
	public void testYyclose_1()
		throws Exception {
		Yylex fixture = new Yylex(new StringReader(""), new Parser(), new ArrayList<String>());
		fixture.yyreset(new StringReader(""));
		fixture.yybegin(1);

		fixture.yyclose();

		// add additional test code here
	}

	/**
	 * Run the void yyclose() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:06 AM
	 */
	@Test
	public void testYyclose_2()
		throws Exception {
		Yylex fixture = new Yylex(new StringReader(""), new Parser(), new ArrayList<String>());
		fixture.yyreset((Reader) null);
		fixture.yybegin(1);

		fixture.yyclose();

		// add additional test code here
	}

	/**
	 * Run the void yyclose() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:06 AM
	 */
	@Test(expected = java.io.IOException.class)
	public void testYyclose_3()
		throws Exception {
		Yylex fixture = new Yylex(new StringReader(""), new Parser(), new ArrayList<String>());
		fixture.yyreset(new StringReader(""));
		fixture.yybegin(1);

		fixture.yyclose();

		// add additional test code here
	}

	/**
	 * Run the int yylength() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:06 AM
	 */
	@Test
	public void testYylength_1()
		throws Exception {
		Yylex fixture = new Yylex(new StringReader(""), new Parser(), new ArrayList<String>());
		fixture.yyreset(new StringReader(""));
		fixture.yybegin(1);

		int result = fixture.yylength();

		// add additional test code here
		assertEquals(0, result);
	}

	/**
	 * Run the int yylex() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:06 AM
	 */
	@Test
	public void testYylex_4()
		throws Exception {
		Yylex fixture = new Yylex(new StringReader(""), new Parser(), new ArrayList<String>());
		fixture.yyreset(new StringReader(""));
		fixture.yybegin(1);

		int result = fixture.yylex();

		// add additional test code here
		assertEquals(0, result);
	}

	/**
	 * Run the int yylex() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:06 AM
	 */
	@Test
	public void testYylex_5()
		throws Exception {
		Yylex fixture = new Yylex(new StringReader(""), new Parser(), new ArrayList<String>());
		fixture.yyreset(new StringReader(""));
		fixture.yybegin(1);

		int result = fixture.yylex();

		// add additional test code here
		assertEquals(0, result);
	}

	/**
	 * Run the int yylex() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:06 AM
	 */
	@Test
	public void testYylex_6()
		throws Exception {
		Yylex fixture = new Yylex(new StringReader(""), new Parser(), new ArrayList<String>());
		fixture.yyreset(new StringReader(""));
		fixture.yybegin(1);

		int result = fixture.yylex();

		// add additional test code here
		assertEquals(0, result);
	}

	/**
	 * Run the int yylex() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:06 AM
	 */
	@Test
	public void testYylex_7()
		throws Exception {
		Yylex fixture = new Yylex(new StringReader(""), new Parser(), new ArrayList<String>());
		fixture.yyreset(new StringReader(""));
		fixture.yybegin(1);

		int result = fixture.yylex();

		// add additional test code here
		assertEquals(0, result);
	}

	/**
	 * Run the int yylex() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:06 AM
	 */
	@Test
	public void testYylex_8()
		throws Exception {
		Yylex fixture = new Yylex(new StringReader(""), new Parser(), new ArrayList<String>());
		fixture.yyreset(new StringReader(""));
		fixture.yybegin(1);

		int result = fixture.yylex();

		// add additional test code here
		assertEquals(0, result);
	}

	/**
	 * Run the int yylex() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:06 AM
	 */
	@Test
	public void testYylex_9()
		throws Exception {
		Yylex fixture = new Yylex(new StringReader(""), new Parser(), new ArrayList<String>());
		fixture.yyreset(new StringReader(""));
		fixture.yybegin(1);

		int result = fixture.yylex();

		// add additional test code here
		assertEquals(0, result);
	}

	/**
	 * Run the int yylex() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:06 AM
	 */
	@Test
	public void testYylex_10()
		throws Exception {
		Yylex fixture = new Yylex(new StringReader(""), new Parser(), new ArrayList<String>());
		fixture.yyreset(new StringReader(""));
		fixture.yybegin(1);

		int result = fixture.yylex();

		// add additional test code here
		assertEquals(0, result);
	}

	/**
	 * Run the int yylex() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:06 AM
	 */
	@Test
	public void testYylex_11()
		throws Exception {
		Yylex fixture = new Yylex(new StringReader(""), new Parser(), new ArrayList<String>());
		fixture.yyreset(new StringReader(""));
		fixture.yybegin(1);

		int result = fixture.yylex();

		// add additional test code here
		assertEquals(0, result);
	}

	/**
	 * Run the int yylex() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:06 AM
	 */
	@Test
	public void testYylex_12()
		throws Exception {
		Yylex fixture = new Yylex(new StringReader(""), new Parser(), new ArrayList<String>());
		fixture.yyreset(new StringReader(""));
		fixture.yybegin(1);

		int result = fixture.yylex();

		// add additional test code here
		assertEquals(0, result);
	}

	/**
	 * Run the int yylex() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:06 AM
	 */
	@Test
	public void testYylex_13()
		throws Exception {
		Yylex fixture = new Yylex(new StringReader(""), new Parser(), new ArrayList<String>());
		fixture.yyreset(new StringReader(""));
		fixture.yybegin(1);

		int result = fixture.yylex();

		// add additional test code here
		assertEquals(0, result);
	}

	/**
	 * Run the int yylex() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:06 AM
	 */
	@Test
	public void testYylex_14()
		throws Exception {
		Yylex fixture = new Yylex(new StringReader(""), new Parser(), new ArrayList<String>());
		fixture.yyreset(new StringReader(""));
		fixture.yybegin(1);

		int result = fixture.yylex();

		// add additional test code here
		assertEquals(0, result);
	}

	/**
	 * Run the int yylex() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:06 AM
	 */
	@Test
	public void testYylex_15()
		throws Exception {
		Yylex fixture = new Yylex(new StringReader(""), new Parser(), new ArrayList<String>());
		fixture.yyreset(new StringReader(""));
		fixture.yybegin(1);

		int result = fixture.yylex();

		// add additional test code here
		assertEquals(0, result);
	}

	/**
	 * Run the int yylex() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:06 AM
	 */
	@Test
	public void testYylex_16()
		throws Exception {
		Yylex fixture = new Yylex(new StringReader(""), new Parser(), new ArrayList<String>());
		fixture.yyreset(new StringReader(""));
		fixture.yybegin(1);

		int result = fixture.yylex();

		// add additional test code here
		assertEquals(0, result);
	}

	/**
	 * Run the int yylex() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:06 AM
	 */
	@Test
	public void testYylex_17()
		throws Exception {
		Yylex fixture = new Yylex(new StringReader(""), new Parser(), new ArrayList<String>());
		fixture.yyreset(new StringReader(""));
		fixture.yybegin(1);

		int result = fixture.yylex();

		// add additional test code here
		assertEquals(0, result);
	}

	/**
	 * Run the int yylex() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:06 AM
	 */
	@Test
	public void testYylex_18()
		throws Exception {
		Yylex fixture = new Yylex(new StringReader(""), new Parser(), new ArrayList<String>());
		fixture.yyreset(new StringReader(""));
		fixture.yybegin(1);

		int result = fixture.yylex();

		// add additional test code here
		assertEquals(0, result);
	}

	/**
	 * Run the int yylex() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:06 AM
	 */
	@Test
	public void testYylex_19()
		throws Exception {
		Yylex fixture = new Yylex(new StringReader(""), new Parser(), new ArrayList<String>());
		fixture.yyreset(new StringReader(""));
		fixture.yybegin(1);

		int result = fixture.yylex();

		// add additional test code here
		assertEquals(0, result);
	}

	/**
	 * Run the void yypushback(int) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:06 AM
	 */
	@Test
	public void testYypushback_1()
		throws Exception {
		Yylex fixture = new Yylex(new StringReader(""), new Parser(), new ArrayList<String>());
		fixture.yyreset(new StringReader(""));
		fixture.yybegin(1);
		int number = 1;

		fixture.yypushback(number);

		// add additional test code here
	}

	/**
	 * Run the void yypushback(int) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:06 AM
	 */
	@Test
	public void testYypushback_2()
		throws Exception {
		Yylex fixture = new Yylex(new StringReader(""), new Parser(), new ArrayList<String>());
		fixture.yyreset(new StringReader(""));
		fixture.yybegin(1);
		int number = 1;

		fixture.yypushback(number);

		// add additional test code here
	}

	/**
	 * Run the void yyreset(Reader) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:06 AM
	 */
	@Test
	public void testYyreset_1()
		throws Exception {
		Yylex fixture = new Yylex(new StringReader(""), new Parser(), new ArrayList<String>());
		fixture.yyreset(new StringReader(""));
		fixture.yybegin(1);
		Reader reader = new StringReader("");

		fixture.yyreset(reader);

		// add additional test code here
	}

	/**
	 * Run the int yystate() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:06 AM
	 */
	@Test
	public void testYystate_1()
		throws Exception {
		Yylex fixture = new Yylex(new StringReader(""), new Parser(), new ArrayList<String>());
		fixture.yyreset(new StringReader(""));
		fixture.yybegin(1);

		int result = fixture.yystate();

		// add additional test code here
		assertEquals(0, result);
	}

	/**
	 * Run the String yytext() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:06 AM
	 */
	@Test
	public void testYytext_1()
		throws Exception {
		Yylex fixture = new Yylex(new StringReader(""), new Parser(), new ArrayList<String>());
		fixture.yyreset(new StringReader(""));
		fixture.yybegin(1);

		String result = fixture.yytext();

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Perform pre-test initialization.
	 *
	 * @throws Exception
	 *         if the initialization fails for some reason
	 *
	 * @generatedBy CodePro at 5/12/13 6:06 AM
	 */
	@Before
	public void setUp()
		throws Exception {
		// add additional set up code here
	}

	/**
	 * Perform post-test clean-up.
	 *
	 * @throws Exception
	 *         if the clean-up fails for some reason
	 *
	 * @generatedBy CodePro at 5/12/13 6:06 AM
	 */
	@After
	public void tearDown()
		throws Exception {
		// Add additional tear down code here
	}

	/**
	 * Launch the test.
	 *
	 * @param args the command line arguments
	 *
	 * @generatedBy CodePro at 5/12/13 6:06 AM
	 */
	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(YylexTest.class);
	}
}