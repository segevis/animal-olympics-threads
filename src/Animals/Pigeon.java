package Animals;

import Graphics.CompetitionPanel;
import Mobility.Point;
import Olympics.Medal;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * The Pigeon class represents a pigeon, which is an air animal.
 * It extends from the AirAnimal class and implements specific behaviors and attributes
 * of a pigeon.
 */
public class Pigeon extends AirAnimal {

    // Attributes
    private String family; // The family of the pigeon

    // Constructors

    /**
     * Default constructor for Pigeon.
     * Initializes the pigeon with default values.
     */
    public Pigeon() {
        super();
        this.family = "Pigeon family";
    }

    /**
     * Parameterized constructor for Pigeon.
     *
     * @param family         The family of the pigeon
     * @param name           The name of the pigeon
     * @param weight         The weight of the pigeon
     * @param speed          The speed of the pigeon
     * @param medalArray     An array of Medals earned by the pigeon
     * @param location       The current location of the pigeon
     * @param totalDistance  The total distance traveled by the pigeon
     * @param gender         The gender of the pigeon
     * @param wingspan       The wingspan of the pigeon
     */
    public Pigeon(String family,
                  String name,
                  double weight,
                  double speed,
                  Medal[] medalArray,
                  Point location,
                  double totalDistance,
                  Gender gender,
                  double wingspan,
                  CompetitionPanel panel)
    {
        super(name, weight, speed, medalArray, location, totalDistance, gender, wingspan, panel,"graphics2/pigeon.png",null,null,null);
        this.family = family;
        loadImages(null);
    }

    public Pigeon clone()
    {
        Pigeon clone = (Pigeon) super.clone();
        clone.family = this.family;
        return clone;
    }

    // Getters and Setters

    /**
     * Retrieves the family of the pigeon.
     *
     * @return The family of the pigeon
     */
    public String getFamily() {
        return family;
    }

    /**
     * Sets the family of the pigeon.
     *
     * @param family The new family of the pigeon
     */
    public void setFamily(String family) {
        this.family = family;
    }

    // Methods

    /**
     * Checks if this Pigeon object is equal to another object.
     *
     * @param obj The object to compare with this Pigeon
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Pigeon)) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }
        Pigeon other = (Pigeon) obj;
        return family != null ? family.equals(other.family) : other.family == null;
    }

    /**
     * Returns a string representation of this Pigeon object.
     *
     * @return A string representation including the attributes of the pigeon
     */
    @Override
    public String toString() {
        return super.toString() + ", family='" + family + '\'';
    }

    /**
     * Retrieves the sound made by this Pigeon.
     *
     * @return The sound made by the pigeon
     */
    @Override
    protected String getIndividualSound() {
        return "Arr-rar-rar-rar-raah"; // Example sound made by a pigeon
    }

    @Override
    public void loadImages(String nm)
    {
        try
        {
            BufferedImage img = ImageIO.read(new File("graphics2/pigeon.png"));
            setImg1(img);
        }
        catch (IOException e)
        {
            System.out.println("Cannot load image"); }
    }
}
