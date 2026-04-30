package cosc202.andie;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import cosc202.andie.ImageOperation;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.awt.BasicStroke;

/**
 *
 * @author timnanevo
 */
public class DrawShape implements ImageOperation, java.io.Serializable {
    
    private String shapeType;
    private boolean filled;
    private Color fillColor;
    private Color outlineColor;
    private Rectangle selection;
    private double scale;
    private boolean dashed;

    public DrawShape(String shapeType, boolean filled, Color fillColor, Color outlineColor, Rectangle selection, double scale, boolean dashed) {
        this.shapeType = shapeType;
        this.filled = filled;
        this.fillColor = fillColor;
        this.outlineColor = outlineColor;
        this.selection = selection;
        this.scale = scale;
        this.dashed = dashed;
    }
    
    @Override
    public BufferedImage apply(BufferedImage input) {
        Graphics2D g2 = input.createGraphics();

        int x = (int)(selection.x / scale);
        int y = (int)(selection.y / scale);
        int w = (int)(selection.width / scale);
        int h = (int)(selection.height / scale);

        
//        g2.setColor(color);
       
        if (dashed) {
            float[] dashPattern = {10f, 5f};
            g2.setStroke(new BasicStroke(2f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10f, dashPattern, 0f));
        }
        
            switch (shapeType) {
                case "Rectangle" -> {
                    if (filled){ 
                        g2.setColor(fillColor);
                        g2.fillRect(x, y, w, h);
                    }   
                    g2.setColor(outlineColor);
                    g2.drawRect(x, y, w, h);
                }
                case "Oval" -> {
                    if  (filled){
                        g2.setColor(fillColor);
                        g2.fillOval(x, y, w, h);
                    }   
                    g2.setColor(outlineColor);
                    g2.drawOval(x, y, w, h);
                }
                case "Line" -> {
                    g2.setColor(outlineColor);
                    g2.drawLine(x, y, x + w, y + h);
                }
            }

        g2.dispose();
        return input;
    }
    
}
                                                                                                            