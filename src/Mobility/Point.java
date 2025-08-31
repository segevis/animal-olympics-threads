package Mobility;

/**
 * The Point class represents a point in a 2-dimensional space with x and y coordinates.
 */
public class Point implements Cloneable {
    // Attributes
    private int x;
    private int y;

    /**
     * Constructs a Point object with specified x and y coordinates.
     *
     * @param x the x-coordinate of the point
     * @param y the y-coordinate of the point
     */
    public Point(int x, int y) {
        if (x >= 0 && y >= 0) {
            this.x = x;
            this.y = y;
        }
    }

    /**
     * Sets the x-coordinate of the point.
     *
     * @param x the new x-coordinate to set
     * @return true if the x-coordinate was set successfully, false otherwise
     */
    public boolean setX(int x) {
        if (x > 0)
        {
            this.x = x;
            return true;
        }
        return false;
    }

    /**
     * Sets the y-coordinate of the point.
     *
     * @param y the new y-coordinate to set
     * @return true if the y-coordinate was set successfully, false otherwise
     */
    public boolean setY(int y) {
        if (y > 0) {
            this.y = y;
            return true;
        }
        return false;
    }

    /**
     * Retrieves the x-coordinate of the point.
     *
     * @return the x-coordinate of the point
     */
    public int getX() {
        return x;
    }


    /**
     * Retrieves the y-coordinate of the point.
     *
     * @return the y-coordinate of the point
     */
    public int getY() {
        return y;
    }

    /**
     * Calculates the Euclidean distance between this point and another point.
     *
     * @param other the other point to calculate the distance to
     * @return the distance between this point and the other point
     */
    public double getDistance(Point other) {
        int dx = this.x - other.getX();
        int dy = this.y - other.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }




    @Override
    public Point clone() {
        try {
            Point clone = (Point) super.clone();
            clone.x = this.x;
            clone.y = this.y;
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
