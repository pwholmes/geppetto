package org.geppetto;

import java.io.FileReader;
import java.io.IOException;
import org.geppetto.domain.GeppettoProgram;
import org.geppetto.parser.generated.Parser;

public class Geppetto {
   public static void main(String args[]) throws IOException {
      Parser yyparser = null;

      try {
         if (args.length > 0) {
            yyparser = new Parser(true);
            GeppettoProgram program = yyparser.parse(new FileReader(args[0]));
   
            /* Print entire program state to console */
            System.out.println(program);
         } else {
            System.err.println("Missing argument: an input file is required.");
         }
      } catch (Throwable e) {
         System.err.println("Error: " + e.getClass().getCanonicalName() + ": " + e.getMessage());
      }
   }
}
