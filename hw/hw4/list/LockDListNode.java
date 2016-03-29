package list;

public class LockDListNode extends DListNode {
    
    protected boolean locked; //protected!
    
    public LockDListNode (Object i, LockDListNode p, LockDListNode n) {
        super(i, p, n);
        locked = false;
    }
    
}