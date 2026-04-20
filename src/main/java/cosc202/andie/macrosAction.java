/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cosc202.andie;

import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javax.swing.Action;

/**
 *
 * @author hebebebebe
 */
public class macrosAction {
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
    public macrosAction() {
        actions = new ArrayList<>();
        actions.add(new macrosActions.recordAction(bundle.getString("record"), null, bundle.getString("OPEN A FILE"), KeyEvent.VK_O));
        actions.add(new macrosActions.stopAction(bundle.getString("stop"), null, bundle.getString("SAVE THE FILE"), KeyEvent.VK_S));
        actions.add(new macrosActions.replayAction(bundle.getString("replay"), null, bundle.getString("SAVE THE FILE"), KeyEvent.VK_S));

    }
}
