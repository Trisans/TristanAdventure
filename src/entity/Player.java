package entity;

import main.GamePanel;
import main.KeyHandler;
import org.w3c.dom.css.Rect;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {
	
	GamePanel gp;
	KeyHandler keyH;
	public int SCREEN_X_PX;
	public int SCREEN_Y_PX;
	public Player(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;
		
		setDefaultValues();
		getPlayerImage();
	}
	public void setDefaultValues() {
		worldX_px = gp.TILE_SIZE * 10;
		worldY_px = gp.TILE_SIZE * 10;
		SCREEN_X_PX = gp.MAX_SCREEN_COL * (gp.TILE_SIZE / 2) - (gp.TILE_SIZE / 2);
		SCREEN_Y_PX = gp.MAX_SCREEN_ROW * (gp.TILE_SIZE / 2) - (gp.TILE_SIZE / 2);
		speed = 4;
		direction = "down";
		isMoving = false;
		solidArea = new Rectangle(SCREEN_X_PX + 9, SCREEN_Y_PX + 33, 30, 12);
		
	}
	
	public void getPlayerImage() {
		try {
			up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_up_1.png")));
			up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_up_2.png")));
			up3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_up_3.png")));
			down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_down_1.png")));
			down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_down_2.png")));
			down3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_down_3.png")));
			left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_left_1.png")));
			left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_left_2.png")));
			left3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_left_3.png")));
			right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_right_1.png")));
			right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_right_2.png")));
			right3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_right_3.png")));
			
		} catch (IOException e) {
			System.out.println("Could not load images" + e);
		}
	}
	
	public void update() {
		double tempSpeed = speed;
		if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
			// if any movement keys are being pressed
			isMoving = true;
//			if ((keyH.upPressed || keyH.downPressed) && (keyH.leftPressed || keyH.rightPressed)) { // moving diagonally
//				// reduce player speed so moving diagonally does not increase speed
//				tempSpeed = speed / Math.sqrt(2);
//			}
			// move player
			if (keyH.upPressed) {
				direction = "up";
			} else if (keyH.downPressed) {
				direction = "down";
			}
			else if (keyH.leftPressed) {
				direction = "left";
				
			} else if (keyH.rightPressed) {
				direction = "right";
			}
			
			// check collision
			collisionOn = false;
			gp.cChecker.checkTile(this);
//			System.out.println(collisionOn);
			
			if (!collisionOn) {
				if (keyH.upPressed) {
					worldY_px -= tempSpeed;
				} else if (keyH.downPressed) {
					worldY_px += tempSpeed;
				}
				else if (keyH.leftPressed) {
					worldX_px -= tempSpeed;
				} else if (keyH.rightPressed) {
					worldX_px += tempSpeed;
				}
				
				// update sprite
				spriteCounter++;
				if (spriteCounter > 6) { // change sprite every 10 frames because update is called 60 times per second
					switch (spriteNum) {
						case 1:
							spriteNum = 2;
							break;
						case 2:
							spriteNum = 1;
							break;
					}
					spriteCounter = 0;
				}
			}
			
			
		} else {
			isMoving = false;
		}
	}
	public void draw(Graphics2D g2) {
		BufferedImage image = null;
//		System.out.println("X: " + (worldX_px / gp.TILE_SIZE) + ", Y: " + (worldY_px / gp.TILE_SIZE));
		if (isMoving) {
			switch (direction) {
				case "up":
					image = up2;
					if (spriteNum == 2) image = up3;
					break;
				case "down" :
					image = down2;
					if (spriteNum == 2) image = down3;
					break;
					
				case "right" :
					image = right2;
					if (spriteNum == 2) image = right3;
					break;
				case "left":
					image = left2;
					if (spriteNum == 2) image = left3;
					break;
			}
		} else {
			switch (direction) {
				case "up":
					image = up1;
					break;
				case "down":
					image = down1;
					break;
					
				case "left":
					image = left1;
					break;
				case "right":
					image = right1;
					break;
			}
			spriteCounter = 0;
		}
		
		g2.drawImage(image, SCREEN_X_PX, SCREEN_Y_PX, gp.TILE_SIZE, gp.TILE_SIZE, null);
		g2.fillRect(SCREEN_X_PX + 9, SCREEN_Y_PX + 33, 30, 12);
	}
}
