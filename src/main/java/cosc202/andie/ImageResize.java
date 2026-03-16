/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cosc202.andie;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
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
        double scale = scaleFactor/100.0;
        int newWidth = (int)(input.getWidth() * scale);
        int newHeight = (int)(input.getHeight() * scale);
        
        BufferedImage output = new BufferedImage(newWidth, newHeight, input.getType());
        
        AffineTransform transform = new AffineTransform();
        transform.scale(scale, scale);
        AffineTransformOp opTransform = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
        opTransform.filter(input, output);
  
       
        return output;
    }
    
}
