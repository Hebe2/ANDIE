/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cosc202.andie;

import java.awt.image.BufferedImage;

/**
 * <p>
 * Inverts the colours of an image.
 * </p>
 *
 * <p>
 * Image inversion works by subtracting each RGB channel value from 255,
 * effectively replacing each pixel with its complementary colour. The alpha
 * channel is left unchanged.
 * </p>
 *
 * @author leuhe253
 */
public class ImageInversion implements ImageOperation{

    /**
     * <p>
     * Apply colour inversion to an image.
     * </p>
     *
     * <p>
     * Each pixel's red, green, and blue channel values are subtracted from 255.
     * The operation is applied in-place and the result image is returned.
     * </p>
     *
     * @param input The image to apply inversion to.
     * @return The resulting (inverted) image.
     */
    @Override
    public BufferedImage apply(BufferedImage input) {
        int w = input.getWidth();
        int h = input.getHeight();
        
        for(int y = 0; y < h; y++){
            for(int x = 0; x < w; x++){
                int pixel = input.getRGB(x,y);
                int alpha = (pixel >> 24) & 0xff;
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = pixel & 0xff;
                
                red = 255 - red;
                green = 255 - green;
                blue = 255 - blue;
                
                pixel = (alpha <<24) | (red << 16) | (green <<8) | blue ;
                input.setRGB(x,y,pixel);
            }
    
        }
        return input;
    }
    
}
