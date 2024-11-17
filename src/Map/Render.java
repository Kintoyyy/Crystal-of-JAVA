package Map;

import Entities.Characters.Movement.Movement;
import Game.Handler;
import Utils.DebugMode;
import Map.Tile.Tile;
import Map.Tile.TileTypes;

import java.awt.*;

public class Render {
    private final int width;
    private final int height;
    private final int[][][] TileLayers;
    private final TileTypes tileTypes;
    private final Movement movement;


    public Render(Parse parse, Movement movement) {
        this.movement = movement;
        movement.setSpawn(parse.getSpawnX(), parse.getSpawnY());
        this.width = parse.getWorldWidth();
        this.height = parse.getWorldHeight();
        this.TileLayers = parse.getLayers();
        this.tileTypes = parse.getTileTypes();
    }

    public void tick() {
        movement.tick();
    }

    public void render(Graphics g) {
        for (int layer = 0; layer < TileLayers.length; layer++) {
            Handler handler = movement.getHandler();

            int xStart = (int) Math.max(0, movement.getCamera().getXOffset() / Tile.TILEWIDTH);
            int xEnd = (int) Math.min(width, (movement.getCamera().getXOffset() + handler.getWidth()) / Tile.TILEWIDTH + 1);
            int yStart = (int) Math.max(0, movement.getCamera().getYOffset() / Tile.TILEHEIGHT);
            int yEnd = (int) Math.min(height, (movement.getCamera().getYOffset() + handler.getHeight()) / Tile.TILEHEIGHT + 1);

            for (int i = yStart; i < yEnd; i++) {
                for (int j = xStart; j < xEnd; j++) {
                    Tile tile = getTile(i, j, layer);
                    if (tile != null) {
                        int tilePosX = (int) (j * Tile.TILEWIDTH - movement.getCamera().getXOffset());
                        int tilePosY = (int) (i * Tile.TILEHEIGHT - movement.getCamera().getYOffset());

                        tile.render(g, tilePosX, tilePosY);

                        if (DebugMode.debugMode()) {
                            if (layer == DebugMode.getRenderedLayerIndex()) {
                                g.drawRect(tilePosX, tilePosY, Tile.TILEWIDTH, Tile.TILEHEIGHT);
                            }
                        }
                    }
                }
            }

            if (layer == TileLayers.length - 1) {
                movement.render(g);
            }
        }
    }

    public Tile getTile(int x, int y, int layer) {
        if (x < 0 || y < 0 || x >= width || y >= height || layer < 0 || layer >= TileLayers.length) {
            return Tile.defaultTile;
        }
        Tile t = tileTypes.getTile(TileLayers[layer][x][y]);
        if (t == null) {
            return Tile.transparentTile;
        }
        return t;
    }

}