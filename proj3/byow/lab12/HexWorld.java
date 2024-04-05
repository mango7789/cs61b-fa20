package byow.lab12;
import org.junit.Test;

import static org.junit.Assert.*;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {

    private static final Random RANDOM = new Random();
    private final int number = 19;
    private final int[][] index = {{2}, {1, 3}, {0, 2, 4}, {1, 3}, {0, 2, 4}, {1, 3}, {0, 2, 4}, {1, 3}, {2}};
    public int s;

    public HexWorld(int s) {
        this.s = s;
    }

    public TETile[][] Hexagon() {
        int Width = 10 * s - 3, Height = 10 * s;
        TETile[][] Hexagon = initialize(Height, Width);
        for (int row = 0; row < index.length; row++) {
            for (int col : index[row]) {
                addHexagon(s, getRowStart(s, row), getColStart(s, col), Hexagon);
            }
        }
        return transpose(Hexagon);
    }

    private void addHexagon(int s, int rowStart, int colStart, TETile[][] hexagon) {
        int height = 2 * s;
        TETile currTile = randomTile();
        // upper half
        for (int i = 0; i < s; i++) {
            int startIndex = getStart(i, s), endIndex = getEnd(i, s);
            // inside the hexagon
            for (int j = startIndex; j < endIndex; j++) {
                hexagon[i + rowStart][j + colStart] = hexagon[rowStart + height - 1 - i][j + colStart] = currTile;
            }
        }
    }

    private int getStart(int row, int s) {
        return s - 1 - row;
    }

    private int getEnd(int row, int s) {
        return 2 * s - 1 + row;
    }

    private TETile randomTile() {
        int tileNum = RANDOM.nextInt(7);
        return switch (tileNum) {
            case 0 -> Tileset.WALL;
            case 1 -> Tileset.FLOWER;
            case 2 -> Tileset.TREE;
            case 3 -> Tileset.AVATAR;
            case 4 -> Tileset.GRASS;
            case 5 -> Tileset.WATER;
            case 6 -> Tileset.SAND;
            default -> Tileset.MOUNTAIN;
        };
    }

    private int getRowStart(int s, int rowIndex) {
        return s * rowIndex;
    }

    private int getColStart(int s, int colIndex) {
        return (2 * s - 1) * colIndex;
    }

    private TETile[][] initialize(int height, int width) {
        TETile[][] Hexagon = new TETile[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Hexagon[i][j] = Tileset.NOTHING;
            }
        }
        return Hexagon;
    }

    public TETile[][] transpose(TETile[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        TETile[][] result = new TETile[cols][rows];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[j][i] = matrix[i][j];
            }
        }

        return result;
    }


}
