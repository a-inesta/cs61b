public class ArrayDeque<T> {
    private int size = 0;
    private T[] items = (T[]) new Object[8];
    private double r;
    private int nextLast = 4;
    private int nextFirst = 3;

    public ArrayDeque() {
    }
//    public ArrayDeque(ArrayDeque other){
//        this.size = other.size;
//        this.r = other.r;
//        this.nextFirst = other.nextFirst;
//        this.nextLast = other.nextLast;
//        T[] temps = (T[]) new Object[other.items.length];
//        System.arraycopy(other.items,0,temps,0,other.items.length);
//        items = temps;
//    }

    /**
     * Get the item at the pos index, if pos is illegal return -1;
     *
     * @param pos
     * @return
     */
    public T get(int pos) {
        return items[pos];
    }

    /**
     * Remove the last item of the array and dec size.
     *
     * @return the removed item.
     */
    public T removeLast() {
        if (minusOne(nextLast) == nextFirst) {
            return null;
        }
        T val = items[minusOne(nextLast)];
        items[minusOne(nextLast)] = null;
        nextLast = minusOne(nextLast);
        size -= 1;
        calR();
        if (r < 0.25 && items.length > 15) {
            resize(items.length / 2);
        }
        return val;
    }

    /**
     * Remove the first item of the array and dec size.
     *
     * @return
     */
    public T removeFirst() {
        if (plusOne(nextFirst) == nextLast) {
            return null;
        }
        T val = items[plusOne(nextFirst)];
        items[plusOne(nextFirst)] = null;
        nextFirst = plusOne(nextFirst);
        size -= 1;
        calR();
        if (r < 0.25 && items.length > 15) {
            resize(items.length / 2);
        }
        return val;
    }

    /**
     * Return true if the size equals to zero.
     *
     * @return ret
     */
    public boolean isEmpty() {
        boolean ret = false;
        if (size == 0) {
            ret = true;
        }
        return ret;
    }

    /**
     * Return size.
     *
     * @return
     */
    public int size() {
        return size;
    }

    /**
     * Print the elements stored in the Deque.
     */
    public void printDeque() {
        for (int i = plusOne(nextFirst); i < nextLast; i = plusOne(i)) {
            System.out.print(items[i] + " ");
        }
        System.out.println();
    }

    /**
     * Add the item at the last of the array and inc size.
     *
     * @param item which is need to add
     */
    public void addLast(T item) {
        if (plusOne(nextLast) == nextFirst) {
            return;
        }
        items[nextLast] = item;
        nextLast = plusOne(nextLast);
        size += 1;
        calR();
        if (r > 0.75) {
            resize(items.length * 3 / 2);
        }
    }

    public void addFirst(T item) {
        if (minusOne(nextFirst) == nextLast) {
            return;
        }
        items[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
        size += 1;
        calR();
        if (r > 0.75) {
            resize(items.length * 3 / 2);
        }
    }

    private void calR() {
        r = (double) size / items.length;
    }

    /**
     * Resize the items[] to the set capacity.
     *
     * @param capacity:the length of the new items[] array
     */
    private void resize(int capacity) {
        T[] temps = (T[]) new Object[capacity];
        if (minusOne(nextFirst) > nextLast) {
            System.arraycopy(items, nextFirst, temps, 0, size);
        } else {
            System.arraycopy(items, nextFirst, temps, 0, size - nextLast + 1);
            System.arraycopy(items, 0, temps, size - nextLast + 1, nextLast);
        }
        items = temps;
        nextLast = size + 1;
        nextFirst = 0;
        return;
    }

    private int minusOne(int pos) {
        return (pos - 1 + items.length) % items.length;
    }

    private int plusOne(int pos) {
        return (pos + 1) % items.length;
    }
}
