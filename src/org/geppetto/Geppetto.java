package org.geppetto;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.geppetto.interpreter.Interpreter;
import org.geppetto.parser.generated.Parser;

public class Geppetto {
   boolean debug         = false;
   boolean verbose       = false;
   boolean printAST      = false;
   String  inputFileName = null;
   Parser  yyparser      = null;

   public static void main(String args[]) throws IOException {
      Geppetto geppetto = new Geppetto();
      geppetto.execute(args);
   }

   public void execute(String[] args) {
      try {
         // Parse the command-line arguments
         parseArgs(args);
         
         // Parse the input file into a GeppettoProgram object (which is effectively our AST)
         yyparser = new Parser(verbose);
         GeppettoProgram program = yyparser.parse(new FileReader(inputFileName));
         if (program == null)
            throw new GeppettoException("Parser error, aborting program.");
         program.setDebug(debug);

         // Print the program state to the console.
         if (printAST)
            System.out.println(program);

         // Run the program!
         System.out.println("Running program...");
         Interpreter interpreter = new Interpreter(debug);
         interpreter.execute(program);
         System.out.println("Program execution complete.");

      } catch (Throwable e) {
         System.err.println("Error: " + e.getClass().getCanonicalName() + ": " + e.getMessage());
      }
   }

   public void parseArgs(String args[]) {
      for (int i = 0; i < args.length; i++) {
         if (args[i].equals("-d") || (args[i].equals("-debug")))
            debug = true;
         else if (args[i].equals("-v") || (args[i].equals("-verbose")))
            debug = true;
         else if (args[i].equals("-t") || (args[i].equals("-tree")))
            printAST = true;
         else if (i == args.length - 1) {
            File f = new File(args[i]);
            if (f.exists())
               inputFileName = args[i];
         } else {
            throw new GeppettoException("Unrecognized argument: " + args[i]);
         }
      }

      if (inputFileName == null)
         throw new GeppettoException("Missing argument: an input file is required.");
   }
}
