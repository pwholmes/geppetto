package org.geppetto.domain.expression;

import org.geppetto.GeppettoException;
import org.geppetto.domain.Operator;
import org.geppetto.domain.declaration.Value;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * The class <code>BinaryExpressionTest</code> contains tests for the class <code>{@link BinaryExpression}</code>.
 *
 * @generatedBy CodePro at 5/12/13 9:16 AM
 * @author user
 * @version $Revision: 1.0 $
 */
public class BinaryExpressionTest {
	
	Value intValue1;
	Value intValue2 ;
	Value booleanValueTrue ;
	Value booleanValueFalse ;
	Value floatValue1;
	Value floatValue2 ;
	
	ConstantExpression intCE1;
	ConstantExpression intCE2 ;
	ConstantExpression boolCET ;
	ConstantExpression boolCEF;
	ConstantExpression floatCE1;
	ConstantExpression floatCE2 ;

	
	/**
	 * Run the BinaryExpression(Expression,Operator,Expression) constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 9:16 AM
	 */
	@Test
	public void testBinaryExpression_1()
		throws Exception {
		Expression operand1 = new ConstantExpression( intValue1);
		Operator operator = Operator.ADD;
		Expression operand2 = new ConstantExpression(intValue2);

		BinaryExpression result = new BinaryExpression(operand1, operator, operand2);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.UnsupportedClassVersionError: org/geppetto/domain/expression/BinaryExpression : Unsupported major.minor version 51.0
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
	 * Run the Expression getOperand1() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 9:16 AM
	 */
	@Test
	public void testGetOperand1_1()
		throws Exception {
		BinaryExpression fixture = new BinaryExpression(intCE1, Operator.ADD, intCE2);

//		Expression result = fixture.getOperand1();

		// add additional test code here
		assertEquals(intCE1, fixture.getOperand1());
	}

	/**
	 * Run the Expression getOperand2() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 9:16 AM
	 */
	@Test
	public void testGetOperand2_1()
		throws Exception {
		BinaryExpression fixture = new BinaryExpression(intCE1, Operator.ADD, intCE2);

		Expression result = fixture.getOperand2();

		// add additional test code here
		assertEquals(intCE2, result);
	}

	/**
	 * Run the Operator getOperator() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 9:16 AM
	 */
	@Test
	public void testGetOperator_1()
		throws Exception {
		BinaryExpression fixture = new BinaryExpression(new InputExpression(), Operator.ADD, new InputExpression());

		Operator result = fixture.getOperator();

		// add additional test code here
		assertEquals(result, Operator.ADD);
	}

	/**
	 * Run the Value getValue() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 9:16 AM
	 */
	@Test
	public void testGetValue_1()
		throws Exception {
		BinaryExpression fixture = new BinaryExpression(floatCE1, Operator.ADD, floatCE2);

		Value result = fixture.getOperand1().getValue();

		// add additional test code here
		assertEquals(result, floatValue1);
	}

	/**
	 * Run the Value getValue() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 9:16 AM
	 */
	@Test
	public void testGetValue_2()
		throws Exception {
		BinaryExpression fixture = new BinaryExpression(floatCE1, Operator.ADD, floatCE2);

		Value result = fixture.getOperand2().getValue();

		// add additional test code here
		assertEquals(result, floatValue2);
	}

	/**
	 * Run the Value getValue() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 9:16 AM
	 */
	@Test
	public void testGetValue_3()
		throws Exception {
		BinaryExpression fixture = new BinaryExpression(floatCE1, Operator.MULTIPLY, floatCE2);

		Value result = fixture.getOperand1().getValue();

		// add additional test code here
		assertEquals(result, floatValue1);
	}

	/**
	 * Run the boolean isLValue() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 9:16 AM
	 */
	@Test
	public void testIsLValue_1()
		throws Exception {
		BinaryExpression fixture = new BinaryExpression(new InputExpression(), Operator.ADD, new InputExpression());

		boolean result = fixture.isLValue();

		// add additional test code here
		assertFalse(result);
	}

	/**
	 * Run the void setValue(Value) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 9:16 AM
	 */
	@Test(expected = org.geppetto.GeppettoException.class)
	public void testSetValue_1()
		throws Exception {
		BinaryExpression fixture = new BinaryExpression(new InputExpression(), Operator.ADD, new InputExpression());
		Value value = new Value(1);

		fixture.setValue(value);

		// add additional test code here
	}

	/**
	 * Run the String toString() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 9:16 AM
	 */
	@Test
	public void testToString_1()
		throws Exception {
		BinaryExpression fixture = new BinaryExpression(intCE1, Operator.ADD, intCE2);

		String result = fixture.toString();
		// add additional test code here
		assertEquals(result,
		"{BinaryExpression: operand1: {ConstantExpression: name: null; value: {Value: type: INT; intValue: 1; floatValue: 0.0; stringValue: null; booleanValue: false}}; operator: ADD; operand2: {ConstantExpression: name: null; value: {Value: type: INT; intValue: 2; floatValue: 0.0; stringValue: null; booleanValue: false}}}"
				);
	
	}

	/**
	 * Perform pre-test initialization.
	 *
	 * @throws Exception
	 *         if the initialization fails for some reason
	 *
	 * @generatedBy CodePro at 5/12/13 9:16 AM
	 */
	@Before
	public void setUp()
		throws Exception {
		// add additional set up code here
		intValue1 = new Value(1);
		intValue2 = new Value(2);
		booleanValueTrue = new Value(true);
		booleanValueFalse = new Value(false);
		floatValue1 = new Value(1.0f);
		floatValue2 = new Value(2.0f);
		
		intCE1 = new ConstantExpression( intValue1 );
		intCE2 = new ConstantExpression( intValue2 );
		boolCET = new ConstantExpression( booleanValueTrue );
		boolCEF = new ConstantExpression( booleanValueFalse );
		floatCE1 = new ConstantExpression( floatValue1 );
		floatCE2 = new ConstantExpression( floatValue2 );

	}

	/**
	 * Perform post-test clean-up.
	 *
	 * @throws Exception
	 *         if the clean-up fails for some reason
	 *
	 * @generatedBy CodePro at 5/12/13 9:16 AM
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
	 * @generatedBy CodePro at 5/12/13 9:16 AM
	 */
	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(BinaryExpressionTest.class);
	}
}