package cosc202.andie;

import java.awt.image.*;
import java.util.*;

/**
 * <p>
 * ImageOperation to apply a Mean (simple blur) filter.
 * </p>
 *
 * <p>
 * A Mean filter blurs an image by replacing each pixel by the average of the
 * pixels in a surrounding neighbourhood, and can be implemented by a
 * convolution.
 * </p>
 *
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA
 * 4.0</a>
 * </p>
 *
 * @see java.awt.image.ConvolveOp
 * @author Steven Mills
 * @version 1.0
 */
public class MeanFilter implements ImageOperation, java.io.Serializable {

    /**
     * The size of filter to apply. A radius of 1 is a 3x3 filter, a radius of 2
     * a 5x5 filter, and so forth.
     */
    private int radius;

    /**
     * <p>
     * Construct a Mean filter with the given size.
     * </p>
     *
     * <p>
     * The size of the filter is the 'radius' of the convolution kernel used. A
     * size of 1 is a 3x3 filter, 2 is 5x5, and so on. Larger filters give a
     * stronger blurring effect.
     * </p>
     *
     * @param radius The radius of the newly constructed MeanFilter
     */
    MeanFilter(int radius) {
        this.radius = radius;
    }

    /**
     * <p>
     * Construct a Mean filter with the default size.
     * </p
     * >
     * <p>
     * By default, a Mean filter has radius 1.
     * </p>
     *
     * @see MeanFilter(int)
     */
    @SuppressWarnings("unused")
    MeanFilter() {
        this(1);
    }

    /**
     * <p>
     * Apply a Mean filter to an image.
     * </p>
     *
     * <p>
     * As with many filters, the Mean filter is implemented via convolution. The
     * size of the convolution kernel is specified by the {@link radius}. Larger
     * radii lead to stronger blurring.
     * </p>
     *
     * @param input The image to apply the Mean filter to.
     * @return The resulting (blurred)) image.
     */
    @Override
    public BufferedImage apply(BufferedImage input) {
        int width = input.getWidth();
        int height = input.getHeight();

        BufferedImage output = new BufferedImage(
                input.getColorModel(),
                input.copyData(null),
                input.isAlphaPremultiplied(), null);

        int size = (2 * radius + 1);
        float weight = 1.0f / (size * size);

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
