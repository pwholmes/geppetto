package org.geppetto.parser.generated;

import java.util.List;
import org.geppetto.parser.generated.Parser;

%%

%byaccj

%{
  private Parser yyparser;
  private List symbolTable;

  public Yylex(java.io.Reader r, Parser yyparser, List symbolTable) {
    this(r);
    this.yyparser = yyparser;
    this.symbolTable = symbolTable;
  }
%}

%%

/* operators */
"+" | 
"-" | 
"*" | 
"/" | 
"^" | 
"(" | 
")"                     { return (int) yycharat(0); }

/* newline */
\n | \r | \r\n          { return Parser.NEWLINE; }

/* float */
[0-9]+"."[0-9]+         { yyparser.yylval = new ParserVal(Double(yytext())); return Parser.FLOAT_VALUE; }

/* integer */
[0-9]+                  { yyparser.yylval = new ParserVal(Integer(yytext())); return Parser.INTEGER_VALUE; }

/* whitespace */
[ \t]+ { }

/* line terminator */
";"                     { return Parser.TERMINATOR; }

/* reserved words */
boolean                 { return Parser.BOOLEAN; }
else                    { return Parser.ELSE; }
end                     { return Parser.END; }
entity                  { return Parser.ENTITY; }
false                   { return Parser.FALSE; }
float                   { return Parser.FLOAT; }
for                     { return Parser.FOR; }
global                  { return Parser.GLOBAL; }
input                   { return Parser.INPUT; }
int                     { return Parser.INT; }
print                   { return Parser.PRINT; }
property                { return Parser.PROPERTY; }
string                  { return Parser.STRING; }
true                    { return Parser.TRUE; }
while                   { return Parser.WHILE; }

/* string values (generally identifiers) */
[a-zA-Z][a-zA-Z0-9]*    { symbolTable.Add(yytext()); yyparser.yylval = symbolTable.indexOf(yytext()); return Parser.STRING_VALUE; }  

/* error fallback */
[^]                     { System.err.println("Error: unexpected character '" + yytext() + "'"); return -1; }
