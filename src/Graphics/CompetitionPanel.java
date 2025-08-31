package Graphics;

import Animals.Animal;
import Competitions.CourierTournament;
import Competitions.RegularTournament;
import Competitions.Tournament;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CompetitionPanel extends JPanel {

    private AddCompetition addCompetition;
    private AddAnimal addAnimal;
    private List<Animal> animalList = new ArrayList<>();
    private List<Animal> allAnimals = new ArrayList<>();
    private List<Competition> competitionList = new ArrayList<>();
    private BufferedImage backgroundImage;
    private JPanel backgroundPanel;
    private JPanel toolsPanel;
    private int prefWidth = 1024;
    private int prefHeight = 768;
    private CompetitionPanel senderPanelToAddAnimal;
    private int AnimalCounter = 0;
    private boolean ifAddAnimal = false;
    private Object[][] data;
    private String selectedAnimalName;
    private int numericValue;
    private Timer timer;
    //new
    private Tournament tournament;

    public CompetitionPanel() {

        senderPanelToAddAnimal = this;
        data = new Object[animalList.size()][7];

        setLayout(new BorderLayout());

        try {
            backgroundImage = ImageIO.read(new File("graphics2/competitionBackground.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }

                Graphics2D g2d = (Graphics2D) g;
                double scaleX = getWidth() / (double)(prefWidth);
                double scaleY = getHeight() / (double)(prefHeight);
                g2d.scale(scaleX, scaleY);

                if (competitionList != null)
                {
                    for(Competition competition : competitionList)
                    {
                        Animal[][] printAnimals = competition.getAnimalCompetitionArray();

                        if(printAnimals != null)
                        {
                            for (int i = 0; i < printAnimals.length; i++)
                            {
                                for (int j = 0; j < printAnimals[i].length; j++)
                                {
                                    if(printAnimals[i][j] != null)
                                    {
                                        printAnimals[i][j].drawObject(g2d);
                                    }
                                }

                            }

                        }
                    }
                }
            }
        };

        toolsPanel = new JPanel();

        JButton addCompetitionButton = new JButton("Add Competition");
        toolsPanel.add(addCompetitionButton);

        JButton addAnimalButton = new JButton("Add Animal");
        toolsPanel.add(addAnimalButton);

        JButton clearButton = new JButton("Clear");
        toolsPanel.add(clearButton);

        JButton eatButton = new JButton("Eat");
        toolsPanel.add(eatButton);

        JButton infoButton = new JButton("Info");
        toolsPanel.add(infoButton);

        JButton exitButton = new JButton("Exit");
        toolsPanel.add(exitButton);

        JButton playButton = new JButton("Play");
        toolsPanel.add(playButton);

        JButton AnimalArrayButton = new JButton("Score");
        toolsPanel.add(AnimalArrayButton);

        JButton PlayThreadButton = new JButton("Play Thread");
        toolsPanel.add(PlayThreadButton);

        add(backgroundPanel, BorderLayout.CENTER);
        add(toolsPanel, BorderLayout.SOUTH);

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (animalList.size() > 0) {
                    Animal tempAnimal = animalList.get(AnimalCounter - 1);
                    animalList.remove(tempAnimal);
                    AnimalCounter--;
                    repaint();
                } else {
                    JOptionPane.showMessageDialog(CompetitionPanel.this, "No Animal To Remove", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        addCompetitionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addCompetition = new AddCompetition();
                competitionList.add(addCompetition.getCurrCompetition());
                System.out.println(competitionList.size() + " competitions added" );
            }
        });
        addAnimalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (addCompetition == null || addCompetition.getSelectedCompetitionType() == null)
                {
                    JOptionPane.showMessageDialog(CompetitionPanel.this, "Please select a competition type first.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    addAnimal = new AddAnimal(addCompetition, senderPanelToAddAnimal);
                    animalList.add(addAnimal.currAnimal);
                    allAnimals.add(addAnimal.currAnimal);// Add animal to allAnimals list
                    ifAddAnimal = true;
                    AnimalCounter++;

                    backgroundPanel.revalidate();
                    backgroundPanel.repaint();
                }
            }
        });
        infoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ifAddAnimal) {
                    showAnimalInfoDialog();
                } else {
                    JOptionPane.showMessageDialog(CompetitionPanel.this, "Please Add Animals, Empty Table", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        eatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShowAnimaEatDialog();
            }
        });
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                timer = new Timer(30, new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {

                        if (competitionList != null)
                        {
                            for(Competition competition : competitionList)
                            {
                                Animal[][] printAnimals = competition.getAnimalCompetitionArray();

                                for (int i = 0; i < printAnimals.length; i++)
                                {
                                    for (int j = 0; j < printAnimals[i].length; j++)
                                    {
                                        if(printAnimals[i][j] != null)
                                        {
                                            printAnimals[i][j].move();
                                        }
                                    }

                                }
                            }
                        }
                        backgroundPanel.repaint();
                    }
                });
                timer.start();
            }
        });
        AnimalArrayButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                displayCompetitionArrays();
            }
        });
        PlayThreadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playTournament();
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
    private void showAnimalInfoDialog() {
        // Refresh data from allAnimals before showing the dialog
        data = convertListTo2DArray(allAnimals.toArray(new Animal[0]));

        JDialog infoDialog = new JDialog();
        infoDialog.setTitle("Animal Information");
        infoDialog.setSize(500, 300);
        infoDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        infoDialog.setLocationRelativeTo(this);

        // Create JTable with updated data
        String[] columnNames = {"Name", "Category", "Type", "Speed", "Energy Amount", "Distance", "Energy"};
        JTable infoTable = new JTable(data, columnNames);

        // Create JScrollPane for the JTable
        JScrollPane infoScroller = new JScrollPane(infoTable);
        infoDialog.add(infoScroller);
        infoDialog.setVisible(true);
    }
    private Object[][] convertListTo2DArray(Animal[] animals) {
        if (animals == null || animals.length == 0) {
            return new Object[0][];
        }

        Object[][] data = new Object[animals.length][7];

        for (int i = 0; i < animals.length; i++) {
            Animal animal = animals[i];

            if (animal != null) {
                data[i][0] = animal.getName();
                data[i][1] = animal.getCategory(animal);
                data[i][2] = animal.getType(animal);
                data[i][3] = animal.getSpeed();
                data[i][4] = animal.getEnergyPerMeter();
                data[i][5] = animal.getTotalDistance();
                data[i][6] = animal.getEnergy();
            } else {
                data[i][0] = "N/A";
                data[i][1] = "N/A";
                data[i][2] = "N/A";
                data[i][3] = "N/A";
                data[i][4] = "N/A";
                data[i][5] = "N/A";
                data[i][6] = "N/A";
            }
        }
        return data;
    }
    public BufferedImage getBackgroundImage() {
        return backgroundImage;
    }
    private void updateAnimalInfoDialog() {
        // Refresh data from allAnimals
        data = convertListTo2DArray(allAnimals.toArray(new Animal[0]));

        // Create JTable with updated data
        String[] columnNames = {"Name", "Category", "Type", "Speed", "Energy Amount", "Distance", "Energy Consumption"};
        JTable infoTable = new JTable(data, columnNames);

        // Create JScrollPane for the JTable
        JScrollPane infoScroller = new JScrollPane(infoTable);

        // Create a new JDialog
        JDialog infoDialog = new JDialog();
        infoDialog.setTitle("Animal Information");
        infoDialog.setSize(500, 300);
        infoDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        infoDialog.setLocationRelativeTo(this);
        infoDialog.add(infoScroller);
        infoDialog.setVisible(true);
    }
    public void ShowAnimaEatDialog() {
        if (animalList != null && !animalList.isEmpty()) {
            JDialog newDialog = new JDialog((Frame) null, "Animal Energy", true);
            newDialog.setSize(500, 300);
            newDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            newDialog.setLocationRelativeTo(this);
            newDialog.setResizable(false);

            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(new BorderLayout());
            mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

            JLabel label = new JLabel("Which animal do you want to feed?");
            label.setHorizontalAlignment(SwingConstants.CENTER);
            mainPanel.add(label, BorderLayout.NORTH);

            JPanel inputPanel = new JPanel();
            inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
            inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            List<String> animalNames = new ArrayList<>();
            for (Animal animal : animalList) {
                animalNames.add(animal.getName());
            }

            JComboBox<String> animalComboBox = new JComboBox<>(animalNames.toArray(new String[0]));
            animalComboBox.setSelectedIndex(-1); // No selection by default
            animalComboBox.setPreferredSize(new Dimension(200, 30)); // Set width to desired value

            inputPanel.add(animalComboBox);

            JTextField numericField = new JTextField();
            numericField.setPreferredSize(new Dimension(200, 30)); // Set width to desired value
            numericField.setToolTipText("Enter a numeric value");

            inputPanel.add(new JLabel("Enter numeric value:"));
            inputPanel.add(numericField);

            mainPanel.add(inputPanel, BorderLayout.CENTER);

            JButton submitButton = new JButton("Submit");
            mainPanel.add(submitButton, BorderLayout.SOUTH);

            submitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String selectedAnimalName = (String) animalComboBox.getSelectedItem();
                    String numericInput = numericField.getText();
                    if (selectedAnimalName != null) {
                        try {
                            int energyToAdd = Integer.parseInt(numericInput); // Parse the numeric value
                            for (Animal animal : animalList) {
                                if (animal.getName().equals(selectedAnimalName)) {
                                    animal.addEnergy(energyToAdd); // Add the energy to the animal
                                    break;
                                }
                            }
                            JOptionPane.showMessageDialog(newDialog,
                                    "You selected: " + selectedAnimalName + "\nNumeric value added: " + energyToAdd,
                                    "Selection",
                                    JOptionPane.INFORMATION_MESSAGE);

                            newDialog.dispose(); // Close the dialog after selection

                            updateAnimalInfoDialog(); // Show updated animal information

                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(newDialog,
                                    "Please enter a valid numeric value.",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(newDialog,
                                "Please select an animal.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            newDialog.add(mainPanel);
            newDialog.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "No animals available.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public List<Competition> getCompetitionList() {return competitionList;}



    public void displayCompetitionArrays() {
        if (competitionList.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No competitions available.", "Information", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // Create a dialog to display the data
        JDialog dataDialog = new JDialog((Frame) null, "Competition Data", true);
        dataDialog.setSize(800, 600);
        dataDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dataDialog.setLocationRelativeTo(this);

        // Create a panel for the dialog
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Create a JTable to display the data
        String[] columnNames = {"Competition Name", "Type", "Description", "Animal Index (Row, Col)", "Animal Name", "Animal Category", "Animal Type", "Animal Speed", "Animal Energy Amount", "Animal Distance", "Animal Energy"};
        Object[][] data = gatherCompetitionData();

        JTable dataTable = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(dataTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Add a button to close the dialog
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> dataDialog.dispose());
        panel.add(closeButton, BorderLayout.SOUTH);

        dataDialog.add(panel);
        dataDialog.setVisible(true);
    }

    private Object[][] gatherCompetitionData() {
        List<Object[]> dataList = new ArrayList<>();

        for (Competition competition : competitionList) {
            System.out.println("Competition: " + competition.getCompetitionName());

            if (competition.isAnimalArrayExist()) {
                Animal[][] animals = competition.getAnimalCompetitionArray();
                for (int row = 0; row < animals.length; row++) {
                    for (int col = 0; col < animals[row].length; col++) {
                        Animal animal = animals[row][col];
                        if (animal != null) {
                            System.out.println("Animal [" + row + "][" + col + "]: " + animal.getName());
                            dataList.add(new Object[]{
                                    competition.getCompetitionName(),
                                    competition.getCompetitionType(),
                                    competition.getCompetitionDescription(),
                                    "[" + row + ", " + col + "]",
                                    animal.getName(),
                                    animal.getCategory(animal),
                                    animal.getType(animal),
                                    animal.getSpeed(),
                                    animal.getEnergyPerMeter(),
                                    animal.getTotalDistance(),
                                    animal.getEnergy()
                            });
                        }
                    }
                }
            } else {
                dataList.add(new Object[]{
                        competition.getCompetitionName(),
                        competition.getCompetitionType(),
                        competition.getCompetitionDescription(),
                        "N/A",
                        "N/A",
                        "N/A",
                        "N/A",
                        "N/A",
                        "N/A",
                        "N/A",
                        "N/A"
                });
            }
        }

        System.out.println("Data Size: " + dataList.size());
        return dataList.toArray(new Object[0][]);
    }

    public void playTournament() {

        for (Competition competition : competitionList) {

            Animal[][] animals = competition.getAnimalCompetitionArray();

            if (animals != null && animals.length > 0) {
                Tournament tournament;

                if (competition.isRelayCompetition())
                {
                    tournament = new CourierTournament(animals,senderPanelToAddAnimal);
                }
                else if (competition.isRegularCompetition())
                {
                    tournament = new RegularTournament(animals,senderPanelToAddAnimal);
                }
                else
                {
                    JOptionPane.showMessageDialog(this, "Invalid competition type.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            else
            {
                JOptionPane.showMessageDialog(this, "No animals available for the tournament.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public String getCompetitionType() {
        return competitionList.getLast().getCompetitionType();
    }
}


