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
  import org.geppetto.domain.expression.Expression;
  import org.geppetto.domain.expression.FunctionExpression;
  import org.geppetto.domain.expression.Operator;
  import org.geppetto.domain.expression.StructureExpression;
  import org.geppetto.domain.expression.UnaryExpression;
  import org.geppetto.domain.expression.VariableExpression;
  import org.geppetto.domain.expression.VariableType;
  import org.geppetto.domain.statement.CompoundStatement;
  import org.geppetto.domain.statement.EndStatement;
  import org.geppetto.domain.statement.ExpressionStatement;
  import org.geppetto.domain.statement.IterationStatement;
  import org.geppetto.domain.statement.NullStatement;
  import org.geppetto.domain.statement.PrintStatement;
  import org.geppetto.domain.statement.ReturnStatement;
  import org.geppetto.domain.statement.SelectionStatement;
  import org.geppetto.domain.statement.Statement;
//#line 60 "Parser.java"




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
    9,    4,    4,   28,   30,   30,   30,   30,   30,   30,
   30,   31,   31,   29,   37,   37,   38,   38,   40,   40,
   41,   41,   41,   42,   42,   42,   42,   42,   43,   43,
   43,   44,   44,   44,   44,   45,   45,   45,   45,   46,
   46,   46,   39,   39,   39,   39,   39,   39,   39,   39,
   47,   47,   48,   18,   49,   49,   32,   32,   50,   51,
   51,   33,   34,   35,   36,
};
final static short yylen[] = {                            2,
    5,    2,    0,    5,    1,    2,    6,    1,    3,    2,
    5,    1,    2,    6,    2,    0,    6,    1,    3,    0,
    2,    1,    1,    1,    1,    1,    1,    1,    1,    1,
    1,    0,    1,    3,    1,    3,    3,    1,    3,    3,
    1,    3,    4,    1,    3,    3,    1,    1,    1,    1,
    1,    1,    2,    4,    1,    1,    1,    1,    1,    1,
    1,    2,    1,    1,    1,    3,    1,    3,    1,    3,
    1,    3,    3,    1,    3,    3,    3,    3,    1,    3,
    3,    1,    3,    3,    3,    1,    2,    2,    2,    1,
    3,    4,    1,    3,    2,    4,    6,    1,    3,    5,
    1,    3,    1,    3,    1,    2,    1,    1,    7,    5,
    7,    5,    2,    5,    3,
};
final static short yydefred[] = {                         3,
    0,    0,   26,   24,   23,    0,   25,    0,    2,    0,
    5,   22,    0,    0,    0,    6,   12,    0,    0,    0,
    0,    0,   13,   52,    0,    0,    0,    8,    0,   47,
   48,   49,   51,   50,    0,    0,    0,    0,    0,    0,
   93,    0,   64,    0,    0,    0,    0,    0,    0,    0,
   82,   86,    0,   53,    0,    0,    0,    0,    0,    0,
   41,    0,   90,   88,   87,   89,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   15,    4,    0,    7,    9,
    0,    0,    0,   94,    0,   91,  103,    0,  101,    0,
    0,    0,    0,    0,    0,   63,    0,   56,    0,   54,
   55,   57,   58,   59,   60,   61,  107,  108,    0,   66,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   83,
   84,   85,    0,    0,    0,   33,    0,    0,    0,   29,
    0,   31,    0,    0,   44,   42,   14,    0,   92,    0,
    0,  113,    0,    0,    0,    0,  105,    0,   62,    0,
    0,    0,   11,    0,    0,    0,    0,   43,    0,    0,
  102,  100,    0,  115,    0,    0,  104,  106,    0,    0,
   18,   37,   40,   34,   36,   39,   46,   45,   97,    0,
    0,    0,   21,    0,    0,  114,  112,  110,    0,   17,
   19,    0,  109,  111,
};
final static short yydgoto[] = {                          1,
    2,    8,   15,   22,   53,    9,   26,   40,   41,   11,
   27,   28,  137,   17,   60,   86,  180,  108,  181,  138,
  139,  140,  141,  142,   61,  144,  145,   24,  109,  110,
  111,  112,  113,  114,  115,  116,   43,   44,   63,   46,
   47,   48,   49,   50,   51,   52,   98,   99,  158,  117,
  118,
};
final static short yysindex[] = {                         0,
    0, -194,    0,    0,    0, -254,    0, -213,    0, -254,
    0,    0,   -7, -254, -253,    0,    0,  -25, -172,  -84,
   69, -239,    0,    0, -219, -254,   10,    0, -254,    0,
    0,    0,    0,    0,   69,   69,   69,   69, -254,  -31,
    0, -206,    0, -182,   11, -171, -180,   -5,   25,  -24,
    0,    0, -172,    0,   56,   -2,   64, -172,   99,  -40,
    0,  102,    0,    0,    0,    0,   98,   60, -254,   38,
   69,   69,   69,   69,   69,   69,   69,   69,   69,   69,
   69,   69,   69,   69, -254,    0,    0, -138,    0,    0,
 -254, -254,   87,    0, -254,    0,    0,   54,    0,  101,
   89,  109,   69,  114,  115,    0,   38,    0,   97,    0,
    0,    0,    0,    0,    0,    0,    0,    0, -171,    0,
 -180,   -5,   -5,   25,   25,   25,   25,  -24,  -24,    0,
    0,    0,  117,  125,  131,    0,   34,  116,  134,    0,
  135,    0,  123,   75,    0,    0,    0,  139,    0,   69,
 -254,    0,   69,  127,   69,   69,    0,  -33,    0, -172,
  -80,  -74,    0,  -81,  -72,  -63, -219,    0, -254, -254,
    0,    0,  155,    0,  170,  172,    0,    0, -254,   76,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  161,
   38,   38,    0,  100, -172,    0,    0,    0,  -58,    0,
    0,  -67,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   19,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   91,
    0,    0,    0,  -39,  165,  -17,   -6,  -27,  202,  185,
    0,    0,  222,    0,    0,   81,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  121,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  126,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  130,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   -1,    0,
  324,  273,  319,  234,  256,  299,  425,  393,  413,    0,
    0,    0,    0,  -38,  -36,    0,    0,  138,  141,    0,
  142,    0,    0,    0,    0,    0,    0,  156,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   96,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    1,    0,
    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    9,  541,  -22,  265,
    0,  221,    0,  268,    0,    0,    0,   86,  103,    0,
    0,    0,    0,    0,  199,    0,  132,  270,  373,  -85,
    0,    0,    0,    0,    0,    0,  230,    0,  574,  232,
  236,   36,  -13,   61,   -8,    0,    0,  160,    0, -155,
  110,
};
final static int YYTABLESIZE=766;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         38,
  107,   65,   55,   92,   65,   35,   35,   38,   68,   37,
   10,   36,   84,   71,   69,   12,   71,   82,   16,   65,
   14,  157,   83,   67,   39,  106,   67,   64,   65,   66,
   21,   71,   19,  107,   69,   25,  199,   69,   29,   68,
  107,   67,   68,  107,   21,  107,  203,   30,   31,   32,
   57,   70,   69,   58,   79,   33,   78,   68,  107,  107,
   14,   85,  124,  125,  126,  127,   34,   81,    6,   80,
   38,   72,  178,  130,  131,  132,    3,   35,   74,   75,
   37,    4,   36,   71,   93,    5,   35,    6,   38,  107,
    7,  177,   38,   73,  149,   39,  106,  150,    3,   35,
   96,   38,   37,    4,   36,  197,  198,    5,   35,  122,
  123,   37,    7,   36,   87,  168,  194,   39,  169,  195,
   88,   10,   89,  107,   10,  107,   39,   98,  134,  135,
  136,   98,   98,   98,   98,   98,   20,   98,   91,   20,
  128,  129,   94,   95,  187,  147,  151,  152,  153,   98,
   98,   98,   98,  155,  156,  159,  160,   95,  163,  164,
  107,   95,   95,   95,   95,   95,   99,   95,  179,  161,
   99,   99,   99,   99,   99,  162,   99,  165,  166,   95,
   95,   95,   95,  167,  170,  174,  182,  184,   99,   99,
   99,   99,   96,  183,  185,  190,   96,   96,   96,   96,
   96,   90,   96,  179,  186,   90,   90,   90,   90,   90,
  191,   90,  192,  202,   96,   96,   96,   96,   65,  196,
  105,    1,  107,   90,   90,   79,   90,   79,   79,   79,
   71,   71,   71,   30,   31,   32,   12,   71,   71,  101,
   67,   33,   74,   79,   79,   74,   79,  102,   67,  103,
   32,   69,   34,  104,  105,   76,   68,   77,   69,   69,
   74,   74,   27,   74,   68,   28,   30,  107,  107,  107,
  107,  107,   16,  107,   77,  107,  107,   77,   90,  200,
  107,  107,   23,  107,  107,  107,  107,  107,  107,   16,
  146,   54,   77,   77,   16,   77,   78,  201,   16,   78,
  188,  120,  119,   16,   30,   31,   32,   12,  121,  171,
  101,  204,   33,   72,   78,   78,   72,   78,  102,    0,
  103,    0,    0,   34,  104,  105,   30,   31,   32,   12,
    0,   72,    0,    0,   33,   30,   31,   32,   12,   75,
    0,    0,   75,   33,    0,   34,    0,    0,   98,   98,
   98,   98,    0,   98,   34,   98,   98,   75,   75,   73,
   75,    0,   73,    0,   70,    0,    0,   70,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   73,   95,   95,
   95,   95,   70,   95,    0,   95,   95,   99,   99,   99,
   99,    0,   99,   42,   99,   99,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   62,    0,    0,
    0,    0,    0,   96,   96,   96,   96,    0,   96,    0,
   96,   96,   90,   90,   90,   90,    0,   90,    0,   90,
   90,    0,    0,   81,    0,   81,   81,   81,    0,    0,
   97,    0,   79,   79,   79,   79,    0,   79,    0,   79,
   79,   81,   81,   80,   81,   80,   80,   80,    0,   74,
   74,   74,   74,    0,   74,   76,   74,   74,   76,    0,
    0,   80,   80,    0,   80,  154,    0,    0,    0,    0,
    0,    0,    0,   76,   76,    0,   76,    0,    0,    0,
    0,   77,   77,   77,   77,    0,   77,    0,   77,   77,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   78,   78,   78,   78,    0,   78,    0,
   78,   78,   97,    0,    0,  173,    0,  175,  176,    0,
   72,   72,   72,    0,    0,    0,    0,   72,   72,    0,
    0,    0,    0,    0,    0,    0,   13,    0,    0,    0,
   18,    0,    0,    0,   20,    0,   75,   75,   75,   75,
    0,   75,    0,   75,   75,    0,   56,    0,    0,   59,
    0,    0,    0,    0,    0,    0,   73,   73,   73,   67,
    0,   70,    0,   73,   73,    0,    0,    0,   70,   70,
    0,    0,    0,    0,   45,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   45,  100,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  133,    0,    0,    0,    0,
    0,  143,   59,    0,    0,  148,    0,    0,    0,    0,
    0,   45,    0,   45,    0,   45,    0,    0,    0,    0,
   81,   81,   81,   81,    0,   81,    0,   81,   81,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   80,   80,   80,   80,    0,   80,   45,   80,   80,    0,
   45,    0,   76,   76,   76,   76,    0,   76,    0,   76,
   76,  172,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  143,
  189,    0,    0,    0,    0,    0,    0,    0,    0,  193,
    0,    0,    0,   45,    0,    0,   45,    0,   45,   45,
    0,   45,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   45,   45,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         33,
    0,   41,   25,   44,   44,   44,   40,   44,   40,   43,
    2,   45,   37,   41,   46,  270,   44,   42,    0,   59,
  274,  107,   47,   41,   58,   59,   44,   36,   37,   38,
  284,   59,   40,   33,   41,   61,  192,   44,  123,   41,
   40,   59,   44,   43,  284,   45,  202,  267,  268,  269,
   41,  258,   59,   44,   60,  275,   62,   59,   58,   59,
  274,   53,   76,   77,   78,   79,  286,   43,  282,   45,
   33,   61,  158,   82,   83,   84,  271,   40,  259,  260,
   43,  276,   45,  266,  125,  280,  125,  282,  125,  123,
  285,  125,   33,  265,   41,   58,   59,   44,  271,   40,
   41,   33,   43,  276,   45,  191,  192,  280,   40,   74,
   75,   43,  285,   45,   59,   41,   41,   58,   44,   44,
  123,   41,   59,  123,   44,  125,   58,   37,  267,  268,
  269,   41,   42,   43,   44,   45,   41,   47,   40,   44,
   80,   81,   41,   46,  167,   59,   46,   59,   40,   59,
   60,   61,   62,   40,   40,   59,   40,   37,  125,   44,
  123,   41,   42,   43,   44,   45,   37,   47,  160,   45,
   41,   42,   43,   44,   45,   45,   47,   44,   44,   59,
   60,   61,   62,   61,   46,   59,  267,  269,   59,   60,
   61,   62,   37,  268,  267,   41,   41,   42,   43,   44,
   45,   37,   47,  195,  268,   41,   42,   43,   44,   45,
   41,   47,   41,  272,   59,   60,   61,   62,  258,   59,
  288,    0,  123,   59,   60,   41,   62,   43,   44,   45,
  258,  259,  260,  267,  268,  269,  270,  265,  266,  273,
  258,  275,   41,   59,   60,   44,   62,  281,  266,  283,
  125,  258,  286,  287,  288,  261,  258,  263,  265,  266,
   59,   60,  125,   62,  266,  125,  125,  267,  268,  269,
  270,  271,    8,  273,   41,  275,  276,   44,   58,  194,
  280,  281,   15,  283,  284,  285,  286,  287,  288,  271,
   92,   22,   59,   60,  276,   62,   41,  195,  280,   44,
  169,   72,   71,  285,  267,  268,  269,  270,   73,  150,
  273,  202,  275,   41,   59,   60,   44,   62,  281,   -1,
  283,   -1,   -1,  286,  287,  288,  267,  268,  269,  270,
   -1,   59,   -1,   -1,  275,  267,  268,  269,  270,   41,
   -1,   -1,   44,  275,   -1,  286,   -1,   -1,  258,  259,
  260,  261,   -1,  263,  286,  265,  266,   59,   60,   41,
   62,   -1,   44,   -1,   41,   -1,   -1,   44,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   59,  258,  259,
  260,  261,   59,  263,   -1,  265,  266,  258,  259,  260,
  261,   -1,  263,   21,  265,  266,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   35,   -1,   -1,
   -1,   -1,   -1,  258,  259,  260,  261,   -1,  263,   -1,
  265,  266,  258,  259,  260,  261,   -1,  263,   -1,  265,
  266,   -1,   -1,   41,   -1,   43,   44,   45,   -1,   -1,
   68,   -1,  258,  259,  260,  261,   -1,  263,   -1,  265,
  266,   59,   60,   41,   62,   43,   44,   45,   -1,  258,
  259,  260,  261,   -1,  263,   41,  265,  266,   44,   -1,
   -1,   59,   60,   -1,   62,  103,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   59,   60,   -1,   62,   -1,   -1,   -1,
   -1,  258,  259,  260,  261,   -1,  263,   -1,  265,  266,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  258,  259,  260,  261,   -1,  263,   -1,
  265,  266,  150,   -1,   -1,  153,   -1,  155,  156,   -1,
  258,  259,  260,   -1,   -1,   -1,   -1,  265,  266,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,    6,   -1,   -1,   -1,
   10,   -1,   -1,   -1,   14,   -1,  258,  259,  260,  261,
   -1,  263,   -1,  265,  266,   -1,   26,   -1,   -1,   29,
   -1,   -1,   -1,   -1,   -1,   -1,  258,  259,  260,   39,
   -1,  258,   -1,  265,  266,   -1,   -1,   -1,  265,  266,
   -1,   -1,   -1,   -1,   21,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   35,   69,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   85,   -1,   -1,   -1,   -1,
   -1,   91,   92,   -1,   -1,   95,   -1,   -1,   -1,   -1,
   -1,   68,   -1,   70,   -1,   72,   -1,   -1,   -1,   -1,
  258,  259,  260,  261,   -1,  263,   -1,  265,  266,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  258,  259,  260,  261,   -1,  263,  103,  265,  266,   -1,
  107,   -1,  258,  259,  260,  261,   -1,  263,   -1,  265,
  266,  151,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  169,
  170,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  179,
   -1,   -1,   -1,  150,   -1,   -1,  153,   -1,  155,  156,
   -1,  158,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  191,  192,
};
}
final static short YYFINAL=1;
final static short YYMAXTOKEN=289;
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
"IDENTIFIER","BOOLEAN","ELSE","END","ENTITY","FALSE","FLOAT","FOR","GLOBAL",
"INPUT","INT","PRINT","PROPERTY","RETURN","RULE","STRING","TRUE","WHILE","IF",
"FOREACH",
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
"rule : RULE expression INFERS statement",
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
"primaryExpression : ':' identifier",
"primaryExpression : ':' identifier '.' identifier",
"primaryExpression : ':' identifier '.' identifier '.' identifier",
"primaryExpression : identifier",
"primaryExpression : identifier '.' identifier",
"primaryExpression : identifier '.' identifier '.' identifier",
"argumentExpressionList : argumentExpression",
"argumentExpressionList : argumentExpressionList ',' argumentExpression",
"argumentExpression : expression",
"compoundStatement : '{' statementList '}'",
"statementList : statement",
"statementList : statementList statement",
"selectionStatement : matchedStatement",
"selectionStatement : openStatement",
"matchedStatement : IF '(' expression ')' matchedStatement ELSE matchedStatement",
"openStatement : IF '(' expression ')' statement",
"openStatement : IF '(' expression ')' matchedStatement ELSE openStatement",
"iterationStatement : WHILE '(' expression ')' statement",
"endStatement : END ';'",
"printStatement : PRINT '(' expression ')' ';'",
"returnStatement : RETURN expression ';'",
};

//#line 434 "../../../../../src/org/geppetto/parser/parser.y"

/* Yylex is the lexer generated by JFlex */
private Yylex lexer;

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
       We supply our own constructor so we can give it whatever parameters we want. 
       Here we pass it the Reader that supplies the input we're going to parse, a 
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
//#line 690 "Parser.java"
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
//#line 70 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<VariableDeclaration> variables = (ArrayList<VariableDeclaration>) val_peek(4).obj;
                                                      ArrayList<PropertyDefinition> properties = (ArrayList<PropertyDefinition>) val_peek(3).obj;
                                                      ArrayList<Entity> entities =  (ArrayList<Entity>) val_peek(2).obj;
                                                      ArrayList<Rule> rules = (ArrayList<Rule>) val_peek(1).obj;
                                                      ArrayList<FunctionDefinition> functions = (ArrayList<FunctionDefinition>) val_peek(0).obj;  
                                                      GeppettoProgram.createInstance(variables, properties, entities, rules, functions); }
break;
case 2:
//#line 79 "../../../../../src/org/geppetto/parser/parser.y"
{ variables = (ArrayList<VariableDeclaration>) val_peek(1).obj;
                                                      if (variables == null)
                                                         variables = new ArrayList<VariableDeclaration>();
                                                      variables.add((VariableDeclaration) val_peek(0).obj); 
                                                      yyval.obj = variables; }
break;
case 3:
//#line 84 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new ArrayList<VariableDeclaration>(); }
break;
case 4:
//#line 88 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new VariableDeclaration(symbolTable.get(val_peek(3).ival), (VariableType) val_peek(4).obj, (Value) val_peek(1).obj); }
break;
case 5:
//#line 92 "../../../../../src/org/geppetto/parser/parser.y"
{ propertyDefinitions = new ArrayList<PropertyDefinition>();
                                                      propertyDefinitions.add((PropertyDefinition) val_peek(0).obj); 
                                                      yyval.obj = propertyDefinitions; }
break;
case 6:
//#line 95 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<PropertyDefinition> propertyDefs = (ArrayList<PropertyDefinition>) val_peek(1).obj;
                                                      propertyDefs.add((PropertyDefinition) val_peek(0).obj); 
                                                      yyval.obj = propertyDefs; }
break;
case 7:
//#line 101 "../../../../../src/org/geppetto/parser/parser.y"
{ PropertyDefinition propertyDef = new PropertyDefinition(symbolTable.get(val_peek(4).ival));
                                                      propertyDef.addAttributeDefinitions((ArrayList<AttributeDefinition>) val_peek(2).obj);
                                                      yyval.obj = propertyDef; }
break;
case 8:
//#line 107 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<AttributeDefinition> attributeDefs = new ArrayList<AttributeDefinition>();
                                                      attributeDefs.add((AttributeDefinition) val_peek(0).obj); 
                                                      yyval.obj = attributeDefs; }
break;
case 9:
//#line 110 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<AttributeDefinition> attributeDefs = (ArrayList<AttributeDefinition>) val_peek(2).obj;
                                                      attributeDefs.add((AttributeDefinition) val_peek(0).obj); 
                                                      yyval.obj = attributeDefs; }
break;
case 10:
//#line 116 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new AttributeDefinition((VariableType)val_peek(1).obj, symbolTable.get(val_peek(0).ival)); }
break;
case 11:
//#line 117 "../../../../../src/org/geppetto/parser/parser.y"
{ AttributeDefinition attributeDef = new AttributeDefinition((VariableType)val_peek(4).obj, symbolTable.get(val_peek(3).ival));
                                                      attributeDef.setConstraint((AttributeConstraint) val_peek(1).obj); 
                                                      yyval.obj = attributeDef; }
break;
case 12:
//#line 123 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<Entity> entities = new ArrayList<Entity>();  
                                                      entities.add((Entity) val_peek(0).obj); 
                                                      yyval.obj = entities; }
break;
case 13:
//#line 126 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<Entity> entities = (ArrayList<Entity>) val_peek(1).obj;  
                                                      entities.add((Entity) val_peek(0).obj); 
                                                      yyval.obj = entities; }
break;
case 14:
//#line 132 "../../../../../src/org/geppetto/parser/parser.y"
{ Entity entity = new Entity(symbolTable.get(val_peek(4).ival));
                                                      entity.addProperties((List<Property>) val_peek(2).obj);
                                                      yyval.obj = entity; }
break;
case 15:
//#line 138 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<FunctionDefinition> functions = (ArrayList<FunctionDefinition>) val_peek(1).obj;
                                                      if (functions == null)
                                                          functions = new ArrayList<FunctionDefinition>();
                                                      functions.add((FunctionDefinition) val_peek(0).obj); 
                                                      yyval.obj = functions; }
break;
case 16:
//#line 143 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new ArrayList<FunctionDefinition>(); }
break;
case 17:
//#line 148 "../../../../../src/org/geppetto/parser/parser.y"
{ String name = symbolTable.get(val_peek(4).ival);
                                                      VariableType type = (VariableType) val_peek(5).obj;
                                                      ArrayList<ArgumentDeclaration> arguments = (ArrayList<ArgumentDeclaration>) val_peek(2).obj;
                                                      CompoundStatement compoundStatement = (CompoundStatement) val_peek(0).obj; 
                                                      yyval.obj = new FunctionDefinition(name, type, arguments, compoundStatement); }
break;
case 18:
//#line 156 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<ArgumentDeclaration> arguments = new ArrayList<ArgumentDeclaration>();
                                                      arguments.add((ArgumentDeclaration) val_peek(0).obj); 
                                                      yyval.obj = arguments; }
break;
case 19:
//#line 159 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<ArgumentDeclaration> arguments = (ArrayList<ArgumentDeclaration>) val_peek(2).obj;
                                                      arguments.add((ArgumentDeclaration) val_peek(0).obj); 
                                                      yyval.obj = arguments; }
break;
case 20:
//#line 162 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new ArrayList<ArgumentDeclaration>(); }
break;
case 21:
//#line 166 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new ArgumentDeclaration(symbolTable.get(val_peek(0).ival), (VariableType) val_peek(1).obj); }
break;
case 22:
//#line 170 "../../../../../src/org/geppetto/parser/parser.y"
{ debug("** IDENTIFIER: ID: " + val_peek(0).ival + "; symb table entry: " + symbolTable.get(val_peek(0).ival)); 
                                                      yyval.ival = val_peek(0).ival; }
break;
case 23:
//#line 175 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = VariableType.INT; }
break;
case 24:
//#line 176 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = VariableType.FLOAT; }
break;
case 25:
//#line 177 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = VariableType.STRING; }
break;
case 26:
//#line 178 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = VariableType.BOOLEAN; }
break;
case 27:
//#line 182 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new AttributeConstraintStringSet((Set<String>) val_peek(0).obj); }
break;
case 28:
//#line 183 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new AttributeConstraintIntegerSet((Set<Integer>) val_peek(0).obj); }
break;
case 29:
//#line 184 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new AttributeConstraintIntegerRange((ArrayList<Integer>)val_peek(0).obj); }
break;
case 30:
//#line 185 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new AttributeConstraintFloatSet((Set<Float>)val_peek(0).obj); }
break;
case 31:
//#line 186 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new AttributeConstraintFloatRange((ArrayList<Float>)val_peek(0).obj); }
break;
case 33:
//#line 191 "../../../../../src/org/geppetto/parser/parser.y"
{ HashSet<String> stringSet = new HashSet<String>(); 
                                                      stringSet.add(symbolTable.get(val_peek(0).ival)); 
                                                      yyval.obj = stringSet; }
break;
case 34:
//#line 194 "../../../../../src/org/geppetto/parser/parser.y"
{ HashSet<String> stringSet = (HashSet<String>) val_peek(2).obj;
                                                      stringSet.add(symbolTable.get(val_peek(0).ival)); 
                                                      yyval.obj = stringSet; }
break;
case 35:
//#line 200 "../../../../../src/org/geppetto/parser/parser.y"
{ HashSet<Integer> intSet = new HashSet<Integer>(); 
                                                      intSet.add(new Integer(val_peek(0).ival)); 
                                                      yyval.obj = intSet; }
break;
case 36:
//#line 203 "../../../../../src/org/geppetto/parser/parser.y"
{ HashSet<Integer> intSet = (HashSet<Integer>) val_peek(2).obj; 
                                                      intSet.add(new Integer(val_peek(0).ival)); 
                                                      yyval.obj = intSet; }
break;
case 37:
//#line 209 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<Integer> intList = new ArrayList<Integer>();
                                                      intList.add(val_peek(2).ival);
                                                      intList.add(val_peek(0).ival);
                                                      yyval.obj = intList; }
break;
case 38:
//#line 216 "../../../../../src/org/geppetto/parser/parser.y"
{ HashSet<Float> floatSet = new HashSet<Float>(); 
                                                      floatSet.add(new Float(val_peek(0).dval)); 
                                                      yyval.obj = floatSet; }
break;
case 39:
//#line 219 "../../../../../src/org/geppetto/parser/parser.y"
{ HashSet<Float> floatSet = (HashSet<Float>) val_peek(2).obj; 
                                                      floatSet.add(new Float(val_peek(0).dval)); 
                                                      yyval.obj = floatSet; }
break;
case 40:
//#line 225 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<Float> floatList = new ArrayList<Float>();
                                                      floatList.add(new Float(val_peek(2).dval));
                                                      floatList.add(new Float(val_peek(1).dval));
                                                      yyval.obj = floatList; }
break;
case 41:
//#line 232 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<Property> properties = new ArrayList<Property>();
                                                      properties.add((Property) val_peek(0).obj); 
                                                      yyval.obj = properties;}
break;
case 42:
//#line 235 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<Property> properties = (ArrayList<Property>) val_peek(2).obj;
                                                      properties.add((Property) val_peek(0).obj); 
                                                      yyval.obj = properties; }
break;
case 43:
//#line 241 "../../../../../src/org/geppetto/parser/parser.y"
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
//#line 255 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<AttributeInitializer> attributeInitializers = new ArrayList<AttributeInitializer>();
                                                      attributeInitializers.add((AttributeInitializer) val_peek(0).obj);
                                                      yyval.obj = attributeInitializers; }
break;
case 45:
//#line 258 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<AttributeInitializer> attributeInitiazers = (ArrayList<AttributeInitializer>) val_peek(2).obj;
                                                      attributeInitiazers.add((AttributeInitializer) val_peek(0).obj);
                                                      yyval.obj = attributeInitiazers; }
break;
case 46:
//#line 264 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new AttributeInitializer(symbolTable.get(val_peek(2).ival), (Value)val_peek(0).obj); }
break;
case 47:
//#line 268 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new Value(val_peek(0).ival); }
break;
case 48:
//#line 269 "../../../../../src/org/geppetto/parser/parser.y"
{ Float fval = new Float(val_peek(0).dval); yyval.obj = new Value(fval); }
break;
case 49:
//#line 270 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new Value(symbolTable.get(val_peek(0).ival)); }
break;
case 50:
//#line 271 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new Value(true); }
break;
case 51:
//#line 272 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new Value(false); }
break;
case 52:
//#line 276 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<Rule> rules = new ArrayList<Rule>();  
                                                      rules.add((Rule) val_peek(0).obj); 
                                                      yyval.obj = rules; }
break;
case 53:
//#line 279 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<Rule> rules = (ArrayList<Rule>) val_peek(1).obj;  
                                                      rules.add((Rule) val_peek(0).obj); 
                                                      yyval.obj = rules; }
break;
case 54:
//#line 285 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new Rule((Expression) val_peek(2).obj, (Statement) val_peek(0).obj); }
break;
case 55:
//#line 289 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 56:
//#line 290 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 57:
//#line 291 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 58:
//#line 292 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 59:
//#line 293 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 60:
//#line 294 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 61:
//#line 295 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 62:
//#line 299 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new ExpressionStatement((Expression) val_peek(1).obj); }
break;
case 63:
//#line 300 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new NullStatement(); }
break;
case 64:
//#line 304 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 65:
//#line 308 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 66:
//#line 309 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new BinaryExpression((Expression) val_peek(2).obj, Operator.ASSIGNMENT, (Expression) val_peek(0).obj); }
break;
case 67:
//#line 313 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 68:
//#line 314 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new BinaryExpression((Expression) val_peek(2).obj, Operator.LOGICAL_OR, (Expression) val_peek(0).obj); }
break;
case 69:
//#line 318 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 70:
//#line 319 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new BinaryExpression( (Expression) val_peek(2).obj,  Operator.LOGICAL_AND, (Expression) val_peek(0).obj); }
break;
case 71:
//#line 323 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 72:
//#line 324 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new BinaryExpression((Expression) val_peek(2).obj, Operator.EQUAL_TO, (Expression) val_peek(0).obj); }
break;
case 73:
//#line 325 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new BinaryExpression((Expression) val_peek(2).obj, Operator.NOT_EQUAL_TO, (Expression) val_peek(0).obj); }
break;
case 74:
//#line 329 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 75:
//#line 330 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new BinaryExpression((Expression) val_peek(2).obj, Operator.GREATER_THAN, (Expression) val_peek(0).obj); }
break;
case 76:
//#line 331 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new BinaryExpression((Expression) val_peek(2).obj, Operator.LESS_THAN, (Expression) val_peek(0).obj); }
break;
case 77:
//#line 332 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new BinaryExpression((Expression) val_peek(2).obj, Operator.GREATER_THAN_OR_EQUAL_TO, (Expression) val_peek(0).obj); }
break;
case 78:
//#line 333 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new BinaryExpression((Expression) val_peek(2).obj, Operator.LESS_THAN_OR_EQUAL_TO, (Expression) val_peek(0).obj); }
break;
case 79:
//#line 337 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 80:
//#line 338 "../../../../../src/org/geppetto/parser/parser.y"
{yyval.obj = new BinaryExpression((Expression) val_peek(2).obj, Operator.ADD, (Expression) val_peek(0).obj); }
break;
case 81:
//#line 339 "../../../../../src/org/geppetto/parser/parser.y"
{yyval.obj = new BinaryExpression((Expression) val_peek(2).obj, Operator.SUBTRACT, (Expression) val_peek(0).obj); }
break;
case 82:
//#line 343 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 83:
//#line 344 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new BinaryExpression((Expression) val_peek(2).obj, Operator.MULTIPLY, (Expression) val_peek(0).obj); }
break;
case 84:
//#line 345 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new BinaryExpression((Expression) val_peek(2).obj, Operator.DIVIDE, (Expression) val_peek(0).obj); }
break;
case 85:
//#line 346 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new BinaryExpression((Expression) val_peek(2).obj, Operator.MODULUS, (Expression) val_peek(0).obj); }
break;
case 86:
//#line 350 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 87:
//#line 351 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new UnaryExpression(Operator.UNARY_PLUS, (Expression) val_peek(1).obj); }
break;
case 88:
//#line 352 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new UnaryExpression(Operator.UNARY_MINUS, (Expression) val_peek(1).obj); }
break;
case 89:
//#line 353 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new UnaryExpression(Operator.UNARY_NEGATION, (Expression) val_peek(1).obj); }
break;
case 90:
//#line 357 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 91:
//#line 358 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new FunctionExpression(symbolTable.get(val_peek(2).ival), null); }
break;
case 92:
//#line 359 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new FunctionExpression(symbolTable.get(val_peek(3).ival), (ArrayList<Expression>) val_peek(3).obj); }
break;
case 93:
//#line 363 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new ConstantExpression((Value) val_peek(0).obj); }
break;
case 94:
//#line 364 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(1).obj; }
break;
case 95:
//#line 365 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new VariableExpression(symbolTable.get(val_peek(1).ival)); }
break;
case 96:
//#line 366 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new StructureExpression(symbolTable.get(val_peek(3).ival), symbolTable.get(val_peek(1).ival)); }
break;
case 97:
//#line 367 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new StructureExpression(symbolTable.get(val_peek(5).ival), symbolTable.get(val_peek(3).ival), symbolTable.get(val_peek(1).ival)); }
break;
case 98:
//#line 368 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new VariableExpression(symbolTable.get(val_peek(0).ival)); }
break;
case 99:
//#line 369 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new StructureExpression(symbolTable.get(val_peek(2).ival), symbolTable.get(val_peek(0).ival)); }
break;
case 100:
//#line 370 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new StructureExpression(symbolTable.get(val_peek(4).ival), symbolTable.get(val_peek(2).ival), symbolTable.get(val_peek(0).ival)); }
break;
case 101:
//#line 374 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<Expression> argList = new ArrayList<Expression>(); 
	                                                  argList.add((Expression) val_peek(0).obj); 
	                                                  yyval.obj = argList; }
break;
case 102:
//#line 377 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<Expression> argList = (ArrayList<Expression>) val_peek(2).obj; 
	                                                  argList.add((Expression) val_peek(0).obj); 
	                                                  yyval.obj = argList; }
break;
case 103:
//#line 386 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 104:
//#line 390 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new CompoundStatement((ArrayList<Statement>) val_peek(1).obj); }
break;
case 105:
//#line 394 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<Statement> statementList = new ArrayList<Statement>(); 
                                                      statementList.add((Statement) val_peek(0).obj); 
                                                      yyval.obj = statementList; }
break;
case 106:
//#line 397 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<Statement> statementList = (ArrayList<Statement>) val_peek(1).obj; 
                                                      statementList.add((Statement) val_peek(0).obj); 
                                                      yyval.obj = statementList; }
break;
case 107:
//#line 403 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 108:
//#line 404 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 109:
//#line 408 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new SelectionStatement((Expression) val_peek(4).obj, (Statement) val_peek(2).obj, (Statement) val_peek(0).obj); }
break;
case 110:
//#line 412 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new SelectionStatement((Expression) val_peek(2).obj, (Statement) val_peek(0).obj); }
break;
case 111:
//#line 413 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new SelectionStatement((Expression) val_peek(4).obj, (Statement) val_peek(2).obj, (Statement) val_peek(0).obj); }
break;
case 112:
//#line 417 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new IterationStatement((Expression) val_peek(2).obj, (Statement) val_peek(0).obj); }
break;
case 113:
//#line 422 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new EndStatement(); }
break;
case 114:
//#line 426 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new PrintStatement((Expression) val_peek(2).obj); }
break;
case 115:
//#line 430 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new ReturnStatement((Expression) val_peek(1).obj); }
break;
//#line 1383 "Parser.java"
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
