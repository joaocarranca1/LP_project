public class ASTBool implements ASTNode {
    private final boolean value;

    public ASTBool(boolean value) {
        this.value = value;
    }

    @Override
    public IValue eval(Environment<IValue> env) throws InterpreterError {
        return new VBool(value);
    }

    @Override
    public String toString() {
        return Boolean.toString(value);
    }
}
