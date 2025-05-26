public class ASTGte implements ASTNode {
    private final ASTNode lhs, rhs;

    public ASTGte(ASTNode lhs, ASTNode rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public IValue eval(Environment<IValue> env) throws InterpreterError {
        IValue v1 = lhs.eval(env);
        IValue v2 = rhs.eval(env);
        if (v1 instanceof VInt && v2 instanceof VInt)
            return new VBool(((VInt)v1).getval() >= ((VInt)v2).getval());
        throw new InterpreterError("Invalid operands for >=");
    }
}

