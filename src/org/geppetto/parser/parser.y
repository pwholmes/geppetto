%{
  import java.io.IOException;
  import java.io.Reader;
  import java.util.ArrayList;
  import java.util.LinkedList;
  import org.geppetto.domain.Attribute;
  import org.geppetto.domain.Entity;
  import org.geppetto.domain.Property;
  import org.geppetto.domain.Rule;
  import org.geppetto.domain.Condition;
  import org.geppetto.domain.Behavior;
  import org.geppetto.domain.VariableType;
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

/* Reserved words */
/* From what I've read this is not the most efficient possible way to do this -- it's a pain to add
   new keywords when you have to define a token ID for each one.  But IMO that's a trifling
   inconvenience, and it makes the grammar so much easier to read. */
%token BOOLEAN ELSE END ENTITY FALSE FLOAT FOR GLOBAL INPUT INT PRINT PROPERTY STRING TRUE WHILE

/* Type definitions */
/* It seems that if you declare a type for *any* symbol in the grammar, BYACCJ decides that you need
 * to declare a type for *every* symbol in the grammar.  This is why you may see the error "$$ is untyped" 
 * suddenly popping up where something previously worked.  This seems like a potentially serious bug in
 * BYACCJ, but I think we can work around it.  Let's try to avoid typing the tokens and symbols.
 * Prof. Aho said he's never had to do it, so I don't think it should be necessary. */
/*
%type <obj> program
%type <obj> attributeList
%type <obj> attribute
%type <obj> typeSpecifier
*/

%%

/**
 * In several places we have list definitions.  Note that allowing lists to be empty introduces
 * the possibility of shift/reduce conflicts; for example, if we have two lists in a row and either
 * one can be empty, the the parser can't tell which one is which.  For now I've resolved  that by
 * simply not allowing those lists to be empty.  So for instance, the propertyDeclarationList and 
 * entityDeclarationList MUST each have at least one entry.  But I that's OK because we can't have 
 * a program without at least one of each of those things.
 */

program:
    definitionList                                  { System.out.println("Parsing program"); } 
    ;

definitionList:
    propertyDeclarationList entityDeclarationList   { System.out.println("Parsing definitions"); }
    ; 

propertyDeclarationList:
    propertyDeclaration                             { properties.add((Property) $1.obj); }
    | propertyDeclarationList propertyDeclaration   { properties.add((Property) $2.obj); }
    ;

propertyDeclaration:
    PROPERTY identifier '(' attributeList ')' ';'   { $$.obj = new Property(symbolTable.get($2.ival)); }
    ;

attributeList:
    attribute                                       { LinkedList<Attribute> attributes = new LinkedList<Attribute>();
                                                      attributes.add((Attribute) $1.obj); 
                                                      $$.obj = attributes; }
    | attributeList attribute                       { LinkedList<Attribute> attributes = (LinkedList<Attribute>) $1.obj;
                                                      attributes.add((Attribute) $2.obj); }
    ;

attribute:
    typeSpecifier identifier                        { $$.obj = new Attribute(yylval.sval, 0); }
    ;
    
entityDeclarationList:
    entityDeclaration                               { entities.add((Entity) $1.obj); }
    | entityDeclarationList entityDeclaration       { entities.add((Entity) $2.obj); }
    ;

entityDeclaration:
    ENTITY identifier '{' propertyList '}' ';'      { Entity entity = new Entity(symbolTable.get($2.ival));
                                                      $$.obj = entity; }
    ;    

propertyList:
    property                                        { LinkedList<Property> properties = new LinkedList<Property>();
                                                      properties.add((Property) $1.obj); 
                                                      $$.obj = properties;}
    | propertyList ',' property                     { LinkedList<Property> properties = (LinkedList<Property>) $1.obj;
                                                      properties.add((Property) $3.obj); 
                                                      $$.obj = properties; }
    ;

property:
    identifier '(' attributeInitializerList ')'     { Property property = new Property(symbolTable.get($1.ival)); }
    ;

attributeInitializerList:
    attributeInitializer                            { } 
    | attributeInitializerList ',' attributeInitializer  { }
    ;

attributeInitializer:
    identifier '=' initialValue                     { }
    ;
    
initialValue:
    INTEGER_LITERAL                                 { }
    | FLOAT_LITERAL                                 { }
    | STRING_LITERAL                                { }
    | TRUE                                          { }
    | FALSE                                         { }
    ; 

identifier:
    STRING_LITERAL                              { $$ = $1; } /* remember that this is an index into the symbol table, not the string itself */ 
    ;
    
typeSpecifier:
    INT                                         { $$.obj = VariableType.INT; }
    | FLOAT                                     { $$.obj = VariableType.FLOAT; }
    | STRING                                    { $$.obj = VariableType.STRING; }
    | BOOLEAN                                   { $$.obj = VariableType.BOOLEAN; }
    ;

%%

/* Yylex is the lexer generated by JFlex */
private Yylex lexer;

/* Symbol table, for identifiers */
public ArrayList<String> symbolTable = new ArrayList<String>();

/* Collections of declared data types */
public LinkedList<Property> properties = new LinkedList<Property>();
public LinkedList<Entity> entities = new LinkedList<Entity>();
public LinkedList<Rule> rules = new LinkedList<Rule>();


/**
 * parse() is called explicitly by the Geppetto main program to start off 
 * the parsing process. 
 **/
public void parse(Reader inputReader) {
    /* Instantiate the lexer.  Yylex is the class generated by JFlex from lexer.flex.  
       We supply our own constructor so we can give it whatever parameters we want. 
       Here we pass it the Reader that supplies the input we're going to parse, a 
       pointer to this class (necessary so the lexer can access the parser's member 
       variable's, such as yylval), and a pointer to the symbol table. */
    lexer = new Yylex(inputReader, this, symbolTable); 
    
    /* Start parsing the input.  BYACCJ will call yylex as necessary to retrieve
       the next token. */    
    yyparse();
}

/**
 * yylex() is called by the BYACCJ parser to retrieve the next token.
 * It should return <0 on an error and 0 on end-of-input.
 *
 * Here we use the JFlex lexer to get the next token.
 * yylval is a member variable of the class generated from this file (Parser).
 * Each time this function is called, we create a new instance of the data type
 * used to store a token value (ParserVal) and set yylval to point to it.
 * Then we call the lexer's yylex() function.  It is the lexer's responsibility
 * to populate yylval appropriately and return an appropriate token ID as its
 * return value.  Token IDs are defined (by us) at the top of this file via
 * the %token directive.  In the lexer they are referenced as "Parser.TOKENID",
 * where TOKENID is an ID defined in the %token directive.
 *
 * ParserVal is "mutable" in that it has members that hold four different data types:
 *  ival holds integer values
 *  dval holds double values
 *  sval holds strings,
 *  obj holds object references
 * In most cases the lexer should probably only be setting only ONE of these members. 
 * 
 * So for instance, if the next token encountered by the lexer is an integer, 
 * it should set yylval.ival to the value of the integer and return a token ID
 * that indicates an integer value.  In our case we have defined a token called 
 * INTEGER_LITERAL.  Be careful not to confuse this with the INT token, which is 
 * returned by the lexer to indicate that it has encountered the "int" reserved 
 * keyword.
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
        System.out.println("ID: " + rv + "; Token: " + tokenToString(yylval));
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
