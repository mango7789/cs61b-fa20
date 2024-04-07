package byow.Core;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.util.*;

public class WorldGenerator {
    private int width;
    private int height;
    private TETile[][] worldGrid;
    private Random random;

    public WorldGenerator(int width, int height, long seed) {
        this.width = width;
        this.height = height;
        this.worldGrid = new TETile[height][width];
        this.random = new Random();
    }

    public void generateWorld() {
        initialize();
        // Generate rooms
        int numRooms = random.nextInt(10) + 10; // Random number of rooms between 3 and 7
        for (int i = 0; i < numRooms; i++) {
            int roomWidth = random.nextInt(8) + 4; // Random room width between 4 and 11
            int roomHeight = random.nextInt(6) + 4; // Random room height between 4 and 9
            int x = random.nextInt(width - roomWidth);
            int y = random.nextInt(height - roomHeight);
            createRoom(x, y, roomWidth, roomHeight);
        }

        // Generate hallways
        int numHallways = random.nextInt(3) + 2; // Random number of hallways between 2 and 4
        for (int i = 0; i < numHallways; i++) {
            int startX = random.nextInt(width);
            int startY = random.nextInt(height);
            int length = random.nextInt(10) + 5; // Random hallway length between 5 and 14
            createHallway(startX, startY, length);
        }
    }

    private void initialize() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                worldGrid[i][j] = Tileset.NOTHING;
            }
        }
    }

    private void createRoom(int x, int y, int width, int height) {
        for (int i = y; i < y + height; i++) {
            for (int j = x; j < x + width; j++) {
                if (i == y || i == y + height - 1 || j == x || j == x + width - 1) {
                    worldGrid[i][j] = Tileset.WALL; // Wall
                } else {
                    worldGrid[i][j] = Tileset.FLOOR; // Floor
                }
            }
        }
    }

    private void createHallway(int startX, int startY, int length) {
        int direction = random.nextInt(4); // 0: up, 1: right, 2: down, 3: left
        int x = startX;
        int y = startY;
        for (int i = 0; i < length; i++) {
            if (x >= 0 && x < width && y >= 0 && y < height) {
                worldGrid[y][x] = Tileset.FLOOR; // Floor
                switch (direction) {
                    case 0: y--; break;
                    case 1: x++; break;
                    case 2: y++; break;
                    case 3: x--; break;
                }
            } else {
                break;
            }
        }
    }

    private void renderWorld() {
        TERenderer ter = new TERenderer();
        ter.initialize(width, height);
        ter.renderFrame(worldGrid);
    }

    public static void main(String[] args) {
        WorldGenerator worldGenerator = new WorldGenerator(100, 40, 1L);
        worldGenerator.generateWorld();
        worldGenerator.renderWorld();
    }
}
