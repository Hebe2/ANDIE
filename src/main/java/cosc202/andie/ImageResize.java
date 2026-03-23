/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cosc202.andie;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

/**
 *<p>
 * ImageOperation to resize an image 
 * </p>
 * 
 * <p>
 * This operation takes in an image, resizes it by a given scale factor and returns the modified image
 * </p>
 * 
 * @author timnanevo
 */
public class ImageResize implements ImageOperation{
    
    private int scaleFactor;
    
    /**
     * <p>
     * Create a new ImageResize operation.
     * </p>
     * 
     * @param scaleFactor the factor by which the image will be resized, given as a percent.   
     */
    public ImageResize(int scaleFactor){
        this.scaleFactor = scaleFactor;
    }
    
    
     /**
     * <p>
     * Applies the image resize operation 
     * </p>
     *
     * <p>
     * The conversion creates another image with the resized dimensions. The scale 
     * factor is given as a percentage where values greater than 100 will increase 
     * the image size while values smaller than 100 will shrink the image. AffineTransform
     * is used to translate the pixels to the new image size. 
     * </p>
     *
     * @param input The image to be resized
     * @return new BufferedImage as a newly resized image of input.
     */
    @Override
    public BufferedImage apply(BufferedImage input) {
      
        double scale = scaleFactor/100.0;
        int newWidth = (int)(input.getWidth() * scale);
        int newHeight = (int)(input.getHeight() * scale);
        
        BufferedImage output = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        
        AffineTransform transform = new AffineTransform();
        transform.scale(scale, scale);
        AffineTransformOp opTransform = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
        opTransform.filter(input, output);
  
        return output;
    }
    
}
