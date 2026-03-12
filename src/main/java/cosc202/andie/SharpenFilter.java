/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cosc202.andie;
import java.awt.image.*;
import java.util.*;
/**
 *
 * @author hebebebebe
 */
public class SharpenFilter implements ImageOperation, java.io.Serializable {
    SharpenFilter(){
       
    }
    
    public BufferedImage apply (BufferedImage input){
        //values for kernel as an array
        float [] array = {  0  ,  -1/2.0f ,  0  , 
                          -1/2.0f  ,  3  ,  -1/2.0f,
                            0 , -1/2.0f , 0  };
        
        //3x3 kernel from teh array
        Kernel kernel = new Kernel(3,3,array);
        
        //apply this as convolution
        ConvolveOp convOp = new ConvolveOp(kernel);
        BufferedImage output = new BufferedImage(input.getColorModel(), input.copyData(null), input.isAlphaPremultiplied(), null);
        convOp.filter(input,output);
        
        return output;
    }
    
    
}
