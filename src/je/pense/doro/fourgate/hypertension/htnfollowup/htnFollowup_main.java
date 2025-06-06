package je.pense.doro.fourgate.hypertension.htnfollowup;


import java.awt.BorderLayout;	
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import je.pense.doro.GDSEMR_frame;
import je.pense.doro.fourgate.hypertension.htnMedication.HTN_MedicationFrame;

public class htnFollowup_main extends JFrame {

    private JTextArea notesTextArea;

    public htnFollowup_main() {
        setTitle("Blood Pressure Medication Side Effects");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1200, 700); // Adjust size as needed
        setLayout(new BorderLayout());

        // Create the West Panel (Notes area and buttons)
        JPanel westPanel = createWestPanel();
        westPanel.setPreferredSize(new Dimension(300, 100));

        // Create main content panel (center)
        JPanel mainPanel = createMainPanel();

        // Add components to the main frame
        add(new JScrollPane(mainPanel), BorderLayout.CENTER);
        add(westPanel, BorderLayout.WEST);

        setLocationRelativeTo(null);
    }

      private JPanel createMainPanel() {
          JPanel mainPanel = new JPanel(new GridLayout(0, 2)); // Adjust layout as needed (rows, columns)

        // Loop through all medication types and create a frame for each
        for (htnFollowup_frame.MedicationType type : htnFollowup_frame.MedicationType.values()) {
            htnFollowup_frame medicationFrame = new htnFollowup_frame(type, notesTextArea); //Pass notesTextArea
            mainPanel.add(medicationFrame); // Add JPanel directly to the mainPanel
        }
        return mainPanel;
    }

    private JPanel createWestPanel() {
        JPanel westPanel = new JPanel();
        westPanel.setLayout(new BorderLayout());

        // Notes Text Area
        notesTextArea = new JTextArea(10, 20);
        notesTextArea.setText("<<< Check points for Hypertension >>>\n");
        JScrollPane notesScrollPane = new JScrollPane(notesTextArea);
        notesScrollPane.setBorder(BorderFactory.createTitledBorder("Notes"));

        // Buttons for the text area
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton clearButton = new JButton("Clear");
        JButton saveButton = new JButton("Save");
        JButton quitButton = new JButton("Quit");

        clearButton.addActionListener(e -> notesTextArea.setText("<<< Medication Side Effect >>>\n")); // Clear notes
        saveButton.addActionListener(e -> {
            GDSEMR_frame.setTextAreaText(1, "\n" + notesTextArea.getText());
            // Save the notes to a file or database (Implementation needed)
            JOptionPane.showMessageDialog(this, "Notes saved (Not implemented yet)", "Save", JOptionPane.INFORMATION_MESSAGE);
        });
        quitButton.addActionListener(e -> dispose()); // Close the entire application

        buttonPanel.add(clearButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(quitButton);

        westPanel.add(notesScrollPane, BorderLayout.CENTER);
        westPanel.add(buttonPanel, BorderLayout.SOUTH);

        return westPanel;
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new htnFollowup_main();
            frame.setVisible(true);
        });
    }
}