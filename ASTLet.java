import java.util.List;

public class ASTLet implements ASTNode {
    private final List<Bind> decls;
    private final ASTNode body;

    public ASTLet(List<Bind> decls, ASTNode body) {
        this.decls = decls;
        this.body = body;
    }

    public IValue eval(Environment<IValue> e) throws InterpreterError {
        Environment<IValue> en = e.beginScope();

        for (Bind binding : decls) {
            IValue value = binding.getExp().eval(en); 
            en.assoc(binding.getId(), value); 
        }

        return body.eval(en);
    }
}

