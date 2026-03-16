/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cosc202.andie;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.InputMismatchException;
import java.util.Scanner;
/**
 *
 * @author timnanevo
 */
public class ImageThresholding implements ImageOperation{
    private int threshold;
 
    public ImageThresholding(int threshold){
       this.threshold = threshold;
    }
    
     /**
     * Create a menu containing the list of Colour actions.
     * @param input 
     *
     * @return 
     */
    /**
     * <p>
     * Apply threshold conversion to an image.
     * </p>
     *
     * @param input The image to be converted with threshold
     * @return BufferedImage of input with threshold color changes applied
     */
    @Override
    public BufferedImage apply(BufferedImage input) {
        for (int y = 0; y < input.getHeight(); ++y) {
            for (int x = 0; x < input.getWidth(); ++x) {
                int argb = input.getRGB(x, y);
                int alpha = (argb >> 24) & 0xff; // extract alpha
                int r = (argb >> 16) & 0xff;
                int g = (argb >> 8) & 0xff;
                int b = argb & 0xff;
                int intensity = (r+g+b)/3;
                int newColor;
                if (intensity >= threshold) {
                    newColor = (alpha << 24) | (255 << 16) | (255 << 8) | 255; // white if above or equal to threshold
                } else {
                    newColor = (alpha << 24) | (0 << 16) | (0 << 8) | 0; // black if below threshold
                }
                input.setRGB(x, y, newColor);
            }
        }

        return input;
    }
}
