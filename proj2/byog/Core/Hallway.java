package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Hallway {
    static LinkedList<Position> positions = new LinkedList<>();
    static List<Position> hallway = new ArrayList<>();
    static List<Position> NOTHING = new ArrayList<>();

    public static void hallwayGenerator(Random random, TETile[][] world) {

        for (int i = 0; i < world.length; i++) {
            for (int j = 0; j < world[0].length; j++) {
                if(world[i][j] == Tileset.NOTHING) {
                    NOTHING.add(new Position(i,j));
                }
            }
        }

        while ( !NOTHING.isEmpty()) {
            int n = RandomUtils.uniform(random,NOTHING.size());
            int x = NOTHING.get(n).x;
            int y = NOTHING.get(n).y;
            recursiveBackTracker(x, y, world, random);
        }
    }

    private static void recursiveBackTracker(int x, int y, TETile[][] world, Random random) {
        world[x][y] = Tileset.FLOOR;
        positions.addFirst(new Position(x, y));
        while (!positions.isEmpty()) {
            Position curPos = positions.getFirst();
            List<Position> availablePos = checkPath(curPos,world);
            if (!availablePos.isEmpty()) {
                connectPath(availablePos, curPos, random, world); //connectPath随机连接一条路
            } else {
                NOTHING.remove(positions.removeFirst()); //如果没路，pop
            }
        }

    }
    private static void connectPath(List<Position> list,Position p,Random r, TETile[][] world) {
        int m = list.size();
        int t = RandomUtils.uniform(r,m);
        Position toConnect = list.get(t);
        if (toConnect.x == p.x) {
            world[p.x][(toConnect.y - p.y)/2 + p.y] = Tileset.FLOOR;
            hallway.add(new Position(p.x,(toConnect.y - p.y)/2 + p.y));
        } else {
            world[(toConnect.x - p.x)/2 + p.x][p.y] = Tileset.FLOOR;
            hallway.add(new Position((toConnect.x - p.x)/2 + p.x,p.y));
        }
        world[p.x][p.y] = Tileset.FLOOR;
        world[toConnect.x][toConnect.y] = Tileset.FLOOR;
        hallway.add(toConnect);
        positions.addFirst(toConnect);
    }
    private static boolean isEnd(int x, int y, TETile[][] world) {
        int cnt = 0;
        if(world[x - 1][y] == Tileset.WALL) { cnt++; }
        if(world[x + 1][y] == Tileset.WALL) { cnt++; }
        if(world[x][y - 1] == Tileset.WALL) { cnt++; }
        if(world[x][y + 1] == Tileset.WALL) { cnt++; }
        return cnt >= 3;
    }
    private static List<Position> checkPath(Position p, TETile[][] world) {
        List<Position> availablePos = new ArrayList<>();
        int a = p.x;
        int b = p.y;
        if (isInside(a + 2, b, world) && world[a + 2][b] == Tileset.NOTHING) {
            availablePos.add(new Position(a+2,b));
        }
        if (isInside(a - 2, b, world) && world[a - 2][b] == Tileset.NOTHING) {
            availablePos.add(new Position(a-2,b));
        }

        if (isInside(a, b + 2, world) && world[a][b + 2] == Tileset.NOTHING) {
            availablePos.add(new Position(a,b+2));
        }

        if (isInside(a, b - 2, world) && world[a][b - 2] == Tileset.NOTHING) {
            availablePos.add(new Position(a,b-2));
        }
        return availablePos;
    }
    private static boolean isInside(int x, int y, TETile[][] world) {
        if (x > world.length - 1 || x < 0) {
            return false;
        }
        if (y > world[0].length - 1 || y < 0) {
            return false;
        }
        return true;
    }
}
