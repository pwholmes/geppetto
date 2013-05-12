package org.geppetto.domain.expression;

import org.geppetto.domain.declaration.Value;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * The class <code>VariantExpressionTest</code> contains tests for the class <code>{@link VariantExpression}</code>.
 *
 * @generatedBy CodePro at 5/12/13 6:41 PM
 * @author user
 * @version $Revision: 1.0 $
 */
public class VariantExpressionTest {
	/**
	 * Run the VariantExpression(String,String) constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:41 PM
	 */
	@Test
	public void testVariantExpression_1()
		throws Exception {
		String name = "";
		String propertyName = "";

		VariantExpression result = new VariantExpression(name, propertyName);

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the VariantExpression(String,String,String) constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:41 PM
	 */
	@Test
	public void testVariantExpression_2()
		throws Exception {
		String name = "";
		String propertyName = "";
		String attributeName = "";

		VariantExpression result = new VariantExpression(name, propertyName, attributeName);

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the String getAttributeName() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:41 PM
	 */
	@Test
	public void testGetAttributeName_1()
		throws Exception {
		VariantExpression fixture = new VariantExpression("", "", "");

		String result = fixture.getAttributeName();

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the String getName() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:41 PM
	 */
	@Test
	public void testGetName_1()
		throws Exception {
		VariantExpression fixture = new VariantExpression("", "", "");

		String result = fixture.getName();

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the String getPropertyName() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:41 PM
	 */
	@Test
	public void testGetPropertyName_1()
		throws Exception {
		VariantExpression fixture = new VariantExpression("", "", "");

		String result = fixture.getPropertyName();

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the Value getValue() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:41 PM
	 */
	@Test
	public void testGetValue_1()
		throws Exception {
		VariantExpression fixture = new VariantExpression("", "", "");

		Value result = fixture.getValue();

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the boolean isLValue() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:41 PM
	 */
	@Test
	public void testIsLValue_1()
		throws Exception {
		VariantExpression fixture = new VariantExpression("", "", "");

		boolean result = fixture.isLValue();

		// add additional test code here
		assertTrue(result);
	}

	/**
	 * Run the void setValue(Value) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:41 PM
	 */
	@Test
	public void testSetValue_1()
		throws Exception {
		VariantExpression fixture = new VariantExpression("", "", "");
		Value value = new Value(1);

		fixture.setValue(value);

		// add additional test code here
	}

	/**
	 * Run the String toString() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:41 PM
	 */
	@Test
	public void testToString_1()
		throws Exception {
		VariantExpression fixture = new VariantExpression("", "", "");

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
	 * @generatedBy CodePro at 5/12/13 6:41 PM
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
	 * @generatedBy CodePro at 5/12/13 6:41 PM
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
	 * @generatedBy CodePro at 5/12/13 6:41 PM
	 */
	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(VariantExpressionTest.class);
	}
}