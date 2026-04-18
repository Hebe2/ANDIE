/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cosc202.andie;
import java.awt.image.*; 
import java.io.Serializable;


/**
 *
 * @author manuella
 */
public class Contrast_Mask implements ImageOperation, Serializable {
    private int radius; 
    private double strength; 
    
    Contrast_Mask(int radius, double strength)
    {
     this.radius = radius; 
     this.strength = strength; 
    } 
    
    Contrast_Mask(){
     this.radius = 1; 
     this.strength = 0.9; 
    }
    
    @Override 
    public BufferedImage apply(BufferedImage input){
        // deep copy 
        // greyscale 
        // inversion 
        // gaussian blur 
        // make transparent 
        // ~B~L~E~N~D~
     return input; 
    }
}
