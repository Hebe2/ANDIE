package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

/**
 * <p>
 * Actions provided by the Edit menu.
 * </p>
 *
 * <p>
 * The Edit menu is very common across a wide range of applications. There are a
 * lot of operations that a user might expect to see here. In the sample code
 * there are Undo and Redo actions, but more may need to be added.
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
public class EditActions {

    
    private static ResourceBundle bundle = LanguageUtil.getBundle();

    /**
     * A list of actions for the Edit menu.
     */
    protected ArrayList<Action> actions;

    /**
     * <p>
     * Create a set of Edit menu actions.
     * </p>
     */
    public EditActions() {
        actions = new ArrayList<>();

        actions.add(new UndoAction(bundle.getString("UNDO"), null, bundle.getString("UNDO"), KeyEvent.VK_Z));
        actions.add(new RedoAction(bundle.getString("REDO"), null, bundle.getString("REDO"), KeyEvent.VK_Y));
        actions.add(new ResizeAction(bundle.getString("RESIZE"), null, bundle.getString("RESIZE"), KeyEvent.VK_X));
        actions.add(new HorizontalFlipAction(bundle.getString("FLIP - HORIZONTAL"), null, bundle.getString("FLIP IMAGINE HOIZONTALLY"), KeyEvent.VK_H));

        
    }

    /**
     * <p>
     * Create a menu containing the list of Edit actions.
     * </p>
     *
     * @return The edit menu UI element.
     */
    public JMenu createMenu() {
        JMenu editMenu = new JMenu(bundle.getString("EDIT"));

        for (Action action : actions) {
            editMenu.add(new JMenuItem(action));
        }

        return editMenu;
    }
    
    public class ResizeAction extends ImageAction {
         ResizeAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }
         
        @Override
        public void actionPerformed(ActionEvent e) {
            String input = JOptionPane.showInputDialog(bundle.getString("ENTER SCALE FACTOR: "));
            try {
                int scaleFactor = Integer.parseInt(input);
                if (scaleFactor < 0){
                    JOptionPane.showMessageDialog(null, bundle.getString("SCALE FACTOR CANNOT BE NEGATIVE"));
                    return;
                }
                //target.getImage().apply(new ImageResize(scale));
                target.repaint();
                target.getParent().revalidate();
                
                } catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(null, bundle.getString("PLEASE ENTER A NUMBER"));
                }
            

            while (true){
                 input = JOptionPane.showInputDialog(bundle.getString("ENTER SCALE FACTOR(%): "));
                try {
                    if (input == null){
                        return;
                    }
                    int scaleFactor = Integer.parseInt(input);
                    if (scaleFactor < 0){
                        JOptionPane.showMessageDialog(null, bundle.getString("SCALE FACTOR CANNOT BE NEGATIVE"));
                        continue;
                    }
                    target.getImage().apply(new ImageResize(scaleFactor));
                    target.repaint();
                    target.getParent().revalidate();
                    break;

                    } catch (NumberFormatException ex){
                        JOptionPane.showMessageDialog(null, bundle.getString("PLEASE ENTER AN INTEGER"));
                    }

                }
            }
        }
    
   
    /**
     * <p>
     * Action to undo an {@link ImageOperation}.
     * </p>
     *
     * @see EditableImage#undo()
     */
    public class UndoAction extends ImageAction {

        /**
         * <p>
         * Create a new undo action.
         * </p>
         *
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if
         * null).
         */
        UndoAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the undo action is triggered.
         * </p>
         *
         * <p>
         * This method is called whenever the UndoAction is triggered. It undoes
         * the most recently applied operation.
         * </p>
         *
         * @param e The event triggering this callback.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            target.getImage().undo();
            target.repaint();
            target.getParent().revalidate();
        }
    }

    /**
     * <p>
     * Action to redo an {@link ImageOperation}.
     * </p>
     *
     * @see EditableImage#redo()
     */
    public class RedoAction extends ImageAction {

        /**
         * <p>
         * Create a new redo action.
         * </p>
         *
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if
         * null).
         */
        RedoAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the redo action is triggered.
         * </p>
         *
         * <p>
         * This method is called whenever the RedoAction is triggered. It redoes
         * the most recently undone operation.
         * </p>
         *
         * @param e The event triggering this callback.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            target.getImage().redo();
            target.repaint();
            target.getParent().revalidate();
        }
        
    

    }
    
    public class HorizontalFlipAction extends ImageAction {

        HorizontalFlipAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            target.getImage().apply(new HorizontalFlip());
            target.repaint();
            target.getParent().revalidate();
        }
    }

}
