/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cosc202.andie;

import static cosc202.andie.EditActions.imageCheck;
import static cosc202.andie.ImageAction.target;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
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
 *<p>
 * Actions provided by the Draw Menu
 * </p>
 * 
 * * <p>
 * The Draw menu contains actions that draw a shape within a user selected region. The shape options
 * are rectangle, oval, and line. Each shape has options for changing the fill and and outline color
 * as well as whether the outline is dashed or solid. 
 * </p>
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
        
        Rectangle selection = imagePanel.getSelection();
        
        if (selection == null || selection.width == 0 || selection.height == 0) {
            JOptionPane.showMessageDialog(target, bundle.getString("PLEASE MAKE A SELECTION"));
            return;
        }
        
        String[] fillOptions = {bundle.getString("FILL"),bundle.getString("NO FILL")};
        JComboBox<String> fillBox = new JComboBox<>(fillOptions);
        
        String[] fillColorNames = {bundle.getString("BLACK"), bundle.getString("RED"), bundle.getString("GREEN"), bundle.getString("BLUE"), bundle.getString("WHITE"), bundle.getString("YELLOW")};
        Color[]  fillColorValues = {Color.BLACK, Color.RED, Color.GREEN, Color.BLUE, Color.WHITE, Color.YELLOW};
        JComboBox<String> fillColorBox = new JComboBox<>(fillColorNames);
        
        String[] outlineColorNames = {bundle.getString("BLACK"), bundle.getString("RED"), bundle.getString("GREEN"), bundle.getString("BLUE"), bundle.getString("WHITE"), bundle.getString("YELLOW")};
        Color[] outlineColorValues = {Color.BLACK, Color.RED, Color.GREEN, Color.BLUE, Color.WHITE, Color.YELLOW};
        JComboBox<String> outlineColorBox = new JComboBox<>(outlineColorNames);
        
        String[] dashOptions = {bundle.getString("SOLID"), bundle.getString("DASHED")};
        JComboBox<String> dashBox = new JComboBox<>(dashOptions);
        
        JPanel panel = new JPanel(new GridLayout(4, 2, 5, 5));
        panel.add(new JLabel(bundle.getString("FILL")));
        panel.add(fillBox);
        panel.add(new JLabel(bundle.getString("FILL COLOR")));
        panel.add(fillColorBox);
        panel.add(new JLabel(bundle.getString("OUTLINE COLOR")));
        panel.add(outlineColorBox);
        panel.add(new JLabel(bundle.getString("DASH")));
        panel.add(dashBox);
        
        int result = JOptionPane.showConfirmDialog(
            null, panel,
            bundle.getString("DRAW") + " " + shapeType,
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.PLAIN_MESSAGE
        );

        if (result != JOptionPane.OK_OPTION) return;
        
            boolean filled = fillBox.getSelectedIndex() == 0;
            Color fillColor = fillColorValues[fillColorBox.getSelectedIndex()];
            Color outlineColor = outlineColorValues[outlineColorBox.getSelectedIndex()];
            boolean dashed = dashBox.getSelectedIndex() == 1;
            applyShape(imagePanel, selection, shapeType, filled, fillColor, outlineColor, dashed);
        
    }

    private void applyShape(ImagePanel imagePanel,Rectangle selection,
                            String shapeType, boolean filled, Color fillColor, Color outlineColor, boolean dashed){
        
          double scale = imagePanel.getZoom()/100.0;
          
          imagePanel.getImage().apply(new DrawShape(shapeType, filled, fillColor, outlineColor, selection, scale, dashed));
        
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
            drawingOptions(bundle.getString("RECTANGLE"));
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
            drawingOptions(bundle.getString("OVAL"));
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
            drawingOptions(bundle.getString("LINE"));
        }
    }
}
        
    

