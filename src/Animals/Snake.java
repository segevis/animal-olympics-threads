package Animals;

import Graphics.CompetitionPanel;
import Mobility.Point;
import Olympics.Medal;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * The Snake class represents a snake, which is a terrestrial animal.
 * It extends from the TerrestrialAnimal class and implements specific behaviors and attributes
 * of a snake.
 */
public class Snake extends TerrestrialAnimal implements IReptile{



    // Attributes
    public enum Poisonous {HIGH, MID ,LOW} // Enum defining the poisonous level of the snake
    private double length; // The length of the snake
    private Poisonous poisonous; // The poisonous level of the snake

    // Constructors
    /**
     * Default constructor for Snake.
     * Initializes the snake with default values.
     */
    public Snake() {
        super();
        length = 0;
        poisonous = Poisonous.HIGH;
    }

    /**
     * Parameterized constructor for Snake.
     *
     * @param length         The length of the snake
     * @param poisonous      The poisonous level of the snake
     * @param name           The name of the snake
     * @param weight         The weight of the snake
     * @param speed          The speed of the snake
     * @param medalArray     An array of Medals earned by the snake
     * @param position       The current position of the snake
     * @param location       The current location of the snake
     * @param totalDistance  The total distance traveled by the snake
     * @param gender         The gender of the snake
     * @param numberOfLegs   The number of legs the snake has
     */
    public Snake(double length,
                 Poisonous poisonous,
                 String name,
                 double weight,
                 double speed,
                 Medal[] medalArray,
                 Point position,
                 Point location,
                 double totalDistance,
                 Gender gender,
                 int numberOfLegs,
                 CompetitionPanel panel)
    {
        super(name, weight, speed, medalArray, location, totalDistance, gender, numberOfLegs,panel,"graphics2/snake1.png","graphics2/snake2.png",null,null);
        this.length = length;
        this.poisonous = poisonous;
        loadImages(null);
    }

    public Snake clone ()
    {
        Snake clone = (Snake) super.clone();
        clone.length = this.length;
        clone.poisonous = this.poisonous;
        return clone;
    }

    /**
     * Increases the speed of the reptile by the specified amount.
     *
     * @param speed the amount by which to increase the speed
     */
    @Override
    public void speedUp(int speed) {
        if (speed > 0)
        {
            setSpeed(speed + getSpeed());
        }
    }

    // Getters and Setters
    /**
     * Retrieves the length of the snake.
     *
     * @return The length of the snake
     */
    public double getLength() {
        return length;
    }

    /**
     * Sets the length of the snake.
     *
     * @param length The new length of the snake
     */
    public void setLength(double length) {
        this.length = length;
    }

    /**
     * Retrieves the poisonous level of the snake.
     *
     * @return The poisonous level of the snake
     */
    public Poisonous getPoisonous() {
        return poisonous;
    }

    /**
     * Sets the poisonous level of the snake.
     *
     * @param poisonous The new poisonous level of the snake
     */
    public void setPoisonous(Poisonous poisonous) {
        this.poisonous = poisonous;
    }

    // Methods

    /**
     * Checks if this Snake object is equal to another object.
     *
     * @param obj The object to compare with this Snake
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Snake)) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }
        Snake other = (Snake) obj;
        return Double.compare(other.length, length) == 0 &&
                poisonous == other.poisonous;
    }

    /**
     * Returns a string representation of this Snake object.
     *
     * @return A string representation including the attributes of the snake
     */
    @Override
    public String toString() {
        return super.toString() +
                ", length=" + length +
                ", poisonous=" + poisonous;
    }

    /**
     * Retrieves the sound made by this Snake.
     *
     * @return The sound made by the snake
     */
    @Override
    protected String getIndividualSound() {
        return "ssssssss"; // Example sound made by a snake
    }

    @Override
    public void loadImages(String nm)
    {
        try
        {
            BufferedImage img = ImageIO.read(new File("graphics2/snake3.png"));
            setImg1(img);
        }
        catch (IOException e)
        {
            System.out.println("Cannot load image"); }

    }

}
