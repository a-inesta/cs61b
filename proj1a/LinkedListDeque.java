public class LinkedListDeque<T> {
    private int size;
    private final ListNode sentinel = new ListNode();

    public LinkedListDeque() {
        sentinel.next = sentinel;
        sentinel.pre = sentinel;
    }

    //    public LinkedListDeque(LinkedListDeque other){
//        sentinel.next = sentinel;
//        sentinel.pre = sentinel;
//        ListNode q = other.sentinel.pre;
//        while (q != other.sentinel){
//            ListNode start = new ListNode(sentinel, q.item, sentinel.next);
//            sentinel.next = start;
//            start.next.pre = start;
//            size += 1;
//            q = q.pre;
//        }
//    }
    private class ListNode {
        public ListNode pre;
        public T item;
        public ListNode next;

        public ListNode() {
        }

        public ListNode(ListNode pre, T item, ListNode next) {
            this.pre = pre;
            this.item = item;
            this.next = next;
        }
    }

    public void addFirst(T item) {
        ListNode start = new ListNode(sentinel, item, sentinel.next);
        sentinel.next = start;
        start.next.pre = start;
        size += 1;
    }

    public void addLast(T item) {
        ListNode end = new ListNode(sentinel.pre, item, sentinel);
        sentinel.pre = end;
        end.pre.next = end;
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        ListNode p = sentinel.next;
        while (p != sentinel) {
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.println();
    }

    public T removeFirst() {
        if (sentinel.next == sentinel) {
            return null;
        }
        size -= 1;
        ListNode first = sentinel.next;
        sentinel.next = first.next;
        first.next.pre = sentinel;
        return first.item;
    }

    public T removeLast() {
        if (sentinel.next == sentinel) {
            return null;
        }
        size -= 1;
        ListNode last = sentinel.pre;
        sentinel.pre = last.pre;
        last.pre.next = sentinel;
        return last.item;
    }

    public T get(int index) {
        if (index > size - 1) {
            return null;
        }
        ListNode p = sentinel;
        for (int i = 0; i < index; i++) {
            p = p.next;
        }
        return p.next.item;
    }

    public T getRecursive(int index) {
        if (index > size - 1) {
            return null;
        }
        getRecursive(index - 1);
        return null;
    }

}
