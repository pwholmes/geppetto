package org.geppetto.domain.expression;

import org.geppetto.GeppettoException;
import org.geppetto.domain.declaration.Value;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * The class <code>ConstantExpressionTest</code> contains tests for the class <code>{@link ConstantExpression}</code>.
 *
 * @generatedBy CodePro at 5/12/13 6:28 AM
 * @author user
 * @version $Revision: 1.0 $
 */
public class ConstantExpressionTest {
	/**
	 * Run the ConstantExpression(String) constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:28 AM
	 */
	@Test
	public void testConstantExpression_1()
		throws Exception {
		String name = "helloWorld";

		ConstantExpression result = new ConstantExpression(name);

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the ConstantExpression(Value) constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:28 AM
	 */
	@Test
	public void testConstantExpression_2()
		throws Exception {
		Value value = new Value(1);

		ConstantExpression result = new ConstantExpression(value);

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the String getName() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:28 AM
	 */
	@Test
	public void testGetName_1()
		throws Exception {
		ConstantExpression fixture = new ConstantExpression("hello");

		String result = fixture.getName();

		// add additional test code here
		assertEquals(result, "hello");
	}

	/**
	 * Run the Value getValue() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:28 AM
	 */
	@Test
	public void testGetValue_1()
		throws Exception {
		ConstantExpression fixture = new ConstantExpression("");

		Value result = fixture.getValue();

		// add additional test code here
		assertNull(result);
	}

	/**
	 * Run the boolean isLValue() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:28 AM
	 */
	@Test
	public void testIsLValue_1()
		throws Exception {
		ConstantExpression fixture = new ConstantExpression("someString");

		boolean result = fixture.isLValue();

		// add additional test code here
		assertFalse(result);
	}

	/**
	 * Run the void setValue(Value) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:28 AM
	 */
	@Test(expected = org.geppetto.GeppettoException.class)
	public void testSetValue_1()
		throws Exception {
		ConstantExpression fixture = new ConstantExpression("");
		Value value = new Value(1);

		fixture.setValue(value);

		// add additional test code here
	}

	/**
	 * Run the String toString() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:28 AM
	 */
	@Test
	public void testToString_1()
		throws Exception {
		ConstantExpression fixture = new ConstantExpression("");

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
	 * @generatedBy CodePro at 5/12/13 6:28 AM
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
	 * @generatedBy CodePro at 5/12/13 6:28 AM
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
	 * @generatedBy CodePro at 5/12/13 6:28 AM
	 */
	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(ConstantExpressionTest.class);
	}
}