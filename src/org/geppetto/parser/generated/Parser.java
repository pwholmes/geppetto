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
  import org.geppetto.domain.ArgumentDeclaration;
  import org.geppetto.domain.AttributeConstraint;
  import org.geppetto.domain.AttributeConstraintFloatRange;
  import org.geppetto.domain.AttributeConstraintFloatSet;
  import org.geppetto.domain.AttributeConstraintIntegerRange;
  import org.geppetto.domain.AttributeConstraintIntegerSet;
  import org.geppetto.domain.AttributeConstraintStringSet;
  import org.geppetto.domain.AttributeDefinition;
  import org.geppetto.domain.AttributeInitializer;
  import org.geppetto.domain.Entity;
  import org.geppetto.domain.FunctionDefinition;
  import org.geppetto.domain.GeppettoProgram;
  import org.geppetto.domain.Property;
  import org.geppetto.domain.PropertyDefinition;
  import org.geppetto.domain.Rule;
  import org.geppetto.domain.Value;
  import org.geppetto.domain.expression.BinaryExpression;
  import org.geppetto.domain.expression.ConstantExpression;
  import org.geppetto.domain.expression.Expression;
  import org.geppetto.domain.expression.FunctionExpression;
  import org.geppetto.domain.expression.Operator;
  import org.geppetto.domain.expression.StructureExpression;
  import org.geppetto.domain.expression.UnaryExpression;
  import org.geppetto.domain.expression.Variable;
  import org.geppetto.domain.expression.VariableType;
  import org.geppetto.domain.statement.CompoundStatement;
  import org.geppetto.domain.statement.EndStatement;
  import org.geppetto.domain.statement.ExpressionStatement;
  import org.geppetto.domain.statement.IterationStatement;
  import org.geppetto.domain.statement.NullStatement;
  import org.geppetto.domain.statement.PrintStatement;
  import org.geppetto.domain.statement.SelectionStatement;
  import org.geppetto.domain.statement.Statement;
//#line 57 "Parser.java"




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
   31,   31,   29,   36,   36,   37,   37,   39,   39,   40,
   40,   40,   41,   41,   41,   41,   41,   42,   42,   42,
   43,   43,   43,   43,   44,   44,   44,   44,   45,   45,
   45,   38,   38,   38,   38,   38,   38,   38,   38,   46,
   46,   47,   18,   48,   48,   32,   32,   49,   50,   50,
   33,   34,   35,
};
final static short yylen[] = {                            2,
    5,    2,    0,    5,    1,    2,    6,    1,    3,    2,
    5,    1,    2,    6,    2,    0,    6,    1,    3,    0,
    2,    1,    1,    1,    1,    1,    1,    1,    1,    1,
    1,    0,    1,    3,    1,    3,    3,    1,    3,    3,
    1,    3,    4,    1,    3,    3,    1,    1,    1,    1,
    1,    1,    2,    4,    1,    1,    1,    1,    1,    1,
    2,    1,    1,    1,    3,    1,    3,    1,    3,    1,
    3,    3,    1,    3,    3,    3,    3,    1,    3,    3,
    1,    3,    3,    3,    1,    2,    2,    2,    1,    3,
    4,    1,    3,    2,    4,    6,    1,    3,    5,    1,
    3,    1,    3,    1,    2,    1,    1,    7,    5,    7,
    5,    2,    5,
};
final static short yydefred[] = {                         3,
    0,    0,   26,   24,   23,    0,   25,    0,    2,    0,
    5,   22,    0,    0,    0,    6,   12,    0,    0,    0,
    0,    0,   13,   52,    0,    0,    0,    8,    0,   47,
   48,   49,   51,   50,    0,    0,    0,    0,    0,    0,
   92,    0,   63,    0,    0,    0,    0,    0,    0,    0,
   81,   85,    0,   53,    0,    0,    0,    0,    0,    0,
   41,    0,   89,   87,   86,   88,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   15,    4,    0,    7,    9,
    0,    0,    0,   93,    0,   90,  102,    0,  100,    0,
    0,    0,    0,    0,   62,    0,   56,    0,   54,   55,
   57,   58,   59,   60,  106,  107,    0,   65,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   82,   83,   84,
    0,    0,    0,   33,    0,    0,    0,   29,    0,   31,
    0,    0,   44,   42,   14,    0,   91,    0,    0,  112,
    0,    0,    0,  104,    0,   61,    0,    0,    0,   11,
    0,    0,    0,    0,   43,    0,    0,  101,   99,    0,
    0,    0,  103,  105,    0,    0,   18,   37,   40,   34,
   36,   39,   46,   45,   96,    0,    0,    0,   21,    0,
    0,  113,  111,  109,    0,   17,   19,    0,  108,  110,
};
final static short yydgoto[] = {                          1,
    2,    8,   15,   22,   53,    9,   26,   40,   41,   11,
   27,   28,  135,   17,   60,   86,  176,  107,  177,  136,
  137,  138,  139,  140,   61,  142,  143,   24,  108,  109,
  110,  111,  112,  113,  114,   43,   44,   63,   46,   47,
   48,   49,   50,   51,   52,   98,   99,  155,  115,  116,
};
final static short yysindex[] = {                         0,
    0, -172,    0,    0,    0, -266,    0, -246,    0, -266,
    0,    0,  -18, -266, -245,    0,    0,  -37, -231,  -80,
   58, -236,    0,    0, -217, -266,   -2,    0, -266,    0,
    0,    0,    0,    0,   58,   58,   58,   58, -266,   17,
    0, -204,    0, -159,   64, -203, -182,  -47,  -34,   33,
    0,    0, -231,    0,   63,  -12,   73, -231,   93,  -41,
    0,   99,    0,    0,    0,    0,   88,  -10, -266,   36,
   58,   58,   58,   58,   58,   58,   58,   58,   58,   58,
   58,   58,   58,   58, -266,    0,    0, -140,    0,    0,
 -266, -266,   83,    0, -266,    0,    0,   20,    0,  104,
   92,  117,  125,  126,    0,   36,    0,  108,    0,    0,
    0,    0,    0,    0,    0,    0, -203,    0, -182,  -47,
  -47,  -34,  -34,  -34,  -34,   33,   33,    0,    0,    0,
  128,  124,  130,    0,   51,  133,  141,    0,  142,    0,
  131,   42,    0,    0,    0,  149,    0,   58, -266,    0,
   58,   58,   58,    0,  -33,    0, -231,  -66,  -61,    0,
  -59,  -52,  -51, -217,    0, -266, -266,    0,    0,  184,
  185,  192,    0,    0, -266,   52,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  186,   36,   36,    0,  121,
 -231,    0,    0,    0,  -22,    0,    0,  -38,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   18,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   76,
    0,    0,    0,  -27,  146,   41,  340,  305,  202,  159,
    0,    0,  251,    0,    0,   61,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  102,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  138,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  111,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  188,    0,  415,  314,
  334,  256,  288,  391,  411,  168,  179,    0,    0,    0,
    0,  -36,  -28,    0,    0,  148,  153,    0,  154,    0,
    0,    0,    0,    0,    0,  137,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   65,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    1,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    3,  552,  -23,  247,
    0,  198,    0,  252,    0,    0,    0,   90,   75,    0,
    0,    0,    0,    0,  191,    0,  127,  268,  324, -100,
    0,    0,    0,    0,    0,  219,    0,  471,  221,  222,
   40,   -5,   50,  -17,    0,    0,  160,    0, -161,   98,
};
final static int YYTABLESIZE=727;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         38,
  106,   55,   92,   12,   10,  154,   35,   35,   81,   37,
   80,   36,   79,   64,   78,   38,   64,   16,   64,   65,
   66,   19,   38,   25,   39,  105,  195,   14,   14,   35,
   96,   64,   37,  106,   36,    6,  199,   21,   57,    3,
  106,   58,   29,  106,    4,  106,   21,   39,    5,   30,
   31,   32,    7,   70,  174,   85,   68,   33,  106,  106,
  147,   73,   69,  148,  128,  129,  130,   34,   38,   84,
  122,  123,  124,  125,   82,   35,   74,   75,   37,   83,
   36,   66,  165,   93,   66,  166,  193,  194,   35,  106,
   38,  173,  190,   39,  105,  191,   38,   35,    3,   66,
   37,   10,   36,    4,   10,   20,   71,    5,   20,    6,
   88,    7,   97,  120,  121,   39,   97,   97,   97,   97,
   97,   87,   97,  106,   72,  106,  132,  133,  134,  126,
  127,   89,   91,   95,   97,   97,   97,   97,   94,   94,
  183,  145,   94,   94,   94,   94,   94,   98,   94,  149,
  150,   98,   98,   98,   98,   98,  151,   98,  106,  175,
   94,   94,   94,   94,  152,  153,  156,  157,  158,   98,
   98,   98,   98,   95,  159,  160,  161,   95,   95,   95,
   95,   95,   89,   95,  162,  163,   89,   89,   89,   89,
   89,  164,   89,  175,  167,   95,   95,   95,   95,   78,
  178,   78,   78,   78,   89,   89,  179,   89,   80,  180,
   80,   80,   80,   76,  181,   77,  182,   78,   78,   79,
   78,   79,   79,   79,  186,  187,   80,   80,   67,   80,
   64,   67,  188,   30,   31,   32,   12,   79,   79,  101,
   79,   33,   73,  106,  192,   73,   67,  102,  104,  198,
    1,   34,  103,  104,   16,   90,   30,   31,   32,   12,
   73,   73,   32,   73,   33,  197,   23,  106,  106,  106,
  106,  106,   27,  106,   34,  106,  106,   28,   30,  196,
  106,  106,  144,  106,  106,  106,  106,  106,   16,   54,
  118,  117,  184,   16,  119,  200,   76,   16,   66,   76,
    0,   16,   30,   31,   32,   12,   66,  168,  101,    0,
   33,    0,    0,    0,   76,   76,  102,   76,    0,    0,
   34,  103,  104,    0,   30,   31,   32,   12,   77,    0,
    0,   77,   33,   97,   97,   97,   97,    0,   97,    0,
   97,   97,   34,    0,   42,   70,   77,   77,   70,   77,
    0,    0,    0,    0,   71,    0,    0,   71,   62,   94,
   94,   94,   94,   70,   94,    0,   94,   94,   98,   98,
   98,   98,   71,   98,   72,   98,   98,   72,    0,    0,
   68,    0,    0,   68,    0,    0,    0,    0,    0,    0,
    0,   97,   72,    0,   95,   95,   95,   95,   68,   95,
    0,   95,   95,   89,   89,   89,   89,    0,   89,    0,
   89,   89,    0,    0,    0,    0,   78,   78,   78,   78,
    0,   78,    0,   78,   78,   80,   80,   80,   80,    0,
   80,   74,   80,   80,   74,    0,   79,   79,   79,   79,
    0,   79,    0,   79,   79,   67,    0,    0,    0,   74,
   74,   75,   74,   67,   75,   69,    0,    0,   69,   73,
   73,   73,   73,    0,   73,    0,   73,   73,    0,   75,
   75,   97,   75,   69,  170,  171,  172,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   45,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   45,    0,    0,    0,    0,
    0,    0,    0,   76,   76,   76,   76,    0,   76,    0,
   76,   76,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   45,    0,
   45,    0,   45,    0,    0,   77,   77,   77,   77,    0,
   77,    0,   77,   77,    0,    0,    0,   13,    0,    0,
    0,   18,   70,   70,   70,   20,    0,    0,    0,   70,
   70,   71,   71,   71,    0,    0,   45,   56,   71,   71,
   59,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   67,   72,   72,   72,    0,    0,    0,   68,   72,   72,
    0,    0,    0,    0,   68,   68,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   45,    0,
  100,   45,   45,   45,    0,   45,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  131,    0,    0,    0,
    0,    0,  141,   59,    0,    0,  146,    0,   74,   74,
   74,   74,    0,   74,    0,   74,   74,   45,   45,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   75,   75,
   75,   75,   69,   75,    0,   75,   75,    0,    0,   69,
   69,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  169,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  141,  185,    0,
    0,    0,    0,    0,    0,    0,  189,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         33,
    0,   25,   44,  270,    2,  106,   40,   44,   43,   43,
   45,   45,   60,   41,   62,   44,   44,    0,   36,   37,
   38,   40,   33,   61,   58,   59,  188,  274,  274,   40,
   41,   59,   43,   33,   45,  282,  198,  283,   41,  271,
   40,   44,  123,   43,  276,   45,  283,   58,  280,  267,
  268,  269,  284,  258,  155,   53,   40,  275,   58,   59,
   41,  265,   46,   44,   82,   83,   84,  285,   33,   37,
   76,   77,   78,   79,   42,   40,  259,  260,   43,   47,
   45,   41,   41,  125,   44,   44,  187,  188,  125,  123,
   33,  125,   41,   58,   59,   44,  125,   40,  271,   59,
   43,   41,   45,  276,   44,   41,  266,  280,   44,  282,
  123,  284,   37,   74,   75,   58,   41,   42,   43,   44,
   45,   59,   47,  123,   61,  125,  267,  268,  269,   80,
   81,   59,   40,   46,   59,   60,   61,   62,   37,   41,
  164,   59,   41,   42,   43,   44,   45,   37,   47,   46,
   59,   41,   42,   43,   44,   45,   40,   47,  123,  157,
   59,   60,   61,   62,   40,   40,   59,   40,   45,   59,
   60,   61,   62,   37,   45,  125,   44,   41,   42,   43,
   44,   45,   37,   47,   44,   44,   41,   42,   43,   44,
   45,   61,   47,  191,   46,   59,   60,   61,   62,   41,
  267,   43,   44,   45,   59,   60,  268,   62,   41,  269,
   43,   44,   45,  261,  267,  263,  268,   59,   60,   41,
   62,   43,   44,   45,   41,   41,   59,   60,   41,   62,
  258,   44,   41,  267,  268,  269,  270,   59,   60,  273,
   62,  275,   41,  123,   59,   44,   59,  281,  287,  272,
    0,  285,  286,  287,    8,   58,  267,  268,  269,  270,
   59,   60,  125,   62,  275,  191,   15,  267,  268,  269,
  270,  271,  125,  273,  285,  275,  276,  125,  125,  190,
  280,  281,   92,  283,  284,  285,  286,  287,  271,   22,
   72,   71,  166,  276,   73,  198,   41,  280,  258,   44,
   -1,  284,  267,  268,  269,  270,  266,  148,  273,   -1,
  275,   -1,   -1,   -1,   59,   60,  281,   62,   -1,   -1,
  285,  286,  287,   -1,  267,  268,  269,  270,   41,   -1,
   -1,   44,  275,  258,  259,  260,  261,   -1,  263,   -1,
  265,  266,  285,   -1,   21,   41,   59,   60,   44,   62,
   -1,   -1,   -1,   -1,   41,   -1,   -1,   44,   35,  258,
  259,  260,  261,   59,  263,   -1,  265,  266,  258,  259,
  260,  261,   59,  263,   41,  265,  266,   44,   -1,   -1,
   41,   -1,   -1,   44,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   68,   59,   -1,  258,  259,  260,  261,   59,  263,
   -1,  265,  266,  258,  259,  260,  261,   -1,  263,   -1,
  265,  266,   -1,   -1,   -1,   -1,  258,  259,  260,  261,
   -1,  263,   -1,  265,  266,  258,  259,  260,  261,   -1,
  263,   41,  265,  266,   44,   -1,  258,  259,  260,  261,
   -1,  263,   -1,  265,  266,  258,   -1,   -1,   -1,   59,
   60,   41,   62,  266,   44,   41,   -1,   -1,   44,  258,
  259,  260,  261,   -1,  263,   -1,  265,  266,   -1,   59,
   60,  148,   62,   59,  151,  152,  153,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   21,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   35,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  258,  259,  260,  261,   -1,  263,   -1,
  265,  266,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   68,   -1,
   70,   -1,   72,   -1,   -1,  258,  259,  260,  261,   -1,
  263,   -1,  265,  266,   -1,   -1,   -1,    6,   -1,   -1,
   -1,   10,  258,  259,  260,   14,   -1,   -1,   -1,  265,
  266,  258,  259,  260,   -1,   -1,  106,   26,  265,  266,
   29,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   39,  258,  259,  260,   -1,   -1,   -1,  258,  265,  266,
   -1,   -1,   -1,   -1,  265,  266,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  148,   -1,
   69,  151,  152,  153,   -1,  155,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   85,   -1,   -1,   -1,
   -1,   -1,   91,   92,   -1,   -1,   95,   -1,  258,  259,
  260,  261,   -1,  263,   -1,  265,  266,  187,  188,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  258,  259,
  260,  261,  258,  263,   -1,  265,  266,   -1,   -1,  265,
  266,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  149,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  166,  167,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  175,
};
}
final static short YYFINAL=1;
final static short YYMAXTOKEN=288;
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
"INPUT","INT","PRINT","PROPERTY","RULE","STRING","TRUE","WHILE","IF","FOREACH",
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
};

//#line 437 "../../../../../src/org/geppetto/parser/parser.y"

/* Yylex is the lexer generated by JFlex */
private Yylex lexer;

/* Symbol table, for identifiers */
public ArrayList<String> symbolTable = new ArrayList<String>();

/* The top-level data type; figuratively, this the root of the AST, though it isn't really a tree. */ 
public GeppettoProgram geppettoProgram = null;

/* Need these as their own global variables because the definitions are needed to validate subsequent declarations.
   These variables are in essence the symbol table. */ 
public ArrayList<PropertyDefinition> propertyDefinitions = null;
public ArrayList<Variable> variables = null; 

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
//#line 670 "Parser.java"
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
//#line 67 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<Variable> variables = (ArrayList<Variable>) val_peek(4).obj;
                                                      ArrayList<PropertyDefinition> properties = (ArrayList<PropertyDefinition>) val_peek(3).obj;
                                                      ArrayList<Entity> entities =  (ArrayList<Entity>) val_peek(2).obj;
                                                      ArrayList<Rule> rules = (ArrayList<Rule>) val_peek(1).obj;
                                                      ArrayList<FunctionDefinition> functions = (ArrayList<FunctionDefinition>) val_peek(0).obj;  
                                                      GeppettoProgram.createInstance(variables, properties, entities, rules, functions); }
break;
case 2:
//#line 76 "../../../../../src/org/geppetto/parser/parser.y"
{ variables = (ArrayList<Variable>) val_peek(1).obj;
                                                      if (variables == null)
                                                         variables = new ArrayList<Variable>();
                                                      variables.add((Variable) val_peek(0).obj); 
                                                      yyval.obj = variables; }
break;
case 3:
//#line 81 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new ArrayList<Variable>(); }
break;
case 4:
//#line 85 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new Variable(symbolTable.get(val_peek(3).ival), (VariableType) val_peek(4).obj, (Value) val_peek(1).obj); }
break;
case 5:
//#line 89 "../../../../../src/org/geppetto/parser/parser.y"
{ propertyDefinitions = new ArrayList<PropertyDefinition>();
                                                      propertyDefinitions.add((PropertyDefinition) val_peek(0).obj); 
                                                      yyval.obj = propertyDefinitions; }
break;
case 6:
//#line 92 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<PropertyDefinition> propertyDefs = (ArrayList<PropertyDefinition>) val_peek(1).obj;
                                                      propertyDefs.add((PropertyDefinition) val_peek(0).obj); 
                                                      yyval.obj = propertyDefs; }
break;
case 7:
//#line 98 "../../../../../src/org/geppetto/parser/parser.y"
{ PropertyDefinition propertyDef = new PropertyDefinition(symbolTable.get(val_peek(4).ival));
                                                      propertyDef.addAttributeDefinitions((ArrayList<AttributeDefinition>) val_peek(2).obj);
                                                      yyval.obj = propertyDef; }
break;
case 8:
//#line 104 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<AttributeDefinition> attributeDefs = new ArrayList<AttributeDefinition>();
                                                      attributeDefs.add((AttributeDefinition) val_peek(0).obj); 
                                                      yyval.obj = attributeDefs; }
break;
case 9:
//#line 107 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<AttributeDefinition> attributeDefs = (ArrayList<AttributeDefinition>) val_peek(2).obj;
                                                      attributeDefs.add((AttributeDefinition) val_peek(0).obj); 
                                                      yyval.obj = attributeDefs; }
break;
case 10:
//#line 113 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new AttributeDefinition((VariableType)val_peek(1).obj, symbolTable.get(val_peek(0).ival)); }
break;
case 11:
//#line 114 "../../../../../src/org/geppetto/parser/parser.y"
{ AttributeDefinition attributeDef = new AttributeDefinition((VariableType)val_peek(4).obj, symbolTable.get(val_peek(3).ival));
                                                      attributeDef.setConstraint((AttributeConstraint) val_peek(1).obj); 
                                                      yyval.obj = attributeDef; }
break;
case 12:
//#line 120 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<Entity> entities = new ArrayList<Entity>();  
                                                      entities.add((Entity) val_peek(0).obj); 
                                                      yyval.obj = entities; }
break;
case 13:
//#line 123 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<Entity> entities = (ArrayList<Entity>) val_peek(1).obj;  
                                                      entities.add((Entity) val_peek(0).obj); 
                                                      yyval.obj = entities; }
break;
case 14:
//#line 129 "../../../../../src/org/geppetto/parser/parser.y"
{ Entity entity = new Entity(symbolTable.get(val_peek(4).ival));
                                                      entity.addProperties((List<Property>) val_peek(2).obj);
                                                      yyval.obj = entity; }
break;
case 15:
//#line 135 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<FunctionDefinition> functions = (ArrayList<FunctionDefinition>) val_peek(1).obj;
                                                      if (functions == null)
                                                          functions = new ArrayList<FunctionDefinition>();
                                                      functions.add((FunctionDefinition) val_peek(0).obj); 
                                                      yyval.obj = functions; }
break;
case 16:
//#line 140 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new ArrayList<FunctionDefinition>(); }
break;
case 17:
//#line 145 "../../../../../src/org/geppetto/parser/parser.y"
{ String name = symbolTable.get(val_peek(4).ival);
                                                      VariableType type = (VariableType) val_peek(5).obj;
                                                      ArrayList<ArgumentDeclaration> arguments = (ArrayList<ArgumentDeclaration>) val_peek(2).obj;
                                                      CompoundStatement compoundStatement = (CompoundStatement) val_peek(0).obj; 
                                                      yyval.obj = new FunctionDefinition(name, type, arguments, compoundStatement); }
break;
case 18:
//#line 153 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<ArgumentDeclaration> arguments = new ArrayList<ArgumentDeclaration>();
                                                      arguments.add((ArgumentDeclaration) val_peek(0).obj); 
                                                      yyval.obj = arguments; }
break;
case 19:
//#line 156 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<ArgumentDeclaration> arguments = (ArrayList<ArgumentDeclaration>) val_peek(2).obj;
                                                      arguments.add((ArgumentDeclaration) val_peek(0).obj); 
                                                      yyval.obj = arguments; }
break;
case 20:
//#line 159 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new ArrayList<ArgumentDeclaration>(); }
break;
case 21:
//#line 163 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new ArgumentDeclaration(symbolTable.get(val_peek(0).ival), (VariableType) val_peek(1).obj); }
break;
case 22:
//#line 167 "../../../../../src/org/geppetto/parser/parser.y"
{ debug("** IDENTIFIER: ID: " + val_peek(0).ival + "; symb table entry: " + symbolTable.get(val_peek(0).ival)); 
                                                      yyval.ival = val_peek(0).ival; }
break;
case 23:
//#line 172 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = VariableType.INT; }
break;
case 24:
//#line 173 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = VariableType.FLOAT; }
break;
case 25:
//#line 174 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = VariableType.STRING; }
break;
case 26:
//#line 175 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = VariableType.BOOLEAN; }
break;
case 27:
//#line 179 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new AttributeConstraintStringSet((Set<String>) val_peek(0).obj); }
break;
case 28:
//#line 180 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new AttributeConstraintIntegerSet((Set<Integer>) val_peek(0).obj); }
break;
case 29:
//#line 181 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new AttributeConstraintIntegerRange((ArrayList<Integer>)val_peek(0).obj); }
break;
case 30:
//#line 182 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new AttributeConstraintFloatSet((Set<Float>)val_peek(0).obj); }
break;
case 31:
//#line 183 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new AttributeConstraintFloatRange((ArrayList<Float>)val_peek(0).obj); }
break;
case 33:
//#line 188 "../../../../../src/org/geppetto/parser/parser.y"
{ HashSet<String> stringSet = new HashSet<String>(); 
                                                      stringSet.add(symbolTable.get(val_peek(0).ival)); 
                                                      yyval.obj = stringSet; }
break;
case 34:
//#line 191 "../../../../../src/org/geppetto/parser/parser.y"
{ HashSet<String> stringSet = (HashSet<String>) val_peek(2).obj;
                                                      stringSet.add(symbolTable.get(val_peek(0).ival)); 
                                                      yyval.obj = stringSet; }
break;
case 35:
//#line 197 "../../../../../src/org/geppetto/parser/parser.y"
{ HashSet<Integer> intSet = new HashSet<Integer>(); 
                                                      intSet.add(new Integer(val_peek(0).ival)); 
                                                      yyval.obj = intSet; }
break;
case 36:
//#line 200 "../../../../../src/org/geppetto/parser/parser.y"
{ HashSet<Integer> intSet = (HashSet<Integer>) val_peek(2).obj; 
                                                      intSet.add(new Integer(val_peek(0).ival)); 
                                                      yyval.obj = intSet; }
break;
case 37:
//#line 206 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<Integer> intList = new ArrayList<Integer>();
                                                      intList.add(val_peek(2).ival);
                                                      intList.add(val_peek(0).ival);
                                                      yyval.obj = intList; }
break;
case 38:
//#line 213 "../../../../../src/org/geppetto/parser/parser.y"
{ HashSet<Float> floatSet = new HashSet<Float>(); 
                                                      floatSet.add(new Float(val_peek(0).dval)); 
                                                      yyval.obj = floatSet; }
break;
case 39:
//#line 216 "../../../../../src/org/geppetto/parser/parser.y"
{ HashSet<Float> floatSet = (HashSet<Float>) val_peek(2).obj; 
                                                      floatSet.add(new Float(val_peek(0).dval)); 
                                                      yyval.obj = floatSet; }
break;
case 40:
//#line 222 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<Float> floatList = new ArrayList<Float>();
                                                      floatList.add(new Float(val_peek(2).dval));
                                                      floatList.add(new Float(val_peek(1).dval));
                                                      yyval.obj = floatList; }
break;
case 41:
//#line 229 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<Property> properties = new ArrayList<Property>();
                                                      properties.add((Property) val_peek(0).obj); 
                                                      yyval.obj = properties;}
break;
case 42:
//#line 232 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<Property> properties = (ArrayList<Property>) val_peek(2).obj;
                                                      properties.add((Property) val_peek(0).obj); 
                                                      yyval.obj = properties; }
break;
case 43:
//#line 238 "../../../../../src/org/geppetto/parser/parser.y"
{ String propertyName = symbolTable.get(val_peek(3).ival);
                                                      PropertyDefinition propertyDef = null;
                                                      for (PropertyDefinition def : propertyDefinitions) {
                                                          if (def.getName().equals(propertyName))
                                                              propertyDef = def;
                                                          }
                                                      if (propertyDef == null)
                                                          throw new IllegalArgumentException("Unknown property: " + propertyName);
                                                      Property property = new Property(propertyName, propertyDef); 
                                                      property.setAttributeValues((List<AttributeInitializer>)val_peek(1).obj); 
                                                      yyval.obj = property; }
break;
case 44:
//#line 252 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<AttributeInitializer> attributeInitializers = new ArrayList<AttributeInitializer>();
                                                      attributeInitializers.add((AttributeInitializer) val_peek(0).obj);
                                                      yyval.obj = attributeInitializers; }
break;
case 45:
//#line 255 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<AttributeInitializer> attributeInitiazers = (ArrayList<AttributeInitializer>) val_peek(2).obj;
                                                      attributeInitiazers.add((AttributeInitializer) val_peek(0).obj);
                                                      yyval.obj = attributeInitiazers; }
break;
case 46:
//#line 261 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new AttributeInitializer(symbolTable.get(val_peek(2).ival), (Value)val_peek(0).obj); }
break;
case 47:
//#line 265 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new Value(val_peek(0).ival); }
break;
case 48:
//#line 266 "../../../../../src/org/geppetto/parser/parser.y"
{ Float fval = new Float(val_peek(0).dval); yyval.obj = new Value(fval); }
break;
case 49:
//#line 267 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new Value(symbolTable.get(val_peek(0).ival)); }
break;
case 50:
//#line 268 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new Value(true); }
break;
case 51:
//#line 269 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new Value(false); }
break;
case 52:
//#line 273 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<Rule> rules = new ArrayList<Rule>();  
                                                      rules.add((Rule) val_peek(0).obj); 
                                                      yyval.obj = rules; }
break;
case 53:
//#line 276 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<Rule> rules = (ArrayList<Rule>) val_peek(1).obj;  
                                                      rules.add((Rule) val_peek(0).obj); 
                                                      yyval.obj = rules; }
break;
case 54:
//#line 282 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new Rule((Expression) val_peek(2).obj, (Statement) val_peek(0).obj); }
break;
case 55:
//#line 286 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 56:
//#line 287 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 57:
//#line 288 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 58:
//#line 289 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 59:
//#line 290 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 60:
//#line 291 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 61:
//#line 295 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new ExpressionStatement((Expression) val_peek(1).obj); }
break;
case 62:
//#line 296 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new NullStatement(); }
break;
case 63:
//#line 300 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 64:
//#line 304 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 65:
//#line 305 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new BinaryExpression((Expression) val_peek(2).obj, Operator.ASSIGNMENT, (Expression) val_peek(0).obj); }
break;
case 66:
//#line 309 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 67:
//#line 310 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new BinaryExpression((Expression) val_peek(2).obj, Operator.LOGICAL_OR, (Expression) val_peek(0).obj); }
break;
case 68:
//#line 314 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 69:
//#line 315 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new BinaryExpression( (Expression) val_peek(2).obj,  Operator.LOGICAL_AND, (Expression) val_peek(0).obj); }
break;
case 70:
//#line 319 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 71:
//#line 320 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new BinaryExpression((Expression) val_peek(2).obj, Operator.EQUAL_TO, (Expression) val_peek(0).obj); }
break;
case 72:
//#line 321 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new BinaryExpression((Expression) val_peek(2).obj, Operator.NOT_EQUAL_TO, (Expression) val_peek(0).obj); }
break;
case 73:
//#line 325 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 74:
//#line 326 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new BinaryExpression((Expression) val_peek(2).obj, Operator.GREATER_THAN, (Expression) val_peek(0).obj); }
break;
case 75:
//#line 327 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new BinaryExpression((Expression) val_peek(2).obj, Operator.LESS_THAN, (Expression) val_peek(0).obj); }
break;
case 76:
//#line 328 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new BinaryExpression((Expression) val_peek(2).obj, Operator.GREATER_THAN_OR_EQUAL_TO, (Expression) val_peek(0).obj); }
break;
case 77:
//#line 329 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new BinaryExpression((Expression) val_peek(2).obj, Operator.LESS_THAN_OR_EQUAL_TO, (Expression) val_peek(0).obj); }
break;
case 78:
//#line 333 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 79:
//#line 334 "../../../../../src/org/geppetto/parser/parser.y"
{yyval.obj = new BinaryExpression((Expression) val_peek(2).obj, Operator.ADD, (Expression) val_peek(0).obj); }
break;
case 80:
//#line 335 "../../../../../src/org/geppetto/parser/parser.y"
{yyval.obj = new BinaryExpression((Expression) val_peek(2).obj, Operator.SUBTRACT, (Expression) val_peek(0).obj); }
break;
case 81:
//#line 339 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 82:
//#line 340 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new BinaryExpression((Expression) val_peek(2).obj, Operator.MULTIPLY, (Expression) val_peek(0).obj); }
break;
case 83:
//#line 341 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new BinaryExpression((Expression) val_peek(2).obj, Operator.DIVIDE, (Expression) val_peek(0).obj); }
break;
case 84:
//#line 342 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new BinaryExpression((Expression) val_peek(2).obj, Operator.MODULUS, (Expression) val_peek(0).obj); }
break;
case 85:
//#line 346 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 86:
//#line 347 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new UnaryExpression(Operator.UNARY_PLUS, (Expression) val_peek(1).obj); }
break;
case 87:
//#line 348 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new UnaryExpression(Operator.UNARY_MINUS, (Expression) val_peek(1).obj); }
break;
case 88:
//#line 349 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new UnaryExpression(Operator.UNARY_NEGATION, (Expression) val_peek(1).obj); }
break;
case 89:
//#line 353 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 90:
//#line 354 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new FunctionExpression(symbolTable.get(val_peek(2).ival), null); }
break;
case 91:
//#line 355 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new FunctionExpression(symbolTable.get(val_peek(3).ival), (ArrayList<Expression>) val_peek(3).obj); }
break;
case 92:
//#line 359 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new ConstantExpression((Value) val_peek(0).obj); }
break;
case 93:
//#line 360 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(1).obj; }
break;
case 94:
//#line 361 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new Variable(symbolTable.get(val_peek(1).ival)); }
break;
case 95:
//#line 362 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new StructureExpression(symbolTable.get(val_peek(3).ival), symbolTable.get(val_peek(1).ival)); }
break;
case 96:
//#line 363 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new StructureExpression(symbolTable.get(val_peek(5).ival), symbolTable.get(val_peek(3).ival), symbolTable.get(val_peek(1).ival)); }
break;
case 97:
//#line 364 "../../../../../src/org/geppetto/parser/parser.y"
{ String name = symbolTable.get(val_peek(0).ival);
                                                      Variable var = null;
                                                      for (Variable v : variables) {
                                                          if (v.getName().equals(name)) {
                                                              var = v;
                                                              break;
                                                          }
                                                      }
                                                      if (var == null)
                                                          throw new IllegalArgumentException("Undeclared identifier: " + name);
                                                      yyval.obj = var; }
break;
case 98:
//#line 375 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new StructureExpression(symbolTable.get(val_peek(2).ival), symbolTable.get(val_peek(0).ival)); }
break;
case 99:
//#line 376 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new StructureExpression(symbolTable.get(val_peek(4).ival), symbolTable.get(val_peek(2).ival), symbolTable.get(val_peek(0).ival)); }
break;
case 100:
//#line 380 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<Expression> argList = new ArrayList<Expression>(); 
	                                                  argList.add((Expression) val_peek(0).obj); 
	                                                  yyval.obj = argList; }
break;
case 101:
//#line 383 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<Expression> argList = (ArrayList<Expression>) val_peek(2).obj; 
	                                                  argList.add((Expression) val_peek(0).obj); 
	                                                  yyval.obj = argList; }
break;
case 102:
//#line 392 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 103:
//#line 396 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new CompoundStatement((ArrayList<Statement>) val_peek(1).obj); }
break;
case 104:
//#line 400 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<Statement> statementList = new ArrayList<Statement>(); 
                                                      statementList.add((Statement) val_peek(0).obj); 
                                                      yyval.obj = statementList; }
break;
case 105:
//#line 403 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<Statement> statementList = (ArrayList<Statement>) val_peek(1).obj; 
                                                      statementList.add((Statement) val_peek(0).obj); 
                                                      yyval.obj = statementList; }
break;
case 106:
//#line 409 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 107:
//#line 410 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 108:
//#line 414 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new SelectionStatement((Expression) val_peek(4).obj, (Statement) val_peek(2).obj, (Statement) val_peek(0).obj); }
break;
case 109:
//#line 418 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new SelectionStatement((Expression) val_peek(2).obj, (Statement) val_peek(0).obj); }
break;
case 110:
//#line 419 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new SelectionStatement((Expression) val_peek(4).obj, (Statement) val_peek(2).obj, (Statement) val_peek(0).obj); }
break;
case 111:
//#line 423 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new IterationStatement((Expression) val_peek(2).obj, (Statement) val_peek(0).obj); }
break;
case 112:
//#line 428 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new EndStatement(); }
break;
case 113:
//#line 432 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new PrintStatement((Expression) val_peek(2).obj); }
break;
//#line 1365 "Parser.java"
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
