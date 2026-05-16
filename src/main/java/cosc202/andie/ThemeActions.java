/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cosc202.andie;

import java.awt.event.ActionEvent;
import static java.nio.file.Files.size;
import java.util.ArrayList;
import javax.swing.*;

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

    public JMenu createSubMenu() {
        JMenu themeMenu = new JMenu(LanguageUtil.getBundle().getString("THEMES"));
        
        for (Action action : actions) {
            themeMenu.add(new JMenuItem(action));
        }
        
        return themeMenu;
    }

    private void setDefaultFont(java.awt.Font font) {
        java.util.Enumeration<Object> keys = UIManager.getDefaults().keys();

        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);

            if (value instanceof javax.swing.plaf.FontUIResource) {
                UIManager.put(key,
                        new javax.swing.plaf.FontUIResource(font));
            }
        }
    }

    class SetThemeAction extends AbstractAction {

        private String lafClass;

        SetThemeAction(String name, ImageIcon icon, String desc, Integer mnemonic, String lafClass) {
            super(name, icon);
            this.lafClass = lafClass;
            putValue(Action.SHORT_DESCRIPTION, desc);
        }

        public void actionPerformed(ActionEvent e) {
            try {
                // saves current window size
                java.awt.Dimension currentSize = Andie.getFrame().getSize();

                // change theme
                UIManager.setLookAndFeel(lafClass);

                //set cconsistent font
                java.awt.Font font = new java.awt.Font("SansSerif", java.awt.Font.BOLD, 14);
                setDefaultFont(font);

                // Refresh UI
                SwingUtilities.updateComponentTreeUI(Andie.getFrame());

                // restore window size
                Andie.getFrame().setSize(currentSize);

                // final refresh
                Andie.getFrame().revalidate();
                Andie.getFrame().repaint();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Could not apply theme: " + ex.getMessage());
            }
        }
    }

}
