package org.geppetto.domain.declaration;



public interface AttributeConstraint {
   public boolean violatesConstraint(Object value);
   public AttributeConstraintType getType();
}
