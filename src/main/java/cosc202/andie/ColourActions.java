package cosc202.andie;

import static cosc202.andie.ImageAction.target;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * <p>
 * Actions provided by the Colour menu.
 * </p>
 *
 * <p>
 * The Colour menu contains actions that affect the colour of each pixel
 * directly without reference to the rest of the image. This includes conversion
 * to greyscale in the sample code, but more operations will need to be added.
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
public class ColourActions {

    private static ResourceBundle bundle = LanguageUtil.getBundle();

    /**
     * A list of actions for the Colour menu.
     */
    protected ArrayList<Action> actions;

    /**
     * <p>
     * Create a set of Colour menu actions.
     * </p>
     */
    public ColourActions() {
        actions = new ArrayList<>();

        actions.add(new ConvertToGreyAction(bundle.getString("GREYSCALE"), null, bundle.getString("CONVERT TO GREYSCALE"), KeyEvent.VK_G));
        actions.add(new ThresholdAction(bundle.getString("THRESHOLD"), null, bundle.getString("APPLY THRESHOLD"), KeyEvent.VK_T));
        actions.add(new InversionAction(bundle.getString("INVERSION"), null, bundle.getString("APPLY INVERSION"), KeyEvent.VK_I));
        actions.add(new SwapRandBAction(bundle.getString("SWAP RED AND BLUE"), null, bundle.getString("SWAP RED AND BLUE"), KeyEvent.VK_R));
        actions.add(new SwapGandBAction(bundle.getString("SWAP GREEN AND BLUE"), null, bundle.getString("SWAP GREEN AND BLUE"), KeyEvent.VK_G));
    }

    /**
     * <p>
     * Create a menu containing the list of Colour actions.
     * </p>
     *
     * @return The colour menu UI element.
     */
    public JMenu createMenu() {
        JMenu fileMenu = new JMenu(bundle.getString("COLOUR"));

        for (Action action : actions) {
            fileMenu.add(new JMenuItem(action));
        }

        return fileMenu;
    }

    private static class SwapGandBAction extends ImageAction {

        public SwapGandBAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            String input = JOptionPane.showInputDialog(bundle.getString("ENTER THRESHOLD VALUE BETWEEN 0-255: "));
            try {
                int threshold = Integer.parseInt(input);
                if (threshold < 0 || threshold > 255) {
                    JOptionPane.showMessageDialog(null, bundle.getString("INVALID THRESHOLD VALUE"));
                    return;
                }
                target.getImage().apply(new ImageThresholding(threshold));
                target.repaint();
                target.getParent().revalidate();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, bundle.getString("PLEASE ENTER AN INTEGER"));

                target.getImage().apply(new SwapGandB());
                target.repaint();
                target.getParent().revalidate();
            }

        }
    }

        private static class SwapRandBAction extends ImageAction {

            public SwapRandBAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
                super(name, icon, desc, mnemonic);
            }

            @Override
            public void actionPerformed(ActionEvent e) {

                target.getImage().apply(new SwapRandB());
                target.repaint();
                target.getParent().revalidate();
            }

        }

        private static class InversionAction extends ImageAction {

            public InversionAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
                super(name, icon, desc, mnemonic);
            }

            @Override
            public void actionPerformed(ActionEvent e) {

                target.getImage().apply(new ImageInversion());
                target.repaint();
                target.getParent().revalidate();
            }

        }

        public class ThresholdAction extends ImageAction {

            ThresholdAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
                super(name, icon, desc, mnemonic);
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                while (true) {
                    String input = JOptionPane.showInputDialog("Enter threshold value between 0-255: ");
                    if (input == null) {
                        return;
                    }
                    try {
                        int threshold = Integer.parseInt(input);
                        if (threshold < 0 || threshold > 255) {
                            JOptionPane.showMessageDialog(null, "Integer must be between 0 and 255");
                            continue;
                        }
                        target.getImage().apply(new ImageThresholding(threshold));
                        target.repaint();
                        target.getParent().revalidate();
                        break;

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Please enter an integer");

                    }
                }
            }
        }

        /**
         * <p>
         * Action to convert an image to greyscale.
         * </p>
         *
         * @see ConvertToGrey
         */
        public class ConvertToGreyAction extends ImageAction {

            /**
             * <p>
             * Create a new convert-to-grey action.
             * </p>
             *
             * @param name The name of the action (ignored if null).
             * @param icon An icon to use to represent the action (ignored if
             * null).
             * @param desc A brief description of the action (ignored if null).
             * @param mnemonic A mnemonic key to use as a shortcut (ignored if
             * null).
             */
            ConvertToGreyAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
                super(name, icon, desc, mnemonic);
            }

            /**
             * <p>
             * Callback for when the convert-to-grey action is triggered.
             * </p>
             *
             * <p>
             * This method is called whenever the ConvertToGreyAction is
             * triggered. It changes the image to greyscale.
             * </p>
             *
             * @param e The event triggering this callback.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                target.getImage().apply(new ConvertToGrey());
                target.repaint();
                target.getParent().revalidate();
            }

        }

    }
