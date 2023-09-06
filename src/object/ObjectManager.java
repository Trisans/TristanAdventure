package object;

import main.GamePanel;

import java.awt.*;

public class ObjectManager {
	GamePanel gp;
	
	public ObjectManager(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setObject() {
		gp.obj[0] = new ObjectChest();
		gp.obj[0].worldX_px = 23 * gp.TILE_SIZE;
		gp.obj[0].worldY_px = 44 * gp.TILE_SIZE;
		
		gp.obj[1] = new ObjectKey();
		gp.obj[1].worldX_px = 22 * gp.TILE_SIZE;
		gp.obj[1].worldY_px = 7 * gp.TILE_SIZE;
	}
	public void draw(Graphics2D g2) {
		int maxX = gp.player.worldX_px + gp.player.SCREEN_X_PX + gp.TILE_SIZE;
		int minX = gp.player.worldX_px - gp.player.SCREEN_X_PX - gp.TILE_SIZE;
		
		int maxY = gp.player.worldY_px + gp.player.SCREEN_Y_PX + gp.TILE_SIZE;
		int minY = gp.player.worldY_px - gp.player.SCREEN_Y_PX - gp.TILE_SIZE;
//		System.out.println(minX + ", " + maxX + " : " + minY + ", " + maxY + " : "
//				+ gp.obj[0].worldX_px + ", " + gp.obj[0].worldY_px);
		for (int i = 0; i < gp.obj.length && gp.obj[i] != null; i++) {
			
			if (gp.obj[i].worldX_px > minX && gp.obj[i].worldX_px < maxX &&
					gp.obj[i].worldY_px > minY && gp.obj[i].worldY_px < maxY) {
				System.out.println("draw 3");
				gp.obj[i].draw(g2, gp);
			}
		}
	}
}
