/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cosc202.andie;

import static cosc202.andie.FilterActions.imageCheck;
import static cosc202.andie.ImageAction.target;
import java.util.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

/**
 *
 * @author hebebebebe
 */
public class RotateActions {

    //private static final ResourceBundle bundle = ResourceBundle.getBundle("Bundle");
    private static ResourceBundle bundle = LanguageUtil.getBundle();

    /**
     * A list of actions for the Rotate menu.
     */
    protected ArrayList<Action> actions;

    /**
     * <p>
     * Create a set of Rotate menu actions.
     * </p>
     */
    public RotateActions() {
        actions = new ArrayList<>();
        actions.add(new CWAction(bundle.getString("90° CLOCKWISE"), null, bundle.getString("ROTATE CLOCKWISE 90"), KeyEvent.VK_C));
        actions.add(new ACWAction(bundle.getString("90° ANTICLOCKWISE"), null, bundle.getString("ROTATE ANTICLOCKWISE 90"), KeyEvent.VK_A));
        actions.add(new One80Action(bundle.getString("180° ROTATE"), null, bundle.getString("180° ROTATE"), KeyEvent.VK_O));

    }

    /**
     * <p>
     * Create a menu containing the list of Rotate actions.
     * </p>
     *
     * @return The filter menu UI element.
     */
    public JMenu createMenu() {
        JMenu rotateMenu = new JMenu(bundle.getString("ROTATE"));

        for (Action action : actions) {
            rotateMenu.add(new JMenuItem(action));
        }

        return rotateMenu;
    }

    /**
     * <p>
     * Action to rotate image 180.
     * </p>
     *
     */
    private static class One80Action extends ImageAction {

        One80Action(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the rotate 180 action is triggered.
         * </p>
         *
         * <p>
         * This method is called whenever the rotate 180 Action is triggered.
         * </p>
         *
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            if(!imageCheck()){
                return;
            }
            target.getImage().apply(new ImageRotation180());
            target.repaint();
            target.getParent().revalidate();
        }
    }

    /**
     * <p>
     * Action to rotate image anticlockwise.
     * </p>
     *
     */
    private static class ACWAction extends ImageAction {

        ACWAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the rotate anticlockwise action is triggered.
         * </p>
         *
         * <p>
         * This method is called whenever the rotate 90 ACW Action is triggered.
         * </p>
         *
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            if(!imageCheck()){
                return;
            }
            target.getImage().apply(new ImageRotation90ACW());
            target.repaint();
            target.getParent().revalidate();
        }
    }

    /**
     * <p>
     * Action to rotate image clockwise.
     * </p>
     *
     */
    public class CWAction extends ImageAction {

        /**
         * <p>
         * Create a rotate clockwise action.
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
         * Callback for when the rotate clockwise action is triggered.
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
            if(!imageCheck()){
                return;
            }
            target.getImage().apply(new ImageRotation90clockwise());
            target.repaint();
            target.getParent().revalidate();
        }

    }
    
    public static boolean imageCheck(){
        if (!target.getImage().hasImage()){
            JOptionPane.showMessageDialog(target, "Please open an image" );
            return false;
        }
        return true;
    }
}
