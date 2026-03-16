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
    
    @Override
    public BufferedImage apply(BufferedImage input) {
        for (int y = 0; y < input.getHeight(); ++y) {
            for (int x = 0; x < input.getWidth(); ++x) {
                int rgb = input.getRGB(x, y);
                Color color = new Color(rgb);
                int r = color.getRed();
                int g = color.getGreen();
                int b = color.getBlue();
                int intensity = (r+g+b)/3;
                if (intensity >= threshold){
                    input.setRGB(x,y ,Color.WHITE.getRGB());
                }
                else{
                    input.setRGB(x,y,Color.BLACK.getRGB());
                }
            }
        }

        return input;
    }
}
