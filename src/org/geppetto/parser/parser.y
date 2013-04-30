%{
  import java.io.IOException;
  import java.io.Reader;
  import java.util.ArrayList;
  import java.util.HashSet;
  import java.util.List;
  import java.util.Set;
  import org.geppetto.domain.Attribute;
  import org.geppetto.domain.AttributeConstraint;
  import org.geppetto.domain.AttributeConstraintFloatRange;
  import org.geppetto.domain.AttributeConstraintFloatSet;
  import org.geppetto.domain.AttributeConstraintIntegerRange;
  import org.geppetto.domain.AttributeConstraintIntegerSet;
  import org.geppetto.domain.AttributeConstraintStringSet;
  import org.geppetto.domain.AttributeDefinition;
  import org.geppetto.domain.AttributeInitializer;
  import org.geppetto.domain.Behavior;
  import org.geppetto.domain.Condition;
  import org.geppetto.domain.Entity;
  import org.geppetto.domain.GeppettoProgram;
  import org.geppetto.domain.Property;
  import org.geppetto.domain.PropertyDefinition;
  import org.geppetto.domain.Rule;
  import org.geppetto.domain.Value;
  import org.geppetto.domain.Variable;
  import org.geppetto.domain.VariableType;
  import org.geppetto.domain.expression.AssignmentExpression;
  import org.geppetto.domain.expression.BinaryExpression;
  import org.geppetto.domain.expression.BooleanExpression;
  import org.geppetto.domain.expression.Expression;
  import org.geppetto.domain.expression.FunctionExpression;
  import org.geppetto.domain.expression.Operator;
  import org.geppetto.domain.expression.PrimaryExpression;
  import org.geppetto.domain.expression.StructureExpression;
  import org.geppetto.domain.expression.UnaryExpression;
  import org.geppetto.domain.statement.EndStatement;
  import org.geppetto.domain.statement.ExpressionStatement;
  import org.geppetto.domain.statement.IterationStatement;
  import org.geppetto.domain.statement.SelectionStatement;
  import org.geppetto.domain.statement.Statement;
  import org.geppetto.parser.Tree;
  import org.geppetto.parser.TreeNode;
  import org.geppetto.parser.TreeNodeType;
%}

/* Symbols */
%token NEWLINE
 
/* Literal data types */
/* The suffix _LITERAL is added to differentiate these token IDs from the token IDs used for  
   the keywords used to *declare* these types (INT, FLOAT, BOOLEAN and STRING), listed below. 
   Note that we don't need a token for BOOLEAN_VALUE because that's covered by the tokens for
   reserved words TRUE and FALSE.  Remember, when we get one of these, the actual VALUE of the
   type is in the appropriate yylval data member. */ 
%token INTEGER_LITERAL FLOAT_LITERAL STRING_LITERAL

/* Meta types */
%token IDENTIFIER

/* Reserved words */
/* From what I've read this is not the most efficient possible way to do this -- it's a pain to add
   new keywords when you have to define a token ID for each one.  But IMO that's a trifling
   inconvenience, and it makes the grammar so much easier to read. */
%token BOOLEAN ELSE END ENTITY FALSE FLOAT FOR GLOBAL INPUT INT PRINT PROPERTY RULE STRING TRUE WHILE IF FOREACH

%%

/**
 * In several places we have list definitions.  Note that allowing lists to be empty introduces
 * the possibility of shift/reduce conflicts; for example, if we have two lists in a row and either
 * one can be empty, the the parser can't tell which one is which.  For now I've resolved  that by
 * simply not allowing those lists to be empty.  So for instance, the propertyDeclarationList and 
 * entityDeclarationList MUST each have at least one entry.  But I that's OK because we can't have 
 * a program without at least one of each of those things.
 */

/* We probably need to add something to the end of the grammar for "program" to account for "other code"
   not in a rule (e.g., if we define an additional function that a rule calls).  But I'm not quite sure
   what that should be just yet... */   
program:
    variableDeclarationList propertyDefinitionList entityDeclarationList ruleDeclarationList  {
                                                      ArrayList<Variable> globalVariables = (ArrayList<Variable>) $1.obj;
                                                      ArrayList<PropertyDefinition> propertyDefinitions = (ArrayList<PropertyDefinition>) $2.obj;
                                                      ArrayList<Entity> entities =  (ArrayList<Entity>) $3.obj;
                                                      ArrayList<Rule> rules = (ArrayList<Rule>) $4.obj; 
                                                      geppettoProgram = new GeppettoProgram(globalVariables, propertyDefinitions, entities, rules); }
    ; 

variableDeclarationList:
    variableDeclarationList variableDeclaration;  { ArrayList<Variable> variables = (ArrayList<Variable>) $1.obj;
                                                      if (variables == null)
                                                         variables = new ArrayList<Variable>();
                                                      variables.add((Variable) $1.obj); 
                                                      $$.obj = variables; } 
    |                                               { $$.obj = new ArrayList<Variable>(); }
    ;

variableDeclaration:
    typeSpecifier identifier '=' literalValue ';'   { $$.obj = new Variable((VariableType) $1.obj, symbolTable.get($2.ival), $4.obj); }
    ;

propertyDefinitionList:
    propertyDefinition                              { propertyDefinitions = new ArrayList<PropertyDefinition>();
                                                      propertyDefinitions.add((PropertyDefinition) $1.obj); 
                                                      $$.obj = propertyDefinitions; }
    | propertyDefinitionList propertyDefinition     { ArrayList<PropertyDefinition> propertyDefs = (ArrayList<PropertyDefinition>) $1.obj;
                                                      propertyDefs.add((PropertyDefinition) $2.obj); 
                                                      $$.obj = propertyDefs; }
    ;

propertyDefinition:
    PROPERTY identifier '(' attributeDefinitionList ')' ';'   { PropertyDefinition propertyDef = new PropertyDefinition(symbolTable.get($2.ival));
                                                      propertyDef.addAttributeDefinitions((ArrayList<AttributeDefinition>) $4.obj);
                                                      $$.obj = propertyDef; }
    ;

attributeDefinitionList:
    attributeDefinition                             { ArrayList<AttributeDefinition> attributeDefs = new ArrayList<AttributeDefinition>();
                                                      attributeDefs.add((AttributeDefinition) $1.obj); 
                                                      $$.obj = attributeDefs; }
    | attributeDefinitionList ',' attributeDefinition  { ArrayList<AttributeDefinition> attributeDefs = (ArrayList<AttributeDefinition>) $1.obj;
                                                      attributeDefs.add((AttributeDefinition) $3.obj); 
                                                      $$.obj = attributeDefs; }
    ;

attributeDefinition:
    typeSpecifier identifier                        { $$.obj = new AttributeDefinition((VariableType)$1.obj, symbolTable.get($2.ival)); }
    | typeSpecifier identifier '{' attributeConstraint '}'  { AttributeDefinition attributeDef = new AttributeDefinition((VariableType)$1.obj, symbolTable.get($2.ival));
                                                      attributeDef.setConstraint((AttributeConstraint) $4.obj); 
                                                      $$.obj = attributeDef; }
    ;

attributeConstraint:
    stringList                                      { $$.obj = new AttributeConstraintStringSet((Set<String>) $1.obj); }
    | integerList                                   { $$.obj = new AttributeConstraintIntegerSet((Set<Integer>) $1.obj); }
    | integerRange                                  { $$.obj = new AttributeConstraintIntegerRange((ArrayList<Integer>)$1.obj); }
    | floatList                                     { $$.obj = new AttributeConstraintFloatSet((Set<Float>)$1.obj); }
    | floatRange                                    { $$.obj = new AttributeConstraintFloatRange((ArrayList<Float>)$1.obj); }
    |
    ;

stringList:
    STRING_LITERAL                                  { HashSet<String> stringSet = new HashSet<String>(); 
                                                      stringSet.add(symbolTable.get($1.ival)); 
                                                      $$.obj = stringSet; }
    | stringList ',' STRING_LITERAL                 { HashSet<String> stringSet = (HashSet<String>) $1.obj;
                                                      stringSet.add(symbolTable.get($3.ival)); 
                                                      $$.obj = stringSet; }
    ;
    
integerList:
    INTEGER_LITERAL                                 { HashSet<Integer> intSet = new HashSet<Integer>(); 
                                                      intSet.add(new Integer($1.ival)); 
                                                      $$.obj = intSet; }
    | integerList ',' INTEGER_LITERAL               { HashSet<Integer> intSet = (HashSet<Integer>) $1.obj; 
                                                      intSet.add(new Integer($3.ival)); 
                                                      $$.obj = intSet; }
    ;
    
integerRange:
    INTEGER_LITERAL '-' INTEGER_LITERAL             { ArrayList<Integer> intList = new ArrayList<Integer>();
                                                      intList.add($1.ival);
                                                      intList.add($2.ival);
                                                      $$.obj = intList; }
    ;
    
floatList:
    FLOAT_LITERAL                                   { HashSet<Float> floatSet = new HashSet<Float>(); 
                                                      floatSet.add(new Float($1.dval)); 
                                                      $$.obj = floatSet; }
    | floatList ',' FLOAT_LITERAL                   { HashSet<Float> floatSet = (HashSet<Float>) $1.obj; 
                                                      floatSet.add(new Float($3.dval)); 
                                                      $$.obj = floatSet; }
    ;

floatRange:
    FLOAT_LITERAL '-' FLOAT_LITERAL                 { ArrayList<Float> floatList = new ArrayList<Float>();
                                                      floatList.add(new Float($1.dval));
                                                      floatList.add(new Float($2.dval));
                                                      $$.obj = floatList; }
    ;

entityDeclarationList:
    entityDeclaration                               { ArrayList<Entity> entities = new ArrayList<Entity>();  
                                                      entities.add((Entity) $1.obj); 
                                                      $$.obj = entities; }
    | entityDeclarationList entityDeclaration       { ArrayList<Entity> entities = (ArrayList<Entity>) $1.obj;  
                                                      entities.add((Entity) $2.obj); 
                                                      $$.obj = entities; }
    ;

entityDeclaration:
    ENTITY identifier '{' propertyList '}' ';'      { Entity entity = new Entity(symbolTable.get($2.ival));
                                                      entity.addProperties((List<Property>) $4.obj);
                                                      $$.obj = entity; }
    ;    

propertyList:
    property                                        { ArrayList<Property> properties = new ArrayList<Property>();
                                                      properties.add((Property) $1.obj); 
                                                      $$.obj = properties;}
    | propertyList ',' property                     { ArrayList<Property> properties = (ArrayList<Property>) $1.obj;
                                                      properties.add((Property) $3.obj); 
                                                      $$.obj = properties; }
    ;

property:
    identifier '(' attributeInitializerList ')'     { String propertyName = symbolTable.get($1.ival);
                                                      PropertyDefinition propertyDef = null;
                                                      for (PropertyDefinition def : propertyDefinitions) {
                                                          if (def.getName().equals(propertyName))
                                                              propertyDef = def;
                                                          }
                                                      if (propertyDef == null)
                                                          throw new IllegalArgumentException("Unknown property: " + propertyName);
                                                      Property property = new Property(propertyName, propertyDef); 
                                                      property.setAttributeValues((List<AttributeInitializer>)$3.obj); 
                                                      $$.obj = property; }
    ;

attributeInitializerList:
    attributeInitializer                            { ArrayList<AttributeInitializer> attributeInitializers = new ArrayList<AttributeInitializer>();
                                                      attributeInitializers.add((AttributeInitializer) $1.obj);
                                                      $$.obj = attributeInitializers; } 
    | attributeInitializerList ',' attributeInitializer  { ArrayList<AttributeInitializer> attributeInitiazers = (ArrayList<AttributeInitializer>) $1.obj;
                                                      attributeInitiazers.add((AttributeInitializer) $3.obj);
                                                      $$.obj = attributeInitiazers; }
    ;

attributeInitializer:
    identifier '=' literalValue                     { $$.obj = new AttributeInitializer(symbolTable.get($1.ival), (Value)$3.obj); }
    ;
    
literalValue:
    INTEGER_LITERAL                                 { $$.obj = new Value($1.ival); }
    | FLOAT_LITERAL                                 { Float fval = new Float($1.dval); $$.obj = new Value(fval); }
    | STRING_LITERAL                                { $$.obj = new Value(symbolTable.get($1.ival)); }
    | TRUE                                          { $$.obj = new Value(true); }
    | FALSE                                         { $$.obj = new Value(false); }
    ; 
    
ruleDeclarationList:
    rule                                            { ArrayList<Rule> rules = new ArrayList<Rule>();  
                                                      rules.add((Rule) $1.obj); 
                                                      $$.obj = rules; }
    | ruleDeclarationList rule                      { ArrayList<Rule> rules = (ArrayList<Rule>) $1.obj;  
                                                      rules.add((Rule) $2.obj); 
                                                      $$.obj = rules; }
    ;
    
rule:
    RULE condition '-' '>' behavior ';'             { $$.obj = new Rule((Condition) $2.obj, (Behavior) $4.obj); }
    ;
    
condition:
    booleanExpression                               { $$.obj = new Condition((BooleanExpression) $1.obj); }
    ;

behavior:
    statement                                       { $$.obj = new Behavior((Statement) $1.obj); }
    ;

statement:
	expressionStatement                             { $$.obj = $1.obj; }
	| compoundStatement	                            { $$.obj = $1.obj; }
	| selectionStatement	                        { $$.obj = $1.obj; }
	| iterationStatement	                        { $$.obj = $1.obj; }
	| endStatement		                            { $$.obj = $1.obj; }
	/*| declaration		                            { $$.obj = $1.obj; } -> check if is in grammar*/
	;
	
expressionStatement:
	expression ';'                                  { $$.obj = new ExpressionStatement((Expression) $1.obj); }
	| ';'
	;
	
expression:
	assignmentExpression                            { $$.obj = $1.obj; }
	;

assignmentExpression:
	booleanExpression                               { $$.obj = $1.obj; }
	| unaryExpression '=' assignmentExpression      { $$.obj = new AssignmentExpression((Expression) $1.obj, (Expression) $3.obj); }
	;

booleanExpression:
    logicalOrExpression                             { $$.obj = $1.obj; }
    ;
	
logicalOrExpression:
	logicalAndExpression                            { $$.obj = $1.obj; }
	| logicalOrExpression "||" logicalAndExpression { $$.obj = new BinaryExpression((Expression) $1.obj, Operator.LOGICAL_OR, (Expression) $3.obj); }
	;

logicalAndExpression:
	equalityExpression                              { $$.obj = $1.obj; }
	| logicalAndExpression "&&" equalityExpression  { $$.obj = new BinaryExpression( (Expression) $1.obj,  Operator.LOGICAL_AND, (Expression) $3.obj); }
	;
	
equalityExpression:
	relationalExpression                            { $$.obj = $1.obj; }
	| equalityExpression "==" relationalExpression  { $$.obj = new BinaryExpression((Expression) $1.obj, Operator.EQUAL_TO, (Expression) $3.obj); }
	| equalityExpression "!=" relationalExpression  { $$.obj = new BinaryExpression((Expression) $1.obj, Operator.NOT_EQUAL_TO, (Expression) $3.obj); }
	;	

relationalExpression:
	additiveExpression                              { $$.obj = $1.obj; }
	| relationalExpression '>' additiveExpression   { $$.obj = new BinaryExpression((Expression) $1.obj, Operator.GREATER_THAN, (Expression) $3.obj); }
	| relationalExpression '<' additiveExpression   { $$.obj = new BinaryExpression((Expression) $1.obj, Operator.LESS_THAN, (Expression) $3.obj); }
	| relationalExpression ">=" additiveExpression  { $$.obj = new BinaryExpression((Expression) $1.obj, Operator.GREATER_THAN_OR_EQUAL, (Expression) $3.obj); }
	| relationalExpression "<=" additiveExpression  { $$.obj = new BinaryExpression((Expression) $1.obj, Operator.LESS_THAN_OR_EQUAL, (Expression) $3.obj); }
	;
	
additiveExpression:
	multiplicativeExpression                        { $$.obj = $1.obj; }
	| additiveExpression '+' multiplicativeExpression {$$.obj = new BinaryExpression((Expression) $1.obj, Operator.PLUS, (Expression) $3.obj); }
	| additiveExpression '-' multiplicativeExpression {$$.obj = new BinaryExpression((Expression) $1.obj, Operator.MINUS, (Expression) $3.obj); }
	;
	
multiplicativeExpression:
	unaryExpression                                 { $$.obj = $1.obj; }
	| multiplicativeExpression '*' unaryExpression  { $$.obj = new BinaryExpression((Expression) $1.obj, Operator.MULTIPLY, (Expression) $3.obj); }
	| multiplicativeExpression '/' unaryExpression  { $$.obj = new BinaryExpression((Expression) $1.obj, Operator.DIVIDE, (Expression) $3.obj); }
	| multiplicativeExpression '%' unaryExpression  { $$.obj = new BinaryExpression((Expression) $1.obj, Operator.MODULUS, (Expression) $3.obj); }	
	;
	
unaryExpression:
	functionExpression                              { $$.obj = $1.obj; }
	| '+' unaryExpression                           { $$.obj = new UnaryExpression(Operator.UNARY_PLUS, (Expression) $1.obj); }
	| '-' unaryExpression                           { $$.obj = new UnaryExpression(Operator.UNARY_MINUS, (Expression) $1.obj); }
	| '!' unaryExpression                           { $$.obj = new UnaryExpression(Operator.UNARY_NEGATION, (Expression) $1.obj); }
	;

functionExpression:
	structureExpression                             { $$.obj = $1.obj; }
	| identifier '(' ')'                            { $$.obj = new FunctionExpression(symbolTable.get($1.ival), null); }
	| identifier '(' argumentExpressionList ')'     { $$.obj = new FunctionExpression(symbolTable.get($1.ival), (ArrayList<Expression>) $1.obj); }
	;

structureExpression:
    primaryExpression                               { $$.obj = $1.obj; }
    identifier '.' identifier                       { $$.obj = new StructureExpression(symbolTable.get($1.ival), symbolTable.get($3.ival)); }
    | identifier '.' identifier '.' identifier      { $$.obj = new StructureExpression(symbolTable.get($1.ival), symbolTable.get($3.ival), symbolTable.get($5.ival)); }
    ;
    
argumentExpressionList:
	argumentExpression                              { ArrayList<Expression> argList = new ArrayList<Expression>(); 
	                                                  argList.add((Expression) $1.obj); 
	                                                  $$.obj = argList; }
	| argumentExpressionList ',' argumentExpression { ArrayList<Expression> argList = (ArrayList<Expression>) $1.obj; 
	                                                  argList.add((Expression) $3.obj); 
	                                                  $$.obj = argList; }
	;

/* In K&R an argumentExpression is defined as an assignmentExpression, but that's because in C an expression can be a comma-delimited 
   list of assignmentExpressions.  Geppetto doesn't support that syntax, so in our grammar expressions and assignmentExpressions are  
   synonymous.  Therefore argumentExpressions can just be expressions, which IMO is more intuitive anyway. */
argumentExpression:
    expression                                      { $$.obj = $1.obj; }
    ;

primaryExpression:
    identifier                                      { $$.obj = new PrimaryExpression(symbolTable.get($1.ival)); }
    | literalValue                                  { $$.obj = new PrimaryExpression((Value) $1.obj); }
    | '(' expression ')'                            { $$.obj = $2.obj; }
    ;

compoundStatement:
	'{' statementList '}'		                    { $$.obj = $2.obj; }
	;
	
statementList:
    statement                                       { ArrayList<Statement> statementList = new ArrayList<Statement>(); 
                                                      statementList.add((Statement) $1.obj); 
                                                      $$.obj = statementList; }
    | statementList statement                       { ArrayList<Statement> statementList = (ArrayList<Statement>) $1.obj; 
                                                      statementList.add((Statement) $2.obj); 
                                                      $$.obj = statementList; }
    ;

selectionStatement:
	IF '(' booleanExpression ')' statement          { $$.obj = new SelectionStatement( (BooleanExpression) $3.obj, (Statement) $5.obj); }
	| IF '(' booleanExpression ')' statement ELSE statement { $$.obj = new SelectionStatement( (BooleanExpression) $3.obj, (Statement) $5.obj, (Statement) $7.obj); }
	;

iterationStatement:
	WHILE '(' booleanExpression ')' statement       { $$.obj = new IterationStatement((BooleanExpression) $3.obj, (Statement) $5.obj); }
	/* | FOREACH identifier statement		            { $$.obj = new IterationStatement((Identifier) $2.obj, (Statement) $3.obj); }  let's hold off on this one for now, it's going to be very difficult to implement*/
	;

endStatement:
	END						                        { $$.obj = new EndStatement(); }
	;

identifier:
    IDENTIFIER                                      { debug("** IDENTIFIER: ID: " + $1.ival + "; symb table entry: " + symbolTable.get($1.ival)); 
                                                      $$.ival = $1.ival; } /* remember that this is an index into the symbol table, not the string itself */ 
    ;
    
typeSpecifier:
    INT                                             { $$.obj = VariableType.INT; }
    | FLOAT                                         { $$.obj = VariableType.FLOAT; }
    | STRING                                        { $$.obj = VariableType.STRING; }
    | BOOLEAN                                       { $$.obj = VariableType.BOOLEAN; }
    ;


%%

/* Yylex is the lexer generated by JFlex */
private Yylex lexer;

/* Symbol table, for identifiers */
public ArrayList<String> symbolTable = new ArrayList<String>();

/* The top-level data type; figuratively, this the root of the AST, though it isn't really a tree. */ 
public GeppettoProgram geppettoProgram = null;

/* Need this as its own global variable because the definitions are needed to validate subsequent declarations */ 
public ArrayList<PropertyDefinition> propertyDefinitions = null;

/**
 * parse() is called explicitly by the Geppetto main program to start off 
 * the parsing process. 
 **/
public GeppettoProgram  parse(Reader inputReader) {
    /* Instantiate the lexer.  Yylex is the class generated by JFlex from lexer.flex.  
       We supply our own constructor so we can give it whatever parameters we want. 
       Here we pass it the Reader that supplies the input we're going to parse, a 
       pointer to this class (necessary so the lexer can access the parser's member 
       variable's, such as yylval), and a pointer to the symbol table. */
    lexer = new Yylex(inputReader, this, symbolTable); 
    
    /* Start parsing the input.  BYACCJ will call yylex as necessary to retrieve
       the next token. */    
    yyparse();
    
    return geppettoProgram;
}

/**
 * yylex() is called by the BYACCJ parser to retrieve the next input token.
 * It should return <0 on an error and 0 on end-of-input.
 *
 * Here we use the JFlex lexer to get the next token.
 * yylval is a member variable of the class generated from this file (Parser).
 * Each time this function is called, we call the lexer's yylex() function.  
 * It is the lexer's responsibility to create and properly populate a new
 * ParserVal() instance to hold the token data, and to return one of the token IDs
 * defined in the %token directives above to indicate the meaning of the data in
 * the ParserVal object.  Note that before calling the lexer, we create a default 
 * ParserVal object and set yylval to point to it, but that object should only be 
 * considered a failsafe in case something goes wrong in the lexer, or if the token 
 * being parsed doesn't need to return any token data (for example, if the token is 
 * a keyword like "else", that token doesn't have any data associated with it; 
 * the lexer just needs to return the ELSE token ID and no ParserVal object is
 * required).
 *
 * ParserVal is "mutable" in that it has members that hold four different data types:
 *  ival holds integer values
 *  dval holds double values
 *  sval holds strings,
 *  obj holds object references
 * In most cases the lexer should only be setting only ONE of these members. 
 * 
 * So for instance, if the next token encountered by the lexer is an integer, 
 * it should set yylval.ival to the value of the integer and return a token ID
 * that indicates an integer value, which in our case is the token ID INTEGER_LITERAL.
 * Be careful not to confuse this with the INT token, which is returned by the lexer 
 * to indicate that it has encountered the "int" reserved keyword.
 **/
private int yylex () {
    int rv = -1;
    try {
        /* Create a default ParserVal for tokens that don't create one of their own
           (e.g., keywords).  This is inefficient because it will just be thrown away 
           half the time, and when it isn't that's because it isn't even necessary to 
           have one, but we need it avoid NPEs in BYACCJ. */
        /*yylval = new ParserVal(0);*/ 
        rv = lexer.yylex();
        
        /* debug code */
        String tokenName = (rv > 0) ? yyname[rv] : String.valueOf(rv);
        debug("ID: " + tokenName + "; Token: " + tokenToString(yylval));

    } catch (IOException e) {
        System.err.println("IO error :" + e);
    }
    return rv;
}

public void yyerror (String error) {
    System.err.println ("Error: " + error);
}

/*
 * This is just to aid in debugging.  It prints out the value of a token returned
 * from the lexer to the console.
 */
private String tokenToString(ParserVal pv) {
    String s = null;
    if (pv == null)
        s = "null"; /* should never happen */
    else
        s = "ival: " + pv.ival + "; dval: " + pv.dval + "; sval: " + pv.sval + "; obj: " + pv.obj;
    return s;
}
