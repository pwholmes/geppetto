package org.geppetto.domain;

public class AttributeDefinition {
   private String              name;
   private VariableType        type;
   private AttributeConstraint constraint;

   protected AttributeDefinition() {
   }

   public AttributeDefinition(VariableType type, String name) {
      this.type = type;
      this.name = name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getName() {
      return name;
   }

   public VariableType getType() {
      return type;
   }

   public AttributeConstraint getConstraint() {
      return constraint;
   }

   public void setConstraint(AttributeConstraint constraint) {
      if (constraint != null) {
         if (type == VariableType.INT && constraint.getType() != AttributeConstraintType.INT_SET && constraint.getType() == AttributeConstraintType.INT_RANGE)
            throw new IllegalArgumentException("Constraint type does not match attribute type");
         if (type == VariableType.FLOAT && constraint.getType() != AttributeConstraintType.FLOAT_SET && constraint.getType() == AttributeConstraintType.FLOAT_RANGE)
            throw new IllegalArgumentException("Constraint type does not match attribute type");
         if (type == VariableType.STRING && constraint.getType() != AttributeConstraintType.STRING_SET)
            throw new IllegalArgumentException("Constraint type does not match attribute type");
         if (type == VariableType.BOOLEAN && constraint.getType() != AttributeConstraintType.BOOLEAN)
            throw new IllegalArgumentException("Constraint type does not match attribute type");
      }
      this.constraint = constraint;
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();

      sb.append(getClass().getSimpleName()).append(": ");
      sb.append("name: ").append(getName());
      sb.append("; type: ").append(getType().toString());
      sb.append("; constraint: ").append(getConstraint());
      
      return sb.toString();
   }
}
