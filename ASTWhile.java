public class ASTWhile implements ASTNode {
    private final ASTNode condition;
    private final ASTNode body;

    public ASTWhile(ASTNode condition, ASTNode body) {
        this.condition = condition;
        this.body = body;
    }

    @Override
    public IValue eval(Environment<IValue> env) throws InterpreterError {
        IValue condVal = condition.eval(env);
        if (!(condVal instanceof VBool)) {
            throw new InterpreterError("Condition must evaluate to a boolean.");
        }

        while (((VBool) condVal).getValue()) {
            body.eval(env);
            condVal = condition.eval(env);
            if (!(condVal instanceof VBool)) {
                throw new InterpreterError("Condition must evaluate to a boolean.");
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "while (" + condition + ") { " + body + " }";
    }
}

