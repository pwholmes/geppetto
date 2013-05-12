package org.geppetto.parser.generated;

import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import org.geppetto.GeppettoProgram;
import org.geppetto.domain.declaration.PropertyDefinition;
import org.geppetto.domain.declaration.VariableDeclaration;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * The class <code>ParserTest</code> contains tests for the class <code>{@link Parser}</code>.
 *
 * @generatedBy CodePro at 5/12/13 6:41 PM
 * @author user
 * @version $Revision: 1.0 $
 */
public class ParserTest {
	/**
	 * Run the Parser() constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:41 PM
	 */
	@Test
	public void testParser_1()
		throws Exception {

		Parser result = new Parser();

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the Parser(boolean) constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:41 PM
	 */
	@Test
	public void testParser_2()
		throws Exception {
		boolean debugMe = true;

		Parser result = new Parser(debugMe);

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the void debug(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:41 PM
	 */
	@Test
	public void testDebug_1()
		throws Exception {
		Parser fixture = new Parser(true);
		fixture.yyerror("");
		fixture.stateptrmax = 1;
		fixture.statestk = new int[] {};
		fixture.yyn = 1;
		ParserVal parserVal = new ParserVal(1.0);
		parserVal.sval = "";
		parserVal.obj = new Object();
		parserVal.ival = 1;
		fixture.yylval = parserVal;
		fixture.geppettoProgram = new GeppettoProgram();
		fixture.yychar = 1;
		fixture.statemax = 1;
		fixture.valstk = new ParserVal[] {};
		fixture.symbolTable = new ArrayList();
		fixture.yytext = "";
		fixture.yys = "";
		fixture.yym = 1;
		fixture.yystate = 1;
		fixture.yyval = new ParserVal();
		fixture.yynerrs = 1;
		fixture.valptr = 1;
		fixture.variables = new ArrayList();
		fixture.stateptr = 1;
		fixture.propertyDefinitions = new ArrayList();
		fixture.yyerrflag = 1;
		String msg = "";

		fixture.debug(msg);

		// add additional test code here
	}

	/**
	 * Run the void debug(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:41 PM
	 */
	@Test
	public void testDebug_2()
		throws Exception {
		Parser fixture = new Parser(false);
		fixture.yyerror("");
		fixture.stateptrmax = 1;
		fixture.statestk = new int[] {};
		fixture.yyn = 1;
		ParserVal parserVal = new ParserVal(1.0);
		parserVal.sval = "";
		parserVal.obj = new Object();
		parserVal.ival = 1;
		fixture.yylval = parserVal;
		fixture.geppettoProgram = new GeppettoProgram();
		fixture.yychar = 1;
		fixture.statemax = 1;
		fixture.valstk = new ParserVal[] {};
		fixture.symbolTable = new ArrayList();
		fixture.yytext = "";
		fixture.yys = "";
		fixture.yym = 1;
		fixture.yystate = 1;
		fixture.yyval = new ParserVal();
		fixture.yynerrs = 1;
		fixture.valptr = 1;
		fixture.variables = new ArrayList();
		fixture.stateptr = 1;
		fixture.propertyDefinitions = new ArrayList();
		fixture.yyerrflag = 1;
		String msg = "";

		fixture.debug(msg);

		// add additional test code here
	}

	/**
	 * Run the void dump_stacks(int) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:41 PM
	 */
	@Test
	public void testDump_stacks_1()
		throws Exception {
		Parser fixture = new Parser(true);
		fixture.yyerror("");
		fixture.stateptrmax = 1;
		fixture.statestk = new int[] {};
		fixture.yyn = 1;
		fixture.yylval = new ParserVal();
		fixture.geppettoProgram = new GeppettoProgram();
		fixture.yychar = 1;
		fixture.statemax = 1;
		fixture.valstk = new ParserVal[] {};
		fixture.symbolTable = new ArrayList();
		fixture.yytext = "";
		fixture.yys = "";
		fixture.yym = 1;
		fixture.yystate = 1;
		fixture.yyval = new ParserVal();
		fixture.yynerrs = 1;
		fixture.valptr = 1;
		fixture.variables = new ArrayList();
		fixture.stateptr = 1;
		fixture.propertyDefinitions = new ArrayList();
		fixture.yyerrflag = 1;
		int count = 1;

		fixture.dump_stacks(count);

		// add additional test code here
	}

	/**
	 * Run the void dump_stacks(int) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:41 PM
	 */
	@Test
	public void testDump_stacks_2()
		throws Exception {
		Parser fixture = new Parser(true);
		fixture.yyerror("");
		fixture.stateptrmax = 1;
		fixture.statestk = new int[] {};
		fixture.yyn = 1;
		ParserVal parserVal = new ParserVal(1.0);
		parserVal.sval = "";
		parserVal.obj = new Object();
		parserVal.ival = 1;
		fixture.yylval = parserVal;
		fixture.geppettoProgram = new GeppettoProgram();
		fixture.yychar = 1;
		fixture.statemax = 1;
		fixture.valstk = new ParserVal[] {};
		fixture.symbolTable = new ArrayList();
		fixture.yytext = "";
		fixture.yys = "";
		fixture.yym = 1;
		fixture.yystate = 1;
		fixture.yyval = new ParserVal();
		fixture.yynerrs = 1;
		fixture.valptr = 1;
		fixture.variables = new ArrayList();
		fixture.stateptr = 1;
		fixture.propertyDefinitions = new ArrayList();
		fixture.yyerrflag = 1;
		int count = 0;

		fixture.dump_stacks(count);

		// add additional test code here
	}

	/**
	 * Run the ParserVal dup_yyval(ParserVal) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:41 PM
	 */
	@Test
	public void testDup_yyval_1()
		throws Exception {
		Parser fixture = new Parser(true);
		fixture.yyerror("");
		fixture.stateptrmax = 1;
		fixture.statestk = new int[] {};
		fixture.yyn = 1;
		ParserVal parserVal = new ParserVal(1.0);
		parserVal.sval = "";
		parserVal.obj = new Object();
		parserVal.ival = 1;
		fixture.yylval = parserVal;
		fixture.geppettoProgram = new GeppettoProgram();
		fixture.yychar = 1;
		fixture.statemax = 1;
		fixture.valstk = new ParserVal[] {};
		fixture.symbolTable = new ArrayList();
		fixture.yytext = "";
		fixture.yys = "";
		fixture.yym = 1;
		fixture.yystate = 1;
		fixture.yyval = new ParserVal();
		fixture.yynerrs = 1;
		fixture.valptr = 1;
		fixture.variables = new ArrayList();
		fixture.stateptr = 1;
		fixture.propertyDefinitions = new ArrayList();
		fixture.yyerrflag = 1;
		ParserVal val = new ParserVal(1.0);
		val.sval = "";
		val.obj = new Object();
		val.ival = 1;

		ParserVal result = fixture.dup_yyval(val);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.UnsupportedClassVersionError: org/geppetto/parser/generated/ParserVal : Unsupported major.minor version 51.0
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
		assertNotNull(result);
	}

	/**
	 * Run the boolean init_stacks() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:41 PM
	 */
	@Test
	public void testInit_stacks_1()
		throws Exception {
		Parser fixture = new Parser(true);
		fixture.yyerror("");
		fixture.stateptrmax = 1;
		fixture.statestk = new int[] {};
		fixture.yyn = 1;
		ParserVal parserVal = new ParserVal(1.0);
		parserVal.sval = "";
		parserVal.obj = new Object();
		parserVal.ival = 1;
		fixture.yylval = parserVal;
		fixture.geppettoProgram = new GeppettoProgram();
		fixture.yychar = 1;
		fixture.statemax = 1;
		fixture.valstk = new ParserVal[] {};
		fixture.symbolTable = new ArrayList();
		fixture.yytext = "";
		fixture.yys = "";
		fixture.yym = 1;
		fixture.yystate = 1;
		fixture.yyval = new ParserVal();
		fixture.yynerrs = 1;
		fixture.valptr = 1;
		fixture.variables = new ArrayList();
		fixture.stateptr = 1;
		fixture.propertyDefinitions = new ArrayList();
		fixture.yyerrflag = 1;

		boolean result = fixture.init_stacks();

		// add additional test code here
		assertTrue(result);
	}

	/**
	 * Run the GeppettoProgram parse(Reader) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:41 PM
	 */
	@Test
	public void testParse_1()
		throws Exception {
		Parser fixture = new Parser(true);
		fixture.yyerror("");
		fixture.stateptrmax = 1;
		fixture.statestk = new int[] {};
		fixture.yyn = 1;
		ParserVal parserVal = new ParserVal(1.0);
		parserVal.sval = "";
		parserVal.obj = new Object();
		parserVal.ival = 1;
		fixture.yylval = parserVal;
		fixture.geppettoProgram = new GeppettoProgram();
		fixture.yychar = 1;
		fixture.statemax = 1;
		fixture.valstk = new ParserVal[] {};
		fixture.symbolTable = new ArrayList();
		fixture.yytext = "";
		fixture.yys = "";
		fixture.yym = 1;
		fixture.yystate = 1;
		fixture.yyval = new ParserVal();
		fixture.yynerrs = 1;
		fixture.valptr = 1;
		fixture.variables = new ArrayList();
		fixture.stateptr = 1;
		fixture.propertyDefinitions = new ArrayList();
		fixture.yyerrflag = 1;
		Reader inputReader = new StringReader("");

		GeppettoProgram result = fixture.parse(inputReader);

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the void run() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:41 PM
	 */
	@Test
	public void testRun_1()
		throws Exception {
		Parser fixture = new Parser(true);
		fixture.yyerror("");
		fixture.stateptrmax = 1;
		fixture.statestk = new int[] {};
		fixture.yyn = 1;
		ParserVal parserVal = new ParserVal(1.0);
		parserVal.sval = "";
		parserVal.obj = new Object();
		parserVal.ival = 1;
		fixture.yylval = parserVal;
		fixture.geppettoProgram = new GeppettoProgram();
		fixture.yychar = 1;
		fixture.statemax = 1;
		fixture.valstk = new ParserVal[] {};
		fixture.symbolTable = new ArrayList();
		fixture.yytext = "";
		fixture.yys = "";
		fixture.yym = 1;
		fixture.yystate = 1;
		fixture.yyval = new ParserVal();
		fixture.yynerrs = 1;
		fixture.valptr = 1;
		fixture.variables = new ArrayList();
		fixture.stateptr = 1;
		fixture.propertyDefinitions = new ArrayList();
		fixture.yyerrflag = 1;

		fixture.run();

		// add additional test code here
	}

	/**
	 * Run the void state_drop(int) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:41 PM
	 */
	@Test
	public void testState_drop_1()
		throws Exception {
		Parser fixture = new Parser(true);
		fixture.yyerror("");
		fixture.stateptrmax = 1;
		fixture.statestk = new int[] {};
		fixture.yyn = 1;
		ParserVal parserVal = new ParserVal(1.0);
		parserVal.sval = "";
		parserVal.obj = new Object();
		parserVal.ival = 1;
		fixture.yylval = parserVal;
		fixture.geppettoProgram = new GeppettoProgram();
		fixture.yychar = 1;
		fixture.statemax = 1;
		fixture.valstk = new ParserVal[] {};
		fixture.symbolTable = new ArrayList();
		fixture.yytext = "";
		fixture.yys = "";
		fixture.yym = 1;
		fixture.yystate = 1;
		fixture.yyval = new ParserVal();
		fixture.yynerrs = 1;
		fixture.valptr = 1;
		fixture.variables = new ArrayList();
		fixture.stateptr = 1;
		fixture.propertyDefinitions = new ArrayList();
		fixture.yyerrflag = 1;
		int cnt = 1;

		fixture.state_drop(cnt);

		// add additional test code here
	}

	/**
	 * Run the int state_peek(int) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:41 PM
	 */
	@Test
	public void testState_peek_1()
		throws Exception {
		Parser fixture = new Parser(true);
		fixture.yyerror("");
		fixture.stateptrmax = 1;
		fixture.statestk = new int[] {};
		fixture.yyn = 1;
		ParserVal parserVal = new ParserVal(1.0);
		parserVal.sval = "";
		parserVal.obj = new Object();
		parserVal.ival = 1;
		fixture.yylval = parserVal;
		fixture.geppettoProgram = new GeppettoProgram();
		fixture.yychar = 1;
		fixture.statemax = 1;
		fixture.valstk = new ParserVal[] {};
		fixture.symbolTable = new ArrayList();
		fixture.yytext = "";
		fixture.yys = "";
		fixture.yym = 1;
		fixture.yystate = 1;
		fixture.yyval = new ParserVal();
		fixture.yynerrs = 1;
		fixture.valptr = 1;
		fixture.variables = new ArrayList();
		fixture.stateptr = 1;
		fixture.propertyDefinitions = new ArrayList();
		fixture.yyerrflag = 1;
		int relative = 1;

		int result = fixture.state_peek(relative);

		// add additional test code here
		assertEquals(0, result);
	}

	/**
	 * Run the int state_pop() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:41 PM
	 */
	@Test
	public void testState_pop_1()
		throws Exception {
		Parser fixture = new Parser(true);
		fixture.yyerror("");
		fixture.stateptrmax = 1;
		fixture.statestk = new int[] {};
		fixture.yyn = 1;
		ParserVal parserVal = new ParserVal(1.0);
		parserVal.sval = "";
		parserVal.obj = new Object();
		parserVal.ival = 1;
		fixture.yylval = parserVal;
		fixture.geppettoProgram = new GeppettoProgram();
		fixture.yychar = 1;
		fixture.statemax = 1;
		fixture.valstk = new ParserVal[] {};
		fixture.symbolTable = new ArrayList();
		fixture.yytext = "";
		fixture.yys = "";
		fixture.yym = 1;
		fixture.yystate = 1;
		fixture.yyval = new ParserVal();
		fixture.yynerrs = 1;
		fixture.valptr = 1;
		fixture.variables = new ArrayList();
		fixture.stateptr = 1;
		fixture.propertyDefinitions = new ArrayList();
		fixture.yyerrflag = 1;

		int result = fixture.state_pop();

		// add additional test code here
		assertEquals(0, result);
	}

	/**
	 * Run the void state_push(int) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:41 PM
	 */
	@Test
	public void testState_push_1()
		throws Exception {
		Parser fixture = new Parser(true);
		fixture.yyerror("");
		fixture.stateptrmax = 1;
		fixture.statestk = new int[] {};
		fixture.yyn = 1;
		ParserVal parserVal = new ParserVal(1.0);
		parserVal.sval = "";
		parserVal.obj = new Object();
		parserVal.ival = 1;
		fixture.yylval = parserVal;
		fixture.geppettoProgram = new GeppettoProgram();
		fixture.yychar = 1;
		fixture.statemax = 1;
		fixture.valstk = new ParserVal[] {};
		fixture.symbolTable = new ArrayList();
		fixture.yytext = "";
		fixture.yys = "";
		fixture.yym = 1;
		fixture.yystate = 1;
		fixture.yyval = new ParserVal();
		fixture.yynerrs = 1;
		fixture.valptr = 1;
		fixture.variables = new ArrayList();
		fixture.stateptr = 1;
		fixture.propertyDefinitions = new ArrayList();
		fixture.yyerrflag = 1;
		int state = 1;

		fixture.state_push(state);

		// add additional test code here
	}

	/**
	 * Run the void val_drop(int) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:41 PM
	 */
	@Test
	public void testVal_drop_1()
		throws Exception {
		Parser fixture = new Parser(true);
		fixture.yyerror("");
		fixture.stateptrmax = 1;
		fixture.statestk = new int[] {};
		fixture.yyn = 1;
		ParserVal parserVal = new ParserVal(1.0);
		parserVal.sval = "";
		parserVal.obj = new Object();
		parserVal.ival = 1;
		fixture.yylval = parserVal;
		fixture.geppettoProgram = new GeppettoProgram();
		fixture.yychar = 1;
		fixture.statemax = 1;
		fixture.valstk = new ParserVal[] {};
		fixture.symbolTable = new ArrayList();
		fixture.yytext = "";
		fixture.yys = "";
		fixture.yym = 1;
		fixture.yystate = 1;
		fixture.yyval = new ParserVal();
		fixture.yynerrs = 1;
		fixture.valptr = 1;
		fixture.variables = new ArrayList();
		fixture.stateptr = 1;
		fixture.propertyDefinitions = new ArrayList();
		fixture.yyerrflag = 1;
		int cnt = 1;

		fixture.val_drop(cnt);

		// add additional test code here
	}

	/**
	 * Run the void val_drop(int) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:41 PM
	 */
	@Test
	public void testVal_drop_2()
		throws Exception {
		Parser fixture = new Parser(true);
		fixture.yyerror("");
		fixture.stateptrmax = 1;
		fixture.statestk = new int[] {};
		fixture.yyn = 1;
		ParserVal parserVal = new ParserVal(1.0);
		parserVal.sval = "";
		parserVal.obj = new Object();
		parserVal.ival = 1;
		fixture.yylval = parserVal;
		fixture.geppettoProgram = new GeppettoProgram();
		fixture.yychar = 1;
		fixture.statemax = 1;
		fixture.valstk = new ParserVal[] {};
		fixture.symbolTable = new ArrayList();
		fixture.yytext = "";
		fixture.yys = "";
		fixture.yym = 1;
		fixture.yystate = 1;
		fixture.yyval = new ParserVal();
		fixture.yynerrs = 1;
		fixture.valptr = 1;
		fixture.variables = new ArrayList();
		fixture.stateptr = 1;
		fixture.propertyDefinitions = new ArrayList();
		fixture.yyerrflag = 1;
		int cnt = 1;

		fixture.val_drop(cnt);

		// add additional test code here
	}

	/**
	 * Run the void val_init() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:41 PM
	 */
	@Test
	public void testVal_init_1()
		throws Exception {
		Parser fixture = new Parser(true);
		fixture.yyerror("");
		fixture.stateptrmax = 1;
		fixture.statestk = new int[] {};
		fixture.yyn = 1;
		ParserVal parserVal = new ParserVal(1.0);
		parserVal.sval = "";
		parserVal.obj = new Object();
		parserVal.ival = 1;
		fixture.yylval = parserVal;
		fixture.geppettoProgram = new GeppettoProgram();
		fixture.yychar = 1;
		fixture.statemax = 1;
		fixture.valstk = new ParserVal[] {};
		fixture.symbolTable = new ArrayList();
		fixture.yytext = "";
		fixture.yys = "";
		fixture.yym = 1;
		fixture.yystate = 1;
		fixture.yyval = new ParserVal();
		fixture.yynerrs = 1;
		fixture.valptr = 1;
		fixture.variables = new ArrayList();
		fixture.stateptr = 1;
		fixture.propertyDefinitions = new ArrayList();
		fixture.yyerrflag = 1;

		fixture.val_init();

		// add additional test code here
	}

	/**
	 * Run the ParserVal val_peek(int) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:41 PM
	 */
	@Test
	public void testVal_peek_1()
		throws Exception {
		Parser fixture = new Parser(true);
		fixture.yyerror("");
		fixture.stateptrmax = 1;
		fixture.statestk = new int[] {};
		fixture.yyn = 1;
		ParserVal parserVal = new ParserVal(1.0);
		parserVal.sval = "";
		parserVal.obj = new Object();
		parserVal.ival = 1;
		fixture.yylval = parserVal;
		fixture.geppettoProgram = new GeppettoProgram();
		fixture.yychar = 1;
		fixture.statemax = 1;
		fixture.valstk = new ParserVal[] {};
		fixture.symbolTable = new ArrayList();
		fixture.yytext = "";
		fixture.yys = "";
		fixture.yym = 1;
		fixture.yystate = 1;
		fixture.yyval = new ParserVal();
		fixture.yynerrs = 1;
		fixture.valptr = 1;
		fixture.variables = new ArrayList();
		fixture.stateptr = 1;
		fixture.propertyDefinitions = new ArrayList();
		fixture.yyerrflag = 1;
		int relative = 1;

		ParserVal result = fixture.val_peek(relative);

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the ParserVal val_peek(int) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:41 PM
	 */
	@Test
	public void testVal_peek_2()
		throws Exception {
		Parser fixture = new Parser(true);
		fixture.yyerror("");
		fixture.stateptrmax = 1;
		fixture.statestk = new int[] {};
		fixture.yyn = 1;
		ParserVal parserVal = new ParserVal(1.0);
		parserVal.sval = "";
		parserVal.obj = new Object();
		parserVal.ival = 1;
		fixture.yylval = parserVal;
		fixture.geppettoProgram = new GeppettoProgram();
		fixture.yychar = 1;
		fixture.statemax = 1;
		fixture.valstk = new ParserVal[] {};
		fixture.symbolTable = new ArrayList();
		fixture.yytext = "";
		fixture.yys = "";
		fixture.yym = 1;
		fixture.yystate = 1;
		fixture.yyval = new ParserVal();
		fixture.yynerrs = 1;
		fixture.valptr = 1;
		fixture.variables = new ArrayList();
		fixture.stateptr = 1;
		fixture.propertyDefinitions = new ArrayList();
		fixture.yyerrflag = 1;
		int relative = 1;

		ParserVal result = fixture.val_peek(relative);

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the ParserVal val_pop() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:41 PM
	 */
	@Test
	public void testVal_pop_1()
		throws Exception {
		Parser fixture = new Parser(true);
		fixture.yyerror("");
		fixture.stateptrmax = 1;
		fixture.statestk = new int[] {};
		fixture.yyn = 1;
		ParserVal parserVal = new ParserVal(1.0);
		parserVal.sval = "";
		parserVal.obj = new Object();
		parserVal.ival = 1;
		fixture.yylval = parserVal;
		fixture.geppettoProgram = new GeppettoProgram();
		fixture.yychar = 1;
		fixture.statemax = 1;
		fixture.valstk = new ParserVal[] {};
		fixture.symbolTable = new ArrayList();
		fixture.yytext = "";
		fixture.yys = "";
		fixture.yym = 1;
		fixture.yystate = 1;
		fixture.yyval = new ParserVal();
		fixture.yynerrs = 1;
		fixture.valptr = -1;
		fixture.variables = new ArrayList();
		fixture.stateptr = 1;
		fixture.propertyDefinitions = new ArrayList();
		fixture.yyerrflag = 1;

		ParserVal result = fixture.val_pop();

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the ParserVal val_pop() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:41 PM
	 */
	@Test
	public void testVal_pop_2()
		throws Exception {
		Parser fixture = new Parser(true);
		fixture.yyerror("");
		fixture.stateptrmax = 1;
		fixture.statestk = new int[] {};
		fixture.yyn = 1;
		ParserVal parserVal = new ParserVal(1.0);
		parserVal.sval = "";
		parserVal.obj = new Object();
		parserVal.ival = 1;
		fixture.yylval = parserVal;
		fixture.geppettoProgram = new GeppettoProgram();
		fixture.yychar = 1;
		fixture.statemax = 1;
		fixture.valstk = new ParserVal[] {};
		fixture.symbolTable = new ArrayList();
		fixture.yytext = "";
		fixture.yys = "";
		fixture.yym = 1;
		fixture.yystate = 1;
		fixture.yyval = new ParserVal();
		fixture.yynerrs = 1;
		fixture.valptr = 1;
		fixture.variables = new ArrayList();
		fixture.stateptr = 1;
		fixture.propertyDefinitions = new ArrayList();
		fixture.yyerrflag = 1;

		ParserVal result = fixture.val_pop();

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the void val_push(ParserVal) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:41 PM
	 */
	@Test
	public void testVal_push_1()
		throws Exception {
		Parser fixture = new Parser(true);
		fixture.yyerror("");
		fixture.stateptrmax = 1;
		fixture.statestk = new int[] {};
		fixture.yyn = 1;
		ParserVal parserVal = new ParserVal(1.0);
		parserVal.sval = "";
		parserVal.obj = new Object();
		parserVal.ival = 1;
		fixture.yylval = parserVal;
		fixture.geppettoProgram = new GeppettoProgram();
		fixture.yychar = 1;
		fixture.statemax = 1;
		fixture.valstk = new ParserVal[] {};
		fixture.symbolTable = new ArrayList();
		fixture.yytext = "";
		fixture.yys = "";
		fixture.yym = 1;
		fixture.yystate = 1;
		fixture.yyval = new ParserVal();
		fixture.yynerrs = 1;
		fixture.valptr = 500;
		fixture.variables = new ArrayList();
		fixture.stateptr = 1;
		fixture.propertyDefinitions = new ArrayList();
		fixture.yyerrflag = 1;
		ParserVal val = new ParserVal();

		fixture.val_push(val);

		// add additional test code here
	}

	/**
	 * Run the void val_push(ParserVal) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:41 PM
	 */
	@Test
	public void testVal_push_2()
		throws Exception {
		Parser fixture = new Parser(true);
		fixture.yyerror("");
		fixture.stateptrmax = 1;
		fixture.statestk = new int[] {};
		fixture.yyn = 1;
		ParserVal parserVal = new ParserVal(1.0);
		parserVal.sval = "";
		parserVal.obj = new Object();
		parserVal.ival = 1;
		fixture.yylval = parserVal;
		fixture.geppettoProgram = new GeppettoProgram();
		fixture.yychar = 1;
		fixture.statemax = 1;
		fixture.valstk = new ParserVal[] {};
		fixture.symbolTable = new ArrayList();
		fixture.yytext = "";
		fixture.yys = "";
		fixture.yym = 1;
		fixture.yystate = 1;
		fixture.yyval = new ParserVal();
		fixture.yynerrs = 1;
		fixture.valptr = 1;
		fixture.variables = new ArrayList();
		fixture.stateptr = 1;
		fixture.propertyDefinitions = new ArrayList();
		fixture.yyerrflag = 1;
		ParserVal val = new ParserVal();

		fixture.val_push(val);

		// add additional test code here
	}

	/**
	 * Run the void yycheck() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:41 PM
	 */
	@Test
	public void testYycheck_1()
		throws Exception {

		Parser.yycheck();

		// add additional test code here
	}

	/**
	 * Run the void yyerror(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:41 PM
	 */
	@Test
	public void testYyerror_1()
		throws Exception {
		Parser fixture = new Parser(true);
		fixture.yyerror("");
		fixture.stateptrmax = 1;
		fixture.statestk = new int[] {};
		fixture.yyn = 1;
		ParserVal parserVal = new ParserVal(1.0);
		parserVal.sval = "";
		parserVal.obj = new Object();
		parserVal.ival = 1;
		fixture.yylval = parserVal;
		fixture.geppettoProgram = new GeppettoProgram();
		fixture.yychar = 1;
		fixture.statemax = 1;
		fixture.valstk = new ParserVal[] {};
		fixture.symbolTable = new ArrayList();
		fixture.yytext = "";
		fixture.yys = "";
		fixture.yym = 1;
		fixture.yystate = 1;
		fixture.yyval = new ParserVal();
		fixture.yynerrs = 1;
		fixture.valptr = 1;
		fixture.variables = new ArrayList();
		fixture.stateptr = 1;
		fixture.propertyDefinitions = new ArrayList();
		fixture.yyerrflag = 1;
		String errorMessage = "";

		fixture.yyerror(errorMessage);

		// add additional test code here
	}

	/**
	 * Run the void yylexdebug(int,int) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:41 PM
	 */
	@Test
	public void testYylexdebug_1()
		throws Exception {
		Parser fixture = new Parser(true);
		fixture.yyerror("");
		fixture.stateptrmax = 1;
		fixture.statestk = new int[] {};
		fixture.yyn = 1;
		ParserVal parserVal = new ParserVal(1.0);
		parserVal.sval = "";
		parserVal.obj = new Object();
		parserVal.ival = 1;
		fixture.yylval = parserVal;
		fixture.geppettoProgram = new GeppettoProgram();
		fixture.yychar = 1;
		fixture.statemax = 1;
		fixture.valstk = new ParserVal[] {};
		fixture.symbolTable = new ArrayList();
		fixture.yytext = "";
		fixture.yys = "";
		fixture.yym = 1;
		fixture.yystate = 1;
		fixture.yyval = new ParserVal();
		fixture.yynerrs = 1;
		fixture.valptr = 1;
		fixture.variables = new ArrayList();
		fixture.stateptr = 1;
		fixture.propertyDefinitions = new ArrayList();
		fixture.yyerrflag = 1;
		int state = 1;
		int ch = -1;

		fixture.yylexdebug(state, ch);

		// add additional test code here
	}

	/**
	 * Run the void yylexdebug(int,int) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:41 PM
	 */
	@Test
	public void testYylexdebug_2()
		throws Exception {
		Parser fixture = new Parser(true);
		fixture.yyerror("");
		fixture.stateptrmax = 1;
		fixture.statestk = new int[] {};
		fixture.yyn = 1;
		ParserVal parserVal = new ParserVal(1.0);
		parserVal.sval = "";
		parserVal.obj = new Object();
		parserVal.ival = 1;
		fixture.yylval = parserVal;
		fixture.geppettoProgram = new GeppettoProgram();
		fixture.yychar = 1;
		fixture.statemax = 1;
		fixture.valstk = new ParserVal[] {};
		fixture.symbolTable = new ArrayList();
		fixture.yytext = "";
		fixture.yys = "";
		fixture.yym = 1;
		fixture.yystate = 1;
		fixture.yyval = new ParserVal();
		fixture.yynerrs = 1;
		fixture.valptr = 1;
		fixture.variables = new ArrayList();
		fixture.stateptr = 1;
		fixture.propertyDefinitions = new ArrayList();
		fixture.yyerrflag = 1;
		int state = 1;
		int ch = 1;

		fixture.yylexdebug(state, ch);

		// add additional test code here
	}

	/**
	 * Run the void yylexdebug(int,int) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:41 PM
	 */
	@Test
	public void testYylexdebug_3()
		throws Exception {
		Parser fixture = new Parser(true);
		fixture.yyerror("");
		fixture.stateptrmax = 1;
		fixture.statestk = new int[] {};
		fixture.yyn = 1;
		ParserVal parserVal = new ParserVal(1.0);
		parserVal.sval = "";
		parserVal.obj = new Object();
		parserVal.ival = 1;
		fixture.yylval = parserVal;
		fixture.geppettoProgram = new GeppettoProgram();
		fixture.yychar = 1;
		fixture.statemax = 1;
		fixture.valstk = new ParserVal[] {};
		fixture.symbolTable = new ArrayList();
		fixture.yytext = "";
		fixture.yys = "";
		fixture.yym = 1;
		fixture.yystate = 1;
		fixture.yyval = new ParserVal();
		fixture.yynerrs = 1;
		fixture.valptr = 1;
		fixture.variables = new ArrayList();
		fixture.stateptr = 1;
		fixture.propertyDefinitions = new ArrayList();
		fixture.yyerrflag = 1;
		int state = 1;
		int ch = -1;

		fixture.yylexdebug(state, ch);

		// add additional test code here
	}

	/**
	 * Run the void yytable() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 5/12/13 6:41 PM
	 */
	@Test
	public void testYytable_1()
		throws Exception {

		Parser.yytable();

		// add additional test code here
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
		new org.junit.runner.JUnitCore().run(ParserTest.class);
	}
}