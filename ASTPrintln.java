public class ASTPrintln implements ASTNode {
    private final ASTNode expression;

    public ASTPrintln(ASTNode expression) {
        this.expression = expression;
    }

    @Override
    public IValue eval(Environment<IValue> env) throws InterpreterError {
    IValue result = expression.eval(env);
    System.out.println(result.toStr());
    return result;
}

}
