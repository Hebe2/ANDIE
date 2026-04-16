/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cosc202.andie;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

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
     * A list of actions for the Edit menu.
     */
    protected ArrayList<Action> actions;
    
    public DrawingOps(){
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
        }
    }
}
