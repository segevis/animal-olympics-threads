package Animals;

import Graphics.CompetitionPanel;
import Mobility.Point;
import Olympics.Medal;

/**
 * AirAnimal is an abstract class representing animals that can fly.
 * It extends the Animal class and includes attributes and methods specific to air animals.
 */
public abstract class AirAnimal extends Animal {
    // Attributes
    private double wingspan;

    /**
     * Default constructor for AirAnimal.
     * Initializes the wingspan to 0.
     */
    public AirAnimal() {
        super();
        wingspan = 0;
    }

    /**
     * Parameterized constructor for AirAnimal.
     *
     * @param name           the name of the air animal
     * @param weight         the weight of the air animal
     * @param speed          the speed of the air animal
     * @param medalArray     the array of medals the air animal has won
     * @param location       the current location of the air animal
     * @param totalDistance  the total distance the air animal has traveled
     * @param gender         the gender of the air animal
     * @param wingspan       the wingspan of the air animal
     */
    public AirAnimal(String name,
                     double weight,
                     double speed,
                     Medal[] medalArray,
                     Point location,
                     double totalDistance,
                     Gender gender,
                     double wingspan,
                     CompetitionPanel panel,
                     String imagePath,
                     String imagePath2,
                     String imagePath3,
                     String imagePath4)
    {
        super(name, weight, speed, medalArray, location, totalDistance, gender ,panel ,imagePath,imagePath2,imagePath3,imagePath4);
        this.wingspan = wingspan;
    }

    public AirAnimal clone()
    {
        AirAnimal clone = ( AirAnimal ) super.clone();
        clone.wingspan = wingspan;
        return clone;
    }

    /**
     * Compares this air animal to another object for equality.
     *
     * @param obj the object to compare with
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;

        if (!(obj instanceof AirAnimal))
            return false;

        if (!super.equals(obj))
            return false;

        AirAnimal other = (AirAnimal) obj;
        return Double.compare(other.wingspan, wingspan) == 0;
    }

    /**
     * Returns a string representation of the air animal.
     *
     * @return a string containing the air animal's details
     */
    @Override
    public String toString() {
        return super.toString() + ", wingspan: " + wingspan;
    }

    /**
     * Abstract method to get the individual sound of the air animal.
     *
     * @return the sound of the air animal
     */
    protected abstract String getIndividualSound();

    @Override
    public void move() {
        System.out.println("move air");

        Point position = getLocation();
        int speed = (int) getSpeed();

        int panelWidth = competitionPanelToPrint.getBackgroundImage().getWidth();

        if (orien == Orientation.EAST)
        {
            position.setX(position.getX() + speed);

            if (position.getX() >= panelWidth - (getSize()*4))
                position.setX(panelWidth - (getSize()*4));
        }
    }
}
