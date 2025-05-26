public class ASTApp implements ASTNode {
    private final ASTNode fnExpr;
    private final ASTNode argExpr;

    public ASTApp(ASTNode fnExpr, ASTNode argExpr) {
        this.fnExpr = fnExpr;
        this.argExpr = argExpr;
    }

    public IValue eval(Environment<IValue> env) throws InterpreterError {
        IValue fnVal = fnExpr.eval(env);
        if (!(fnVal instanceof VClosure)) {
            throw new InterpreterError("Not a function: " + fnVal.toStr());
        }
        VClosure closure = (VClosure) fnVal;
        
        IValue argVal = argExpr.eval(env);
        Environment<IValue> newEnv = closure.getEnv().beginScope();
        newEnv.assoc(closure.getParam(), argVal);
        
        return closure.getBody().eval(newEnv);
    }
}