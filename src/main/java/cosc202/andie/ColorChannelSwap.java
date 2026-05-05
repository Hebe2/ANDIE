/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cosc202.andie;

import java.awt.image.BufferedImage;
import java.io.Serializable;

/**
 *
 * @author shika747
 * @author habebebebe
 */
public class ColorChannelSwap implements ImageOperation, Serializable{
    /**
     * The target channel order, e.g. {@code "RGB"}, {@code "BGR"}, {@code "GRB"}, etc.
     * Must be one of the six permutations of R, G, and B.
     */

    
    /** The channel order, e.g. {@code "BGR"} or {@code "GRB"}. */
    private String order;
    
    /**
     * @param order the RGB permutation to apply (e.g. {@code "BGR"}, {@code "GRB"})
     */
    public ColorChannelSwap(String order) {
        this.order = order;
    }
    
    /**
     * Applies the channel swap to the image in place, preserving alpha.
     *
     * @param input the image to modify
     * @return the modified image
     */

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

                int newRed = red;
                int newGreen = green;
                int newBlue = blue;

                if (order.equals("RGB")) {
                    newRed = red;
                    newGreen = green;
                    newBlue = blue;
                } else if (order.equals("RBG")) {
                    newRed = red;
                    newGreen = blue;
                    newBlue = green;
                } else if (order.equals("GRB")) {
                    newRed = green;
                    newGreen = red;
                    newBlue = blue;

                } else if (order.equals("GBR")) {
                    newRed = blue;
                    newGreen = red;
                    newBlue = green;
                    
                    
                } else if (order.equals("BRG")) {
                    newRed = green;
                    newGreen = blue;
                    newBlue = red;                    
                    
                    
                } else if (order.equals("BGR")) {
                    newRed = blue;
                    newGreen = green;
                    newBlue = red;
                }

                pixel = (alpha << 24) | (newRed << 16) | (newGreen << 8) | newBlue;
                input.setRGB(x, y, pixel);
            }

        }
        return input;
    }
}
