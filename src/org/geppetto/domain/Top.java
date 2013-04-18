package org.geppetto.domain;

import java.io.FileReader;
import java.io.IOException;
import org.geppetto.parser.Parser;
import org.geppetto.domain.Tree;


public class Top {
	 public static void main(String args[]) throws IOException {
		    System.out.println("BYACC/Java with JFlex Calculator Demo");

		    boolean interactive = false;
		    Parser yyparser;
		    if ( args.length > 0 ) {
		      // parse a file
		      yyparser = new Parser(interactive, new FileReader(args[0]));
//		    	yyparser = new Parser(interactive, new FileReader("in.gep"));
		    }
		    else {
		      // interactive mode
		      System.out.println("[Quit with CTRL-D]");
//		      System.out.print("Expression: ");
		      interactive = false;
//			    yyparser = new Parser(interactive, new InputStreamReader(System.in));
		      yyparser = new Parser(interactive, new FileReader("in.gep"));
		    }

		   Tree AST = yyparser.makeAST();
		   AST.print();
		    
		    if (interactive) {
		      System.out.println();
		      System.out.println("Have a nice day");
		    }
		  }
}
