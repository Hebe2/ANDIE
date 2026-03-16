/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author manuella
 */
public class SettingsAction {

    protected ArrayList<Action> actions;

    public SettingsAction() {
        actions = new ArrayList<>();
        actions.add(new SetEnglishAction("English", null, "Switch to English", null));
        actions.add(new SetGermanAction("Germen", null, "Switch to German", null));
    }

    public JMenu createMenu() {
        JMenu settingsMenu = new JMenu("Settings");
        for (Action action : actions) {
            settingsMenu.add(new JMenuItem(action));
        }
        return settingsMenu;
    }

    public class SetEnglishAction extends AbstractAction {
        SetEnglishAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon);
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            LanguageUtil.setLanguage("NZ", "en");
            JOptionPane.showMessageDialog(null, "Please restart ANDIE to apply language change.");
        }
    }

    public class SetGermanAction extends AbstractAction {
        SetGermanAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon);
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            LanguageUtil.setLanguage("DE", "de");
            JOptionPane.showMessageDialog(null, "Please restart ANDIE to apply language change.");
        }
    }
}