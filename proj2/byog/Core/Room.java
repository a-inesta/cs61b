package byog.Core;

import byog.TileEngine.TETile;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Room {
    private final Position leftBot;
    private final Position rightTop;
    private final int width;
    private final int height;
    private int doors;
    private ArrayList<Position> allPositions;

    public Room(Position posLeftBot, int width, int height) {
        this.leftBot = posLeftBot;
        this.width = width;
        this.height = height;
        this.rightTop = new Position(leftBot.x + width - 1, leftBot.y + height - 1);
        this.doors = 0;
        allPositions = Position.allPositions(leftBot,rightTop);
    }

    public ArrayList<Position> getAllPositions() {
        return allPositions;
    }
    public boolean isConnected() {
        return doors > 0;
    }
    public void addDoor() {
        doors += 1;
    }
    public int doors() {
        return doors;
    }
    public Position rightTop() {
        return rightTop;
    }

    public Position leftBot() {
        return leftBot;
    }

    public int width() {
        return width;
    }

    public int height() {
        return height;
    }

    public boolean overlap(Room room) {
        int x1 = leftBot.x;
        int x2 = rightTop.x;
        int x3 = room.leftBot().x;
        int x4 = room.rightTop().x;
        int y1 = leftBot.y;
        int y2 = rightTop.y;
        int y3 = room.leftBot().y;
        int y4 = room.rightTop().y;
        // Left x
        int leftX = Math.max(x1, x3);
        // Right x
        int rightX = Math.min(x2, x4);
        // Bottom y
        int botY = Math.max(y1, y3);
        // TopY
        int topY = Math.min(y2, y4);

        if (rightX > leftX - 2 && topY > botY - 2)
            return true;
        return false;
    }


    public void openDoors(Room room, TETile[][] world, Random rand) {
        int n = RandomUtils.uniform(rand,4);
        for (int i = 0; i < n; i++) {
            int a = RandomUtils.uniform(rand,4);

        }
    }


}
