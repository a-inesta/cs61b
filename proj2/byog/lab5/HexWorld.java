package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static  final int N = 4;
    private static final long SEED = 6666;
    private static final Random RANDOM = new Random(SEED);

    private static class Positon {
        private final int x;
        private final int y;
        private final int var;
        public Positon(int x, int y, int var) {
            this.x = x;
            this.y = y;
            this.var = var;
        }
    }

    /**
     * 遍历每一列的起点坐标
     * @param tiles
     * @param side
     * @param pList
     */
    public static void drawHexagon(TETile[][] tiles, int side, List<Positon> pList) {
        for(Positon p : pList) {
            addColumn(tiles, p ,side);
        }
    }

    /**
     * 画出一列六边形
     * @param tiles
     * @param p
     * @param side
     */
    public static void addColumn(TETile[][] tiles, Positon p, int side) {
        for(int i = 0; i < p.var; i++) {
            addHexagon(tiles, side, new Positon(p.x, p.y - 2 * i *side, 0));
        }
    }

    /**
     * 根据左上角的点p画出一个边长为side的六边形
     * @param tiles
     * @param side
     * @param p
     */
    public static  void  addHexagon(TETile[][] tiles, int side, Positon p) {
        if (side < 2) {
            throw new IllegalArgumentException("Hexagon must be at least size 2.");
        }
        TETile teTile = randomTile();

        for(int i = 0;i < side; i++) {
            addLevelHexas(tiles,p.x - i,p.y - i,side + 2*i, teTile);
            addLevelHexas(tiles,p.x - i,p.y + i - side * 2 + 1, side + 2*i, teTile);
        }
    }

    /**
     * 画出一行图形，起始点为(x,y)
     * @param tiles 地图
     * @param x 横坐标
     * @param y 纵坐标
     * @param length 长度
     * @param teTile 添加的景象
     */
    public static void addLevelHexas(TETile[][] tiles, int x, int y, int length, TETile teTile) {
        for (int i = 0; i < length; i++) {
            tiles[x + i][y] = TETile.colorVariant(teTile,255,255,255,new Random(50*i - 10*y));
        }
    }

    /** Picks a RANDOM tile with a 33% change of being
     *  a wall, 33% chance of being a flower, and 33%
     *  chance of being empty space.
     */
    private static TETile randomTile() {
        int tileNum = RANDOM.nextInt(6);
        switch (tileNum) {
            case 0: return Tileset.WALL;
            case 1: return Tileset.FLOWER;
            case 2: return Tileset.GRASS;
            case 3: return Tileset.MOUNTAIN;
            case 4: return Tileset.FLOOR;
            case 5: return Tileset.TREE;
            default: return Tileset.NOTHING;
        }
    }

    public static void fillWithNothingTiles(TETile[][] tiles) {
        int height = tiles[0].length;
        int width = tiles.length;
        for (int x = 0; x < width; x += 1) {
            for (int y = 0; y < height; y += 1) {
                tiles[x][y] = Tileset.NOTHING;
            }
        }
    }

    public static List<Positon> calculateStart(int WIDTH, int HEIGHT, int side) {
        Positon mid = new Positon(WIDTH/2 - side/2,HEIGHT - 2, 5);
        Positon leftBeside = new Positon(mid.x - (2 * side - 1), mid.y - side, 4);
        Positon rightBeside = new Positon(mid.x + (2 * side - 1), mid.y - side, 4);
        Positon leftAway = new Positon(leftBeside.x - (2 * side - 1), leftBeside.y - side, 3);
        Positon rightAway = new Positon(rightBeside.x + (2 * side - 1), rightBeside.y - side, 3);
        List<Positon> toReturn = new ArrayList<>();
        toReturn.add(mid);
        toReturn.add(leftBeside);
        toReturn.add(leftAway);
        toReturn.add(rightBeside);
        toReturn.add(rightAway);
        return toReturn;
    }

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        int WIDTH = 11 * N - 4;
        int HEIGHT = 10 * N + 2;
         ter.initialize(WIDTH, HEIGHT);

        TETile[][] hexagonTiles = new TETile[WIDTH][HEIGHT];
        fillWithNothingTiles(hexagonTiles);
        List<Positon> positonList = calculateStart(WIDTH, HEIGHT,N);
        drawHexagon(hexagonTiles,N,positonList);
        ter.renderFrame(hexagonTiles);
    }

}
