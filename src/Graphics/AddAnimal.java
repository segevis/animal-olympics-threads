package Graphics;

import Animals.*;
import Mobility.Point;
import Olympics.Medal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


public class AddAnimal extends Component {
    public Animal currAnimal; // Changed from array to ArrayList
    private AddCompetition addCompetition;
    private CompetitionPanel competitionPanelSendToAnimal;
    private Competition selectedCompetition;
    private boolean[] WaterOccupiedRoutes;
    private boolean[] AirOccupiedRoutes;

    public AddAnimal(AddCompetition addCompetition,CompetitionPanel competitionPanel) {
        selectedCompetition = null;
        this.competitionPanelSendToAnimal = competitionPanel; // Store the reference here
        this.addCompetition = addCompetition;
        WaterOccupiedRoutes = new boolean[4];
        AirOccupiedRoutes = new boolean[5];
        addAnimal();
    }

    public void addAnimal() {

        JDialog newDialog = new JDialog((Frame) null, "Animal dialog", true);
        newDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        newDialog.setSize(400, 150); // Adjusted size for a single combo box
        newDialog.setLocationRelativeTo(null);
        newDialog.setLayout(new BorderLayout());

        JLabel message = new JLabel("Choose an option from the list");
        message.setHorizontalAlignment(SwingConstants.CENTER);
        newDialog.add(message, BorderLayout.NORTH);

        JPanel comboBoxPanel = new JPanel();
        comboBoxPanel.setLayout(new FlowLayout()); // Center the combo box in the panel

        List<Competition> competitions = competitionPanelSendToAnimal.getCompetitionList(); // Get list of competitions
        String[] competitionNames = competitions.stream().map(Competition::getCompetitionName).toArray(String[]::new); // Extract competition names
        JComboBox<String> competitionComboBox = new JComboBox<>(competitionNames);
        comboBoxPanel.add(new JLabel("Choose competition:")); // Label for the combo box
        comboBoxPanel.add(competitionComboBox); // Add the combo box to the panel

        newDialog.add(comboBoxPanel, BorderLayout.CENTER);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String selectedCompetition = (String) competitionComboBox.getSelectedItem();

                if (selectedCompetition != null)
                {
                    creatArrayForList(selectedCompetition, competitions);
                }
                else
                {
                    JOptionPane.showMessageDialog(newDialog, "Please make a selection.", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        newDialog.add(submitButton, BorderLayout.SOUTH);
        newDialog.setVisible(true);
    }

    public Animal createWaterAnimal(String animalType) {
        final Animal[] tempAnimal = {null};
        final int[] waterRoutUsed = new int[1];

        JLabel routWaterLabel = new JLabel("Water routing");
        String[] routWater = {"1", "2", "3", "4"};
        JComboBox<String> animalWaterRoutComboBox = new JComboBox<>(routWater);

        JDialog newDialog = new JDialog((Frame) null, "Create Water Animal", true);
        newDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        newDialog.setSize(600, 600);
        newDialog.setLocationRelativeTo(null);
        newDialog.setLayout(new GridLayout(0, 2));

        JTextField nameField = new JTextField();
        JTextField weightField = new JTextField();
        JTextField speedField = new JTextField();
        JTextField additionalField = new JTextField();
        JComboBox<String> genderComboBox = new JComboBox<>(new String[]{"MALE", "FEMALE"});

        newDialog.add(new JLabel("Name:"));
        newDialog.add(nameField);
        newDialog.add(new JLabel("Weight:"));
        newDialog.add(weightField);
        newDialog.add(new JLabel("Speed:"));
        newDialog.add(speedField);
        newDialog.add(new JLabel("Gender:"));
        newDialog.add(genderComboBox);
        newDialog.add(routWaterLabel);
        newDialog.add(animalWaterRoutComboBox);

        switch (animalType) {
            case "Alligator":
                newDialog.add(new JLabel("Area of Living:"));
                newDialog.add(additionalField);
                break;
            case "Whale":
                newDialog.add(new JLabel("Food Type:"));
                newDialog.add(additionalField);
                break;
            case "Dolphin":
                newDialog.add(new JLabel("Water Type (SEA/SWEET):"));
                newDialog.add(additionalField);
                break;
        }

        JButton submitButton = new JButton("Submit");
        newDialog.add(new JLabel()); // Empty cell
        newDialog.add(submitButton);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = nameField.getText();
                    double weight = Double.parseDouble(weightField.getText());
                    double speed = Double.parseDouble(speedField.getText());
                    Animal.Gender gender = Animal.Gender.valueOf((String) genderComboBox.getSelectedItem());
                    Point location = new Point(50, 0); // Default location for water animals
                    double totalDistance = 0; // Initial total distance traveled
                    Medal[] medals = new Medal[0]; // No medals initially
                    int MAX_DIVE = -800; // Example max dive depth
                    double diveDepth = 0;

                    boolean validRouteSelected = false;
                    while (!validRouteSelected) {
                        String rout = animalWaterRoutComboBox.getSelectedItem().toString();
                        int waterRout = Integer.parseInt(rout);

                        if (WaterOccupiedRoutes[waterRout - 1]) {
                            JOptionPane.showMessageDialog(newDialog, "This water route is already occupied. Please select a different route.", "Input Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        } else {
                            validRouteSelected = true;
                            waterRoutUsed[0] = waterRout;
                            Point loc = getWaterPoint(waterRout);
                            switch (animalType) {
                                case "Alligator":
                                    String areaOfLiving = additionalField.getText();
                                    tempAnimal[0] = new Alligator(areaOfLiving, name, weight, speed, medals, loc, totalDistance, gender, MAX_DIVE, diveDepth, 4, competitionPanelSendToAnimal);
                                    break;
                                case "Whale":
                                    String foodType = additionalField.getText();
                                    tempAnimal[0] = new Whale(foodType, name, weight, speed, medals, loc, totalDistance, gender, MAX_DIVE, diveDepth, competitionPanelSendToAnimal);
                                    break;
                                case "Dolphin":
                                    String water = additionalField.getText();
                                    Dolphin.waterType waterType = Dolphin.waterType.valueOf(water.toUpperCase());
                                    tempAnimal[0] = new Dolphin(waterType, name, weight, speed, medals, loc, totalDistance, gender, MAX_DIVE, diveDepth, competitionPanelSendToAnimal);
                                    break;
                                default:
                                    throw new IllegalArgumentException("Invalid animal type: " + animalType);
                            }

                            // Update the array to mark the route as occupied
                            WaterOccupiedRoutes[waterRout - 1] = true;
                            newDialog.dispose();
                        }
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(newDialog, "Please enter valid numeric values.", "Input Error", JOptionPane.ERROR_MESSAGE);
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(newDialog, ex.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        newDialog.setVisible(true);
        return tempAnimal[0];
    }

    public Animal createLandAnimal(String animalType) {

        final Animal[] tempAnimal = {null};

        JDialog newDialog = new JDialog((Frame) null, "Create Land Animal", true);
        newDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        newDialog.setSize(600, 600);
        newDialog.setLocationRelativeTo(null);
        newDialog.setLayout(new GridLayout(0, 2));

        JTextField nameField = new JTextField();
        JTextField weightField = new JTextField();
        JTextField speedField = new JTextField();
        JTextField additionalField = new JTextField(); // For additional info
        JTextField poisonousField = new JTextField(); // For poisonous level
        JComboBox<String> genderComboBox = new JComboBox<>(new String[]{"MALE", "FEMALE"});

        newDialog.add(new JLabel("Name:"));
        newDialog.add(nameField);
        newDialog.add(new JLabel("Weight:"));
        newDialog.add(weightField);
        newDialog.add(new JLabel("Speed:"));
        newDialog.add(speedField);
        newDialog.add(new JLabel("Gender:"));
        newDialog.add(genderComboBox);

        switch (animalType) {
            case "Dog":
                newDialog.add(new JLabel("Breed:"));
                newDialog.add(additionalField);
                break;
            case "Cat":
                newDialog.add(new JLabel("Castrated (true/false):"));
                newDialog.add(additionalField);
                break;
            case "Snake":
                newDialog.add(new JLabel("Length:"));
                newDialog.add(additionalField);
                newDialog.add(new JLabel("Poisonous (HIGH/MID/LOW):"));
                newDialog.add(poisonousField);
                break;
        }

        JButton submitButton = new JButton("Submit");
        newDialog.add(new JLabel());  // Empty cell
        newDialog.add(submitButton);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = nameField.getText();
                    double weight = Double.parseDouble(weightField.getText());
                    double speed = Double.parseDouble(speedField.getText());
                    Animal.Gender gender = Animal.Gender.valueOf((String) genderComboBox.getSelectedItem());
                    Point location = new Point(0, 0);  // Default location for land animals
                    double totalDistance = 0;
                    Medal[] medals = new Medal[0];

                    switch (animalType) {
                        case "Dog":
                            String breed = additionalField.getText();
                            currAnimal = new Dog(breed, name, weight, speed, medals, location, totalDistance, gender, 4, competitionPanelSendToAnimal);
                            tempAnimal[0] = currAnimal;
                            break;
                        case "Cat":
                            boolean castrated = Boolean.parseBoolean(additionalField.getText());
                            currAnimal = new Cat(castrated, name, weight, speed, medals, location, totalDistance, gender, 4 , competitionPanelSendToAnimal);
                            tempAnimal[0] = currAnimal;
                            break;
                        case "Snake":
                            double length = Double.parseDouble(additionalField.getText());
                            String poisonousStr = poisonousField.getText();
                            Snake.Poisonous poisonousType = Snake.Poisonous.valueOf(poisonousStr.toUpperCase());
                            currAnimal = new Snake(length, poisonousType, name, weight, speed, medals, location, location, totalDistance, gender, 0, competitionPanelSendToAnimal);
                            tempAnimal[0] = currAnimal;
                            break;
                    }

                    newDialog.dispose();
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(newDialog, "Please enter valid numeric values.", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        newDialog.setVisible(true);
        return tempAnimal[0];
    }

    public Animal createAirAnimal(String animalType) {
        final Animal[] tempAnimal = {null};

        JLabel routAirLabel = new JLabel("Air routing");
        String[] routAir = {"1", "2", "3", "4", "5"};
        JComboBox<String> animalAirRoutComboBox = new JComboBox<>(routAir);

        JDialog newDialog = new JDialog((Frame) null, "Create Air Animal", true);
        newDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        newDialog.setSize(600, 600);
        newDialog.setLocationRelativeTo(null);
        newDialog.setLayout(new GridLayout(0, 2));

        JTextField nameField = new JTextField();
        JTextField weightField = new JTextField();
        JTextField speedField = new JTextField();
        JTextField additionalField = new JTextField();
        JTextField wingspanField = new JTextField(); // Create a JTextField for wingspan
        JComboBox<String> genderComboBox = new JComboBox<>(new String[]{"MALE", "FEMALE"});

        newDialog.add(new JLabel("Name:"));
        newDialog.add(nameField);
        newDialog.add(new JLabel("Weight:"));
        newDialog.add(weightField);
        newDialog.add(new JLabel("Speed:"));
        newDialog.add(speedField);
        newDialog.add(new JLabel("Gender:"));
        newDialog.add(genderComboBox);
        newDialog.add(routAirLabel);
        newDialog.add(animalAirRoutComboBox);

        switch (animalType) {
            case "Eagle":
                newDialog.add(new JLabel("Altitude of Flight:"));
                newDialog.add(additionalField);
                newDialog.add(new JLabel("Wingspan:"));
                newDialog.add(wingspanField); // Add wingspanField to the dialog
                break;
            case "Pigeon":
                newDialog.add(new JLabel("Family:"));
                newDialog.add(additionalField);
                newDialog.add(new JLabel("Wingspan:"));
                newDialog.add(wingspanField); // Add wingspanField to the dialog
                break;
        }

        JButton submitButton = new JButton("Submit");
        newDialog.add(new JLabel()); // Empty cell
        newDialog.add(submitButton);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = nameField.getText();
                    double weight = Double.parseDouble(weightField.getText());
                    double speed = Double.parseDouble(speedField.getText());
                    Animal.Gender gender = Animal.Gender.valueOf((String) genderComboBox.getSelectedItem());
                    double totalDistance = 0;
                    Medal[] medals = new Medal[0];
                    double wingspan = Double.parseDouble(wingspanField.getText());

                    boolean validRouteSelected = false;
                    while (!validRouteSelected) {
                        String rout = animalAirRoutComboBox.getSelectedItem().toString();
                        int airRout = Integer.parseInt(rout);


                        if (AirOccupiedRoutes[airRout - 1]) {
                            JOptionPane.showMessageDialog(newDialog, "This air route is already occupied. Please select a different route.", "Input Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        else
                        {
                            validRouteSelected = true;
                            Point loc = getAirPoint(airRout);

                            switch (animalType) {
                                case "Eagle":
                                    double altitudeOfFlight = Double.parseDouble(additionalField.getText());
                                    tempAnimal[0] = new Eagle(altitudeOfFlight, name, weight, speed, medals, loc, totalDistance, gender, wingspan, competitionPanelSendToAnimal);
                                    break;
                                case "Pigeon":
                                    String family = additionalField.getText();
                                    tempAnimal[0] = new Pigeon(family, name, weight, speed, medals, loc, totalDistance, gender, wingspan, competitionPanelSendToAnimal);
                                    break;
                                default:
                                    throw new IllegalArgumentException("Invalid animal type: " + animalType);
                            }

                            // Update the array to mark the route as occupied
                            AirOccupiedRoutes[airRout - 1] = true;
                            newDialog.dispose();
                        }
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(newDialog, "Please enter valid numeric values.", "Input Error", JOptionPane.ERROR_MESSAGE);
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(newDialog, ex.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        newDialog.setVisible(true);
        return tempAnimal[0];
    }

    public void errorMessage(String message) {

        JDialog errorDialog = new JDialog((Frame) null, "Error", true);
        errorDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        errorDialog.setSize(600, 200);
        errorDialog.setLocationRelativeTo(null);
        errorDialog.setLayout(new BorderLayout());

        JLabel errorLabel = new JLabel("Error: " + message, SwingConstants.CENTER);
        errorLabel.setFont(new Font("Arial", Font.BOLD, 16));
        errorDialog.add(errorLabel, BorderLayout.CENTER);

        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                errorDialog.dispose();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton);
        errorDialog.add(buttonPanel, BorderLayout.SOUTH);

        errorDialog.setVisible(true);
    }

    public Point getWaterPoint(int choice) {


        BufferedImage img = competitionPanelSendToAnimal.getBackgroundImage();

        int imgY = img.getHeight();
        int imgX = img.getWidth();

        // Calculate common divisor for X coordinate using integer division
        int xCoord = (int)(imgX / 14.5);

        switch (choice) {
            case 1:
                return new Point(xCoord, (int)(imgY / 8));
            case 2:
                return new Point(xCoord, (int)(imgY / 3));
            case 3:
                return new Point(xCoord, (int)((imgY / 3) + (imgY / 5)));
            case 4:
                return new Point(xCoord, (int)((imgY / 3) + (imgY / 3) + (imgY / 13)));
            default:
                // Log an error or throw an exception if choice is invalid
                System.err.println("Invalid choice: " + choice);
                return null;
        }
    }

    public Point getAirPoint(int choice) {


        BufferedImage img = competitionPanelSendToAnimal.getBackgroundImage();

        int imgY = img.getHeight();
        int xCoord = 0;

        switch (choice) {
            case 1:
                return new Point(xCoord, 0);
            case 2:
                return new Point(xCoord, (int)(imgY/8 + imgY/10));
            case 3:
                return new Point(xCoord, (int)((imgY/3) + (imgY/10)));
            case 4:
                return new Point(xCoord, (int)((imgY/2) + (imgY/7)));
            case 5:
                return new Point(xCoord, (int)((imgY/2) + (imgY/3)));
            default:
                System.err.println("Invalid choice: " + choice);
                return null;
        }
    }

    public void creatArrayForList(String selectedCompetitionName, List<Competition> competitions) {

        for (Competition competition : competitions) {
            if (competition.getCompetitionName().equals(selectedCompetitionName))
            {
                selectedCompetition = competition;
                break;
            }
        }

        if (selectedCompetition.getAnimalCompetitionArray() != null && selectedCompetition.getArraySize() > 0) {
            System.out.println("Animal array has already been created for this competition.");
            errorMessage("Animal array has already been created for this competition.");
            return;
        }

        if (selectedCompetition.getCompetitionDescription().equals("Running competition")) {
            if(selectedCompetition.getCompetitionType().equals("Water")){
                String title = "Water Running Competition";
                String[] options = {"1", "2", "3", "4"};
                int numberOfAnimals = selectNumberOfAnimals(options,title,selectedCompetitionName);
                selectedCompetition.setAnimalArraySize(numberOfAnimals,1);
                String[] animalOptions = {"Alligator", "Whale", "Dolphin"};

                for (int i = 0; i < numberOfAnimals; i++)
                {
                    String remainingText = "Animals left to create: " + (numberOfAnimals - i);
                    String selectedAnimalType = (String) JOptionPane.showInputDialog(null, remainingText + "\nSelect animal type:", "Select Water Animal", JOptionPane.PLAIN_MESSAGE, null, animalOptions, animalOptions[0]);

                    if (selectedAnimalType != null && !selectedAnimalType.isEmpty())
                    {
                        Animal tempAnimal = createWaterAnimal(selectedAnimalType);
                        selectedCompetition.addAnimal(i,0,tempAnimal);
                    }
                }
            }
            else if (selectedCompetition.getCompetitionType().equals("Air")) {
                String title = "Air Running Competition";
                String[] options = {"1", "2", "3", "4" , "5"};
                int numberOfAnimals = selectNumberOfAnimals(options, title, selectedCompetitionName);
                selectedCompetition.setAnimalArraySize(numberOfAnimals,1);
                String[] animalOptions = {"Eagle", "Pigeon"};

                for (int i = 0; i < numberOfAnimals; i++)
                {
                    String remainingText = "Animals left to create: " + (numberOfAnimals - i);
                    String selectedAnimalType = (String) JOptionPane.showInputDialog(null, remainingText + "\nSelect animal type:", "Select Air Animal", JOptionPane.PLAIN_MESSAGE, null, animalOptions, animalOptions[0]);

                    if (selectedAnimalType != null && !selectedAnimalType.isEmpty())
                    {
                        Animal tempAnimal = createAirAnimal(selectedAnimalType);
                        selectedCompetition.addAnimal(i, 0,tempAnimal);
                    }
                }
            }
            else if (selectedCompetition.getCompetitionType().equals("Land")) {
                String title = "Land Running Competition";
                String[] options = {"1", "2", "3", "4"};
                int numberOfAnimals = selectNumberOfAnimals(options, title, selectedCompetitionName);
                selectedCompetition.setAnimalArraySize(numberOfAnimals,1);
                String[] animalOptions = {"Cat", "Dog", "Snake"};

                for (int i = 0; i < numberOfAnimals; i++)
                {
                    String remainingText = "Animals left to create: " + (numberOfAnimals - i);
                    String selectedAnimalType = (String) JOptionPane.showInputDialog(null, remainingText + "\nSelect animal type:", "Select Land Animal", JOptionPane.PLAIN_MESSAGE, null, animalOptions, animalOptions[0]);

                    if (selectedAnimalType != null && !selectedAnimalType.isEmpty())
                    {
                        Animal tempAnimal = createLandAnimal(selectedAnimalType);
                        selectedCompetition.addAnimal(i, 0 ,tempAnimal);
                    }
                }
            }
        }
        else if (selectedCompetition.getCompetitionDescription().equals("Relay competition")) {
            if (selectedCompetition.getCompetitionType().equals("Water")) {
                String title = "Relay Running Competition";
                String title2 = "Enter number of animals per group";
                String[] options = {"1", "2", "3", "4"};
                String[] animalOptions = {"Alligator", "Whale", "Dolphin"};

                // For selecting the number of groups
                int groupSize = selectNumberOfItems(options, "Select Number of Groups", "Select the number of groups");
                // For selecting the number of animals per group
                int numberOfAnimalsPerGroup = selectNumberOfItems(options, title2, "Select the number of animals per group");
                // Set the size of the animal array in the competition
                selectedCompetition.setAnimalArraySize(groupSize * numberOfAnimalsPerGroup, numberOfAnimalsPerGroup);

                // Animal array should be initialized for the relay
                Animal[][] animalGroups = new Animal[groupSize][numberOfAnimalsPerGroup];

                // Loop through each group
                for (int i = 0; i < groupSize; i++) {

                    // Loop through each animal in the group
                    for (int j = 0; j < numberOfAnimalsPerGroup; j++) {
                        // Show a dialog to select an animal type
                        String selectedAnimal = (String) JOptionPane.showInputDialog(
                                this,
                                "Select animal for position " + (j + 1) + " in group " + (i + 1),
                                "Animal Selection",
                                JOptionPane.PLAIN_MESSAGE,
                                null,
                                animalOptions,
                                animalOptions[0]
                        );

                        // Validate selection
                        if (selectedAnimal != null) {
                            // Create an animal based on the selection
                            Animal animal;
                            switch (selectedAnimal) {
                                case "Alligator":
                                    animal = createWaterAnimal("Alligator");
                                    break;
                                case "Whale":
                                    animal = createWaterAnimal("Whale");
                                    break;
                                case "Dolphin":
                                    animal = createWaterAnimal("Dolphin");
                                    break;
                                default:
                                    animal = null;
                                    break;
                            }

                            if (animal != null) {
                                animalGroups[i][j] = animal;
                                selectedCompetition.addAnimal(i, j, animal);
                            } else {
                                JOptionPane.showMessageDialog(this, "Error creating animal. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                                return;
                            }
                        } else {
                            // User cancelled the selection
                            JOptionPane.showMessageDialog(this, "Animal selection was cancelled.", "Cancelled", JOptionPane.WARNING_MESSAGE);
                            return;
                        }
                    }
                    // Set the locations for the animals in the current group
                    SetAnimalLocationRelay locationRelay = new SetAnimalLocationRelay(animalGroups[i], competitionPanelSendToAnimal, numberOfAnimalsPerGroup);
//                    SetAnimalLocationRelay locationRelay2 = new SetAnimalLocationRelay(selectedCompetition.getAnimalCompetitionArray()[numberOfAnimalsPerGroup], competitionPanelSendToAnimal, numberOfAnimalsPerGroup);
                }

                // Notify the user that the competition setup is complete
                JOptionPane.showMessageDialog(this, "Relay competition setup complete.", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
            else if (selectedCompetition.getCompetitionType().equals("Land")) {

                String title = "Relay Running Competition";
                String title2 = "Enter number of animals per group";
                String[] options = {"1", "2", "3", "4"};
                String[] animalOptions = {"Dog", "Cat", "Snake"};

                // For selecting the number of groups
                int groupSize = selectNumberOfItems(options, "Select Number of Groups", "Select the number of groups");
                // For selecting the number of animals per group
                int numberOfAnimalsPerGroup = selectNumberOfItems(options, title2, "Select the number of animals per group");
                // Set the size of the animal array in the competition
                selectedCompetition.setAnimalArraySize(groupSize * numberOfAnimalsPerGroup, numberOfAnimalsPerGroup);

                // Animal array should be initialized for the relay
                Animal[][] animalGroups = new Animal[groupSize][numberOfAnimalsPerGroup];

                // Loop through each group
                for (int i = 0; i < groupSize; i++) {
                    // Loop through each animal in the group
                    for (int j = 0; j < numberOfAnimalsPerGroup; j++) {
                        // Show a dialog to select an animal type
                        String selectedAnimal = (String) JOptionPane.showInputDialog(
                                this,
                                "Select animal for position " + (j + 1) + " in group " + (i + 1),
                                "Animal Selection",
                                JOptionPane.PLAIN_MESSAGE,
                                null,
                                animalOptions,
                                animalOptions[0]
                        );

                        // Validate selection
                        if (selectedAnimal != null) {
                            // Create an animal based on the selection
                            Animal animal;
                            switch (selectedAnimal) {
                                case "Dog":
                                    animal = createLandAnimal("Dog");
                                    break;
                                case "Cat":
                                    animal = createLandAnimal("Cat");
                                    break;
                                case "Snake":
                                    animal = createLandAnimal("Snake");
                                    break;
                                default:
                                    animal = null;
                                    break;
                            }

                            if (animal != null) {
                                animalGroups[i][j] = animal;
                                selectedCompetition.addAnimal(i, j, animal);
                            } else {
                                JOptionPane.showMessageDialog(this, "Error creating animal. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                                return;
                            }
                        } else {
                            // User cancelled the selection
                            JOptionPane.showMessageDialog(this, "Animal selection was cancelled.", "Cancelled", JOptionPane.WARNING_MESSAGE);
                            return;
                        }
                    }
                    // Set the locations for the animals in the current group
                    SetAnimalLocationRelay locationRelay = new SetAnimalLocationRelay(animalGroups[i], competitionPanelSendToAnimal, numberOfAnimalsPerGroup);
                }

                // Notify the user that the competition setup is complete
                JOptionPane.showMessageDialog(this, "Relay competition setup complete.", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
            else if (selectedCompetition.getCompetitionType().equals("Air")) {

                String title = "Relay Air Competition";
                String title2 = "Enter number of animals per group";
                String[] options = {"1", "2", "3", "4"};
                String[] airAnimalOptions = {"Eagle", "Pigeon"};

                // For selecting the number of groups
                int groupSize = selectNumberOfItems(options, "Select Number of Groups", "Select the number of groups");
                // For selecting the number of animals per group
                int numberOfAnimalsPerGroup = selectNumberOfItems(options, title2, "Select the number of animals per group");
                // Set the size of the animal array in the competition
                selectedCompetition.setAnimalArraySize(groupSize * numberOfAnimalsPerGroup, numberOfAnimalsPerGroup);

                // Animal array should be initialized for the relay
                Animal[][] animalGroups = new Animal[groupSize][numberOfAnimalsPerGroup];

                // Loop through each group
                for (int i = 0; i < groupSize; i++) {
                    // Loop through each animal in the group
                    for (int j = 0; j < numberOfAnimalsPerGroup; j++) {
                        // Show a dialog to select an animal type
                        String selectedAnimal = (String) JOptionPane.showInputDialog(
                                this,
                                "Select air animal for position " + (j + 1) + " in group " + (i + 1),
                                "Animal Selection",
                                JOptionPane.PLAIN_MESSAGE,
                                null,
                                airAnimalOptions,
                                airAnimalOptions[0]
                        );

                        // Validate selection
                        if (selectedAnimal != null) {
                            // Create an air animal based on the selection
                            Animal animal;
                            switch (selectedAnimal) {
                                case "Eagle":
                                    animal = createAirAnimal("Eagle");
                                    break;
                                case "Pigeon":
                                    animal = createAirAnimal("Pigeon");
                                    break;
                                default:
                                    animal = null;
                                    break;
                            }

                            if (animal != null) {
                                animalGroups[i][j] = animal;
                                selectedCompetition.addAnimal(i, j, animal);
                            } else {
                                JOptionPane.showMessageDialog(this, "Error creating animal. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                                return;
                            }
                        } else {
                            JOptionPane.showMessageDialog(this, "Animal selection was cancelled.", "Cancelled", JOptionPane.WARNING_MESSAGE);
                            return;
                        }
                    }
                    SetAnimalLocationRelay locationRelay = new SetAnimalLocationRelay(animalGroups[i], competitionPanelSendToAnimal, numberOfAnimalsPerGroup);
                }
                JOptionPane.showMessageDialog(this, "Relay air competition setup complete.", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "Unsupported competition description.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public int selectNumberOfAnimals(String[] numberOfOption, String title, String competitionName) {

        JDialog newDialog = new JDialog((Frame) null, title, true);
        newDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        newDialog.setSize(600, 200);
        newDialog.setLocationRelativeTo(null);
        newDialog.setLayout(new BorderLayout());

        // Create a panel with a titled border
        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centerPanel.setBorder(BorderFactory.createTitledBorder("Select number of animals for competition " + competitionName));

        // Create the JComboBox and set its preferred size
        JComboBox<String> animalCountComboBox = new JComboBox<>(numberOfOption);
        animalCountComboBox.setPreferredSize(new Dimension(newDialog.getWidth() / 4, 30)); // Quarter of the width and 30px height

        centerPanel.add(animalCountComboBox);
        newDialog.add(centerPanel, BorderLayout.CENTER);

        AtomicInteger selectedNumber = new AtomicInteger();

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> {
            String selected = (String) animalCountComboBox.getSelectedItem();
            selectedNumber.set(Integer.parseInt(selected));
            System.out.println("Selected number of animals: " + selectedNumber.get());
            newDialog.dispose();
        });

        newDialog.add(submitButton, BorderLayout.SOUTH);
        newDialog.setVisible(true);

        return selectedNumber.get();
    }

    public int selectNumberOfItems(String[] options, String title, String message) {
        JDialog dialog = new JDialog((Frame) null, title, true);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setSize(600, 200);
        dialog.setLocationRelativeTo(null);
        dialog.setLayout(new BorderLayout());

        // Create a panel with a titled border
        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centerPanel.setBorder(BorderFactory.createTitledBorder(message));

        // Create the JComboBox and set its preferred size
        JComboBox<String> comboBox = new JComboBox<>(options);
        comboBox.setPreferredSize(new Dimension(dialog.getWidth() / 4, 30)); // Quarter of the width and 30px height

        centerPanel.add(comboBox);
        dialog.add(centerPanel, BorderLayout.CENTER);

        AtomicInteger selectedValue = new AtomicInteger();

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> {
            String selected = (String) comboBox.getSelectedItem();
            if (selected != null) {
                selectedValue.set(Integer.parseInt(selected));
            }
            dialog.dispose();
        });

        dialog.add(submitButton, BorderLayout.SOUTH);
        dialog.setVisible(true);

        return selectedValue.get();
    }
}


