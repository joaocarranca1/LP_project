public class VClosure implements IValue {
    private final Environment<IValue> env;
    private final String param; 
    private final ASTNode body; 

    public VClosure(Environment<IValue> env, String param, ASTNode body) {
        this.env = env;
        this.param = param;
        this.body = body;
    }

    public String toStr() { return "<closure>"; }
    public Environment<IValue> getEnv() { return env; }

    public String getParam() { return param; }
    public ASTNode getBody() { return body; }
    
}
