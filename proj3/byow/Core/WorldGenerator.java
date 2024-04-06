package byow.Core;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.util.*;

public class WorldGenerator {
    private static final int GRID_WIDTH = 50;
    private static final int GRID_HEIGHT = 50;

    private static final TETile FLOOR = Tileset.FLOOR;
    private static final TETile WALL = Tileset.WALL;
    private static final TETile UNUSED = Tileset.NOTHING;

    private TETile[][] world;
    private Random random;

    public WorldGenerator() {
        world = new TETile[GRID_HEIGHT][GRID_WIDTH];
        random = new Random();
    }

    public void generateWorld() {
        // Initialize grid with unused spaces
        fillGridWithUnused();
        // Generate rooms and hallways recursively
        divide(1, 1, GRID_WIDTH - 2, GRID_HEIGHT - 2);
    }

    private void fillGridWithUnused() {
        for (int i = 0; i < GRID_HEIGHT; i++) {
            for (int j = 0; j < GRID_WIDTH; j++) {
                world[i][j] = UNUSED;
            }
        }
    }

    private void divide(int x1, int y1, int x2, int y2) {
        if (x2 - x1 < 3 || y2 - y1 < 3) {
            return;
        }

        // Choose whether to divide horizontally or vertically
        boolean divideHorizontally = random.nextBoolean();

        // Divide the space with a wall
        int wx = divideHorizontally ? x1 : (random.nextInt((x2 - x1 - 1) / 2) * 2 + x1 + 1);
        int wy = divideHorizontally ? (random.nextInt((y2 - y1 - 1) / 2) * 2 + y1 + 1) : y1;

        // Draw the wall
        if (divideHorizontally) {
            for (int i = x1; i <= x2; i++) {
                world[wy][i] = WALL;
            }
        } else {
            for (int i = y1; i <= y2; i++) {
                world[i][wx] = WALL;
            }
        }

        // Create gaps in the wall
        int gapX = divideHorizontally ? (random.nextInt((x2 - x1) / 2) * 2 + x1) : wx;
        int gapY = divideHorizontally ? wy : (random.nextInt((y2 - y1) / 2) * 2 + y1);

        world[gapY][gapX] = FLOOR;

        // Recursively divide the four quadrants
        divide(x1, y1, wx - 1, wy - 1);
        divide(wx + 1, wy + 1, x2, y2);
        divide(x1, wy + 1, wx - 1, y2);
        divide(wx + 1, y1, x2, wy - 1);
    }

    private void printWorld() {
        for (int i = 0; i < GRID_HEIGHT; i++) {
            for (int j = 0; j < GRID_WIDTH; j++) {
                System.out.print(world[i][j]);
            }
            System.out.println();
        }
    }

    private void renderWorld() {
        TERenderer ter = new TERenderer();
        ter.initialize(GRID_WIDTH, GRID_HEIGHT);
        ter.renderFrame(world);
    }

    public static void main(String[] args) {
        WorldGenerator worldGenerator = new WorldGenerator();
        worldGenerator.generateWorld();
        worldGenerator.renderWorld();
    }
}
