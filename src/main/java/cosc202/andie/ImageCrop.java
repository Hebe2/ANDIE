/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cosc202.andie;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.Serializable;

/**
 *<p>
 * ImageOperation that crops an image to a user selected region 
 *</p>
 * 
 * @author timnanevo
 */
public class ImageCrop implements ImageOperation, Serializable {

    private int imgX, imgY, imgWidth, imgHeight;

    public ImageCrop(ImagePanel panel) {
        Rectangle selection = panel.getSelection();

        if (selection == null) {
            this.imgX = 0;
            this.imgY = 0;
            this.imgWidth = 0;
            this.imgHeight = 0;
            return;
        }

        double scale = panel.getZoom() / 100.0;

        this.imgX = (int) (selection.x / scale);
        this.imgY = (int) (selection.y / scale);
        this.imgWidth = (int) (selection.width / scale);
        this.imgHeight = (int) (selection.height / scale);

        panel.clearSelection();
    }

    @Override
    public BufferedImage apply(BufferedImage input) {

        //ensure in bounds
        imgX = Math.max(0, imgX);
        imgY = Math.max(0, imgY);
        imgWidth = Math.min(imgWidth, input.getWidth() - imgX);
        imgHeight = Math.min(imgHeight, input.getHeight() - imgY);

        if (imgWidth <= 0 || imgHeight <= 0) {
            return input;
        }

        BufferedImage crop = input.getSubimage(imgX, imgY, imgWidth, imgHeight);

        BufferedImage copy = new BufferedImage(imgWidth, imgHeight, input.getType());
        copy.getGraphics().drawImage(crop, 0, 0, null);

        //imagePanel.clearSelection();
        return copy;
    }

}
