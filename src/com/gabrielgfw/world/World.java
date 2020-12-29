package com.gabrielgfw.world;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class World {

    private Tile[] tiles;
    public static int WIDTH, HEIGHT;

    public World(String path) {

        // # Mapping pixels from map.png:
        // Basically, we're going to pass through every pixel
        // setting a sprite based on the currently color of the pixel.
        try {
            BufferedImage map = ImageIO.read(getClass().getResource(path));

            // # Copying every pixel from the map to the array of pixels:
            int[] pixels = new int[map.getWidth() * map.getHeight()];
            WIDTH = map.getWidth();
            HEIGHT = map.getHeight();
            tiles = new Tile[map.getWidth() * map.getHeight()];
            map.getRGB(0, 0, map.getWidth(), map.getHeight(), pixels, 0, map.getWidth());

            // # Colour Hex:
            // Extract the color hex code (follow below instructions):
            // '0xFF' + 'hexCode'
            for(int xx = 0; xx < map.getWidth(); xx++) {
                for(int yy = 0; yy < map.getHeight(); yy++) {
                    int currentPixel = pixels[xx + (yy * map.getWidth())];

                    // # Black Color (Grass Tile):
                    if(currentPixel == 0xFF010101) {
                        tiles[xx + (yy * WIDTH)] = new FloorTile(xx * 16, yy * 16, Tile.TILE_GRASS);

                    // # White Color (Stone Wall):
                    } else if(currentPixel == 0xFFffffff) {
                        tiles[xx + (yy * WIDTH)] = new WallTile(xx * 16, yy * 16, Tile.TILE_WALL);

                    // # Player Spawn Location:
                    } else if(currentPixel == 0xFF0000ff) {
                        tiles[xx + (yy * WIDTH)] = new FloorTile(xx * 16, yy * 16, Tile.TILE_GRASS);

                    } else {
                        tiles[xx + (yy * WIDTH)] = new FloorTile(xx * 16, yy * 16, Tile.TILE_GRASS);

                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void render(Graphics g) {
        for(int xx = 0; xx < WIDTH; xx++) {
            for(int yy = 0; yy < HEIGHT; yy++) {
                Tile tile = tiles[xx + (yy * WIDTH)];
                tile.render(g);
            }
        }
    }

}
