package Animals;

import Graphics.CompetitionPanel;
import Mobility.Point;
import Olympics.Medal;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Alligator extends WaterAnimal {

    private String areaOfLiving; // Area where the alligator lives
    private TerrestrialAnimal T;

    public Alligator() {
        super();
        this.T = new ConcreteTerrestrialAnimal();
        this.areaOfLiving = "Florida"; // Default area of living for an alligator
        loadImages(null);
    }

    public Alligator(String areaOfLiving,
                     String name,
                     double weight,
                     double speed,
                     Medal[] medalArray,
                     Point location,
                     double totalDistance,
                     Animal.Gender gender,
                     int MAX_DIVE,
                     double diveDept,
                     int numberOfLegs, // Number of legs for the terrestrial representation
                     CompetitionPanel panel) {

        super(name, weight, speed, medalArray, location, totalDistance, gender, MAX_DIVE, diveDept, panel, "graphics2/alligator1.png", "graphics2/alligator2.png", null, null);
        this.areaOfLiving = areaOfLiving;
        this.T = new ConcreteTerrestrialAnimal(name, weight, speed, medalArray, location, totalDistance, gender, numberOfLegs, panel, "graphics2/alligator1.png", "graphics2/alligator2.png", null, null);
    }

    public Alligator clone() {
        Alligator clone = (Alligator) super.clone();
        clone.areaOfLiving = this.areaOfLiving;
        clone.T = (TerrestrialAnimal) this.T.clone(); // Clone the terrestrial animal representation
        return clone;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Alligator)) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }
        Alligator other = (Alligator) obj;
        return areaOfLiving.equals(other.areaOfLiving);
    }

    @Override
    public String toString() {
        return super.toString() + " Area of Living: " + areaOfLiving;
    }

    @Override
    protected String getIndividualSound() {
        return "Roar"; // Example sound made by an alligator
    }

    @Override
    public void loadImages(String nm) {
        try {
            BufferedImage img = ImageIO.read(new File("graphics2/alligator3.png"));
            setImg1(img);
        } catch (IOException e) {
            System.out.println("Cannot load image");
        }
    }
}
