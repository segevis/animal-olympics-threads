package Animals;

/**
 * The IReptile interface represents the behaviors and attributes of a reptile.
 */
public interface IReptile {
    // Attributes
    static final int MAX_SPEED = 5; // Maximum speed constant for reptiles

    // Methods

    /**
     * Increases the speed of the reptile by the specified amount.
     *
     * @param speed the amount by which to increase the speed
     */
    void speedUp(int speed);
}
