package Game;

import CharacterMovement.Entity;
import Entities.Characters.Movement;
import World.Tile;

public class GameCamera {
	
	private Handler handler;
	private float xOffset, yOffset;
	
	public GameCamera(Handler handler, float xOffset, float yOffset) {
		this.handler = handler;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
	
	public void checkBlankSpace() {
		if(xOffset < 0) {
			xOffset = 0;
		} else if(xOffset > handler.getWorld().getWidth() * Tile.TILEWIDTH - handler.getWidth()) {
			xOffset = handler.getWorld().getWidth() * Tile.TILEWIDTH - handler.getWidth();
		}
		
		if(yOffset < 0) {
			yOffset = 0;
		} else if(yOffset > handler.getWorld().getHeight() * Tile.TILEHEIGHT - handler.getHeight()) {
			yOffset = handler.getWorld().getHeight() * Tile.TILEHEIGHT - handler.getHeight();
		}
	} 
	
	public void centerOnEntity(Entity e) {
		xOffset = e.getX() - (float) handler.getWidth() / 2 + (float) e.getWidth() / 2;
		yOffset = e.getY() - (float) handler.getHeight() / 2 + (float) e.getHeight() / 2;
		checkBlankSpace();
	}

	public void centerThis(Movement e) {
		xOffset = e.getX() - (float) handler.getWidth() / 2 + (float) e.getWidth() / 2;
		yOffset = e.getY() - (float) handler.getHeight() / 2 + (float) e.getHeight() / 2;
		checkBlankSpace();
	}


	public void move(float xAmount, float yAmount) {
		xOffset += xAmount;
		yOffset += yAmount;
	}

	public float getxOffset() {
		return xOffset;
	}

	public void setxOffset(float xOffset) {
		this.xOffset = xOffset;
	}

	public float getyOffset() {
		return yOffset;
	}

	public void setyOffset(float yOffset) {
		this.yOffset = yOffset;
	}
}
