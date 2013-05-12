package org.geppetto;

import java.util.ArrayList;
import org.geppetto.domain.DataType;
import org.geppetto.domain.declaration.ArgumentDeclaration;
import org.geppetto.domain.declaration.FunctionDefinition;
import org.geppetto.domain.declaration.Value;
import org.geppetto.domain.declaration.VariableDeclaration;
import org.geppetto.domain.expression.Expression;
import org.geppetto.domain.statement.CompoundStatement;
import org.geppetto.domain.statement.Statement;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * The class <code>ProgramContextTest</code> contains tests for the class <code>{@link ProgramContext}</code>.
 *
 * @generatedBy CodePro at 5/12/13 11:11 AM
 * @author user
 * @version $Revision: 1.0 $
 */
public class ProgramContextTest {
	
	String name;
	ArrayList<VariableDeclaration> variables;
	
	String name1;
	DataType dt1;
	VariableDeclaration vd1;
	String name2;
	DataType dt2;
	VariableDeclaration vd2;
	String name3;
	DataType dt3;
	VariableDeclaration vd3;
	
	/**
	 * Run the ProgramContext(String,ArrayList<VariableDeclaration>) constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 11:11 AM
	 */
	@Test
	public void testProgramContext_1()
		throws Exception {

		ProgramContext result = new ProgramContext(name, variables);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.UnsupportedClassVersionError: org/geppetto/ProgramContext : Unsupported major.minor version 51.0
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
		assertEquals(result.getVariableDeclarations(), variables);
	}

	/**
	 * Run the ProgramContext(FunctionDefinition,ArrayList<Expression>) constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 11:11 AM
	 */
	@Test
	public void testProgramContext_2()
		throws Exception {

		ProgramContext result = new ProgramContext(name, variables);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.UnsupportedClassVersionError: org/geppetto/ProgramContext : Unsupported major.minor version 51.0
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
		assertEquals(result.getName(), name);
	}


	/**
	 * Run the String getName() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 11:11 AM
	 */
	@Test
	public void testGetName_1()
		throws Exception {
		ProgramContext fixture = new ProgramContext(name, variables);

		String result = fixture.getName();

		// add additional test code here
		assertEquals(name, result);
	}

	/**
	 * Run the Value getReturnValue() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 11:11 AM
	 */
	@Test
	public void testGetReturnValue_1()
		throws Exception {
		ProgramContext fixture = new ProgramContext("", new ArrayList());
		fixture.setReturnValue(new Value(1));
		fixture.setReturnCalled(true);

		Value result = fixture.getReturnValue();

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the ArrayList<VariableDeclaration> getVariableDeclarations() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 11:11 AM
	 */
	@Test
	public void testGetVariableDeclarations_1()
		throws Exception {
		ProgramContext fixture = new ProgramContext(name, variables);
		fixture.setReturnValue(new Value(1));
		fixture.setReturnCalled(true);

		ArrayList<VariableDeclaration> result = fixture.getVariableDeclarations();

		// add additional test code here
		assertEquals(result, variables);
	}

	/**
	 * Run the boolean isReturnCalled() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 11:11 AM
	 */
	@Test
	public void testIsReturnCalled_1()
		throws Exception {
		ProgramContext fixture = new ProgramContext("", new ArrayList());
		fixture.setReturnValue(new Value(1));
		fixture.setReturnCalled(true);

		boolean result = fixture.isReturnCalled();

		// add additional test code here
		assertTrue(result);
	}

	/**
	 * Run the boolean isReturnCalled() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 11:11 AM
	 */
	@Test
	public void testIsReturnCalled_2()
		throws Exception {
		ProgramContext fixture = new ProgramContext(name, variables);
		fixture.setReturnValue(new Value(1));
		fixture.setReturnCalled(false);

		boolean result = fixture.isReturnCalled();

		// add additional test code here
		assertFalse(result);
	}

	/**
	 * Run the void setReturnCalled(boolean) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 11:11 AM
	 */
	@Test
	public void testSetReturnCalled_1()
		throws Exception {
		ProgramContext fixture = new ProgramContext(name, variables);
		Value intValue = new Value(1);
		fixture.setReturnValue(intValue);
		fixture.setReturnCalled(true);
		boolean returnCalled = true;

		fixture.setReturnCalled(returnCalled);

		// add additional test code here
		assertTrue(fixture.isReturnCalled());
	}

	/**
	 * Run the String toString() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 11:11 AM
	 */
	@Test
	public void testToString_1()
		throws Exception {
		ProgramContext fixture = new ProgramContext(name, variables);
		fixture.setReturnValue(new Value(1));
		fixture.setReturnCalled(true);

		String result = fixture.toString();
		String idealString = "{ProgramContext: name: null; variableDeclarations: " +
				"null; returnCalled: true; returnValue: {Value: type: INT; intValue: " +
				"1; floatValue: 0.0; stringValue: null; booleanValue: false}}" ;


		// add additional test code here
		assertEquals(result, idealString);
	}

	/**
	 * Perform pre-test initialization.
	 *
	 * @throws Exception
	 *         if the initialization fails for some reason
	 *
	 * @generatedBy CodePro at 5/12/13 11:11 AM
	 */
	@Before
	public void setUp()
		throws Exception {
		// add additional set up code here
		String name = "contextName";
		ArrayList<VariableDeclaration> variables = new ArrayList<VariableDeclaration>();
		
		String name1 = "name1";
		DataType dt1 = DataType.STRING;
		VariableDeclaration vd1 = new VariableDeclaration(name1, dt1);
		String name2 = "name2";
		DataType dt2 = DataType.STRING;
		VariableDeclaration vd2 = new VariableDeclaration(name2, dt2);
		String name3 = "name3";
		DataType dt3 = DataType.STRING;
		VariableDeclaration vd3 = new VariableDeclaration(name3, dt3);
		
		variables.add(vd1);
		variables.add(vd2);
		variables.add(vd3);

		
	}

	/**
	 * Perform post-test clean-up.
	 *
	 * @throws Exception
	 *         if the clean-up fails for some reason
	 *
	 * @generatedBy CodePro at 5/12/13 11:11 AM
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
	 * @generatedBy CodePro at 5/12/13 11:11 AM
	 */
	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(ProgramContextTest.class);
	}
}