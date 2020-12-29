package com.gabrielgfw.main;

import com.gabrielgfw.entities.Entity;
import com.gabrielgfw.entities.Player;
import com.gabrielgfw.graphics.Spritesheet;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Game extends Canvas implements Runnable {

    public static JFrame frame;
    private Thread thread;
    private BufferedImage image;

    private boolean isRunning = true;
    private final int WIDTH = 240;
    private final int HEIGHT = 160;
    private final int SCALE = 5;

    public List<Entity> entities;
    public Spritesheet spritesheet;


    public Game() {

        // # Window Resolution:
        setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        initFrame();

        // # Objects Initializer:
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        entities = new ArrayList<Entity>();
        spritesheet = new Spritesheet("/spritesheet.png");

        // # Creating Player;
        Player player = new Player(0, 0 , 16, 16, spritesheet.getSprite(0, 0, 16, 16));
        entities.add(player);
    }

    public void initFrame() {
        frame = new JFrame("Game Test");
        frame.add(this);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public synchronized void start() {
        thread = new Thread(this);
        isRunning = true;
        thread.start();
    }

    public synchronized void stop() {
        isRunning = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }

    public void tick() {

        // # Calling the logic (tick) for all entities:
        for(int i = 0; i < entities.size(); i++) {
            Entity e = entities.get(i);
            e.tick();
        }
    }

    public void render() {
        BufferStrategy bs = this.getBufferStrategy();

        // # Otimize render;
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        // # Graphics render;
        Graphics g = image.getGraphics();
        g.setColor(new Color(0, 0, 0));
        g.fillRect(0, 0, WIDTH, HEIGHT);

        // # Rendering all entities:
        for(int i = 0; i < entities.size(); i++) {
            Entity e = entities.get(i);
            e.render(g);
        }

        // # Player render;
        Graphics2D g2 = (Graphics2D) g;

        // # Darkness Overlay;
        // g2.rotate(Math.toRadians(-45), 90+8, 90+8);
        // g2.setColor(new Color(0,0,0,100));
        // g2.fillRect(0, 0, WIDTH, HEIGHT);

        g.dispose();
        g = bs.getDrawGraphics();
        g.drawImage(image, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
        bs.show();
    }

    public void run() {

        // # Setting tick rate;
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;

        // # FPS display;
        int frames = 0;
        double timer = System.currentTimeMillis();

        while (isRunning) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            if (delta >= 1) {
                tick();
                render();
                frames++;
                delta--;
            }

            if (System.currentTimeMillis() - timer >= 1000) {
                System.out.println("FPS: " + frames);
                frames = 0;
                timer += 1000;
            }
        }
        stop();
    }
}
