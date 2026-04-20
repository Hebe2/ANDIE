/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cosc202.andie;
import java.awt.image.BufferedImage;
import java.awt.*; 
import java.io.Serializable; 

/**
 *
 * @author manuella
 */
public class ColourTinter implements ImageOperation, Serializable {
    private Color tintColor; 
    private float strength; 
    
    ColourTinter(Color tintColor, float strength){
    this.tintColor = tintColor; 
    this.strength = Math.max(0.0f, Math.min(1.0f, strength)); 
   
    
    }
    
    public BufferedImage apply(BufferedImage input){
        int width = input.getWidth();
        int height = input.getHeight(); 
        
        BufferedImage output = new BufferedImage(width, height, input.getType()); 
        
        int tintRed = tintColor.getRed();
        int tintGreen = tintColor.getGreen();
        int tintBlue = tintColor.getBlue();
        
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int argb = input.getRGB(x, y);
                int alpha = (argb >> 24) & 0xFF;
                int r = (argb >> 16) & 0xFF;
                int g = (argb >> 8) & 0xFF;
                int b = argb & 0xFF;
                
          int newRed = Math.round((1 - strength) * r + strength * tintRed);
          int newGreen = Math.round((1 - strength) * g + strength * tintGreen); 
          int newBlue = Math.round((1 - strength) * b + strength * tintBlue); 
          
          int newArgb = (alpha << 24) | (newRed << 16) | (newGreen << 8) | newBlue;output.setRGB(x, y, newArgb);
          
          
                
            }
        }
        
     return output; 
     }
    
    
    
}
