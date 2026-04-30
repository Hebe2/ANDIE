/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cosc202.andie;

import java.awt.image.BufferedImage;

/**
 * Applies a Gaussian Blue filter to an image.
 *
 * This filter works by generating a Gaussian kernel based on the specified
 * radius, then performs a convolution over the image. Each pixel is replaced
 * with a weighted average of its neighboring pixels.
 *
 * @author shika747
 */
public class GaussianFilter implements ImageOperation, java.io.Serializable {

    /**
     * The radius of the Gaussian kernel. Determines the strength and size of
     * the blur effect.
     */
    private int radius;

    /**
     * Constructs Gaussian Filter with a specified radius.
     *
     * @param radius - the radius of the kernel. Must be positive numbers.
     */
    GaussianFilter(int radius) {
        this.radius = radius;
    }

    //default constructor
    GaussianFilter() {
        radius = 1;
    }

    /**
     * Applies Gaussian blur to input image.
     *
     * A Gaussian kernel is generated and normalized. The filter is applied
     * using convolution, where each pixel is recalculated as a weight sum of
     * its neighbors.
     *
     * @param input - image to be blurred
     * @return - image containing blurred result
     */
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

        float[][] kernel = new float[size][size];
        double sum = 0;

        //gaussian kernel
        for (int ky = -radius; ky <= radius; ky++) {
            for (int kx = -radius; kx <= radius; kx++) {
                double exponent = -((kx * kx) + (ky * ky)) / (2 * (sigma * sigma));
                double value = Math.exp(exponent);

                kernel[ky + radius][kx + radius] = (float) value;
                sum += value;
            }
        }

        //normalize kernel
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                kernel[y][x] = (float) (kernel[y][x] / sum);
            }
        }

        //Apply gaussian blur via convolution helper (no offset needed)
        ConvolutionHelper helper = new ConvolutionHelper();

        return helper.apply(input, kernel, 0);
    }
}