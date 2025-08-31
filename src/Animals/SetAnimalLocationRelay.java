package Animals;

import Animals.Animal;
import Graphics.CompetitionPanel;
import Mobility.Point;

public class SetAnimalLocationRelay {

    public SetAnimalLocationRelay(Animal[] animalGroup, CompetitionPanel pan, int groupSize) {
        if (animalGroup == null) {
            throw new NullPointerException("animalGroup is null");
        }

        groupTypeSwitch(animalGroup, pan, groupSize);
    }

    public void groupTypeSwitch(Animal[] animalGroup, CompetitionPanel pan, int groupSize) {
        switch (pan.getCompetitionType()) {
            case "Land":
                terrestrialSwitch(animalGroup, pan, groupSize);
                break;
            case "Water":
                waterSwitch(animalGroup, pan, groupSize);
                break;
            case "Air":
                airSwitch(animalGroup, pan, groupSize);
                break;
            default:
                throw new IllegalArgumentException("Unknown competition type: " + pan.getCompetitionType());
        }
    }

    private void terrestrialSwitch(Animal[] animalGroup, CompetitionPanel pan, int groupSize) {
        if (groupSize == 0) {
            throw new IllegalArgumentException("groupSize must be greater than 0");
        }

        // Ensure there are no more animals than groupSize
        int actualGroupSize = Math.min(groupSize, animalGroup.length);
        int laneWidth = pan.getWidth() / groupSize;

        for (int i = 0; i < actualGroupSize; i++) {
            if (i >= animalGroup.length) break;
            animalGroup[i].setLocation(new Point(i * laneWidth, 0));
        }
    }

    private void waterSwitch(Animal[] animalGroup, CompetitionPanel pan, int groupSize) {
        if (groupSize == 0) {
            throw new IllegalArgumentException("groupSize must be greater than 0");
        }

        // Ensure there are no more animals than groupSize
        int actualGroupSize = Math.min(groupSize, animalGroup.length);
        int laneWidth = pan.getWidth() / groupSize;

        for (int i = 0; i < actualGroupSize; i++) {
            if (i >= animalGroup.length) break;
            animalGroup[i].setLocation(new Point(i * laneWidth, 0));
        }
    }

    private void airSwitch(Animal[] animalGroup, CompetitionPanel pan, int groupSize) {
        if (groupSize == 0) {
            throw new IllegalArgumentException("groupSize must be greater than 0");
        }

        // Ensure there are no more animals than groupSize
        int actualGroupSize = Math.min(groupSize, animalGroup.length);
        int laneWidth = pan.getWidth() / groupSize;

        for (int i = 0; i < actualGroupSize; i++) {
            if (i >= animalGroup.length) break;
            animalGroup[i].setLocation(new Point(i * laneWidth, 0));
        }
    }
}
