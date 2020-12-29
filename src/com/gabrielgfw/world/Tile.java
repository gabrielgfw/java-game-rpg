package com.gabrielgfw.world;

import com.gabrielgfw.main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {

    public static BufferedImage TILE_GRASS = Game.spritesheet.getSprite(80, 0, 16, 16);
    public static BufferedImage TILE_WALL = Game.spritesheet.getSprite(96, 0, 16, 16);

    private BufferedImage sprite;
    private int x, y;

    public Tile(int x, int y, BufferedImage sprite) {
        this.x = x;
        this.y = y;
        this.sprite = sprite;
    }

    public void render(Graphics g) {
        g.drawImage(sprite, x, y, null);
    }

}
