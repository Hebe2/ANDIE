package cosc202.andie;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.*;

/**
 * <p>
 * UI display element for {@link EditableImage}s.
 * </p>
 *
 * <p>
 * This class extends {@link JPanel} to allow for rendering of an image, as well
 * as zooming in and out.
 * </p>
 *
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA
 * 4.0</a>
 * </p>
 *
 * @author Steven Mills
 * @version 1.0
 */
public class ImagePanel extends JPanel implements MouseListener, MouseMotionListener {

    /**
     * The image to display in the ImagePanel.
     */
    private EditableImage image;
    
    //JAVADOC
    private Point startPoint = null;
    private Point endPoint = null;
    private Rectangle selection = null;

    /**
     * <p>
     * The zoom-level of the current view. A scale of 1.0 represents actual
     * size; 0.5 is zoomed out to half size; 1.5 is zoomed in to one-and-a-half
     * size; and so forth.
     * </p>
     *
     * <p>
     * Note that the scale is internally represented as a multiplier, but
     * externally as a percentage.
     * </p>
     */
    private double scale;

    /**
     * <p>
     * Create a new ImagePanel.
     * </p>
     *
     * <p>
     * Newly created ImagePanels have a default zoom level of 100%
     * </p>
     */
    public ImagePanel() {
        image = new EditableImage();
        scale = 1.0;
        
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    /**
     * <p>
     * Get the currently displayed image
     * </p>
     *
     * @return the image currently displayed.
     */
    @SuppressWarnings("NonPublicExported")
    public EditableImage getImage() {
        return image;
    }
    
    //JAVADOC
    public Rectangle getSelection(){
        return selection;
    }
    
    /**
     * <p>
     * Get the current zoom level as a percentage.
     * </p>
     *
     * <p>
     * The percentage zoom is used for the external interface, where 100% is the
     * original size, 50% is half-size, etc.
     * </p>
     *
     * @return The current zoom level as a percentage.
     */
    public double getZoom() {
        return 100 * scale;
    }

    /**
     * <p>
     * Set the current zoom level as a percentage.
     * </p>
     *
     * <p>
     * The percentage zoom is used for the external interface, where 100% is the
     * original size, 50% is half-size, etc. The zoom level is restricted to the
     * range [50, 200].
     * </p>
     *
     * @param zoomPercent The new zoom level as a percentage.
     */
    public void setZoom(double zoomPercent) {
        if (zoomPercent < 50) {
            zoomPercent = 50;
        }
        if (zoomPercent > 200) {
            zoomPercent = 200;
        }
        scale = zoomPercent / 100;
    }

    /**
     * <p>
     * Gets the preferred size of this component for UI layout.
     * </p>
     *
     * <p>
     * The preferred size is the size of the image (scaled by zoom level), or a
     * default size if no image is present.
     * </p>
     *
     * @return The preferred size of this component.
     */
    @Override
    public Dimension getPreferredSize() {
        if (image.hasImage()) {
            return new Dimension((int) Math.round(image.getCurrentImage().getWidth() * scale),
                    (int) Math.round(image.getCurrentImage().getHeight() * scale));
        } else {
            return new Dimension(450, 450);
        }
    }

    /**
     * <p>
     * (Re)draw the component in the GUI.
     * </p>
     *
     * @param g The Graphics component to draw the image on.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image.hasImage()) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.scale(scale, scale);
            g2.drawImage(image.getCurrentImage(), null, 0, 0);
            g2.dispose();
        }
        
        if (selection != null){
            Graphics2D g2 = (Graphics2D) g.create();

            // semi-transparent fill
            g2.setColor(new Color(255, 255, 255, 90)); // RGBA (alpha = transparency)
            g2.fill(selection);
          
            //g2.setColor(Color.BLACK);
            //g2.setStroke(new BasicStroke(2));
            g2.draw(selection);
            g2.dispose();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        mousePressed(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        startPoint = e.getPoint();
        endPoint = startPoint;
        selection = new Rectangle(startPoint);
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseDragged(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        endPoint = e.getPoint();
        
        int x = Math.min(startPoint.x, endPoint.x);
        int y = Math.min(startPoint.y, endPoint.y);
        int width = Math.abs(startPoint.x - endPoint.x);
        int height = Math.abs(startPoint.y - endPoint.y);
        
        selection = new Rectangle(x, y, width, height);
  
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
    
    public void clearSelection(){
        selection = null;
        repaint();
    }
}
