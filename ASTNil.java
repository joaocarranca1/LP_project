public class ASTNil implements ASTNode {
    public ASTNil() {}
  
    @Override
    public IValue eval(Environment<IValue> env) throws InterpreterError {
      return new VList();
    }
  
    @Override
    public String toString() {
      return "nil";
    }
  }
  