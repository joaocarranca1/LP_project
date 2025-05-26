public class ASTLazyCons implements ASTNode {
    private final ASTNode head;
    private final ASTNode tail;
    
    public ASTLazyCons(ASTNode head, ASTNode tail) {
        this.head = head;
        this.tail = tail;
    }

    @Override
    public IValue eval(Environment<IValue> env) throws InterpreterError {
        return new VLazyList(() -> {
            try {
                return head.eval(env);
            } catch (InterpreterError e) {
                throw new RuntimeException("Error evaluating head in lazy cons", e);
            }
        }, () -> {
            try {
                IValue tVal = tail.eval(env);
                if (tVal instanceof VList) {
                    return (VList) tVal;
                }
                // If the tail evaluates to something else, return empty list
                return VLazyList.empty();
            } catch (InterpreterError e) {
                throw new RuntimeException("Error evaluating tail in lazy cons", e);
            }
        });
    }

    @Override
    public String toString() {
        return "(" + head + " :? " + tail + ")";
    }
}
