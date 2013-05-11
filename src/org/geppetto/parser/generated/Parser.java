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
  import org.geppetto.domain.expression.InputExpression;
  import org.geppetto.domain.expression.LengthExpression;
  import org.geppetto.domain.expression.RandomExpression;
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
//#line 64 "Parser.java"




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
   12,    3,    3,   14,    4,    4,   16,   16,    5,    5,
   19,   20,   20,   20,   22,    8,    7,    7,    7,    7,
   13,   13,   13,   13,   13,   13,   23,   23,   24,   24,
   25,   26,   26,   27,   15,   15,   28,   29,   29,   30,
    9,    9,    9,    9,    9,   17,   31,   31,   32,   32,
   34,   34,   35,   35,   35,   36,   36,   36,   36,   36,
   37,   37,   37,   38,   38,   38,   38,   39,   39,   39,
   39,   40,   40,   40,   40,   40,   40,   40,   40,   33,
   33,   33,   33,   33,   33,   33,   41,   41,   18,   18,
   18,   18,   18,   18,   18,   42,   42,   21,   48,   48,
   43,   43,   44,   45,   46,   47,
};
final static short yylen[] = {                            2,
    5,    2,    0,    5,    1,    2,    6,    1,    3,    2,
    5,    1,    2,    6,    1,    2,    6,    7,    2,    0,
    6,    1,    3,    0,    2,    1,    1,    1,    1,    1,
    1,    1,    1,    1,    1,    0,    1,    3,    1,    3,
    3,    1,    3,    3,    1,    3,    4,    1,    3,    3,
    1,    1,    1,    1,    1,    1,    1,    3,    1,    3,
    1,    3,    1,    3,    3,    1,    3,    3,    3,    3,
    1,    3,    3,    1,    3,    3,    3,    1,    2,    2,
    2,    1,    3,    4,    4,    4,    4,    4,    3,    1,
    3,    1,    3,    5,    4,    6,    1,    3,    1,    1,
    1,    1,    1,    1,    1,    2,    1,    4,    1,    2,
    5,    7,    5,    2,    5,    3,
};
final static short yydefred[] = {                         3,
    0,    0,   30,   28,   27,    0,   29,    0,    2,    0,
    5,   26,    0,    0,    0,    6,   12,    0,    0,    0,
    0,    0,   13,   15,    0,    0,    0,    8,    0,    0,
    0,    0,   16,   51,   52,   53,   55,   54,    0,    0,
    0,    0,    0,    0,   45,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   90,    0,   56,    0,    0,    0,
    0,    0,    0,    0,   74,   78,    0,    0,   19,    4,
    0,    7,    9,    0,    0,    0,    0,    0,    0,    0,
   82,   80,   79,   81,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   37,    0,    0,    0,
   33,    0,   35,    0,    0,   48,   46,   14,   89,    0,
   39,   42,    0,    0,    0,   91,    0,   83,   97,    0,
    0,    0,    0,   58,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   75,   76,   77,    0,    0,    0,    0,
   11,    0,    0,    0,    0,   47,    0,   85,   88,   87,
   86,    0,   84,    0,    0,    0,    0,    0,    0,    0,
  107,    3,    0,   17,  100,   99,  101,  102,  103,  104,
  105,    0,    0,    0,   22,   41,   44,   38,   40,   43,
   50,   49,    0,   98,   94,  114,    0,    0,    0,    0,
    0,  106,   18,   25,    0,    0,   96,    0,    0,  116,
    0,  109,    0,   21,   23,    0,    0,    0,  108,  110,
    0,  115,  113,    0,  112,
};
final static short yydgoto[] = {                          1,
    2,    8,   15,   22,   32,    9,   10,   54,   55,   11,
   27,   28,  108,   17,   44,   24,  173,  174,   69,  184,
  175,  185,  109,  110,  111,  112,  113,   45,  115,  116,
   57,   58,   59,   60,   61,   62,   63,   64,   65,   66,
  130,  176,  177,  178,  179,  180,  181,  213,
};
final static short yysindex[] = {                         0,
    0, -215,    0,    0,    0, -252,    0, -243,    0, -252,
    0,    0,  -12, -252, -269,    0,    0,   -4, -260,  -61,
  -38, -224,    0,    0, -220, -252,   31,    0, -252,   96,
   28, -260,    0,    0,    0,    0,    0,    0,   36,  -15,
   43, -260,   76,  -40,    0,   83,   91,   97,   96,   96,
   96,   96, -252,   -3,    0,   80,    0, -128,   79, -108,
 -172,    3,  -10,   64,    0,    0,   96, -252,    0,    0,
 -125,    0,    0, -252, -252,  100,  120,   96, -118,  122,
    0,    0,    0,    0,  118,   87, -252,  -93,   96,   96,
   96,   96,   96,   96,   96,   96,   96,   96,   96,   96,
   96,   96,  132,  140,  129,  137,    0,   65,  148,  154,
    0,  155,    0,  139,   38,    0,    0,    0,    0,  167,
    0,    0,   39,   40,   63,    0, -252,    0,    0,   69,
  163,   60, -108,    0, -172,    3,    3,  -10,  -10,  -10,
  -10,   64,   64,    0,    0,    0,  -41, -260,  -49,  -45,
    0,  -43,  -42,  -13, -220,    0, -252,    0,    0,    0,
    0,  178,    0,   96, -252,  168,  190,  193,   96,  201,
    0,    0,  187,    0,    0,    0,    0,    0,    0,    0,
    0,   60, -252,   71,    0,    0,    0,    0,    0,    0,
    0,    0, -252,    0,    0,    0,   96,   96,  198,   96,
  -33,    0,    0,    0,  144, -260,    0,  222,  234,    0,
  237,    0,   33,    0,    0,   60,  220,   60,    0,    0,
   22,    0,    0,   60,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   15,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  295,    0,    0,    0,    0,    0,    0,    0,   73,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  125,    0,    0,    0,  -35,  169,  -27,
  -21,  221,  277,  395,    0,    0,    0,    0,    0,    0,
  171,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  -36,  -31,    0,    0,  173,  174,
    0,  180,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  134,    0,  -14,    0,   -5,  290,  301,  348,  383,  423,
  432,  403,  415,    0,    0,    0,    0,   81,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  160,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    1,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
  135,    0,    0,    0,    0,    0,   67,  494,  -22,  302,
    0,  267,    0,  296,    0,  293,   -9,  289,    0,    0,
  107,  116,  244,  247,    0,  253,    0,  263,    0,  191,
  260,    0,  501,  264,  268,   42,  -44,   54,   46,    0,
    0,    0,    0,    0,    0,    0,    0,    0,
};
final static int YYTABLESIZE=698;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         52,
  111,   30,   39,   75,   14,   57,   49,   39,   57,   51,
    3,   50,   42,   59,   20,    4,   59,   12,   21,   61,
   56,    5,   61,   57,   53,  171,   60,   19,    7,   60,
   14,   59,   99,  111,   98,   62,   86,   61,   62,   80,
  111,    6,   87,  111,   60,  111,   34,   35,   36,  138,
  139,  140,  141,   62,   37,    3,   25,  103,  111,  111,
    4,   29,   97,   21,   96,   52,    5,   67,  120,    6,
   38,   41,   49,    7,   42,   51,  129,   50,  156,  159,
  160,  157,  152,  153,   76,   26,   92,   93,   39,  172,
   53,  171,   52,   42,   70,   82,   83,   84,   68,   49,
  102,   72,   51,  161,   50,  100,  154,   71,   26,  163,
  101,  205,  164,   10,  206,   74,   10,   53,  171,   52,
   88,   24,   77,  111,   24,  111,   49,  128,   52,   51,
   78,   50,  191,  136,  137,   49,   79,   89,   51,   90,
   50,  105,  106,  107,   53,  144,  145,  146,  121,  122,
  107,  142,  143,   53,  194,  172,   91,  219,  118,  199,
  119,   92,  126,  127,  132,   92,   92,   92,   92,   92,
   93,   92,  147,  149,   93,   93,   93,   93,   93,  148,
   93,  150,  172,   92,   92,   92,   92,  208,  209,  151,
  211,  152,   93,   93,   93,   93,   95,  153,  154,  155,
   95,   95,   95,   95,   95,   82,   95,  158,  165,   82,
   82,   82,   82,   82,  183,   82,  182,  186,   95,   95,
   95,   95,  187,  193,  189,  188,  196,   82,   82,  197,
   82,   12,  198,   34,   35,   36,   12,    3,   59,  166,
  200,   37,    4,   61,   61,  202,  167,   46,    5,   47,
  168,   60,   48,  169,  190,    7,  210,   38,  170,   62,
   62,   63,  216,   94,   63,   95,  172,  111,  111,  111,
  111,  111,  183,  111,  217,  111,  111,  218,  222,   63,
  111,  111,  111,  111,  111,   20,  111,  111,  111,  111,
   20,  111,  111,  224,    1,   36,   20,   31,   32,   34,
   35,   36,   12,   20,   34,  166,  201,   37,   73,   16,
   23,  214,  167,   46,   33,   47,  168,   66,   48,  169,
   66,  215,  123,   38,  170,  124,   34,   35,   36,   12,
   64,  125,  166,   64,   37,   66,   66,  117,   66,  167,
   46,   65,   47,  168,   65,   48,  169,  192,   64,  134,
   38,  170,  133,   34,   35,   36,   12,    0,  135,   65,
    0,   37,   34,   35,   36,   12,    0,   46,    0,   47,
   37,    0,   48,    0,    0,    0,   46,   38,   47,    0,
    0,   48,    0,   92,   92,   92,   38,   92,   69,   92,
   92,   69,   93,   93,   93,    0,   93,    0,   93,   93,
    0,    0,    0,    0,    0,    0,   69,   69,    0,   69,
    0,    0,    0,    0,    0,    0,    0,    0,   95,   95,
   95,    0,   95,   70,   95,   95,   70,   82,   82,   82,
    0,   82,    0,   82,   82,   71,    0,   71,   71,   71,
    0,   70,   70,   73,   70,   73,   73,   73,    0,    0,
    0,    0,    0,   71,   71,   72,   71,   72,   72,   72,
    0,   73,   73,   67,   73,    0,   67,    0,    0,    0,
  203,    0,   68,   72,   72,   68,   72,    0,    0,   63,
   63,   67,   67,    0,   67,   63,   63,    0,    0,  212,
   68,   68,    0,   68,    0,    0,    0,    0,    0,   13,
    0,  220,    0,   18,  221,    0,  223,   20,    0,    0,
    0,    0,  225,    0,   31,    0,    0,    0,    0,   40,
    0,    0,   43,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   66,   66,   66,    0,   66,
    0,   66,   66,    0,    0,    0,   85,    0,   64,   64,
   81,   81,   81,    0,   64,   64,    0,    0,    0,   65,
   65,  104,    0,    0,    0,   65,   65,  114,   43,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  131,    0,    0,    0,    0,    0,    0,    0,    0,   81,
    0,   81,   81,   81,   81,   81,   81,   81,   81,   81,
   81,   81,   81,    0,    0,    0,   69,   69,   69,    0,
   69,    0,   69,   69,    0,    0,    0,    0,    0,    0,
  162,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   70,   70,   70,    0,   70,    0,   70,   70,    0,
  114,    0,    0,   71,   71,   71,    0,   71,  195,   71,
   71,   73,   73,   73,    0,   73,    0,   73,   73,    0,
    0,    0,    0,   72,   72,   72,  204,   72,    0,   72,
   72,   67,   67,   67,    0,   67,  207,   67,   67,    0,
   68,   68,   68,    0,   68,    0,   68,   68,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         33,
    0,   40,   25,   44,  274,   41,   40,   44,   44,   43,
  271,   45,   44,   41,    0,  276,   44,  270,  288,   41,
   30,  282,   44,   59,   58,   59,   41,   40,  289,   44,
  274,   59,   43,   33,   45,   41,   40,   59,   44,   49,
   40,  285,   46,   43,   59,   45,  267,  268,  269,   94,
   95,   96,   97,   59,  275,  271,   61,   67,   58,   59,
  276,  123,   60,  288,   62,   33,  282,   40,   78,  285,
  291,   41,   40,  289,   44,   43,   86,   45,   41,   41,
   41,   44,   44,   44,  125,   19,  259,  260,  125,  123,
   58,   59,   33,  125,   59,   50,   51,   52,   32,   40,
   37,   59,   43,   41,   45,   42,   44,  123,   42,   41,
   47,   41,   44,   41,   44,   40,   44,   58,   59,   33,
   41,   41,   40,  123,   44,  125,   40,   41,   33,   43,
   40,   45,  155,   92,   93,   40,   40,  266,   43,   61,
   45,  267,  268,  269,   58,  100,  101,  102,  267,  268,
  269,   98,   99,   58,  164,  123,  265,  125,   59,  169,
   41,   37,   41,   46,  258,   41,   42,   43,   44,   45,
   37,   47,   41,   45,   41,   42,   43,   44,   45,   40,
   47,   45,  123,   59,   60,   61,   62,  197,  198,  125,
  200,   44,   59,   60,   61,   62,   37,   44,   44,   61,
   41,   42,   43,   44,   45,   37,   47,   41,   46,   41,
   42,   43,   44,   45,  148,   47,  258,  267,   59,   60,
   61,   62,  268,   46,  267,  269,   59,   59,   60,   40,
   62,  270,   40,  267,  268,  269,  270,  271,  266,  273,
   40,  275,  276,  265,  266,   59,  280,  281,  282,  283,
  284,  266,  286,  287,  268,  289,   59,  291,  292,  265,
  266,   41,   41,  261,   44,  263,  123,  267,  268,  269,
  270,  271,  206,  273,   41,  275,  276,   41,   59,   59,
  280,  281,  282,  283,  284,  271,  286,  287,  288,  289,
  276,  291,  292,  272,    0,  125,  282,  125,  125,  267,
  268,  269,  270,  289,  125,  273,  172,  275,   42,    8,
   15,  205,  280,  281,   22,  283,  284,   41,  286,  287,
   44,  206,   79,  291,  292,   79,  267,  268,  269,  270,
   41,   79,  273,   44,  275,   59,   60,   75,   62,  280,
  281,   41,  283,  284,   44,  286,  287,  157,   59,   90,
  291,  292,   89,  267,  268,  269,  270,   -1,   91,   59,
   -1,  275,  267,  268,  269,  270,   -1,  281,   -1,  283,
  275,   -1,  286,   -1,   -1,   -1,  281,  291,  283,   -1,
   -1,  286,   -1,  259,  260,  261,  291,  263,   41,  265,
  266,   44,  259,  260,  261,   -1,  263,   -1,  265,  266,
   -1,   -1,   -1,   -1,   -1,   -1,   59,   60,   -1,   62,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  259,  260,
  261,   -1,  263,   41,  265,  266,   44,  259,  260,  261,
   -1,  263,   -1,  265,  266,   41,   -1,   43,   44,   45,
   -1,   59,   60,   41,   62,   43,   44,   45,   -1,   -1,
   -1,   -1,   -1,   59,   60,   41,   62,   43,   44,   45,
   -1,   59,   60,   41,   62,   -1,   44,   -1,   -1,   -1,
  182,   -1,   41,   59,   60,   44,   62,   -1,   -1,  259,
  260,   59,   60,   -1,   62,  265,  266,   -1,   -1,  201,
   59,   60,   -1,   62,   -1,   -1,   -1,   -1,   -1,    6,
   -1,  213,   -1,   10,  216,   -1,  218,   14,   -1,   -1,
   -1,   -1,  224,   -1,   21,   -1,   -1,   -1,   -1,   26,
   -1,   -1,   29,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  259,  260,  261,   -1,  263,
   -1,  265,  266,   -1,   -1,   -1,   53,   -1,  259,  260,
   50,   51,   52,   -1,  265,  266,   -1,   -1,   -1,  259,
  260,   68,   -1,   -1,   -1,  265,  266,   74,   75,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   87,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   89,
   -1,   91,   92,   93,   94,   95,   96,   97,   98,   99,
  100,  101,  102,   -1,   -1,   -1,  259,  260,  261,   -1,
  263,   -1,  265,  266,   -1,   -1,   -1,   -1,   -1,   -1,
  127,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  259,  260,  261,   -1,  263,   -1,  265,  266,   -1,
  157,   -1,   -1,  259,  260,  261,   -1,  263,  165,  265,
  266,  259,  260,  261,   -1,  263,   -1,  265,  266,   -1,
   -1,   -1,   -1,  259,  260,  261,  183,  263,   -1,  265,
  266,  259,  260,  261,   -1,  263,  193,  265,  266,   -1,
  259,  260,  261,   -1,  263,   -1,  265,  266,
};
}
final static short YYFINAL=1;
final static short YYMAXTOKEN=292;
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
"GLOBAL","IF","INPUT","INT","LENGTH","PRINT","PROPERTY","RANDOM","RETURN",
"RULE","STRING","THEN","TRUE","WHILE",
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
"ruleDeclarationList : rule",
"ruleDeclarationList : ruleDeclarationList rule",
"rule : RULE '(' expression ')' INFERS statement",
"rule : RULE identifier '(' expression ')' INFERS statement",
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
"functionExpression : LENGTH '(' expression ')'",
"functionExpression : RANDOM '(' floatList ')'",
"functionExpression : RANDOM '(' integerList ')'",
"functionExpression : RANDOM '(' stringList ')'",
"functionExpression : INPUT '(' ')'",
"primaryExpression : constant",
"primaryExpression : '(' expression ')'",
"primaryExpression : identifier",
"primaryExpression : identifier '.' identifier",
"primaryExpression : identifier '.' identifier '.' identifier",
"primaryExpression : ':' identifier '.' identifier",
"primaryExpression : ':' identifier '.' identifier '.' identifier",
"argumentExpressionList : expression",
"argumentExpressionList : argumentExpressionList ',' expression",
"statement : expressionStatement",
"statement : compoundStatement",
"statement : selectionStatement",
"statement : iterationStatement",
"statement : endStatement",
"statement : printStatement",
"statement : returnStatement",
"expressionStatement : expression ';'",
"expressionStatement : ';'",
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

//#line 443 "../../../../../src/org/geppetto/parser/parser.y"

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
//#line 698 "Parser.java"
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
//#line 74 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<VariableDeclaration> variables = (ArrayList<VariableDeclaration>) val_peek(4).obj;
                                                      ArrayList<PropertyDefinition> properties = (ArrayList<PropertyDefinition>) val_peek(3).obj;
                                                      ArrayList<Entity> entities =  (ArrayList<Entity>) val_peek(2).obj;
                                                      ArrayList<Rule> rules = (ArrayList<Rule>) val_peek(1).obj;
                                                      ArrayList<FunctionDefinition> functions = (ArrayList<FunctionDefinition>) val_peek(0).obj;  
                                                      GeppettoProgram.createInstance(variables, properties, entities, rules, functions); }
break;
case 2:
//#line 83 "../../../../../src/org/geppetto/parser/parser.y"
{ variables = (ArrayList<VariableDeclaration>) val_peek(1).obj;
                                                      if (variables == null)
                                                         variables = new ArrayList<VariableDeclaration>();
                                                      variables.add((VariableDeclaration) val_peek(0).obj); 
                                                      yyval.obj = variables; }
break;
case 3:
//#line 88 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new ArrayList<VariableDeclaration>(); }
break;
case 4:
//#line 92 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new VariableDeclaration(symbolTable.get(val_peek(3).ival), (DataType) val_peek(4).obj, (Value) val_peek(1).obj); }
break;
case 5:
//#line 96 "../../../../../src/org/geppetto/parser/parser.y"
{ propertyDefinitions = new ArrayList<PropertyDefinition>();
                                                      propertyDefinitions.add((PropertyDefinition) val_peek(0).obj); 
                                                      yyval.obj = propertyDefinitions; }
break;
case 6:
//#line 99 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<PropertyDefinition> propertyDefs = (ArrayList<PropertyDefinition>) val_peek(1).obj;
                                                      propertyDefs.add((PropertyDefinition) val_peek(0).obj); 
                                                      yyval.obj = propertyDefs; }
break;
case 7:
//#line 105 "../../../../../src/org/geppetto/parser/parser.y"
{ PropertyDefinition propertyDef = new PropertyDefinition(symbolTable.get(val_peek(4).ival));
                                                      propertyDef.addAttributeDefinitions((ArrayList<AttributeDefinition>) val_peek(2).obj);
                                                      yyval.obj = propertyDef; }
break;
case 8:
//#line 111 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<AttributeDefinition> attributeDefs = new ArrayList<AttributeDefinition>();
                                                      attributeDefs.add((AttributeDefinition) val_peek(0).obj); 
                                                      yyval.obj = attributeDefs; }
break;
case 9:
//#line 114 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<AttributeDefinition> attributeDefs = (ArrayList<AttributeDefinition>) val_peek(2).obj;
                                                      attributeDefs.add((AttributeDefinition) val_peek(0).obj); 
                                                      yyval.obj = attributeDefs; }
break;
case 10:
//#line 120 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new AttributeDefinition((DataType)val_peek(1).obj, symbolTable.get(val_peek(0).ival)); }
break;
case 11:
//#line 121 "../../../../../src/org/geppetto/parser/parser.y"
{ AttributeDefinition attributeDef = new AttributeDefinition((DataType)val_peek(4).obj, symbolTable.get(val_peek(3).ival));
                                                      attributeDef.setConstraint((AttributeConstraint) val_peek(1).obj); 
                                                      yyval.obj = attributeDef; }
break;
case 12:
//#line 127 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<Entity> entities = new ArrayList<Entity>();  
                                                      entities.add((Entity) val_peek(0).obj); 
                                                      yyval.obj = entities; }
break;
case 13:
//#line 130 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<Entity> entities = (ArrayList<Entity>) val_peek(1).obj;  
                                                      entities.add((Entity) val_peek(0).obj); 
                                                      yyval.obj = entities; }
break;
case 14:
//#line 136 "../../../../../src/org/geppetto/parser/parser.y"
{ Entity entity = new Entity(symbolTable.get(val_peek(4).ival));
                                                      entity.addProperties((List<Property>) val_peek(2).obj);
                                                      yyval.obj = entity; }
break;
case 15:
//#line 142 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<Rule> rules = new ArrayList<Rule>();  
                                                      rules.add((Rule) val_peek(0).obj); 
                                                      yyval.obj = rules; }
break;
case 16:
//#line 145 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<Rule> rules = (ArrayList<Rule>) val_peek(1).obj;
                                                      rules.add((Rule) val_peek(0).obj);
                                                      yyval.obj = rules; }
break;
case 17:
//#line 151 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new Rule((Expression) val_peek(3).obj, (Statement) val_peek(0).obj); }
break;
case 18:
//#line 152 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new Rule(symbolTable.get(val_peek(5).ival), (Expression) val_peek(3).obj, (Statement) val_peek(0).obj); }
break;
case 19:
//#line 156 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<FunctionDefinition> functions = (ArrayList<FunctionDefinition>) val_peek(1).obj;
                                                      if (functions == null)
                                                          functions = new ArrayList<FunctionDefinition>();
                                                      functions.add((FunctionDefinition) val_peek(0).obj); 
                                                      yyval.obj = functions; }
break;
case 20:
//#line 161 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new ArrayList<FunctionDefinition>(); }
break;
case 21:
//#line 166 "../../../../../src/org/geppetto/parser/parser.y"
{ String name = symbolTable.get(val_peek(4).ival);
                                                      DataType type = (DataType) val_peek(5).obj;
                                                      ArrayList<ArgumentDeclaration> arguments = (ArrayList<ArgumentDeclaration>) val_peek(2).obj;
                                                      CompoundStatement compoundStatement = (CompoundStatement) val_peek(0).obj; 
                                                      yyval.obj = new FunctionDefinition(name, type, arguments, compoundStatement); }
break;
case 22:
//#line 174 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<ArgumentDeclaration> arguments = new ArrayList<ArgumentDeclaration>();
                                                      arguments.add((ArgumentDeclaration) val_peek(0).obj); 
                                                      yyval.obj = arguments; }
break;
case 23:
//#line 177 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<ArgumentDeclaration> arguments = (ArrayList<ArgumentDeclaration>) val_peek(2).obj;
                                                      arguments.add((ArgumentDeclaration) val_peek(0).obj); 
                                                      yyval.obj = arguments; }
break;
case 24:
//#line 180 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new ArrayList<ArgumentDeclaration>(); }
break;
case 25:
//#line 184 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new ArgumentDeclaration(symbolTable.get(val_peek(0).ival), (DataType) val_peek(1).obj); }
break;
case 26:
//#line 188 "../../../../../src/org/geppetto/parser/parser.y"
{ debug("** IDENTIFIER: ID: " + val_peek(0).ival + "; symb table entry: " + symbolTable.get(val_peek(0).ival)); 
                                                      yyval.ival = val_peek(0).ival; }
break;
case 27:
//#line 193 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = DataType.INT; }
break;
case 28:
//#line 194 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = DataType.FLOAT; }
break;
case 29:
//#line 195 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = DataType.STRING; }
break;
case 30:
//#line 196 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = DataType.BOOLEAN; }
break;
case 31:
//#line 200 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new AttributeConstraintStringSet((Set<String>) val_peek(0).obj); }
break;
case 32:
//#line 201 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new AttributeConstraintIntegerSet((Set<Integer>) val_peek(0).obj); }
break;
case 33:
//#line 202 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new AttributeConstraintIntegerRange((ArrayList<Integer>)val_peek(0).obj); }
break;
case 34:
//#line 203 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new AttributeConstraintFloatSet((Set<Float>)val_peek(0).obj); }
break;
case 35:
//#line 204 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new AttributeConstraintFloatRange((ArrayList<Float>)val_peek(0).obj); }
break;
case 37:
//#line 209 "../../../../../src/org/geppetto/parser/parser.y"
{ HashSet<String> stringSet = new HashSet<String>(); 
                                                      stringSet.add(symbolTable.get(val_peek(0).ival)); 
                                                      yyval.obj = stringSet; }
break;
case 38:
//#line 212 "../../../../../src/org/geppetto/parser/parser.y"
{ HashSet<String> stringSet = (HashSet<String>) val_peek(2).obj;
                                                      stringSet.add(symbolTable.get(val_peek(0).ival)); 
                                                      yyval.obj = stringSet; }
break;
case 39:
//#line 218 "../../../../../src/org/geppetto/parser/parser.y"
{ HashSet<Integer> intSet = new HashSet<Integer>(); 
                                                      intSet.add(new Integer(val_peek(0).ival)); 
                                                      yyval.obj = intSet; }
break;
case 40:
//#line 221 "../../../../../src/org/geppetto/parser/parser.y"
{ HashSet<Integer> intSet = (HashSet<Integer>) val_peek(2).obj; 
                                                      intSet.add(new Integer(val_peek(0).ival)); 
                                                      yyval.obj = intSet; }
break;
case 41:
//#line 227 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<Integer> intList = new ArrayList<Integer>();
                                                      intList.add(val_peek(2).ival);
                                                      intList.add(val_peek(0).ival);
                                                      yyval.obj = intList; }
break;
case 42:
//#line 234 "../../../../../src/org/geppetto/parser/parser.y"
{ HashSet<Float> floatSet = new HashSet<Float>(); 
                                                      floatSet.add(new Float(val_peek(0).dval)); 
                                                      yyval.obj = floatSet; }
break;
case 43:
//#line 237 "../../../../../src/org/geppetto/parser/parser.y"
{ HashSet<Float> floatSet = (HashSet<Float>) val_peek(2).obj; 
                                                      floatSet.add(new Float(val_peek(0).dval)); 
                                                      yyval.obj = floatSet; }
break;
case 44:
//#line 243 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<Float> floatList = new ArrayList<Float>();
                                                      floatList.add(new Float(val_peek(2).dval));
                                                      floatList.add(new Float(val_peek(1).dval));
                                                      yyval.obj = floatList; }
break;
case 45:
//#line 250 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<Property> properties = new ArrayList<Property>();
                                                      properties.add((Property) val_peek(0).obj); 
                                                      yyval.obj = properties;}
break;
case 46:
//#line 253 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<Property> properties = (ArrayList<Property>) val_peek(2).obj;
                                                      properties.add((Property) val_peek(0).obj); 
                                                      yyval.obj = properties; }
break;
case 47:
//#line 259 "../../../../../src/org/geppetto/parser/parser.y"
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
case 48:
//#line 273 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<AttributeInitializer> attributeInitializers = new ArrayList<AttributeInitializer>();
                                                      attributeInitializers.add((AttributeInitializer) val_peek(0).obj);
                                                      yyval.obj = attributeInitializers; }
break;
case 49:
//#line 276 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<AttributeInitializer> attributeInitiazers = (ArrayList<AttributeInitializer>) val_peek(2).obj;
                                                      attributeInitiazers.add((AttributeInitializer) val_peek(0).obj);
                                                      yyval.obj = attributeInitiazers; }
break;
case 50:
//#line 282 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new AttributeInitializer(symbolTable.get(val_peek(2).ival), (Value)val_peek(0).obj); }
break;
case 51:
//#line 286 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new Value(val_peek(0).ival); }
break;
case 52:
//#line 287 "../../../../../src/org/geppetto/parser/parser.y"
{ Float fval = new Float(val_peek(0).dval); yyval.obj = new Value(fval); }
break;
case 53:
//#line 288 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new Value(symbolTable.get(val_peek(0).ival)); }
break;
case 54:
//#line 289 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new Value(true); }
break;
case 55:
//#line 290 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new Value(false); }
break;
case 56:
//#line 294 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 57:
//#line 298 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 58:
//#line 299 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new BinaryExpression((Expression) val_peek(2).obj, Operator.ASSIGNMENT, (Expression) val_peek(0).obj); }
break;
case 59:
//#line 303 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 60:
//#line 304 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new BinaryExpression((Expression) val_peek(2).obj, Operator.LOGICAL_OR, (Expression) val_peek(0).obj); }
break;
case 61:
//#line 308 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 62:
//#line 309 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new BinaryExpression( (Expression) val_peek(2).obj,  Operator.LOGICAL_AND, (Expression) val_peek(0).obj); }
break;
case 63:
//#line 313 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 64:
//#line 314 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new BinaryExpression((Expression) val_peek(2).obj, Operator.EQUAL_TO, (Expression) val_peek(0).obj); }
break;
case 65:
//#line 315 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new BinaryExpression((Expression) val_peek(2).obj, Operator.NOT_EQUAL_TO, (Expression) val_peek(0).obj); }
break;
case 66:
//#line 319 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 67:
//#line 320 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new BinaryExpression((Expression) val_peek(2).obj, Operator.GREATER_THAN, (Expression) val_peek(0).obj); }
break;
case 68:
//#line 321 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new BinaryExpression((Expression) val_peek(2).obj, Operator.LESS_THAN, (Expression) val_peek(0).obj); }
break;
case 69:
//#line 322 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new BinaryExpression((Expression) val_peek(2).obj, Operator.GREATER_THAN_OR_EQUAL_TO, (Expression) val_peek(0).obj); }
break;
case 70:
//#line 323 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new BinaryExpression((Expression) val_peek(2).obj, Operator.LESS_THAN_OR_EQUAL_TO, (Expression) val_peek(0).obj); }
break;
case 71:
//#line 327 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 72:
//#line 328 "../../../../../src/org/geppetto/parser/parser.y"
{yyval.obj = new BinaryExpression((Expression) val_peek(2).obj, Operator.ADD, (Expression) val_peek(0).obj); }
break;
case 73:
//#line 329 "../../../../../src/org/geppetto/parser/parser.y"
{yyval.obj = new BinaryExpression((Expression) val_peek(2).obj, Operator.SUBTRACT, (Expression) val_peek(0).obj); }
break;
case 74:
//#line 333 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 75:
//#line 334 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new BinaryExpression((Expression) val_peek(2).obj, Operator.MULTIPLY, (Expression) val_peek(0).obj); }
break;
case 76:
//#line 335 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new BinaryExpression((Expression) val_peek(2).obj, Operator.DIVIDE, (Expression) val_peek(0).obj); }
break;
case 77:
//#line 336 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new BinaryExpression((Expression) val_peek(2).obj, Operator.MODULUS, (Expression) val_peek(0).obj); }
break;
case 78:
//#line 340 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 79:
//#line 341 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new UnaryExpression(Operator.UNARY_PLUS, (Expression) val_peek(1).obj); }
break;
case 80:
//#line 342 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new UnaryExpression(Operator.UNARY_MINUS, (Expression) val_peek(1).obj); }
break;
case 81:
//#line 343 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new UnaryExpression(Operator.UNARY_NEGATION, (Expression) val_peek(1).obj); }
break;
case 82:
//#line 347 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 83:
//#line 348 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new FunctionExpression(symbolTable.get(val_peek(2).ival), new ArrayList<Expression>()); }
break;
case 84:
//#line 349 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new FunctionExpression(symbolTable.get(val_peek(3).ival), (ArrayList<Expression>) val_peek(1).obj); }
break;
case 85:
//#line 350 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new LengthExpression((Expression) val_peek(1).obj); }
break;
case 86:
//#line 351 "../../../../../src/org/geppetto/parser/parser.y"
{ RandomExpression expression = new RandomExpression(); expression.setFloatSet((HashSet<Float>) val_peek(1).obj); yyval.obj = expression; }
break;
case 87:
//#line 352 "../../../../../src/org/geppetto/parser/parser.y"
{ RandomExpression expression = new RandomExpression(); expression.setIntegerSet((HashSet<Integer>) val_peek(1).obj); yyval.obj = expression; }
break;
case 88:
//#line 353 "../../../../../src/org/geppetto/parser/parser.y"
{ RandomExpression expression = new RandomExpression(); expression.setStringSet((HashSet<String>) val_peek(1).obj); yyval.obj = expression; }
break;
case 89:
//#line 354 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new InputExpression(); }
break;
case 90:
//#line 358 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new ConstantExpression((Value) val_peek(0).obj); }
break;
case 91:
//#line 359 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(1).obj; }
break;
case 92:
//#line 360 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new VariableExpression(symbolTable.get(val_peek(0).ival)); }
break;
case 93:
//#line 361 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new EntityExpression(symbolTable.get(val_peek(2).ival), symbolTable.get(val_peek(0).ival)); }
break;
case 94:
//#line 362 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new EntityExpression(symbolTable.get(val_peek(4).ival), symbolTable.get(val_peek(2).ival), symbolTable.get(val_peek(0).ival)); }
break;
case 95:
//#line 363 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new VariantExpression(symbolTable.get(val_peek(3).ival), symbolTable.get(val_peek(1).ival)); }
break;
case 96:
//#line 364 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new VariantExpression(symbolTable.get(val_peek(5).ival), symbolTable.get(val_peek(3).ival), symbolTable.get(val_peek(1).ival)); }
break;
case 97:
//#line 368 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<Expression> argList = new ArrayList<Expression>(); 
	                                                  argList.add((Expression) val_peek(0).obj); 
	                                                  yyval.obj = argList; }
break;
case 98:
//#line 371 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<Expression> argList = (ArrayList<Expression>) val_peek(2).obj; 
	                                                  argList.add((Expression) val_peek(0).obj); 
	                                                  yyval.obj = argList; }
break;
case 99:
//#line 377 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 100:
//#line 378 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 101:
//#line 379 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 102:
//#line 380 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 103:
//#line 381 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 104:
//#line 382 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 105:
//#line 383 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 106:
//#line 387 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new ExpressionStatement((Expression) val_peek(1).obj); }
break;
case 107:
//#line 388 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new NullStatement(); }
break;
case 108:
//#line 392 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new CompoundStatement((ArrayList<VariableDeclaration>) val_peek(2).obj, (ArrayList<Statement>) val_peek(1).obj); }
break;
case 109:
//#line 396 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<Statement> statementList = new ArrayList<Statement>(); 
                                                      statementList.add((Statement) val_peek(0).obj); 
                                                      yyval.obj = statementList; }
break;
case 110:
//#line 399 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<Statement> statementList = (ArrayList<Statement>) val_peek(1).obj; 
                                                      statementList.add((Statement) val_peek(0).obj); 
                                                      yyval.obj = statementList; }
break;
case 111:
//#line 405 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new SelectionStatement((Expression) val_peek(2).obj, (Statement) val_peek(0).obj); }
break;
case 112:
//#line 406 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new SelectionStatement((Expression) val_peek(4).obj, (Statement) val_peek(2).obj, (Statement) val_peek(0).obj); }
break;
case 113:
//#line 426 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new IterationStatement((Expression) val_peek(2).obj, (Statement) val_peek(0).obj); }
break;
case 114:
//#line 431 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new EndStatement(); }
break;
case 115:
//#line 435 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new PrintStatement((Expression) val_peek(2).obj); }
break;
case 116:
//#line 439 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new ReturnStatement((Expression) val_peek(1).obj); }
break;
//#line 1395 "Parser.java"
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
