/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cosc202.andie;

import static cosc202.andie.EditActions.imageCheck;
import static cosc202.andie.ImageAction.target;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author timnanevo
 */
public class DrawingOps {
    
    /**
     * A @ResourceBundle that retrieves strings throughout the class in the
     * proper language
     */
    private static ResourceBundle bundle = LanguageUtil.getBundle();

    /**
     * A list of actions for the Drawing menu.
     */
    protected ArrayList<Action> actions;
    
    //JAVADOC
    private ImagePanel imagePanel;
    
    public DrawingOps(ImagePanel imagePanel){
        this.imagePanel = imagePanel;
        actions = new ArrayList<>();
        actions.add(new RectangleAction(bundle.getString("RECTANGLE"), null, bundle.getString("DRAW"), null));
        actions.add(new OvalAction(bundle.getString("OVAL"), null, bundle.getString("DRAW"), null));
        actions.add(new LineAction(bundle.getString("LINE"), null, bundle.getString("DRAW"), null));

    }
    
    /**
     * <p>
     * Create a menu containing the list of Edit actions.
     * </p>
     *
     * @return The edit menu UI element.
     */
    public JMenu createMenu() {
        JMenu drawingMenu = new JMenu(bundle.getString("DRAW"));

        for (Action action : actions) {
            drawingMenu.add(new JMenuItem(action));
        }

        return drawingMenu;
    }
    
    private void drawingOptions(String shapeType){
    
        if (!imageCheck()){
            return;
        }
        
//        List<Rectangle> selections = imagePanel.getSelections();
//
//        if (selections == null || selections.isEmpty()) {
//            JOptionPane.showMessageDialog(target, bundle.getString("PLEASE MAKE A SELECTION"));
//            return;
//        }
        
        Rectangle selection = imagePanel.getSelection();
        
        if (selection == null || selection.width == 0 || selection.height == 0) {
            JOptionPane.showMessageDialog(target, bundle.getString("PLEASE MAKE A SELECTION"));
            return;
        }
        
        String[] fillOptions = {"No Fill", "Fill"};
        JComboBox<String> fillBox = new JComboBox<>(fillOptions);
        
        String[] colorNames = {"Black", "Red", "Green", "Blue", "White", "Yellow"};
        Color[]  colorValues = {Color.BLACK, Color.RED, Color.GREEN, Color.BLUE, Color.WHITE, Color.YELLOW};
        JComboBox<String> colorBox = new JComboBox<>(colorNames);
        
        JPanel panel = new JPanel(new GridLayout(2, 2, 5, 5));
        panel.add(new JLabel("Fill:"));
        panel.add(fillBox);
        panel.add(new JLabel("Color:"));
        panel.add(colorBox);
        
        int result = JOptionPane.showConfirmDialog(
            null, panel,
            bundle.getString("DRAW") + " " + shapeType,
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.PLAIN_MESSAGE
        );

        if (result != JOptionPane.OK_OPTION) return;
        
            boolean filled = fillBox.getSelectedIndex() == 1;
            Color color = colorValues[colorBox.getSelectedIndex()];
            applyShape(imagePanel, selection, shapeType, filled, color);
        
    }

    private void applyShape(ImagePanel imagePanel,Rectangle selection,
                            String shapeType, boolean filled, Color color){
        
          double scale = imagePanel.getZoom()/100.0;
          
          imagePanel.getImage().apply(new DrawShape(shapeType, filled, color, selection, scale));
        
//        BufferedImage img = imagePanel.getImage().getCurrentImage();
//        Graphics2D g2 = img.createGraphics();
//        
//        //correct zoom
//        double scale = imagePanel.getZoom() / 100.0;
//        int x = (int)(selection.x / scale);
//        int y = (int)(selection.y / scale);
//        int w = (int)(selection.width / scale);
//        int h = (int)(selection.height / scale);
//        
//        g2.setColor(color);
//        //g2.setStroke(new BasicStroke(2));
//        //g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//
//        switch (shapeType) {
//            case "Rectangle" -> {
//                if (filled) g2.fillRect(x, y, w, h);
//                else        g2.drawRect(x, y, w, h);
//            }
//            case "Oval" -> {
//                if (filled) g2.fillOval(x, y, w, h);
//                else        g2.drawOval(x, y, w, h);
//            }
//            case "Line" -> {
//                g2.drawLine(x, y, x + w, y + h);
//            }
//        }
//        
//        g2.dispose();
//
        imagePanel.clearSelection();
        imagePanel.repaint();
    }
    
    /**
     * 
     */
    public class RectangleAction extends AbstractAction {
         /**
         * <p>
         * Create a new RectangleAction.
         * </p>
         *
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if
         * null).
         */
        RectangleAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon);
        }
        /**
         * <p>
         * 
         * <p>
         * 
         * @param e The event triggering this callback.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            drawingOptions("Rectangle");
        }
    }
    
    /**
     * 
     */
    public class OvalAction extends AbstractAction {
         /**
         * <p>
         * Create a new RectangleAction.
         * </p>
         *
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if
         * null).
         */
        OvalAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon);
        }
        /**
         * <p>
         * 
         * <p>
         * 
         * @param e The event triggering this callback.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            drawingOptions("Oval");
        }
    }
    
    /**
     * 
     */
    public class LineAction extends AbstractAction {
         /**
         * <p>
         * Create a new RectangleAction.
         * </p>
         *
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if
         * null).
         */
        LineAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon);
        }
        /**
         * <p>
         * 
         * <p>
         * 
         * @param e The event triggering this callback.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            drawingOptions("Line");
        }
    }
}
        
    

