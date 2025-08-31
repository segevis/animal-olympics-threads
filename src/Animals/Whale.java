package Animals;

import Graphics.CompetitionPanel;
import Mobility.Point;
import Olympics.Medal;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * The Whale class represents a whale, which is a type of water animal.
 * It extends from the WaterAnimal class and implements specific behaviors and attributes
 * of a whale.
 */
public class Whale extends WaterAnimal {

    // Attributes
    private String foodType; // The type of food the whale eats

    // Constructors

    /**
     * Default constructor for Whale.
     * Initializes the whale with default values.
     */
    public Whale() {
        super();
        foodType = "tuna";
    }

    /**
     * Parameterized constructor for Whale.
     *
     * @param foodType       The type of food the whale eats
     * @param name           The name of the whale
     * @param weight         The weight of the whale
     * @param speed          The speed of the whale
     * @param medalArray     An array of Medals earned by the whale
     * @param location       The current location of the whale
     * @param totalDistance  The total distance traveled by the whale
     * @param gender         The gender of the whale
     * @param MAX_DIVE       The maximum diving depth of the whale
     * @param diveDept       The current diving depth of the whale
     */
    public Whale(String foodType,
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
        super(name, weight, speed, medalArray, location, totalDistance, gender, MAX_DIVE, diveDept,panel,"graphics2/whale.png","graphics2/whale.png",null,null);
        this.foodType = foodType;
        loadImages(null);
    }

    public Whale clone ()
    {
        Whale clone = (Whale) super.clone();
        clone.foodType = this.foodType;
        return clone;
    }

    // Getters and Setters

    /**
     * Retrieves the type of food the whale eats.
     *
     * @return The type of food
     */
    public String getFoodType() {
        return foodType;
    }

    /**
     * Sets the type of food the whale eats.
     *
     * @param foodType The new type of food
     */
    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    // Methods

    /**
     * Checks if this Whale object is equal to another object.
     *
     * @param obj The object to compare with this Whale
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Whale)) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }
        Whale other = (Whale) obj;
        return foodType.equals(other.foodType);
    }

    /**
     * Returns a string representation of this Whale object.
     *
     * @return A string representation including the attributes of the whale
     */
    @Override
    public String toString() {
        return super.toString() + ", foodType='" + foodType + '\'';
    }

    /**
     * Retrieves the sound made by this Whale.
     *
     * @return The sound made by the whale
     */
    protected String getIndividualSound() {
        return "Splash"; // Example sound made by a whale
    }

    @Override
    public void loadImages(String BACKGROUND_PATH)
    {
        try
        {
            BufferedImage img = ImageIO.read(new File("graphics2/whale.png"));
            setImg1(img);
        }
        catch (IOException e)
        {
            System.out.println("Cannot load image"); }
    }
}
