package object;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject {
	public BufferedImage image;
	public String name;
	public boolean collision = false;
	public int worldX_px, worldY_px;
	
	public void draw(Graphics2D g2, GamePanel gp) {
		int screenX = this.worldX_px - gp.player.worldX_px + gp.player.SCREEN_X_PX;
		int screenY = this.worldY_px - gp.player.worldY_px + gp.player.SCREEN_Y_PX;
		g2.drawImage(image, screenX, screenY, gp.TILE_SIZE, gp.TILE_SIZE, null);
	}
}
