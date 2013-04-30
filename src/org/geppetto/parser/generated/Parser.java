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



//#line 2 "src/org/geppetto/parser/parser.y"
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
  import org.geppetto.parser.Tree;
  import org.geppetto.parser.TreeNode;
  import org.geppetto.parser.TreeNodeType;
  import org.geppetto.expression.*;
//#line 47 "Parser.java"




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
    4,   23,   24,   25,   27,   27,   28,   28,   28,   28,
   28,   29,   34,   35,   35,   36,   36,   38,   38,   39,
   39,   39,   40,   40,   40,   40,   40,   41,   41,   41,
   42,   42,   42,   42,   37,   37,   37,   37,   44,   44,
   43,   43,   43,   46,   46,   45,   45,   45,   45,   45,
   30,   31,   31,   32,   32,   33,    7,    6,    6,    6,
    6,   26,   26,
};
final static short yylen[] = {                            2,
    4,    2,    0,    5,    1,    2,    6,    1,    3,    2,
    5,    1,    1,    1,    1,    1,    0,    1,    3,    1,
    3,    3,    1,    3,    3,    1,    2,    6,    1,    3,
    4,    1,    3,    3,    1,    1,    1,    1,    1,    1,
    2,    6,    1,    1,    1,    2,    1,    1,    1,    1,
    1,    1,    1,    1,    3,    1,    3,    1,    3,    1,
    3,    3,    1,    3,    3,    3,    3,    1,    3,    3,
    1,    3,    3,    3,    1,    2,    2,    2,    3,    5,
    1,    3,    4,    1,    2,    1,    1,    1,    1,    3,
    3,    5,    7,    5,    3,    1,    1,    1,    1,    1,
    1,    1,    1,
};
final static short yydefred[] = {                         3,
    0,    0,  101,   99,   98,    0,  100,    0,    2,    0,
    5,   97,    0,    0,    0,    6,   26,    0,    0,    0,
    0,    0,   27,   40,    0,    0,    0,    8,    0,  103,
  102,    0,   43,   41,   35,   36,   37,   39,   38,    0,
    0,    0,    0,    0,    0,   29,    0,    4,    0,    7,
    9,    0,    0,    0,    0,    0,    0,   18,    0,    0,
    0,   14,    0,   16,    0,    0,   32,   30,   28,   87,
   88,   96,    0,    0,    0,    0,    0,    0,    0,    0,
   86,    0,   89,    0,   45,   47,   48,   49,   50,   51,
   52,   53,    0,    0,    0,    0,    0,    0,    0,    0,
   81,    0,    0,   11,    0,    0,    0,    0,   31,    0,
    0,    0,    0,    0,    0,   77,   76,   78,   42,   46,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   22,   25,   19,   21,   24,
   34,   33,    0,    0,   95,   90,   91,   71,    0,   55,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   72,
   73,   74,   82,    0,    0,    0,    0,   83,    0,   94,
    0,    0,   93,
};
final static short yydgoto[] = {                          1,
    2,    8,   15,   22,    9,   26,   81,   40,   11,   27,
   28,   59,   60,   61,   62,   63,   64,   17,   45,   46,
   66,   67,   24,   32,   82,   83,   84,   85,   86,   87,
   88,   89,   90,   91,   92,   93,   94,   95,   96,   97,
   98,   99,  100,    0,  101,  165,
};
final static short yysindex[] = {                         0,
    0, -198,    0,    0,    0, -240,    0, -219,    0, -240,
    0,    0,   -4, -240, -222,    0,    0,   -1, -148,  -52,
 -260, -200,    0,    0, -235, -240,  -26,    0, -240,    0,
    0,   31,    0,    0,    0,    0,    0,    0,    0,   29,
  -32,   35, -148,   62,  -43,    0,   46,    0, -211,    0,
    0, -240, -240,   65,  932,   76,   81,    0,    3,   94,
   98,    0,   99,    0,   75,  -24,    0,    0,    0,    0,
    0,    0,  104,  105, -240,  214,  932,  214,  214,  214,
    0,   87,    0,  932,    0,    0,    0,    0,    0,    0,
    0,    0, -133,   88, -126, -176,  -49,   18,    8,  116,
    0, -100,  -99,    0, -101,  -97,  -96, -235,    0, -240,
 -260, -260,  932,  121,  958,    0,    0,    0,    0,    0,
  214,  214,  214,  214,  214,  214,  214,  214,  214,  214,
  214,  214,  214,  214,  124,    0,    0,    0,    0,    0,
    0,    0,  125,  127,    0,    0,    0,    0, -126,    0,
 -176,  -49,  -49,   18,   18,   18,   18,    8,    8,    0,
    0,    0,    0, -133,  283,  932,  932,    0, -133,    0,
  -93,  932,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  171,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   -9,    0,    0,    0,    0,    0,    0,    0,   47,    0,
    0,    0,    0,    0,    0,  -42,  -25,    0,    0,   48,
   49,    0,   50,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  118,    0,    0,    0,    0,    0,    0,
    0,    0,  -33,   -3,   25,  143,  810,  351,  705,   56,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   92,    0,
  392,  837,  869,  422,  452,  493,  523,  750,  780,    0,
    0,    0,    0,  556,    0,    0,    0,    0,  905,    0,
  896,    0,    0,
};
final static short yygindex[] = {                         0,
    0,    0,    0,    0,    0,  176,  187,   74,  179,    0,
  146,    0,    0,    0,    0,    0,    0,  177,    0,  138,
    0,   84,  173,    0,    0,  -16,  119,  -62,    0,    0,
    0,    0,    0,   33,   77, -132, 1117,   79,   83,    6,
  -48,   10,    0,    0,    0,    0,
};
final static int YYTABLESIZE=1282;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         54,
   53,   20,  164,   54,   33,   30,   54,   54,   54,   54,
  127,   54,  126,   54,   42,   31,  109,   43,   23,  110,
   12,  120,   35,   36,   37,   54,   54,   54,   54,   71,
   38,   10,  169,   71,   10,   19,   71,   71,   71,   71,
   39,   71,   14,   71,  134,   14,   56,   57,   58,  132,
  145,   21,  120,    6,  133,   71,   71,   56,   71,   25,
  131,   56,  130,    3,   56,   56,   56,   56,    4,   56,
   29,   56,    5,   21,    6,   47,    7,  154,  155,  156,
  157,   54,   20,   56,   56,   56,   56,   48,   75,   54,
   49,   54,   75,   50,  143,  144,   75,   75,   75,   23,
   75,   52,   75,  170,  171,  124,  125,   55,  114,  173,
  116,  117,  118,    3,   75,   75,   75,   75,    4,   71,
  102,   71,    5,   69,   57,  103,    7,  104,   57,  152,
  153,   57,   57,   57,   57,  108,   57,  105,   57,  158,
  159,  106,  107,  111,  112,  119,  121,   56,  122,   56,
   57,   57,   57,   57,  123,  135,   80,  136,  138,  137,
  139,  146,  140,   76,  163,  166,   79,  167,   78,  172,
    1,   17,   12,   13,   15,   58,   44,   10,   75,   58,
   75,  141,   58,   58,   58,   58,   16,   58,   51,   58,
   68,   23,   13,  142,   34,  115,   18,    0,  150,  149,
   20,   58,   58,   58,   58,  151,    0,    0,    0,    0,
    0,    0,   41,    0,   57,   44,   57,    0,    0,    0,
    0,    0,    0,    0,   54,   54,    0,   54,    0,   54,
   54,    0,   54,    0,  128,  129,    0,    0,   65,   44,
    0,    0,   54,   54,   54,   54,   80,   54,   54,   54,
   54,   54,    0,   76,   71,   71,   79,   71,   78,   71,
   71,  113,   71,    0,    0,   58,    0,   58,    0,    0,
    0,    0,   71,   71,   71,   71,   71,   71,   71,   71,
   71,   71,   56,   56,    0,   56,    0,   56,   56,    0,
   56,    0,    0,    0,    0,    0,   65,    0,    0,    0,
   56,   56,   56,   56,   56,    0,   56,   56,   56,   56,
    0,    0,    0,   75,   75,   80,   75,    0,   75,   75,
    0,   75,   76,  168,    0,   79,    0,   78,    0,    0,
    0,   75,   75,   75,   75,   75,   75,   75,   75,   75,
   75,    0,    0,    0,    0,    0,    0,    0,    0,   57,
   57,    0,   57,    0,   57,   57,    0,   57,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   57,   57,   57,
   57,   57,    0,   57,   57,   57,   57,    0,    0,    0,
    0,   70,   71,   63,   12,    0,    0,   63,    0,   30,
   63,   63,   63,    0,    0,    0,    0,   63,    0,   31,
   58,   58,    0,   58,    0,   58,   58,    0,   58,   63,
   63,   63,   63,    0,    0,    0,    0,    0,   58,   58,
   58,   58,   58,   58,   59,    0,   58,   58,   59,    0,
    0,   59,   59,   59,   59,    0,   59,    0,   59,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   59,   59,   59,   59,   64,    0,    0,    0,   64,    0,
    0,   64,   64,   64,    0,    0,    0,    0,   64,    0,
    0,   70,   71,   63,   12,   63,    0,    0,    0,   30,
   64,   64,   64,   64,   65,    0,    0,    0,   65,   31,
    0,   65,   65,   65,    0,    0,    0,    0,   65,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   65,   65,   65,   65,   59,    0,   59,    0,    0,    0,
    0,    0,    0,    0,    0,   66,    0,    0,    0,   66,
    0,    0,   66,   66,   66,    0,    0,    0,    0,   66,
   70,   71,    0,   12,   64,    0,   64,    0,   30,    0,
    0,   66,   66,   66,   66,   67,    0,    0,   31,   67,
    0,    0,   67,   67,   67,    0,    0,    0,    0,   67,
    0,    0,    0,    0,   65,    0,   65,    0,    0,    0,
    0,   67,   67,   67,   67,    0,    0,    0,   84,    0,
    0,    0,    0,    0,    0,   84,   84,    0,   84,    0,
   84,    0,    0,    0,    0,    0,    0,    0,   63,   63,
    0,   63,    0,   63,   63,   66,   63,   66,    0,    0,
    0,    0,    0,    0,    0,    0,   63,   63,   63,   63,
   63,   63,   63,   63,   63,   63,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   67,    0,   67,    0,   59,
   59,    0,   59,    0,   59,   59,    0,   59,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   59,   59,   59,
   59,   59,   59,    0,    0,   59,   59,    0,    0,   64,
   64,    0,   64,    0,   64,   64,    0,   64,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   64,   64,   64,
   64,   64,   64,   64,   64,   64,   64,    0,    0,   65,
   65,    0,   65,    0,   65,   65,    0,   65,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   65,   65,   65,
   65,   65,   65,   65,   65,   65,   65,   68,    0,    0,
    0,    0,    0,    0,   68,   68,    0,   68,    0,   68,
   66,   66,    0,   66,    0,   66,   66,    0,   66,    0,
    0,    0,    0,   68,   68,   68,   68,    0,   66,   66,
   66,   66,   66,   66,   66,   66,   66,   66,    0,    0,
   67,   67,   70,   67,    0,   67,   67,    0,   67,   70,
   70,    0,   70,    0,   70,    0,    0,    0,   67,   67,
   67,   67,   67,   67,   67,   67,   67,   67,   70,   70,
   70,   70,   69,   84,   84,    0,   84,    0,    0,   69,
   69,   84,   69,    0,   69,    0,    0,   68,    0,   68,
    0,   84,    0,    0,    0,    0,    0,    0,   69,   69,
   69,   69,   60,    0,    0,    0,   60,    0,    0,   60,
   60,   60,   60,    0,   60,    0,   60,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   60,   61,
   60,    0,   70,   61,   70,    0,   61,   61,   61,   61,
    0,   61,    0,   61,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   61,    0,   61,    0,    0,
    0,   62,   69,    0,   69,   62,    0,    0,   62,   62,
   62,   62,    0,   62,    0,   62,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   62,   92,   62,
    0,    0,   60,    0,   60,   92,    0,   85,   92,    0,
   92,    0,    0,    0,   85,   85,    0,   85,    0,   85,
    0,    0,    0,    0,   92,    0,    0,    0,    0,   61,
    0,   61,   68,   68,   80,   68,    0,   68,   68,    0,
   68,   76,    0,    0,   79,    0,   78,    0,    0,    0,
   68,   68,   68,   68,   68,   68,   68,   68,   68,   68,
   80,   62,    0,   62,    0,    0,    0,   76,    0,    0,
   79,    0,   78,    0,    0,    0,    0,   70,   70,    0,
   70,    0,   70,   70,    0,   70,    0,    0,   92,    0,
   92,    0,    0,    0,    0,   70,   70,   70,   70,   70,
   70,   70,   70,   70,   70,    0,    0,   69,   69,    0,
   69,    0,   69,   69,    0,   69,    0,    0,    0,    0,
    0,    0,    0,    0,   77,   69,   69,   69,   69,   69,
   69,   69,   69,   69,   69,    0,    0,   60,   60,    0,
   60,    0,   60,   60,    0,   60,    0,    0,    0,    0,
   77,    0,  147,    0,    0,   60,   60,   60,   60,   60,
   60,   60,   60,    0,   61,   61,    0,   61,    0,   61,
   61,    0,   61,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   61,   61,   61,   61,   61,   61,   61,   61,
    0,    0,    0,    0,    0,    0,   62,   62,    0,   62,
    0,   62,   62,    0,   62,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   62,   62,   62,   62,   62,   62,
   62,   62,    0,   92,   92,    0,   92,    0,    0,   92,
    0,   92,   85,   85,    0,   85,    0,    0,    0,    0,
   85,   92,   92,   92,   92,    0,    0,    0,    0,    0,
   85,    0,    0,    0,    0,    0,    0,    0,    0,   70,
   71,    0,   12,    0,    0,   72,    0,   30,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   31,   73,   74,
   75,    0,    0,    0,    0,   70,   71,    0,   12,    0,
    0,   72,    0,   30,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   31,   73,   74,   75,  148,    0,  148,
  148,  148,  148,  148,  148,  148,  148,  148,  160,  161,
  162,  148,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  148,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         33,
   44,   44,  135,   37,   21,  266,   40,   41,   42,   43,
   60,   45,   62,   47,   41,  276,   41,   44,   44,   44,
  261,   84,  258,  259,  260,   59,   60,   61,   62,   33,
  266,   41,  165,   37,   44,   40,   40,   41,   42,   43,
  276,   45,  265,   47,   37,  265,  258,  259,  260,   42,
  113,  274,  115,  273,   47,   59,   60,   33,   62,   61,
   43,   37,   45,  262,   40,   41,   42,   43,  267,   45,
  123,   47,  271,  274,  273,   45,  275,  126,  127,  128,
  129,  125,  125,   59,   60,   61,   62,   59,   33,  123,
  123,  125,   37,   59,  111,  112,   41,   42,   43,  125,
   45,   40,   47,  166,  167,  282,  283,   62,   76,  172,
   78,   79,   80,  262,   59,   60,   61,   62,  267,  123,
   45,  125,  271,   59,   33,   45,  275,  125,   37,  124,
  125,   40,   41,   42,   43,   61,   45,   44,   47,  130,
  131,   44,   44,   40,   40,   59,  280,  123,   61,  125,
   59,   60,   61,   62,  281,   40,   33,  258,  260,  259,
  258,   41,  259,   40,   41,   41,   43,   41,   45,  263,
    0,  125,  125,  125,  125,   33,   59,    2,  123,   37,
  125,  108,   40,   41,   42,   43,    8,   45,   43,   47,
   53,   15,    6,  110,   22,   77,   10,   -1,  122,  121,
   14,   59,   60,   61,   62,  123,   -1,   -1,   -1,   -1,
   -1,   -1,   26,   -1,  123,   29,  125,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  258,  259,   -1,  261,   -1,  263,
  264,   -1,  266,   -1,  284,  285,   -1,   -1,   52,   53,
   -1,   -1,  276,  277,  278,  279,   33,  281,  282,  283,
  284,  285,   -1,   40,  258,  259,   43,  261,   45,  263,
  264,   75,  266,   -1,   -1,  123,   -1,  125,   -1,   -1,
   -1,   -1,  276,  277,  278,  279,  280,  281,  282,  283,
  284,  285,  258,  259,   -1,  261,   -1,  263,  264,   -1,
  266,   -1,   -1,   -1,   -1,   -1,  110,   -1,   -1,   -1,
  276,  277,  278,  279,  280,   -1,  282,  283,  284,  285,
   -1,   -1,   -1,  258,  259,   33,  261,   -1,  263,  264,
   -1,  266,   40,   41,   -1,   43,   -1,   45,   -1,   -1,
   -1,  276,  277,  278,  279,  280,  281,  282,  283,  284,
  285,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  258,
  259,   -1,  261,   -1,  263,  264,   -1,  266,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  276,  277,  278,
  279,  280,   -1,  282,  283,  284,  285,   -1,   -1,   -1,
   -1,  258,  259,   33,  261,   -1,   -1,   37,   -1,  266,
   40,   41,   42,   -1,   -1,   -1,   -1,   47,   -1,  276,
  258,  259,   -1,  261,   -1,  263,  264,   -1,  266,   59,
   60,   61,   62,   -1,   -1,   -1,   -1,   -1,  276,  277,
  278,  279,  280,  281,   33,   -1,  284,  285,   37,   -1,
   -1,   40,   41,   42,   43,   -1,   45,   -1,   47,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   59,   60,   61,   62,   33,   -1,   -1,   -1,   37,   -1,
   -1,   40,   41,   42,   -1,   -1,   -1,   -1,   47,   -1,
   -1,  258,  259,  123,  261,  125,   -1,   -1,   -1,  266,
   59,   60,   61,   62,   33,   -1,   -1,   -1,   37,  276,
   -1,   40,   41,   42,   -1,   -1,   -1,   -1,   47,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   59,   60,   61,   62,  123,   -1,  125,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   33,   -1,   -1,   -1,   37,
   -1,   -1,   40,   41,   42,   -1,   -1,   -1,   -1,   47,
  258,  259,   -1,  261,  123,   -1,  125,   -1,  266,   -1,
   -1,   59,   60,   61,   62,   33,   -1,   -1,  276,   37,
   -1,   -1,   40,   41,   42,   -1,   -1,   -1,   -1,   47,
   -1,   -1,   -1,   -1,  123,   -1,  125,   -1,   -1,   -1,
   -1,   59,   60,   61,   62,   -1,   -1,   -1,   33,   -1,
   -1,   -1,   -1,   -1,   -1,   40,   41,   -1,   43,   -1,
   45,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  258,  259,
   -1,  261,   -1,  263,  264,  123,  266,  125,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  276,  277,  278,  279,
  280,  281,  282,  283,  284,  285,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  123,   -1,  125,   -1,  258,
  259,   -1,  261,   -1,  263,  264,   -1,  266,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  276,  277,  278,
  279,  280,  281,   -1,   -1,  284,  285,   -1,   -1,  258,
  259,   -1,  261,   -1,  263,  264,   -1,  266,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  276,  277,  278,
  279,  280,  281,  282,  283,  284,  285,   -1,   -1,  258,
  259,   -1,  261,   -1,  263,  264,   -1,  266,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  276,  277,  278,
  279,  280,  281,  282,  283,  284,  285,   33,   -1,   -1,
   -1,   -1,   -1,   -1,   40,   41,   -1,   43,   -1,   45,
  258,  259,   -1,  261,   -1,  263,  264,   -1,  266,   -1,
   -1,   -1,   -1,   59,   60,   61,   62,   -1,  276,  277,
  278,  279,  280,  281,  282,  283,  284,  285,   -1,   -1,
  258,  259,   33,  261,   -1,  263,  264,   -1,  266,   40,
   41,   -1,   43,   -1,   45,   -1,   -1,   -1,  276,  277,
  278,  279,  280,  281,  282,  283,  284,  285,   59,   60,
   61,   62,   33,  258,  259,   -1,  261,   -1,   -1,   40,
   41,  266,   43,   -1,   45,   -1,   -1,  123,   -1,  125,
   -1,  276,   -1,   -1,   -1,   -1,   -1,   -1,   59,   60,
   61,   62,   33,   -1,   -1,   -1,   37,   -1,   -1,   40,
   41,   42,   43,   -1,   45,   -1,   47,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   59,   33,
   61,   -1,  123,   37,  125,   -1,   40,   41,   42,   43,
   -1,   45,   -1,   47,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   59,   -1,   61,   -1,   -1,
   -1,   33,  123,   -1,  125,   37,   -1,   -1,   40,   41,
   42,   43,   -1,   45,   -1,   47,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   59,   33,   61,
   -1,   -1,  123,   -1,  125,   40,   -1,   33,   43,   -1,
   45,   -1,   -1,   -1,   40,   41,   -1,   43,   -1,   45,
   -1,   -1,   -1,   -1,   59,   -1,   -1,   -1,   -1,  123,
   -1,  125,  258,  259,   33,  261,   -1,  263,  264,   -1,
  266,   40,   -1,   -1,   43,   -1,   45,   -1,   -1,   -1,
  276,  277,  278,  279,  280,  281,  282,  283,  284,  285,
   33,  123,   -1,  125,   -1,   -1,   -1,   40,   -1,   -1,
   43,   -1,   45,   -1,   -1,   -1,   -1,  258,  259,   -1,
  261,   -1,  263,  264,   -1,  266,   -1,   -1,  123,   -1,
  125,   -1,   -1,   -1,   -1,  276,  277,  278,  279,  280,
  281,  282,  283,  284,  285,   -1,   -1,  258,  259,   -1,
  261,   -1,  263,  264,   -1,  266,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  123,  276,  277,  278,  279,  280,
  281,  282,  283,  284,  285,   -1,   -1,  258,  259,   -1,
  261,   -1,  263,  264,   -1,  266,   -1,   -1,   -1,   -1,
  123,   -1,  125,   -1,   -1,  276,  277,  278,  279,  280,
  281,  282,  283,   -1,  258,  259,   -1,  261,   -1,  263,
  264,   -1,  266,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  276,  277,  278,  279,  280,  281,  282,  283,
   -1,   -1,   -1,   -1,   -1,   -1,  258,  259,   -1,  261,
   -1,  263,  264,   -1,  266,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  276,  277,  278,  279,  280,  281,
  282,  283,   -1,  258,  259,   -1,  261,   -1,   -1,  264,
   -1,  266,  258,  259,   -1,  261,   -1,   -1,   -1,   -1,
  266,  276,  277,  278,  279,   -1,   -1,   -1,   -1,   -1,
  276,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  258,
  259,   -1,  261,   -1,   -1,  264,   -1,  266,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  276,  277,  278,
  279,   -1,   -1,   -1,   -1,  258,  259,   -1,  261,   -1,
   -1,  264,   -1,  266,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  276,  277,  278,  279,  121,   -1,  123,
  124,  125,  126,  127,  128,  129,  130,  131,  132,  133,
  134,  135,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  165,
};
}
final static short YYFINAL=1;
final static short YYMAXTOKEN=285;
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
null,null,null,null,null,null,null,null,"NEWLINE","INTEGER_LITERAL",
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
"variableDeclaration : typeSpecifier identifier '=' literalValue ';'",
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
"attributeInitializer : identifier '=' literalValue",
"literalValue : INTEGER_LITERAL",
"literalValue : FLOAT_LITERAL",
"literalValue : STRING_LITERAL",
"literalValue : TRUE",
"literalValue : FALSE",
"ruleDeclarationList : rule",
"ruleDeclarationList : ruleDeclarationList rule",
"rule : RULE condition '-' '>' behavior ';'",
"condition : booleanExpression",
"behavior : statementList",
"statementList : statement",
"statementList : statementList statement",
"statement : expressionStatement",
"statement : compoundStatement",
"statement : selectionStatement",
"statement : iterationStatement",
"statement : endStatement",
"expressionStatement : expression",
"expression : assignmentExpression",
"assignmentExpression : logicalOrExpression",
"assignmentExpression : unaryExpression '=' assignmentExpression",
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
"unaryExpression : '+' expression",
"unaryExpression : '-' expression",
"unaryExpression : '!' expression",
"structureExpression : identifier '.' identifier",
"structureExpression : identifier '.' identifier '.' identifier",
"functionExpression : primaryExpression",
"functionExpression : functionExpression '(' ')'",
"functionExpression : functionExpression '(' argumentExpressionList ')'",
"argumentExpressionList : logicalOrExpression",
"argumentExpressionList : argumentExpressionList logicalOrExpression",
"primaryExpression : identifier",
"primaryExpression : INTEGER_LITERAL",
"primaryExpression : FLOAT_LITERAL",
"primaryExpression : booleanExpression",
"primaryExpression : '(' expression ')'",
"compoundStatement : '{' statementList '}'",
"selectionStatement : IF '(' booleanExpression ')' statement",
"selectionStatement : IF '(' booleanExpression ')' statement ELSE statement",
"iterationStatement : WHILE '(' booleanExpression ')' statement",
"iterationStatement : FOREACH identifier statement",
"endStatement : END",
"identifier : IDENTIFIER",
"typeSpecifier : INT",
"typeSpecifier : FLOAT",
"typeSpecifier : STRING",
"typeSpecifier : BOOLEAN",
"booleanExpression : TRUE",
"booleanExpression : FALSE",
};

//#line 381 "src/org/geppetto/parser/parser.y"

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
//#line 743 "Parser.java"
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
//#line 68 "src/org/geppetto/parser/parser.y"
{
                                                      ArrayList<Variable> globalVariables = (ArrayList<Variable>) val_peek(3).obj;
                                                      ArrayList<PropertyDefinition> propertyDefinitions = (ArrayList<PropertyDefinition>) val_peek(2).obj;
                                                      ArrayList<Entity> entities =  (ArrayList<Entity>) val_peek(1).obj;
                                                      ArrayList<Rule> rules = (ArrayList<Rule>) val_peek(0).obj; 
                                                      geppettoProgram = new GeppettoProgram(globalVariables, propertyDefinitions, entities, rules); }
break;
case 2:
//#line 77 "src/org/geppetto/parser/parser.y"
{ ArrayList<Variable> variables = (ArrayList<Variable>) val_peek(1).obj;
                                                      if (variables == null)
                                                         variables = new ArrayList<Variable>();
                                                      variables.add((Variable) val_peek(1).obj); 
                                                      yyval.obj = variables; }
break;
case 3:
//#line 82 "src/org/geppetto/parser/parser.y"
{ yyval.obj = new ArrayList<Variable>(); }
break;
case 4:
//#line 86 "src/org/geppetto/parser/parser.y"
{ yyval.obj = new Variable((VariableType) val_peek(4).obj, symbolTable.get(val_peek(3).ival), val_peek(1).obj); }
break;
case 5:
//#line 90 "src/org/geppetto/parser/parser.y"
{ propertyDefinitions = new ArrayList<PropertyDefinition>();
                                                      propertyDefinitions.add((PropertyDefinition) val_peek(0).obj); 
                                                      yyval.obj = propertyDefinitions; }
break;
case 6:
//#line 93 "src/org/geppetto/parser/parser.y"
{ ArrayList<PropertyDefinition> propertyDefs = (ArrayList<PropertyDefinition>) val_peek(1).obj;
                                                      propertyDefs.add((PropertyDefinition) val_peek(0).obj); 
                                                      yyval.obj = propertyDefs; }
break;
case 7:
//#line 99 "src/org/geppetto/parser/parser.y"
{ PropertyDefinition propertyDef = new PropertyDefinition(symbolTable.get(val_peek(4).ival));
                                                      propertyDef.addAttributeDefinitions((ArrayList<AttributeDefinition>) val_peek(2).obj);
                                                      yyval.obj = propertyDef; }
break;
case 8:
//#line 105 "src/org/geppetto/parser/parser.y"
{ ArrayList<AttributeDefinition> attributeDefs = new ArrayList<AttributeDefinition>();
                                                      attributeDefs.add((AttributeDefinition) val_peek(0).obj); 
                                                      yyval.obj = attributeDefs; }
break;
case 9:
//#line 108 "src/org/geppetto/parser/parser.y"
{ ArrayList<AttributeDefinition> attributeDefs = (ArrayList<AttributeDefinition>) val_peek(2).obj;
                                                      attributeDefs.add((AttributeDefinition) val_peek(0).obj); 
                                                      yyval.obj = attributeDefs; }
break;
case 10:
//#line 114 "src/org/geppetto/parser/parser.y"
{ yyval.obj = new AttributeDefinition((VariableType)val_peek(1).obj, symbolTable.get(val_peek(0).ival)); }
break;
case 11:
//#line 115 "src/org/geppetto/parser/parser.y"
{ AttributeDefinition attributeDef = new AttributeDefinition((VariableType)val_peek(4).obj, symbolTable.get(val_peek(3).ival));
                                                      attributeDef.setConstraint((AttributeConstraint) val_peek(1).obj); 
                                                      yyval.obj = attributeDef; }
break;
case 12:
//#line 121 "src/org/geppetto/parser/parser.y"
{ yyval.obj = new AttributeConstraintStringSet((Set<String>) val_peek(0).obj); }
break;
case 13:
//#line 122 "src/org/geppetto/parser/parser.y"
{ yyval.obj = new AttributeConstraintIntegerSet((Set<Integer>) val_peek(0).obj); }
break;
case 14:
//#line 123 "src/org/geppetto/parser/parser.y"
{ yyval.obj = new AttributeConstraintIntegerRange((ArrayList<Integer>)val_peek(0).obj); }
break;
case 15:
//#line 124 "src/org/geppetto/parser/parser.y"
{ yyval.obj = new AttributeConstraintFloatSet((Set<Float>)val_peek(0).obj); }
break;
case 16:
//#line 125 "src/org/geppetto/parser/parser.y"
{ yyval.obj = new AttributeConstraintFloatRange((ArrayList<Float>)val_peek(0).obj); }
break;
case 18:
//#line 130 "src/org/geppetto/parser/parser.y"
{ HashSet<String> stringSet = new HashSet<String>(); 
                                                      stringSet.add(symbolTable.get(val_peek(0).ival)); 
                                                      yyval.obj = stringSet; }
break;
case 19:
//#line 133 "src/org/geppetto/parser/parser.y"
{ HashSet<String> stringSet = (HashSet<String>) val_peek(2).obj;
                                                      stringSet.add(symbolTable.get(val_peek(0).ival)); 
                                                      yyval.obj = stringSet; }
break;
case 20:
//#line 139 "src/org/geppetto/parser/parser.y"
{ HashSet<Integer> intSet = new HashSet<Integer>(); 
                                                      intSet.add(new Integer(val_peek(0).ival)); 
                                                      yyval.obj = intSet; }
break;
case 21:
//#line 142 "src/org/geppetto/parser/parser.y"
{ HashSet<Integer> intSet = (HashSet<Integer>) val_peek(2).obj; 
                                                      intSet.add(new Integer(val_peek(0).ival)); 
                                                      yyval.obj = intSet; }
break;
case 22:
//#line 148 "src/org/geppetto/parser/parser.y"
{ ArrayList<Integer> intList = new ArrayList<Integer>();
                                                      intList.add(val_peek(2).ival);
                                                      intList.add(val_peek(1).ival);
                                                      yyval.obj = intList; }
break;
case 23:
//#line 155 "src/org/geppetto/parser/parser.y"
{ HashSet<Float> floatSet = new HashSet<Float>(); 
                                                      floatSet.add(new Float(val_peek(0).dval)); 
                                                      yyval.obj = floatSet; }
break;
case 24:
//#line 158 "src/org/geppetto/parser/parser.y"
{ HashSet<Float> floatSet = (HashSet<Float>) val_peek(2).obj; 
                                                      floatSet.add(new Float(val_peek(0).dval)); 
                                                      yyval.obj = floatSet; }
break;
case 25:
//#line 164 "src/org/geppetto/parser/parser.y"
{ ArrayList<Float> floatList = new ArrayList<Float>();
                                                      floatList.add(new Float(val_peek(2).dval));
                                                      floatList.add(new Float(val_peek(1).dval));
                                                      yyval.obj = floatList; }
break;
case 26:
//#line 171 "src/org/geppetto/parser/parser.y"
{ ArrayList<Entity> entities = new ArrayList<Entity>();  
                                                      entities.add((Entity) val_peek(0).obj); 
                                                      yyval.obj = entities; }
break;
case 27:
//#line 174 "src/org/geppetto/parser/parser.y"
{ ArrayList<Entity> entities = (ArrayList<Entity>) val_peek(1).obj;  
                                                      entities.add((Entity) val_peek(0).obj); 
                                                      yyval.obj = entities; }
break;
case 28:
//#line 180 "src/org/geppetto/parser/parser.y"
{ Entity entity = new Entity(symbolTable.get(val_peek(4).ival));
                                                      entity.addProperties((List<Property>) val_peek(2).obj);
                                                      yyval.obj = entity; }
break;
case 29:
//#line 186 "src/org/geppetto/parser/parser.y"
{ ArrayList<Property> properties = new ArrayList<Property>();
                                                      properties.add((Property) val_peek(0).obj); 
                                                      yyval.obj = properties;}
break;
case 30:
//#line 189 "src/org/geppetto/parser/parser.y"
{ ArrayList<Property> properties = (ArrayList<Property>) val_peek(2).obj;
                                                      properties.add((Property) val_peek(0).obj); 
                                                      yyval.obj = properties; }
break;
case 31:
//#line 195 "src/org/geppetto/parser/parser.y"
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
//#line 209 "src/org/geppetto/parser/parser.y"
{ ArrayList<AttributeInitializer> attributeInitializers = new ArrayList<AttributeInitializer>();
                                                      attributeInitializers.add((AttributeInitializer) val_peek(0).obj);
                                                      yyval.obj = attributeInitializers; }
break;
case 33:
//#line 212 "src/org/geppetto/parser/parser.y"
{ ArrayList<AttributeInitializer> attributeInitiazers = (ArrayList<AttributeInitializer>) val_peek(2).obj;
                                                      attributeInitiazers.add((AttributeInitializer) val_peek(0).obj);
                                                      yyval.obj = attributeInitiazers; }
break;
case 34:
//#line 218 "src/org/geppetto/parser/parser.y"
{ yyval.obj = new AttributeInitializer(symbolTable.get(val_peek(2).ival), (Value)val_peek(0).obj); }
break;
case 35:
//#line 222 "src/org/geppetto/parser/parser.y"
{ yyval.obj = new Value(val_peek(0).ival); }
break;
case 36:
//#line 223 "src/org/geppetto/parser/parser.y"
{ Float fval = new Float(val_peek(0).dval); yyval.obj = new Value(fval); }
break;
case 37:
//#line 224 "src/org/geppetto/parser/parser.y"
{ yyval.obj = new Value(symbolTable.get(val_peek(0).ival)); }
break;
case 38:
//#line 225 "src/org/geppetto/parser/parser.y"
{ yyval.obj = new Value(true); }
break;
case 39:
//#line 226 "src/org/geppetto/parser/parser.y"
{ yyval.obj = new Value(false); }
break;
case 40:
//#line 230 "src/org/geppetto/parser/parser.y"
{ ArrayList<Rule> rules = new ArrayList<Rule>();  
                                                      rules.add((Rule) val_peek(0).obj); 
                                                      yyval.obj = rules; }
break;
case 41:
//#line 233 "src/org/geppetto/parser/parser.y"
{ ArrayList<Rule> rules = (ArrayList<Rule>) val_peek(1).obj;  
                                                      rules.add((Rule) val_peek(0).obj); 
                                                      yyval.obj = rules; }
break;
case 42:
//#line 239 "src/org/geppetto/parser/parser.y"
{ yyval.obj = new Rule((Condition) val_peek(4).obj, (Behavior) val_peek(2).obj); }
break;
case 43:
//#line 243 "src/org/geppetto/parser/parser.y"
{ yyval.obj = new Condition(); }
break;
case 44:
//#line 247 "src/org/geppetto/parser/parser.y"
{ yyval.obj = new Behavior(); }
break;
case 45:
//#line 251 "src/org/geppetto/parser/parser.y"
{ yyval.obj = new StatementList( (Statement) val_peek(0).obj); }
break;
case 46:
//#line 252 "src/org/geppetto/parser/parser.y"
{ yyval.obj = new StatementList((StatementList)val_peek(1).obj, (Statement)val_peek(0).obj); }
break;
case 47:
//#line 256 "src/org/geppetto/parser/parser.y"
{ yyval.obj = new Statement((ExpressionStatement)val_peek(0).obj); }
break;
case 48:
//#line 257 "src/org/geppetto/parser/parser.y"
{ yyval.obj = new Statement((CompoundStatement)val_peek(0).obj); }
break;
case 49:
//#line 258 "src/org/geppetto/parser/parser.y"
{ yyval.obj = new Statement((SelectionStatement)val_peek(0).obj); }
break;
case 50:
//#line 259 "src/org/geppetto/parser/parser.y"
{ yyval.obj = new Statement((IterationStatement)val_peek(0).obj); }
break;
case 51:
//#line 260 "src/org/geppetto/parser/parser.y"
{ yyval.obj = new Statement((EndStatement)val_peek(0).obj); }
break;
case 52:
//#line 265 "src/org/geppetto/parser/parser.y"
{ yyval.obj = new ExpressionStatement((Expression) val_peek(0).obj); }
break;
case 53:
//#line 269 "src/org/geppetto/parser/parser.y"
{ yyval.obj = new Expression((AssignmentExpression) val_peek(0).obj); }
break;
case 54:
//#line 273 "src/org/geppetto/parser/parser.y"
{ yyval.obj = new AssignmentExpression((LogicalOrExpression) val_peek(0).obj); }
break;
case 55:
//#line 274 "src/org/geppetto/parser/parser.y"
{yyval.obj = new AssignmentExpression( (UnaryExpression) val_peek(2).obj, true, (AssignmentExpression) val_peek(0).obj); }
break;
case 56:
//#line 278 "src/org/geppetto/parser/parser.y"
{ yyval.obj = new LogicalOrExpression((LogicalAndExpression) val_peek(0).obj); }
break;
case 57:
//#line 279 "src/org/geppetto/parser/parser.y"
{ yyval.obj = new LogicalOrExpression( (LogicalOrExpression) val_peek(2).obj, true, (LogicalAndExpression) val_peek(0).obj); }
break;
case 58:
//#line 283 "src/org/geppetto/parser/parser.y"
{ yyval.obj = new LogicalAndExpression((EqualityExpression)val_peek(0).obj); }
break;
case 59:
//#line 284 "src/org/geppetto/parser/parser.y"
{ yyval.obj = new LogicalAndExpression( (LogicalAndExpression) val_peek(2).obj, true, (EqualityExpression)val_peek(0).obj  ); }
break;
case 60:
//#line 288 "src/org/geppetto/parser/parser.y"
{ yyval.obj = new EqualityExpression((RelationalExpression)val_peek(0).obj); }
break;
case 61:
//#line 289 "src/org/geppetto/parser/parser.y"
{ yyval.obj = new EqualityExpression( (EqualityExpression)val_peek(2).obj, EqualityOperator.EQUALTO, (RelationalExpression) val_peek(0).obj);}
break;
case 62:
//#line 290 "src/org/geppetto/parser/parser.y"
{ yyval.obj = new EqualityExpression( (EqualityExpression)val_peek(2).obj, EqualityOperator.NOT_EQUALTO, (RelationalExpression) val_peek(0).obj);}
break;
case 63:
//#line 294 "src/org/geppetto/parser/parser.y"
{ yyval.obj = new RelationalExpression((AdditiveExpression)val_peek(0).obj); }
break;
case 64:
//#line 295 "src/org/geppetto/parser/parser.y"
{yyval.obj = new RelationalExpression( (RelationalExpression)val_peek(2).obj, RelationalOperator.GREATER_THAN, (AdditiveExpression)val_peek(0).obj);}
break;
case 65:
//#line 296 "src/org/geppetto/parser/parser.y"
{yyval.obj = new RelationalExpression( (RelationalExpression)val_peek(2).obj, RelationalOperator.LESS_THAN, (AdditiveExpression)val_peek(0).obj);}
break;
case 66:
//#line 297 "src/org/geppetto/parser/parser.y"
{yyval.obj = new RelationalExpression( (RelationalExpression)val_peek(2).obj, RelationalOperator.GREATER_THAN_OR_EQUAL, (AdditiveExpression)val_peek(0).obj);}
break;
case 67:
//#line 298 "src/org/geppetto/parser/parser.y"
{yyval.obj = new RelationalExpression( (RelationalExpression)val_peek(2).obj, RelationalOperator.LESS_THAN_OR_EQUAL, (AdditiveExpression)val_peek(0).obj);}
break;
case 68:
//#line 302 "src/org/geppetto/parser/parser.y"
{ yyval.obj = new AdditiveExpression((MultiplicativeExpression)val_peek(0).obj); }
break;
case 69:
//#line 303 "src/org/geppetto/parser/parser.y"
{yyval.obj = new AdditiveExpression((AdditiveExpression)val_peek(2).obj, AdditiveOperator.PLUS, (MultiplicativeExpression)val_peek(0).obj);}
break;
case 70:
//#line 304 "src/org/geppetto/parser/parser.y"
{yyval.obj = new AdditiveExpression((AdditiveExpression)val_peek(2).obj, AdditiveOperator.MINUS, (MultiplicativeExpression)val_peek(0).obj);}
break;
case 71:
//#line 308 "src/org/geppetto/parser/parser.y"
{ yyval.obj = new MultiplicativeExpression((UnaryExpression)val_peek(0).obj); }
break;
case 72:
//#line 309 "src/org/geppetto/parser/parser.y"
{yyval.obj = new MultiplicativeExpression((MultiplicativeExpression)val_peek(2).obj, MultiplicativeOperator.MULT, (UnaryExpression)val_peek(0).obj);}
break;
case 73:
//#line 310 "src/org/geppetto/parser/parser.y"
{yyval.obj = new MultiplicativeExpression((MultiplicativeExpression)val_peek(2).obj, MultiplicativeOperator.DIV, (UnaryExpression)val_peek(0).obj);}
break;
case 74:
//#line 311 "src/org/geppetto/parser/parser.y"
{yyval.obj = new MultiplicativeExpression((MultiplicativeExpression)val_peek(2).obj, MultiplicativeOperator.MOD, (UnaryExpression)val_peek(0).obj);}
break;
case 75:
//#line 315 "src/org/geppetto/parser/parser.y"
{ yyval.obj = new UnaryExpression((FunctionExpression)val_peek(0).obj); }
break;
case 76:
//#line 316 "src/org/geppetto/parser/parser.y"
{yyval.obj = new UnaryExpression(UnaryOperator.UNARY_PLUS ,(Expression)val_peek(1).obj);}
break;
case 77:
//#line 317 "src/org/geppetto/parser/parser.y"
{yyval.obj = new UnaryExpression(UnaryOperator.UNARY_MINUS ,(Expression)val_peek(1).obj);}
break;
case 78:
//#line 318 "src/org/geppetto/parser/parser.y"
{yyval.obj = new UnaryExpression(UnaryOperator.NOT ,(Expression)val_peek(1).obj);}
break;
case 79:
//#line 322 "src/org/geppetto/parser/parser.y"
{yyval.obj = new StructureExpression(symbolTable.get(val_peek(2).ival), symbolTable.get(val_peek(0).ival) );}
break;
case 80:
//#line 323 "src/org/geppetto/parser/parser.y"
{yyval.obj = new StructureExpression(symbolTable.get(val_peek(4).ival), symbolTable.get(val_peek(2).ival), symbolTable.get(val_peek(0).ival) );}
break;
case 81:
//#line 327 "src/org/geppetto/parser/parser.y"
{ yyval.obj = new FunctionExpression((PrimaryExpression)val_peek(0).obj); }
break;
case 82:
//#line 328 "src/org/geppetto/parser/parser.y"
{ yyval.obj = new FunctionExpression((FunctionExpression)val_peek(2).obj); }
break;
case 83:
//#line 329 "src/org/geppetto/parser/parser.y"
{ yyval.obj = new FunctionExpression((ArgumentExpressionList)val_peek(1).obj); }
break;
case 84:
//#line 333 "src/org/geppetto/parser/parser.y"
{ yyval.obj = new ArgumentExpressionList((LogicalOrExpression)val_peek(0).obj); }
break;
case 85:
//#line 334 "src/org/geppetto/parser/parser.y"
{ yyval.obj = new ArgumentExpressionList((ArgumentExpressionList) val_peek(1).obj, (LogicalOrExpression)val_peek(0).obj); }
break;
case 86:
//#line 338 "src/org/geppetto/parser/parser.y"
{ yyval.obj = new PrimaryExpression(symbolTable.get(val_peek(0).ival)); }
break;
case 87:
//#line 339 "src/org/geppetto/parser/parser.y"
{ yyval.obj = new PrimaryExpression(val_peek(0).ival); }
break;
case 88:
//#line 340 "src/org/geppetto/parser/parser.y"
{ yyval.obj = new PrimaryExpression(val_peek(0).dval); }
break;
case 89:
//#line 341 "src/org/geppetto/parser/parser.y"
{ yyval.obj = new PrimaryExpression( (BooleanExpression) val_peek(0).obj);  }
break;
case 90:
//#line 342 "src/org/geppetto/parser/parser.y"
{ yyval.obj = new PrimaryExpression((Expression)val_peek(2).obj); }
break;
case 91:
//#line 346 "src/org/geppetto/parser/parser.y"
{ yyval.obj = new CompoundStatement( (StatementList)val_peek(1).obj) ;}
break;
case 92:
//#line 350 "src/org/geppetto/parser/parser.y"
{ yyval.obj = new SelectionStatement( (BooleanExpression)val_peek(2).obj, (Statement)val_peek(0).obj); }
break;
case 93:
//#line 351 "src/org/geppetto/parser/parser.y"
{ yyval.obj = new SelectionStatement( (BooleanExpression)val_peek(4).obj, (Statement)val_peek(2).obj, true, (Statement)val_peek(0).obj); }
break;
case 94:
//#line 355 "src/org/geppetto/parser/parser.y"
{ yyval.obj = new IterationStatement((BooleanExpression)val_peek(2).obj, (Statement)val_peek(0).obj); }
break;
case 95:
//#line 356 "src/org/geppetto/parser/parser.y"
{ yyval.obj = new IterationStatement((Identifier)val_peek(1).obj, (Statement)val_peek(0).obj); }
break;
case 96:
//#line 360 "src/org/geppetto/parser/parser.y"
{ yyval.obj = new EndStatement(); }
break;
case 97:
//#line 364 "src/org/geppetto/parser/parser.y"
{ debug("** IDENTIFIER: ID: " + val_peek(0).ival + "; symb table entry: " + symbolTable.get(val_peek(0).ival)); 
                                                      yyval.ival = val_peek(0).ival; }
break;
case 98:
//#line 369 "src/org/geppetto/parser/parser.y"
{ yyval.obj = VariableType.INT; }
break;
case 99:
//#line 370 "src/org/geppetto/parser/parser.y"
{ yyval.obj = VariableType.FLOAT; }
break;
case 100:
//#line 371 "src/org/geppetto/parser/parser.y"
{ yyval.obj = VariableType.STRING; }
break;
case 101:
//#line 372 "src/org/geppetto/parser/parser.y"
{ yyval.obj = VariableType.BOOLEAN; }
break;
case 102:
//#line 376 "src/org/geppetto/parser/parser.y"
{ yyval.ival = 1; }
break;
case 103:
//#line 377 "src/org/geppetto/parser/parser.y"
{ yyval.ival = 0; }
break;
//#line 1368 "Parser.java"
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
