public class OffByN implements CharacterComparator {
    int n;
    OffByN(int n) {
        this.n = n;
    }
    @Override
    public boolean equalChars(char x, char y) {
        return x - y == n || y - x == n;
    }
}
