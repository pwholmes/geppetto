package org.geppetto.domain;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.geppetto.parser.generated.Parser;

public class Geppetto {
   public static void main(String args[]) throws IOException {
      boolean interactive = false;
      Parser yyparser = null;

      if (args.length > 0) {
         yyparser = new Parser(interactive, new FileReader(args[0]));
      } else {
         // interactive mode
         System.out.println("[Quit with CTRL-D]");
         System.out.print("Expression: ");
         interactive = true;
         yyparser = new Parser(interactive, new InputStreamReader(System.in));
      }

      Tree abstractSyntaxTree = yyparser.makeAST();
      abstractSyntaxTree.print();

      if (interactive) {
         System.out.println("\nHave a nice day");
      }
   }
}
