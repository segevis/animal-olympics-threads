package Animals;

import Mobility.Mobile;
import Mobility.Point;
import Olympics.Medal;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import Graphics.CompetitionPanel;
import java.awt.image.BufferedImage;

import Graphics.*;
import Mobility.ILocatable;

import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * The Animal class is an abstract class that represents a generic animal.
 * It extends the Mobile class and includes additional attributes and methods specific to animals.
 */
public abstract class Animal extends Mobile implements ILocatable, IMoveable, IDrawable {

    // Attributes
    public enum Gender {MALE, FEMALE, HERMAPHRODITE;}
    public enum Orientation {SOUTH, NORTH, EAST, WEST}
    private String name;
    private double weight;
    private double speed;
    private Medal[] medalArray;
    private Gender gender;
    //new Attributes
    private int size;
    private int id;
    protected Orientation orien = Orientation.EAST;
    private int maxEnergy;
    private int energy;
    private int energyPerMeter;
    protected CompetitionPanel competitionPanelToPrint;
    private BufferedImage img1, img2, img3, img4;



    @Override
    public Animal clone() {
        try
        {
            Animal clone = (Animal) super.clone();
            clone.medalArray = (medalArray != null) ? Arrays.copyOf(medalArray, medalArray.length) : null;
            clone.name = this.name;
            clone.gender = this.gender;
            clone.weight = this.weight;
            clone.speed = this.speed;
            clone.maxEnergy = this.maxEnergy;
            clone.energyPerMeter = this.energyPerMeter;
            clone.competitionPanelToPrint = this.competitionPanelToPrint;
            return clone;
        } catch (CloneNotSupportedException e)
        {
            throw new AssertionError();
        }
    }
    /**
     * Default constructor for the Animal class.
     * Initializes the animal's attributes to default values.
     */
    public Animal() {
        Point p = new Point(0, 0);
        super(p, 0);
        this.gender = Gender.MALE;
        name = null;
        weight = 0;
        speed = 0;
        medalArray = new Medal[0];
    }
    /**
     * Parameterized constructor for the Animal class.
     *
     * @param name          the name of the animal
     * @param weight        the weight of the animal
     * @param speed         the speed of the animal
     * @param medalArray    the array of medals the animal has won
     * @param location      the current location of the animal
     * @param totalDistance the total distance the animal has traveled
     * @param gender        the gender of the animal
     */
    public Animal(String name,
                  double weight,
                  double speed,
                  Medal[] medalArray,
                  Point location,
                  double totalDistance,
                  Gender gender,
                  CompetitionPanel panel,
                  String imagePath,
                  String imagePath2,
                  String imagePath3,
                  String imagePath4) {
        super(location, totalDistance);
        this.size = 65;
        this.gender = gender;
        this.name = name;
        this.weight = weight;
        this.speed = speed;
        this.medalArray = new Medal[medalArray.length];
        this.competitionPanelToPrint = panel;
        this.energy = 0;

        // העתק את המדליות
        for (int i = 0; i < medalArray.length; i++)
        {
            this.medalArray[i] = medalArray[i];
        }

        // טען את התמונה מהנתיב
        loadImages(imagePath);
    }
    /**
     * Copy constructor for the Animal class.
     *
     * @param other another animal object to copy from
     */
    public Animal(Animal other) {
        Point p = other.getLocation();
        double totalDistance = other.getTotalDistance();
        super(p, totalDistance);
        this.gender = other.gender;
        this.name = other.name;
        this.weight = other.weight;
        this.speed = other.speed;
        this.medalArray = new Medal[other.medalArray.length];
        for (int i = 0; i < other.medalArray.length; i++) {
            this.medalArray[i] = other.medalArray[i];
        }
    }
    // Setters
    /**
     * Sets the name of the animal.
     *
     * @param name the new name of the animal
     * @return true if the name was set successfully
     */
    public boolean setName(String name) {
        this.name = name;
        return true;
    }
    /**
     * Sets the weight of the animal.
     *
     * @param weight the new weight of the animal
     * @return true if the weight was set successfully
     */
    public boolean setWeight(double weight) {
        this.weight = weight;
        return true;
    }
    /**
     * Sets the speed of the animal.
     *
     * @param speed the new speed of the animal
     * @return true if the speed was set successfully
     */
    public boolean setSpeed(double speed) {
        this.speed = speed;
        return true;
    }
    /**
     * Sets the gender of the animal.
     *
     * @param gender the new gender of the animal
     * @return true if the gender was set successfully
     */
    public boolean setGender(Gender gender) {
        this.gender = gender;
        return true;
    }
    // Getters
    /**
     * Gets the name of the animal.
     *
     * @return the name of the animal
     */
    public String getName() {
        return name;
    }
    /**
     * Gets the weight of the animal.
     *
     * @return the weight of the animal
     */
    public double getWeight() {
        return weight;
    }
    /**
     * Gets the speed of the animal.
     *
     * @return the speed of the animal
     */
    public double getSpeed() {
        return speed;
    }
    /**
     * Gets the array of medals the animal has won.
     *
     * @return the array of medals
     */
    public Medal[] getMedalArray() {
        return medalArray;
    }
    /**
     * Gets the gender of the animal.
     *
     * @return the gender of the animal
     */
    public Gender getGender() {
        return gender;
    }
    // equals
    /**
     * Compares this animal to another object for equality.
     *
     * @param obj the object to compare with
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (!(obj instanceof Animal))
            return false;

        Animal other = (Animal) obj;

        return this.name.equals(other.name) && this.weight == other.weight &&
                this.speed == other.speed && this.gender == other.gender
                && Arrays.equals(this.medalArray, other.medalArray);
    }
    // toString
    /**
     * Returns a string representation of the animal.
     *
     * @return a string containing the animal's details
     */
    @Override
    public String toString() {
        return super.toString() + "name: " + name + ", weight: " + weight + ", speed: " + speed +
                ", gender: " + gender + ", medals: " + Arrays.toString(medalArray);
    }
    // makeSound
    /**
     * Makes the animal produce a sound.
     *
     * @param animal the animal that makes the sound
     */
    public void makeSound(Animal animal) {
        System.out.println("Animal <" + animal.name + "> said <" + getIndividualSound() + ">");
    }
    /**
     * Abstract method to get the individual sound of the animal.
     *
     * @return the sound of the animal
     */
    protected abstract String getIndividualSound();
    public String getAnimaleName()
    {
        return name;
    }
    @Override
    public void drawObject(Graphics g) {
        if (img1 != null)
        {
            // Example: Drawing the image based on orientation
            if (orien == Orientation.EAST)
            {
                g.drawImage(img1, getLocation().getX(), getLocation().getY() - size / 10, size * 2, size, competitionPanelToPrint);
            }
            else if (orien == Orientation.SOUTH)
            {
                g.drawImage(img1, getLocation().getX(), getLocation().getY() - size / 10, size, size, null);
            }
            else if (orien == Orientation.WEST)
            {
                g.drawImage(img1, getLocation().getX(), getLocation().getY() - size / 10, size * 2, size, null);
            }
            else if (orien == Orientation.NORTH)
            {
                g.drawImage(img1, getLocation().getX() - size / 2, getLocation().getY() - size / 10, size, size * 2, null);
            }
        } else {
            System.out.println("Images not loaded properly.");
        }
    }
    public void setImg1(BufferedImage img)
    {
        this.img1 = img;
    }
    /**
     * Loads the images for this Dog.
     *
     * @param nm Not used in this implementation.
     */
    @Override
    public void loadImages(String nm) {
        if (nm != null && !nm.isEmpty())
        {
            try
            {
                File imageFile = new File(nm);
                if (imageFile.exists())
                {
                    BufferedImage img = ImageIO.read(imageFile);
                    setImg1(img);
                    System.out.println("Image is good !!!!!!! " + nm);
                }
                else
                {
                    System.out.println("Image file does not exist: " + nm);
                }
            }
            catch (IOException e)
            {
                System.out.println("Cannot load image: " + e.getMessage());
            }
        }
        else
        {
            System.out.println("No image path provided.");
        }
    }
    public int getMaxEnergy() {
        return maxEnergy;
    }
    public int getEnergyPerMeter() {
        return energyPerMeter;
    }
    public String getCategory(Animal animal) {
        if (animal instanceof WaterAnimal) return "Water";
        if (animal instanceof TerrestrialAnimal) return "Land";
        if (animal instanceof AirAnimal) return "Air";
        return "Unknown";
    }
    public String getType(Animal animal) {
        if (animal instanceof Alligator) return "Alligator";
        if (animal instanceof Whale) return "Whale";
        if (animal instanceof Dolphin) return "Dolphin";
        if (animal instanceof Dog) return "Dog";
        if (animal instanceof Cat) return "Cat";
        if (animal instanceof Snake) return "Snake";
        if (animal instanceof Eagle) return "Eagle";
        if (animal instanceof Pigeon) return "Pigeon";
        return "Unknown";
    }
    public void addEnergy(int energy) {
        this.energy += energy;
    }
    public int getEnergy() {
        return energy;
    }
    public int getSize(){return size;}
    public double getDistance(){
        return getTotalDistance();
    }

    public CompetitionPanel getPanel(){
        return competitionPanelToPrint;
    }
}

