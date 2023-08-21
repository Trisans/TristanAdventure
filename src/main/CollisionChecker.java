package main;

import entity.Entity;

public class CollisionChecker {
	GamePanel gp;
	public CollisionChecker(GamePanel gp) {
		this.gp = gp;
	}
	
	public void checkTile(Entity entity) {
		int entityLeftWorldXPx = entity.worldX_px + 9;
		int entityRightWorldXPx = entity.worldX_px + 9 + entity.solidArea.width;
		int entityTopWorldYPx = entity.worldY_px + 33;
		int entityBottomWorldYPx = entity.worldY_px + 33 + entity.solidArea.height;
		
		int entityLeftCol = entityLeftWorldXPx / gp.TILE_SIZE;
		int entityRightCol = entityRightWorldXPx / gp.TILE_SIZE;
		int entityTopRow = entityTopWorldYPx / gp.TILE_SIZE;
		int entityBottomRow = entityBottomWorldYPx / gp.TILE_SIZE;
		
		int tileNum1 = 1000;
		int tileNum2 = 1000;
		
		switch (entity.direction) {
			case "up":
				// if the block 4 pixels ahead of the player is solid, set entity.collisionOn to true
				entityTopRow = ((entityTopWorldYPx - entity.speed) / gp.TILE_SIZE);
				tileNum1 = gp.tileM.map[entityTopRow][entityLeftCol];
				tileNum2 = gp.tileM.map[entityTopRow][entityRightCol];
//				System.out.println(entityLeftCol);
				if (gp.tileM.tiles[tileNum1].collision || gp.tileM.tiles[tileNum2].collision) {
					entity.collisionOn = true;
				}
				break;
			case "down":
				entityBottomRow = ((entityBottomWorldYPx + entity.speed) / gp.TILE_SIZE);
				tileNum1 = gp.tileM.map[entityBottomRow][entityLeftCol];
				tileNum2 = gp.tileM.map[entityBottomRow][entityRightCol];
				if (gp.tileM.tiles[tileNum1].collision || gp.tileM.tiles[tileNum2].collision) {
					entity.collisionOn = true;
				}
				break;
			case "left":
				entityLeftCol = ((entityLeftWorldXPx - entity.speed) / gp.TILE_SIZE);
				tileNum1 = gp.tileM.map[entityTopRow][entityLeftCol];
				tileNum2 = gp.tileM.map[entityBottomRow][entityLeftCol];
				
				if (gp.tileM.tiles[tileNum1].collision || gp.tileM.tiles[tileNum2].collision) {
					entity.collisionOn = true;
				}
				break;
			case "right":
				entityRightCol = ((entityRightWorldXPx + entity.speed) / gp.TILE_SIZE);
				tileNum1 = gp.tileM.map[entityTopRow][entityRightCol];
				tileNum2 = gp.tileM.map[entityBottomRow][entityRightCol];
				
				if (gp.tileM.tiles[tileNum1].collision || gp.tileM.tiles[tileNum2].collision) {
					entity.collisionOn = true;
				}
				break;
		}
		
		System.out.println(entity.worldX_px / gp.TILE_SIZE + ", " + entity.worldY_px / gp.TILE_SIZE + " - " + tileNum1 +
				", " + tileNum2 + " - " +
				gp.tileM.tiles[tileNum1].collision + ", " + gp.tileM.tiles[tileNum2].collision);
		
	}
}
