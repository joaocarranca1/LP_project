public class ASTDeref implements ASTNode {
    private final ASTNode expr;

    public ASTDeref(ASTNode expr) {
        this.expr = expr;
    }

    @Override
    public IValue eval(Environment<IValue> env) throws InterpreterError {
        IValue ref = expr.eval(env);
        if (!(ref instanceof VBox)) {
            throw new InterpreterError("Attempted to dereference a non-box");
        }
        return ((VBox) ref).get();
    }

    @Override
    public String toString() {
        return "!" + expr;
    }
}

