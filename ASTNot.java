public class ASTNot implements ASTNode {
    private final ASTNode expr;

    public ASTNot(ASTNode expr) {
        this.expr = expr;
    }

    @Override
    public IValue eval(Environment<IValue> env) throws InterpreterError {
        IValue val = expr.eval(env);
        if (!(val instanceof VBool)) {
            throw new InterpreterError("Expected boolean in NOT, got: " + val.toStr());
        }
        boolean result = !((VBool) val).getValue();
        return new VBool(result);
    }

    @Override
    public String toString() {
        return "(~" + expr.toString() + ")";
    }
}
