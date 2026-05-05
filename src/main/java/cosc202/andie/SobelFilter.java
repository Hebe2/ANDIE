/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cosc202.andie;

import java.awt.image.BufferedImage;

/**
 *
 * @author shika747
 */
public class SobelFilter implements ImageOperation, java.io.Serializable {

    int type;

    public SobelFilter(int type) {
        this.type = type;

    }

    @Override
    public BufferedImage apply(BufferedImage input) {

        float[][] horizontalKernel = {
            {-1 / 2f, 0f, 1 / 2f},
            {-1f, 0f, 1f},
            {-1 / 2f, 0f, 1 / 2f}
        };

        float[][] verticalKernel = {
            {-1 / 2f, -1f, -1 / 2f},
            {0f, 0f, 0f},
            {1 / 2f, 1f, 1 / 2f}
        };

        //Horizontal and Vertical Sobel 
        ConvolutionHelper helper = new ConvolutionHelper();

        BufferedImage horizontal = helper.apply(input, horizontalKernel, 128);
        BufferedImage vertical = helper.apply(input, verticalKernel, 128);

        if (type == 0) {
            return horizontal;
        }

        if (type == 1) {
            return vertical;
        }

        BufferedImage output = new BufferedImage(
                input.getWidth(),
                input.getHeight(),
                BufferedImage.TYPE_INT_ARGB
        );

        for (int y = 0; y < input.getHeight(); y++) {
            for (int x = 0; x < input.getWidth(); x++) {
                int hRGB = horizontal.getRGB(x, y);
                int vRGB = vertical.getRGB(x, y);
                
                int hA = ((hRGB >> 24) & 0xFF);
                int hR = ((hRGB >> 16) & 0xFF) - 128;
                int hG = ((hRGB >> 8) & 0xFF) - 128;
                int hB = (hRGB & 0xFF) - 128;
                
                int vA = ((vRGB >> 24) & 0xFF);
                int vR = ((vRGB >> 16) & 0xFF) - 128;
                int vG = ((vRGB >> 8) & 0xFF) - 128;
                int vB = (vRGB & 0xFF) - 128;
                

                int r = magnitude(hR, vR);
                int g = magnitude(hG, vG);
                int b = magnitude(hB, vB);

                int a = (hRGB >> 24) & 0xFF;

                int rgb = (a << 24) | (r << 16) | (g << 8) | b;

                output.setRGB(x, y, rgb);
            }

        }
        return output;
    }

    /**
     * Helper method that calculates the combined value for the color channel
     */
    private int magnitude(int h, int v) {
        int value = (int) Math.sqrt((h * h) + (v * v));
        return Math.max(0, Math.min(255, value));
    }
}
