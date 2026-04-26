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
    
    @Override
    public BufferedImage apply(BufferedImage input) {
        float[][] kernel = {
            {0f, -1 / 2.0f, 0f},
            {-1 / 2.0f, 3f, -1 / 2.0f},
            {0, -1 / 2.0f, 0}
        };

        //Apply sharpen kernel via convolution helper (no offset needed)
        ConvolutionHelper helper = new ConvolutionHelper();
        return helper.apply(input, kernel, 0);
    }

}
