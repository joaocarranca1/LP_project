public class ASTOr implements ASTNode {
    ASTNode left, right;

    public ASTOr(ASTNode left, ASTNode right) {
        this.left = left;
        this.right = right;
    }

    public IValue eval(Environment<IValue> e) throws InterpreterError {
        IValue lv = left.eval(e);
        if (!(lv instanceof VBool)) throw new InterpreterError("Left side not boolean");
        if (((VBool)lv).getValue()) return new VBool(true);
        return right.eval(e);
    }
}
