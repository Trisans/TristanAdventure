package main;

import entity.Player;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    // SCREEN SETTINGS
    final int ORIGINAL_TILE_SIZE = 16; // 16x16 tile
    final int SCALE = 3;

    public final int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE; // 48x48 tile
    final int MAX_SCREEN_COL = 16; // the number of tiles displayed horizontally in the game window
    final int MAX_SCREEN_ROW = 12; // the number of tiles displayed vertically in the game window
    final int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COL; // 768px
    final int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROW; // 576px

    KeyHandler keyH = new KeyHandler();
    Thread gameThread; // when this thread is created, the run method is automatically called
    Player player = new Player(this, keyH);
    
    // set player's default position
    int playerX = 100;
    int playerY = 100;
    double playerSpeed = 4;
    
    // FPS
    final int FPS = 60;

    public GamePanel() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT)); // set screen size
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); // improves rendering performance
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start(); // calls the run method
    }

    @Override
    public void run() {
        
        double drawInterval = 1000000000 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval; // when System.nanoTime() = nextDrawTime, draw the screen again

        while (gameThread.isAlive()) {
            double startTime = System.nanoTime();
            update();

            repaint(); // somehow this calls paintComponent
    
            try { // all this code maintains fps
                
                double remainingTime = (nextDrawTime - System.nanoTime()) / 1000000; // = 0.016 seconds - time update() and repaint() take, in milliseconds
                
                if (remainingTime < 0) {
                    remainingTime = 0;
                }
                
                Thread.sleep((long)remainingTime);
                
                nextDrawTime += drawInterval;
//                System.out.println("FPS: " + 1000000000 /  (System.nanoTime() - startTime));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void update() {
        player.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g; // cast g to a 2D graphics graph
        g2.setBackground(Color.GREEN);
        player.draw(g2);
        

        g2.dispose(); // clears memory
    }
}