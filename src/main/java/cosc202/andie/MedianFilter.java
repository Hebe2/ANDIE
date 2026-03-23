/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cosc202.andie;
import java.awt.image.*;
import java.util.*;
/**
 *
 * @author parma754
 */
public class MedianFilter implements ImageOperation, java.io.Serializable {

    private int radius;

    MedianFilter(int radius) {
        this.radius = radius;
    }

    MedianFilter() {
        this(1);
    }

    public BufferedImage apply(BufferedImage input) {
    int width = input.getWidth();
    int height = input.getHeight();
    BufferedImage output = new BufferedImage(input.getColorModel(), input.copyData(null), input.isAlphaPremultiplied(), null);

    for (int y = 0; y < height; y++) {
        for (int x = 0; x < width; x++) {
          
            List<Integer> rValues = new ArrayList<>();
            List<Integer> gValues = new ArrayList<>();
            List<Integer> bValues = new ArrayList<>();
            List<Integer> aValues = new ArrayList<>();

            for (int ky = -radius; ky <= radius; ky++) {
                for (int kx = -radius; kx <= radius; kx++) {
                    int nx = Math.max(0, Math.min(x + kx, width - 1));
                    int ny = Math.max(0, Math.min(y + ky, height - 1));
                    int argb = input.getRGB(nx, ny);
                    int a = (argb >> 24) & 0xFF;
                    rValues.add((argb >> 16) & 0xFF);
                    gValues.add((argb >> 8) & 0xFF);
                    bValues.add(argb & 0xFF);
                    aValues.add((argb >> 24) & 0xFF);
                }
            }
        
            Collections.sort(rValues);
            Collections.sort(gValues);
            Collections.sort(bValues);
            Collections.sort(aValues);

           
            int mid = rValues.size() / 2;
            int argb = (aValues.get(mid) << 24) | (rValues.get(mid) << 16) | (gValues.get(mid) << 8) | bValues.get(mid);
            output.setRGB(x, y, argb);
        }
    }
    return output;
}
        
}
