package World;

import Entities.EntityManager;
import CharacterMovement.Player;
import Utils.DebugMode;
import Game.Handler;
import org.w3c.dom.*;

import javax.xml.parsers.*;
import java.awt.*;
import java.io.File;

public class World {

    private final Handler handler;
    private int width, height;
    private int spawnX, spawnY;
    private int[][][] tiles;
    private final EntityManager entityManager;

    private TileSet tileSet;

    public World(Handler handler, String path) {
        this.handler = handler;

        entityManager = new EntityManager(handler, new Player(handler, 0, 0));

        loadWorld(path);

//        for (int[][] tile : tiles) {
//            for (int i = 0; i < width; i++) {
//                for (int j = 0; j < height; j++) {
//                    if (tile[j][i] >= 96 && tile[j][i] < 100) {
////                        entityManager.addEntity(new Tree(handler, (int) (j * Tile.TILEWIDTH), (int) (i * Tile.TILEWIDTH) - (int) (Tile.TILEWIDTH * 1.5), 99 - tile[j][i]));
//                    }
//                }
//            }
//        }
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void tick() {
        entityManager.tick();
    }

    public void render(Graphics g) {
        for (int layer = 0; layer < tiles.length; layer++) {
            int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILEWIDTH);
            int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILEWIDTH + 1);
            int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILEHEIGHT);
            int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILEHEIGHT + 1);

            for (int i = yStart; i < yEnd; i++) {
                for (int j = xStart; j < xEnd; j++) {
                    Tile tile = getTile(i, j, layer);
                    if (tile != null) {
                        int tilePosX = (int) (j * Tile.TILEWIDTH - handler.getGameCamera().getxOffset());
                        int tilePosY = (int) (i * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset());
                        tile.render(g, tilePosX, tilePosY);

                        if (DebugMode.debugMode()) {
                            if (layer == DebugMode.getRenderedLayerIndex()) {
                                g.drawRect(tilePosX, tilePosY, Tile.TILEWIDTH, Tile.TILEHEIGHT);
                            }
                        }
                    }
                }
            }

            if (layer == tiles.length - 1) {
//                entityManager.render(g);
            }
        }
    }

    public Tile getTile(int x, int y, int layer) {
        if (x < 0 || y < 0 || x >= width || y >= height || layer < 0 || layer >= tiles.length) {
            return Tile.defaultTile;
        }

        Tile t = tileSet.getTile(tiles[layer][x][y]);

        if (t == null) {
            return Tile.transparentTile;
        }

        return t;
    }

    public Tile getTile(int x, int y) {
        return getTile(x, y, 1);
    }

    private void loadWorld(String path) {
        try {
            File inputFile = new File(path);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);

            // Extract the map element
            NodeList map = doc.getElementsByTagName("map");
            if (map.getLength() > 0) {
                Element mapElement = (Element) map.item(0);
                // Initialize TileSet
                NodeList tilesets = mapElement.getElementsByTagName("tileset");
                tileSet = new TileSet(tilesets);

                // Retrieve map dimensions
                this.width = Integer.parseInt(mapElement.getAttribute("width"));
                this.height = Integer.parseInt(mapElement.getAttribute("height"));

                // Initialize TileArraySet with extracted dimensions
                NodeList layers = mapElement.getElementsByTagName("layer");
                tiles = new TileArraySet(layers, width, height).getTiles();

                spawnX = mapElement.hasAttribute("spawnX") ?
                        Integer.parseInt(mapElement.getAttribute("spawnX")) : 10;
                spawnY = mapElement.hasAttribute("spawnY") ?
                        Integer.parseInt(mapElement.getAttribute("spawnY")) : 10;

                entityManager.getPlayer().setX(spawnX * Tile.TILEWIDTH);
                entityManager.getPlayer().setY(spawnY * Tile.TILEWIDTH);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getSpawnX() {
        return spawnX;
    }

    public int getSpawnY() {
        return spawnY;
    }

    public void setSpawnX(int spawnX) {
        this.spawnX = spawnX;
    }

    public void setSpawnY(int spawnY) {
        this.spawnY = spawnY;
    }


}
