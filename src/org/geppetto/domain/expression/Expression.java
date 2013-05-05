package org.geppetto.domain.expression;

import org.geppetto.domain.Value;


public interface Expression {
   public boolean isLValue();
   public void setValue(Value value);
   public Value getValue();
}
