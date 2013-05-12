package org.geppetto;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * The class <code>InterpreterTest</code> contains tests for the class <code>{@link Interpreter}</code>.
 *
 * @generatedBy CodePro at 5/12/13 6:11 AM
 * @author user
 * @version $Revision: 1.0 $
 */
public class InterpreterTest {
	/**
	 * Run the Interpreter(boolean) constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:11 AM
	 */
	@Test
	public void testInterpreter_1()
		throws Exception {
		boolean debug = true;

		Interpreter result = new Interpreter(debug);
		assertNotNull(result);
	}




	/**
	 * Run the boolean isDebug() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:11 AM
	 */
	@Test
	public void testIsDebug_1()
		throws Exception {
		Interpreter fixture = new Interpreter(true);

		boolean result = fixture.isDebug();

		// add additional test code here
		assertTrue(result);
	}

	/**
	 * Run the boolean isDebug() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:11 AM
	 */
	@Test
	public void testIsDebug_2()
		throws Exception {
		Interpreter fixture = new Interpreter(false);

		boolean result = fixture.isDebug();

		// add additional test code here
		assertFalse(result);
	}

	/**
	 * Run the void print(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:11 AM
	 */
	@Test
	public void testPrint_1()
		throws Exception {
		Interpreter fixture = new Interpreter(true);
		String s = "";

		fixture.print(s);

		// add additional test code here
	}

	/**
	 * Run the void setDebug(boolean) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:11 AM
	 */
	@Test
	public void testSetDebug_1()
		throws Exception {
		Interpreter fixture = new Interpreter(true);
		boolean debug = true;

		fixture.setDebug(debug);

		// add additional test code here
	}

	/**
	 * Perform pre-test initialization.
	 *
	 * @throws Exception
	 *         if the initialization fails for some reason
	 *
	 * @generatedBy CodePro at 5/12/13 6:11 AM
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
	 * @generatedBy CodePro at 5/12/13 6:11 AM
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
	 * @generatedBy CodePro at 5/12/13 6:11 AM
	 */
	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(InterpreterTest.class);
	}
}