public class ArrayDequeMod {
    public int size = 0;
    public int[] items = new int[8];
    public int startPos;
    private double r;
    private int nextLast;
    private int nextFirst;
    public ArrayDequeMod(int startPos){
        this.startPos = startPos;
        nextFirst = (startPos - 1 + items.length) % items.length;
        nextLast = (startPos + 1 + items.length) % items.length;
        calR();
    }
    public void addLast(int item){
        if(r > 0.5){
            resize(items.length * 3 / 2);
        }
        items[nextLast] = item;
        nextLast = (nextLast + 1) % items.length;
        size += 1;
        calR();
    }

    public void addFirst(int item){
        if(r > 0.5){
            resize(items.length * 3 / 2);
        }
        items[nextFirst] = item;
        nextFirst = (nextFirst + 1) % items.length;
        size += 1;
        calR();
    }

    private void resize(int capacity){
        int[] temps =new int[capacity];
        System.arraycopy(items,0,temps,0,size);
        items = temps;
        calR();
    }
    public void calR(){
        r = (double)size/items.length;
    }
}


