public class ArrayDeque<T> {
    /**
     * The starting size of your array should be 8.
     * The amount of memory that your program uses at any given time must be proportional to the
     * number of items.
     * For example, if you add 10,000 items to the deque, and then remove 9,999 items, you
     * shouldn’t still be using an array of length 10,000ish.
     * For arrays of length 16 or more, your usage factor should always be at least 25%.
     * For smaller arrays, your usage factor can be arbitrarily low.
     */
    private T[] arrayItems;
    private int size;
    private int nStart;//one before first item
    private int nEnd;//one after last item

//    public static void main(String[] args) {//FIXME comment when submit
//        ArrayDeque<Integer> aList = new ArrayDeque<Integer>();
//        //aList.printDeque();
//        int size = 35;
//        for (int i = 0; i < size; i++) {
//            aList.addFirst(size - i - 1);
//        }
//        aList.printDeque();
//        System.out.println(aList.size);
//        System.out.println(aList.nStart);
//        System.out.println(aList.nEnd);
//        size = 35;
//        for (int i = 0; i < size; i++) {
//            aList.addLast(i);
//        }
//        aList.printDeque();
//        System.out.println(aList.size);
//        System.out.println(aList.nStart);
//        System.out.println(aList.nEnd);
//        System.out.println("*********DELETE************");
//        size = 40;
//        for (int i = 0; i < size; i++) {
//            System.out.println("delete count = " + (i + 1) + ", deleted item = " + aList
//            .removeFirst() + ", usage
//            factor = " + aList.getUsageFactor());
//        }
//        aList.printDeque();
//        size = 33;
//        for (int i = 0; i < size; i++) {
//            System.out.println("delete count = " + (i + 1) + ", deleted item = " + aList
//            .removeLast() + ", usage
//            factor = " + aList.getUsageFactor());
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
     * @return usage factor of arrayItem
     */
    private float getUsageFactor() {
        return (float) size / arrayItems.length;
    }

    /**
     * Creates an empty linked list deque.
     */
    public ArrayDeque() {
        arrayItems = (T[]) (new Object[8]);
        size = 0;
        nStart = 0;
        nEnd = 1;

    }

    /**
     * @return True if deque is empty, false otherwise.
     */
    public boolean isEmpty() {
        return !(size > 0);
    }

    /**
     * @return the number of items in the deque.
     */
    public int size() {
        return size;
    }

    /**
     * Prints the items in the deque from first to prior, separated by a space.
     */
    public void printDeque() {
        int i = nStart + 1 < arrayItems.length ? nStart + 1 : 0;
        while (i != nEnd) {
            System.out.print(arrayItems[i] + " ");
            i = i + 1 < arrayItems.length ? i + 1 : 0;
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
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        if (nStart + index + 1 < arrayItems.length) {
            return arrayItems[nStart + index + 1];
        } else {
            return arrayItems[index - (arrayItems.length - nStart - 1)];
        }
    }

    /**
     * Resize arrayitem
     * Will change nStart and nEnd !!!
     *
     * @param newSize
     * @return
     */
    private int resize(int newSize) {
        try {
            if (newSize > arrayItems.length && nStart != nEnd) {
                throw new Exception("对于扩大化resize的错误调用");
            } else if (newSize < arrayItems.length && newSize < size + 2) {
                throw new Exception("对于缩小化resize的错误调用");
            }
        } catch (Exception e) {
            System.out.println("resize错误");
        }
        //System.out.print("doing resize: from arrayitem[" + arrayItems.length + "], size = " +
        // size + " -> To ");
        // FIXME comment when submit
        T[] newArray = (T[]) (new Object[newSize]);
        if (nStart < nEnd) {
            // S -> E
            System.arraycopy(arrayItems, nStart, newArray, 0, nEnd - nStart);//also copy [nStart]
        } else {
            // S -> length - 1, 0 -> E
            System.arraycopy(arrayItems, nStart, newArray, 0, arrayItems.length - nStart);//also
            // copy [nStart]
            System.arraycopy(arrayItems, 0, newArray, arrayItems.length - nStart, nEnd);
        }
        arrayItems = newArray;
        nStart = 0;
        nEnd = size + 1;
        //System.out.println("arrayitem[" + arrayItems.length + "], size = " + size + ".");
        // FIXME comment when submit
        return 0;
    }

    /**
     * Adds an item of type T to the front of the deque.
     * change size
     *
     * @param item: The item need to be added
     */
    public void addFirst(T item) {
        if (nStart == nEnd) {
            resize(2 * size + 2);
        }
        arrayItems[nStart] = item;
        nStart = nStart - 1 < 0 ? arrayItems.length - 1 : nStart - 1;
        size++;
    }

    /**
     * Adds an item of type T to the back of the deque.
     * change size
     *
     * @param item: The item need to be added
     */
    public void addLast(T item) {
        if (nStart == nEnd) {
            resize(2 * size + 2);
        }
        arrayItems[nEnd] = item;
        nEnd = nEnd + 1 >= arrayItems.length ? 0 : nEnd + 1;
        size++;
    }

    /**
     * Removes and returns the item at the front of the deque. If no such item exists, returns null.
     * change size
     *
     * @return The removed item. If no such item exists, returns null.
     */
    public T removeFirst() {
        if (size < 1) {
            return null;
        }
        nStart = nStart + 1 >= arrayItems.length ? 0 : nStart + 1;
        size--;
        T deletedItem = arrayItems[nStart];
        arrayItems[nStart] = null;
        if (size < (float) arrayItems.length / 2 && size + 2 < arrayItems.length) {
            resize(size + 2);
        }
        return deletedItem;
    }

    /**
     * Removes and returns the item at the back of the deque. If no such item exists, returns null.
     * change size
     *
     * @return The removed item. If no such item exists, returns null.
     */
    public T removeLast() {
        if (size < 1) {
            return null;
        }
        nEnd = nEnd - 1 < 0 ? arrayItems.length - 1 : nEnd - 1;
        size--;
        T deletedItem = arrayItems[nEnd];
        arrayItems[nEnd] = null;
        if (size < (float) arrayItems.length / 2 && size + 2 < arrayItems.length) {
            resize(size + 2);
        }
        return deletedItem;
    }
}
