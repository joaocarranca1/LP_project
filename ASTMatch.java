public class ASTMatch implements ASTNode {
    private final ASTNode expr;
    private final String nilCaseName;
    private final ASTNode nilCaseExpr;
    private final String headName;
    private final String tailName;
    private final ASTNode consCaseExpr;

    public ASTMatch(ASTNode expr, String nilCaseName, ASTNode nilCaseExpr,
                    String headName, String tailName, ASTNode consCaseExpr) {
        this.expr = expr;
        this.nilCaseName = nilCaseName;
        this.nilCaseExpr = nilCaseExpr;
        this.headName = headName;
        this.tailName = tailName;
        this.consCaseExpr = consCaseExpr;
    }

    @Override
    public IValue eval(Environment<IValue> env) throws InterpreterError  {
        IValue val = expr.eval(env);
        if (!(val instanceof VList)) {
            throw new RuntimeException("Match expression must evaluate to a list");
        }

        VList list = (VList) val;
        if (list.isEmpty()) {
            Environment <IValue> newEnv = new Environment <IValue> (env);
            newEnv.assoc(nilCaseName, val);
            return nilCaseExpr.eval(newEnv);
        } else {
            Environment <IValue>  newEnv = new Environment <IValue> (env);
            newEnv.assoc(headName, list.getHead());
            newEnv.assoc(tailName, list.getTail());
            return consCaseExpr.eval(newEnv);
        }
    }

    @Override
    public String toString() {
        return "match " + expr + " with nil => " + nilCaseExpr + 
               " | " + headName + " :: " + tailName + " => " + consCaseExpr;
    }
}

