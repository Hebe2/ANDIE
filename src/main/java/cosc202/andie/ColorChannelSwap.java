/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cosc202.andie;

import java.awt.image.BufferedImage;

/**
 *
 * @author shika747
 */
public class ColorChannelSwap implements ImageOperation {

    private String order;

    public ColorChannelSwap(String order) {
        this.order = order;
    }

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

                int r = red;
                int b = blue;
                int g = green;

                if (order.equals("RGB")) {
                    green = b;
                    blue = g;
                } else if (order.equals("GRB")) {
                    red = g;
                    green = r;
                } else if (order.equals("GBR")) {
                    red = g;
                    green = b;
                    blue = r;
                } else if (order.equals("BRG")) {
                    red = b;
                    green = r;
                    blue = g;
                } else if (order.equals("BGR")) {
                    red = b;
                    blue = r;
                }
                pixel = (alpha << 24) | (red << 16) | (green << 8) | blue;
                input.setRGB(x, y, pixel);
            }

        }
        return input; 
    }
}
