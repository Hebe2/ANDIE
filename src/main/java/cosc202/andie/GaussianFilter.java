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
public class GaussianFilter implements ImageOperation, java.io.Serializable {

    private int radius;

    GaussianFilter(int radius) {
        this.radius = radius;
    }

    //default constructor
    GaussianFilter() {
        radius = 1;
    }

    @Override
    public BufferedImage apply(BufferedImage input) {
        int width = input.getWidth();
        int height = input.getHeight();

        BufferedImage output = new BufferedImage(
                input.getColorModel(),
                input.copyData(null),
                input.isAlphaPremultiplied(), null);

        int size = 2 * radius + 1;
        double sigma = radius / 3.0;
        if (sigma == 0) {
            sigma = 1;
        }

        double[][] kernel = new double[size][size];
        double sum = 0;

        //gaussian kernel
        for (int ky = -radius; ky <= radius; ky++) {
            for (int kx = -radius; kx <= radius; kx++) {
                double exponent = -((kx * kx) + (ky * ky)) / (2 * (sigma * sigma));
                double value = Math.exp(exponent);

                kernel[ky + radius][kx + radius] = value;
                sum += value;
            }
        }

        //normalize kernel
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                kernel[y][x] = kernel[y][x] / sum;
            }
        }

        //gaussian blur application
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                double r = 0;
                double g = 0;
                double b = 0;

                for (int ky = -radius; ky <= radius; ky++) {
                    for (int kx = -radius; kx <= radius; kx++) {
                        int nx = Math.max(0, Math.min(x + kx, width - 1));
                        int ny = Math.max(0, Math.min(y + ky, height - 1));

                        int argb = input.getRGB(nx, ny);

                        int red = (argb >> 16) & 0xFF;
                        int green = (argb >> 8) & 0xFF;
                        int blue = argb & 0xFF;
                        double weight = kernel[ky + radius][kx + radius];

                        r += red * weight;
                        g += green * weight;
                        b += blue * weight;

                    }

                }
                
                int newRed = Math.min(255, Math.max(0, (int) Math.round(r)));
                int newGreen = Math.min(255, Math.max(0, (int) Math.round(g)));
                int newBlue = Math.min(255, Math.max(0, (int) Math.round(b)));

                int newRGB = (0xFF << 24) | (newRed << 16) | (newGreen << 8) | newBlue;
                output.setRGB(x, y, newRGB);
                
            }

        }
        return output;
    }

}


