public class ASTEq implements ASTNode {
    private final ASTNode lhs;
    private final ASTNode rhs;

    public ASTEq(ASTNode lhs, ASTNode rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    @Override
    public IValue eval(Environment<IValue> env) throws InterpreterError {
        IValue v1 = lhs.eval(env);
        IValue v2 = rhs.eval(env);

        if (v1 instanceof VInt && v2 instanceof VInt) {
            return new VBool(((VInt) v1).getval() == ((VInt) v2).getval());
        } else if (v1 instanceof VBool && v2 instanceof VBool) {
            return new VBool(((VBool) v1).getValue() == ((VBool) v2).getValue());
        } else {
            throw new InterpreterError("Invalid operands for ==");
        }
    }

    @Override
    public String toString() {
        return "(" + lhs + " == " + rhs + ")";
    }
}

