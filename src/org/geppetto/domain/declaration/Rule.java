package org.geppetto.domain.declaration;

import org.geppetto.domain.expression.Expression;
import org.geppetto.domain.statement.Statement;

public class Rule {
   private String name = "<anonymous>";
   private Expression condition;
   private Statement behavior;
   
   @SuppressWarnings("unused")
   private Rule() {}
   
   public Rule(String name, Expression condition, Statement behavior) {
      this.name = name;
      this.condition = condition;
      this.behavior = behavior;
   }

   public Rule(Expression condition, Statement behavior) {
      this.condition = condition;
      this.behavior = behavior;
   }
   
   public String getName() {
      return name;
   }
   
   public Expression getCondition() {
      return condition;
   }
   
   public Statement getBehavior() {
      return behavior;
   }
   
   public String toString() {
      StringBuilder sb = new StringBuilder();
      
      sb.append("{").append(this.getClass().getSimpleName()).append(": ");
      sb.append("name: ").append(getName());
      sb.append("; condition: ").append(getCondition());
      sb.append("; behavior: ").append(getBehavior());
      sb.append("}");
      
      return sb.toString();
   }
}
