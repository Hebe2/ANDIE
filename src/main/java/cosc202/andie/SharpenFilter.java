/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cosc202.andie;

import java.awt.image.*;
import java.util.*;

/**
 * <p>
 * ImageOperation to apply a Sharpen filter.
 * </p>
 *
 * <p>
 * A sharpen filter enhances the edges and fine details of an image by
 * emphasising differences between neighbouring pixels, and can be implemented
 * by a convolution.
 * </p>
 *
 * @author hebebebebe
 */
public class SharpenFilter implements ImageOperation, java.io.Serializable {

    /**
     * <p>
     * Construct a Sharpen filter with each click.
     * </p>
     *
     */
    SharpenFilter() {
    }

    /**
     * <p>
     * Apply a Sharpen filter to an image.
     * </p>
     *
     * <p>
     * As with many filters, the Sharpen filter is implemented via convolution.
     * It enhances edges and fine details by emphasising differences between
     * neighbouring pixels using a fixed 3x3 kernel.
     * </p>
     *
     * @param input The image to apply the Sharpen filter to.
     * @return The resulting (sharpen) image.
     */
    public BufferedImage apply(BufferedImage input) {
        int width = input.getWidth();
        int height = input.getHeight();

        BufferedImage output = new BufferedImage(
                input.getColorModel(),
                input.copyData(null),
                input.isAlphaPremultiplied(), null);
        
        float[][] kernel = {
            {0f, -1 / 2.0f, 0f},
            {-1 / 2.0f, 3f, -1 / 2.0f},
            {0, -1 / 2.0f, 0}
        };
        int radius = 1;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                float a = 0, r = 0, g = 0, b = 0;

                for (int ky = -radius; ky <= radius; ky++) {
                    for (int kx = -radius; kx <= radius; kx++) {
                        int nx = Math.max(0, Math.min(x + kx, width - 1));
                        int ny = Math.max(0, Math.min(y + ky, height - 1));

                        int argb = input.getRGB(nx, ny);

                        int alpha = (argb >> 24) & 0xFF;
                        int red = (argb >> 16) & 0xFF;
                        int green = (argb >> 8) & 0xFF;
                        int blue = argb & 0xFF;
                        
                        float weight = kernel[ky+radius][kx+radius];
                        
                        a += alpha * weight;
                        r += red * weight;
                        g += green * weight;
                        b += blue * weight;

                    }

                }

                int newAlpha = (int) Math.min(255, Math.max(0, Math.round(a)));
                int newRed = (int) Math.min(255, Math.max(0, Math.round(r)));
                int newGreen = (int) Math.min(255, Math.max(0, Math.round(g)));
                int newBlue = (int) Math.min(255, Math.max(0, Math.round(b)));

                int newRGB = (newAlpha << 24) | (newRed << 16) | (newGreen << 8) | newBlue;
                output.setRGB(x, y, newRGB);
            }
        }
        return output;
    }

}
