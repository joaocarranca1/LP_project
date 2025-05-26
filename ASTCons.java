public class ASTCons implements ASTNode {
    private final ASTNode head;
    private final ASTNode tail;

    public ASTCons(ASTNode head, ASTNode tail) {
        this.head = head;
        this.tail = tail;
    }

    @Override
    public IValue eval(Environment<IValue> env) throws InterpreterError {
        IValue hVal = head.eval(env);
        IValue tVal = tail.eval(env);
        if (!(tVal instanceof VList)) {
            throw new RuntimeException("Tail of cons must be a list");
        }
        return new VList(hVal, (VList) tVal);
    }

    @Override
    public String toString() {
        return "(" + head + " :: " + tail + ")";
    }
}
