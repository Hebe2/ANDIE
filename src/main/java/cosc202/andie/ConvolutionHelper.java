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
public class ConvolutionHelper {

    public BufferedImage apply(BufferedImage input, float[][] kernel, int offset) {
        int width = input.getWidth();
        int height = input.getHeight();
        int radius = kernel.length / 2;

        BufferedImage output = new BufferedImage(
                input.getColorModel(),
                input.copyData(null),
                input.isAlphaPremultiplied(), null);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {

                double a = 0, r = 0, g = 0, b = 0;

                for (int ky = -radius; ky <= radius; ky++) {
                    for (int kx = -radius; kx <= radius; kx++) {
                        int nx = Math.max(0, Math.min(x + kx, width - 1));
                        int ny = Math.max(0, Math.min(y + ky, height - 1));

                        int argb = input.getRGB(nx, ny);

                        int alpha = (argb >> 24) & 0xFF;
                        int red = (argb >> 16) & 0xFF;
                        int green = (argb >> 8) & 0xFF;
                        int blue = argb & 0xFF;
                        double weight = kernel[ky + radius][kx + radius];

                        a += alpha * weight;
                        r += red * weight;
                        g += green * weight;
                        b += blue * weight;

                    }
                }

                int newAlpha = (int) Math.min(255, Math.max(0, Math.round(a)));
                int newRed = (int) Math.min(255, Math.max(0, Math.round(r + offset)));
                int newGreen = (int) Math.min(255, Math.max(0, Math.round(g + offset)));
                int newBlue = (int) Math.min(255, Math.max(0, Math.round(b + offset)));

                int newRGB = (newAlpha << 24) | (newRed << 16) | (newGreen << 8) | newBlue;
                output.setRGB(x, y, newRGB);

            }
        }

        return output;
    }

}
