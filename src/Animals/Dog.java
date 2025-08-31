package Animals;

import Graphics.CompetitionPanel;
import Mobility.Point;
import Olympics.Medal;

import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * The Dog class represents a dog, which is a terrestrial animal.
 * It inherits from the TerrestrialAnimal class and implements specific behaviors and attributes
 * of a dog.
 */
public class Dog extends TerrestrialAnimal {

    // Attributes
    private String breed; // The breed of the dog
    private BufferedImage img; // The image of the dog

    // Constructors

    /**
     * Parameterized constructor for Dog.
     *
     * @param breed          The breed of the dog
     * @param name           The name of the dog
     * @param weight         The weight of the dog
     * @param speed          The speed of the dog
     * @param medalArray     An array of Medals earned by the dog
     * @param location       The current location of the dog
     * @param totalDistance  The total distance traveled by the dog
     * @param gender         The gender of the dog
     * @param numberOfLegs   The number of legs of the dog
     * @param panel          The competition panel where the dog will be displayed
     */
    public Dog(String breed,
               String name,
               double weight,
               double speed,
               Medal[] medalArray,
               Point location,
               double totalDistance,
               Gender gender,
               int numberOfLegs,
               CompetitionPanel panel)
    {
        super(name, weight, speed, medalArray, location, totalDistance, gender, numberOfLegs, panel, "graphics2/dog1.png","graphics2/dog2.png",null,null);
        this.breed = breed;
    }

    @Override
    public Dog clone()
    {
        Dog clone = (Dog) super.clone();
        clone.breed = this.breed;
        return clone;
    }

    // Getters and Setters
    /**
     * Retrieves the breed of the dog.
     *
     * @return The breed of the dog
     */
    public String getBreed()
    {
        return breed;
    }

    /**
     * Sets the breed of the dog.
     *
     * @param breed The new breed of the dog
     */
    public void setBreed(String breed)
    {
        this.breed = breed;
    }

    // Methods

    /**
     * Checks if this Dog object is equal to another object.
     *
     * @param obj The object to compare with this Dog
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Dog)) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }
        Dog other = (Dog) obj;
        return breed.equals(other.breed);
    }

    /**
     * Returns a string representation of this Dog object.
     *
     * @return A string representation including the attributes of the dog
     */
    @Override
    public String toString()
    {
        return super.toString() + ", breed: " + breed;
    }

    /**
     * Retrieves the sound made by this Dog.
     *
     * @return The sound made by the dog
     */
    protected String getIndividualSound()
    {
        return "Woof Woof"; // Example sound made by a dog
    }


}
