package Animals;

import Graphics.CompetitionPanel;
import Mobility.Point;
import Olympics.Medal;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * The Dolphin class represents a dolphin, which is a water animal.
 * It inherits from the WaterAnimal class and implements specific behaviors and attributes
 * of a dolphin.
 */
public class Dolphin extends WaterAnimal {



    // Attributes
    public enum waterType {SEA, SWEET} // Types of water environments where dolphins live
    private waterType type; // The type of water environment where the dolphin lives

    // Constructors

    /**
     * Default constructor for Dolphin.
     * Initializes the dolphin with default values.
     */
    public Dolphin() {
        super();
        type = waterType.SEA; // Default type of water environment for dolphins
    }

    /**
     * Parameterized constructor for Dolphin.
     *
     * @param type           The type of water environment where the dolphin lives
     * @param name           The name of the dolphin
     * @param weight         The weight of the dolphin
     * @param speed          The speed of the dolphin
     * @param medalArray     An array of Medals earned by the dolphin
     * @param location       The current location of the dolphin
     * @param totalDistance  The total distance traveled by the dolphin
     * @param gender         The gender of the dolphin
     * @param MAX_DIVE       The maximum depth the dolphin can dive
     * @param diveDept       The current depth the dolphin has dived
     */
    public Dolphin(waterType type,
                   String name,
                   double weight,
                   double speed,
                   Medal[] medalArray,
                   Point location,
                   double totalDistance,
                   Animal.Gender gender,
                   int MAX_DIVE,
                   double diveDept,
                   CompetitionPanel panel)
    {
        super(name, weight, speed, medalArray, location, totalDistance, gender, MAX_DIVE, diveDept, panel,"graphics2/dolphin1.png","graphics2/dolphin2.png",null,null);
        this.type = type;
        loadImages(null);
    }

    public Dolphin clone ()
    {
        Dolphin clone = (Dolphin)super.clone();
        clone.type = this.type;
        return clone;
    }

    // Getters and Setters
    /**
     * Retrieves the type of water environment where the dolphin lives.
     *
     * @return The type of water environment
     */
    public waterType getType() {
        return type;
    }

    /**
     * Sets the type of water environment where the dolphin lives.
     *
     * @param type The new type of water environment
     */
    public void setType(waterType type) {
        this.type = type;
    }

    // Methods

    /**
     * Checks if this Dolphin object is equal to another object.
     *
     * @param obj The object to compare with this Dolphin
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Dolphin)) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }
        Dolphin other = (Dolphin) obj;
        return type == other.type;
    }

    /**
     * Returns a string representation of this Dolphin object.
     *
     * @return A string representation including the attributes of the dolphin
     */
    @Override
    public String toString() {
        return super.toString() + ", type: " + type;
    }

    /**
     * Retrieves the sound made by this Dolphin.
     *
     * @return The sound made by the dolphin
     */
    protected String getIndividualSound() {
        return "Click-click"; // Example sound made by a dolphin
    }

    @Override
    public void loadImages(String nm)
    {
        try
        {
            BufferedImage img = ImageIO.read(new File("graphics2/dolphin2.png"));
            setImg1(img);
        }
        catch (IOException e)
        {
            System.out.println("Cannot load image"); }

    }
}
