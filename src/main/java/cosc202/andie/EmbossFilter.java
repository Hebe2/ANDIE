/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cosc202.andie;

import java.awt.image.BufferedImage;

/**
 *Applies an emboss filter to an image. 
 * Emboss filter highlights edges in an image by simulating a directional light source, 
 * changing the orientation of highlights and shadows in the image to create a 3D-like effect. 
 * 
 * @author shika747
 */
public class EmbossFilter implements ImageOperation, java.io.Serializable {

    private int kernelDirection;
    
    /**
     * Constructs an EmbossFilter with a specified direction.
     * @param kernelDirection - an integer from 0 to 7 representing emboss direction
     */
    EmbossFilter(int kernelDirection) {
        this.kernelDirection = kernelDirection;
    }
    
    /**
     * Applies directional emboss filter to the input image.
     * 
     * Method seleccts a convolution kernel based on the specified direction and applies it 
     * to the image. An offset is added so the result stays visible, instead of becoming too dark or 
     * too bright.
     * 
     * @param input - the image to process.
     * @return - an embossed image.
     */
    @Override
    public BufferedImage apply(BufferedImage input) {
        
//        System.out.println("Kernel direction: " + kernelDirection);
        
        float[][][] kernel = {
            
            //collection of 8 emboss directions
            
            //kernel 0
            {
                {0f, 0f, 0f},
                {1f, 0f, -1f},
                {0f, 0f, 0f}
            },
            //kernel 1
            {
                {1f, 0f, 0f},
                {0f, 0f, 0f},
                {0f, 0f, -1f}
            },
            //kernel 2
            {
                {0f, 1f, 0f},
                {0f, 0f, 0f},
                {0f, -1f, 0f}
            },
            //kernel 3
            {
                {0f, 0f, 1f},
                {0f, 0f, 0f},
                {-1f, 0f, 0f}
            },
            //kernel 4
            {
                {0f, 0f, 0f},
                {-1f, 0f, 1f},
                {0f, 0f, 0f}
            },
            //kernel 5
            {
                {-1f, 0f, 0f},
                {0f, 0f, 0f},
                {0f, 0f, 1f}
            },
            //kernel 6
            {
                {0f, -1f, 0f},
                {0f, 0f, 0f},
                {0f, 1f, 0f}
            },
            //kernel 7
            {
                {0f, 0f, -1f},
                {0f, 0f, 0f},
                {1f, 0f, 0f}
            },
        
        };

        ConvolutionHelper helper = new ConvolutionHelper();

        return helper.apply(input, kernel[kernelDirection], 128);
    }
}
