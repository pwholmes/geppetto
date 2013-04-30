#!/usr/bin/bash

cd src/org/geppetto/parser #cwd = geppetto-master/src/org/geppetto/parser
jflex lexer.flex
mv Yylex.java generated/Yylex.java
cd ../../../../  #cwd = geppetto-master
chmod a+x tools/byaccj/linux/yacc.linux
./tools/byaccj/linux/yacc.linux -J -d -v -t -Jpackage=org.geppetto.parser.generated src/org/geppetto/parser/parser.y
mv *.java src/org/geppetto/parser/generated/
mv y.output src/org/geppetto/parser/generated/

# build: a way that works: file->new->java project [name="4-25-r-1"]
# cd geppetto-master
# cp * into wksp/4-25-r-1/
#
# run config: prog arg: testdoc/math.gep