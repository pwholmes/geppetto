package org.geppetto.domain.expression;

import org.geppetto.GeppettoException;
import org.geppetto.domain.Operator;
import org.geppetto.domain.declaration.Value;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * The class <code>UnaryExpressionTest</code> contains tests for the class <code>{@link UnaryExpression}</code>.
 *
 * @generatedBy CodePro at 5/12/13 7:33 AM
 * @author user
 * @version $Revision: 1.0 $
 */
public class UnaryExpressionTest {
	/**
	 * Run the UnaryExpression(Operator,Expression) constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 7:33 AM
	 */
	@Test
	public void testUnaryExpression_1()
		throws Exception {
		Operator operator = Operator.ADD;
		Expression operand = new InputExpression();

		UnaryExpression result = new UnaryExpression(operator, operand);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.UnsupportedClassVersionError: org/geppetto/domain/expression/UnaryExpression : Unsupported major.minor version 51.0
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
	 * Run the Expression getOperand() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 7:33 AM
	 */
	@Test
	public void testGetOperand_1()
		throws Exception {
		UnaryExpression fixture = new UnaryExpression(Operator.ADD, new ConstantExpression(new Value(3)));

		Expression result = fixture.getOperand();

		// add additional test code here
		assertEquals(result.getClass(), ConstantExpression.class);
	}

	/**
	 * Run the Operator getOperator() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 7:33 AM
	 */
	@Test
	public void testGetOperator_1()
		throws Exception {
		UnaryExpression fixture = new UnaryExpression(Operator.ADD, new ConstantExpression(new Value("stringValue")));

		Operator result = fixture.getOperator();

		// add additional test code here
		assertEquals(result, Operator.ADD);
	}

	/**
	 * Run the Value getValue() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 7:33 AM
	 */
	@Test
	public void testGetValue_1()
		throws Exception {
		UnaryExpression fixture = new UnaryExpression(Operator.ADD, new ConstantExpression( new Value(1)));

		ConstantExpression result = (ConstantExpression) fixture.getOperand();

		// add additional test code here
		assertEquals(result.getValue().getIntValue(), 1);
	}

	/**
	 * Run the Value getValue() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 7:33 AM
	 */
	@Test
	public void testGetValue_2()
		throws Exception {
		UnaryExpression fixture = new UnaryExpression(Operator.UNARY_MINUS, new ConstantExpression( new Value(true) ));

		ConstantExpression result = (ConstantExpression) fixture.getOperand();

		// add additional test code here
		assertTrue(result.getValue().getBooleanValue());
	}



	/**
	 * Run the boolean isLValue() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 7:33 AM
	 */
	@Test
	public void testIsLValue_1()
		throws Exception {
		UnaryExpression fixture = new UnaryExpression(Operator.ADD, new VariableExpression("foo"));

		boolean result = fixture.isLValue();

		// add additional test code here
		assertFalse(result);
	}

	/**
	 * Run the void setValue(Value) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 7:33 AM
	 */
	@Test(expected = org.geppetto.GeppettoException.class)
	public void testSetValue_1()
		throws Exception {
		UnaryExpression fixture = new UnaryExpression(Operator.ADD, new InputExpression());
		Value value = new Value(1);

		fixture.setValue(value);

		// add additional test code here
		assertEquals(fixture.getValue().getIntValue(), 1);
	}

	/**
	 * Run the String toString() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 7:33 AM
	 */
	@Test
	public void testToString_1()
		throws Exception {
		UnaryExpression fixture = new UnaryExpression(Operator.ADD, new InputExpression());

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
	 * @generatedBy CodePro at 5/12/13 7:33 AM
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
	 * @generatedBy CodePro at 5/12/13 7:33 AM
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
	 * @generatedBy CodePro at 5/12/13 7:33 AM
	 */
	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(UnaryExpressionTest.class);
	}
}