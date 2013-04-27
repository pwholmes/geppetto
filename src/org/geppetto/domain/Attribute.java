package org.geppetto.domain;

/**
 * This is absolutely HORRIBLE OO design, but too bad.  Tempus fugit.
 * @author Paul
 *
 */
public class Attribute {
   private String              name;
   private VariableType        type;
   private AttributeConstraint constraint;
   private int                 iValue;
   private float               fValue;
   private String              sValue;
   private boolean             bValue;

   protected Attribute() {
   }

   public Attribute(VariableType type, String name) {
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

   public void setValue(int value) {
      if (constraint != null && constraint.violatesConstraint(new Integer(value)))
         throw new IllegalArgumentException("Value violates attribute constraint.");
      iValue = value;
   }

   public void setValue(float value) {
      if (constraint != null && constraint.violatesConstraint(new Float(value)))
         throw new IllegalArgumentException("Value violates attribute constraint.");
      fValue = value;
   }

   public void setValue(String value) {
      if (constraint != null && constraint.violatesConstraint(new String(value)))
         throw new IllegalArgumentException("Value violates attribute constraint.");
      sValue = value;
   }

   public void setValue(boolean value) {
      if (constraint != null && constraint.violatesConstraint(new Boolean(value)))
         throw new IllegalArgumentException("Value violates attribute constraint.");
      bValue = value;
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

   public int getIntValue() {
      return iValue;
   }

   public float getFloatValue() {
      return fValue;
   }

   public String getStringValue() {
      return sValue;
   }

   public boolean getBooleanValue() {
      return bValue;
   }
   
   public String toString() {
      StringBuilder sb = new StringBuilder();

      sb.append(getClass().getSimpleName()).append(": ");
      sb.append("name: ").append(getName());
      sb.append("; type: ").append(getType().toString());
      sb.append("; constraint: ").append(getConstraint().toString());
      sb.append("; ivalue: ").append(getIntValue());
      sb.append("; fvalue: ").append(getFloatValue());
      sb.append("; svalue: ").append(getStringValue());
      sb.append("; bvalue: ").append(getBooleanValue());
      
      return sb.toString();
   }
}
