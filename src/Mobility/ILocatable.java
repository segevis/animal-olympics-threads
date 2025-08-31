package Mobility;

/**
 * The ILocatable interface represents objects that have a location and can update their location.
 */
public interface ILocatable {
    /**
     * Retrieves the current location of the object.
     *
     * @return the current location of the object
     */
    Point getLocation();

    /**
     * Sets the current location of the object.
     *
     * @param point the new location to set
     * @return true if the location was set successfully, false otherwise
     */
    Boolean setLocation(Point point);
}
