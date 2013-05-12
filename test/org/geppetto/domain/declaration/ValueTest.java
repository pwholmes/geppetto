package org.geppetto.domain.declaration;

import org.geppetto.GeppettoException;
import org.geppetto.domain.DataType;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * The class <code>ValueTest</code> contains tests for the class <code>{@link Value}</code>.
 *
 * @generatedBy CodePro at 5/12/13 6:36 AM
 * @author user
 * @version $Revision: 1.0 $
 */
public class ValueTest {
	/**
	 * Run the Value(float) constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:36 AM
	 */
	@Test
	public void testValue_1()
		throws Exception {
		float floatValue = 1.0f;

		Value result = new Value(floatValue);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.UnsupportedClassVersionError: org/geppetto/domain/declaration/Value : Unsupported major.minor version 51.0
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
		assertEquals(result.getFloatValue(), 1.0f , .0001);
	}

	/**
	 * Run the Value(int) constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:36 AM
	 */
	@Test
	public void testValue_2()
		throws Exception {
		int intValue = 1;

		Value result = new Value(intValue);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.UnsupportedClassVersionError: org/geppetto/domain/declaration/Value : Unsupported major.minor version 51.0
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
		assertEquals(result.getIntValue(), 1);
	}

	/**
	 * Run the Value(String) constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:36 AM
	 */
	@Test
	public void testValue_3()
		throws Exception {
		String stringValue = "valueString";

		Value result = new Value(stringValue);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.UnsupportedClassVersionError: org/geppetto/domain/declaration/Value : Unsupported major.minor version 51.0
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
		assertEquals(result.getStringValue(), "valueString");
	}

	/**
	 * Run the Value(DataType) constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:36 AM
	 */
	@Test
	public void testValue_4()
		throws Exception {
		DataType type = DataType.BOOLEAN;

		Value result = new Value(type);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.UnsupportedClassVersionError: org/geppetto/domain/declaration/Value : Unsupported major.minor version 51.0
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
	 * Run the Value(boolean) constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:36 AM
	 */
	@Test
	public void testValue_5()
		throws Exception {
		boolean booleanValue = true;

		Value result = new Value(booleanValue);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.UnsupportedClassVersionError: org/geppetto/domain/declaration/Value : Unsupported major.minor version 51.0
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
	 * Run the int compare(Value,Value) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:36 AM
	 */
	@Test
	public void testCompare_1()
		throws Exception {
		Value value1 = new Value(true);
		Value value2 = new Value(true);

		int result = Value.compare(value1, value2);

		// add additional test code here
		assertEquals(0, result);
	}

	/**
	 * Run the int compare(Value,Value) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:36 AM
	 */
	@Test
	public void testCompare_2()
		throws Exception {
		Value value1 = new Value(true);
		Value value2 = new Value(false);

		int result = Value.compare(value1, value2);

		// add additional test code here
		assertTrue( result > 0);
	}

	/**
	 * Run the int compare(Value,Value) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:36 AM
	 */
	@Test
	public void testCompare_3()
		throws Exception {
		Value value1 = new Value(10);
		Value value2 = new Value(8);

		int result = Value.compare(value1, value2);

		// add additional test code here
		assertTrue( result > 0);
	}

	/**
	 * Run the int compare(Value,Value) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:36 AM
	 */
	@Test
	public void testCompare_4()
		throws Exception {
		Value value1 = new Value(8);
		Value value2 = new Value(9);

		int result = Value.compare(value1, value2);

		// add additional test code here
		assertTrue( result < 0);
	}


	/**
	 * Run the int compareTo(Value) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:36 AM
	 */
	@Test
	public void testCompareTo_1()
		throws Exception {
		Value fixture = new Value(1);
		Value value = new Value(1);

		int result = fixture.compareTo(value);

		// add additional test code here
		assertTrue( result ==0  );
	}

	/**
	 * Run the Value convertTo(DataType) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:36 AM
	 */
	@Test
	public void testConvertTo_1()
		throws Exception {
		Value fixture = new Value(1.0f);
		DataType type = DataType.BOOLEAN;

		Value result = fixture.convertTo(type);

		// add additional test code here
		assertTrue(result.getType().equals(DataType.BOOLEAN));
	}

	/**
	 * Run the Value convertTo(DataType) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:36 AM
	 */
	@Test
	public void testConvertTo_2()
		throws Exception {
		Value fixture = new Value(1.0f);
		DataType type = DataType.STRING;

		Value result = fixture.convertTo(type);

		// add additional test code here
		assertTrue(result.getType().equals(DataType.STRING));
	}

	/**
	 * Run the Value convertTo(DataType) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:36 AM
	 */
	@Test
	public void testConvertTo_3()
		throws Exception {
		Value fixture = new Value(1.0f);

		Value result = fixture.convertTo(DataType.FLOAT);

		// add additional test code here
		assertEquals(result.getFloatValue(), 1.0f, .0001f);
	}





	/**
	 * Run the Value copy() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:36 AM
	 */
	@Test
	public void testCopy_1()
		throws Exception {
		Value fixture = new Value(1.0f);

		Value result = fixture.copy();

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the boolean equals(Value) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:36 AM
	 */
	@Test
	public void testEquals_1()
		throws Exception {
		Value fixture = new Value(1.0f);
		Value value = new Value(1);

		boolean result = fixture.equals(value);

		// add additional test code here
		assertFalse(result);
	}

	/**
	 * Run the boolean equals(Value) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:36 AM
	 */
	@Test
	public void testEquals_2()
		throws Exception {
		Value fixture = new Value(1);
		Value value = new Value(1);

		boolean result = fixture.equals(value);

		// add additional test code here
		assertTrue(result);
	}

	/**
	 * Run the boolean equals(Value,Value) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:36 AM
	 */
	@Test
	public void testEquals_3()
		throws Exception {
		Value value1 = null;
		Value value2 = new Value(1);

		boolean result = Value.equals(value1, value2);

		// add additional test code here
		assertFalse(result);
	}


	/**
	 * Run the boolean getBooleanValue() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:36 AM
	 */
	@Test
	public void testGetBooleanValue_1()
		throws Exception {
		Value fixture = new Value(1.0f);

		boolean result = fixture.getBooleanValue();

		// add additional test code here
		assertTrue(result);
	}

	/**
	 * Run the boolean getBooleanValue() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:36 AM
	 */
	@Test
	public void testGetBooleanValue_2()
		throws Exception {
		Value fixture = new Value(1.0f);

		boolean result = fixture.getBooleanValue();

		// add additional test code here
		assertTrue(result);
	}

	/**
	 * Run the boolean getBooleanValue() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:36 AM
	 */
	@Test
	public void testGetBooleanValue_3()
		throws Exception {
		Value fixture = new Value(1.0f);

		boolean result = fixture.getBooleanValue();

		// add additional test code here
		assertTrue(result);
	}


	/**
	 * Run the float getFloatValue() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:36 AM
	 */
	@Test
	public void testGetFloatValue_1()
		throws Exception {
		Value fixture = new Value(1.0f);

		float result = fixture.getFloatValue();

		// add additional test code here
		assertFalse(0.0f == result);
	}

	/**
	 * Run the float getFloatValue() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:36 AM
	 */
	@Test
	public void testGetFloatValue_2()
		throws Exception {
		Value fixture = new Value(1.0f);

		float result = fixture.getFloatValue();

		// add additional test code here
		assertEquals(1.0f, result, 0.1f);
	}

	/**
	 * Run the float getFloatValue() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:36 AM
	 */
	@Test
	public void testGetFloatValue_3()
		throws Exception {
		Value fixture = new Value(1);

		float result = fixture.getFloatValue();

		// add additional test code here
		assertEquals(1.0f, result, 0.1f);
	}


	/**
	 * Run the int getIntValue() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:36 AM
	 */
	@Test
	public void testGetIntValue_1()
		throws Exception {
		Value fixture = new Value(1);

		int result = fixture.getIntValue();

		// add additional test code here
		assertEquals(1, result);
	}

	/**
	 * Run the int getIntValue() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:36 AM
	 */
	@Test
	public void testGetIntValue_2()
		throws Exception {
		Value fixture = new Value(1.0f);

		int result = fixture.getIntValue();

		// add additional test code here
		assertEquals(1, result);
	}

	/**
	 * Run the int getIntValue() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:36 AM
	 */
	@Test
	public void testGetIntValue_3()
		throws Exception {
		Value fixture = new Value(5.0f);

		int result = fixture.getIntValue();

		// add additional test code here
		assertEquals(5, result);
	}


	/**
	 * Run the String getStringValue() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:36 AM
	 */
	@Test
	public void testGetStringValue_1()
		throws Exception {
		Value fixture = new Value(1.0f);

		String result = fixture.getStringValue();

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the String getStringValue() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:36 AM
	 */
	@Test
	public void testGetStringValue_2()
		throws Exception {
		Value fixture = new Value(1.0f);

		String result = fixture.getStringValue();

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the String getStringValue() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:36 AM
	 */
	@Test
	public void testGetStringValue_3()
		throws Exception {
		Value fixture = new Value(1.0f);

		String result = fixture.getStringValue();

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the String getStringValue() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:36 AM
	 */
	@Test
	public void testGetStringValue_4()
		throws Exception {
		Value fixture = new Value(1.0f);

		String result = fixture.getStringValue();

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the DataType getType() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:36 AM
	 */
	@Test
	public void testGetType_1()
		throws Exception {
		Value fixture = new Value(1.0f);

		DataType result = fixture.getType();

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the String toString() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:36 AM
	 */
	@Test
	public void testToString_1()
		throws Exception {
		Value fixture = new Value(1.0f);

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
	 * @generatedBy CodePro at 5/12/13 6:36 AM
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
	 * @generatedBy CodePro at 5/12/13 6:36 AM
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
	 * @generatedBy CodePro at 5/12/13 6:36 AM
	 */
	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(ValueTest.class);
	}
}