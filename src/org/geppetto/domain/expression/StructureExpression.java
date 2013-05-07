package org.geppetto.domain.expression;

import org.geppetto.domain.declaration.Value;


public class StructureExpression implements Expression {
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

   public String getName1() {
      return name1;
   }

   public String getName2() {
      return name2;
   }

   public String getName3() {
      return name3;
   }

   @Override
   public boolean isLValue() {
      return true;
   }

   @Override
   public void setValue(Value value) {
      // TODO setValue() for StructureExpression.  What do we want to do with this thing???
   }

   @Override
   public Value getValue() {
      // TODO getValue() for StructureExpression.  What do we want to do with this thing???
      return null;
   }
   
   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder();
      
      sb.append("{").append(this.getClass().getSimpleName()).append(": ");
      sb.append("name1: ").append(getName1());
      sb.append("; name2: ").append(getName2());
      sb.append("; name3: ").append(getName3());
      sb.append("}");
      
      return sb.toString();
   }

}
