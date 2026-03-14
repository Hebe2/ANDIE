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
import java.io.File;
import javax.imageio.ImageIO;

public class ImageRotation {
//  
//BufferedImage inputImage = ImageIO.read(inputFile);
//double rotationAngle = Math.toRadians(90);

//AffineTransform transform = new AffineTransform();
//transform.rotate(rotationAngle, newWidth/2, newHeight/2);
//transform.translate((newWidth - width)/2), (newHeight - height)/2);
//    
public BufferedImage apply(BufferedImage input) {
   int width = input.getWidth();
int height = input.getHeight();

int newWidth = (int) Math.abs(width * Math.cos(rotationAngle)) + (int) Math.abs(height * Math.sin(rotationAngle));

int newHeight = (int) Math.abs(height * Math.cos(rotationAngle)) + (int) Math.abs(width * Math.sin(rotationAngle));

BufferedImage outputImage = new BufferedImage(newWidth, newHeight, input.getType()); 
    Graphics2D g2d = outputImage.createGraphics();
g2d.setTransform(transform);
g2d.drawImage(input, 0, 0, null);
g2d.dispose();
}



}
