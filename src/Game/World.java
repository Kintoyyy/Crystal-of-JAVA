package Game;

import Assets.EntityManager;
import Assets.Player;
import Assets.Tile;
import Assets.Tree;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.awt.*;
import java.io.File;

public class World {

	private Handler handler;
	private int width, height;
	private int spawnX, spawnY;
	private int[][][] tiles;
	private EntityManager entityManager;

	public World(Handler handler, String path) {
		this.handler = handler;
		entityManager = new EntityManager(handler, new Player(handler, 100, 100));

		loadWorld(path);

		entityManager.getPlayer().setX(spawnX * Tile.TILEWIDTH + 10);
		entityManager.getPlayer().setY(spawnY * Tile.TILEHEIGHT + 16);

		for (int layer = 0; layer < tiles.length; layer++) {
			for (int i = 0; i < width; i++) {
				for (int j = 0; j < height; j++) {
					if (tiles[layer][j][i] >= 96 && tiles[layer][j][i] < 100) {
						entityManager.addEntity(new Tree(handler, (int) (j * Tile.TILEWIDTH), (int) (i * Tile.TILEWIDTH) - (int) (Tile.TILEWIDTH * 1.5), 99 - tiles[layer][j][i]));
					}
				}
			}
		}
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
						tile.render(g, (int) (j * Tile.TILEWIDTH - handler.getGameCamera().getxOffset()), (int) (i * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));
					}
				}
			}

			if (layer == 1) {
				entityManager.render(g);
			}
		}
	}

	public Tile getTile(int x, int y, int layer) {
		if (x < 0 || y < 0 || x >= width || y >= height || layer < 0 || layer >= tiles.length) {
			return Tile.grassTile;
		}

		Tile t = Tile.tiles[tiles[layer][x][y]];
		if (t == null) {
			return Tile.grassTile;
		}
		return t;
	}

	private void loadWorld(String path) {
		try {
			File inputFile = new File(path);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();

			NodeList tilemap = doc.getElementsByTagName("tilemap");
			if (tilemap.getLength() > 0) {
				Element tilemapElement = (Element) tilemap.item(0);
				width = Integer.parseInt(tilemapElement.getAttribute("tileswide"));
				height = Integer.parseInt(tilemapElement.getAttribute("tileshigh"));

				NodeList layers = tilemapElement.getElementsByTagName("layer");
				tiles = new int[layers.getLength()][height][width]; // Initialize 3D array

				for (int l = 0; l < layers.getLength(); l++) {
					Element layer = (Element) layers.item(l);
					NodeList tileNodes = layer.getElementsByTagName("tile");
					for (int i = 0; i < tileNodes.getLength(); i++) {
						Element tile = (Element) tileNodes.item(i);
						int x = Integer.parseInt(tile.getAttribute("x"));
						int y = Integer.parseInt(tile.getAttribute("y"));
						int index = Integer.parseInt(tile.getAttribute("index"));

						tiles[l][y][x] = index;
					}
				}

				spawnX = tilemapElement.hasAttribute("spawnX") ?
						Integer.parseInt(tilemapElement.getAttribute("spawnX")) : 10;
				spawnY = tilemapElement.hasAttribute("spawnY") ?
						Integer.parseInt(tilemapElement.getAttribute("spawnY")) : 10;

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Tile getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height) {
			return Tile.grassTile;
		}

		for (int layer = tiles.length - 1; layer >= 0; layer--) {
			int tileIndex = tiles[layer][y][x];
			Tile t = Tile.tiles[tileIndex];

			if (t != null) {
				return t;
			}
		}
		return Tile.grassTile;
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
