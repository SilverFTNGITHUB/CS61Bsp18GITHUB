public class LinkedListDeque<T> implements Deque<T> {
    private final LinkedListNode sentinel;
    private int size;

    //when deal with DEQUE LIST remember to change !!BOTH!! "<-" and "->"

//    public static void main(String[] args) {//FIXME comment when submit
//        //ArrayDeque<Integer> aList = new ArrayDeque<Integer>();
//        LinkedListDeque aList = new LinkedListDeque();
//        //aList.printDeque();
//        int size = 35;
//        for (int i = 0; i < size; i++) {
//            aList.addFirst(size - i - 1);
//        }
//        aList.printDeque();
//        System.out.println(aList.size);
//        size = 35;
//        for (int i = 0; i < size; i++) {
//            aList.addLast(i);
//        }
//        aList.printDeque();
//        System.out.println(aList.size);
//        System.out.println("*********DELETE************");
//        size = 40;
//        for (int i = 0; i < size; i++) {
//            System.out.println("delete count = " + (i + 1) + ", deleted item = " + aList
//            .removeFirst());
//        }
//        aList.printDeque();
//        size = 33;
//        for (int i = 0; i < size; i++) {
//            System.out.println("delete count = " + (i + 1) + ", deleted item = " + aList
//            .removeLast());
//        }
//        System.out.println("**********GET***********");
//        size = 8;
//        for (int i = 0; i < size; i++) {
//            aList.addFirst(size - i - 1);
//        }
//        aList.printDeque();
//        for (int i = 0; i < size; i++) {
//            System.out.println("get index = " + i + ", item = " + aList.get(i));
//        }
//    }

    /**
     *
     */
    private class LinkedListNode {
        T item;
        LinkedListNode prior;
        LinkedListNode next;

        /**
         * Create a LinkedListNode
         */
        public LinkedListNode() {
        }

        /**
         * Create and initialize a LinkedListNode
         *
         * @param item:
         * @param prior: Prior node
         * @param next:  Next node
         */
        public LinkedListNode(T item, LinkedListNode prior, LinkedListNode next) {
            this.item = item;
            this.prior = prior;
            this.next = next;
        }

        /**
         * get the node at index
         *
         * @param index:
         * @return The finded node
         */
        public LinkedListNode getRecursionNode(int index) {
            if (index == 0) {
                return this;
            }
            return this.next.getRecursionNode(--index);
        }
    }

    /**
     * Creates an empty linked list deque.
     */
    public LinkedListDeque() {
        //this.sentinel.item = 7;//can't set certain item cause the data type is unsure
        this.sentinel = new LinkedListNode();
        this.sentinel.prior = this.sentinel;
        this.sentinel.next = this.sentinel;
        size = 0;
    }

    /**
     * @return True if deque is empty, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return !(size > 0);
    }

    /**
     * @return the number of items in the deque.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Prints the items in the deque from first to prior, separated by a space.
     */
    @Override
    public void printDeque() {
        LinkedListNode p = this.sentinel.next;
        while (p != this.sentinel) {
            System.out.print(p.item + " ");
            p = p.next;
        }
        //System.out.println();//FIXME commented when submit
    }

    /**
     * Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null.
     * Must not alter the deque!
     *
     * @param index:
     * @return The item at the given index. If no such item exists, returns null.
     */
    @Override
    public T get(int index) {
        if (index < 0 || index + 1 > this.size()) {
            return null;
        }
        LinkedListNode p = this.sentinel;
        while (index >= 0) {
            p = p.next;
            index--;
        }
        return p.item;
    }

    /**
     * Same as get, but uses recursion.
     * change size
     *
     * @param index:
     * @return The item at the given index. If no such item exists, returns null.
     */
    public T getRecursive(int index) {
        if (index < 0 || index + 1 > this.size()) {
            return null;
        }
        LinkedListNode p = this.sentinel.next;
        p = p.getRecursionNode(index);
        return p.item;
    }

    /**
     * Adds an item of type T to the front of the deque.
     * change size
     *
     * @param item: The item need to be added
     */
    @Override
    public void addFirst(T item) {
        LinkedListNode newNode = new LinkedListNode(item, this.sentinel, this.sentinel.next);
        //set "<-" and "->" of
        // new node
        this.sentinel.next.prior = newNode;// change "<-" of old front
        this.sentinel.next = newNode;//change "->" of sentinel
        size++;
    }

    /**
     * Adds an item of type T to the back of the deque.
     *
     * @param item: The item need to be added
     */
    @Override
    public void addLast(T item) {
        LinkedListNode newNode = new LinkedListNode(item, this.sentinel.prior, this.sentinel);
        //set "<-" and "->" of
        // new node
        this.sentinel.prior.next = newNode;//change "->" of old end
        this.sentinel.prior = newNode;//change "<-" of sentinel
        size++;
    }

    /**
     * Removes and returns the item at the front of the deque. If no such item exists, returns null.
     * change size
     *
     * @return The removed item. If no such item exists, returns null.
     */
    @Override
    public T removeFirst() {
        if (this.size() - 1 < 0) {
            return null;
        }
        LinkedListNode deletedNode = this.sentinel.next;
        deletedNode.next.prior = deletedNode.prior;//change "<-" of next node
        this.sentinel.next = deletedNode.next;//change "->" of prior node
        size--;
        return deletedNode.item;
    }

    /**
     * Removes and returns the item at the back of the deque. If no such item exists, returns null.
     * change size
     *
     * @return The removed item. If no such item exists, returns null.
     */
    @Override
    public T removeLast() {
        if (this.size() - 1 < 0) {
            return null;
        }
        LinkedListNode deletedNode = this.sentinel.prior;
        deletedNode.prior.next = deletedNode.next;//change "->" of prior node
        deletedNode.next.prior = deletedNode.prior;//change "<->" of next node
        size--;
        return deletedNode.item;
    }


}