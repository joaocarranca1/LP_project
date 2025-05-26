import java.util.*;

public class Environment <E>{
    Environment<E> anc;
    Map<String, E> bindings;

    Environment(){
        anc = null;
        bindings = new HashMap<String,E>();
    }
    
    Environment(Environment<E> ancestor){
        this.anc = ancestor;  // Remember parent scope
        this.bindings = new HashMap<String,E>();  // New empty scope
    }

    Environment<E> beginScope(){
        return new Environment<E>(this);
    }
    
    Environment<E> endScope(){
        return anc;
    }

    void assoc(String id, E bind) throws InterpreterError {
        if (bindings.containsKey(id)) {
            throw new InterpreterError("Variable " + id + " already declared");
        }
        bindings.put(id, bind);
    }

    void update(String id, E bind)  {
	// code missing
    }


    E find(String id) throws InterpreterError {
    if (bindings.containsKey(id)) {
        return bindings.get(id);
    }
    if (anc != null) {
        return anc.find(id);
    }
    throw new InterpreterError("Variable " + id + " not found");
    }

}
