/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cosc202.andie;
import java.awt.image.BufferedImage;
/**
 *
 * @author manuella
 */
public class BrightnessContrast implements ImageOperation, java.io.Serializable {
    private int brightness; 
    private int contrasts; 
    
    BrightnessContrast(int brightness, int contrasts){
    this.brightness = brightness; 
    this.contrasts = contrasts; 
       
    }
    
    BrightnessContrast(){
     this.brightness = 0; 
     this.contrasts = 0;   
    }
    
    @Override 
    public BufferedImage apply(BufferedImage input){
     int width = input.getWidth();
     int height = input.getHeight(); 
     
     BufferedImage output = new BufferedImage(
     input.getColorModel(),
                input.copyData(null),
                input.isAlphaPremultiplied(),
                null
     ); 
     
     for(int y = 0; y < height; y++){
      for(int x = 0; x < width; x++){
        int argb = input.getRGB(x, y); 
        
        int a = (argb >> 24) & 0xFF;
                int r = (argb >> 16) & 0xFF;
                int g = (argb >> 8) & 0xFF;
                int b = argb & 0xFF;

                r = clamp(applyFormula(r));
                g = clamp(applyFormula(g));
                b = clamp(applyFormula(b));

                int newArgb = (a << 24) | (r << 16) | (g << 8) | b;
                output.setRGB(x, y, newArgb);
        
      }
     }
     
     return output; 
     
    }
    private int applyFormula(int v) {
        double result = (1 + contrasts / 100.0) * (v - 127.5) + 127.5 * (1 + brightness / 100.0);
        return (int) Math.round(result);
    }
     private int clamp(int value) {
        return Math.max(0, Math.min(255, value));
    }
    
}
