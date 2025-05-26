public class ASTPrint implements ASTNode {
    private final ASTNode expression;

    public ASTPrint(ASTNode expression) {
        this.expression = expression;
    }

    @Override
    public IValue eval(Environment<IValue> env) throws InterpreterError {
        IValue result = expression.eval(env);
        System.out.print(result);
        return result;
    }
}
