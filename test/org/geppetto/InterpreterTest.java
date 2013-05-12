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

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.UnsupportedClassVersionError: org/geppetto/Interpreter : Unsupported major.minor version 51.0
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
		assertNotNull(result);
	}

	/**
	 * Run the void execute(GeppettoProgram) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:11 AM
	 */
	@Test
	public void testExecute_1()
		throws Exception {
		Interpreter fixture = new Interpreter(true);
		GeppettoProgram program = new GeppettoProgram();
		program.setEndRequested(true);

		fixture.execute(program);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.UnsupportedClassVersionError: org/geppetto/GeppettoProgram : Unsupported major.minor version 51.0
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
		//       at com.instantiations.eclipse.analysis.expression.model.VariableAccessExpression.assign(VariableAccessExpression.java:295)
		//       at com.instantiations.eclipse.analysis.expression.model.AssignmentExpression.execute(AssignmentExpression.java:200)
		//       at com.instantiations.eclipse.analysis.expression.model.ExpressionSequence.execute(ExpressionSequence.java:316)
		//       at com.instantiations.assist.eclipse.junit.execution.core.ExecutionRequest.execute(ExecutionRequest.java:286)
		//       at com.instantiations.assist.eclipse.junit.execution.communication.LocalExecutionClient$1.run(LocalExecutionClient.java:158)
		//       at java.lang.Thread.run(Thread.java:679)
	}

	/**
	 * Run the void execute(GeppettoProgram) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:11 AM
	 */
	@Test
	public void testExecute_2()
		throws Exception {
		Interpreter fixture = new Interpreter(true);
		GeppettoProgram program = new GeppettoProgram();
		program.setEndRequested(true);

		fixture.execute(program);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.UnsupportedClassVersionError: org/geppetto/GeppettoProgram : Unsupported major.minor version 51.0
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
		//       at com.instantiations.eclipse.analysis.expression.model.VariableAccessExpression.assign(VariableAccessExpression.java:295)
		//       at com.instantiations.eclipse.analysis.expression.model.AssignmentExpression.execute(AssignmentExpression.java:200)
		//       at com.instantiations.eclipse.analysis.expression.model.ExpressionSequence.execute(ExpressionSequence.java:316)
		//       at com.instantiations.assist.eclipse.junit.execution.core.ExecutionRequest.execute(ExecutionRequest.java:286)
		//       at com.instantiations.assist.eclipse.junit.execution.communication.LocalExecutionClient$1.run(LocalExecutionClient.java:158)
		//       at java.lang.Thread.run(Thread.java:679)
	}

	/**
	 * Run the void execute(GeppettoProgram) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:11 AM
	 */
	@Test
	public void testExecute_3()
		throws Exception {
		Interpreter fixture = new Interpreter(false);
		GeppettoProgram program = new GeppettoProgram();
		program.setEndRequested(true);

		fixture.execute(program);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.UnsupportedClassVersionError: org/geppetto/GeppettoProgram : Unsupported major.minor version 51.0
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
		//       at com.instantiations.eclipse.analysis.expression.model.VariableAccessExpression.assign(VariableAccessExpression.java:295)
		//       at com.instantiations.eclipse.analysis.expression.model.AssignmentExpression.execute(AssignmentExpression.java:200)
		//       at com.instantiations.eclipse.analysis.expression.model.ExpressionSequence.execute(ExpressionSequence.java:316)
		//       at com.instantiations.assist.eclipse.junit.execution.core.ExecutionRequest.execute(ExecutionRequest.java:286)
		//       at com.instantiations.assist.eclipse.junit.execution.communication.LocalExecutionClient$1.run(LocalExecutionClient.java:158)
		//       at java.lang.Thread.run(Thread.java:679)
	}

	/**
	 * Run the void execute(GeppettoProgram) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:11 AM
	 */
	@Test
	public void testExecute_4()
		throws Exception {
		Interpreter fixture = new Interpreter(true);
		GeppettoProgram program = new GeppettoProgram();

		fixture.execute(program);

		// add additional test code here
	}

	/**
	 * Run the void execute(GeppettoProgram) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:11 AM
	 */
	@Test
	public void testExecute_5()
		throws Exception {
		Interpreter fixture = new Interpreter(true);
		GeppettoProgram program = new GeppettoProgram();
		program.setEndRequested(true);

		fixture.execute(program);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.UnsupportedClassVersionError: org/geppetto/GeppettoProgram : Unsupported major.minor version 51.0
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
		//       at com.instantiations.eclipse.analysis.expression.model.VariableAccessExpression.assign(VariableAccessExpression.java:295)
		//       at com.instantiations.eclipse.analysis.expression.model.AssignmentExpression.execute(AssignmentExpression.java:200)
		//       at com.instantiations.eclipse.analysis.expression.model.ExpressionSequence.execute(ExpressionSequence.java:316)
		//       at com.instantiations.assist.eclipse.junit.execution.core.ExecutionRequest.execute(ExecutionRequest.java:286)
		//       at com.instantiations.assist.eclipse.junit.execution.communication.LocalExecutionClient$1.run(LocalExecutionClient.java:158)
		//       at java.lang.Thread.run(Thread.java:679)
	}

	/**
	 * Run the void execute(GeppettoProgram) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:11 AM
	 */
	@Test
	public void testExecute_6()
		throws Exception {
		Interpreter fixture = new Interpreter(false);
		GeppettoProgram program = new GeppettoProgram();
		program.setEndRequested(true);

		fixture.execute(program);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.UnsupportedClassVersionError: org/geppetto/GeppettoProgram : Unsupported major.minor version 51.0
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
		//       at com.instantiations.eclipse.analysis.expression.model.VariableAccessExpression.assign(VariableAccessExpression.java:295)
		//       at com.instantiations.eclipse.analysis.expression.model.AssignmentExpression.execute(AssignmentExpression.java:200)
		//       at com.instantiations.eclipse.analysis.expression.model.ExpressionSequence.execute(ExpressionSequence.java:316)
		//       at com.instantiations.assist.eclipse.junit.execution.core.ExecutionRequest.execute(ExecutionRequest.java:286)
		//       at com.instantiations.assist.eclipse.junit.execution.communication.LocalExecutionClient$1.run(LocalExecutionClient.java:158)
		//       at java.lang.Thread.run(Thread.java:679)
	}

	/**
	 * Run the void execute(GeppettoProgram) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:11 AM
	 */
	@Test
	public void testExecute_7()
		throws Exception {
		Interpreter fixture = new Interpreter(true);
		GeppettoProgram program = new GeppettoProgram();
		program.setEndRequested(true);

		fixture.execute(program);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.UnsupportedClassVersionError: org/geppetto/GeppettoProgram : Unsupported major.minor version 51.0
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
		//       at com.instantiations.eclipse.analysis.expression.model.VariableAccessExpression.assign(VariableAccessExpression.java:295)
		//       at com.instantiations.eclipse.analysis.expression.model.AssignmentExpression.execute(AssignmentExpression.java:200)
		//       at com.instantiations.eclipse.analysis.expression.model.ExpressionSequence.execute(ExpressionSequence.java:316)
		//       at com.instantiations.assist.eclipse.junit.execution.core.ExecutionRequest.execute(ExecutionRequest.java:286)
		//       at com.instantiations.assist.eclipse.junit.execution.communication.LocalExecutionClient$1.run(LocalExecutionClient.java:158)
		//       at java.lang.Thread.run(Thread.java:679)
	}

	/**
	 * Run the void execute(GeppettoProgram) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:11 AM
	 */
	@Test
	public void testExecute_8()
		throws Exception {
		Interpreter fixture = new Interpreter(false);
		GeppettoProgram program = new GeppettoProgram();
		program.setEndRequested(true);

		fixture.execute(program);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.UnsupportedClassVersionError: org/geppetto/GeppettoProgram : Unsupported major.minor version 51.0
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
		//       at com.instantiations.eclipse.analysis.expression.model.VariableAccessExpression.assign(VariableAccessExpression.java:295)
		//       at com.instantiations.eclipse.analysis.expression.model.AssignmentExpression.execute(AssignmentExpression.java:200)
		//       at com.instantiations.eclipse.analysis.expression.model.ExpressionSequence.execute(ExpressionSequence.java:316)
		//       at com.instantiations.assist.eclipse.junit.execution.core.ExecutionRequest.execute(ExecutionRequest.java:286)
		//       at com.instantiations.assist.eclipse.junit.execution.communication.LocalExecutionClient$1.run(LocalExecutionClient.java:158)
		//       at java.lang.Thread.run(Thread.java:679)
	}

	/**
	 * Run the void execute(GeppettoProgram) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:11 AM
	 */
	@Test
	public void testExecute_9()
		throws Exception {
		Interpreter fixture = new Interpreter(true);
		GeppettoProgram program = new GeppettoProgram();
		program.setEndRequested(true);

		fixture.execute(program);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.UnsupportedClassVersionError: org/geppetto/GeppettoProgram : Unsupported major.minor version 51.0
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
		//       at com.instantiations.eclipse.analysis.expression.model.VariableAccessExpression.assign(VariableAccessExpression.java:295)
		//       at com.instantiations.eclipse.analysis.expression.model.AssignmentExpression.execute(AssignmentExpression.java:200)
		//       at com.instantiations.eclipse.analysis.expression.model.ExpressionSequence.execute(ExpressionSequence.java:316)
		//       at com.instantiations.assist.eclipse.junit.execution.core.ExecutionRequest.execute(ExecutionRequest.java:286)
		//       at com.instantiations.assist.eclipse.junit.execution.communication.LocalExecutionClient$1.run(LocalExecutionClient.java:158)
		//       at java.lang.Thread.run(Thread.java:679)
	}

	/**
	 * Run the void execute(GeppettoProgram) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:11 AM
	 */
	@Test
	public void testExecute_10()
		throws Exception {
		Interpreter fixture = new Interpreter(false);
		GeppettoProgram program = new GeppettoProgram();
		program.setEndRequested(true);

		fixture.execute(program);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.UnsupportedClassVersionError: org/geppetto/GeppettoProgram : Unsupported major.minor version 51.0
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
		//       at com.instantiations.eclipse.analysis.expression.model.VariableAccessExpression.assign(VariableAccessExpression.java:295)
		//       at com.instantiations.eclipse.analysis.expression.model.AssignmentExpression.execute(AssignmentExpression.java:200)
		//       at com.instantiations.eclipse.analysis.expression.model.ExpressionSequence.execute(ExpressionSequence.java:316)
		//       at com.instantiations.assist.eclipse.junit.execution.core.ExecutionRequest.execute(ExecutionRequest.java:286)
		//       at com.instantiations.assist.eclipse.junit.execution.communication.LocalExecutionClient$1.run(LocalExecutionClient.java:158)
		//       at java.lang.Thread.run(Thread.java:679)
	}

	/**
	 * Run the void execute(GeppettoProgram) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:11 AM
	 */
	@Test
	public void testExecute_11()
		throws Exception {
		Interpreter fixture = new Interpreter(true);
		GeppettoProgram program = new GeppettoProgram();
		program.setEndRequested(false);

		fixture.execute(program);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.UnsupportedClassVersionError: org/geppetto/GeppettoProgram : Unsupported major.minor version 51.0
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
		//       at com.instantiations.eclipse.analysis.expression.model.VariableAccessExpression.assign(VariableAccessExpression.java:295)
		//       at com.instantiations.eclipse.analysis.expression.model.AssignmentExpression.execute(AssignmentExpression.java:200)
		//       at com.instantiations.eclipse.analysis.expression.model.ExpressionSequence.execute(ExpressionSequence.java:316)
		//       at com.instantiations.assist.eclipse.junit.execution.core.ExecutionRequest.execute(ExecutionRequest.java:286)
		//       at com.instantiations.assist.eclipse.junit.execution.communication.LocalExecutionClient$1.run(LocalExecutionClient.java:158)
		//       at java.lang.Thread.run(Thread.java:679)
	}

	/**
	 * Run the void execute(GeppettoProgram) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:11 AM
	 */
	@Test
	public void testExecute_12()
		throws Exception {
		Interpreter fixture = new Interpreter(false);
		GeppettoProgram program = new GeppettoProgram();
		program.setEndRequested(false);

		fixture.execute(program);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.UnsupportedClassVersionError: org/geppetto/GeppettoProgram : Unsupported major.minor version 51.0
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
		//       at com.instantiations.eclipse.analysis.expression.model.VariableAccessExpression.assign(VariableAccessExpression.java:295)
		//       at com.instantiations.eclipse.analysis.expression.model.AssignmentExpression.execute(AssignmentExpression.java:200)
		//       at com.instantiations.eclipse.analysis.expression.model.ExpressionSequence.execute(ExpressionSequence.java:316)
		//       at com.instantiations.assist.eclipse.junit.execution.core.ExecutionRequest.execute(ExecutionRequest.java:286)
		//       at com.instantiations.assist.eclipse.junit.execution.communication.LocalExecutionClient$1.run(LocalExecutionClient.java:158)
		//       at java.lang.Thread.run(Thread.java:679)
	}

	/**
	 * Run the void execute(GeppettoProgram) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:11 AM
	 */
	@Test
	public void testExecute_13()
		throws Exception {
		Interpreter fixture = new Interpreter(true);
		GeppettoProgram program = new GeppettoProgram();
		program.setEndRequested(true);

		fixture.execute(program);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.UnsupportedClassVersionError: org/geppetto/GeppettoProgram : Unsupported major.minor version 51.0
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
		//       at com.instantiations.eclipse.analysis.expression.model.VariableAccessExpression.assign(VariableAccessExpression.java:295)
		//       at com.instantiations.eclipse.analysis.expression.model.AssignmentExpression.execute(AssignmentExpression.java:200)
		//       at com.instantiations.eclipse.analysis.expression.model.ExpressionSequence.execute(ExpressionSequence.java:316)
		//       at com.instantiations.assist.eclipse.junit.execution.core.ExecutionRequest.execute(ExecutionRequest.java:286)
		//       at com.instantiations.assist.eclipse.junit.execution.communication.LocalExecutionClient$1.run(LocalExecutionClient.java:158)
		//       at java.lang.Thread.run(Thread.java:679)
	}

	/**
	 * Run the void execute(GeppettoProgram) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:11 AM
	 */
	@Test
	public void testExecute_14()
		throws Exception {
		Interpreter fixture = new Interpreter(false);
		GeppettoProgram program = new GeppettoProgram();
		program.setEndRequested(true);

		fixture.execute(program);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.UnsupportedClassVersionError: org/geppetto/GeppettoProgram : Unsupported major.minor version 51.0
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
		//       at com.instantiations.eclipse.analysis.expression.model.VariableAccessExpression.assign(VariableAccessExpression.java:295)
		//       at com.instantiations.eclipse.analysis.expression.model.AssignmentExpression.execute(AssignmentExpression.java:200)
		//       at com.instantiations.eclipse.analysis.expression.model.ExpressionSequence.execute(ExpressionSequence.java:316)
		//       at com.instantiations.assist.eclipse.junit.execution.core.ExecutionRequest.execute(ExecutionRequest.java:286)
		//       at com.instantiations.assist.eclipse.junit.execution.communication.LocalExecutionClient$1.run(LocalExecutionClient.java:158)
		//       at java.lang.Thread.run(Thread.java:679)
	}

	/**
	 * Run the void execute(GeppettoProgram) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:11 AM
	 */
	@Test
	public void testExecute_15()
		throws Exception {
		Interpreter fixture = new Interpreter(true);
		GeppettoProgram program = new GeppettoProgram();
		program.setEndRequested(true);

		fixture.execute(program);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.UnsupportedClassVersionError: org/geppetto/GeppettoProgram : Unsupported major.minor version 51.0
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
		//       at com.instantiations.eclipse.analysis.expression.model.VariableAccessExpression.assign(VariableAccessExpression.java:295)
		//       at com.instantiations.eclipse.analysis.expression.model.AssignmentExpression.execute(AssignmentExpression.java:200)
		//       at com.instantiations.eclipse.analysis.expression.model.ExpressionSequence.execute(ExpressionSequence.java:316)
		//       at com.instantiations.assist.eclipse.junit.execution.core.ExecutionRequest.execute(ExecutionRequest.java:286)
		//       at com.instantiations.assist.eclipse.junit.execution.communication.LocalExecutionClient$1.run(LocalExecutionClient.java:158)
		//       at java.lang.Thread.run(Thread.java:679)
	}

	/**
	 * Run the void execute(GeppettoProgram) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:11 AM
	 */
	@Test
	public void testExecute_16()
		throws Exception {
		Interpreter fixture = new Interpreter(false);
		GeppettoProgram program = new GeppettoProgram();
		program.setEndRequested(true);

		fixture.execute(program);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.UnsupportedClassVersionError: org/geppetto/GeppettoProgram : Unsupported major.minor version 51.0
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
		//       at com.instantiations.eclipse.analysis.expression.model.VariableAccessExpression.assign(VariableAccessExpression.java:295)
		//       at com.instantiations.eclipse.analysis.expression.model.AssignmentExpression.execute(AssignmentExpression.java:200)
		//       at com.instantiations.eclipse.analysis.expression.model.ExpressionSequence.execute(ExpressionSequence.java:316)
		//       at com.instantiations.assist.eclipse.junit.execution.core.ExecutionRequest.execute(ExecutionRequest.java:286)
		//       at com.instantiations.assist.eclipse.junit.execution.communication.LocalExecutionClient$1.run(LocalExecutionClient.java:158)
		//       at java.lang.Thread.run(Thread.java:679)
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