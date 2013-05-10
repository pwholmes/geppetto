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
  import java.util.HashSet;
  import java.util.List;
  import java.util.Set;
  import org.geppetto.GeppettoException;
  import org.geppetto.GeppettoProgram;
  import org.geppetto.domain.DataType;
  import org.geppetto.domain.Operator;
  import org.geppetto.domain.declaration.ArgumentDeclaration;
  import org.geppetto.domain.declaration.AttributeConstraint;
  import org.geppetto.domain.declaration.AttributeConstraintFloatRange;
  import org.geppetto.domain.declaration.AttributeConstraintFloatSet;
  import org.geppetto.domain.declaration.AttributeConstraintIntegerRange;
  import org.geppetto.domain.declaration.AttributeConstraintIntegerSet;
  import org.geppetto.domain.declaration.AttributeConstraintStringSet;
  import org.geppetto.domain.declaration.AttributeDefinition;
  import org.geppetto.domain.declaration.AttributeInitializer;
  import org.geppetto.domain.declaration.Entity;
  import org.geppetto.domain.declaration.FunctionDefinition;
  import org.geppetto.domain.declaration.Property;
  import org.geppetto.domain.declaration.PropertyDefinition;
  import org.geppetto.domain.declaration.Rule;
  import org.geppetto.domain.declaration.Value;
  import org.geppetto.domain.declaration.VariableDeclaration;
  import org.geppetto.domain.expression.BinaryExpression;
  import org.geppetto.domain.expression.ConstantExpression;
  import org.geppetto.domain.expression.EntityExpression;
  import org.geppetto.domain.expression.Expression;
  import org.geppetto.domain.expression.FunctionExpression;
  import org.geppetto.domain.expression.UnaryExpression;
  import org.geppetto.domain.expression.VariableExpression;
  import org.geppetto.domain.expression.VariantExpression;
  import org.geppetto.domain.statement.CompoundStatement;
  import org.geppetto.domain.statement.EndStatement;
  import org.geppetto.domain.statement.ExpressionStatement;
  import org.geppetto.domain.statement.IterationStatement;
  import org.geppetto.domain.statement.NullStatement;
  import org.geppetto.domain.statement.PrintStatement;
  import org.geppetto.domain.statement.ReturnStatement;
  import org.geppetto.domain.statement.SelectionStatement;
  import org.geppetto.domain.statement.Statement;
//#line 61 "Parser.java"




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
    0,    1,    1,    6,    2,    2,   10,   11,   11,   12,
   12,    3,    3,   14,    5,    5,   16,   17,   17,   17,
   19,    8,    7,    7,    7,    7,   13,   13,   13,   13,
   13,   13,   20,   20,   21,   21,   22,   23,   23,   24,
   15,   15,   25,   26,   26,   27,    9,    9,    9,    9,
    9,    4,    4,   28,   28,   30,   30,   30,   30,   30,
   30,   30,   31,   31,   29,   37,   37,   38,   38,   40,
   40,   41,   41,   41,   42,   42,   42,   42,   42,   43,
   43,   43,   44,   44,   44,   44,   45,   45,   45,   45,
   46,   46,   46,   39,   39,   39,   39,   39,   39,   39,
   47,   47,   48,   18,   49,   49,   32,   32,   33,   34,
   35,   36,
};
final static short yylen[] = {                            2,
    5,    2,    0,    5,    1,    2,    6,    1,    3,    2,
    5,    1,    2,    6,    2,    0,    6,    1,    3,    0,
    2,    1,    1,    1,    1,    1,    1,    1,    1,    1,
    1,    0,    1,    3,    1,    3,    3,    1,    3,    3,
    1,    3,    4,    1,    3,    3,    1,    1,    1,    1,
    1,    1,    2,    6,    7,    1,    1,    1,    1,    1,
    1,    1,    2,    1,    1,    1,    3,    1,    3,    1,
    3,    1,    3,    3,    1,    3,    3,    3,    3,    1,
    3,    3,    1,    3,    3,    3,    1,    2,    2,    2,
    1,    3,    4,    1,    3,    1,    3,    5,    4,    6,
    1,    3,    1,    4,    1,    2,    5,    7,    5,    2,
    5,    3,
};
final static short yydefred[] = {                         3,
    0,    0,   26,   24,   23,    0,   25,    0,    2,    0,
    5,   22,    0,    0,    0,    6,   12,    0,    0,    0,
    0,    0,   13,   52,    0,    0,    0,    8,    0,    0,
    0,    0,   53,   47,   48,   49,   51,   50,    0,    0,
    0,    0,    0,    0,   41,    0,    0,    0,    0,    0,
    0,   94,    0,   65,    0,    0,    0,    0,    0,    0,
    0,   83,   87,    0,    0,   15,    4,    0,    7,    9,
    0,    0,    0,    0,   91,   89,   88,   90,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   33,    0,    0,    0,   29,    0,   31,    0,    0,   44,
   42,   14,   95,    0,   92,  103,    0,  101,    0,    0,
    0,   67,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   84,   85,   86,    0,    0,    0,    0,   11,    0,
    0,    0,    0,   43,    0,    0,   93,    0,    0,    0,
    0,    0,    0,    0,   64,    3,   57,    0,   54,   56,
   58,   59,   60,   61,   62,    0,    0,    0,   18,   37,
   40,   34,   36,   39,   46,   45,    0,  102,   98,  110,
    0,    0,    0,    0,    0,   63,   55,   21,    0,    0,
  100,    0,    0,  112,    0,  105,    0,   17,   19,    0,
    0,    0,  104,  106,    0,  111,  109,    0,  108,
};
final static short yydgoto[] = {                          1,
    2,    8,   15,   22,   32,    9,   10,   51,   52,   11,
   27,   28,  102,   17,   44,   66,  168,  157,  169,  103,
  104,  105,  106,  107,   45,  109,  110,   24,  158,  159,
  160,  161,  162,  163,  164,  165,   54,   55,   56,   57,
   58,   59,   60,   61,   62,   63,  117,  118,  197,
};
final static short yysindex[] = {                         0,
    0, -229,    0,    0,    0, -227,    0, -253,    0, -227,
    0,    0,   32, -227, -263,    0,    0,   27, -207,  -32,
  -38, -193,    0,    0, -154, -227,  -28,    0, -227,   85,
   56, -207,    0,    0,    0,    0,    0,    0,   46,   -7,
   58, -207,   89,  -36,    0,   85,   85,   85,   85, -227,
   17,    0,   90,    0, -133,   73, -129, -194,  -57,  -16,
    3,    0,    0,   85, -227,    0,    0, -109,    0,    0,
 -227, -227,   78,   98,    0,    0,    0,    0,   95,   61,
 -227, -116,   85,   85,   85,   85,   85,   85,   85,   85,
   85,   85,   85,   85,   85,   85,  108,  112,  116,  122,
    0,   43,  125,  134,    0,  135,    0,  119,    8,    0,
    0,    0,    0, -227,    0,    0,   26,    0,  146,   52,
 -129,    0, -194,  -57,  -57,  -16,  -16,  -16,  -16,    3,
    3,    0,    0,    0,  -65, -207,  -73,  -72,    0,  -74,
  -70,  -63, -154,    0, -227,  152,    0,   85, -227,  153,
  176,  177,   85,  179,    0,    0,    0,  155,    0,    0,
    0,    0,    0,    0,    0,   52, -227,   33,    0,    0,
    0,    0,    0,    0,    0,    0, -227,    0,    0,    0,
   85,   85,  161,   85,  -33,    0,    0,    0,   99, -207,
    0,  180,  182,    0,  183,    0,   28,    0,    0,   52,
  192,   52,    0,    0,  -19,    0,    0,   52,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   18,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  258,    0,    0,    0,    0,    0,    0,    0,   35,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  103,    0,    0,    0,  -35,  166,  -27,   68,   79,  200,
  186,    0,    0,    0,    0,    0,    0,  141,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  -25,  -22,
    0,    0,  142,  148,    0,  160,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  129,    0,
  -11,    0,  174,  234,  265,  299,  352,  394,  414,  220,
  374,    0,    0,    0,    0,   37,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  140,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    1,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
  136,    0,    0,    0,    0,    0,   -4,  461,  -21,  291,
    0,  260,    0,  289,    0,    0,    0,  118,  120,    0,
    0,    0,    0,    0,  240,    0,  169,  293,  398, -146,
    0,    0,    0,    0,    0,    0,  232,    0,  500,  243,
  238,   12,   66,   15,  -12,    0,    0,  198,    0,
};
final static int YYTABLESIZE=680;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         49,
  107,   30,   91,   39,   90,   66,   46,   72,   66,   48,
   14,   47,   41,   68,   26,   42,   68,   16,   35,  187,
   14,   38,   21,   66,   50,  155,   93,   65,   92,   69,
    6,   68,   69,  107,   76,   77,   78,   26,  196,   96,
  107,    3,   12,  107,   94,  107,    4,   69,  144,   95,
  204,  145,    5,  205,    6,  207,   80,    7,  107,  107,
   49,  209,   81,    3,   86,   87,  147,   46,    4,  148,
   48,   19,   47,  189,    5,   10,  190,   20,   10,    7,
   20,  132,  133,  134,   49,   50,  155,   25,   73,  156,
   29,   46,   21,   49,   48,   64,   47,  124,  125,   35,
   46,  115,   38,   48,   67,   47,  130,  131,   70,   50,
  155,   70,   34,   35,   36,   68,   69,   49,   50,   72,
   37,  175,   72,  107,   46,  107,   70,   48,   71,   47,
   82,  167,   83,   84,   38,   85,  112,   72,  113,   96,
  114,  120,   50,   96,   96,   96,   96,   96,  135,   96,
  156,  136,  203,  126,  127,  128,  129,   99,  100,  101,
  137,   96,   96,   96,   96,   97,  138,  139,  140,   97,
   97,   97,   97,   97,  156,   97,   99,  141,  142,  143,
   99,   99,   99,   99,   99,  167,   99,   97,   97,   97,
   97,  149,  166,  170,  172,  171,  173,  177,   99,   99,
   99,   99,   91,   88,  174,   89,   91,   91,   91,   91,
   91,  180,   91,  186,   71,  181,  182,   71,  184,  194,
  200,  156,  201,  202,   91,   91,   80,   91,   80,   80,
   80,   12,   71,   34,   35,   36,   12,    3,   68,  150,
   75,   37,    4,   75,   80,   80,  151,   80,    5,  152,
  206,  153,  208,    7,   69,   38,  154,    1,   75,   75,
   82,   75,   82,   82,   82,   32,   27,  107,  107,  107,
  107,  107,   28,  107,   73,  107,  107,   73,   82,   82,
  107,   82,  107,  107,   30,  107,  107,  107,   16,  107,
  107,  185,   73,   16,   34,   35,   36,   12,   16,   16,
  150,   70,   37,   23,   16,   74,  198,  151,   74,  199,
  152,  111,  153,  176,   33,  122,   38,  154,   34,   35,
   36,   12,  123,   74,  150,  121,   37,   34,   35,   36,
   12,  151,   70,   70,  152,   37,  153,   72,   72,   78,
   38,  154,   78,   72,   72,  178,    0,    0,    0,   38,
    0,   34,   35,   36,   12,    0,    0,   78,   78,   37,
   78,   96,   96,   96,    0,   96,    0,   96,   96,    0,
    0,    0,    0,   38,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   97,   97,   97,
    0,   97,   79,   97,   97,   79,    0,    0,   99,   99,
   99,    0,   99,    0,   99,   99,    0,    0,    0,    0,
   79,   79,    0,   79,   81,    0,   81,   81,   81,    0,
    0,    0,    0,    0,   91,   91,   91,   53,   91,    0,
   91,   91,   81,   81,   76,   81,    0,   76,   71,   71,
    0,    0,    0,   74,   80,   80,   80,    0,   80,    0,
   80,   80,   76,   76,   77,   76,    0,   77,   75,   75,
   75,   97,   75,    0,   75,   75,   13,    0,    0,    0,
   18,    0,   77,   77,   20,   77,    0,  116,   82,   82,
   82,   31,   82,    0,   82,   82,   40,    0,    0,   43,
    0,    0,   73,   73,    0,    0,    0,    0,   73,   73,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   79,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   74,   74,   98,    0,    0,    0,   74,
   74,  108,   43,    0,    0,    0,    0,    0,    0,    0,
    0,  119,    0,    0,    0,  116,   75,   75,   75,    0,
  183,    0,    0,    0,    0,    0,    0,   78,   78,   78,
    0,   78,    0,   78,   78,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  146,    0,    0,    0,  192,  193,
    0,  195,   75,    0,   75,   75,   75,   75,   75,   75,
   75,   75,   75,   75,   75,   75,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  108,    0,    0,    0,  179,
   79,   79,   79,    0,   79,    0,   79,   79,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  188,    0,    0,
    0,    0,   81,   81,   81,    0,   81,  191,   81,   81,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   76,   76,   76,    0,   76,    0,   76,   76,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   77,   77,   77,    0,   77,    0,   77,   77,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         33,
    0,   40,   60,   25,   62,   41,   40,   44,   44,   43,
  274,   45,   41,   41,   19,   44,   44,    0,   44,  166,
  274,   44,  286,   59,   58,   59,   43,   32,   45,   41,
  284,   59,   44,   33,   47,   48,   49,   42,  185,   37,
   40,  271,  270,   43,   42,   45,  276,   59,   41,   47,
  197,   44,  282,  200,  284,  202,   40,  287,   58,   59,
   33,  208,   46,  271,  259,  260,   41,   40,  276,   44,
   43,   40,   45,   41,  282,   41,   44,   41,   44,  287,
   44,   94,   95,   96,   33,   58,   59,   61,  125,  123,
  123,   40,  286,   33,   43,   40,   45,   86,   87,  125,
   40,   41,  125,   43,   59,   45,   92,   93,   41,   58,
   59,   44,  267,  268,  269,  123,   59,   33,   58,   41,
  275,  143,   44,  123,   40,  125,   59,   43,   40,   45,
   41,  136,  266,   61,  289,  265,   59,   59,   41,   37,
   46,  258,   58,   41,   42,   43,   44,   45,   41,   47,
  123,   40,  125,   88,   89,   90,   91,  267,  268,  269,
   45,   59,   60,   61,   62,   37,   45,  125,   44,   41,
   42,   43,   44,   45,  123,   47,   37,   44,   44,   61,
   41,   42,   43,   44,   45,  190,   47,   59,   60,   61,
   62,   46,  258,  267,  269,  268,  267,   46,   59,   60,
   61,   62,   37,  261,  268,  263,   41,   42,   43,   44,
   45,   59,   47,   59,   41,   40,   40,   44,   40,   59,
   41,  123,   41,   41,   59,   60,   41,   62,   43,   44,
   45,  270,   59,  267,  268,  269,  270,  271,  266,  273,
   41,  275,  276,   44,   59,   60,  280,   62,  282,  283,
   59,  285,  272,  287,  266,  289,  290,    0,   59,   60,
   41,   62,   43,   44,   45,  125,  125,  267,  268,  269,
  270,  271,  125,  273,   41,  275,  276,   44,   59,   60,
  280,   62,  282,  283,  125,  285,  286,  287,  271,  289,
  290,  156,   59,  276,  267,  268,  269,  270,    8,  282,
  273,   42,  275,   15,  287,   41,  189,  280,   44,  190,
  283,   72,  285,  145,   22,   84,  289,  290,  267,  268,
  269,  270,   85,   59,  273,   83,  275,  267,  268,  269,
  270,  280,  265,  266,  283,  275,  285,  259,  260,   41,
  289,  290,   44,  265,  266,  148,   -1,   -1,   -1,  289,
   -1,  267,  268,  269,  270,   -1,   -1,   59,   60,  275,
   62,  259,  260,  261,   -1,  263,   -1,  265,  266,   -1,
   -1,   -1,   -1,  289,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  259,  260,  261,
   -1,  263,   41,  265,  266,   44,   -1,   -1,  259,  260,
  261,   -1,  263,   -1,  265,  266,   -1,   -1,   -1,   -1,
   59,   60,   -1,   62,   41,   -1,   43,   44,   45,   -1,
   -1,   -1,   -1,   -1,  259,  260,  261,   30,  263,   -1,
  265,  266,   59,   60,   41,   62,   -1,   44,  265,  266,
   -1,   -1,   -1,   46,  259,  260,  261,   -1,  263,   -1,
  265,  266,   59,   60,   41,   62,   -1,   44,  259,  260,
  261,   64,  263,   -1,  265,  266,    6,   -1,   -1,   -1,
   10,   -1,   59,   60,   14,   62,   -1,   80,  259,  260,
  261,   21,  263,   -1,  265,  266,   26,   -1,   -1,   29,
   -1,   -1,  259,  260,   -1,   -1,   -1,   -1,  265,  266,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   50,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  259,  260,   65,   -1,   -1,   -1,  265,
  266,   71,   72,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   81,   -1,   -1,   -1,  148,   47,   48,   49,   -1,
  153,   -1,   -1,   -1,   -1,   -1,   -1,  259,  260,  261,
   -1,  263,   -1,  265,  266,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  114,   -1,   -1,   -1,  181,  182,
   -1,  184,   83,   -1,   85,   86,   87,   88,   89,   90,
   91,   92,   93,   94,   95,   96,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  145,   -1,   -1,   -1,  149,
  259,  260,  261,   -1,  263,   -1,  265,  266,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  167,   -1,   -1,
   -1,   -1,  259,  260,  261,   -1,  263,  177,  265,  266,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  259,  260,  261,   -1,  263,   -1,  265,  266,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  259,  260,  261,   -1,  263,   -1,  265,  266,
};
}
final static short YYFINAL=1;
final static short YYMAXTOKEN=290;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,"'!'",null,null,null,"'%'",null,null,"'('","')'","'*'","'+'",
"','","'-'","'.'","'/'",null,null,null,null,null,null,null,null,null,null,"':'",
"';'","'<'","'='","'>'",null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,"'{'",null,"'}'",null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,"NEWLINE","INFERS","EQUAL_TO",
"NOT_EQUAL_TO","GTE","GREATER_THAN_OR_EQUAL_TO","LTE","LESS_THAN_OR_EQUAL_TO",
"LOGICAL_AND","LOGICAL_OR","INTEGER_LITERAL","FLOAT_LITERAL","STRING_LITERAL",
"IDENTIFIER","BOOLEAN","ELSE","END","ENTITY","FALSE","FLOAT","FOR","FOREACH",
"GLOBAL","IF","INPUT","INT","PRINT","PROPERTY","RETURN","RULE","STRING","THEN",
"TRUE","WHILE",
};
final static String yyrule[] = {
"$accept : program",
"program : variableDeclarationList propertyDefinitionList entityDeclarationList ruleDeclarationList functionDefinitionList",
"variableDeclarationList : variableDeclarationList variableDeclaration",
"variableDeclarationList :",
"variableDeclaration : typeSpecifier identifier '=' constant ';'",
"propertyDefinitionList : propertyDefinition",
"propertyDefinitionList : propertyDefinitionList propertyDefinition",
"propertyDefinition : PROPERTY identifier '(' attributeDefinitionList ')' ';'",
"attributeDefinitionList : attributeDefinition",
"attributeDefinitionList : attributeDefinitionList ',' attributeDefinition",
"attributeDefinition : typeSpecifier identifier",
"attributeDefinition : typeSpecifier identifier '{' attributeConstraint '}'",
"entityDeclarationList : entityDeclaration",
"entityDeclarationList : entityDeclarationList entityDeclaration",
"entityDeclaration : ENTITY identifier '{' propertyList '}' ';'",
"functionDefinitionList : functionDefinitionList functionDefinition",
"functionDefinitionList :",
"functionDefinition : typeSpecifier identifier '(' argumentDeclarationList ')' compoundStatement",
"argumentDeclarationList : argumentDeclaration",
"argumentDeclarationList : argumentDeclarationList ',' argumentDeclaration",
"argumentDeclarationList :",
"argumentDeclaration : typeSpecifier identifier",
"identifier : IDENTIFIER",
"typeSpecifier : INT",
"typeSpecifier : FLOAT",
"typeSpecifier : STRING",
"typeSpecifier : BOOLEAN",
"attributeConstraint : stringList",
"attributeConstraint : integerList",
"attributeConstraint : integerRange",
"attributeConstraint : floatList",
"attributeConstraint : floatRange",
"attributeConstraint :",
"stringList : STRING_LITERAL",
"stringList : stringList ',' STRING_LITERAL",
"integerList : INTEGER_LITERAL",
"integerList : integerList ',' INTEGER_LITERAL",
"integerRange : INTEGER_LITERAL '-' INTEGER_LITERAL",
"floatList : FLOAT_LITERAL",
"floatList : floatList ',' FLOAT_LITERAL",
"floatRange : FLOAT_LITERAL '-' FLOAT_LITERAL",
"propertyList : property",
"propertyList : propertyList ',' property",
"property : identifier '(' attributeInitializerList ')'",
"attributeInitializerList : attributeInitializer",
"attributeInitializerList : attributeInitializerList ',' attributeInitializer",
"attributeInitializer : identifier '=' constant",
"constant : INTEGER_LITERAL",
"constant : FLOAT_LITERAL",
"constant : STRING_LITERAL",
"constant : TRUE",
"constant : FALSE",
"ruleDeclarationList : rule",
"ruleDeclarationList : ruleDeclarationList rule",
"rule : RULE '(' expression ')' INFERS statement",
"rule : RULE identifier '(' expression ')' INFERS statement",
"statement : expressionStatement",
"statement : compoundStatement",
"statement : selectionStatement",
"statement : iterationStatement",
"statement : endStatement",
"statement : printStatement",
"statement : returnStatement",
"expressionStatement : expression ';'",
"expressionStatement : ';'",
"expression : assignmentExpression",
"assignmentExpression : logicalOrExpression",
"assignmentExpression : primaryExpression '=' assignmentExpression",
"logicalOrExpression : logicalAndExpression",
"logicalOrExpression : logicalOrExpression LOGICAL_OR logicalAndExpression",
"logicalAndExpression : equalityExpression",
"logicalAndExpression : logicalAndExpression LOGICAL_AND equalityExpression",
"equalityExpression : relationalExpression",
"equalityExpression : equalityExpression EQUAL_TO relationalExpression",
"equalityExpression : equalityExpression NOT_EQUAL_TO relationalExpression",
"relationalExpression : additiveExpression",
"relationalExpression : relationalExpression '>' additiveExpression",
"relationalExpression : relationalExpression '<' additiveExpression",
"relationalExpression : relationalExpression GTE additiveExpression",
"relationalExpression : relationalExpression LTE additiveExpression",
"additiveExpression : multiplicativeExpression",
"additiveExpression : additiveExpression '+' multiplicativeExpression",
"additiveExpression : additiveExpression '-' multiplicativeExpression",
"multiplicativeExpression : unaryExpression",
"multiplicativeExpression : multiplicativeExpression '*' unaryExpression",
"multiplicativeExpression : multiplicativeExpression '/' unaryExpression",
"multiplicativeExpression : multiplicativeExpression '%' unaryExpression",
"unaryExpression : functionExpression",
"unaryExpression : '+' unaryExpression",
"unaryExpression : '-' unaryExpression",
"unaryExpression : '!' unaryExpression",
"functionExpression : primaryExpression",
"functionExpression : identifier '(' ')'",
"functionExpression : identifier '(' argumentExpressionList ')'",
"primaryExpression : constant",
"primaryExpression : '(' expression ')'",
"primaryExpression : identifier",
"primaryExpression : identifier '.' identifier",
"primaryExpression : identifier '.' identifier '.' identifier",
"primaryExpression : ':' identifier '.' identifier",
"primaryExpression : ':' identifier '.' identifier '.' identifier",
"argumentExpressionList : argumentExpression",
"argumentExpressionList : argumentExpressionList ',' argumentExpression",
"argumentExpression : expression",
"compoundStatement : '{' variableDeclarationList statementList '}'",
"statementList : statement",
"statementList : statementList statement",
"selectionStatement : IF '(' expression ')' statement",
"selectionStatement : IF '(' expression ')' statement ELSE statement",
"iterationStatement : WHILE '(' expression ')' statement",
"endStatement : END ';'",
"printStatement : PRINT '(' expression ')' ';'",
"returnStatement : RETURN expression ';'",
};

//#line 442 "../../../../../src/org/geppetto/parser/parser.y"

/* Yylex is the lexer generated by JFlex */
private Yylex lexer;

/* Stored error message from BYacc/J, set by yyerror() below.
   The idea here was that we'd be able to pass BYacc/J's parse errors back to the main app, thinking that
   the messages would be be something useful like "Unexpected token 'IF'" or something along those lines.
   But this message is invariably *always* just "stack underflow. aborting..." on a parse error, so it's 
   both totally useless and totally non-user-friendly. */
public String errorMessage = null;

/* Symbol table, for identifiers */
public ArrayList<String> symbolTable = new ArrayList<String>();

/* The top-level data type; figuratively, this the root of the AST, though it isn't really a tree. */ 
public GeppettoProgram geppettoProgram = null;

/* Need these as their own global variables because the definitions are needed to validate subsequent declarations.
   These variables are in essence the symbol table. */ 
public ArrayList<PropertyDefinition> propertyDefinitions = null;
public ArrayList<VariableDeclaration> variables = null; 

/**
 * parse() is called explicitly by the Geppetto main program to start off 
 * the parsing process. 
 **/
public GeppettoProgram  parse(Reader inputReader) {
    /* Instantiate the lexer.  Yylex is the class generated by JFlex from lexer.flex.  
       We supply our own constructor, so we can give it whatever parameters we want. 
       Here we pass in the Reader that supplies the input we're going to parse, a 
       pointer to this class (necessary so the lexer can access the parser's member 
       variables, such as yylval), and a pointer to the symbol table. */
    lexer = new Yylex(inputReader, this, symbolTable); 
    
    /* Start parsing the input.  BYACCJ will call yylex as necessary to retrieve
       the next token. */    
    yyparse();

    return GeppettoProgram.getInstance();
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

/*
 * Store BYacc/J's error message for future use.  See the comment on the errorMessage field
 * about 100 lines up for why this turned out to be pointless.
 */
public void yyerror (String errorMessage) {
    this.errorMessage = errorMessage;
}

/*
 * This is just to aid in debugging.  It converts the value of a token returned
 * from the lexer into a String so it can be printed to the console.
 */
private String tokenToString(ParserVal pv) {
    String s = null;
    if (pv == null)
        s = "null"; /* should never happen */
    else
        s = "ival: " + pv.ival + "; dval: " + pv.dval + "; sval: " + pv.sval + "; obj: " + pv.obj;
    return s;
}
//#line 679 "Parser.java"
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
//#line 71 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<VariableDeclaration> variables = (ArrayList<VariableDeclaration>) val_peek(4).obj;
                                                      ArrayList<PropertyDefinition> properties = (ArrayList<PropertyDefinition>) val_peek(3).obj;
                                                      ArrayList<Entity> entities =  (ArrayList<Entity>) val_peek(2).obj;
                                                      ArrayList<Rule> rules = (ArrayList<Rule>) val_peek(1).obj;
                                                      ArrayList<FunctionDefinition> functions = (ArrayList<FunctionDefinition>) val_peek(0).obj;  
                                                      GeppettoProgram.createInstance(variables, properties, entities, rules, functions); }
break;
case 2:
//#line 80 "../../../../../src/org/geppetto/parser/parser.y"
{ variables = (ArrayList<VariableDeclaration>) val_peek(1).obj;
                                                      if (variables == null)
                                                         variables = new ArrayList<VariableDeclaration>();
                                                      variables.add((VariableDeclaration) val_peek(0).obj); 
                                                      yyval.obj = variables; }
break;
case 3:
//#line 85 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new ArrayList<VariableDeclaration>(); }
break;
case 4:
//#line 89 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new VariableDeclaration(symbolTable.get(val_peek(3).ival), (DataType) val_peek(4).obj, (Value) val_peek(1).obj); }
break;
case 5:
//#line 93 "../../../../../src/org/geppetto/parser/parser.y"
{ propertyDefinitions = new ArrayList<PropertyDefinition>();
                                                      propertyDefinitions.add((PropertyDefinition) val_peek(0).obj); 
                                                      yyval.obj = propertyDefinitions; }
break;
case 6:
//#line 96 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<PropertyDefinition> propertyDefs = (ArrayList<PropertyDefinition>) val_peek(1).obj;
                                                      propertyDefs.add((PropertyDefinition) val_peek(0).obj); 
                                                      yyval.obj = propertyDefs; }
break;
case 7:
//#line 102 "../../../../../src/org/geppetto/parser/parser.y"
{ PropertyDefinition propertyDef = new PropertyDefinition(symbolTable.get(val_peek(4).ival));
                                                      propertyDef.addAttributeDefinitions((ArrayList<AttributeDefinition>) val_peek(2).obj);
                                                      yyval.obj = propertyDef; }
break;
case 8:
//#line 108 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<AttributeDefinition> attributeDefs = new ArrayList<AttributeDefinition>();
                                                      attributeDefs.add((AttributeDefinition) val_peek(0).obj); 
                                                      yyval.obj = attributeDefs; }
break;
case 9:
//#line 111 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<AttributeDefinition> attributeDefs = (ArrayList<AttributeDefinition>) val_peek(2).obj;
                                                      attributeDefs.add((AttributeDefinition) val_peek(0).obj); 
                                                      yyval.obj = attributeDefs; }
break;
case 10:
//#line 117 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new AttributeDefinition((DataType)val_peek(1).obj, symbolTable.get(val_peek(0).ival)); }
break;
case 11:
//#line 118 "../../../../../src/org/geppetto/parser/parser.y"
{ AttributeDefinition attributeDef = new AttributeDefinition((DataType)val_peek(4).obj, symbolTable.get(val_peek(3).ival));
                                                      attributeDef.setConstraint((AttributeConstraint) val_peek(1).obj); 
                                                      yyval.obj = attributeDef; }
break;
case 12:
//#line 124 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<Entity> entities = new ArrayList<Entity>();  
                                                      entities.add((Entity) val_peek(0).obj); 
                                                      yyval.obj = entities; }
break;
case 13:
//#line 127 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<Entity> entities = (ArrayList<Entity>) val_peek(1).obj;  
                                                      entities.add((Entity) val_peek(0).obj); 
                                                      yyval.obj = entities; }
break;
case 14:
//#line 133 "../../../../../src/org/geppetto/parser/parser.y"
{ Entity entity = new Entity(symbolTable.get(val_peek(4).ival));
                                                      entity.addProperties((List<Property>) val_peek(2).obj);
                                                      yyval.obj = entity; }
break;
case 15:
//#line 139 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<FunctionDefinition> functions = (ArrayList<FunctionDefinition>) val_peek(1).obj;
                                                      if (functions == null)
                                                          functions = new ArrayList<FunctionDefinition>();
                                                      functions.add((FunctionDefinition) val_peek(0).obj); 
                                                      yyval.obj = functions; }
break;
case 16:
//#line 144 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new ArrayList<FunctionDefinition>(); }
break;
case 17:
//#line 149 "../../../../../src/org/geppetto/parser/parser.y"
{ String name = symbolTable.get(val_peek(4).ival);
                                                      DataType type = (DataType) val_peek(5).obj;
                                                      ArrayList<ArgumentDeclaration> arguments = (ArrayList<ArgumentDeclaration>) val_peek(2).obj;
                                                      CompoundStatement compoundStatement = (CompoundStatement) val_peek(0).obj; 
                                                      yyval.obj = new FunctionDefinition(name, type, arguments, compoundStatement); }
break;
case 18:
//#line 157 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<ArgumentDeclaration> arguments = new ArrayList<ArgumentDeclaration>();
                                                      arguments.add((ArgumentDeclaration) val_peek(0).obj); 
                                                      yyval.obj = arguments; }
break;
case 19:
//#line 160 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<ArgumentDeclaration> arguments = (ArrayList<ArgumentDeclaration>) val_peek(2).obj;
                                                      arguments.add((ArgumentDeclaration) val_peek(0).obj); 
                                                      yyval.obj = arguments; }
break;
case 20:
//#line 163 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new ArrayList<ArgumentDeclaration>(); }
break;
case 21:
//#line 167 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new ArgumentDeclaration(symbolTable.get(val_peek(0).ival), (DataType) val_peek(1).obj); }
break;
case 22:
//#line 171 "../../../../../src/org/geppetto/parser/parser.y"
{ debug("** IDENTIFIER: ID: " + val_peek(0).ival + "; symb table entry: " + symbolTable.get(val_peek(0).ival)); 
                                                      yyval.ival = val_peek(0).ival; }
break;
case 23:
//#line 176 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = DataType.INT; }
break;
case 24:
//#line 177 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = DataType.FLOAT; }
break;
case 25:
//#line 178 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = DataType.STRING; }
break;
case 26:
//#line 179 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = DataType.BOOLEAN; }
break;
case 27:
//#line 183 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new AttributeConstraintStringSet((Set<String>) val_peek(0).obj); }
break;
case 28:
//#line 184 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new AttributeConstraintIntegerSet((Set<Integer>) val_peek(0).obj); }
break;
case 29:
//#line 185 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new AttributeConstraintIntegerRange((ArrayList<Integer>)val_peek(0).obj); }
break;
case 30:
//#line 186 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new AttributeConstraintFloatSet((Set<Float>)val_peek(0).obj); }
break;
case 31:
//#line 187 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new AttributeConstraintFloatRange((ArrayList<Float>)val_peek(0).obj); }
break;
case 33:
//#line 192 "../../../../../src/org/geppetto/parser/parser.y"
{ HashSet<String> stringSet = new HashSet<String>(); 
                                                      stringSet.add(symbolTable.get(val_peek(0).ival)); 
                                                      yyval.obj = stringSet; }
break;
case 34:
//#line 195 "../../../../../src/org/geppetto/parser/parser.y"
{ HashSet<String> stringSet = (HashSet<String>) val_peek(2).obj;
                                                      stringSet.add(symbolTable.get(val_peek(0).ival)); 
                                                      yyval.obj = stringSet; }
break;
case 35:
//#line 201 "../../../../../src/org/geppetto/parser/parser.y"
{ HashSet<Integer> intSet = new HashSet<Integer>(); 
                                                      intSet.add(new Integer(val_peek(0).ival)); 
                                                      yyval.obj = intSet; }
break;
case 36:
//#line 204 "../../../../../src/org/geppetto/parser/parser.y"
{ HashSet<Integer> intSet = (HashSet<Integer>) val_peek(2).obj; 
                                                      intSet.add(new Integer(val_peek(0).ival)); 
                                                      yyval.obj = intSet; }
break;
case 37:
//#line 210 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<Integer> intList = new ArrayList<Integer>();
                                                      intList.add(val_peek(2).ival);
                                                      intList.add(val_peek(0).ival);
                                                      yyval.obj = intList; }
break;
case 38:
//#line 217 "../../../../../src/org/geppetto/parser/parser.y"
{ HashSet<Float> floatSet = new HashSet<Float>(); 
                                                      floatSet.add(new Float(val_peek(0).dval)); 
                                                      yyval.obj = floatSet; }
break;
case 39:
//#line 220 "../../../../../src/org/geppetto/parser/parser.y"
{ HashSet<Float> floatSet = (HashSet<Float>) val_peek(2).obj; 
                                                      floatSet.add(new Float(val_peek(0).dval)); 
                                                      yyval.obj = floatSet; }
break;
case 40:
//#line 226 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<Float> floatList = new ArrayList<Float>();
                                                      floatList.add(new Float(val_peek(2).dval));
                                                      floatList.add(new Float(val_peek(1).dval));
                                                      yyval.obj = floatList; }
break;
case 41:
//#line 233 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<Property> properties = new ArrayList<Property>();
                                                      properties.add((Property) val_peek(0).obj); 
                                                      yyval.obj = properties;}
break;
case 42:
//#line 236 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<Property> properties = (ArrayList<Property>) val_peek(2).obj;
                                                      properties.add((Property) val_peek(0).obj); 
                                                      yyval.obj = properties; }
break;
case 43:
//#line 242 "../../../../../src/org/geppetto/parser/parser.y"
{ String propertyName = symbolTable.get(val_peek(3).ival);
                                                      PropertyDefinition propertyDef = null;
                                                      for (PropertyDefinition def : propertyDefinitions) {
                                                          if (def.getName().equals(propertyName))
                                                              propertyDef = def;
                                                          }
                                                      if (propertyDef == null)
                                                          throw new GeppettoException("Unknown property: " + propertyName);
                                                      Property property = new Property(propertyName, propertyDef); 
                                                      property.setAttributeValues((List<AttributeInitializer>)val_peek(1).obj); 
                                                      yyval.obj = property; }
break;
case 44:
//#line 256 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<AttributeInitializer> attributeInitializers = new ArrayList<AttributeInitializer>();
                                                      attributeInitializers.add((AttributeInitializer) val_peek(0).obj);
                                                      yyval.obj = attributeInitializers; }
break;
case 45:
//#line 259 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<AttributeInitializer> attributeInitiazers = (ArrayList<AttributeInitializer>) val_peek(2).obj;
                                                      attributeInitiazers.add((AttributeInitializer) val_peek(0).obj);
                                                      yyval.obj = attributeInitiazers; }
break;
case 46:
//#line 265 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new AttributeInitializer(symbolTable.get(val_peek(2).ival), (Value)val_peek(0).obj); }
break;
case 47:
//#line 269 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new Value(val_peek(0).ival); }
break;
case 48:
//#line 270 "../../../../../src/org/geppetto/parser/parser.y"
{ Float fval = new Float(val_peek(0).dval); yyval.obj = new Value(fval); }
break;
case 49:
//#line 271 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new Value(symbolTable.get(val_peek(0).ival)); }
break;
case 50:
//#line 272 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new Value(true); }
break;
case 51:
//#line 273 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new Value(false); }
break;
case 52:
//#line 277 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<Rule> rules = new ArrayList<Rule>();  
                                                      rules.add((Rule) val_peek(0).obj); 
                                                      yyval.obj = rules; }
break;
case 53:
//#line 280 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<Rule> rules = (ArrayList<Rule>) val_peek(1).obj;  
                                                      rules.add((Rule) val_peek(0).obj); 
                                                      yyval.obj = rules; }
break;
case 54:
//#line 286 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new Rule((Expression) val_peek(3).obj, (Statement) val_peek(0).obj); }
break;
case 55:
//#line 287 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new Rule(symbolTable.get(val_peek(5).ival), (Expression) val_peek(3).obj, (Statement) val_peek(0).obj); }
break;
case 56:
//#line 291 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 57:
//#line 292 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 58:
//#line 293 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 59:
//#line 294 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 60:
//#line 295 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 61:
//#line 296 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 62:
//#line 297 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 63:
//#line 301 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new ExpressionStatement((Expression) val_peek(1).obj); }
break;
case 64:
//#line 302 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new NullStatement(); }
break;
case 65:
//#line 306 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 66:
//#line 310 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 67:
//#line 311 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new BinaryExpression((Expression) val_peek(2).obj, Operator.ASSIGNMENT, (Expression) val_peek(0).obj); }
break;
case 68:
//#line 315 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 69:
//#line 316 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new BinaryExpression((Expression) val_peek(2).obj, Operator.LOGICAL_OR, (Expression) val_peek(0).obj); }
break;
case 70:
//#line 320 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 71:
//#line 321 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new BinaryExpression( (Expression) val_peek(2).obj,  Operator.LOGICAL_AND, (Expression) val_peek(0).obj); }
break;
case 72:
//#line 325 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 73:
//#line 326 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new BinaryExpression((Expression) val_peek(2).obj, Operator.EQUAL_TO, (Expression) val_peek(0).obj); }
break;
case 74:
//#line 327 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new BinaryExpression((Expression) val_peek(2).obj, Operator.NOT_EQUAL_TO, (Expression) val_peek(0).obj); }
break;
case 75:
//#line 331 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 76:
//#line 332 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new BinaryExpression((Expression) val_peek(2).obj, Operator.GREATER_THAN, (Expression) val_peek(0).obj); }
break;
case 77:
//#line 333 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new BinaryExpression((Expression) val_peek(2).obj, Operator.LESS_THAN, (Expression) val_peek(0).obj); }
break;
case 78:
//#line 334 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new BinaryExpression((Expression) val_peek(2).obj, Operator.GREATER_THAN_OR_EQUAL_TO, (Expression) val_peek(0).obj); }
break;
case 79:
//#line 335 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new BinaryExpression((Expression) val_peek(2).obj, Operator.LESS_THAN_OR_EQUAL_TO, (Expression) val_peek(0).obj); }
break;
case 80:
//#line 339 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 81:
//#line 340 "../../../../../src/org/geppetto/parser/parser.y"
{yyval.obj = new BinaryExpression((Expression) val_peek(2).obj, Operator.ADD, (Expression) val_peek(0).obj); }
break;
case 82:
//#line 341 "../../../../../src/org/geppetto/parser/parser.y"
{yyval.obj = new BinaryExpression((Expression) val_peek(2).obj, Operator.SUBTRACT, (Expression) val_peek(0).obj); }
break;
case 83:
//#line 345 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 84:
//#line 346 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new BinaryExpression((Expression) val_peek(2).obj, Operator.MULTIPLY, (Expression) val_peek(0).obj); }
break;
case 85:
//#line 347 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new BinaryExpression((Expression) val_peek(2).obj, Operator.DIVIDE, (Expression) val_peek(0).obj); }
break;
case 86:
//#line 348 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new BinaryExpression((Expression) val_peek(2).obj, Operator.MODULUS, (Expression) val_peek(0).obj); }
break;
case 87:
//#line 352 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 88:
//#line 353 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new UnaryExpression(Operator.UNARY_PLUS, (Expression) val_peek(1).obj); }
break;
case 89:
//#line 354 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new UnaryExpression(Operator.UNARY_MINUS, (Expression) val_peek(1).obj); }
break;
case 90:
//#line 355 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new UnaryExpression(Operator.UNARY_NEGATION, (Expression) val_peek(1).obj); }
break;
case 91:
//#line 359 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 92:
//#line 360 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new FunctionExpression(symbolTable.get(val_peek(2).ival), new ArrayList<Expression>()); }
break;
case 93:
//#line 361 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new FunctionExpression(symbolTable.get(val_peek(3).ival), (ArrayList<Expression>) val_peek(1).obj); }
break;
case 94:
//#line 365 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new ConstantExpression((Value) val_peek(0).obj); }
break;
case 95:
//#line 366 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(1).obj; }
break;
case 96:
//#line 367 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new VariableExpression(symbolTable.get(val_peek(0).ival)); }
break;
case 97:
//#line 368 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new EntityExpression(symbolTable.get(val_peek(2).ival), symbolTable.get(val_peek(0).ival)); }
break;
case 98:
//#line 369 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new EntityExpression(symbolTable.get(val_peek(4).ival), symbolTable.get(val_peek(2).ival), symbolTable.get(val_peek(0).ival)); }
break;
case 99:
//#line 370 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new VariantExpression(symbolTable.get(val_peek(3).ival), symbolTable.get(val_peek(1).ival)); }
break;
case 100:
//#line 371 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new VariantExpression(symbolTable.get(val_peek(5).ival), symbolTable.get(val_peek(3).ival), symbolTable.get(val_peek(1).ival)); }
break;
case 101:
//#line 375 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<Expression> argList = new ArrayList<Expression>(); 
	                                                  argList.add((Expression) val_peek(0).obj); 
	                                                  yyval.obj = argList; }
break;
case 102:
//#line 378 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<Expression> argList = (ArrayList<Expression>) val_peek(2).obj; 
	                                                  argList.add((Expression) val_peek(0).obj); 
	                                                  yyval.obj = argList; }
break;
case 103:
//#line 387 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 104:
//#line 391 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new CompoundStatement((ArrayList<VariableDeclaration>) val_peek(2).obj, (ArrayList<Statement>) val_peek(1).obj); }
break;
case 105:
//#line 395 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<Statement> statementList = new ArrayList<Statement>(); 
                                                      statementList.add((Statement) val_peek(0).obj); 
                                                      yyval.obj = statementList; }
break;
case 106:
//#line 398 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<Statement> statementList = (ArrayList<Statement>) val_peek(1).obj; 
                                                      statementList.add((Statement) val_peek(0).obj); 
                                                      yyval.obj = statementList; }
break;
case 107:
//#line 404 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new SelectionStatement((Expression) val_peek(2).obj, (Statement) val_peek(0).obj); }
break;
case 108:
//#line 405 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new SelectionStatement((Expression) val_peek(4).obj, (Statement) val_peek(2).obj, (Statement) val_peek(0).obj); }
break;
case 109:
//#line 425 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new IterationStatement((Expression) val_peek(2).obj, (Statement) val_peek(0).obj); }
break;
case 110:
//#line 430 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new EndStatement(); }
break;
case 111:
//#line 434 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new PrintStatement((Expression) val_peek(2).obj); }
break;
case 112:
//#line 438 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new ReturnStatement((Expression) val_peek(1).obj); }
break;
//#line 1360 "Parser.java"
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
