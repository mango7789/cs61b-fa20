package byow.lab12;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;

public class HexWorldLauncher {
    public static void main(String[] args) {
        TERenderer ter = new TERenderer();

        int s = 3;
        int Width = 10 * s - 3, Height = 10 * s;

        ter.initialize(Width, Height);

        HexWorld hw = new HexWorld(s);
        TETile[][] randomTiles = hw.Hexagon();

        ter.renderFrame(randomTiles);
    }



}
