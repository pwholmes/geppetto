package org.geppetto.parser.generated;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * The class <code>ParserValTest</code> contains tests for the class <code>{@link ParserVal}</code>.
 *
 * @generatedBy CodePro at 5/12/13 6:06 AM
 * @author user
 * @version $Revision: 1.0 $
 */
public class ParserValTest {
	/**
	 * Run the ParserVal() constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:06 AM
	 */
	@Test
	public void testParserVal_1()
		throws Exception {

		ParserVal result = new ParserVal();

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the ParserVal(double) constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:06 AM
	 */
	@Test
	public void testParserVal_2()
		throws Exception {
		double val = 1.0;

		ParserVal result = new ParserVal(val);

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the ParserVal(int) constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:06 AM
	 */
	@Test
	public void testParserVal_3()
		throws Exception {
		int val = 1;

		ParserVal result = new ParserVal(val);

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the ParserVal(Object) constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:06 AM
	 */
	@Test
	public void testParserVal_4()
		throws Exception {
		Object val = new Object();

		ParserVal result = new ParserVal(val);

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the ParserVal(String) constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:06 AM
	 */
	@Test
	public void testParserVal_5()
		throws Exception {
		String val = "";

		ParserVal result = new ParserVal(val);

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
		new org.junit.runner.JUnitCore().run(ParserValTest.class);
	}
}