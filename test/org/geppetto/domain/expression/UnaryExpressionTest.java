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
		UnaryExpression fixture = new UnaryExpression(Operator.ADD, new ConstantExpression( new Value("constant")));

		String result = fixture.getOperand().getValue().getStringValue();

		// add additional test code here
		assertEquals(result, "constant");
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