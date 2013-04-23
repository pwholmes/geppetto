/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Copyright (C) 2001 Gerwin Klein <lsf@jflex.de>                          *
 * All rights reserved.                                                    *
 *                                                                         *
 * This is a modified version of the example from                          *
 *   http://www.lincom-asg.com/~rjamison/byacc/                            *
 *                                                                         *
 * Thanks to Larry Bell and Bob Jamison for suggestions and comments.      *
 *                                                                         *
 * This program is free software; you can redistribute it and/or modify    *
 * it under the terms of the GNU General Public License. See the file      *
 * COPYRIGHT for more information.                                         *
 *                                                                         *
 * This program is distributed in the hope that it will be useful,         *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of          *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the           *
 * GNU General Public License for more details.                            *
 *                                                                         *
 * You should have received a copy of the GNU General Public License along *
 * with this program; if not, write to the Free Software Foundation, Inc., *
 * 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA                 *
 *                                                                         *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

	
%{
  import java.io.IOException;
  import java.io.Reader;
  import org.geppetto.domain.Phrase;
  import org.geppetto.domain.Tree;
  import org.geppetto.domain.TreeNode;
 
%}
      
%token NL          /* newline  */
%token <dval> NUM  /* a number */

%type <obj> exp
%type <obj> prgm

%left '-' '+'
%left '*' '/'
%left NEG          /* negation--unary minus */
%right '^'         /* exponentiation        */
      
%%

prgm : exp					{ AST = new Tree( new TreeNode(Phrase.PRGM, "", $1) ); } 
	;

exp:     NUM                { $$ = new TreeNode(Phrase.NUM, $1); }
       | exp '+' exp        { $$ = new TreeNode(Phrase.EXP, "", $1, new TreeNode(Phrase.PLUS, "+"), $3); }
       | exp '-' exp        { $$ = new TreeNode(Phrase.EXP, "", $1, new TreeNode(Phrase.MINUS, "-"), $3); }						
       | exp '*' exp        { $$ = new TreeNode(Phrase.EXP, "", $1, new TreeNode(Phrase.MULT, "*"), $3); }
       | exp '/' exp        { $$ = new TreeNode(Phrase.EXP, "", $1, new TreeNode(Phrase.DIV, "/"), $3); }						

/*      							
       | '-' exp  %prec NEG { $$ = -$2; }
       | exp '^' exp        { $$ = Math.pow($1, $3); }
       | '(' exp ')'        { $$ = $2; }
 */
       ;

%%

/* necessary functions. mainly overhead - shouldnt need to edit below
here */

  private Yylex lexer;
  boolean interactive;
	public Tree AST;	


  private int yylex () {
    int yyl_return = -1;
    try {
      yylval = new ParserVal(0);
      yyl_return = lexer.yylex();
    }
    catch (IOException e) {
      System.err.println("IO error :"+e);
    }
    return yyl_return;
  }


  public void yyerror (String error) {
    System.err.println ("Error: " + error);
  }


  public Parser(boolean interactive, Reader r) {
  	this.interactive = interactive;
    lexer = new Yylex(r, this);
    //AST = new Tree();
  }

	public Tree makeAST() {
		yyparse();
		return AST;
	}


