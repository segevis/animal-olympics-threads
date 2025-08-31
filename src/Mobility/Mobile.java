package Mobility;

/**
 * Mobile is an abstract class representing mobile objects with location and distance capabilities.
 * It implements the ILocatable interface.
 */
public abstract class Mobile implements ILocatable {
    // Attributes
    private Point location;
    private double totalDistance;

    /**
     * Constructor for Mobile objects.
     *
     * @param location      the initial location of the mobile object
     * @param totalDistance the total distance traveled by the mobile object
     */
    public Mobile(Point location, double totalDistance) {
        this.location = location;
        this.totalDistance = totalDistance;
    }


    /**
     * Retrieves the current location of the mobile object.
     *
     * @return the current location of the mobile object
     */
    @Override
    public Point getLocation()
    {
        return location;
    }

    /**
     * Retrieves the total distance traveled by the mobile object.
     *
     * @return the total distance traveled by the mobile object
     */
    public double getTotalDistance() {
        return totalDistance;
    }

    /**
     * Sets the current location of the mobile object.
     *
     * @param location the new location to set
     * @return true if the location was set successfully, false otherwise
     */
    @Override
    public Boolean setLocation(Point location) {
        this.location = location;
        return true;
    }

    /**
     * Sets the total distance traveled by the mobile object.
     *
     * @param totalDistance the new total distance to set
     * @return true if the total distance was set successfully, false otherwise
     */
    public boolean setTotalDistance(double totalDistance) {
        this.totalDistance = totalDistance;
        return true;
    }

    /**
     * Adds a specified distance to the total distance traveled by the mobile object.
     *
     * @param totalDistance the distance to add to the total distance
     */
    public void addTotalDistance(double totalDistance) {
        this.totalDistance += totalDistance;
    }

    /**
     * Calculates the Euclidean distance between the current location of the mobile object and a given point.
     *
     * @param p1 the point to calculate the distance to
     * @return the Euclidean distance between the current location and the given point
     */
    public double calcDistance(Point p1) {
        int xDifference = location.getX() - p1.getX();
        int yDifference = location.getY() - p1.getY();
        return Math.sqrt(xDifference * xDifference + yDifference * yDifference);
    }

    /**
     * Compares this mobile object with another object for equality.
     *
     * @param obj the object to compare with
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (!(obj instanceof Mobile))
            return false;

        Mobile m = (Mobile) obj;

        return location.equals(m.location) && totalDistance == m.totalDistance;
    }

    /**
     * Returns a string representation of the mobile object, including its location and total distance.
     *
     * @return a string representation of the mobile object
     */
    @Override
    public String toString()
    {
        return "Location: (" + location.getX() + ", " + location.getY() + "), Total Distance: " + totalDistance;
    }
}
