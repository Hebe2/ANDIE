/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cosc202.andie;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
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

/**
 *
 * @author hebebebebe
 *
 *
 */
public class MacrosAction {

    private static ResourceBundle bundle = LanguageUtil.getBundle();
    public int shortcut = Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx();

    /**
     * A list of actions for the File menu.
     */
    protected ArrayList<Action> actions;

    /**
     * <p>
     * Create a set of File menu actions.
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
     * Create a menu containing the list of Edit actions.
     * </p>
     *
     * @return The edit menu UI element.
     */
    public JMenu createMenu() {
        JMenu macrosMenu = new JMenu(bundle.getString("MACROS"));

        for (Action action : actions) {
            macrosMenu.add(new JMenuItem(action));
        }

        return macrosMenu;
    }

    public class recordAction extends ImageAction {

        public recordAction(String name, ImageIcon icon, String desc, int mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {
            if (!EditActions.imageCheck()) {
                return;
            }
            JOptionPane.showMessageDialog(target, bundle.getString("MACROS RECORDING"), bundle.getString("MACROS"), JOptionPane.INFORMATION_MESSAGE );
            target.getImage().record();
        }
    }

    public class stopAction extends ImageAction {

        public stopAction(String name, ImageIcon icon, String desc, int mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!EditActions.imageCheck()) {
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

    public class applyAction extends ImageAction {

        public applyAction(String name, ImageIcon icon, String desc, int mnemonic) {
            super(name, icon, desc, mnemonic);
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
