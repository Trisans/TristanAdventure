package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
	
	public boolean isPlayer = false;
	public int worldX_px, worldY_px;
	public int speed;
	
	public String direction;
	public boolean isMoving;
	
	public BufferedImage[][] spriteList = new BufferedImage[4][4]; // player-specific
	public int spriteCounter = 0;
	public int spriteNum = 1;
	public Rectangle solidArea;
	
	public boolean collisionOn = false;
	// player-specific
	public boolean collisionUp = false;
	public boolean collisionDown = false;
	public boolean collisionLeft = false;
	public boolean collisionRight = false;
}
