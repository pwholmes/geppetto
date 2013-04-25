//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";



package org.geppetto.parser.generated;



//#line 2 "../../../../../src/org/geppetto/parser/parser.y"
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
//#line 32 "Parser.java"




public class Parser
             implements ParserTokens
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//public class ParserVal is defined in ParserVal.java


String   yytext;//user variable to return contextual strings
ParserVal yyval; //used to return semantic vals from action routines
ParserVal yylval;//the 'lval' (result) I got from yylex()
ParserVal valstk[];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
void val_init()
{
  valstk=new ParserVal[YYSTACKSIZE];
  yyval=new ParserVal();
  yylval=new ParserVal();
  valptr=-1;
}
void val_push(ParserVal val)
{
  if (valptr>=YYSTACKSIZE)
    return;
  valstk[++valptr]=val;
}
ParserVal val_pop()
{
  if (valptr<0)
    return new ParserVal();
  return valstk[valptr--];
}
void val_drop(int cnt)
{
int ptr;
  ptr=valptr-cnt;
  if (ptr<0)
    return;
  valptr = ptr;
}
ParserVal val_peek(int relative)
{
int ptr;
  ptr=valptr-relative;
  if (ptr<0)
    return new ParserVal();
  return valstk[ptr];
}
final ParserVal dup_yyval(ParserVal val)
{
  ParserVal dup = new ParserVal();
  dup.ival = val.ival;
  dup.dval = val.dval;
  dup.sval = val.sval;
  dup.obj = val.obj;
  return dup;
}
//#### end semantic value section ####
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    2,    2,    4,    6,    6,    7,    9,    9,
    9,    9,    9,    9,   10,   10,   11,   11,   12,   13,
   13,   14,    3,    3,   15,   16,   16,   17,   18,   18,
   19,   20,   20,   20,   20,   20,    5,    8,    8,    8,
    8,
};
final static short yylen[] = {                            2,
    1,    2,    1,    2,    6,    1,    3,    5,    1,    1,
    1,    1,    1,    0,    1,    3,    1,    3,    3,    1,
    3,    3,    1,    2,    6,    1,    3,    4,    1,    3,
    3,    1,    1,    1,    1,    1,    1,    1,    1,    1,
    1,
};
final static short yydefred[] = {                         0,
    0,    0,    1,    0,    3,   37,    0,    0,    0,    4,
   23,    0,    0,   24,   41,   39,   38,   40,    0,    6,
    0,    0,    0,    0,    0,    0,    0,   26,    5,    7,
    0,    0,    0,    0,    0,    0,   15,    0,    0,    0,
   11,    0,   13,    0,    0,   29,   27,   25,    0,    0,
    8,    0,    0,    0,    0,   28,    0,   19,   22,   16,
   18,   21,   32,   33,   34,   36,   35,   31,   30,
};
final static short yydgoto[] = {                          2,
    3,    4,    9,    5,   26,   19,   20,   21,   38,   39,
   40,   41,   42,   43,   11,   27,   28,   45,   46,   68,
};
final static short yysindex[] = {                      -264,
 -245,    0,    0, -258,    0,    0,  -21, -245, -241,    0,
    0, -249, -103,    0,    0,    0,    0,    0,  -33,    0,
 -245, -245,  -30, -249,  -93,   -9,  -44,    0,    0,    0,
 -232, -245, -245,  -26,  -13,  -10,    0,  -89,   -7,   -6,
    0,   -5,    0,  -20,  -27,    0,    0,    0, -218, -217,
    0, -216, -215, -214, -254,    0, -245,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,   46,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  -78,    0,    0,    0,  -43,  -42,    0,    0,  -77,  -76,
    0,  -75,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,    0,    0,   47,    2,    0,   28,    0,    0,    0,
    0,    0,    0,    0,   44,    0,   21,    0,   -2,    0,
};
final static int YYTABLESIZE=83;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         33,
   17,   20,    7,   63,   64,   65,    8,   23,    1,   13,
   24,   66,   15,   56,    1,    6,   57,   16,   12,   22,
   67,   17,   25,    8,   18,   35,   36,   37,   29,   31,
   32,   49,   48,   44,   50,   51,   52,   53,   54,   58,
   55,   59,   61,   60,   62,    2,   14,    9,   10,   12,
   10,   30,   14,   47,   69,    0,    0,    0,   44,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   34,   17,   20,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         44,
   44,   44,    1,  258,  259,  260,  265,   41,  273,    8,
   44,  266,  262,   41,  273,  261,   44,  267,   40,  123,
  275,  271,   21,  265,  274,  258,  259,  260,   59,  123,
   40,   45,   59,   32,   45,  125,   44,   44,   44,  258,
   61,  259,  258,  260,  259,    0,  125,  125,  125,  125,
    4,   24,    9,   33,   57,   -1,   -1,   -1,   57,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  125,  125,  125,
};
}
final static short YYFINAL=2;
final static short YYMAXTOKEN=276;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,"'('","')'",null,null,"','",
"'-'",null,null,null,null,null,null,null,null,null,null,null,null,null,"';'",
null,"'='",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
"'{'",null,"'}'",null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,"NEWLINE","INTEGER_LITERAL","FLOAT_LITERAL",
"STRING_LITERAL","IDENTIFIER","BOOLEAN","ELSE","END","ENTITY","FALSE","FLOAT",
"FOR","GLOBAL","INPUT","INT","PRINT","PROPERTY","STRING","TRUE","WHILE",
};
final static String yyrule[] = {
"$accept : program",
"program : definitionList",
"definitionList : propertyDeclarationList entityDeclarationList",
"propertyDeclarationList : propertyDeclaration",
"propertyDeclarationList : propertyDeclarationList propertyDeclaration",
"propertyDeclaration : PROPERTY identifier '(' attributeList ')' ';'",
"attributeList : attribute",
"attributeList : attributeList ',' attribute",
"attribute : typeSpecifier identifier '{' attributeLegalValues '}'",
"attributeLegalValues : stringList",
"attributeLegalValues : integerList",
"attributeLegalValues : integerRange",
"attributeLegalValues : floatList",
"attributeLegalValues : floatRange",
"attributeLegalValues :",
"stringList : STRING_LITERAL",
"stringList : stringList ',' STRING_LITERAL",
"integerList : INTEGER_LITERAL",
"integerList : integerList ',' INTEGER_LITERAL",
"integerRange : INTEGER_LITERAL '-' INTEGER_LITERAL",
"floatList : FLOAT_LITERAL",
"floatList : floatList ',' FLOAT_LITERAL",
"floatRange : FLOAT_LITERAL '-' FLOAT_LITERAL",
"entityDeclarationList : entityDeclaration",
"entityDeclarationList : entityDeclarationList entityDeclaration",
"entityDeclaration : ENTITY identifier '{' propertyList '}' ';'",
"propertyList : property",
"propertyList : propertyList ',' property",
"property : identifier '(' attributeInitializerList ')'",
"attributeInitializerList : attributeInitializer",
"attributeInitializerList : attributeInitializerList ',' attributeInitializer",
"attributeInitializer : identifier '=' initialValue",
"initialValue : INTEGER_LITERAL",
"initialValue : FLOAT_LITERAL",
"initialValue : STRING_LITERAL",
"initialValue : TRUE",
"initialValue : FALSE",
"identifier : IDENTIFIER",
"typeSpecifier : INT",
"typeSpecifier : FLOAT",
"typeSpecifier : STRING",
"typeSpecifier : BOOLEAN",
};

//#line 175 "../../../../../src/org/geppetto/parser/parser.y"

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
        
        /* debug code */
        String tokenName = (rv > 0) ? yyname[rv] : String.valueOf(rv);
        System.out.println("ID: " + tokenName + "; Token: " + tokenToString(yylval));
        
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
//#line 365 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
case 1:
//#line 63 "../../../../../src/org/geppetto/parser/parser.y"
{ System.out.println("Parsing program"); }
break;
case 2:
//#line 67 "../../../../../src/org/geppetto/parser/parser.y"
{ System.out.println("Parsing definitions"); }
break;
case 3:
//#line 71 "../../../../../src/org/geppetto/parser/parser.y"
{ properties.add((Property) val_peek(0).obj); }
break;
case 4:
//#line 72 "../../../../../src/org/geppetto/parser/parser.y"
{ properties.add((Property) val_peek(0).obj); }
break;
case 5:
//#line 76 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new Property(symbolTable.get(val_peek(4).ival)); }
break;
case 6:
//#line 80 "../../../../../src/org/geppetto/parser/parser.y"
{ LinkedList<Attribute> attributes = new LinkedList<Attribute>();
                                                      attributes.add((Attribute) val_peek(0).obj); 
                                                      yyval.obj = attributes; }
break;
case 7:
//#line 83 "../../../../../src/org/geppetto/parser/parser.y"
{ LinkedList<Attribute> attributes = (LinkedList<Attribute>) val_peek(2).obj;
                                                      attributes.add((Attribute) val_peek(0).obj); }
break;
case 8:
//#line 88 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new Attribute(yylval.sval, 0); }
break;
case 9:
//#line 92 "../../../../../src/org/geppetto/parser/parser.y"
{ }
break;
case 10:
//#line 93 "../../../../../src/org/geppetto/parser/parser.y"
{ }
break;
case 11:
//#line 94 "../../../../../src/org/geppetto/parser/parser.y"
{ }
break;
case 12:
//#line 95 "../../../../../src/org/geppetto/parser/parser.y"
{ }
break;
case 13:
//#line 96 "../../../../../src/org/geppetto/parser/parser.y"
{ }
break;
case 14:
//#line 97 "../../../../../src/org/geppetto/parser/parser.y"
{ }
break;
case 15:
//#line 101 "../../../../../src/org/geppetto/parser/parser.y"
{ }
break;
case 16:
//#line 102 "../../../../../src/org/geppetto/parser/parser.y"
{ }
break;
case 17:
//#line 106 "../../../../../src/org/geppetto/parser/parser.y"
{ }
break;
case 18:
//#line 107 "../../../../../src/org/geppetto/parser/parser.y"
{ }
break;
case 19:
//#line 111 "../../../../../src/org/geppetto/parser/parser.y"
{ }
break;
case 20:
//#line 115 "../../../../../src/org/geppetto/parser/parser.y"
{ }
break;
case 21:
//#line 116 "../../../../../src/org/geppetto/parser/parser.y"
{ }
break;
case 22:
//#line 120 "../../../../../src/org/geppetto/parser/parser.y"
{ }
break;
case 23:
//#line 124 "../../../../../src/org/geppetto/parser/parser.y"
{ entities.add((Entity) val_peek(0).obj); }
break;
case 24:
//#line 125 "../../../../../src/org/geppetto/parser/parser.y"
{ entities.add((Entity) val_peek(0).obj); }
break;
case 25:
//#line 129 "../../../../../src/org/geppetto/parser/parser.y"
{ Entity entity = new Entity(symbolTable.get(val_peek(4).ival));
                                                      yyval.obj = entity; }
break;
case 26:
//#line 134 "../../../../../src/org/geppetto/parser/parser.y"
{ LinkedList<Property> properties = new LinkedList<Property>();
                                                      properties.add((Property) val_peek(0).obj); 
                                                      yyval.obj = properties;}
break;
case 27:
//#line 137 "../../../../../src/org/geppetto/parser/parser.y"
{ LinkedList<Property> properties = (LinkedList<Property>) val_peek(2).obj;
                                                      properties.add((Property) val_peek(0).obj); 
                                                      yyval.obj = properties; }
break;
case 28:
//#line 143 "../../../../../src/org/geppetto/parser/parser.y"
{ Property property = new Property(symbolTable.get(val_peek(3).ival)); }
break;
case 29:
//#line 147 "../../../../../src/org/geppetto/parser/parser.y"
{ }
break;
case 30:
//#line 148 "../../../../../src/org/geppetto/parser/parser.y"
{ }
break;
case 31:
//#line 152 "../../../../../src/org/geppetto/parser/parser.y"
{ }
break;
case 32:
//#line 156 "../../../../../src/org/geppetto/parser/parser.y"
{ }
break;
case 33:
//#line 157 "../../../../../src/org/geppetto/parser/parser.y"
{ }
break;
case 34:
//#line 158 "../../../../../src/org/geppetto/parser/parser.y"
{ }
break;
case 35:
//#line 159 "../../../../../src/org/geppetto/parser/parser.y"
{ }
break;
case 36:
//#line 160 "../../../../../src/org/geppetto/parser/parser.y"
{ }
break;
case 37:
//#line 164 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval = val_peek(0); }
break;
case 38:
//#line 168 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = VariableType.INT; }
break;
case 39:
//#line 169 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = VariableType.FLOAT; }
break;
case 40:
//#line 170 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = VariableType.STRING; }
break;
case 41:
//#line 171 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = VariableType.BOOLEAN; }
break;
//#line 686 "Parser.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 */
public void run()
{
  yyparse();
}
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public Parser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public Parser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################
