package org.geppetto.domain.declaration;



public interface AttributeConstraint {
   public boolean violatesConstraint(Value value);
   public AttributeConstraintType getType();
}
