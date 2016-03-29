package list;

public class LockDList extends DList {
    
    // Don't need to write constructor, since default is super();.
    //public LockDList() {
    //    //DListNode head = newNode(Integer.MIN_VALUE, null, null);
    //    //head.prev = (LockDListNode)head;
    //    //head.next = (LockDListNode)head;
    //    //((LockDListNode)head).locked = true;
    //    //size = 0;
    //    //System.out.println("Lock Constructor: (instanceof LockDListNode)" + (head instanceof LockDListNode));
    //}
    
    protected DListNode newNode(Object item, DListNode prev, DListNode next) {
        return new LockDListNode(item, (LockDListNode)prev, (LockDListNode)next);
    }
    
    public void lockNode(DListNode node) {
        ((LockDListNode)node).locked = true;
    }
    
    public void remove(DListNode node) {
        if (((LockDListNode)node).locked == true) {
            return;
        }
        else {
            super.remove(node);
        }            
    }
    
    // from jrgoldfinemiddleton/cs61b @ github
    public static void main(String[] args) {
        System.out.println("Constructing a new LockDList.");
        LockDList l1 = new LockDList();
        System.out.println(l1);
        System.out.println("Current size: " + l1.length());
    
        System.out.println("\nAttempting to remove the head node.");
        l1.remove(l1.head);
        System.out.println(l1);
        System.out.println("Current size: " + l1.length());
    
        System.out.println("\nAttempting to remove the node after head.");
        l1.remove(l1.head.next);
        System.out.println(l1);
        System.out.println("Current size: " + l1.length());
    
        System.out.println("\nAttempting to insert a new front node containing 3.");
        l1.insertFront(new Integer(3));
        System.out.println(l1);
        System.out.println("Current size: " + l1.length());
    
        System.out.println("\nAttempting to insert a new front node containing 2.");
        l1.insertFront(new Integer(2));
        System.out.println(l1);
        System.out.println("Current size: " + l1.length());
    
        System.out.println("\nAttempting to insert a new front node containing 99.");
        l1.insertFront(new Integer(99));
        System.out.println(l1);
        System.out.println("Current size: " + l1.length());
    
        System.out.println("\nAttempting to lock the node after head.");
        l1.lockNode(l1.head.next);
        System.out.println("Locked? " + ((LockDListNode) l1.head.next).locked);
        
        System.out.println("\nAttempting to remove the node after head.");
        l1.remove(l1.head.next);
        System.out.println(l1);
        System.out.println("Current size: " + l1.length());
        l1.checkInvariants();
    
        System.out.println("Attempting to insert a new back node containing 9");
        l1.insertBack(new Integer(9));
        System.out.println(l1);
        System.out.println("Current size: " + l1.length());
    
        System.out.print("\nAttempting to insert a new back node containing ");
        System.out.println("\"deleteMe\".");
        l1.insertBack(new String("deleteMe"));
        System.out.println(l1);
        System.out.println("Current size: " + l1.length());
    
        System.out.print("\nAttempting to insert a new node containing 4 after the ");
        System.out.println("second node.");
        l1.insertAfter(4, l1.next(l1.head).next);
        System.out.println(l1);
        System.out.println("Current size: " + l1.length());
    
        System.out.println("\nAttempting to remove the back node.");
        l1.remove(l1.prev(l1.head));
        System.out.println(l1);
        System.out.println("Current size: " + l1.length());
    
        System.out.print("\nAttempting to insert a new node containing 8 after ");
        System.out.println("the second to last node.");
        l1.insertAfter(8, l1.prev(l1.back()));
        System.out.println(l1);
        System.out.println("Current size: " + l1.length());
        l1.checkInvariants();
    
        System.out.println("Constructing a new LockDList.");
        LockDList l2 = new LockDList();
        System.out.println(l2);
    
        System.out.print("\nAttempting to set the head node of the new LockDList ");
        System.out.println("to the first node of the first LockDList.");
        System.out.print("Good luck with that! (I bet it will screw up the ");
        System.out.println("invariants.)\n");
        l2.head = l1.head.next;
        System.out.println("First LockDList:");
        l1.checkInvariants();
        System.out.println("Second LockDList:");
        l2.checkInvariants();
    }
    
    
}