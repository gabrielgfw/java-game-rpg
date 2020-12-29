package com.gabrielgfw.entities;

import com.gabrielgfw.main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity {

    // # Movement variables:
    public boolean right, up, left, down;
    public int dir = 0;
    public int rightDir = 0;
    public int leftDir = 1;
    public int upDir = 2;
    public int downDir = 3;
    public double speed = 1;

    // # Sprites animation variables:
    private int framesSprite = 0;
    private int maxFramesSprite = 8;
    private int indexSprite;
    private int maxIndexSprite = 2;
    private boolean moved;

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
        moved = false;

        // # Player's Movement:
        if(right) {
            moved = true;
            dir = rightDir;
            x += speed;

        } else if(left) {
            moved = true;
            dir = leftDir;
            x -= speed;
        }

        if(up) {
            moved = true;
            dir = upDir;
            y -= speed;

        } else if(down) {
            moved = true;
            dir = downDir;
            y += speed;
        }

        // # Animation Sprite Cycle:
        if(moved) {
            framesSprite++;
            if(framesSprite == maxFramesSprite) {
                framesSprite = 0;
                indexSprite++;
                if(indexSprite > maxIndexSprite) {
                    indexSprite = 0;
                }
            }
        }
    }

    public void render(Graphics g) {

        // # Idle checking:
        if(!moved) {
            if(dir == rightDir) {
                g.drawImage(rightPlayer[0], this.getX(), this.getY(), null);
            }
            if(dir == leftDir) {
                g.drawImage(leftPlayer[0], this.getX(), this.getY(), null);
            }
            if(dir == upDir) {
                g.drawImage(upPlayer[0], this.getX(), this.getY(), null);
            }
            if(dir == downDir) {
                g.drawImage(downPlayer[0], this.getX(), this.getY(), null);
            }

        } else {
            // # Right Animation Sprites:
            if (dir == rightDir) {
                g.drawImage(rightPlayer[indexSprite], this.getX(), this.getY(), null);

                // # Left Animation Sprites:
            } else if (dir == leftDir) {
                g.drawImage(leftPlayer[indexSprite], this.getX(), this.getY(), null);
            }

            // # Up Animation Sprites:
            if (dir == upDir) {
                g.drawImage(upPlayer[indexSprite], this.getX(), this.getY(), null);

                // # Left Animation Sprites:
            } else if (dir == downDir) {
                g.drawImage(downPlayer[indexSprite], this.getX(), this.getY(), null);
            }
        }



    }
}
