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
        float [] array = {  0  ,  -1/2.0f ,  0  , 
                          -1/2.0f  ,  3  ,  -1/2.0f,
                            0 , -1/2.0f , 0  };
         
        Kernel kernel = new Kernel(3,3,array);
        
        ConvolveOp convOp = new ConvolveOp(kernel);
        BufferedImage output = new BufferedImage(input.getWidth(), input.getHeight(), input.getType());
        convOp.filter(input,output);
        
        return output;
    }
    
    
}
