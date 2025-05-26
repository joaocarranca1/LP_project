import java.util.function.Supplier;

public class VLazyList extends VList {
    private final Supplier<IValue> headThunk;
    private final Supplier<VList> tailThunk;
    private IValue cachedHead = null;
    private VList cachedTail = null;
    private boolean headEvaluated = false;
    private boolean tailEvaluated = false;

    public VLazyList(Supplier<IValue> headThunk, Supplier<VList> tailThunk) {
        super();
        this.headThunk = headThunk;
        this.tailThunk = tailThunk;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public IValue getHead() {
        if (!headEvaluated) {
            cachedHead = headThunk.get();
            headEvaluated = true;
        }
        return cachedHead;
    }

    @Override
    public VList getTail() {
        if (!tailEvaluated) {
            cachedTail = tailThunk.get();
            tailEvaluated = true;
        }
        return cachedTail;
    }

    @Override
    public String toStr() {
        return getHead().toStr() + " :? " + getTail().toStr();
    }
}

