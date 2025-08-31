//segev isaac id : 207938085
//sifra avigdor id: 207067125
//ayelet ratz
package System;

import Animals.*;
import Mobility.Point;
import Olympics.Medal;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Sys class is the main entry point of the program.
 * It contains methods for creating animals and interacting with them.
 */
public class Sys {
    //exceptions check

    /**
     * This method ensures that the input integer is within a specified range.
     *
     * @param min the minimum acceptable value
     * @param max the maximum acceptable value
     * @return a valid integer within the specified range
     */
    public static int getSafeInt(int min, int max) {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        do {
            try {
                System.out.println("Enter a value between " + min + " and " + max + ".");
                choice = scanner.nextInt();
                if (choice < min || choice > max) {
                    throw new IllegalArgumentException("The value you entered is invalid");
                }
            } catch (NoSuchElementException | IllegalArgumentException e) {
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                scanner.nextLine();
            }
        } while (choice < min || choice > max);

        return choice;
    }

    /**
     * This method ensures that the input double is greater than 0.
     *
     * @return a valid double greater than 0
     */
    public static double getSafeDouble() {
        Scanner scanner = new Scanner(System.in);
        double value = -1;

        do {
            try {
                value = scanner.nextDouble();
                if ((value <= 0)) {
                    throw new IllegalArgumentException("The value you entered is invalid");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number greater than 0.");
                scanner.nextLine();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                scanner.nextLine();
            }
        } while (value < 0);

        return value;
    }

    /**
     * Main method which initializes the program, allowing users to create animals and interact with them.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of animals you want to create: ");
        int numberOfAnimals = getSafeInt(1, 100);

        Animal[] animals = new Animal[numberOfAnimals];

        for (int i = 0; i < numberOfAnimals; i++) {
            System.out.println("Choose the type of animal you want to create: ");
            System.out.println("1. Terrestrial Animal");
            System.out.println("2. Water Animal");
            System.out.println("3. Air Animal");
            int typeChoice = getSafeInt(1, 3);

            switch (typeChoice) {
                case 1:
                    System.out.println("Choose the terrestrial animal:");
                    System.out.println("1. Dog");
                    System.out.println("2. Cat");
                    System.out.println("3. Snake");
                    int terrestrialChoice = getSafeInt(1, 3);
                    animals[i] = createTerrestrialAnimal(terrestrialChoice, scanner);
                    break;
                case 2:
                    System.out.println("Choose the water animal:");
                    System.out.println("1. Alligator");
                    System.out.println("2. Whale");
                    System.out.println("3. Dolphin");
                    int waterChoice = getSafeInt(1, 3);
                    animals[i] = createWaterAnimal(waterChoice, scanner);
                    break;
                case 3:
                    System.out.println("Choose the air animal:");
                    System.out.println("1. Eagle");
                    System.out.println("2. Pigeon");
                    int airChoice = getSafeInt(1, 2);
                    animals[i] = createAirAnimal(airChoice, scanner);
                    break;
                default:
                    System.out.println("Invalid choice.");
                    i--; // Decrement i to repeat this iteration
                    break;
            }
        }

        // Menu to interact with animals
        boolean exit = false;
        while (!exit) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Show information about all animals");
            System.out.println("2. Make all animals speak");
            System.out.println("3. Exit");
            int choice = getSafeInt(1, 3);

            switch (choice) {
                case 1:
                    displayAnimalInformation(animals);
                    break;
                case 2:
                    makeAnimalsSpeak(animals);
                    break;
                case 3:
                    exit = true;
                    System.out.println("Exiting program...");
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        }
        scanner.close();
    }

    /**
     * Displays information about all the animals.
     *
     * @param animals array of animals
     */
    private static void displayAnimalInformation(Animal[] animals) {
        System.out.println("\nAnimal Information:");
        for (Animal animal : animals) {
            if (animal != null) {
                System.out.println(animal.toString());
            }
        }
    }

    /**
     * Makes all animals speak.
     *
     * @param animals array of animals
     */
    private static void makeAnimalsSpeak(Animal[] animals) {
        System.out.println("\nMaking animals speak:");
        for (Animal animal : animals) {
            if (animal != null) {
                animal.makeSound(animal);
            }
        }
    }

    /**
     * Creates a terrestrial animal based on user input.
     *
     * @param choice  the type of terrestrial animal to create
     * @param scanner the scanner object for user input
     * @return the created terrestrial animal
     */
    public static Animal createTerrestrialAnimal(int choice, Scanner scanner) {
        System.out.println("Enter the name of the animal:");
        String name = scanner.next();
        System.out.println("Enter the weight of the animal:");
        double weight = getSafeDouble();
        System.out.println("Enter the speed of the animal:");
        double speed = getSafeDouble();
        System.out.println("Enter the number of legs of the animal:");
        int numOfLegs = getSafeInt(2, 4);

        Point position = new Point(0, 20); // Default position for terrestrial animals
        Point location = new Point(0, 20); // Default location for terrestrial animals
        double totalDistance = 0; // Initial total distance traveled
        Animal.Gender gender = Animal.Gender.MALE; // Example gender, modify as per input
        Medal[] medals = new Medal[0]; // No medals initially

        switch (choice) {
            case 1:
                System.out.println("Enter the breed of the dog:");
                String breed = scanner.next();
//                return new Dog(breed, name, weight, speed, medals, location, totalDistance, gender, numOfLegs);
            case 2:
                System.out.println("Is the cat castrated? (true/false):");
                boolean castrated = scanner.nextBoolean();
//                return new Cat(castrated, name, weight, speed, medals, location, totalDistance, gender, numOfLegs);
            case 3:
                System.out.println("Enter the length of the snake:");
                double length = getSafeDouble();
                System.out.println("Enter the poisonous level of the snake (HIGH/LOW):");
                String poisonousLevel = scanner.next().toUpperCase();
                Snake.Poisonous poisonous = Snake.Poisonous.valueOf(poisonousLevel);
//                return new Snake(length, poisonous, name, weight, speed, medals, position, location, totalDistance, gender, numOfLegs);
            default:
                System.out.println("Invalid choice.");
                return null;
        }
    }

    /**
     * Creates a water animal based on user input.
     *
     * @param choice  the type of water animal to create
     * @param scanner the scanner object for user input
     * @return the created water animal
     */
    public static Animal createWaterAnimal(int choice, Scanner scanner) {
        System.out.println("Enter the name of the animal:");
        String name = scanner.next();
        System.out.println("Enter the weight of the animal:");
        double weight = getSafeDouble();
        System.out.println("Enter the speed of the animal:");
        double speed = getSafeDouble();

        Point location = new Point(50, 0); // Default location for water animals
        double totalDistance = 0; // Initial total distance traveled
        Animal.Gender gender = Animal.Gender.MALE; // Example gender, modify as per input
        Medal[] medals = new Medal[0]; // No medals initially
        int MAX_DIVE = -800; // Example max dive depth
        double diveDepth = 0; // Example current dive depth

        switch (choice) {
            case 1:
                System.out.println("Enter the area of living of the alligator:");
                String areaOfLiving = scanner.next();
//                return new Alligator(areaOfLiving, name, weight, speed, medals, location, totalDistance, gender, MAX_DIVE, diveDepth,4);
            case 2:
                System.out.println("Enter the food type of the whale:");
                String foodType = scanner.next();
//                return new Whale(foodType, name, weight, speed, medals, location, totalDistance, gender, MAX_DIVE, diveDepth);
            case 3:
                System.out.println("Enter the water type of the dolphin (SEA/SWEET):");
                String waterTypeInput = scanner.next().toUpperCase();
                Dolphin.waterType waterType = Dolphin.waterType.valueOf(waterTypeInput);
//                return new Dolphin(waterType, name, weight, speed, medals, location, totalDistance, gender, MAX_DIVE, diveDepth);
            default:
                System.out.println("Invalid choice.");
                return null;
        }
    }

    /**
     * Creates an air animal based on user input.
     *
     * @param choice  the type of air animal to create
     * @param scanner the scanner object for user input
     * @return the created air animal
     */
    public static Animal createAirAnimal(int choice, Scanner scanner) {
        System.out.println("Enter the name of the animal:");
        String name = scanner.next();
        System.out.println("Enter the weight of the animal:");
        double weight = getSafeDouble();
        System.out.println("Enter the speed of the animal:");
        double speed = getSafeDouble();
        System.out.println("Enter the wingspan of the animal:");
        double wingspan = getSafeDouble();

        Point position = new Point(0, 100); // Default position for air animals
        Point location = new Point(0, 100); // Default location for air animals
        double totalDistance = 0; // Initial total distance traveled
        Animal.Gender gender = Animal.Gender.MALE; // Example gender, modify as per input
        Medal[] medals = new Medal[0]; // No medals initially

        switch (choice) {
            case 1:
                System.out.println("Enter the altitude of flight of the eagle:");
                double altitudeOfFlight = scanner.nextDouble();
//                return new Eagle(altitudeOfFlight, name, weight, speed, medals, location, totalDistance, gender, wingspan);
            case 2:
                System.out.println("Enter the family of the pigeon:");
                String family = scanner.next();
//                return new Pigeon(family, name, weight, speed, medals, location, totalDistance, gender, wingspan);
            default:
                System.out.println("Invalid choice.");
                return null;
        }
    }
}
