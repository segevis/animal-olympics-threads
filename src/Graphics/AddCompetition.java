package Graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AddCompetition
{
    private String selectedCompetitionType;
    private String runningOrRelayInputByUser;
    private String competitionName;
    Competition currCompetition;

    public AddCompetition() {

        JDialog newDialog = new JDialog((Frame) null, "New Competition", true);
        newDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        newDialog.setSize(700, 100); // Adjusted size for better layout
        newDialog.setLocationRelativeTo(null);
        newDialog.setLayout(new BorderLayout());

        JLabel message = new JLabel("Hello! You have chosen to set up a new competition. Please select your choice.");
        message.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new FlowLayout(FlowLayout.LEFT)); // Align components to the left

        String[] animalTypes = {"Water", "Land", "Air"};
        JComboBox<String> comboBox = new JComboBox<>(animalTypes);
        formPanel.add(comboBox);

        String[] competitionTypes = {"Relay competition", "Running competition"};
        JComboBox<String> comboBox2 = new JComboBox<>(competitionTypes);
        formPanel.add(comboBox2);

        formPanel.add(Box.createVerticalStrut(10)); // Add vertical space between components

        JLabel space = new JLabel("");
        formPanel.add(space);

        JLabel nameLabel = new JLabel("Enter competition name: ");
        JTextField nameField = new JTextField(20);
        formPanel.add(nameLabel);
        formPanel.add(nameField);

        formPanel.add(Box.createVerticalStrut(10)); // Add vertical space before the button

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                selectedCompetitionType = (String) comboBox.getSelectedItem();
                runningOrRelayInputByUser = (String) comboBox2.getSelectedItem();
                competitionName = nameField.getText().trim();

                currCompetition = new Competition(selectedCompetitionType, competitionName, runningOrRelayInputByUser);

                JOptionPane.showMessageDialog(newDialog, "Competition Name: " + competitionName + "\n" + "Animal Type: " + selectedCompetitionType + "\n" + "Competition Type: " + runningOrRelayInputByUser, "Competition Details", JOptionPane.INFORMATION_MESSAGE);

                newDialog.dispose();
            }
        });

        formPanel.add(submitButton);
        newDialog.add(message, BorderLayout.NORTH);
        newDialog.add(formPanel, BorderLayout.CENTER);
        newDialog.setVisible(true);
    }
    public String getSelectedCompetitionType() {return selectedCompetitionType;}
    public String getRunningOrRelayInputByUser() {return runningOrRelayInputByUser;}
    public String getCompetitionName() {return competitionName;}
    public Competition getCurrCompetition() {return currCompetition;}

}
