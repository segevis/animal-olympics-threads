package Animals;

import Graphics.CompetitionPanel;
import Mobility.Point;
import Olympics.Medal;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * The Cat class represents a cat, which is a terrestrial animal.
 * It inherits from the TerrestrialAnimal class and implements specific behaviors and attributes
 * of a cat.
 */
public class Cat extends TerrestrialAnimal {

    // Attributes
    private boolean castrated; // Indicates if the cat is castrated

    // Constructors

    /**
     * Default constructor for Cat.
     * Sets default values for attributes.
     */
    public Cat() {
        super();
        castrated = false; // Default value for castrated attribute
    }

    /**
     * Parameterized constructor for Cat.
     *
     * @param castrated      Indicates if the cat is castrated
     * @param name           The name of the cat
     * @param weight         The weight of the cat
     * @param speed          The speed of the cat
     * @param medalArray     An array of Medals earned by the cat
     * @param location       The current location of the cat
     * @param totalDistance  The total distance traveled by the cat
     * @param gender         The gender of the cat
     * @param numberOfLegs   The number of legs of the cat
     */
    public Cat(boolean castrated,
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
        super(name, weight, speed, medalArray, location, totalDistance, gender, numberOfLegs,panel, "graphics2/cat1.png","graphics2/cat2.png",null,null);
        this.castrated = castrated;
        loadImages("graphics2/cat1.png");
    }

    public Cat clone()
    {
        Cat clone = (Cat) super.clone();
        clone.castrated = castrated;
        return clone;
    }

    // Methods

    /**
     * Checks if this Cat object is equal to another object.
     *
     * @param obj The object to compare with this Cat
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Cat)) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }
        Cat other = (Cat) obj;
        return castrated == other.castrated;
    }

    /**
     * Returns a string representation of this Cat object.
     *
     * @return A string representation including the attributes of the cat
     */
    @Override
    public String toString() {
        return super.toString() + ", castrated: " + castrated;
    }

    /**
     * Retrieves the sound made by this Cat.
     *
     * @return The sound made by the cat
     */
    protected String getIndividualSound() {
        return "Meow"; // Example sound made by a cat
    }

}
