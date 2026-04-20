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

/**
 *
 * @author timnanevo
 */
public class DrawShape implements ImageOperation, java.io.Serializable {
    
    private String shapeType;
    private boolean filled;
    private Color color;
    private Rectangle selection;
    private double scale;

    public DrawShape(String shapeType, boolean filled, Color color, Rectangle selection, double scale) {
        this.shapeType = shapeType;
        this.filled = filled;
        this.color = color;
        this.selection = selection;
        this.scale = scale;
    }
    
    @Override
    public BufferedImage apply(BufferedImage input) {
        Graphics2D g2 = input.createGraphics();

        int x = (int)(selection.x / scale);
        int y = (int)(selection.y / scale);
        int w = (int)(selection.width / scale);
        int h = (int)(selection.height / scale);

        
        g2.setColor(color);
       
        

            switch (shapeType) {
                case "Rectangle" -> {
                    if (filled) g2.fillRect(x, y, w, h);
                    else        g2.drawRect(x, y, w, h);
                }
                case "Oval" -> {
                    if (filled) g2.fillOval(x, y, w, h);
                    else        g2.drawOval(x, y, w, h);
                }
                case "Line" -> {
                    g2.drawLine(x, y, x + w, y + h);
                }
            }

        g2.dispose();
        return input;
    }
    
}
                                                                                                            