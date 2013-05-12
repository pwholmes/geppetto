package org.geppetto.domain.expression;

import java.util.ArrayList;
import org.geppetto.GeppettoException;
import org.geppetto.GeppettoProgram;
import org.geppetto.ProgramContext;
import org.geppetto.domain.declaration.FunctionDefinition;
import org.geppetto.domain.declaration.Value;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * The class <code>FunctionExpressionTest</code> contains tests for the class <code>{@link FunctionExpression}</code>.
 *
 * @generatedBy CodePro at 5/12/13 10:21 AM
 * @author user
 * @version $Revision: 1.0 $
 */
public class FunctionExpressionTest {
	
	
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
	
	ArrayList<Expression> expressions;
	
	

	/**
	 * Run the FunctionExpression(String,ArrayList<Expression>) constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 10:21 AM
	 */
	@Test
	public void testFunctionExpression()
		throws Exception {
		String name = "functionExpressionName";
		ArrayList<Expression> argumentList = expressions;

		FunctionExpression result = new FunctionExpression(name, argumentList);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.UnsupportedClassVersionError: org/geppetto/domain/expression/FunctionExpression : Unsupported major.minor version 51.0
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
		assertEquals(result.getArguments().get(3), boolCEF);
	}

	/**
	 * Run the ArrayList<Expression> getArguments() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 10:21 AM
	 */
	@Test
	public void testGetArguments_1()
		throws Exception {
		FunctionExpression fixture = new FunctionExpression("", expressions);

		ArrayList<Expression> result = fixture.getArguments();

		// add additional test code here
		assertEquals(result, expressions);
	}

	/**
	 * Run the String getName() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 10:21 AM
	 */
	@Test
	public void testGetName_1()
		throws Exception {
		FunctionExpression fixture = new FunctionExpression("someName", new ArrayList());

		String result = fixture.getName();

		// add additional test code here
		assertEquals(result, "someName");
	}

	/**
	 * Run the Value getValue() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 10:21 AM
	 */
	@Test
	public void testGetValue_1()
		throws Exception {
		
		FunctionDefinition functionDef = GeppettoProgram.getInstance().getFunctionDefinition("name");
	      
	    // Add a new context to the stack, call the function, then pop the context off the stack.
	    // (We still have a reference to the context, so it won't be garbage collected until this function exits.)
	    ProgramContext context = new ProgramContext(functionDef, expressions);
	    GeppettoProgram.getInstance().getContexts().add(context);
	    functionDef.getCompoundStatement().execute();
	    GeppettoProgram.getInstance().getContexts().removeLast();
		Value value = context.getReturnValue();
	      
		FunctionExpression fixture = new FunctionExpression("someName", expressions);

		Value result = fixture.getValue();

		// add additional test code here
		assertEquals(result, value);
	}

	
	/**
	 * Run the boolean isLValue() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 10:21 AM
	 */
	@Test
	public void testIsLValue_1()
		throws Exception {
		FunctionExpression fixture = new FunctionExpression("", new ArrayList());

		boolean result = fixture.isLValue();

		// add additional test code here
		assertFalse(result);
	}


	/**
	 * Run the String toString() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 10:21 AM
	 */
	@Test
	public void testToString_1()
		throws Exception {
		FunctionExpression fixture = new FunctionExpression("functionExpressionName", expressions);

		String result = fixture.toString();

		String idealResult = "{FunctionExpression: name: functionExpressionName; arguments: " +
				"[{ConstantExpression: name: null; value: {Value: type: INT; intValue: 1; " +
				"floatValue: 0.0; stringValue: null; booleanValue: false}}, {ConstantExpression: " +
				"name: null; value: {Value: type: INT; intValue: 2; floatValue: 0.0; stringValue: " +
				"null; booleanValue: false}}, {ConstantExpression: name: null; value: {Value: " +
				"type: BOOLEAN; intValue: 0; floatValue: 0.0; stringValue: null; booleanValue: " +
				"true}}, {ConstantExpression: name: null; value: {Value: type: BOOLEAN; intValue: " +
				"0; floatValue: 0.0; stringValue: null; booleanValue: false}}, " +
				"{ConstantExpression: name: null; value: {Value: type: FLOAT; intValue: 0; " +
				"floatValue: 1.0; stringValue: null; booleanValue: false}}, {ConstantExpression: " +
				"name: null; value: {Value: type: FLOAT; intValue: 0; floatValue: 1.0; stringValue: " +
				"null; booleanValue: false}}]}"		;

		// add additional test code here
		assertEquals(result, idealResult);
	}

	/**
	 * Perform pre-test initialization.
	 *
	 * @throws Exception
	 *         if the initialization fails for some reason
	 *
	 * @generatedBy CodePro at 5/12/13 10:21 AM
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
		
		expressions = new ArrayList<Expression>();
		expressions.add(intCE1);
		expressions.add(intCE2);
		expressions.add(boolCET);
		expressions.add(boolCEF);
		expressions.add(floatCE1);
		expressions.add(floatCE1);

	}

	/**
	 * Perform post-test clean-up.
	 *
	 * @throws Exception
	 *         if the clean-up fails for some reason
	 *
	 * @generatedBy CodePro at 5/12/13 10:21 AM
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
	 * @generatedBy CodePro at 5/12/13 10:21 AM
	 */
	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(FunctionExpressionTest.class);
	}
}