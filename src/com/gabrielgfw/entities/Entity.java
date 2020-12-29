package com.gabrielgfw.entities;

import com.gabrielgfw.main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {

    public static BufferedImage ITEM_APPLE_EN = Game.spritesheet.getSprite(112, 0, 16, 16);
    public static BufferedImage ITEM_FIRE_GEM_EN = Game.spritesheet.getSprite(128, 0, 16, 16);


    protected double x;
    protected double y;
    protected int width;
    protected int height;

    private BufferedImage sprite;

    public Entity(int x, int y, int width, int height, BufferedImage sprite) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.sprite = sprite;
    }



    public int getX() {
        return (int)this.x;
    }

    public int getY() {
        return (int)this.y;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void tick() {

    }

    public void render(Graphics g) {
        g.drawImage(sprite, this.getX(), this.getY(),null);
    }
}
