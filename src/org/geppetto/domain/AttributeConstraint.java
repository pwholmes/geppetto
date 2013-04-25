package org.geppetto.domain;

public interface AttributeConstraint {
   public boolean violatesConstraint(Object value);
   public AttributeConstraintType getType();
}
