public class ArrayDeque<T> {
    private int size = 0;
    private T[] items = (T[]) new Object[8];
    private int startPos = 4;
    private double r;
    private int nextLast;
    private int nextFirst;
    public ArrayDeque(){

        nextFirst = (startPos - 1 + items.length) % items.length;
        nextLast = (startPos + 1 + items.length) % items.length;
        calR();
    }
    public ArrayDeque(ArrayDeque other){
        this.size = other.size;
        this.startPos = other.startPos;
        this.r = other.r;
        this.nextFirst = other.nextFirst;
        this.nextLast = other.nextLast;
        T[] temps = (T[]) new Object[other.items.length];
        System.arraycopy(other.items,0,temps,0,other.items.length);
        items = temps;
    }
    /**
     * Get the item at the pos index, if pos is illegal return -1;
     * @param pos
     * @return
     */
    public T get(int pos){
        return items[pos];
    }
    /**
     * Remove the last item of the array and dec size.
     * @return the removed item.
     */
    public T removeLast(){
        if(nextLast - 1 == nextFirst){
            return null;
        }
        int last = (nextLast - 1 + items.length) % items.length;
        T val = items[last];
        items[last] = null;
        nextLast = last;
        size -= 1;
        calR();
        return val;
    }

    /**
     * Remove the first item of the array and dec size.
     * @return
     */
    public T removeFirst(){
        if(nextFirst + 1 == nextLast){
            return null;
        }
        int first = (nextFirst + 1 + items.length) % items.length;
        T val = items[first];
        items[first] = null;
        nextFirst = first;
        size -= 1;
        calR();
        return val;
    }

    /**
     * Return true if the size equals to zero.
     * @return ret
     */
    public boolean isEmpty(){
        boolean ret = false;
        if(size == 0){
            ret = true;
        }
        return ret;
    }
    /**
     * Return size.
     * @return
     */
    public int size(){
        return size;
    }

    /**
     * Print the elements stored in the Deque.
     */
    public void printDeque(){
        for (int i = (nextFirst + 1) % items.length; i < nextLast; i = (i + 1) % items.length) {
            System.out.print(items[i] + " ");
        }
        System.out.println();
    }
    /**
     * Add the item at the last of the array and inc size.
     * @param item which is need to add
     */
    public void addLast(T item){
        items[nextLast] = item;
        nextLast = (nextLast + 1) % items.length;
        size += 1;
        calR();
    }

    public void addFirst(T item){
        items[nextFirst] = item;
        nextFirst = (nextFirst + 1) % items.length;
        size += 1;
        calR();
    }
    public void calR(){
        r = (double)size/items.length;
    }

    /**
     * Resize the items[] to the set capacity.
     * @param capacity:the length of the new items[] array
     */
    private void resize(int capacity){
        T[] temps =(T[])new Object[capacity];
        System.arraycopy(items,0,temps,0,size);
        items = temps;
        calR();
    }
}
