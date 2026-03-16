/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cosc202.andie;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/**
 *
 * @author hebebebebe
 */
public class ImageRotation180 implements ImageOperation{
    public BufferedImage apply(BufferedImage input) {
        
        
        int width = input.getWidth();
        int height = input.getHeight();

       
        
        BufferedImage output = new BufferedImage(width, height, input.getType());
        
        Graphics2D g2d = output.createGraphics();
        
        
        double angle = Math.toRadians(180);
        AffineTransform transform = new AffineTransform();
        transform.rotate(angle, width/2, height/2);

        //transform.translate(width, height);
  
        g2d.setTransform(transform);
        g2d.drawImage(input, 0, 0, null);
        g2d.dispose();
        return output;
    }
}
