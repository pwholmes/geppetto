package org.geppetto;

import java.io.FileReader;
import java.io.IOException;
import org.geppetto.parser.generated.Parser;

public class Geppetto {
   public static void main(String args[]) throws IOException {
      Parser yyparser = null;

      if (args.length > 0) {
         yyparser = new Parser(true);
         yyparser.parse(new FileReader(args[0]));
      } else {
         System.err.println("Missing argument: an input file is required.");
      }
   }
}
