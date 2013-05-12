package org.geppetto.domain.expression;

import java.util.ArrayList;
import org.geppetto.GeppettoException;
import org.geppetto.domain.declaration.Value;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * The class <code>FunctionExpressionTest</code> contains tests for the class <code>{@link FunctionExpression}</code>.
 *
 * @generatedBy CodePro at 5/12/13 10:21 AM
 * @author user
 * @version $Revision: 1.0 $
 */
public class FunctionExpressionTest {
	/**
	 * Run the FunctionExpression() constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 10:21 AM
	 */
	@Test
	public void testFunctionExpression_1()
		throws Exception {

		FunctionExpression result = new FunctionExpression();

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.UnsupportedClassVersionError: org/geppetto/domain/expression/FunctionExpression : Unsupported major.minor version 51.0
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
	 * Run the FunctionExpression(String,ArrayList<Expression>) constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 10:21 AM
	 */
	@Test
	public void testFunctionExpression_2()
		throws Exception {
		String name = "";
		ArrayList<Expression> argumentList = new ArrayList();

		FunctionExpression result = new FunctionExpression(name, argumentList);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.UnsupportedClassVersionError: org/geppetto/domain/expression/FunctionExpression : Unsupported major.minor version 51.0
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
	 * Run the ArrayList<Expression> getArguments() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 10:21 AM
	 */
	@Test
	public void testGetArguments_1()
		throws Exception {
		FunctionExpression fixture = new FunctionExpression("", new ArrayList());

		ArrayList<Expression> result = fixture.getArguments();

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the String getName() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 10:21 AM
	 */
	@Test
	public void testGetName_1()
		throws Exception {
		FunctionExpression fixture = new FunctionExpression("", new ArrayList());

		String result = fixture.getName();

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the Value getValue() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 10:21 AM
	 */
	@Test
	public void testGetValue_1()
		throws Exception {
		FunctionExpression fixture = new FunctionExpression("", new ArrayList());

		Value result = fixture.getValue();

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the Value getValue() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 10:21 AM
	 */
	@Test
	public void testGetValue_2()
		throws Exception {
		FunctionExpression fixture = new FunctionExpression("", new ArrayList());

		Value result = fixture.getValue();

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the Value getValue() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 10:21 AM
	 */
	@Test(expected = org.geppetto.GeppettoException.class)
	public void testGetValue_3()
		throws Exception {
		FunctionExpression fixture = new FunctionExpression("", new ArrayList());

		Value result = fixture.getValue();

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the boolean isLValue() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 10:21 AM
	 */
	@Test
	public void testIsLValue_1()
		throws Exception {
		FunctionExpression fixture = new FunctionExpression("", new ArrayList());

		boolean result = fixture.isLValue();

		// add additional test code here
		assertTrue(result);
	}

	/**
	 * Run the void setValue(Value) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 10:21 AM
	 */
	@Test(expected = org.geppetto.GeppettoException.class)
	public void testSetValue_1()
		throws Exception {
		FunctionExpression fixture = new FunctionExpression("", new ArrayList());
		Value value = new Value(1);

		fixture.setValue(value);

		// add additional test code here
	}

	/**
	 * Run the String toString() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 10:21 AM
	 */
	@Test
	public void testToString_1()
		throws Exception {
		FunctionExpression fixture = new FunctionExpression("", new ArrayList());

		String result = fixture.toString();

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Perform pre-test initialization.
	 *
	 * @throws Exception
	 *         if the initialization fails for some reason
	 *
	 * @generatedBy CodePro at 5/12/13 10:21 AM
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
	 * @generatedBy CodePro at 5/12/13 10:21 AM
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
	 * @generatedBy CodePro at 5/12/13 10:21 AM
	 */
	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(FunctionExpressionTest.class);
	}
}