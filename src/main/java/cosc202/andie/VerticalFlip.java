/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cosc202.andie;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

/**
 *Flips an image vertically
 * 
 * @author shika747
 */

import cosc202.andie.ImageOperation;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class VerticalFlip implements ImageOperation, java.io.Serializable {
    /**
     * Applies vertical flip to image
     * @param input - image to be flipped
     * @return a new vertically flipped image
     */

    @Override
    public BufferedImage apply(BufferedImage input) {
        AffineTransform transform = new AffineTransform();

        //flip vertically 
        transform.scale(1, -1);

        //move image back 
        transform.translate(0, -input.getHeight());

        AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        BufferedImage output = op.filter(input, null);

        
        return output;
    }
}