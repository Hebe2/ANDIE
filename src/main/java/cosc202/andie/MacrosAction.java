/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cosc202.andie;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

/**
 * <p>
 * Provides the actions and menu components used for recording, saving and
 * applying image editing macros within the application
 * </p>
 *
 * <p>
 * This class created a Macros menu containing actions for starting a macro
 * recording, stopping and saving a recorded macro, and applying a previously
 * saved macro. Macros are stored as .ops files in a macros directory in the
 * user's home folder. The macro is a recorded sequence of image operations that
 * are recorded and saved to a file, that can be reapplied to another image.
 * </p>
 *
 * @author hebebebebe
 */
public class MacrosAction {

    private static ResourceBundle bundle = LanguageUtil.getBundle();
    public int shortcut = Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx();

    /**
     * A list of actions for the macros menu.
     */
    protected ArrayList<Action> actions;

    /**
     * <p>
     * Create a set of macros menu actions.
     * </p>
     */
    public MacrosAction() {
        actions = new ArrayList<>();

        actions.add(new recordAction(bundle.getString("RECORD"), null, bundle.getString("RECORD MACROS"), KeyEvent.VK_R));
        actions.add(new stopAction(bundle.getString("STOP"), null, bundle.getString("STOP MACROS RECORDING"), KeyEvent.VK_T));
        actions.add(new applyAction(bundle.getString("APPLY"), null, bundle.getString("APPLY MACROS"), KeyEvent.VK_P));
    }

    /**
     * <p>
     * Create a menu containing the list of macros actions.
     * </p>
     *
     * @return The macros menu UI element.
     */
    public JMenu createMenu() {
        JMenu macrosMenu = new JMenu(bundle.getString("MACROS"));

        for (Action action : actions) {
            macrosMenu.add(new JMenuItem(action));
        }

        return macrosMenu;
    }

    private int recordedImageWidth = 0;
    private int recordedImageHeight = 0;

    /**
     * <p>
     * Action to record an macros.
     * </p>
     *
     * see record
     */
    public class recordAction extends ImageAction {

        public recordAction(String name, ImageIcon icon, String desc, int mnemonic) {
            super(name, icon, desc, mnemonic);
            putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_R, shortcut | InputEvent.SHIFT_DOWN_MASK));

        }

        public void actionPerformed(ActionEvent e) {
            if (!EditActions.imageCheck()) {
                return;
            }
            if (target.getImage().isRecording()) {
                JOptionPane.showMessageDialog(target, bundle.getString("MACROS ALREADY RECORDING"), bundle.getString("MACROS"), JOptionPane.INFORMATION_MESSAGE);

            } else {
                JOptionPane.showMessageDialog(target, bundle.getString("MACROS RECORDING"), bundle.getString("MACROS"), JOptionPane.INFORMATION_MESSAGE);
                target.getImage().record();
            }
        }
    }

    /**
     * <p>
     * Action to stop recording an macros.
     * </p>
     *
     * see EditableImage stopRecording()
     */
    public class stopAction extends ImageAction {

        public stopAction(String name, ImageIcon icon, String desc, int mnemonic) {
            super(name, icon, desc, mnemonic);
            putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_P, shortcut | InputEvent.SHIFT_DOWN_MASK));

        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!EditActions.imageCheck()) {
                return;
            }
            int choice = JOptionPane.showConfirmDialog(
                    target,
                    "Do you want to save the recorded macro? \n hit no if you want to discard macros.",
                    "Stop Recording",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE
            );

            if (choice == JOptionPane.CANCEL_OPTION) {// do nothing, keep recording
                return;
            }

            if (choice == JOptionPane.NO_OPTION) {//discard the macros 
                target.getImage().cancelRecording();
                return;
            }

            String macrosFolder = System.getProperty("user.home") + File.separator + "macros";
            new File(macrosFolder).mkdirs();

            JFileChooser fileChooser = new JFileChooser(macrosFolder);
            fileChooser.setCurrentDirectory(new File(macrosFolder));
            fileChooser.setDialogTitle("Save Macro As");
            int result = fileChooser.showSaveDialog(target);

            if (result == JFileChooser.APPROVE_OPTION) {
                try {
                    String macroFilepath = fileChooser.getSelectedFile().getCanonicalPath();
                    if (!macroFilepath.endsWith(".ops")) {
                        macroFilepath += ".ops";
                    }
                    target.getImage().stopRecording(macroFilepath);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        }
    }

    /**
     * <p>
     * Action to apply an macros.
     * </p>
     *
     * see EditableImage applyMacros()
     */
    public class applyAction extends ImageAction {

        public applyAction(String name, ImageIcon icon, String desc, int mnemonic) {
            super(name, icon, desc, mnemonic);
            putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_A, shortcut | InputEvent.SHIFT_DOWN_MASK));

        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!EditActions.imageCheck()) {
                return;
            }
            String macrosFolder = System.getProperty("user.home") + File.separator + "macros";
            File folder = new File(macrosFolder);
            File[] macroFiles = folder.listFiles((dir, name) -> name.endsWith(".ops"));

            if (macroFiles == null || macroFiles.length == 0) {
                JOptionPane.showMessageDialog(target, "No macros found!");
                return;
            }

            String[] fileNames = new String[macroFiles.length];
            for (int i = 0; i < macroFiles.length; i++) {
                fileNames[i] = macroFiles[i].getName();
            }

            String chosen = (String) JOptionPane.showInputDialog(
                    target,
                    "Choose a macro to apply:",
                    "Apply Macro",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    fileNames,
                    fileNames[0]
            );
            if (chosen == null) {
                return;
            }

//            int warning = JOptionPane.showConfirmDialog(
//                    target,
//                    "drawing proportions may not match if the image size is different from when the macro was recorded. Do you want to continue?",
//                    "Proportions Warning",
//                    JOptionPane.YES_NO_OPTION,
//                    JOptionPane.WARNING_MESSAGE
//            );
//
//            if (warning == JOptionPane.NO_OPTION) {
//                return;
//            }
            try {
                String fullPath = macrosFolder + File.separator + chosen;
                target.getImage().applyMacros(fullPath);
                target.repaint();
                target.getParent().revalidate();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            target.repaint();
            target.getParent().revalidate();
        }
    }
}
