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
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.UnsupportedClassVersionError: org/geppetto/domain/declaration/ArgumentDeclaration : Unsupported major.minor version 51.0
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
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.UnsupportedClassVersionError: org/geppetto/domain/declaration/ArgumentDeclaration : Unsupported major.minor version 51.0
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