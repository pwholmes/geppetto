package org.geppetto.parser.generated;

import java.util.List;
import org.geppetto.parser.generated.Parser;

%%

%byaccj

%{
  private Parser yyparser;
  private List<String> symbolTable;

  public Yylex(java.io.Reader r, Parser yyparser, List<String> symbolTable) {
    this(r);
    this.yyparser = yyparser;
    this.symbolTable = symbolTable;
  }
%}

%%

/* whitespace */
[ \t]+                  { } /* ignore */

/* newline */
\n | \r | \r\n          { } /* ignore as whitespace */

/* symbols and operators */
/* JFlex crashes on startup if I try to put these symbols into a bracketed regexp: [\+\*\^\/-(){}=,;] 
   I'm probably missing or messing up an escape sequence, but the following works, so it's good enough: */
"->"                    { return Parser.INFERS; } 

"+" | 
"-" | 
"*" | 
"/" | 
"^" | 
"(" | 
")" |
"{" |
"}" |
"=" |
">" |
"<" |
"!" |
"|" |
"&" |
"," |
";"                     { return (int) yycharat(0); } /* pass through to parser untouched */

/* float literals */
[0-9]+"."[0-9]+         { yyparser.yylval = new ParserVal(Double.parseDouble(yytext())); return Parser.FLOAT_LITERAL; }

/* integer literals */
[0-9]+                  { yyparser.yylval = new ParserVal(Integer.parseInt(yytext())); return Parser.INTEGER_LITERAL; }

/* string literals */
\"[^\"]*\"              { symbolTable.add(yytext()); yyparser.yylval.ival = symbolTable.indexOf(yytext()); return Parser.STRING_LITERAL; }  

/* reserved words */
boolean                 { return Parser.BOOLEAN; }
else                    { return Parser.ELSE; }
end                     { return Parser.END; }
entity                  { return Parser.ENTITY; }
false                   { return Parser.FALSE; }
float                   { return Parser.FLOAT; }
for                     { return Parser.FOR; }
foreach                 { return Parser.FOREACH; }
global                  { return Parser.GLOBAL; }
if                      { return Parser.IF; }
input                   { return Parser.INPUT; }
int                     { return Parser.INT; }
print                   { return Parser.PRINT; }
property                { return Parser.PROPERTY; }
rule                    { return Parser.RULE; }
string                  { return Parser.STRING; }
true                    { return Parser.TRUE; }
while                   { return Parser.WHILE; }

/* identifiers */
[a-zA-Z][a-zA-Z0-9]*    { symbolTable.add(yytext()); yyparser.yylval = new ParserVal(symbolTable.indexOf(yytext())); return Parser.IDENTIFIER; }  

/* error fallback */
[^]                     { System.err.println("Error: unexpected character '" + yytext() + "'"); return -1; }
