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
public class ImageRotation90ACW implements ImageOperation{
    public BufferedImage apply(BufferedImage input) {
        
        
        int width = input.getWidth();
        int height = input.getHeight();

        int newWidth = height;
        int newHeight = width;
        
        BufferedImage output = new BufferedImage(newWidth, newHeight, input.getType());
        
        Graphics2D g2d = output.createGraphics();
        
        AffineTransform transform = new AffineTransform();
        double angle = Math.toRadians(-90);
        transform.rotate(angle, newWidth/2, newHeight / 2);

        transform.translate((newWidth - width) / 2, (newHeight - height) / 2);
  
        g2d.setTransform(transform);
        g2d.drawImage(input, 0, 0, null);
        g2d.dispose();
        return output;
    }
}
