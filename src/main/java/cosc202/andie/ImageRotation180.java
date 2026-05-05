/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cosc202.andie;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.Serializable;

/**
 * <p>
 * Rotates an image 180 degrees about its centre.
 * </p>
 *
 * <p>
 * The rotation is performed using an {@link AffineTransform} applied with a
 * {@link Graphics2D} context. The output image has the same dimensions and
 * type as the input.
 * </p>
 * 
 * @author leuhe253
 */
public class ImageRotation180 implements ImageOperation, Serializable{
    
    /**
     * <p>
     * Apply a 180 degree rotation to an image.
     * </p>
     *
     * <p>
     * The image is rotated at around its centre point, so the result image will
     * appears flipped both horizontally and vertically.
     * </p>
     *
     * @param input The image to rotate.
     * @return The resulting (rotated 180°) image.
     */
    public BufferedImage apply(BufferedImage input) {
        
        int width = input.getWidth();
        int height = input.getHeight();

       
        
        BufferedImage output = new BufferedImage(width, height, input.getType());
        
        Graphics2D g2d = output.createGraphics();
        
        
        double angle = Math.toRadians(180);
        AffineTransform transform = new AffineTransform();
        transform.rotate(angle, width/2, height/2);
  
        g2d.setTransform(transform);
        g2d.drawImage(input, 0, 0, null);
        g2d.dispose();
        return output;
    }
}
