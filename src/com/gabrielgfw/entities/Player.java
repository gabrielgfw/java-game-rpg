package com.gabrielgfw.entities;

import com.gabrielgfw.main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity {

    public boolean right, up, left, down;
    public int dir = 0;
    public int rightDir = 0;
    public int leftDir = 1;
    public int upDir = 2;
    public int downDir = 3;
    public double speed = 1;

    private int frames = 0;
    private BufferedImage[] rightPlayer;
    private BufferedImage[] leftPlayer;
    private BufferedImage[] upPlayer;
    private BufferedImage[] downPlayer;


    public Player(int x, int y, int width, int height, BufferedImage sprite) {
        super(x, y, width, height, sprite);

        rightPlayer = new BufferedImage[3];
        leftPlayer = new BufferedImage[3];
        upPlayer = new BufferedImage[3];
        downPlayer = new BufferedImage[3];

        for(int i = 0; i < 3; i++) {
            rightPlayer[i] = Game.spritesheet.getSprite(48, (i*16), 16, 16);
            leftPlayer[i] = Game.spritesheet.getSprite(32, (i*16), 16, 16);
            upPlayer[i] = Game.spritesheet.getSprite(16, (i*16), 16, 16);
            downPlayer[i] = Game.spritesheet.getSprite(0, (i*16), 16, 16);

        }
    }

    public void tick() {
        if(right) {
            dir = rightDir;
            x += speed;
        } else if(left) {
            dir = leftDir;
            x -= speed;
        }

        if(up) {
            dir = upDir;
            y -= speed;
        } else if(down) {
            dir = downDir;
            y += speed;
        }
    }

    public void render(Graphics g) {
        if(dir == rightDir) {
            g.drawImage(rightPlayer[0], this.getX(), this.getY(), null);
        } else if(dir == leftDir) {
            g.drawImage(leftPlayer[0], this.getX(), this.getY(), null);
        }

        if(dir == upDir) {
            g.drawImage(upPlayer[0], this.getX(), this.getY(), null);
        } else if(dir == downDir) {
            g.drawImage(downPlayer[0], this.getX(), this.getY(), null);
        }

    }
}
