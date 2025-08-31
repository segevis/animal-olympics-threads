package Animals;

import Graphics.CompetitionPanel;
import Mobility.Point;
import Olympics.Medal;

public class ConcreteTerrestrialAnimal extends TerrestrialAnimal {
    public ConcreteTerrestrialAnimal() {
        super();
    }

    public ConcreteTerrestrialAnimal(String name,
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
                                     String imagePath4) {
        super(name, weight, speed, medalArray, location, totalDistance, gender, numberOfLegs, panel, imagePath, imagePath2, imagePath3, imagePath4);
    }

    @Override
    protected String getIndividualSound() {
        return ""; // Define the specific sound for this terrestrial animal
    }

    @Override
    public void loadImages(String nm) {
        // Implement image loading logic here
    }
}
