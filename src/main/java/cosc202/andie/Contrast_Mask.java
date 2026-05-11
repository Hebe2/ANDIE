/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cosc202.andie;

import java.awt.image.*;
import java.io.Serializable;

/**
 *<p>
 * ImageOperation that applies a contrast mask to an image  
 *</p>
 * 
 * <p>
 * This operation uses a combination of greyscale, image inversion, gaussian blur and soft light blend
 * to create the contrast mask. The strength of the mask and the size of the blur can be adjusted to change the mask. 
 * </p>
 * 
 * @author manuella
 */
public class Contrast_Mask implements ImageOperation, Serializable {

    private int radius;
    private double strength;

    Contrast_Mask(int radius, double strength) {
        this.radius = radius;
        this.strength = strength;   
    }

    Contrast_Mask() {
        this.radius = 1;
        this.strength = 0.9;
    }

    @Override
    public BufferedImage apply(BufferedImage input) {
        // deep copy 
        ColorModel cm = input.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = input.copyData(input.getRaster().createCompatibleWritableRaster());
        BufferedImage mask = new BufferedImage(cm, raster, isAlphaPremultiplied, null);
        // greyscale 
        mask = new ConvertToGrey().apply(mask);
        // inversion 
        mask = new ImageInversion().apply(mask);
        // gaussian blur 
        mask = new GaussianFilter(radius).apply(mask);
        // the making of  transparancy :p  
        int alpha = (int) Math.round(strength * 255);
        for (int y = 0; y < mask.getHeight(); y++) {
            for (int x = 0; x < mask.getWidth(); x++) {
                int argb = mask.getRGB(x, y);
                int r = (argb >> 16) & 0xFF;
                int g = (argb >> 8) & 0xFF;
                int b = argb & 0xFF;
                int newArgb = (alpha << 24) | (r << 16) | (g << 8) | b;
                mask.setRGB(x, y, newArgb);

            }
        }

        // ~B~L~E~N~D~ using soft light       
        for (int y = 0; y < input.getHeight(); y++) {
            for (int x = 0; x < input.getWidth(); x++) {
                int origArgb = input.getRGB(x, y);
                int maskArgb = mask.getRGB(x, y);

                int origA = (origArgb >> 24) & 0xFF;
                double origR = ((origArgb >> 16) & 0xFF) / 255.0;
                double origG = ((origArgb >> 8) & 0xFF) / 255.0;
                double origB = (origArgb & 0xFF) / 255.0;

                double maskR = ((maskArgb >> 16) & 0xFF) / 255.0;
                double maskG = ((maskArgb >> 8) & 0xFF) / 255.0;
                double maskB = (maskArgb & 0xFF) / 255.0;
                double weight = ((maskArgb >> 24) & 0xFF) / 255.0;

                int newR =  blendAndWeight(origR, maskR, weight);
                int newG = blendAndWeight(origG, maskG, weight);
                int newB = blendAndWeight(origB, maskB, weight);

                input.setRGB(x, y, (origA << 24) | (newR << 16) | (newG << 8) | newB);
            }
        }
        return input;

    }
// helper method

    private int blendAndWeight(double orig, double mask, double weight) {
        double blended;
        if (mask <= 0.5) {
            blended = orig - (1 - 2 * mask) * orig * (1 - orig);
        } else {
            double D = (orig <= 0.25) ? ((16 * orig - 12) * orig + 4) * orig : Math.sqrt(orig);
            blended = orig + (2 * mask - 1) * (D - orig);
        }
        double weighted = blended * weight + orig * (1 - weight);
        return (int) Math.round(weighted * 255);
    }

}
