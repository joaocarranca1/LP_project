public class ASTAssign implements ASTNode {
    private final ASTNode lhs;
    private final ASTNode rhs;

    public ASTAssign(ASTNode lhs, ASTNode rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    @Override
    public IValue eval(Environment<IValue> env) throws InterpreterError {
        if (!(lhs instanceof ASTId)) {
            throw new InterpreterError("Left-hand side of := must be an identifier");
        }
        String name = ((ASTId) lhs).id;
        IValue ref = env.find(name);
        if (!(ref instanceof VBox)) {
            throw new InterpreterError("Assigned variable is not a box");
        }
        IValue val = rhs.eval(env);
        ((VBox) ref).set(val);
        return val;
    }

    @Override
    public String toString() {
        return "(" + lhs + " := " + rhs + ")";
    }
}

