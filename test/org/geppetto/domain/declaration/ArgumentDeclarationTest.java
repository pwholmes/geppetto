package org.geppetto.domain.declaration;

import org.geppetto.domain.DataType;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * The class <code>ArgumentDeclarationTest</code> contains tests for the class <code>{@link ArgumentDeclaration}</code>.
 *
 * @generatedBy CodePro at 5/12/13 8:44 AM
 * @author user
 * @version $Revision: 1.0 $
 */
public class ArgumentDeclarationTest {
	/**
	 * Run the ArgumentDeclaration(String,DataType) constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 8:44 AM
	 */
	@Test
	public void testArgumentDeclaration_1()
		throws Exception {
		String name = "name";
		DataType type = DataType.BOOLEAN;

		ArgumentDeclaration result = new ArgumentDeclaration(name, type);

		// add additional test code here

		assertEquals(result.getName(), "name");
	}

	/**
	 * Run the ArgumentDeclaration(String,DataType) constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 8:44 AM
	 */
	@Test
	public void testArgumentDeclaration_2()
		throws Exception {
		String name = "name";
		DataType type = DataType.BOOLEAN;

		ArgumentDeclaration result = new ArgumentDeclaration(name, type);

		// add additional test code here

		assertEquals(result.getType(), DataType.BOOLEAN);
	}

	
	
	
	/**
	 * Run the String getName() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 8:44 AM
	 */
	@Test
	public void testGetName_1()
		throws Exception {
		ArgumentDeclaration fixture = new ArgumentDeclaration("", DataType.BOOLEAN);

		String result = fixture.getName();

		// add additional test code here
		assertEquals(result, "");
	}

	/**
	 * Run the DataType getType() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 8:44 AM
	 */
	@Test
	public void testGetType_1()
		throws Exception {
		ArgumentDeclaration fixture = new ArgumentDeclaration("", DataType.BOOLEAN);

		DataType result = fixture.getType();

		// add additional test code here
		assertEquals(result, DataType.BOOLEAN);
	}

	/**
	 * Run the String toString() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 8:44 AM
	 */
	@Test
	public void testToString_1()
		throws Exception {
		ArgumentDeclaration fixture = new ArgumentDeclaration("", DataType.BOOLEAN);

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
	 * @generatedBy CodePro at 5/12/13 8:44 AM
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
	 * @generatedBy CodePro at 5/12/13 8:44 AM
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
	 * @generatedBy CodePro at 5/12/13 8:44 AM
	 */
	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(ArgumentDeclarationTest.class);
	}
}