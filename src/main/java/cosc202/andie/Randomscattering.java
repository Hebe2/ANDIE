/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cosc202.andie;

import java.awt.image.BufferedImage;
import java.util.Random;

/**
 *
 * @author manuella
 */
public class Randomscattering implements ImageOperation, java.io.Serializable {

    private int radius;

    Randomscattering(int radius) {
        this.radius = radius;
    }

    Randomscattering() {
        this.radius = 5;
    }

    @Override
    public BufferedImage apply(BufferedImage input) {
        int width = input.getWidth();
        int height = input.getHeight();

        BufferedImage output = new BufferedImage(
                input.getColorModel(),
                input.copyData(null),
                input.isAlphaPremultiplied(),
                null
        );

        Random rand = new Random();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int dx = rand.nextInt(2 * radius + 1) - radius; 
                int dy = rand.nextInt(2 * radius + 1) - radius; 
                
                int nx = Math.max(0, Math.min(x + dx, width - 1)); 
                int ny = Math.max(0, Math.min(y + dy, height - 1)); 
                
                int rgb = input.getRGB(nx, ny);
                output.setRGB(x,y,rgb);
            }
        }
        return output;
    }

}
