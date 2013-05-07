package org.geppetto.domain.declaration;

import org.geppetto.GeppettoException;
import org.geppetto.domain.DataType;

public class AttributeDefinition {
   private String              name;
   private DataType        type;
   private AttributeConstraint constraint;

   protected AttributeDefinition() {
   }

   public AttributeDefinition(DataType type, String name) {
      this.type = type;
      this.name = name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getName() {
      return name;
   }

   public DataType getType() {
      return type;
   }

   public AttributeConstraint getConstraint() {
      return constraint;
   }

   public void setConstraint(AttributeConstraint constraint) {
      if (constraint != null) {
         if (type == DataType.INT && constraint.getType() != AttributeConstraintType.INT_SET && constraint.getType() != AttributeConstraintType.INT_RANGE)
            throw new GeppettoException("Constraint type does not match attribute type");
         if (type == DataType.FLOAT && constraint.getType() != AttributeConstraintType.FLOAT_SET && constraint.getType() != AttributeConstraintType.FLOAT_RANGE)
            throw new GeppettoException("Constraint type does not match attribute type");
         if (type == DataType.STRING && constraint.getType() != AttributeConstraintType.STRING_SET)
            throw new GeppettoException("Constraint type does not match attribute type");
         if (type == DataType.BOOLEAN && constraint.getType() != AttributeConstraintType.BOOLEAN)
            throw new GeppettoException("Constraint type does not match attribute type");
      }
      this.constraint = constraint;
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();

      sb.append("{").append(this.getClass().getSimpleName()).append(": ");
      sb.append("name: ").append(getName());
      sb.append("; type: ").append(getType().toString());
      sb.append("; constraint: ").append(getConstraint());
      sb.append("}");
      
      return sb.toString();
   }
}
