/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cosc202.andie;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 *
 * @author hebebebebe
 */
public class ThemeActions {

    protected ArrayList<Action> actions;

    public ThemeActions() {
        actions = new ArrayList<>();
        actions.add(new SetThemeAction("Light", null, "Light theme", null, UIManager.getSystemLookAndFeelClassName()));
        actions.add(new SetThemeAction("Dark (Motif)", null, "Dark theme", null, "com.sun.java.swing.plaf.motif.MotifLookAndFeel"));

        actions.add(new SetThemeAction("Nimbus", null, "Dark theme", null, "javax.swing.plaf.nimbus.NimbusLookAndFeel"));
        actions.add(new SetThemeAction("Metal", null, "Metal theme", null, "javax.swing.plaf.metal.MetalLookAndFeel"));

    }

    public JMenu createMenu() {
        JMenu themeMenu = new JMenu("Theme");
        for (Action action : actions) {
            themeMenu.add(new JMenuItem(action));
        }
        return themeMenu;
    }

    class SetThemeAction extends ImageAction {

        private String lafClass;

        SetThemeAction(String name, ImageIcon icon, String desc, Integer mnemonic, String lafClass) {
            super(name, icon, desc, mnemonic);
            this.lafClass = lafClass;
        }

        public void actionPerformed(ActionEvent e) {
            try {

                UIManager.setLookAndFeel(lafClass);
                SwingUtilities.updateComponentTreeUI(Andie.getFrame());
                Andie.getFrame().pack();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Could not apply theme: " + ex.getMessage());
            }
        }
    }
}
