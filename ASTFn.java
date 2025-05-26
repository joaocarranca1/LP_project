public class ASTFn implements ASTNode {
    private final String param;
    private final ASTNode body;

    public ASTFn(String param, ASTNode body) {
        this.param = param;
        this.body = body;
    }

    public IValue eval(Environment<IValue> env) {
        return new VClosure(env, param, body);
    }
}
