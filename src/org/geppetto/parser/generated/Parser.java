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
  import org.geppetto.domain.Behavior;
  import org.geppetto.domain.Condition;
  import org.geppetto.domain.Entity;
  import org.geppetto.domain.FunctionDefinition;
  import org.geppetto.domain.GeppettoProgram;
  import org.geppetto.domain.Property;
  import org.geppetto.domain.PropertyDefinition;
  import org.geppetto.domain.Rule;
  import org.geppetto.domain.Value;
  import org.geppetto.domain.expression.AssignmentExpression;
  import org.geppetto.domain.expression.BinaryExpression;
  import org.geppetto.domain.expression.BooleanExpression;
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
    9,    4,    4,   28,   29,   30,   32,   32,   32,   32,
   32,   33,   33,   31,   37,   37,   38,   40,   40,   41,
   41,   42,   42,   42,   43,   43,   43,   43,   43,   44,
   44,   44,   45,   45,   45,   45,   46,   46,   46,   46,
   47,   47,   47,   39,   39,   39,   48,   48,   50,   49,
   49,   49,   18,   51,   51,   34,   35,   36,
};
final static short yylen[] = {                            2,
    5,    2,    0,    5,    1,    2,    6,    1,    3,    2,
    5,    1,    2,    6,    2,    0,    6,    1,    3,    0,
    2,    1,    1,    1,    1,    1,    1,    1,    1,    1,
    1,    0,    1,    3,    1,    3,    3,    1,    3,    3,
    1,    3,    4,    1,    3,    3,    1,    1,    1,    1,
    1,    1,    2,    4,    1,    1,    1,    1,    1,    1,
    1,    2,    1,    1,    1,    3,    1,    1,    3,    1,
    3,    1,    3,    3,    1,    3,    3,    3,    3,    1,
    3,    3,    1,    3,    3,    3,    1,    2,    2,    2,
    1,    3,    4,    1,    3,    5,    1,    3,    1,    1,
    1,    3,    3,    1,    2,    5,    5,    2,
};
final static short yydefred[] = {                         3,
    0,    0,   26,   24,   23,    0,   25,    0,    2,    0,
    5,   22,    0,    0,    0,    6,   12,    0,    0,    0,
    0,    0,   13,   52,    0,    0,    0,    8,    0,   47,
   48,   49,   51,   50,    0,    0,    0,    0,    0,  101,
    0,   55,   64,   65,    0,    0,    0,    0,    0,    0,
    0,   83,   87,   94,    0,   53,    0,    0,    0,    0,
    0,    0,   41,    0,   91,   89,   88,   90,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   15,    4,    0,    7,
    9,    0,    0,    0,  102,   92,   99,    0,   97,    0,
    0,    0,    0,   63,    0,   58,   54,    0,   56,   57,
   59,   60,   61,   66,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   84,   85,   86,    0,    0,    0,
   33,    0,    0,    0,   29,    0,   31,    0,    0,   44,
   42,   14,   93,    0,    0,  108,    0,    0,  104,    0,
   62,    0,    0,    0,   11,    0,    0,    0,    0,   43,
    0,   98,   96,    0,    0,  103,  105,    0,    0,   18,
   37,   40,   34,   36,   39,   46,   45,    0,    0,   21,
    0,    0,  107,  106,   17,   19,
};
final static short yydgoto[] = {                          1,
    2,    8,   15,   22,   55,    9,   26,   39,   40,   11,
   27,   28,  132,   17,   62,   87,  169,  106,  170,  133,
  134,  135,  136,  137,   63,  139,  140,   24,   41,  107,
  108,  109,  110,  111,  112,  113,   43,   44,   65,   46,
   47,   48,   49,   50,   51,   52,   53,   98,   54,   99,
  150,
};
final static short yysindex[] = {                         0,
    0, -221,    0,    0,    0, -259,    0, -234,    0, -259,
    0,    0,   -7, -259, -260,    0,    0,  -12, -220,  -86,
   -2, -216,    0,    0, -223, -259,  -26,    0, -259,    0,
    0,    0,    0,    0,   -2,   -2,   -2,   -2,    7,    0,
 -184,    0,    0,    0,   23, -177, -167,  -91,  -54,   37,
  112,    0,    0,    0, -220,    0,   41,  -15,   65, -220,
  118,  -40,    0,  115,    0,    0,    0,    0,  -11, -259,
  -24,   -2,   -2,   -2,   -2,   -2,   -2,   -2,   -2,   -2,
   -2,   -2,   -2,   -2,   -2, -259,    0,    0, -129,    0,
    0, -259, -259,  106,    0,    0,    0,   25,    0,  128,
  121,  145,  149,    0,  -24,    0,    0,  135,    0,    0,
    0,    0,    0,    0, -167,  -91,  -54,  -54,   37,   37,
   37,   37,  112,  112,    0,    0,    0,  156,  155,  158,
    0,   81,  168,  174,    0,  178,    0,  163,   47,    0,
    0,    0,    0,   -2, -259,    0,   -2,   -2,    0,  -33,
    0, -220,  -42,  -37,    0,  -41,  -38,  -30, -223,    0,
 -259,    0,    0,  198,  206,    0,    0, -259,   74,    0,
    0,    0,    0,    0,    0,    0,    0,  -24,  -24,    0,
  127, -220,    0,    0,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    1,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   34,    0,
    0,    0,    0,    0,   69,  -17,  189,   13,  -39,  111,
   82,    0,    0,    0,  255,    0,    0,   89,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  144,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   60,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  243,   24,  164,  173,  122,  131,
  142,  151,   91,  102,    0,    0,    0,    0,  -16,   -8,
    0,    0,  150,  161,    0,  166,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  116,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,    0,    0,    0,    0,    0,   15,  430,  -22,  252,
    0,  210,    0,  261,    0,    0,    0,  107,   98,    0,
    0,    0,    0,    0,  203,    0,  137,  279,    0,    0,
    4,  -92,    0,    0,    0,    0,  231,   40,  392,    0,
  232,  230,  123,   99,  134,  382,    0,    0,    0,  162,
    0,
};
final static int YYTABLESIZE=598;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         38,
   16,   72,   57,   93,   72,   80,   35,   79,   38,   37,
   12,   36,  149,   14,   59,   35,   10,   60,   37,   72,
   36,   38,   21,   67,   42,  104,   67,   35,   35,   96,
   38,   37,   19,   36,  104,   38,   29,   35,   64,   14,
   37,   67,   36,   30,   31,   32,   69,    6,   25,    3,
    3,   33,   70,   70,    4,    4,   70,  167,    5,    5,
    6,   34,    7,    7,   71,  143,   21,   71,  144,   86,
  100,   70,   97,   71,  100,  100,  100,  100,  100,   82,
  100,   81,   71,   72,   94,  183,  184,  160,   73,  105,
  161,  166,  100,  100,  100,  100,   95,   74,  105,   88,
   95,   95,   95,   95,   95,   91,   95,   89,   35,   91,
   91,   91,   91,   91,  181,   91,   38,  182,   95,   95,
   95,   95,   80,   90,   80,   80,   80,   91,   91,   10,
   91,   82,   10,   82,   82,   82,  176,  129,  130,  131,
   80,   80,   81,   80,   81,   81,   81,   97,   85,   82,
   82,   75,   82,   83,   75,   95,   20,   92,   84,   20,
   81,   81,   78,   81,  142,   78,  168,   75,   76,   75,
   75,   79,   75,  145,   79,  119,  120,  121,  122,  146,
   78,   78,   76,   78,  147,   76,  164,  165,  148,   79,
   79,   77,   79,  151,   77,  152,  168,  117,  118,  153,
   76,   76,  154,   76,   73,  155,   77,   73,   78,   77,
   77,  156,   77,   74,  123,  124,   74,  157,   72,   72,
   72,  158,   73,  159,  171,   72,   72,  173,  174,   68,
  172,   74,   68,   30,   31,   32,   12,  175,  178,  101,
   67,   33,   30,   31,   32,   12,  179,   68,  101,  105,
   33,   34,  102,  103,    1,   30,   31,   32,   12,   16,
   34,  102,  103,   33,   30,   31,   32,   12,   32,   91,
   70,   16,   33,   34,   27,   23,   16,   70,   70,  186,
   16,   71,   34,   69,   16,   28,   69,  185,   71,   71,
   30,  100,  100,  100,  100,  141,  100,  177,  100,  100,
   56,   69,  114,  116,  115,  162,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   95,   95,   95,
   95,    0,   95,    0,   95,   95,   91,   91,   91,   91,
    0,   91,    0,   91,   91,    0,    0,    0,    0,   80,
   80,   80,   80,    0,   80,    0,   80,   80,   82,   82,
   82,   82,    0,   82,    0,   82,   82,    0,    0,   81,
   81,   81,   81,    0,   81,    0,   81,   81,   75,   75,
   75,   75,    0,   75,    0,   75,   75,    0,    0,   78,
   78,   78,   78,    0,   78,    0,   78,   78,   79,   79,
   79,   79,    0,   79,    0,   79,   79,    0,    0,   76,
   76,   76,   76,    0,   76,    0,   76,   76,   77,   77,
   77,   77,   45,   77,    0,   77,   77,   66,   67,   68,
    0,   73,   73,   73,    0,    0,   45,    0,   73,   73,
   74,   74,   74,    0,    0,   13,    0,   74,   74,   18,
    0,    0,    0,   20,    0,    0,   68,    0,    0,    0,
    0,    0,    0,    0,   68,   58,    0,    0,   61,    0,
   45,    0,   45,   45,  125,  126,  127,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   45,    0,    0,  100,
   69,    0,    0,    0,    0,    0,    0,    0,   69,    0,
    0,    0,    0,    0,    0,  128,    0,    0,    0,    0,
    0,  138,   61,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   45,    0,    0,    0,    0,
    0,   45,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   45,
   45,    0,    0,    0,  163,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  138,    0,    0,    0,    0,    0,    0,  180,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         33,
    0,   41,   25,   44,   44,   60,   40,   62,   33,   43,
  270,   45,  105,  274,   41,   40,    2,   44,   43,   59,
   45,   33,  283,   41,   21,   59,   44,   44,   40,   41,
   33,   43,   40,   45,   59,   44,  123,   40,   35,  274,
   43,   59,   45,  267,  268,  269,   40,  282,   61,  271,
  271,  275,   46,   41,  276,  276,   44,  150,  280,  280,
  282,  285,  284,  284,   41,   41,  283,   44,   44,   55,
   37,   59,   69,  258,   41,   42,   43,   44,   45,   43,
   47,   45,   59,   61,  125,  178,  179,   41,  266,  123,
   44,  125,   59,   60,   61,   62,   37,  265,  123,   59,
   41,   42,   43,   44,   45,   37,   47,  123,  125,   41,
   42,   43,   44,   45,   41,   47,  125,   44,   59,   60,
   61,   62,   41,   59,   43,   44,   45,   59,   60,   41,
   62,   41,   44,   43,   44,   45,  159,  267,  268,  269,
   59,   60,   41,   62,   43,   44,   45,  144,   37,   59,
   60,   41,   62,   42,   44,   41,   41,   40,   47,   44,
   59,   60,   41,   62,   59,   44,  152,  259,  260,   59,
   60,   41,   62,   46,   44,   77,   78,   79,   80,   59,
   59,   60,   41,   62,   40,   44,  147,  148,   40,   59,
   60,   41,   62,   59,   44,   40,  182,   75,   76,   45,
   59,   60,   45,   62,   41,  125,  261,   44,  263,   59,
   60,   44,   62,   41,   81,   82,   44,   44,  258,  259,
  260,   44,   59,   61,  267,  265,  266,  269,  267,   41,
  268,   59,   44,  267,  268,  269,  270,  268,   41,  273,
  258,  275,  267,  268,  269,  270,   41,   59,  273,  123,
  275,  285,  286,  287,    0,  267,  268,  269,  270,    8,
  285,  286,  287,  275,  267,  268,  269,  270,  125,   60,
  258,  271,  275,  285,  125,   15,  276,  265,  266,  182,
  280,  258,  285,   41,  284,  125,   44,  181,  265,  266,
  125,  258,  259,  260,  261,   93,  263,  161,  265,  266,
   22,   59,   72,   74,   73,  144,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  258,  259,  260,
  261,   -1,  263,   -1,  265,  266,  258,  259,  260,  261,
   -1,  263,   -1,  265,  266,   -1,   -1,   -1,   -1,  258,
  259,  260,  261,   -1,  263,   -1,  265,  266,  258,  259,
  260,  261,   -1,  263,   -1,  265,  266,   -1,   -1,  258,
  259,  260,  261,   -1,  263,   -1,  265,  266,  258,  259,
  260,  261,   -1,  263,   -1,  265,  266,   -1,   -1,  258,
  259,  260,  261,   -1,  263,   -1,  265,  266,  258,  259,
  260,  261,   -1,  263,   -1,  265,  266,   -1,   -1,  258,
  259,  260,  261,   -1,  263,   -1,  265,  266,  258,  259,
  260,  261,   21,  263,   -1,  265,  266,   36,   37,   38,
   -1,  258,  259,  260,   -1,   -1,   35,   -1,  265,  266,
  258,  259,  260,   -1,   -1,    6,   -1,  265,  266,   10,
   -1,   -1,   -1,   14,   -1,   -1,  258,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  266,   26,   -1,   -1,   29,   -1,
   69,   -1,   71,   72,   83,   84,   85,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  105,   -1,   -1,   70,
  258,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  266,   -1,
   -1,   -1,   -1,   -1,   -1,   86,   -1,   -1,   -1,   -1,
   -1,   92,   93,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  144,   -1,   -1,   -1,   -1,
   -1,  150,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  178,
  179,   -1,   -1,   -1,  145,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  161,   -1,   -1,   -1,   -1,   -1,   -1,  168,
};
}
final static short YYFINAL=1;
final static short YYMAXTOKEN=288;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,"'!'",null,null,null,"'%'",null,null,"'('","')'","'*'","'+'",
"','","'-'","'.'","'/'",null,null,null,null,null,null,null,null,null,null,null,
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
"rule : RULE condition INFERS behavior",
"condition : expression",
"behavior : statement",
"statement : expressionStatement",
"statement : compoundStatement",
"statement : selectionStatement",
"statement : iterationStatement",
"statement : endStatement",
"expressionStatement : expression ';'",
"expressionStatement : ';'",
"expression : assignmentExpression",
"assignmentExpression : booleanExpression",
"assignmentExpression : structureExpression '=' assignmentExpression",
"booleanExpression : logicalOrExpression",
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
"functionExpression : structureExpression",
"functionExpression : identifier '(' ')'",
"functionExpression : identifier '(' argumentExpressionList ')'",
"structureExpression : primaryExpression",
"structureExpression : identifier '.' identifier",
"structureExpression : identifier '.' identifier '.' identifier",
"argumentExpressionList : argumentExpression",
"argumentExpressionList : argumentExpressionList ',' argumentExpression",
"argumentExpression : expression",
"primaryExpression : identifier",
"primaryExpression : constant",
"primaryExpression : '(' expression ')'",
"compoundStatement : '{' statementList '}'",
"statementList : statement",
"statementList : statementList statement",
"selectionStatement : IF '(' booleanExpression ')' statement",
"iterationStatement : WHILE '(' booleanExpression ')' statement",
"endStatement : END ';'",
};

//#line 449 "../../../../../src/org/geppetto/parser/parser.y"

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
//#line 637 "Parser.java"
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
//#line 85 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<Variable> globalVariables = (ArrayList<Variable>) val_peek(4).obj;
                                                      ArrayList<PropertyDefinition> propertyDefinitions = (ArrayList<PropertyDefinition>) val_peek(3).obj;
                                                      ArrayList<Entity> entities =  (ArrayList<Entity>) val_peek(2).obj;
                                                      ArrayList<Rule> rules = (ArrayList<Rule>) val_peek(1).obj;
                                                      ArrayList<FunctionDefinition> functions = (ArrayList<FunctionDefinition>) val_peek(0).obj;  
                                                      geppettoProgram = new GeppettoProgram(globalVariables, propertyDefinitions, entities, rules, functions); }
break;
case 2:
//#line 94 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<Variable> variables = (ArrayList<Variable>) val_peek(1).obj;
                                                      if (variables == null)
                                                         variables = new ArrayList<Variable>();
                                                      variables.add((Variable) val_peek(0).obj); 
                                                      yyval.obj = variables; }
break;
case 3:
//#line 99 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new ArrayList<Variable>(); }
break;
case 4:
//#line 103 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new Variable(symbolTable.get(val_peek(3).ival), (VariableType) val_peek(4).obj, (Value) val_peek(1).obj); }
break;
case 5:
//#line 107 "../../../../../src/org/geppetto/parser/parser.y"
{ propertyDefinitions = new ArrayList<PropertyDefinition>();
                                                      propertyDefinitions.add((PropertyDefinition) val_peek(0).obj); 
                                                      yyval.obj = propertyDefinitions; }
break;
case 6:
//#line 110 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<PropertyDefinition> propertyDefs = (ArrayList<PropertyDefinition>) val_peek(1).obj;
                                                      propertyDefs.add((PropertyDefinition) val_peek(0).obj); 
                                                      yyval.obj = propertyDefs; }
break;
case 7:
//#line 116 "../../../../../src/org/geppetto/parser/parser.y"
{ PropertyDefinition propertyDef = new PropertyDefinition(symbolTable.get(val_peek(4).ival));
                                                      propertyDef.addAttributeDefinitions((ArrayList<AttributeDefinition>) val_peek(2).obj);
                                                      yyval.obj = propertyDef; }
break;
case 8:
//#line 122 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<AttributeDefinition> attributeDefs = new ArrayList<AttributeDefinition>();
                                                      attributeDefs.add((AttributeDefinition) val_peek(0).obj); 
                                                      yyval.obj = attributeDefs; }
break;
case 9:
//#line 125 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<AttributeDefinition> attributeDefs = (ArrayList<AttributeDefinition>) val_peek(2).obj;
                                                      attributeDefs.add((AttributeDefinition) val_peek(0).obj); 
                                                      yyval.obj = attributeDefs; }
break;
case 10:
//#line 131 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new AttributeDefinition((VariableType)val_peek(1).obj, symbolTable.get(val_peek(0).ival)); }
break;
case 11:
//#line 132 "../../../../../src/org/geppetto/parser/parser.y"
{ AttributeDefinition attributeDef = new AttributeDefinition((VariableType)val_peek(4).obj, symbolTable.get(val_peek(3).ival));
                                                      attributeDef.setConstraint((AttributeConstraint) val_peek(1).obj); 
                                                      yyval.obj = attributeDef; }
break;
case 12:
//#line 138 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<Entity> entities = new ArrayList<Entity>();  
                                                      entities.add((Entity) val_peek(0).obj); 
                                                      yyval.obj = entities; }
break;
case 13:
//#line 141 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<Entity> entities = (ArrayList<Entity>) val_peek(1).obj;  
                                                      entities.add((Entity) val_peek(0).obj); 
                                                      yyval.obj = entities; }
break;
case 14:
//#line 147 "../../../../../src/org/geppetto/parser/parser.y"
{ Entity entity = new Entity(symbolTable.get(val_peek(4).ival));
                                                      entity.addProperties((List<Property>) val_peek(2).obj);
                                                      yyval.obj = entity; }
break;
case 15:
//#line 153 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<FunctionDefinition> functions = (ArrayList<FunctionDefinition>) val_peek(1).obj;
                                                      if (functions == null)
                                                          functions = new ArrayList<FunctionDefinition>();
                                                      functions.add((FunctionDefinition) val_peek(0).obj); 
                                                      yyval.obj = functions; }
break;
case 16:
//#line 158 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new ArrayList<FunctionDefinition>(); }
break;
case 17:
//#line 163 "../../../../../src/org/geppetto/parser/parser.y"
{ String name = symbolTable.get(val_peek(4).ival);
                                                      VariableType type = (VariableType) val_peek(5).obj;
                                                      ArrayList<ArgumentDeclaration> arguments = (ArrayList<ArgumentDeclaration>) val_peek(2).obj;
                                                      CompoundStatement compoundStatement = (CompoundStatement) val_peek(0).obj; 
                                                      yyval.obj = new FunctionDefinition(name, type, arguments, compoundStatement); }
break;
case 18:
//#line 171 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<ArgumentDeclaration> arguments = new ArrayList<ArgumentDeclaration>();
                                                      arguments.add((ArgumentDeclaration) val_peek(0).obj); 
                                                      yyval.obj = arguments; }
break;
case 19:
//#line 174 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<ArgumentDeclaration> arguments = (ArrayList<ArgumentDeclaration>) val_peek(2).obj;
                                                      arguments.add((ArgumentDeclaration) val_peek(0).obj); 
                                                      yyval.obj = arguments; }
break;
case 20:
//#line 177 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new ArrayList<ArgumentDeclaration>(); }
break;
case 21:
//#line 181 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new ArgumentDeclaration(symbolTable.get(val_peek(0).ival), (VariableType) val_peek(1).obj); }
break;
case 22:
//#line 185 "../../../../../src/org/geppetto/parser/parser.y"
{ debug("** IDENTIFIER: ID: " + val_peek(0).ival + "; symb table entry: " + symbolTable.get(val_peek(0).ival)); 
                                                      yyval.ival = val_peek(0).ival; }
break;
case 23:
//#line 190 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = VariableType.INT; }
break;
case 24:
//#line 191 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = VariableType.FLOAT; }
break;
case 25:
//#line 192 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = VariableType.STRING; }
break;
case 26:
//#line 193 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = VariableType.BOOLEAN; }
break;
case 27:
//#line 197 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new AttributeConstraintStringSet((Set<String>) val_peek(0).obj); }
break;
case 28:
//#line 198 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new AttributeConstraintIntegerSet((Set<Integer>) val_peek(0).obj); }
break;
case 29:
//#line 199 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new AttributeConstraintIntegerRange((ArrayList<Integer>)val_peek(0).obj); }
break;
case 30:
//#line 200 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new AttributeConstraintFloatSet((Set<Float>)val_peek(0).obj); }
break;
case 31:
//#line 201 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new AttributeConstraintFloatRange((ArrayList<Float>)val_peek(0).obj); }
break;
case 33:
//#line 206 "../../../../../src/org/geppetto/parser/parser.y"
{ HashSet<String> stringSet = new HashSet<String>(); 
                                                      stringSet.add(symbolTable.get(val_peek(0).ival)); 
                                                      yyval.obj = stringSet; }
break;
case 34:
//#line 209 "../../../../../src/org/geppetto/parser/parser.y"
{ HashSet<String> stringSet = (HashSet<String>) val_peek(2).obj;
                                                      stringSet.add(symbolTable.get(val_peek(0).ival)); 
                                                      yyval.obj = stringSet; }
break;
case 35:
//#line 215 "../../../../../src/org/geppetto/parser/parser.y"
{ HashSet<Integer> intSet = new HashSet<Integer>(); 
                                                      intSet.add(new Integer(val_peek(0).ival)); 
                                                      yyval.obj = intSet; }
break;
case 36:
//#line 218 "../../../../../src/org/geppetto/parser/parser.y"
{ HashSet<Integer> intSet = (HashSet<Integer>) val_peek(2).obj; 
                                                      intSet.add(new Integer(val_peek(0).ival)); 
                                                      yyval.obj = intSet; }
break;
case 37:
//#line 224 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<Integer> intList = new ArrayList<Integer>();
                                                      intList.add(val_peek(2).ival);
                                                      intList.add(val_peek(1).ival);
                                                      yyval.obj = intList; }
break;
case 38:
//#line 231 "../../../../../src/org/geppetto/parser/parser.y"
{ HashSet<Float> floatSet = new HashSet<Float>(); 
                                                      floatSet.add(new Float(val_peek(0).dval)); 
                                                      yyval.obj = floatSet; }
break;
case 39:
//#line 234 "../../../../../src/org/geppetto/parser/parser.y"
{ HashSet<Float> floatSet = (HashSet<Float>) val_peek(2).obj; 
                                                      floatSet.add(new Float(val_peek(0).dval)); 
                                                      yyval.obj = floatSet; }
break;
case 40:
//#line 240 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<Float> floatList = new ArrayList<Float>();
                                                      floatList.add(new Float(val_peek(2).dval));
                                                      floatList.add(new Float(val_peek(1).dval));
                                                      yyval.obj = floatList; }
break;
case 41:
//#line 247 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<Property> properties = new ArrayList<Property>();
                                                      properties.add((Property) val_peek(0).obj); 
                                                      yyval.obj = properties;}
break;
case 42:
//#line 250 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<Property> properties = (ArrayList<Property>) val_peek(2).obj;
                                                      properties.add((Property) val_peek(0).obj); 
                                                      yyval.obj = properties; }
break;
case 43:
//#line 256 "../../../../../src/org/geppetto/parser/parser.y"
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
//#line 270 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<AttributeInitializer> attributeInitializers = new ArrayList<AttributeInitializer>();
                                                      attributeInitializers.add((AttributeInitializer) val_peek(0).obj);
                                                      yyval.obj = attributeInitializers; }
break;
case 45:
//#line 273 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<AttributeInitializer> attributeInitiazers = (ArrayList<AttributeInitializer>) val_peek(2).obj;
                                                      attributeInitiazers.add((AttributeInitializer) val_peek(0).obj);
                                                      yyval.obj = attributeInitiazers; }
break;
case 46:
//#line 279 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new AttributeInitializer(symbolTable.get(val_peek(2).ival), (Value)val_peek(0).obj); }
break;
case 47:
//#line 283 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new Value(val_peek(0).ival); }
break;
case 48:
//#line 284 "../../../../../src/org/geppetto/parser/parser.y"
{ Float fval = new Float(val_peek(0).dval); yyval.obj = new Value(fval); }
break;
case 49:
//#line 285 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new Value(symbolTable.get(val_peek(0).ival)); }
break;
case 50:
//#line 286 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new Value(true); }
break;
case 51:
//#line 287 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new Value(false); }
break;
case 52:
//#line 291 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<Rule> rules = new ArrayList<Rule>();  
                                                      rules.add((Rule) val_peek(0).obj); 
                                                      yyval.obj = rules; }
break;
case 53:
//#line 294 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<Rule> rules = (ArrayList<Rule>) val_peek(1).obj;  
                                                      rules.add((Rule) val_peek(0).obj); 
                                                      yyval.obj = rules; }
break;
case 54:
//#line 300 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new Rule((Condition) val_peek(2).obj, (Behavior) val_peek(0).obj); }
break;
case 55:
//#line 307 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new Condition((Expression) val_peek(0).obj); }
break;
case 56:
//#line 311 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new Behavior((Statement) val_peek(0).obj); }
break;
case 57:
//#line 315 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 58:
//#line 316 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 59:
//#line 317 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 60:
//#line 318 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 61:
//#line 319 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 62:
//#line 323 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new ExpressionStatement((Expression) val_peek(1).obj); }
break;
case 63:
//#line 324 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new NullStatement(); }
break;
case 64:
//#line 328 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 65:
//#line 332 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 66:
//#line 333 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new AssignmentExpression((Expression) val_peek(2).obj, (Expression) val_peek(0).obj); }
break;
case 67:
//#line 337 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 68:
//#line 341 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 69:
//#line 342 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new BinaryExpression((Expression) val_peek(2).obj, Operator.LOGICAL_OR, (Expression) val_peek(0).obj); }
break;
case 70:
//#line 346 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 71:
//#line 347 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new BinaryExpression( (Expression) val_peek(2).obj,  Operator.LOGICAL_AND, (Expression) val_peek(0).obj); }
break;
case 72:
//#line 351 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 73:
//#line 352 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new BinaryExpression((Expression) val_peek(2).obj, Operator.EQUAL_TO, (Expression) val_peek(0).obj); }
break;
case 74:
//#line 353 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new BinaryExpression((Expression) val_peek(2).obj, Operator.NOT_EQUAL_TO, (Expression) val_peek(0).obj); }
break;
case 75:
//#line 357 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 76:
//#line 358 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new BinaryExpression((Expression) val_peek(2).obj, Operator.GREATER_THAN, (Expression) val_peek(0).obj); }
break;
case 77:
//#line 359 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new BinaryExpression((Expression) val_peek(2).obj, Operator.LESS_THAN, (Expression) val_peek(0).obj); }
break;
case 78:
//#line 360 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new BinaryExpression((Expression) val_peek(2).obj, Operator.GREATER_THAN_OR_EQUAL_TO, (Expression) val_peek(0).obj); }
break;
case 79:
//#line 361 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new BinaryExpression((Expression) val_peek(2).obj, Operator.LESS_THAN_OR_EQUAL_TO, (Expression) val_peek(0).obj); }
break;
case 80:
//#line 365 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 81:
//#line 366 "../../../../../src/org/geppetto/parser/parser.y"
{yyval.obj = new BinaryExpression((Expression) val_peek(2).obj, Operator.PLUS, (Expression) val_peek(0).obj); }
break;
case 82:
//#line 367 "../../../../../src/org/geppetto/parser/parser.y"
{yyval.obj = new BinaryExpression((Expression) val_peek(2).obj, Operator.MINUS, (Expression) val_peek(0).obj); }
break;
case 83:
//#line 371 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 84:
//#line 372 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new BinaryExpression((Expression) val_peek(2).obj, Operator.MULTIPLY, (Expression) val_peek(0).obj); }
break;
case 85:
//#line 373 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new BinaryExpression((Expression) val_peek(2).obj, Operator.DIVIDE, (Expression) val_peek(0).obj); }
break;
case 86:
//#line 374 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new BinaryExpression((Expression) val_peek(2).obj, Operator.MODULUS, (Expression) val_peek(0).obj); }
break;
case 87:
//#line 378 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 88:
//#line 379 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new UnaryExpression(Operator.UNARY_PLUS, (Expression) val_peek(1).obj); }
break;
case 89:
//#line 380 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new UnaryExpression(Operator.UNARY_MINUS, (Expression) val_peek(1).obj); }
break;
case 90:
//#line 381 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new UnaryExpression(Operator.UNARY_NEGATION, (Expression) val_peek(1).obj); }
break;
case 91:
//#line 385 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 92:
//#line 386 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new FunctionExpression(symbolTable.get(val_peek(2).ival), null); }
break;
case 93:
//#line 387 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new FunctionExpression(symbolTable.get(val_peek(3).ival), (ArrayList<Expression>) val_peek(3).obj); }
break;
case 94:
//#line 391 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 95:
//#line 392 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new StructureExpression(symbolTable.get(val_peek(2).ival), symbolTable.get(val_peek(0).ival)); }
break;
case 96:
//#line 393 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new StructureExpression(symbolTable.get(val_peek(4).ival), symbolTable.get(val_peek(2).ival), symbolTable.get(val_peek(0).ival)); }
break;
case 97:
//#line 397 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<Expression> argList = new ArrayList<Expression>(); 
	                                                  argList.add((Expression) val_peek(0).obj); 
	                                                  yyval.obj = argList; }
break;
case 98:
//#line 400 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<Expression> argList = (ArrayList<Expression>) val_peek(2).obj; 
	                                                  argList.add((Expression) val_peek(0).obj); 
	                                                  yyval.obj = argList; }
break;
case 99:
//#line 409 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 100:
//#line 413 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new Variable(symbolTable.get(val_peek(0).ival)); }
break;
case 101:
//#line 414 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new ConstantExpression((Value) val_peek(0).obj); }
break;
case 102:
//#line 415 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(1).obj; }
break;
case 103:
//#line 419 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new CompoundStatement((ArrayList<Statement>) val_peek(1).obj); }
break;
case 104:
//#line 423 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<Statement> statementList = new ArrayList<Statement>(); 
                                                      statementList.add((Statement) val_peek(0).obj); 
                                                      yyval.obj = statementList; }
break;
case 105:
//#line 426 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<Statement> statementList = (ArrayList<Statement>) val_peek(1).obj; 
                                                      statementList.add((Statement) val_peek(0).obj); 
                                                      yyval.obj = statementList; }
break;
case 106:
//#line 432 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new SelectionStatement( (BooleanExpression) val_peek(2).obj, (Statement) val_peek(0).obj); }
break;
case 107:
//#line 439 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new IterationStatement((BooleanExpression) val_peek(2).obj, (Statement) val_peek(0).obj); }
break;
case 108:
//#line 444 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new EndStatement(); }
break;
//#line 1302 "Parser.java"
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
