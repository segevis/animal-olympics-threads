package Animals;

import Graphics.CompetitionPanel;
import Mobility.Point;
import Olympics.Medal;

/**
 * WaterAnimal is an abstract class representing animals that live in water.
 * It extends the Animal class and includes attributes and methods specific to water animals.
 */
public abstract class WaterAnimal extends Animal {
    // Attributes
    private static final int MAX_DIVE = -800;
    private double diveDept;

    /**
     * Default constructor for WaterAnimal.
     * Initializes the dive depth to 0.
     */
    public WaterAnimal() {
        super();
        diveDept = 0;
    }

    /**
     * Parameterized constructor for WaterAnimal.
     *
     * @param name           the name of the water animal
     * @param weight         the weight of the water animal
     * @param speed          the speed of the water animal
     * @param medalArray     the array of medals the water animal has won
     * @param location       the current location of the water animal
     * @param totalDistance  the total distance the water animal has traveled
     * @param gender         the gender of the water animal
     * @param MAX_DIVE       the maximum dive depth for the water animal
     * @param diveDept       the current dive depth of the water animal
     */
    public WaterAnimal(String name,
                       double weight,
                       double speed,
                       Medal[] medalArray,
                       Point location,
                       double totalDistance,
                       Animal.Gender gender,
                       int MAX_DIVE,
                       double diveDept,
                       CompetitionPanel panel,
                       String imagePath,
                       String imagePath2,
                       String imagePath3,
                       String imagePath4)
    {
        super(name, weight, speed, medalArray, location, totalDistance, gender,panel, imagePath,imagePath2,imagePath3,imagePath4);
        this.diveDept = diveDept;
    }

    public WaterAnimal clone()
    {
        WaterAnimal clone  = (WaterAnimal) super.clone();
        clone.diveDept = this.diveDept;
        return clone;
    }

    /**
     * Method to make the water animal dive.
     *
     * @param distance the distance to dive
     * @return true if the dive was successful, false otherwise
     */
    public boolean dive(double distance) {
        if (distance > 0)
            distance = distance * -1;

        if (diveDept + distance >= MAX_DIVE) {
            diveDept += distance;
            return true;
        }
        return false;
    }

    /**
     * Compares this water animal to another object for equality.
     *
     * @param obj the object to compare with
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (!(obj instanceof WaterAnimal))
            return false;

        if (!super.equals(obj))
            return false;

        WaterAnimal other = (WaterAnimal) obj;
        return Double.compare(other.diveDept, diveDept) == 0;
    }

    /**
     * Returns a string representation of the water animal.
     *
     * @return a string containing the water animal's details
     */
    @Override
    public String toString() {
        return super.toString() + ", diveDept: " + diveDept;
    }

    /**
     * Abstract method to get the individual sound of the water animal.
     *
     * @return the sound of the water animal
     */
    protected abstract String getIndividualSound();

    @Override
    public void move()
    {
        System.out.println("move water");

        Point position = getLocation();
        int speed = (int) getSpeed();

        int panelWidth = competitionPanelToPrint.getBackgroundImage().getWidth();

        if (orien == Orientation.EAST)
        {
            position.setX(position.getX() + speed);

            if (position.getX() >= panelWidth - getSize()*6)
                position.setX(panelWidth - getSize()*6);

        }
    }
}
