package cosc202.andie;

import static cosc202.andie.ImageAction.target;
import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;
import javax.swing.*;
import javax.imageio.*;

/**
 * <p>
 * Main class for A Non-Destructive Image Editor (ANDIE).
 * </p>
 *
 * <p>
 * This class is the entry point for the program. It creates a Graphical User
 * Interface (GUI) that provides access to various image editing and processing
 * operations.
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
public class Andie {

    /**
     * <p>
     * Launches the main GUI for the ANDIE program.
     * </p>
     *
     * <p>
     * This method sets up an interface consisting of an active image (an
     * {@code ImagePanel}) and various menus which can be used to trigger
     * operations to load, save, edit, etc. These operations are implemented
     * {@link ImageOperation}s and triggered via {@code ImageAction}s grouped by
     * their general purpose into menus.
     * </p>
     *
     * @see ImagePanel
     * @see ImageAction
     * @see ImageOperation
     * @see FileActions
     * @see EditActions
     * @see ViewActions
     * @see FilterActions
     * @see ColourActions
     * @see RotateActions
     * @see SettingActions
     *
     * @throws Exception if something goes wrong.
     */
 
    private static void createAndShowGUI() throws Exception {
        ResourceBundle bundle = LanguageUtil.getBundle();
        
        // Set up the main GUI frame
        JFrame frame = new JFrame("ANDIE");

        Image image = ImageIO.read(Andie.class.getClassLoader().getResource("icon.png"));
        frame.setIconImage(image);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        

        // The main content area is an ImagePanel
        ImagePanel imagePanel = new ImagePanel();
        ImageAction.setTarget(imagePanel);
        JScrollPane scrollPane = new JScrollPane(imagePanel);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Add in menus for various types of action the user may perform.
        JMenuBar menuBar = new JMenuBar();

        // File menus are pretty standard, so things that usually go in File menus go here.
        FileActions fileActions = new FileActions();
        menuBar.add(fileActions.createMenu());
        
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
    @Override
    public void windowClosing(java.awt.event.WindowEvent e) {
        if (target.getImage().hasUnsavedChanges()) {
            int result = JOptionPane.showConfirmDialog(
                target,
                bundle.getString("UNSAVED CHANGES MESSAGE"),
                bundle.getString("UNSAVED CHANGES TITLE"),
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.WARNING_MESSAGE
            );
            if (result == JOptionPane.YES_OPTION) {
                try {
                    target.getImage().save();
                } catch (Exception ex) {}
                System.exit(0);
            } else if (result == JOptionPane.NO_OPTION) {
                System.exit(0);
            }
        } else {
            System.exit(0);
        }
    }
});
        

        // Likewise Edit menus are very common, so should be clear what might go here.
        EditActions editActions = new EditActions();
        menuBar.add(editActions.createMenu());

        // View actions control how the image is displayed, but do not alter its actual content
        ViewActions viewActions = new ViewActions();
        menuBar.add(viewActions.createMenu());

        // Filters apply a per-pixel operation to the image, generally based on a local window
        FilterActions filterActions = new FilterActions();
        menuBar.add(filterActions.createMenu());

        // Actions that affect the representation of colour in the image
        ColourActions colourActions = new ColourActions();
        menuBar.add(colourActions.createMenu());

        // Rotate the images 90 degrees clockwise
        RotateActions rotateActions = new RotateActions();
        menuBar.add(rotateActions.createMenu());

        // Settings to change language, english and german
        SettingAction settingActions = new SettingAction();
        menuBar.add(settingActions.createMenu());
        
        // Draws shapes inside a selection
        DrawingOps drawingActions = new DrawingOps(imagePanel);
        menuBar.add(drawingActions.createMenu());
        
        MacrosAction macrosActions = new MacrosAction();
        menuBar.add(macrosActions.createMenu());

        frame.add(createToolBar(fileActions, editActions, viewActions, rotateActions), BorderLayout.PAGE_START);

        frame.setJMenuBar(menuBar);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * <p>
     * Main entry point to the ANDIE program.
     * </p>
     *
     * <p>
     * Creates and launches the main GUI in a separate thread. As a result, this
     * is essentially a wrapper around {@code createAndShowGUI()}.
     * </p>
     *
     * @param args Command line arguments, not currently used
     * @throws Exception If something goes awry
     * @see #createAndShowGUI()
     */
    public static void main(String[] args) throws Exception {
        javax.swing.SwingUtilities.invokeLater(() -> {
            try {
                createAndShowGUI();
            } catch (Exception ex) {
                ex.printStackTrace();
                System.exit(1);
            }
        });
    }

    private static JToolBar createToolBar(FileActions fileActions, EditActions editActions, ViewActions viewActions, RotateActions rotateActions) {
        JToolBar toolBar = new JToolBar("Tools");

        int shortcut = Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx();

        //open
        addButton(toolBar, "open.png", "Open Image", () -> {
            fileActions.actions.get(0).actionPerformed(null);
        });

        //save
        addButton(toolBar, "save.png", "Save Image", () -> {
            fileActions.actions.get(1).actionPerformed(null);
        });

        //save as
        addButton(toolBar, "saveAs.png", "Save As", () -> {
            fileActions.actions.get(2).actionPerformed(null);
        });

        toolBar.addSeparator();

        //undo
        addButton(toolBar, "undo.png", "undo last action", () -> {
            editActions.actions.get(0).actionPerformed(null);
            target.repaint();
        });

        //redo
        addButton(toolBar, "redo.png", "redo last action", () -> {
            editActions.actions.get(1).actionPerformed(null);
            target.repaint();
        });

        toolBar.addSeparator();

        //crop
        addButton(toolBar, "crop.png", "crop image", () -> {
            editActions.actions.get(5).actionPerformed(null);
            target.repaint();
        });
        
        addButton(toolBar, "resize.png", "resize image", () -> {
            editActions.actions.get(2).actionPerformed(null);
            target.repaint();
        });
        
        toolBar.addSeparator();

        //zoom In
        addButton(toolBar, "zoomIn.png", "zoom in", () -> {
            viewActions.actions.get(0).actionPerformed(null);
            target.repaint();
        });

        //zoomOut
        addButton(toolBar, "zoomOut.png", "zoom in", () -> {
           viewActions.actions.get(1).actionPerformed(null);
            target.repaint();
        });

        //zoom full
        addButton(toolBar, "zoomFull.png", "zoom full", () -> {
            viewActions.actions.get(2).actionPerformed(null);
            target.repaint();
        });

        toolBar.addSeparator();

        //rotate 90cw
        addButton(toolBar, "90cw.png", "rotate 90 CW", () -> {
            rotateActions.actions.get(0).actionPerformed(null);
            target.repaint();
        });

        //rotate 90acw
        addButton(toolBar, "90acw.png", "rotate 90 ACW", () -> {
            rotateActions.actions.get(1).actionPerformed(null);
            target.repaint();
        });

        //180
        addButton(toolBar, "180.png", "rotate 180", () -> {
            rotateActions.actions.get(2).actionPerformed(null);
            target.repaint();
        });

        toolBar.addSeparator();

        //vertical flip
        
        addButton(toolBar, "flipVert.png", "flip vertical", () -> {
            editActions.actions.get(4).actionPerformed(null);
            target.repaint();
        });

        //horizontal flip
        addButton(toolBar, "flipHori.png", "flip horizontal", () -> {
            editActions.actions.get(3).actionPerformed(null);
            target.repaint();
        });

        toolBar.addSeparator();

        return toolBar;
    }

    private static void addButton(JToolBar toolBar, String iconPath, String toolTip, Runnable action) {
        JButton button = new JButton();
        button.setIcon(loadIcon(iconPath));
        button.setToolTipText(toolTip);
        button.addActionListener(e -> action.run());
        toolBar.add(button);
    }

    private static ImageIcon loadIcon(String filename) {
        URL url = Andie.class.getClassLoader().getResource("toolIcons/" + filename);
        if (url != null) {
            ImageIcon icon = new ImageIcon(url);
            Image scaled = icon.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
            return new ImageIcon(scaled);
        }
        return null;
    }

}
