package org.geppetto.domain.expression;

public class StructureExpression {
   private String name1;
   private String name2;
   private String name3;
   
   @SuppressWarnings("unused")
   private StructureExpression() {}
   
   public StructureExpression(String name1, String name2) {
      this.name1 = name1;
      this.name2 = name2;
   }

   public StructureExpression(String name1, String name2, String name3) {
      this.name1 = name1;
      this.name2 = name2;
      this.name3 = name3;
   }
}
