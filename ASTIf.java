public class ASTIf implements ASTNode {
    private final ASTNode condition;
    private final ASTNode thenBranch;
    private final ASTNode elseBranch;

    public ASTIf(ASTNode condition, ASTNode thenBranch, ASTNode elseBranch) {
        this.condition = condition;
        this.thenBranch = thenBranch;
        this.elseBranch = elseBranch;
    }

    @Override
    public IValue eval(Environment<IValue> env) throws InterpreterError {
        IValue condVal = condition.eval(env);
        if (!(condVal instanceof VBool)) {
            throw new InterpreterError("Condition must evaluate to a boolean.");
        }
        if (((VBool) condVal).getValue()) {
            return thenBranch.eval(env);
        } else {
            return elseBranch.eval(env);
        }
    }

    @Override
    public String toString() {
        return "if (" + condition + ") { " + thenBranch + " } else { " + elseBranch + " }";
    }
}
