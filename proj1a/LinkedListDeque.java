public class LinkedListDeque<Barca> {
    private int size;
    private final ListNode sentinel = new ListNode();

    public LinkedListDeque(){
        sentinel.next = sentinel;
        sentinel.pre = sentinel;
    }
    public LinkedListDeque(LinkedListDeque other){
        sentinel.next = sentinel;
        sentinel.pre = sentinel;
        ListNode q = other.sentinel.pre;
        while (q != other.sentinel){
            ListNode start = new ListNode(sentinel, q.item, sentinel.next);
            sentinel.next = start;
            start.next.pre = start;
            size += 1;
            q = q.pre;
        }
    }
    public class ListNode {
        public ListNode pre;
        public Barca item;
        public ListNode next;
        public ListNode(){};
        public ListNode(ListNode pre, Barca item, ListNode next){
            this.pre = pre;
            this.item = item;
            this.next = next;
        }
    }

    public void addFirst(Barca item){
        ListNode start = new ListNode(sentinel, item, sentinel.next);
        sentinel.next = start;
        start.next.pre = start;
        size += 1;
    }

    public void addLast(Barca item){
        ListNode end = new ListNode(sentinel.pre,item,sentinel);
        sentinel.pre = end;
        end.pre.next = end;
        size += 1;
    }
    public boolean isEmpty(){
        return size == 0;
    }
    public int size(){
        return size;
    }
    public void printDeque(){
        ListNode p = sentinel.next;
        while (p != sentinel){
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.println();
    }
    public Barca removeFirst(){
        if(sentinel.next == sentinel){
            return null;
        }
        size -= 1;
        ListNode first = sentinel.next;
        sentinel.next = first.next;
        return first.item;
    }
    public Barca removeLast(){
        if(sentinel.next == sentinel){
            return null;
        }
        size -= 1;
        ListNode last = sentinel.pre;
        sentinel.pre = last.pre;
        return last.item;
    }
    public Barca get(int index){
        if(index > size - 1){
            return null;
        }
        ListNode p = sentinel;
        for (int i = 0; i < index; i++) {
            p = p.next;
        }
        return p.next.item;
    }
    public Barca getRecursive(int index){
        if(index > size - 1){
            return null;
        }
        getRecursive(index - 1);
        return null;
    }

}
