package org.geppetto.domain.analyzer;

public class SemanticAnalyzerException extends Exception {
   private static final long serialVersionUID = 598875955889835225L;

   @SuppressWarnings("unused")
   private SemanticAnalyzerException() {
   }
   
   public SemanticAnalyzerException(String message) {
      super(message);
   }
   
   public SemanticAnalyzerException(String message, Throwable exception) {
      super(message, exception);
   }
}
