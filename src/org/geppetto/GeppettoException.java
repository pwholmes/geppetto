package org.geppetto;

public class GeppettoException extends Error {
   private static final long serialVersionUID = 1L;

   public GeppettoException() {
      super();
   }

   public GeppettoException(String message) {
      super(message);
   }

   public GeppettoException(Throwable cause) {
      super(cause);
   }

   public GeppettoException(String message, Throwable cause) {
      super(message, cause);
   }

}
