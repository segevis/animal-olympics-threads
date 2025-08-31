package Graphics;

import Animals.Animal;

public class Competition {

    private String competitionType;
    private String competitionName;
    private String competitionDescription;
    private boolean regularCompetition;
    private boolean relayCompetition;
    private Animal[][] animalCompetitionArray;
    private boolean animalArrayExist;
    private int relayGroupSize = 0; // The size of each group in a relay race
    static int animalCounter = 0;


    public Competition(String competitionType, String competitionName, String competitionDescription) {
        this.animalArrayExist = false;
        this.animalCompetitionArray = null;
        this.competitionType = competitionType;
        this.competitionName = competitionName;
        this.competitionDescription = competitionDescription;

        if (competitionDescription.equalsIgnoreCase("Running competition")) {
            this.regularCompetition = true;
            this.relayCompetition = false;
        } else {
            this.regularCompetition = false;
            this.relayCompetition = true;
        }
    }

    public Animal[][] getAnimalCompetitionArray() {
        return animalCompetitionArray;
    }

    public String getCompetitionType() {
        return competitionType;
    }

    public String getCompetitionName() {
        return competitionName;
    }

    public String getCompetitionDescription() {
        return competitionDescription;
    }

    public int getAnimalCounter() {
        return animalCounter;
    }

    public int getArraySize() {

        if (this.animalCompetitionArray == null) {
            return 0;
        }
        return this.animalCompetitionArray.length;
    }

    public boolean isAnimalArrayExist() {
        return animalArrayExist;
    }

    public boolean isRegularCompetition() {
        return regularCompetition;
    }

    public boolean isRelayCompetition() {
        return relayCompetition;
    }

    // Set the animal array size for both regular and relay competitions
    public void setAnimalArraySize(int size, int relayGroupSize) {
        if (!animalArrayExist) {
            if (regularCompetition) {
                if (size > 0) {
                    animalCompetitionArray = new Animal[size][1];
                    animalArrayExist = true;
                } else {
                    System.out.println("Invalid size. The array size must be greater than 0.");
                }
            } else if (relayCompetition)
            {
                if (size > 0 && relayGroupSize > 0)
                {
                    this.relayGroupSize = relayGroupSize;
                    animalCompetitionArray = new Animal[size][relayGroupSize];
                    animalArrayExist = true;
                }
                else
                {
                    System.out.println("Invalid size or group size. Both must be greater than 0.");
                }
            }
        } else {
            System.out.println("Animal array already exists. Cannot create a new one.");
        }
    }

    // Add an animal to the competition array
    public void addAnimal(int laneIndex, int groupIndex, Animal temp) {

        if (animalArrayExist)
        {
            if (regularCompetition)
            {
                if (laneIndex >= 0 && laneIndex < animalCompetitionArray.length)
                {
                    if (animalCompetitionArray[laneIndex][0] == null) {
                        animalCompetitionArray[laneIndex][0] = temp;
                        animalCounter++;
                    }
                    else
                    {
                        System.out.println("Position already occupied.");
                    }
                } else {
                    System.out.println("Index out of bounds.");
                }
            }
            else if (relayCompetition)
            {
                if (laneIndex >= 0 && laneIndex < animalCompetitionArray.length && groupIndex >= 0 && groupIndex < relayGroupSize)
                {
                    if (animalCompetitionArray[laneIndex][groupIndex] == null)
                    {
                        animalCompetitionArray[laneIndex][groupIndex] = temp;
                        animalCounter++;
                    }
                    else
                    {
                        System.out.println("Position already occupied.");
                    }
                }
                else
                {
                    System.out.println("Index out of bounds.");
                }
            }
        }
        else
        {
            System.out.println("Animal array does not exist. Please create an array first.");
        }
    }

    @Override
    public String toString() {
        return competitionType + " " + competitionName + " " + competitionDescription;
    }
}
