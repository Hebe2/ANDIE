/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cosc202.andie;

/**
 *
 * @author hebebebebe
 */
//import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
//import java.awt.geom.AffineTransform;

public class ImageRotation90clockwise implements ImageOperation{

//    public BufferedImage apply(BufferedImage input) {
//        double rotationAngle = Math.toRadians(90);
//
//        int width = input.getWidth();
//        int height = input.getHeight();
//        
//        BufferedImage output = new BufferedImage(newWidth, newHeight, input.getType());
//        AffineTransform transform = new AffineTransform();
//        transform.rotate(rotationAngle, newWidth / 2, newHeight / 2);
//
//        transform.translate((newWidth - width) / 2, (newHeight - height) / 2);
//
//        Graphics2D g2d = output.createGraphics();
//        g2d.setTransform(transform);
//        g2d.drawImage(input, 0, 0, null);
//        g2d.dispose();
//        return output;
//    }
     private final ImageRotation90clockwise rotator = new ImageRotation90clockwise();
    
    public ImageRotation90clockwise() {
       
    }
    
    @Override
    public BufferedImage apply(BufferedImage input) {
        return rotator.apply(input);  
    }

}
