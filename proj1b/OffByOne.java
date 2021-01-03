public class OffByOne implements CharacterComparator {
    @Override
    public boolean equalChars(char x, char y) {
        if (x >= 'A' && x <= 'Z') {
            x = (char) (x - ('A' - 'a'));
        }
        if (y >= 'A' && y <= 'Z') {
            y = (char) (y - ('A' - 'a'));
        }
        return x - y == 1 || y - x == 1;
    }
}
