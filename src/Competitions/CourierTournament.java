package Competitions;

import Animals.AirAnimal;
import Animals.Animal;
import Animals.TerrestrialAnimal;
import Animals.WaterAnimal;
import Graphics.CompetitionPanel;
import java.util.concurrent.atomic.AtomicBoolean;

public class CourierTournament extends Tournament {

    public CourierTournament(Animal[][] animals, CompetitionPanel panel) {
        super(animals, panel);
    }

    @Override
    protected void setup(Animal[][] animals, CompetitionPanel panel) {
        AtomicBoolean startFlag = new AtomicBoolean(false);
        Scores scores = new Scores();

        double runningLengthX = competitionPanel.getBackgroundImage().getWidth();
        double runningLengthY = competitionPanel.getBackgroundImage().getHeight();
        AnimalThread animalThread;

        for (Animal[] group : animals) {
            if (group == null || group.length == 0) {
                System.err.println("Group is null or empty.");
                continue;
            }

            int n = group.length;
            AtomicBoolean[] flags = new AtomicBoolean[n];
            for (int i = 0; i < n; i++) {
                flags[i] = new AtomicBoolean(false);
            }

            for (int i = 0; i < n; i++) {
                Animal animal = group[i];
                if (animal == null) {
                    System.err.println("Animal at position [" + i + "] is null.");
                    continue;
                }

                AtomicBoolean start = (i == 0) ? startFlag : flags[i - 1];
                AtomicBoolean finish = flags[i];

                if (animal instanceof WaterAnimal) {
                    // Adjust the length for water animals based on the group size
                    runningLengthX = competitionPanel.getBackgroundImage().getWidth() / group.length;
                    animalThread = new AnimalThread(animal, runningLengthX, start, finish);
                } else if (animal instanceof AirAnimal) {
                    animalThread = new AnimalThread(animal, runningLengthX, start, finish);
                } else if (animal instanceof TerrestrialAnimal) {
                    // Handle terrestrial animals if needed
                    continue;
                } else {
                    System.err.println("Unknown animal type.");
                    continue;
                }
                new Thread(animalThread).start();
            }

            String groupName = "Group_" + group.hashCode();
            // Ensure the last animal in the group is not null for referee
            if (group[n - 1] != null) {
                Referee referee = new Referee(groupName, group[n - 1].getName(), scores);
                new Thread(referee).start();
            } else {
                System.err.println("Last animal in group [" + groupName + "] is null.");
            }
        }

        tournamentThread = new TournamentThread(scores, startFlag, animals.length);
        new Thread(tournamentThread).start();
    }
}
