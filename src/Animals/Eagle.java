package Animals;

import Graphics.CompetitionPanel;
import Mobility.Point;
import Olympics.Medal;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * The Eagle class represents an eagle, which is an air animal.
 * It extends from the AirAnimal class and implements specific behaviors and attributes
 * of an eagle.
 */
public class Eagle extends AirAnimal {

    // Attributes
    private double altitudeOfFlight; // The altitude at which the eagle flies

    // Constructors

    /**
     * Default constructor for Eagle.
     * Initializes the eagle with default values.
     */
    public Eagle() {
        super();
        this.altitudeOfFlight = 0;
    }

    /**
     * Parameterized constructor for Eagle.
     *
     * @param altitudeOfFlight The altitude at which the eagle flies
     * @param name             The name of the eagle
     * @param weight           The weight of the eagle
     * @param speed            The speed of the eagle
     * @param medalArray       An array of Medals earned by the eagle
     * @param location         The current location of the eagle
     * @param totalDistance    The total distance traveled by the eagle
     * @param gender           The gender of the eagle
     * @param wingspan         The wingspan of the eagle
     */
    public Eagle(double altitudeOfFlight,
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
        super(name, weight, speed, medalArray, location, totalDistance, gender, wingspan,panel,"graphics2/eagle1.png","graphics2/eagle2.png",null,null);
        this.altitudeOfFlight = altitudeOfFlight;
        loadImages(null);
    }

    public Eagle clone ()
    {
        Eagle clone = (Eagle) super.clone();
        clone.altitudeOfFlight = this.altitudeOfFlight;
        return clone;
    }

    // Getters and Setters

    /**
     * Retrieves the altitude at which the eagle flies.
     *
     * @return The altitude of flight
     */
    public double getAltitudeOfFlight() {
        return altitudeOfFlight;
    }

    /**
     * Sets the altitude at which the eagle flies.
     *
     * @param altitudeOfFlight The new altitude of flight
     */
    public void setAltitudeOfFlight(double altitudeOfFlight) {
        this.altitudeOfFlight = altitudeOfFlight;
    }

    // Methods

    /**
     * Checks if this Eagle object is equal to another object.
     *
     * @param obj The object to compare with this Eagle
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Eagle)) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }
        Eagle other = (Eagle) obj;
        return Double.compare(other.altitudeOfFlight, altitudeOfFlight) == 0;
    }

    /**
     * Returns a string representation of this Eagle object.
     *
     * @return A string representation including the attributes of the eagle
     */
    @Override
    public String toString() {
        return super.toString() + ", altitudeOfFlight: " + altitudeOfFlight;
    }

    /**
     * Retrieves the sound made by this Eagle.
     *
     * @return The sound made by the eagle
     */
    @Override
    protected String getIndividualSound() {
        return "Clack-wack-chack"; // Example sound made by an eagle
    }

    @Override
    public void loadImages(String nm)
    {
        try
        {
            BufferedImage img = ImageIO.read(new File("graphics2/eagle1.png"));
            setImg1(img);
        }
        catch (IOException e)
        {
            System.out.println("Cannot load image"); }
    }
}
