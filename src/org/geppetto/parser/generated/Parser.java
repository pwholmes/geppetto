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
  import org.geppetto.domain.statement.EndStatement;
  import org.geppetto.domain.statement.ExpressionStatement;
  import org.geppetto.domain.statement.IterationStatement;
  import org.geppetto.domain.statement.SelectionStatement;
  import org.geppetto.domain.statement.Statement;
//#line 56 "Parser.java"




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
    0,    1,    1,    5,    2,    2,    9,   10,   10,   11,
   11,   12,   12,   12,   12,   12,   12,   13,   13,   14,
   14,   15,   16,   16,   17,    3,    3,   18,   19,   19,
   20,   21,   21,   22,    8,    8,    8,    8,    8,    4,
    4,   23,   24,   25,   27,   27,   27,   27,   27,   28,
   28,   26,   33,   33,   34,   36,   36,   37,   37,   38,
   38,   38,   39,   39,   39,   39,   39,   40,   40,   40,
   41,   41,   41,   41,   42,   42,   42,   42,   43,   43,
   43,   35,   35,   35,   44,   44,   46,   45,   45,   45,
   29,   47,   47,   30,   31,   32,    7,    6,    6,    6,
    6,
};
final static short yylen[] = {                            2,
    4,    2,    0,    5,    1,    2,    6,    1,    3,    2,
    5,    1,    1,    1,    1,    1,    0,    1,    3,    1,
    3,    3,    1,    3,    3,    1,    2,    6,    1,    3,
    4,    1,    3,    3,    1,    1,    1,    1,    1,    1,
    2,    5,    1,    1,    1,    1,    1,    1,    1,    2,
    1,    1,    1,    3,    1,    1,    3,    1,    3,    1,
    3,    3,    1,    3,    3,    3,    3,    1,    3,    3,
    1,    3,    3,    3,    1,    2,    2,    2,    1,    3,
    4,    1,    3,    5,    1,    3,    1,    1,    1,    3,
    3,    1,    2,    5,    5,    1,    1,    1,    1,    1,
    1,
};
final static short yydefred[] = {                         3,
    0,    0,  101,   99,   98,    0,  100,    0,    2,    0,
    5,   97,    0,    0,    0,    6,   26,    0,    0,    0,
    0,    0,   27,   40,    0,    0,    0,    8,    0,   35,
   36,   37,   39,   38,    0,    0,    0,    0,    0,   89,
    0,   43,   52,   53,    0,    0,    0,    0,    0,    0,
    0,   71,   75,   82,   41,    0,    0,    0,    0,    0,
    0,   29,    0,   79,   77,   76,   78,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    4,    0,    7,    9,    0,    0,
    0,   90,   80,   87,    0,   85,    0,   96,    0,    0,
   51,    0,    0,    0,   44,   45,   46,   47,   48,   49,
   54,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   72,   73,   74,    0,    0,   18,    0,    0,    0,
   14,    0,   16,    0,    0,   32,   30,   28,   81,    0,
    0,    0,    0,   92,    0,   42,   50,    0,    0,   11,
    0,    0,    0,    0,   31,    0,   86,   84,    0,    0,
   91,   93,   22,   25,   19,   21,   24,   34,   33,    0,
    0,   95,   94,
};
final static short yydgoto[] = {                          1,
    2,    8,   15,   22,    9,   26,   39,   40,   11,   27,
   28,  128,  129,  130,  131,  132,  133,   17,   61,   62,
  135,  136,   24,   41,  103,  104,  105,  106,  107,  108,
  109,  110,   43,   44,   64,   46,   47,   48,   49,   50,
   51,   52,   53,   95,   54,   96,  145,
};
final static short yysindex[] = {                         0,
    0,   43,    0,    0,    0, -260,    0, -254,    0, -260,
    0,    0,  -13, -260, -213,    0,    0,  -10,  -53,  -65,
  143, -201,    0,    0, -195, -260,   80,    0, -260,    0,
    0,    0,    0,    0,  143,  143,  143,  143,   10,    0,
 -179,    0,    0,    0,   25, -189, -184, -207,  -59,   -6,
   -7,    0,    0,    0,    0,   44,    4,   54,  -53,   96,
  -18,    0,   88,    0,    0,    0,    0,  139, -260,   35,
  143,  143,  143,  143,  143,  143,  143,  143,  143,  143,
  143,  143,  143,  143,    0,   -9,    0,    0, -260, -260,
   86,    0,    0,    0,   84,    0,  101,    0,  110,  119,
    0,   35,  107,  116,    0,    0,    0,    0,    0,    0,
    0, -184, -207,  -59,  -59,   -6,   -6,   -6,   -6,   -7,
   -7,    0,    0,    0,  124,  136,    0,   53,  141,  145,
    0,  147,    0,  126,   90,    0,    0,    0,    0,  143,
 -260,  143,  143,    0,   14,    0,    0,  -56,  -60,    0,
  -52,  -48,  -47, -195,    0, -260,    0,    0,  173,  176,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   35,
   35,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  218,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  -37,    0,
    0,    0,    0,    0,    1,  -23,  133,    8,  149,   60,
   40,    0,    0,    0,    0,    0,   94,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   99,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  -28,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  163,  161,  153,  157,   82,   89,  102,  111,   46,
   52,    0,    0,    0,  -16,  -15,    0,    0,  100,  103,
    0,  104,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,    0,    0,    0,    0,  231,  439,  -14,  226,    0,
  177,    0,    0,    0,    0,    0,    0,  228,    0,  170,
    0,  105,  240,    0,    0,   20,   61,    0,    0,    0,
    0,    0,  192,   11,   97,    0,  193,  191,  121,  162,
   76,   33,    0,    0,    0,  129,    0,
};
final static int YYTABLESIZE=595;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         88,
   77,   12,   76,   88,   88,   88,   88,   88,   83,   88,
   56,   14,   83,   83,   83,   83,   83,   55,   83,    6,
   55,   88,   88,   88,   88,   90,   19,   20,   23,   84,
   83,   83,   83,   83,   82,   55,   81,   79,   80,   83,
   42,   79,   79,   79,   79,   79,   38,   79,   58,   68,
   25,   58,   14,   35,   63,   69,   37,   29,   36,   79,
   79,   21,   79,   30,   31,   32,   58,   38,   65,   66,
   67,   33,  101,   21,   35,   74,   75,   37,   70,   36,
   68,   34,   68,   68,   68,   71,   70,   94,   70,   70,
   70,   72,   69,  101,   69,   69,   69,   73,   68,   68,
   63,   68,   85,   63,   70,   70,   91,   70,   20,   23,
   69,   69,   87,   69,  122,  123,  124,   45,   63,   63,
   58,   63,   64,   59,  139,   64,   86,  140,   92,   65,
  155,   45,   65,  156,   10,   89,  102,   10,  161,  168,
   64,   64,   66,   64,  138,   66,  141,   65,   65,  142,
   65,   67,  159,  160,   67,  120,  121,  102,  143,   94,
   66,   66,  144,   66,   45,  146,   45,   45,  148,   67,
   67,   38,   67,   56,  147,   38,   56,  150,   35,   93,
  149,   37,   35,   36,  151,   37,  154,   36,  152,   60,
  153,   56,   60,   61,  114,  115,   61,   62,   45,  164,
   62,   59,  163,   57,   59,  162,   57,   60,  165,    3,
  166,   61,  167,  170,    4,   62,  171,    1,    5,   59,
   88,   57,    7,   17,   12,   78,   79,   13,   15,   83,
  172,  173,   10,   16,   55,   88,   45,  116,  117,  118,
  119,   45,   23,   88,   88,   88,   88,   88,   88,  125,
  126,  127,   83,   83,   83,   83,   83,   83,   79,  137,
  169,   55,  111,  113,  112,   58,   45,   45,  157,    0,
    0,    0,   30,   31,   32,   12,    0,    0,   98,    0,
   33,   79,   79,   79,   79,   79,   79,    0,   58,   58,
   34,   99,  100,   30,   31,   32,   12,   68,    0,   98,
    0,   33,    0,   70,    0,    3,    0,    0,    0,   69,
    4,   34,   99,  100,    5,    0,    6,   63,    7,    0,
   68,   68,   68,   68,   68,   68,   70,   70,   70,   70,
   70,   70,   69,   69,   69,   69,   69,   69,    0,   64,
   63,   63,   63,   63,   63,   63,   65,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   66,
    0,    0,   64,   64,   64,   64,   64,   64,   67,   65,
   65,   65,   65,   65,   65,    0,    0,    0,    0,    0,
    0,    0,   66,   66,   66,   66,   66,   66,    0,    0,
   56,   67,   67,   67,   67,   67,   67,   30,   31,   32,
   12,   30,   31,   32,   12,   33,   60,    0,    0,   33,
   61,    0,    0,   56,   62,   34,    0,    0,   59,   34,
   57,    0,    0,    0,    0,    0,    0,    0,    0,   60,
   60,   60,   60,   61,   61,   61,   61,   62,   62,   62,
   62,   59,   59,   57,   13,    0,    0,    0,   18,    0,
    0,    0,   20,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   57,    0,    0,   60,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   97,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  134,   60,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  158,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  134,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         37,
   60,  262,   62,   41,   42,   43,   44,   45,   37,   47,
   25,  266,   41,   42,   43,   44,   45,   41,   47,  274,
   44,   59,   60,   61,   62,   44,   40,   44,   44,   37,
   59,   60,   61,   62,   42,   59,   43,   37,   45,   47,
   21,   41,   42,   43,   44,   45,   33,   47,   41,   40,
   61,   44,  266,   40,   35,   46,   43,  123,   45,   59,
   60,  275,   62,  259,  260,  261,   59,   33,   36,   37,
   38,  267,   59,  275,   40,  283,  284,   43,  258,   45,
   41,  277,   43,   44,   45,   61,   41,   68,   43,   44,
   45,  281,   41,   59,   43,   44,   45,  282,   59,   60,
   41,   62,   59,   44,   59,   60,  125,   62,  125,  125,
   59,   60,   59,   62,   82,   83,   84,   21,   59,   60,
   41,   62,   41,   44,   41,   44,  123,   44,   41,   41,
   41,   35,   44,   44,   41,   40,  123,   44,  125,  154,
   59,   60,   41,   62,   59,   44,   46,   59,   60,   40,
   62,   41,  142,  143,   44,   80,   81,  123,   40,  140,
   59,   60,  102,   62,   68,   59,   70,   71,   45,   59,
   60,   33,   62,   41,   59,   33,   44,  125,   40,   41,
   45,   43,   40,   45,   44,   43,   61,   45,   44,   41,
   44,   59,   44,   41,   74,   75,   44,   41,  102,  260,
   44,   41,  259,   41,   44,  145,   44,   59,  261,  263,
  259,   59,  260,   41,  268,   59,   41,    0,  272,   59,
  258,   59,  276,  125,  125,  285,  286,  125,  125,  258,
  170,  171,    2,    8,  258,   59,  140,   76,   77,   78,
   79,  145,   15,  281,  282,  283,  284,  285,  286,  259,
  260,  261,  281,  282,  283,  284,  285,  286,  258,   90,
  156,   22,   71,   73,   72,  258,  170,  171,  140,   -1,
   -1,   -1,  259,  260,  261,  262,   -1,   -1,  265,   -1,
  267,  281,  282,  283,  284,  285,  286,   -1,  281,  282,
  277,  278,  279,  259,  260,  261,  262,  258,   -1,  265,
   -1,  267,   -1,  258,   -1,  263,   -1,   -1,   -1,  258,
  268,  277,  278,  279,  272,   -1,  274,  258,  276,   -1,
  281,  282,  283,  284,  285,  286,  281,  282,  283,  284,
  285,  286,  281,  282,  283,  284,  285,  286,   -1,  258,
  281,  282,  283,  284,  285,  286,  258,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  258,
   -1,   -1,  281,  282,  283,  284,  285,  286,  258,  281,
  282,  283,  284,  285,  286,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  281,  282,  283,  284,  285,  286,   -1,   -1,
  258,  281,  282,  283,  284,  285,  286,  259,  260,  261,
  262,  259,  260,  261,  262,  267,  258,   -1,   -1,  267,
  258,   -1,   -1,  281,  258,  277,   -1,   -1,  258,  277,
  258,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  281,
  282,  283,  284,  281,  282,  283,  284,  281,  282,  283,
  284,  281,  282,  281,    6,   -1,   -1,   -1,   10,   -1,
   -1,   -1,   14,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   26,   -1,   -1,   29,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   69,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   89,   90,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  141,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  156,
};
}
final static short YYFINAL=1;
final static short YYMAXTOKEN=286;
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
null,null,null,null,null,null,null,null,"NEWLINE","INFERS","INTEGER_LITERAL",
"FLOAT_LITERAL","STRING_LITERAL","IDENTIFIER","BOOLEAN","ELSE","END","ENTITY",
"FALSE","FLOAT","FOR","GLOBAL","INPUT","INT","PRINT","PROPERTY","RULE","STRING",
"TRUE","WHILE","IF","FOREACH","\"||\"","\"&&\"","\"==\"","\"!=\"","\">=\"",
"\"<=\"",
};
final static String yyrule[] = {
"$accept : program",
"program : variableDeclarationList propertyDefinitionList entityDeclarationList ruleDeclarationList",
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
"entityDeclarationList : entityDeclaration",
"entityDeclarationList : entityDeclarationList entityDeclaration",
"entityDeclaration : ENTITY identifier '{' propertyList '}' ';'",
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
"rule : RULE condition INFERS behavior ';'",
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
"logicalOrExpression : logicalOrExpression \"||\" logicalAndExpression",
"logicalAndExpression : equalityExpression",
"logicalAndExpression : logicalAndExpression \"&&\" equalityExpression",
"equalityExpression : relationalExpression",
"equalityExpression : equalityExpression \"==\" relationalExpression",
"equalityExpression : equalityExpression \"!=\" relationalExpression",
"relationalExpression : additiveExpression",
"relationalExpression : relationalExpression '>' additiveExpression",
"relationalExpression : relationalExpression '<' additiveExpression",
"relationalExpression : relationalExpression \">=\" additiveExpression",
"relationalExpression : relationalExpression \"<=\" additiveExpression",
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
"endStatement : END",
"identifier : IDENTIFIER",
"typeSpecifier : INT",
"typeSpecifier : FLOAT",
"typeSpecifier : STRING",
"typeSpecifier : BOOLEAN",
};

//#line 410 "../../../../../src/org/geppetto/parser/parser.y"

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
//#line 613 "Parser.java"
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
//#line 77 "../../../../../src/org/geppetto/parser/parser.y"
{
                                                      ArrayList<Variable> globalVariables = (ArrayList<Variable>) val_peek(3).obj;
                                                      ArrayList<PropertyDefinition> propertyDefinitions = (ArrayList<PropertyDefinition>) val_peek(2).obj;
                                                      ArrayList<Entity> entities =  (ArrayList<Entity>) val_peek(1).obj;
                                                      ArrayList<Rule> rules = (ArrayList<Rule>) val_peek(0).obj; 
                                                      geppettoProgram = new GeppettoProgram(globalVariables, propertyDefinitions, entities, rules); }
break;
case 2:
//#line 86 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<Variable> variables = (ArrayList<Variable>) val_peek(1).obj;
                                                      if (variables == null)
                                                         variables = new ArrayList<Variable>();
                                                      variables.add((Variable) val_peek(0).obj); 
                                                      yyval.obj = variables; }
break;
case 3:
//#line 91 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new ArrayList<Variable>(); }
break;
case 4:
//#line 95 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new Variable(symbolTable.get(val_peek(3).ival), (VariableType) val_peek(4).obj, (Value) val_peek(1).obj); }
break;
case 5:
//#line 99 "../../../../../src/org/geppetto/parser/parser.y"
{ propertyDefinitions = new ArrayList<PropertyDefinition>();
                                                      propertyDefinitions.add((PropertyDefinition) val_peek(0).obj); 
                                                      yyval.obj = propertyDefinitions; }
break;
case 6:
//#line 102 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<PropertyDefinition> propertyDefs = (ArrayList<PropertyDefinition>) val_peek(1).obj;
                                                      propertyDefs.add((PropertyDefinition) val_peek(0).obj); 
                                                      yyval.obj = propertyDefs; }
break;
case 7:
//#line 108 "../../../../../src/org/geppetto/parser/parser.y"
{ PropertyDefinition propertyDef = new PropertyDefinition(symbolTable.get(val_peek(4).ival));
                                                      propertyDef.addAttributeDefinitions((ArrayList<AttributeDefinition>) val_peek(2).obj);
                                                      yyval.obj = propertyDef; }
break;
case 8:
//#line 114 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<AttributeDefinition> attributeDefs = new ArrayList<AttributeDefinition>();
                                                      attributeDefs.add((AttributeDefinition) val_peek(0).obj); 
                                                      yyval.obj = attributeDefs; }
break;
case 9:
//#line 117 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<AttributeDefinition> attributeDefs = (ArrayList<AttributeDefinition>) val_peek(2).obj;
                                                      attributeDefs.add((AttributeDefinition) val_peek(0).obj); 
                                                      yyval.obj = attributeDefs; }
break;
case 10:
//#line 123 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new AttributeDefinition((VariableType)val_peek(1).obj, symbolTable.get(val_peek(0).ival)); }
break;
case 11:
//#line 124 "../../../../../src/org/geppetto/parser/parser.y"
{ AttributeDefinition attributeDef = new AttributeDefinition((VariableType)val_peek(4).obj, symbolTable.get(val_peek(3).ival));
                                                      attributeDef.setConstraint((AttributeConstraint) val_peek(1).obj); 
                                                      yyval.obj = attributeDef; }
break;
case 12:
//#line 130 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new AttributeConstraintStringSet((Set<String>) val_peek(0).obj); }
break;
case 13:
//#line 131 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new AttributeConstraintIntegerSet((Set<Integer>) val_peek(0).obj); }
break;
case 14:
//#line 132 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new AttributeConstraintIntegerRange((ArrayList<Integer>)val_peek(0).obj); }
break;
case 15:
//#line 133 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new AttributeConstraintFloatSet((Set<Float>)val_peek(0).obj); }
break;
case 16:
//#line 134 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new AttributeConstraintFloatRange((ArrayList<Float>)val_peek(0).obj); }
break;
case 18:
//#line 139 "../../../../../src/org/geppetto/parser/parser.y"
{ HashSet<String> stringSet = new HashSet<String>(); 
                                                      stringSet.add(symbolTable.get(val_peek(0).ival)); 
                                                      yyval.obj = stringSet; }
break;
case 19:
//#line 142 "../../../../../src/org/geppetto/parser/parser.y"
{ HashSet<String> stringSet = (HashSet<String>) val_peek(2).obj;
                                                      stringSet.add(symbolTable.get(val_peek(0).ival)); 
                                                      yyval.obj = stringSet; }
break;
case 20:
//#line 148 "../../../../../src/org/geppetto/parser/parser.y"
{ HashSet<Integer> intSet = new HashSet<Integer>(); 
                                                      intSet.add(new Integer(val_peek(0).ival)); 
                                                      yyval.obj = intSet; }
break;
case 21:
//#line 151 "../../../../../src/org/geppetto/parser/parser.y"
{ HashSet<Integer> intSet = (HashSet<Integer>) val_peek(2).obj; 
                                                      intSet.add(new Integer(val_peek(0).ival)); 
                                                      yyval.obj = intSet; }
break;
case 22:
//#line 157 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<Integer> intList = new ArrayList<Integer>();
                                                      intList.add(val_peek(2).ival);
                                                      intList.add(val_peek(1).ival);
                                                      yyval.obj = intList; }
break;
case 23:
//#line 164 "../../../../../src/org/geppetto/parser/parser.y"
{ HashSet<Float> floatSet = new HashSet<Float>(); 
                                                      floatSet.add(new Float(val_peek(0).dval)); 
                                                      yyval.obj = floatSet; }
break;
case 24:
//#line 167 "../../../../../src/org/geppetto/parser/parser.y"
{ HashSet<Float> floatSet = (HashSet<Float>) val_peek(2).obj; 
                                                      floatSet.add(new Float(val_peek(0).dval)); 
                                                      yyval.obj = floatSet; }
break;
case 25:
//#line 173 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<Float> floatList = new ArrayList<Float>();
                                                      floatList.add(new Float(val_peek(2).dval));
                                                      floatList.add(new Float(val_peek(1).dval));
                                                      yyval.obj = floatList; }
break;
case 26:
//#line 180 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<Entity> entities = new ArrayList<Entity>();  
                                                      entities.add((Entity) val_peek(0).obj); 
                                                      yyval.obj = entities; }
break;
case 27:
//#line 183 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<Entity> entities = (ArrayList<Entity>) val_peek(1).obj;  
                                                      entities.add((Entity) val_peek(0).obj); 
                                                      yyval.obj = entities; }
break;
case 28:
//#line 189 "../../../../../src/org/geppetto/parser/parser.y"
{ Entity entity = new Entity(symbolTable.get(val_peek(4).ival));
                                                      entity.addProperties((List<Property>) val_peek(2).obj);
                                                      yyval.obj = entity; }
break;
case 29:
//#line 195 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<Property> properties = new ArrayList<Property>();
                                                      properties.add((Property) val_peek(0).obj); 
                                                      yyval.obj = properties;}
break;
case 30:
//#line 198 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<Property> properties = (ArrayList<Property>) val_peek(2).obj;
                                                      properties.add((Property) val_peek(0).obj); 
                                                      yyval.obj = properties; }
break;
case 31:
//#line 204 "../../../../../src/org/geppetto/parser/parser.y"
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
case 32:
//#line 218 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<AttributeInitializer> attributeInitializers = new ArrayList<AttributeInitializer>();
                                                      attributeInitializers.add((AttributeInitializer) val_peek(0).obj);
                                                      yyval.obj = attributeInitializers; }
break;
case 33:
//#line 221 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<AttributeInitializer> attributeInitiazers = (ArrayList<AttributeInitializer>) val_peek(2).obj;
                                                      attributeInitiazers.add((AttributeInitializer) val_peek(0).obj);
                                                      yyval.obj = attributeInitiazers; }
break;
case 34:
//#line 227 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new AttributeInitializer(symbolTable.get(val_peek(2).ival), (Value)val_peek(0).obj); }
break;
case 35:
//#line 231 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new Value(val_peek(0).ival); }
break;
case 36:
//#line 232 "../../../../../src/org/geppetto/parser/parser.y"
{ Float fval = new Float(val_peek(0).dval); yyval.obj = new Value(fval); }
break;
case 37:
//#line 233 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new Value(symbolTable.get(val_peek(0).ival)); }
break;
case 38:
//#line 234 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new Value(true); }
break;
case 39:
//#line 235 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new Value(false); }
break;
case 40:
//#line 239 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<Rule> rules = new ArrayList<Rule>();  
                                                      rules.add((Rule) val_peek(0).obj); 
                                                      yyval.obj = rules; }
break;
case 41:
//#line 242 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<Rule> rules = (ArrayList<Rule>) val_peek(1).obj;  
                                                      rules.add((Rule) val_peek(0).obj); 
                                                      yyval.obj = rules; }
break;
case 42:
//#line 248 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new Rule((Condition) val_peek(3).obj, (Behavior) val_peek(1).obj); }
break;
case 43:
//#line 255 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new Condition((Expression) val_peek(0).obj); }
break;
case 44:
//#line 259 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new Behavior((Statement) val_peek(0).obj); }
break;
case 45:
//#line 263 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 46:
//#line 264 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 47:
//#line 265 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 48:
//#line 266 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 49:
//#line 267 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 50:
//#line 272 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new ExpressionStatement((Expression) val_peek(1).obj); }
break;
case 52:
//#line 277 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 53:
//#line 281 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 54:
//#line 282 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new AssignmentExpression((Expression) val_peek(2).obj, (Expression) val_peek(0).obj); }
break;
case 55:
//#line 286 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 56:
//#line 290 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 57:
//#line 291 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new BinaryExpression((Expression) val_peek(2).obj, Operator.LOGICAL_OR, (Expression) val_peek(0).obj); }
break;
case 58:
//#line 295 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 59:
//#line 296 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new BinaryExpression( (Expression) val_peek(2).obj,  Operator.LOGICAL_AND, (Expression) val_peek(0).obj); }
break;
case 60:
//#line 300 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 61:
//#line 301 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new BinaryExpression((Expression) val_peek(2).obj, Operator.EQUAL_TO, (Expression) val_peek(0).obj); }
break;
case 62:
//#line 302 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new BinaryExpression((Expression) val_peek(2).obj, Operator.NOT_EQUAL_TO, (Expression) val_peek(0).obj); }
break;
case 63:
//#line 306 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 64:
//#line 307 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new BinaryExpression((Expression) val_peek(2).obj, Operator.GREATER_THAN, (Expression) val_peek(0).obj); }
break;
case 65:
//#line 308 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new BinaryExpression((Expression) val_peek(2).obj, Operator.LESS_THAN, (Expression) val_peek(0).obj); }
break;
case 66:
//#line 309 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new BinaryExpression((Expression) val_peek(2).obj, Operator.GREATER_THAN_OR_EQUAL, (Expression) val_peek(0).obj); }
break;
case 67:
//#line 310 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new BinaryExpression((Expression) val_peek(2).obj, Operator.LESS_THAN_OR_EQUAL, (Expression) val_peek(0).obj); }
break;
case 68:
//#line 314 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 69:
//#line 315 "../../../../../src/org/geppetto/parser/parser.y"
{yyval.obj = new BinaryExpression((Expression) val_peek(2).obj, Operator.PLUS, (Expression) val_peek(0).obj); }
break;
case 70:
//#line 316 "../../../../../src/org/geppetto/parser/parser.y"
{yyval.obj = new BinaryExpression((Expression) val_peek(2).obj, Operator.MINUS, (Expression) val_peek(0).obj); }
break;
case 71:
//#line 320 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 72:
//#line 321 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new BinaryExpression((Expression) val_peek(2).obj, Operator.MULTIPLY, (Expression) val_peek(0).obj); }
break;
case 73:
//#line 322 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new BinaryExpression((Expression) val_peek(2).obj, Operator.DIVIDE, (Expression) val_peek(0).obj); }
break;
case 74:
//#line 323 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new BinaryExpression((Expression) val_peek(2).obj, Operator.MODULUS, (Expression) val_peek(0).obj); }
break;
case 75:
//#line 327 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 76:
//#line 328 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new UnaryExpression(Operator.UNARY_PLUS, (Expression) val_peek(1).obj); }
break;
case 77:
//#line 329 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new UnaryExpression(Operator.UNARY_MINUS, (Expression) val_peek(1).obj); }
break;
case 78:
//#line 330 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new UnaryExpression(Operator.UNARY_NEGATION, (Expression) val_peek(1).obj); }
break;
case 79:
//#line 334 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 80:
//#line 335 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new FunctionExpression(symbolTable.get(val_peek(2).ival), null); }
break;
case 81:
//#line 336 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new FunctionExpression(symbolTable.get(val_peek(3).ival), (ArrayList<Expression>) val_peek(3).obj); }
break;
case 82:
//#line 340 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 83:
//#line 341 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new StructureExpression(symbolTable.get(val_peek(2).ival), symbolTable.get(val_peek(0).ival)); }
break;
case 84:
//#line 342 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new StructureExpression(symbolTable.get(val_peek(4).ival), symbolTable.get(val_peek(2).ival), symbolTable.get(val_peek(0).ival)); }
break;
case 85:
//#line 346 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<Expression> argList = new ArrayList<Expression>(); 
	                                                  argList.add((Expression) val_peek(0).obj); 
	                                                  yyval.obj = argList; }
break;
case 86:
//#line 349 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<Expression> argList = (ArrayList<Expression>) val_peek(2).obj; 
	                                                  argList.add((Expression) val_peek(0).obj); 
	                                                  yyval.obj = argList; }
break;
case 87:
//#line 358 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(0).obj; }
break;
case 88:
//#line 362 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new Variable(symbolTable.get(val_peek(0).ival)); }
break;
case 89:
//#line 363 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new ConstantExpression((Value) val_peek(0).obj); }
break;
case 90:
//#line 364 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(1).obj; }
break;
case 91:
//#line 368 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = val_peek(1).obj; }
break;
case 92:
//#line 372 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<Statement> statementList = new ArrayList<Statement>(); 
                                                      statementList.add((Statement) val_peek(0).obj); 
                                                      yyval.obj = statementList; }
break;
case 93:
//#line 375 "../../../../../src/org/geppetto/parser/parser.y"
{ ArrayList<Statement> statementList = (ArrayList<Statement>) val_peek(1).obj; 
                                                      statementList.add((Statement) val_peek(0).obj); 
                                                      yyval.obj = statementList; }
break;
case 94:
//#line 381 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new SelectionStatement( (BooleanExpression) val_peek(2).obj, (Statement) val_peek(0).obj); }
break;
case 95:
//#line 388 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new IterationStatement((BooleanExpression) val_peek(2).obj, (Statement) val_peek(0).obj); }
break;
case 96:
//#line 393 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = new EndStatement(); }
break;
case 97:
//#line 397 "../../../../../src/org/geppetto/parser/parser.y"
{ debug("** IDENTIFIER: ID: " + val_peek(0).ival + "; symb table entry: " + symbolTable.get(val_peek(0).ival)); 
                                                      yyval.ival = val_peek(0).ival; }
break;
case 98:
//#line 402 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = VariableType.INT; }
break;
case 99:
//#line 403 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = VariableType.FLOAT; }
break;
case 100:
//#line 404 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = VariableType.STRING; }
break;
case 101:
//#line 405 "../../../../../src/org/geppetto/parser/parser.y"
{ yyval.obj = VariableType.BOOLEAN; }
break;
//#line 1234 "Parser.java"
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
