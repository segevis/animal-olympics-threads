package Animals;

import Graphics.CompetitionPanel;
import Mobility.Point;
import Olympics.Medal;

/**
 * TerrestrialAnimal is an abstract class representing animals that live on land.
 * It extends the Animal class and includes attributes and methods specific to terrestrial animals.
 */
public abstract class TerrestrialAnimal extends Animal {
    // Attributes
    private int numberOfLegs;

    /**
     * Default constructor for TerrestrialAnimal.
     * Initializes the number of legs to 0.
     */
    public TerrestrialAnimal() {
        super();
        numberOfLegs = 0;
    }

    /**
     * Parameterized constructor for TerrestrialAnimal.
     *
     * @param name           the name of the terrestrial animal
     * @param weight         the weight of the terrestrial animal
     * @param speed          the speed of the terrestrial animal
     * @param medalArray     the array of medals the terrestrial animal has won
     * @param location       the current location of the terrestrial animal
     * @param totalDistance  the total distance the terrestrial animal has traveled
     * @param gender         the gender of the terrestrial animal
     * @param numberOfLegs   the number of legs of the terrestrial animal
     */
    public TerrestrialAnimal(String name,
                             double weight,
                             double speed,
                             Medal[] medalArray,
                             Point location,
                             double totalDistance,
                             Gender gender,
                             int numberOfLegs,
                             CompetitionPanel panel,
                             String imagePath,
                             String imagePath2,
                             String imagePath3,
                             String imagePath4)
    {
        super(name, weight, speed, medalArray, location, totalDistance, gender, panel, imagePath, imagePath2, imagePath3, imagePath4);
        this.numberOfLegs = numberOfLegs;
    }

    @Override
    public TerrestrialAnimal clone()
    {
        TerrestrialAnimal clone = (TerrestrialAnimal) super.clone();
        clone.numberOfLegs = this.numberOfLegs;
        return clone;
    }


    /**
     * Compares this terrestrial animal to another object for equality.
     *
     * @param obj the object to compare with
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;

        if (!(obj instanceof TerrestrialAnimal))
            return false;

        if (!super.equals(obj))
            return false;

        TerrestrialAnimal other = (TerrestrialAnimal) obj;
        return numberOfLegs == other.numberOfLegs;
    }

    /**
     * Returns a string representation of the terrestrial animal.
     *
     * @return a string containing the terrestrial animal's details
     */
    @Override
    public String toString() {
        return super.toString() + ", numberOfLegs: " + numberOfLegs;
    }

    /**
     * Abstract method to get the individual sound of the terrestrial animal.
     *
     * @return the sound of the terrestrial animal
     */
    protected abstract String getIndividualSound();

    @Override
    public void move() {

        System.out.println("move land");

        Point position = getLocation();
        int speed = (int) getSpeed();

        int panelWidth = competitionPanelToPrint.getBackgroundImage().getWidth();
        int panelHeight = competitionPanelToPrint.getBackgroundImage().getHeight();

        // תנועה בהתאם לכיוון הנוכחי
        if (orien == Orientation.EAST)
        {
            position.setX(position.getX() + speed);
            // תנועה ימינה
            if (position.getX() >= panelWidth - (getSize() * 4))
            {
                position.setX(panelWidth - (getSize() * 4));
                orien = Orientation.SOUTH;
            }
        }
        else if (orien == Orientation.SOUTH)
        {
            position.setY(position.getY() + speed); // תנועה למטה
            if (position.getY() >= panelHeight - (getSize() * 2)) { // גבול תחתון
                position.setY(panelHeight - (getSize() * 2));
                orien = Orientation.WEST;
            }
        }
        else if (orien == Orientation.WEST)
        {
            position.setX(position.getX() - speed); // תנועה שמאלה
            if (position.getX() <= (getSize() * 2))
            { // גבול שמאלי
                position.setX(getSize());
                orien = Orientation.NORTH;
            }
        }
        else if (orien == Orientation.NORTH)
        {
            position.setY(position.getY() - speed); // תנועה למעלה
            if (position.getY() <= getSize() - speed)
            { // גבול עליון
                position.setY(getSize() - speed);
            }
        }
        setLocation(position);
    }
}
