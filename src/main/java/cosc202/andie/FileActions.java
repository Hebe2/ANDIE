package cosc202.andie;

import static cosc202.andie.EditActions.imageCheck;
import static cosc202.andie.ImageAction.target;
import java.awt.Toolkit;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;
import javax.imageio.*;

/**
 * <p>
 * Actions provided by the File menu.
 * </p>
 *
 * <p>
 * The File menu is very common across applications, and there are several items
 * that the user will expect to find here. Opening and saving files is an
 * obvious one, but also exiting the program.
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
public class FileActions {

    //private static final ResourceBundle bundle = ResourceBundle.getBundle("Bundle");
    private static ResourceBundle bundle = LanguageUtil.getBundle();
    public int shortcut = Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx();
    private JButton recordButton;

    /**
     * A list of actions for the File menu.
     */
    protected ArrayList<Action> actions;

    /**
     * <p>
     * Create a set of File menu actions.
     * </p>
     */
    public FileActions() {
        actions = new ArrayList<>();
        actions.add(new FileOpenAction(bundle.getString("OPEN"), null, bundle.getString("OPEN A FILE"), KeyEvent.VK_O));
        actions.add(new FileSaveAction(bundle.getString("SAVE"), null, bundle.getString("SAVE THE FILE"), KeyEvent.VK_S));
        actions.add(new FileSaveAsAction(bundle.getString("SAVE AS"), null, bundle.getString("SAVE A COPY"), KeyEvent.VK_A));
        actions.add(new FileExitAction(bundle.getString("EXIT"), null, bundle.getString("EXIT THE PROGRAM"), 0));
        actions.add(new FileExportAction(bundle.getString("EXPORT"), null, bundle.getString("EXPORT THE IMAGE"), KeyEvent.VK_E));

    }

    /**
     * <p>
     * Create a menu containing the list of File actions.
     * </p>
     *
     * @return The File menu UI element.
     */
    public JMenu createMenu() {
        JMenu fileMenu = new JMenu(bundle.getString("FILE"));

        for (Action action : actions) {
            fileMenu.add(new JMenuItem(action));
        }

        return fileMenu;
    }

    /**
     * <p>
     * Action to open an image from file.
     * </p>
     *
     * @see EditableImage#open(String)
     */
    public class FileOpenAction extends ImageAction {

        /**
         * <p>
         * Create a new file-open action.
         * </p>
         *
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if
         * null).
         */
        FileOpenAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_O, shortcut));
        }

        /**
         * <p>
         * Callback for when the file-open action is triggered.
         * </p>
         *
         * <p>
         * This method is called whenever the FileOpenAction is triggered. It
         * prompts the user to select a file and opens it as an image.
         * </p>
         *
         * @param e The event triggering this callback.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
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
                    } catch (Exception ex) {
                    }
                } else if (result == JOptionPane.CANCEL_OPTION) {
                    return;
                }
            }
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(target);

            if (result == JFileChooser.APPROVE_OPTION) {
                try {
                    String imageFilepath = fileChooser.getSelectedFile().getCanonicalPath();
                    target.getImage().open(imageFilepath);
                } catch (Exception ex) {
                    System.exit(1);
                }
            }

            target.repaint();
            target.getParent().revalidate();

            if (target.getImage().hasImage()) {
                int recordChoice = JOptionPane.showConfirmDialog(
                        target,
                        "Would you like to record a macro?",
                        "Record Macro",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE
                );

                if (recordChoice == JOptionPane.YES_OPTION) {
                    target.getImage().record();
                    JOptionPane.showMessageDialog(
                            target,
                            "Recording started! Apply filters to record them.",
                            "Recording",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                    if (recordButton != null) {
                        recordButton.setIcon(Andie.loadIcon("recordRed.png"));
                    }
                }
            }
        }

    }

    /**
     * <p>
     * Action to save an image to its current file location.
     * </p>
     *
     * @see EditableImage#save()
     */
    public class FileSaveAction extends ImageAction {

        /**
         * <p>
         * Create a new file-save action.
         * </p>
         *
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if
         * null).
         */
        FileSaveAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S, shortcut));

        }

        /**
         * <p>
         * Callback for when the file-save action is triggered.
         * </p>
         *
         * <p>
         * This method is called whenever the FileSaveAction is triggered. It
         * saves the image to its original filepath.
         * </p>
         *
         * @param e The event triggering this callback.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!imageCheck()) {
                return;
            }
            try {
                target.getImage().save();
            } catch (Exception ex) {
                System.exit(1);
            }
        }

    }

    /**
     * <p>
     * Action to save an image to a new file location.
     * </p>
     *
     * @see EditableImage#saveAs(String)
     */
    public class FileSaveAsAction extends ImageAction {

        /**
         * <p>
         * Create a new file-save-as action.
         * </p>
         *
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if
         * null).
         */
        FileSaveAsAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S, shortcut | InputEvent.SHIFT_DOWN_MASK));
        }

        /**
         * <p>
         * Callback for when the file-save-as action is triggered.
         * </p>
         *
         * <p>
         * This method is called whenever the FileSaveAsAction is triggered. It
         * prompts the user to select a file and saves the image to it.
         * </p>
         *
         * @param e The event triggering this callback.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!imageCheck()) {
                return;
            }
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showSaveDialog(target);

            if (result == JFileChooser.APPROVE_OPTION) {
                try {
                    String imageFilepath = fileChooser.getSelectedFile().getCanonicalPath();
                    target.getImage().saveAs(imageFilepath);
                } catch (Exception ex) {
                    System.exit(1);
                }
            }
        }

    }

    /**
     * <p>
     * Action to quit the ANDIE application.
     * </p>
     */
    public class FileExitAction extends ImageAction {

        /**
         * <p>
         * Create a new file-exit action.
         * </p>
         *
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if
         * null).
         */
        FileExitAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Q, shortcut));

        }

        /**
         * <p>
         * Callback for when the file-exit action is triggered.
         * </p>
         *
         * <p>
         * This method is called whenever the FileExitAction is triggered. It
         * quits the program.
         * </p>
         *
         * @param e The event triggering this callback.
         */
        @Override
        public void actionPerformed(ActionEvent e) {

            if (target.getImage().isRecording()) {
                JOptionPane.showMessageDialog(
                        target,
                        bundle.getString("MACROS STILL RECORDING"),
                        bundle.getString("ERROR"),
                        JOptionPane.INFORMATION_MESSAGE);
                return;
            }
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
                    } catch (Exception ex) {
                    }
                    System.exit(0);
                } else if (result == JOptionPane.NO_OPTION) {
                    System.exit(0);
                }

            } else {
                System.exit(0);
            }
        }

    }

    /**
     * <p>
     * ImageAction to export an image
     * </p>
     */
    public class FileExportAction extends ImageAction {

        /**
         * <p>
         * Create a new file-export action.
         * </p>
         *
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if
         * null).
         */
        FileExportAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_E, shortcut | InputEvent.SHIFT_DOWN_MASK));

        }

        /**
         * <p>
         * Callback for when the file-export action is triggered.
         * </p>
         *
         * <p>
         * This method is called whenever the FileExportAction is triggered. It
         * opens a file chooser that prompts users to select a location and name
         * for their image to save on their computer.
         * </p>
         *
         * @param e The event triggering this callback.
         */
        @Override

        public void actionPerformed(ActionEvent e) {
            if (!imageCheck()) {
                return;
            }

            // Ask for format first
            String[] formats = {"PNG", "JPEG"};
            JComboBox<String> formatDropdown = new JComboBox<>(formats);

            int formatResult = JOptionPane.showConfirmDialog(
                    target,
                    formatDropdown,
                    "Choose Export Format",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE
            );

            if (formatResult == JOptionPane.CANCEL_OPTION || formatResult == JOptionPane.CLOSED_OPTION) {
                return;
            }

            String selectedFormat = (String) formatDropdown.getSelectedItem();
            String ext = selectedFormat.equals("PNG") ? "png" : "jpg";

            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showSaveDialog(target);
            if (result == JFileChooser.APPROVE_OPTION) {
                try {
                    String imageFilepath = fileChooser.getSelectedFile().getCanonicalPath();

                    // Strip existing extension and use chosen one
                    if (imageFilepath.contains(".")) {
                        imageFilepath = imageFilepath.substring(0, imageFilepath.lastIndexOf("."));
                    }
                    imageFilepath += "." + ext;

                    BufferedImage exportImage = target.getImage().getCurrentImage();

                    if (hasTransparentPixels(exportImage)) {
                        int choice = JOptionPane.showConfirmDialog(
                                target,
                                "This image contains transparent pixels. Continue exporting?",
                                "Transparency Warning",
                                JOptionPane.OK_CANCEL_OPTION,
                                JOptionPane.WARNING_MESSAGE
                        );
                        if (choice == JOptionPane.CANCEL_OPTION || choice == JOptionPane.CLOSED_OPTION) {
                            return;
                        }
                    }

                    ImageIO.write(exportImage, ext, new java.io.File(imageFilepath));
                } catch (Exception ex) {
                    System.exit(1);
                }
            }
        }

        private boolean hasTransparentPixels(BufferedImage exportImage) {
            if (exportImage == null) {
                return false;
            }
            for (int y = 0; y < exportImage.getHeight(); y++) {
                for (int x = 0; x < exportImage.getWidth(); x++) {
                    int pixel = exportImage.getRGB(x, y);
                    int alpha = (pixel >> 24) & 0xff;
                    if (alpha < 255) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    public void setRecordButton(JButton button) {
        this.recordButton = button;
    }
}
