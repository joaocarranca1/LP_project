import java.util.function.Supplier;

public class VLazyList extends VList {
    private static final VLazyList EMPTY = new VLazyList();
    
    private final Supplier<IValue> headThunk;
    private final Supplier<VList> tailThunk;
    private IValue cachedHead = null;
    private VList cachedTail = null;
    private boolean headEvaluated = false;
    private boolean tailEvaluated = false;
    private final boolean isEmpty;

    // Private constructor for empty list
    private VLazyList() {
        this.headThunk = null;
        this.tailThunk = null;
        this.isEmpty = true;
    }

    public VLazyList(Supplier<IValue> headThunk, Supplier<VList> tailThunk) {
        this.headThunk = headThunk;
        this.tailThunk = tailThunk;
        this.isEmpty = false;
    }

    public static VLazyList empty() {
        return EMPTY;
    }

    @Override
    public boolean isEmpty() {
        return isEmpty;
    }

    @Override
    public IValue getHead() {
        if (isEmpty) {
            throw new RuntimeException("Cannot get head of empty lazy list");
        }
        if (!headEvaluated) {
            cachedHead = headThunk.get();
            headEvaluated = true;
        }
        return cachedHead;
    }

    @Override
    public VList getTail() {
        if (isEmpty) {
            throw new RuntimeException("Cannot get tail of empty lazy list");
        }
        if (!tailEvaluated) {
            cachedTail = tailThunk.get();
            tailEvaluated = true;
            // Check if the tail is null and convert to empty list if needed
            if (cachedTail == null) {
                cachedTail = VLazyList.empty();
            }
        }
        return cachedTail;
    }

    @Override
    public String toStr() {
        if (isEmpty) {
            return "nil";
        }
        return getHead().toStr() + " :? " + getTail().toStr();
    }
}


