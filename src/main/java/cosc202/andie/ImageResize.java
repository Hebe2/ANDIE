/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cosc202.andie;

import java.awt.image.BufferedImage;

/**
 *
 * @author timnanevo
 */
public class ImageResize implements ImageOperation{
    
    private int scaleFactor;
    
    public ImageResize(int scaleFactor){
        this.scaleFactor = scaleFactor;
    }
    //scaling by percent
    //calculate new width and height by multiplying by percent
    @Override
    public BufferedImage apply(BufferedImage input) {
        int scale = scaleFactor/100;
        
        
        
        
        
    }
    
    
    
    
}
