/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
/**
 *
 * @author hebebebebe
 */
public class RotateActions {


    /**
     * A list of actions for the Filter menu.
     */
    protected ArrayList<Action> actions;

    /**
     * <p>
     * Create a set of Rotate menu actions.
     * </p>
     */
    public RotateActions() {
        actions = new ArrayList<>();
        actions.add(new CWAction("90° clockwise", null, "rotate clockwise 90", KeyEvent.VK_C));
        actions.add(new ACWAction("90° anticlockwise", null, "rotate anticlockwise 90", KeyEvent.VK_A));

    }

    /**
     * <p>
     * Create a menu containing the list of Filter actions.
     * </p>
     *
     * @return The filter menu UI element.
     */
    public JMenu createMenu() {
        JMenu rotateMenu = new JMenu("Rotate");

        for (Action action : actions) {
            rotateMenu.add(new JMenuItem(action));
        }

        return rotateMenu;
    }

    private static class ACWAction extends ImageAction {

        ACWAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }
        
        public void actionPerformed(ActionEvent e) {

            target.getImage().apply(new ImageRotation90ACW());
            target.repaint();
            target.getParent().revalidate();
        }
    }

    /**
     * <p>
     * Action to blur an image with a mean filter.
     * </p>
     *
     * @see MeanFilter
     */
    public class CWAction extends ImageAction {

        /**
         * <p>
         * Create a new mean-filter action.
         * </p>
         *
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if
         * null).
         */
        CWAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the convert-to-grey action is triggered.
         * </p>
         *
         * <p>
         * This method is called whenever the rotate 90 CW Action is triggered.
         * </p>
         *
         * @param e The event triggering this callback.
         */
        @Override
        public void actionPerformed(ActionEvent e) {

            target.getImage().apply(new ImageRotation90clockwise());
            target.repaint();
            target.getParent().revalidate();
        }

    }
}


