public class ASTBox implements ASTNode {
    private final ASTNode value;

    public ASTBox(ASTNode value) {
        this.value = value;
    }

    @Override
    public IValue eval(Environment<IValue> env) throws InterpreterError {
        return new VBox(value.eval(env));
    }

    @Override
    public String toString() {
        return "box(" + value + ")";
    }
}
