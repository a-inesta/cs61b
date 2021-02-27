package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.*;

public class MapGenerator {
    private Random rand;
    private TETile[][] world;
    private int width;
    private int height;
    List<Position> positions;
    List<Room> rooms;

    public MapGenerator(Random r, int width, int height) {
        rand = r;
        this.width = width;
        this.height = height;
        this.positions = new ArrayList<>();
        this.rooms = new ArrayList<>();
        this.world = new TETile[width][height];
    }

    public TETile[][] world() {
        return world;
    }

    public void initWorld() {
        for (int i = 0; i < world.length; i++) {
            for (int j = 0; j < world[0].length; j++) {
                world[i][j] = Tileset.NOTHING;
            }
        }
        fillMap();
        startPosition();
        generateRooms();
        delDuplicate();
        drawRooms();

        Hallway.hallwayGenerator(rand, world);
        connectRooms();
        removeDeadEnds();
        removeEnds();
    }

    private void removeEnds() {
        for (int i = 0; i < width ; i++) {
            for (int j = 0; j < height ; j++) {
                if (i != width - 1 && world[i+1][j  ] == Tileset.FLOOR) continue;
                if (i != 0 && world[i-1][j  ] == Tileset.FLOOR) continue;
                if ((i != width - 1 && j != height - 1) && world[i+1][j+1] == Tileset.FLOOR) continue;
                if ((i != 0 && j != 0) && world[i - 1][j - 1] == Tileset.FLOOR) continue;
                if (j != height - 1 && world[i  ][j+1] == Tileset.FLOOR) continue;
                if (j != 0 && world[i  ][j-1] == Tileset.FLOOR) continue;
                if ((i != 0 && j != height - 1) && world[i-1][j+1] == Tileset.FLOOR) continue;
                if ((i != width - 1 && j != 0) && world[i+1][j-1] == Tileset.FLOOR) continue;
                world[i][j] = Tileset.NOTHING;
            }
        }
    }

    private void connectRooms() {
        //遍历Room
        for (Room r : rooms) {
            int n = RandomUtils.uniform(rand,2) + 1;
            List<Position> doors = potentialDoors(r); //找出能作为门的墙壁
            for (int i = 0; i < n; i++) {
                if (!doors.isEmpty()) {
                    int m = RandomUtils.uniform(rand,doors.size());
                    Position door = doors.remove(m);
                    world[door.x][door.y] = Tileset.FLOOR;
                }
            }

        }
    }

    /**
     * 遍历房间的墙壁，当墙壁两侧都是FLOOR的时候将其加入list
     * @param room
     * @return
     */
    private List<Position> potentialDoors(Room room) {
        List<Position> list = new ArrayList<>();
        int xStart = room.leftBot().x;
        int yStart = room.leftBot().y;
        int xEnd = room.rightTop().x;
        int yEnd = room.rightTop().y;
        for (int i = 0; i < room.width(); i++) {
            if (world[i + xStart][yStart - 1] == Tileset.FLOOR
                    && world[i + xStart][yStart + 1] == Tileset.FLOOR) {
                list.add(new Position(i + xStart,yStart));
            }
            if (world[i + xStart][yEnd - 1] == Tileset.FLOOR
                    && world[i + xStart][yEnd + 1] == Tileset.FLOOR) {
                list.add(new Position(i + xStart,yEnd));
            }
        }
        for (int i = 0; i < room.height(); i++) {
            if (world[xStart - 1][yStart + i] == Tileset.FLOOR
                    && world[1 + xStart][yStart + i] == Tileset.FLOOR) {
                list.add(new Position(xStart,yStart + i));
            }
            if (world[xEnd - 1][yStart + i] == Tileset.FLOOR
                    && world[xEnd + 1][yStart + i] == Tileset.FLOOR) {
                list.add(new Position(xEnd,yStart + i));
            }
        }
        return list;
    }

    private void removeDeadEnds() {
        boolean done = false;

        while (!done) {
            done = true;
            for (int i = 0; i < world[0].length; i++) {
                for (int j = 0; j < world.length; j++) {
                    if (world[j][i] != Tileset.FLOOR) {
                        continue;
                    }
                    if (!isInDeadEnd(new Position(j, i))) {
                        continue;
                    }
                    done = false;
                    world[j][i] = Tileset.WALL;
                }
            }
        }
    }

    private boolean isInDeadEnd(Position p) {
        int cnt = 0;
        if(world[p.x + 1][p.y    ] == Tileset.WALL){cnt++;}
        if(world[p.x - 1][p.y    ] == Tileset.WALL){cnt++;}
        if(world[p.x    ][p.y + 1] == Tileset.WALL){cnt++;}
        if(world[p.x    ][p.y - 1] == Tileset.WALL){cnt++;}
        return cnt >= 3;
    }
    private void delDuplicate() {
        for (int i = 0; i < rooms.size(); i++) {
            for (int j = i + 1; j < rooms.size(); j++) {
                if (rooms.get(i).overlap(rooms.get(j))) {
                    rooms.remove(rooms.get(j));
                    j--;
                }
            }
        }
    }

    private void drawRooms() {
        for (Room r : rooms
        ) {
            addRooms(r, Tileset.FLOOR);
        }
    }

    /**
     * Draw the room in the map with specific type.
     */
    private void addRooms(Room r, TETile t) {
        addFloor(r);
        addWall(r);
    }


    private void addFloor(Room r) {
        TETile floor = Tileset.FLOOR;
        for (int i = 0; i < r.height() - 2; i++) {
            horizontalLine(r.leftBot().x + 1, r.leftBot().y + 1 + i, r.width() - 2,
                    floor);
        }

    }

    /**
     * Draw  wall.
     * ********
     * *      *
     * *      *
     * ********
     *
     * @param r the room to add wall
     */
    private void addWall(Room r) {
        TETile wall = Tileset.WALL;
        int x = r.leftBot().x;
        int y = r.leftBot().y;
        horizontalLine(x, y, r.width(), wall);
        horizontalLine(x, y + r.height() - 1, r.width(), wall);
        verticalLine(x, y, r.height(), wall);
        verticalLine(x + r.width() - 1, y, r.height(), wall);
    }

    /**
     * 画一条水平线
     *
     * @param a      画线的起始点横坐标
     * @param b      纵坐标
     * @param length 画线的长度
     * @param t      画线的填充类型
     */
    private void horizontalLine(int a, int b, int length, TETile t) {
        int x = Math.min(a, this.width - 1);
        int y = Math.min(b, this.height - 1);
        for (int i = 0; i < length && x + i < this.width - 1; i++) {
            world[x + i][y] = t;
        }
    }

    /**
     * 画一条垂直线
     *
     * @param x      画线的起始点横坐标
     * @param y      纵坐标
     * @param length 画线的长度
     * @param t      画线的填充类型
     */
    private void verticalLine(int x, int y, int length, TETile t) {
        int a = Math.min(x, this.width - 1);
        int b = Math.min(y, this.height - 1);
        for (int i = 0; i < length && b + i < this.height - 1; i++) {
            world[a][b + i] = t;
        }
    }

    private void generateRooms() {

        for (Position pos : positions) {
            int width = RandomUtils.uniform(rand, 6, 15);
            int height = RandomUtils.uniform(rand, 6, 10);
            Room room = new Room(pos, Math.min(width, this.width - pos.x - 1), Math.min(height, this.height - pos.y - 1));
            rooms.add(room);
        }
    }

    private void startPosition() {
        int n = width * height / 20 + RandomUtils.uniform(rand, width * height / 40);
        for (int i = 0; i < n; i++) {
            int x = RandomUtils.uniform(rand, width - 10) + 2;
            int y = RandomUtils.uniform(rand, height - 10) + 2;
            if(world[x][y] != Tileset.WALL) continue;
            Position p = new Position(x, y);
            positions.add(p);
        }
        Collections.sort(positions);
    }

    private void fillMap() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (world[i][j] == Tileset.NOTHING) {
                    if (i % 2 == 0) {
                        world[i][j] = Tileset.WALL;
                    } else {
                        if (j % 2 == 0) {
                            world[i][j] = Tileset.WALL;
                        }
                    }
                }
            }
        }
    }

}
