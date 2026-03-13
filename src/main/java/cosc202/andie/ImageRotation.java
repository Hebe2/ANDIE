/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cosc202.andie;

/**
 *
 * @author hebebebebe
 */

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;

public class ImageRotation {
    public BufferedImage rotateImage(BufferedImage image, double degrees){
            int width = image.getWidth();
            int height = image.getHeight();
            
            BufferedImage rotated = new BufferedImage(width, height, image.getType());
            
            Graphics2D g2d = rotated.createGraphics();
            
            AffineTransform transform = new AffineTransform();
            transform.rotate(Math.toRadians(degrees),width/2.0,height/2.0);
            
            g2d.setTransform(transform);
            g2d.drawImage(image,0,0, null);
            g2d.dispose();
            
            return rotated;
    }
}
