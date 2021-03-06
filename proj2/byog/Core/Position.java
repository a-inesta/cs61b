package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.ArrayList;

public class Position implements Comparable<Position> {
    public int x;
    public int y;
    public Position(int posX, int posY) {
        this.x = posX;
        this.y = posY;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (x != position.x) return false;
        return y == position.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public int compareTo(Position o) {
        return this.x - o.x;
    }

    public static ArrayList<Position> allPositions(Position p1, Position p2) {
        ArrayList<Position> ret = new ArrayList<>();
        int x1 = p1.x;
        int x2 = p2.x;
        int y1 = p1.y;
        int y2 = p2.y;
        for (int i = x1; i <= x2 ; i++) {
            for (int j = y1; j <= y2; j++) {
                ret.add(new Position(i,j));
            }
        }
        return ret;
    }
}
