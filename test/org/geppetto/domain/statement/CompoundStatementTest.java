package org.geppetto.domain.statement;

import java.util.ArrayList;
import org.geppetto.domain.declaration.VariableDeclaration;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * The class <code>CompoundStatementTest</code> contains tests for the class <code>{@link CompoundStatement}</code>.
 *
 * @generatedBy CodePro at 5/12/13 6:41 PM
 * @author user
 * @version $Revision: 1.0 $
 */
public class CompoundStatementTest {
	/**
	 * Run the CompoundStatement(ArrayList<VariableDeclaration>,ArrayList<Statement>) constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:41 PM
	 */
	@Test
	public void testCompoundStatement_1()
		throws Exception {
		ArrayList<VariableDeclaration> variables = new ArrayList();
		ArrayList<Statement> statements = new ArrayList();

		CompoundStatement result = new CompoundStatement(variables, statements);

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the void execute() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:41 PM
	 */
	@Test
	public void testExecute_1()
		throws Exception {
		CompoundStatement fixture = new CompoundStatement(new ArrayList(), new ArrayList());

		fixture.execute();

		// add additional test code here
	}

	/**
	 * Run the void execute() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:41 PM
	 */
	@Test
	public void testExecute_2()
		throws Exception {
		CompoundStatement fixture = new CompoundStatement(new ArrayList(), new ArrayList());

		fixture.execute();

		// add additional test code here
	}

	/**
	 * Run the void execute() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:41 PM
	 */
	@Test
	public void testExecute_3()
		throws Exception {
		CompoundStatement fixture = new CompoundStatement(new ArrayList(), new ArrayList());

		fixture.execute();

		// add additional test code here
	}

	/**
	 * Run the ArrayList<Statement> getStatements() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:41 PM
	 */
	@Test
	public void testGetStatements_1()
		throws Exception {
		CompoundStatement fixture = new CompoundStatement(new ArrayList(), new ArrayList());

		ArrayList<Statement> result = fixture.getStatements();

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the ArrayList<VariableDeclaration> getVariables() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:41 PM
	 */
	@Test
	public void testGetVariables_1()
		throws Exception {
		CompoundStatement fixture = new CompoundStatement(new ArrayList(), new ArrayList());

		ArrayList<VariableDeclaration> result = fixture.getVariables();

		// add additional test code here
		assertNotNull(result);
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
		CompoundStatement fixture = new CompoundStatement(new ArrayList(), new ArrayList());

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
		new org.junit.runner.JUnitCore().run(CompoundStatementTest.class);
	}
}