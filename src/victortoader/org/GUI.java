package victortoader.org;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.SwingUtilities;


/*
 * victortoader.org.GUI.java uses these files:
 *   images/Open16.gif
 *   images/Save16.gif
 */
public class GUI extends JPanel
        implements ActionListener {
    static private final String newline = "\n";
    JButton openButton, uploadButton;
    JTextArea log;
    JFileChooser fc;
    public static String Path = null;

    public GUI() {
        super(new BorderLayout());

        //Create the log first, because the action listeners
        //need to refer to it.
        log = new JTextArea(5, 20);
        log.setMargin(new Insets(5, 5, 5, 5));
        log.setEditable(false);
        JScrollPane logScrollPane = new JScrollPane(log);

        //Create a file chooser
        fc = new JFileChooser();

        //Uncomment one of the following lines to try a different
        //file selection mode.  The first allows just directories
        //to be selected (and, at least in the Java look and feel,
        //shown).  The second allows both files and directories
        //to be selected.  If you leave these lines commented out,
        //then the default mode (FILES_ONLY) will be used.
        //
        //fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        //fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

        //Create the open button.  We use the image from the JLF
        //Graphics Repository (but we extracted it from the jar).
        openButton = new JButton("Choose XML",
                createImageIcon("images/Open16.gif"));
        openButton.addActionListener(this);

        //Create the save button.  We use the image from the JLF
        //Graphics Repository (but we extracted it from the jar).
        uploadButton = new JButton("Upload",
                createImageIcon("images/Save16.gif"));
        uploadButton.addActionListener(this);

        //For layout purposes, put the buttons in a separate panel
        JPanel buttonPanel = new JPanel(); //use FlowLayout
        buttonPanel.add(openButton);
        buttonPanel.add(uploadButton);

        //Add the buttons and the log to this panel.
        add(buttonPanel, BorderLayout.PAGE_START);
        add(logScrollPane, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent e) {
        File file = null;
        //Handle Select XML button action.
        if (e.getSource() == openButton) {
            int returnVal = fc.showOpenDialog(GUI.this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                file = fc.getSelectedFile();
                //This is where a real application would open the file.
                log.append("Selected XML File: " + file.getName() + "." + newline);
            } else {
                log.append("Selecting XML File  command cancelled by user." + newline);
            }
            log.setCaretPosition(log.getDocument().getLength());

            Path = file.getAbsolutePath();
// Debug only: System.out.print("pathul este: " + Path);



            //Handle Upload button action.
        } else if (e.getSource() == uploadButton) {

            try

            {
                User U1 = new User();
                U1.setUserName(Unmarshalling.DoIt(Path).getName());
                U1.setUserMessage(Unmarshalling.DoIt(Path).getMessage());
                JDBCMain.insertRecordIntoDbUserTable(U1);


            } catch (SQLException e2) {
                System.out.println(e2.getMessage());
            }

            file = fc.getSelectedFile();
            //This is where a real application would save the file.
            log.append("Uploading: " + file.getName() + "." + newline);
        } else {
            log.append("Uploading command cancelled by user." + newline);
        }
        log.setCaretPosition(log.getDocument().getLength());
       /* (String) file.getAbsolutePath()*/
        ;
        ;
    }


    /**
     * Returns an ImageIcon, or null if the path was invalid.
     */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = GUI.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    /**
     * Create the victortoader.org.GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("victortoader.org.GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add content to the window.
        frame.add(new GUI());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }


    public static String GetPath(File file) {
        System.out.println("GetPath method: " + file.getAbsolutePath());
        return file.getAbsolutePath();
    }

    public static void main(String[] args) {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's victortoader.org.GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //Turn off metal's use of bold fonts
                UIManager.put("swing.boldMetal", Boolean.FALSE);
                createAndShowGUI();
            }
        });
    }
}