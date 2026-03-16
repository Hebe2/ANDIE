/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cosc202.andie;

import java.awt.image.BufferedImage;

/**
 *
 * @author hebebebebe
 */
public class SwapGandB implements ImageOperation{
    
    @Override
    public BufferedImage apply(BufferedImage input) {
        int w = input.getWidth();
        int h = input.getHeight();

        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                int pixel = input.getRGB(x, y);
                int alpha = (pixel >> 24) & 0xff;
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = pixel & 0xff;

                int b = blue;
                //int r = red;
                int g = green;
                green = b;
                //red = b;
                blue = g;

                pixel = (alpha << 24) | (red << 16) | (green << 8) | blue;
                input.setRGB(x, y, pixel);
            }
        }

        return input;

    }
}
